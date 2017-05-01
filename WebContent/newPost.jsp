<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿画面</title>
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

		<div class="form-area">

			<form action="newPost" method="post">
				件名（50文字以内で入力してください）
				<input name="title" value="${title}"><c:remove var="title" scope="session" /><br />
				<br/>
				本文（1000文字以内で入力してください）<br />
<textarea name="text" cols="50" rows="5"><c:out value="${text}" /></textarea>
				<br/>
			<c:remove var="text" scope="session" />
				カテゴリー（10文字以内で入力してください）
				<input name="category" value="${category}"><c:remove var="category" scope="session" />
				<br /> <input type="submit" value="新規投稿">
			</form>

		</div>

	</div>
</body>
</html>
