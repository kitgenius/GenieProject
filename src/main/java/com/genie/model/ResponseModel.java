package com.genie.model;
/*author:Genie
 *date:2017年10月12日
 *响应模型
 *status:标识请求是否成功
 *msg:状态原因说明
 *data:其他相关信息
 *code:状态码，G200-成功，参考http状态码，G+http状态码
**/
public class ResponseModel {
	boolean status;
	String msg;
	Object data;
	String code;
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	
	
}
