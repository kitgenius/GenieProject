<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js">
</script>
<title>Insert title here</title>
</head>
<body>
Welcome,<%=session.getAttribute("username").toString()%>!<p>
oldSessionId:<%=session.getAttribute("oldSessionId").toString()%><p>
newSessionId:<%=session.getAttribute("newSessionId").toString()%><p>
</body>
</html>