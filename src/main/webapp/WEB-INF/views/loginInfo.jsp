<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login Info</title>
</head>
<body>
<div id="info">
	Welcome,<%=session.getAttribute("username").toString()%>!<p>
	oldSessionId:<%=session.getAttribute("oldSessionId").toString()%><p>
	newSessionId:<%=session.getAttribute("newSessionId").toString()%><p>
</div>
<div>
	<a href="logout.do"><input type="button" name="logoutBtn" value="退出"></a>
</div>
	
	<div>
		<a href="userInfo.do"><input type="button" name="userInfoBtn" value="用户信息"></a>
	</div>

</body>
</html>