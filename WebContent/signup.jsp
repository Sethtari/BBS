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
		<form action="signup" method="post">
			<br /> <label for="login_id">ログインID</label> <input name="login_id"
				value="${login_id}" id="login_id" />（半角英数字で6文字以上20文字以内で入力してください）<br /> <label
				for="password">パスワード</label> <input name="password" type="password"
				id="password" /> <br />
				<label for="password_check">パスワード再確認</label>（誤入力防止にもう一度同じパスワードを入力してください）
			<input name="password_check" type="password" id="password_check" /><br />

			<label for="name">名称</label> <input name="name" value="${name}"
				id="name" />（名称は10文字以下で入力してください）<br />

			<label for="branch_id">支店</label>
						<select name="branch_id">
				<c:forEach items="${branches}" var="branch">
						<option value="${branch.id}">
							<c:out value="${branch.name}" />
						</option>
				</c:forEach>
			</select><br />

			<label for="position_id">部署・役職</label>

						<select name="position_id">
				<c:forEach items="${positions}" var="position">
						<option value="${position.id}">
							<c:out value="${position.name}" />
						</option>
				</c:forEach>
			</select><br />

			<input	type="submit" value="登録" /> <br /><br />
			<a href="./management">戻る</a>
		</form>
	</div>
</body>
</html>
