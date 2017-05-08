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
			<a href="./">ホーム画面へ</a> <a href="signup">ユーザーの新規登録画面へ</a>

		</div>

		<div class="userList">
			<table border="2">
				<tr>
					<td>ID</td>
					<td>ログインID</td>
					<td>名称</td>
					<td>支店</td>
					<td>部署・役職</td>
					<td colspan="3">ユーザー</td>
				</tr>
				<c:forEach items="${users}" var="user">
					<tr>
						<td><c:out value="${user.id}" /></td>
						<td><c:out value="${user.loginId}" /></td>
						<td><c:out value="${user.name}" /></td>
						<td><c:forEach items="${branches}" var="branch">
								<c:if test="${branch.id == user.branchId}">
									<c:out value="${branch.name}" />
						</c:if>
						</c:forEach>
						</td>
						<td><c:forEach items="${positions}" var="position">
								<c:if test="${position.id == user.positionId}">
									<c:out value="${position.name}" />
						</c:if>
						</c:forEach></td>
						<td><form action="management" method="post">
								<c:if test="${user.isStopped == '0'}">
									<input type="hidden" name="isStopped" value="1" /><button type="submit" name="id" value="${user.id}">停止</button>
								</c:if>
								<c:if test="${user.isStopped == '1'}">
									<input type="hidden" name="isStopped" value="0" /><button type="submit" name="id" value="${user.id}">復活</button>
								</c:if></form></td>

						<td><form action="settings" method="get">
								<button type="submit" name="settingsButton" value="${user.id}">編集</button>
							</form></td>

						<td><c:if test="${user.id != loginUser.getId()}"><form action="deleteuser" method="get">
								<input type="hidden" name="deleteId" value="${user.id}" /><button type="submit" name="deleteButton">削除</button>
							</form></c:if></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
