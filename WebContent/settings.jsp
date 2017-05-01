<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${editUser.loginID}の設定</title>
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
	<c:remove var="errorMessages" scope="session"/>
</c:if>


<form action="settings" method="post"><br />
	<label for="login_id">ログインID</label>
	<input name="login_id" value="${editUser.loginID}" ID="login_id"/>名前はあなたの公開プロフィールに表示されます）<br />

	<label for="password">パスワード</label>
	<input name="password" type="password" /> <br />

	<label for="password_check">パスワード再確認</label>（誤入力防止にもう一度同じパスワードを入力してください）
	<input name="password_check" type="password" id="password_check" /><br />

	<label for="name">名称</label>
	<input name="name" value="${editUser.name}" ID="name"/><br />

	<label for="branch_id">支店</label>
	<input name="branch_id" value="${editUser.branchID}" ID="branch_id"/><br />

	<label for="position_id">部署・役職</label>
	<input name="position_id" value="${editUser.positionID}" ID="position_id" /><br />

	<input type="submit" value="登録" /> <br />
	<a href="management">戻る</a>
</form>
</div>
</body>
</html>
