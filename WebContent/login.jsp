<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ログイン画面</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body><div class="bodies"><div  class="postTitle" align="left">ログイン</div><br /><br />


<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<div class="bg">
<form action="login" method="post">
<table><tr><td>
	ログインID</td><td><input name="loginId" value="${loginId}" id="loginId"/></td></tr>
	<tr><td>
	パスワード</td><td><input name="password" type="password" id="password"/></td></tr></table>
	<br />
	<button type="submit">ログイン</button> <br />
</form>
</div></div>
</body>
</html>
