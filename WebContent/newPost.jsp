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
<body><div class="bodies"><div  class="postTitle" align="left">新規投稿</div><br />


		<div class="menuTitle"><a href="./">戻る</a></div><br />
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
<form action="newpost" method="post"><table border="0"><tr><td>

				件名<br />（50文字以内）</td><td>
				<input name="title" value="${title}"><c:remove var="title" scope="session" /></td></tr><tr><td>
				本文<br />（1000文字以内）</td><td>
<textarea name="text" cols="100" rows="10"><c:out value="${text}" /></textarea></td></tr><tr><td>

			<c:remove var="text" scope="session" />
				カテゴリー<br />（10文字以内）</td><td>
				<input name="category" value="${category}"><c:remove var="category" scope="session" />
				<input type="hidden" name="loginId" value="${loginUser.getName()}" />
				</td></tr></table>
				<br /><button type="submit">新規投稿</button>
			</form></div>
</div></body>
</html>
