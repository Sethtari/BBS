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
		<div class="header">
			<a href="newpost">新規投稿</a>
			<c:if test="${loginUser.getPositionId().equals('1')}">
				<a href="management">ユーザー管理</a>
			</c:if>
			<a href="logout">ログアウト</a>
		</div>
		<br />
		<br />

		<div align="center">
			<c:out value="${loginUser.getName()}" />
			さんとしてログインしています。 <br />
		</div>
		<br />
		<hr />
		<br /> カテゴリーフィルター :
		<form action="./" method="post">
			<select name="category">
			<option value="all"><c:out value="条件なし" />
				<c:forEach items="${posts}" var="post">
						<option value="${post.postsCategory}">
							<c:out value="${post.postsCategory}" />
				</c:forEach>
			</select>
			<button type="submit" name="choiseCategory">絞り込む</button>
		</form>
		<br /> 日時指定 : 2017/4/1 ～ 2017/5/9<br />

		<c:forEach items="${userPosts}" var="userPost">
			<br />
			<hr />
			<br />
			<br />
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
				<c:if
					test="${loginUser.getId() == userPost.usersId || loginUser.getPositionId().equals('2')}">
					<tr>
						<td colspan="2"><div align="right">
								<form action="deletepost" method="post">
									<input type="hidden" name="deletePostId"
										value="${userPost.postsId}" />
									<button type="submit" name="deletePost">投稿を削除する</button>
								</form>
							</div></td>
					</tr>
				</c:if>

				<c:if
					test="${loginUser.getPositionId().equals('3') && userPost.usersPositionId.equals('4') && loginUser.getBranchId().equals(userPost.usersBranchId)}">
					<tr>
						<td colspan="2"><div align="right">
								<form action="deletepost" method="post">
									<input type="hidden" name="deletePostId"
										value="${userPost.postsId}" />
									<button type="submit" name="deletePost">投稿を削除する</button>
								</form>
							</div></td>
					</tr>
				</c:if>

			</table>
			<br />
			<c:forEach items="${userComments}" var="userComment">
				<c:if test="${userComment.postsId == userPost.postsId}">
					<table border="1" width="70%" rules="rows" align="right">
						<tr>
						<tr>
							<td width="70">本文</td>
							<td><c:out value="${userComment.commentsText}" /></td>
						</tr>

						<tr>
							<td width="70">投稿者</td>
							<td><c:out value="${userComment.usersName}" /></td>
						</tr>
						<tr>
							<td width="70">投稿日時</td>
							<td><fmt:formatDate value="${userComment.commentsCreatedAt}"
									pattern="yyyy/MM/dd HH:mm:ss" /></td>
						</tr>
						<c:if
							test="${loginUser.getId() == userComment.usersId || loginUser.getPositionId().equals('2') ||( loginUser.getPositionId().equals('3') && userComment.usersPositionId.equals('4') && loginUser.getBranchId().equals(userComment.usersBranchId))}">
							<tr>
								<td colspan="2">
									<div align="right">
										<form action="deletecomment" method="post">
											<input type="hidden" name="deleteCommentId"
												value="${userComment.commentsId}" />
											<button type="submit" name="deleteComment">コメントを削除する</button>
										</form>
									</div>
								</td>
							</tr>
						</c:if>

					</table>
					<br />
				</c:if>
			</c:forEach>
			<br />
			<form action="newcomment" method="post">
				<input type="hidden" name="comenttedPostId"
					value="${userPost.postsId}" />

				<textarea name="text" cols="80%" rows="5"><c:if
						test="${userPost.postsId==missCommentId}">
						<c:out value="${text}" />
						<c:remove var="text" scope="session" />
					</c:if></textarea>
				<br /> <input type="submit" value="コメントする">（500文字まで）
			</form>
		</c:forEach>
		<br /> <br />

		<c:remove var="text" scope="session" />
	</div>
</body>
</html>
