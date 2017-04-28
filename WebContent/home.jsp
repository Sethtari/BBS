<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ホーム画面</title>
</head>
<body>
	<div class="main-contents">

		<div class="header">
			<a href="post">新規投稿</a> <a href="management">ユーザー管理</a> <a href="logout">ログアウト</a>
		</div>

		<div class="messages">
			<c:forEach items="${messages}" var="message">
				<div class="message">
					<div class="account-name">
						<span class="account"><c:out value="${message.account}" /></span>
						<span class="name"><c:out value="${message.name}" /></span>
					</div>
					<div class="text">
						<c:out value="${message.text}" />
					</div>
					<div class="date">
						<fmt:formatDate value="${message.insertDate}"
							pattern="yyyy/MM/dd HH:mm:ss" />
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>
