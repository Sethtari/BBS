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
			<a href="newpost">新規投稿</a> <a href="management">ユーザー管理</a> <a
				href="logout">ログアウト</a>
		</div>

		<c:out value="${loginUser.getName()}" />さんとしてログインしています。
		<br />
		<br />
		<br />

		<c:forEach items="${userPosts}" var="userPost">
			<table border="3" width="80%" rules="rows">
				<tr>
					<td width="30">件名</td>
					<td><c:out value="${userPost.postsTitle}" /></td>
				</tr>
				<tr>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td width="70">本文</td>
					<td><c:out value="${userPost.postsText}" /></td>
				</tr>
				<tr>
					<td width="70">カテゴリ</td>
					<td><c:out value="${userPost.postsCategory}" /></td>
				</tr>
				<tr>
					<td width="70">投稿者</td>
					<td><c:out value="${userPost.usersName}" /></td>
				</tr>
				<tr>
					<td width="70">投稿日時</td>
					<td><fmt:formatDate value="${userPost.postsCreatedAt}"
							pattern="yyyy/MM/dd HH:mm:ss" /></td>
				</tr>
				<tr><td colspan="2"><form action="deletepost" method="get"><input type="hidden" name="deletePostId" value="${userPost.postsId}" /><button type="submit" name="deletePost">投稿を削除する</button></form></td></tr>

			</table>
			<br />
					<form action="comment" method="post">
					<input type="hidden" name="comenttedPostId" value="${userPost.postsId}" />

			<textarea name="text" cols="80%" rows="5" class="tweet-box"></textarea>
			<br />
			<input type="submit" value="コメントする">（500文字まで）</form><br /><br />

			<br />
		</c:forEach>

	</div>

</body>
</html>
