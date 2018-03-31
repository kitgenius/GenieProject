package com.genie.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.util.Assert;

import com.genie.dao.PropertyFilter.MatchType;

/**
 * 
 * @Description : PageGenericDao 接口的实现类
 * @author cc
 */
public class HibernateDao<T, PK extends Serializable> extends HibernateGenericDao<T, PK>
		implements PageGenericDao<T, PK> {
	/**
	 * 
	 * Description : HibernateDao构造函数
	 * @return
	 * 
	 */
	public HibernateDao() {
		super();
	}
	/**
	 * 
	 * Description : HibernateDao带参数的构造函数
	 * 
	 * @param SessionFactory
	 *            Session对象
	 * @param entityClass
	 *            实体类
	 * @return
	 * 
	 */
	public HibernateDao(final SessionFactory sessionFactory, final Class<T> entityClass) {
		super(sessionFactory, entityClass);
	}

	/**
	 * 
	 * Description : HQL分页查询
	 * 
	 * @param Page
	 *            分页对象
	 * @param hql
	 *            HQL语句
	 * @param Object
	 *            参数值
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Page<T> queryPage(Page<T> page, String hql, Object... values) {
		// TODO Auto-generated method stub
		Assert.notNull(page, "page不能为空");
		Query q = createQuery(hql, values);
		int totalCount = countHqlResult(hql, values);
		page.setTotalCount(totalCount);
		setPageParameter(q, page);
		List<T> result = q.list();
		page.setResultList(result);
		return page;
	}

	/**
	 * 
	 * Description : HQL分页查询
	 * 
	 * @param Page
	 *            分页对象
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            map对象参数值
	 * @return
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Page<T> queryPage(Page<T> page, String hql, Map<String, Object> values) {
		// TODO Auto-generated method stub
		Assert.notNull(page, "page不能为空");
		Query q = createQuery(hql, values);
		int totalCount = countHqlResult(hql, values);
		page.setTotalCount(totalCount);
		setPageParameter(q, page);
		List<T> result = q.list();
		page.setResultList(result);
		return page;
	}

	/**
	 * 
	 * Description : 按Criteria分页查询
	 *
	 * @param page
	 *            分页对象
	 * @param criterions
	 *            条件
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	public Page<T> queryPage(Page<T> page, Criterion... criterions) {
		// TODO Auto-generated method stub
		Assert.notNull(page, "page不能为空");
		Criteria c = createCriteria(criterions);
		int totalCount = countCriteriaResult(c);
		page.setTotalCount(totalCount);
		setPageParameter(c, page);
		List<T> result = c.list();
		page.setResultList(result);
		return page;
	}

	/**
	 * 
	 * Description : 按属性过滤条件列表分页查找对象
	 *
	 * @param page
	 *            分页对象
	 * @param filters
	 *            属性过滤条件
	 * @return
	 *
	 */
	public Page<T> queryPage(final Page<T> page, final List<PropertyFilter> filters) {
		Criterion[] criterions = buildPropertyFilterCriterions(filters);
		return queryPage(page, criterions);
	}

	/**
	 * 
	 * Description : 执行count查询获得本次Criteria查询所能获得的对象总数
	 *
	 * @param c
	 *            Criteria对象
	 * @return
	 *
	 */
	@SuppressWarnings("unchecked")
	public int countCriteriaResult(Criteria c) {
		// TODO Auto-generated method stub
		CriteriaImpl impl = (CriteriaImpl) c;
		// 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();
		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List<CriteriaImpl.OrderEntry>) ReflectionUtils.getFieldValue(impl, "orderEntries");
			ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}
		// 执行Count查询
		int totalCount = (Integer) c.setProjection(Projections.rowCount()).uniqueResult();
		// 将之前的Projection,ResultTransformer和OrderBy条件重新设回去
		c.setProjection(projection);
		if (projection == null) {
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			c.setResultTransformer(transformer);
		}
		try {
			ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}
		return totalCount;
	}

	/**
	 * 
	 * Description : 执行count查询获得本次Hql查询所能获得的对象总数
	 *
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            查询值
	 * @return
	 *
	 */
	public int countHqlResult(String hql, Object... values) {
		String fromHql = hql;
		// select子句与order by子句会影响count查询,进行简单的排除.
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");
		String selectHql = "*";
		// 如果含有 distinct 描述符，进行简单处理
		if (StringUtils.indexOf(hql, "distinct") != -1) {
			selectHql = StringUtils.substringBetween(hql, "select", "from").trim();
		}
		String countHql = "select count(" + selectHql + ") " + fromHql;
		try {
			Long count = findUnique(countHql, values);
			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
		}
	}

	/**
	 * 
	 * Description : 执行count查询获得本次Hql查询所能获得的对象总数
	 *
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            map对象查询值
	 * @return
	 *
	 */
	public int countHqlResult(String hql, Map<String, Object> values) {
		String fromHql = hql;
		// select子句与order by子句会影响count查询,进行简单的排除.
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");
		String countHql = "select count(*) " + fromHql;
		try {
			Long count = findUnique(countHql, values);
			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
		}
	}

	/**
	 * 
	 * Description : 设置分页参数到Query对象,辅助函数
	 * 
	 * @param q
	 * @param page
	 * @return
	 * 
	 */
	@SuppressWarnings("static-access")
	protected Query setPageParameter(final Query q, final Page<T> page) {
		// 如果每页显示的记录数 为-1，查询所有记录 ，不做分页
		if (page.getPageSize() != page.ALL_SIZE) {
			q.setFirstResult(page.getBeginCount());
			q.setMaxResults(page.getPageSize());
		}
		return q;
	}

	/**
	 * 
	 * Description : 设置分页参数到Criteria对象,辅助函数
	 * 
	 * @param c
	 * @param page
	 * @return
	 * 
	 */
	protected Criteria setPageParameter(final Criteria c, final Page<T> page) {
		c.setFirstResult(page.getCurrentPage() - 1);
		c.setMaxResults(page.getPageSize());
		// if (page.isOrderBySetted()) {
		// String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
		// String[] orderArray = StringUtils.split(page.getOrder(), ',');
		//
		// Assert.isTrue(orderByArray.length == orderArray.length,
		// "分页多重排序参数中,排序字段与排序方向的个数不相等");
		//
		// for (int i = 0; i < orderByArray.length; i++) {
		// if (Page.ASC.equals(orderArray[i])) {
		// c.addOrder(Order.asc(orderByArray[i]));
		// } else {
		// c.addOrder(Order.desc(orderByArray[i]));
		// }
		// }
		// }
		return c;
	}

	/**
	 * 
	 * Description : 按属性条件列表创建Criterion数组,辅助函数
	 * 
	 * @param filters
	 * @return
	 * 
	 */
	protected Criterion[] buildPropertyFilterCriterions(final List<PropertyFilter> filters) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		for (PropertyFilter filter : filters) {
			if (!filter.isMultiProperty()) { // 只有一个属性需要比较的情况.
				Criterion criterion = buildPropertyFilterCriterion(filter.getPropertyName(), filter.getPropertyValue(),
						filter.getMatchType());
				criterionList.add(criterion);
			} else {// 包含多个属性需要比较的情况,进行or处理.
				Disjunction disjunction = Restrictions.disjunction();
				for (String param : filter.getPropertyNames()) {
					Criterion criterion = buildPropertyFilterCriterion(param, filter.getPropertyValue(),
							filter.getMatchType());
					disjunction.add(criterion);
				}
				criterionList.add(disjunction);
			}
		}
		return criterionList.toArray(new Criterion[criterionList.size()]);
	}

	/**
	 * 
	 * Description : 按属性条件参数创建Criterion,辅助函数
	 * 
	 * @param propertyName
	 * @param propertyValue
	 * @param matchType
	 * @return
	 * 
	 */
	protected Criterion buildPropertyFilterCriterion(final String propertyName, final Object propertyValue,
			final MatchType matchType) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = null;
		try {
			// 根据MatchType构造criterion
			if (MatchType.EQ.equals(matchType)) {
				criterion = Restrictions.eq(propertyName, propertyValue);
			} else if (MatchType.LIKE.equals(matchType)) {
				criterion = Restrictions.like(propertyName, (String) propertyValue, MatchMode.ANYWHERE);
			} else if (MatchType.LE.equals(matchType)) {
				criterion = Restrictions.le(propertyName, propertyValue);
			} else if (MatchType.LT.equals(matchType)) {
				criterion = Restrictions.lt(propertyName, propertyValue);
			} else if (MatchType.GE.equals(matchType)) {
				criterion = Restrictions.ge(propertyName, propertyValue);
			} else if (MatchType.GT.equals(matchType)) {
				criterion = Restrictions.gt(propertyName, propertyValue);
			}
		} catch (Exception e) {
			throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
		}
		return criterion;
	}
}
