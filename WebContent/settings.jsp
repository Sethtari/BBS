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
<body>
	<div class="main-contents">

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


		<form action="settings" method="post">
			<br /> <label for="login_id">ログインID</label>
			<input name="login_id" value="${editUser.loginId}" ID="login_id" />

			<br />

			<label for="password">パスワード</label> <input name="password" type="password" />
			<br />

			<label for="passwordCheck">パスワード再確認</label>（誤入力防止にもう一度同じパスワードを入力してください）
			<input name="passwordCheck" type="password" id="passwordCheck" /><br />

			<label for="name">名称</label> <input name="name"
				value="${editUser.name}" ID="name" /><br />

			<label for="branch_id">支店</label>
			<select name="branch_id">
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
			</select><br />

			<label for="position_id">部署・役職</label>
			<select name="position_id">
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
			</select><br />

			<input type="submit" value="登録" /> <br /> <a href="management">戻る</a>
		</form>
	</div>
</body>
</html>
