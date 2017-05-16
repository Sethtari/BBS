<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${editUser.loginId}の設定</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body><div class="bodies"><div class="postTitle" align="left">編集</div><br />

	<div  class="menuTitle"><a href="management">戻る</a></div><br />
		<c:if test="${ not empty errorMessages }">
			<div class="errorMessages">
				<ul>
					<c:forEach items="${errorMessages}" var="message">
						<li><c:out value="${message}" />
					</c:forEach>
				</ul>
			</div>
			<c:remove var="errorMessages" scope="session" />
		</c:if>
<div class="bg">
		<form action="settings" method="post">
		<table border="1" bordercolor="#cccccc" bgcolor="#FFFFFF"><tr><td>
			ログインID<br />
			(6字以上20字以内の半角英数)</td><td><input name="loginId" value="${editUser.loginId}" ID="loginId" /></td></tr>
			<tr><td>パスワード<br />
			(6字以上255字以内の半角英数)</td><td><input name="password" type="password" /></td></tr>
			<tr><td>パスワード再確認<br />
			(確認のため再度パスワードを入力)</td><td>
			<input name="passwordCheck" type="password" id="passwordCheck" /></td></tr>

			<tr><td>名称<br/>
			(10字以内)</td><td><input name="name" value="${editUser.name}" ID="name" /></td></tr>

			<c:if test="${editUser.id != loginUser.getId()}">
			<tr><td>支店</td><td><select name="branchId">
				<c:forEach items="${branches}" var="branch">
					<c:if test="${branch.id == editUser.branchId }">
						<option value="${branch.id}" selected>
							<c:out value="${branch.name}" />
						</option>
					</c:if>
					<c:if test="${branch.id != editUser.branchId }">
						<option value="${branch.id}">
							<c:out value="${branch.name}" />
						</option>
					</c:if>
				</c:forEach>
			</select></td></tr>


			<tr><td>部署・役職</td><td><select name="positionId">
				<c:forEach items="${positions}" var="position">
					<c:if test="${position.id == editUser.positionId }">
						<option value="${position.id}" selected>
							<c:out value="${position.name}" />
						</option>
					</c:if>
					<c:if test="${position.id != editUser.positionId }">
						<option value="${position.id}">
							<c:out value="${position.name}" />
						</option>
					</c:if>
				</c:forEach>
			</select></td></tr>
			</c:if></table>
			<br /> <input type="submit" value="登録" /> <br />
		</form></div>
</div></body>
</html>
