<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー管理画面</title>
<%--<link href="./css/style.css" rel="stylesheet" type="text/css">--%>
</head>
<body>
	<div class="main-contents">

		<div class="header">
			<a href="./home">ホーム画面へ</a> <a href="signup">ユーザーの新規登録画面へ</a>

		</div>

		<div class="userList">
		<table border ="2">
					<tr>
					<td>ID</td>
					<td>ログインID</td>
					<td>名称</td>
					<td>支店</td>
					<td>部署・役職</td>
					<td>ユーザー</td>
					</tr>
			<c:forEach items="${users}" var="user">
					<tr>
					<td><c:out value="${user.ID}" /></td>
					<td><c:out value="${user.loginID}" /></td>
					<td><c:out value="${user.name}" /></td>
					<td><c:out value="${user.branchID}" /></td>
					<td><c:out value="${user.positionID}" /></td>
					<td><form action="./management" method="post">
				<c:if test="${user.isStopped == '0'}"><button type="submit" name="isStopButton" value="${user.ID}1" >停止</button></c:if><c:if test="${user.isStopped == '1'}"><button type="submit" name="isStopButton" value="${user.ID}0" >復活</button></c:if></form><form action="./management" method="post"></form></td>
					</tr>
			</c:forEach></table>
		</div>
	</div>
</body>
</html>
