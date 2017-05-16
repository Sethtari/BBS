<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー登録</title>
<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body><div class="bodies"><div class="postTitle" align="left">新規登録</div><br />
<div class="menuTitle"><a href="./management">戻る</a></div>
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
<br /><br />
<div class="bg">
		<form action="signup" method="post"><table border="1" bordercolor="cccccc" bgcolor="#FFFFFF"><tr><td>
			ログインID<br />(6字以上20字以内の半角英数)</td><td><input name="newId"
				value="${newId}" id="newId" /></td></tr>
				<tr><td>パスワード<br />(6字以上255字以内の半角英数)</td><td><input name="password" type="password" id="password" /></td></tr>
				<tr><td>パスワード再確認<br />（確認のため再度パスワードを入力)</td><td>
			<input name="passwordCheck" type="password" id="passwordCheck" /></td></tr>
				<tr><td>名称<br />（10文字以内）</td><td><input name="newName" value="${newName}"
				id="newName" /></td></tr>
				<tr><td>
			支店</td><td><select name="branchId">
				<c:forEach items="${branches}" var="branch">
				<c:if test="${branch.id == branchId }">
					<option value="${branch.id}" selected>
						<c:out value="${branch.name}" />
					</option>
				</c:if>
				<c:if test="${branch.id != branchId }">
					<option value="${branch.id}">
						<c:out value="${branch.name}" />
					</option>
				</c:if>
				</c:forEach>
			</select></td></tr>
				<tr><td>部署・役職</td><td><select name="positionId">
				<c:forEach items="${positions}" var="position">
					<c:if test="${position.id == positionId }">
						<option value="${position.id}" selected>
							<c:out value="${position.name}" />
						</option>
					</c:if>

					<c:if test="${position.id != positionId }">
						<option value="${position.id}">
							<c:out value="${position.name}" />
						</option>
					</c:if>
				</c:forEach>
			</select></td></tr></table>
			<br />
			<button type="submit">登録する</button>

		</form></div></div>
		<c:remove var="newId" scope="session" />
		<c:remove var="positionId" scope="session" />
		<c:remove var="branchId" scope="session" />
		<c:remove var="newName" scope="session" />
</body>
</html>
