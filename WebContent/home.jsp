<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ホーム画面</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body><div class="bodies"><div class="postTitle">ホーム</div><br />

					<div class="menuTitle"><a href="newpost" class="categoryTitle">新規投稿</a></div>
			<c:if test="${loginUser.getPositionId().equals('1')}">
				<div class="menuTitle"><a href="management" class="categoryTitle">ユーザー管理</a></div>
			</c:if>
			<div class="menuTitle"><a href="logout" class="categoryTitle">ログアウト</a></div>
					<br />
					　　<b><c:out value="${loginUser.getName()}" />さんとしてログインしています。</b><br />
		<c:if test="${ not empty errorMessages }">
			<div class="errorMessages">
				<ul>
					<c:forEach items="${errorMessages}" var="message">
						<li><c:out value="${message}" /></li>
					</c:forEach>
				</ul>
			</div>
			<c:remove var="errorMessages" scope="session" />
		</c:if><div class="bg">
 <form action="./" method="get">
			カテゴリー : <select name="category">
				<option value="">
					<c:forEach items="${categoryList}" var="categories">
					<c:if test="${categories.postsCategory.equals(category)}">
					<option value="${categories.postsCategory}" selected /></c:if>

					<c:if test="${!categories.postsCategory.equals(category)}">
					<option value="${categories.postsCategory}" /></c:if>
					<c:out value="${categories.postsCategory}" />
					</c:forEach>
			</select><br /><br />
			日付指定 :<input type="date" name="dateMin" value="${dateMin }"> ～
			<input type="date" name="dateMax" value="${dateMax }"><br /><br />
			<button type="submit">絞り込む</button></form>
		</div><hr />




		<c:forEach items="${userPosts}" var="userPost">

			<br />
			<br />


					<div class="post">
					<div class="postTitle"><c:out value="${userPost.postsTitle}" /></div><br />

					<div class="postBg">
					<div class="element"><c:forEach var="s" items="${fn:split(userPost.postsText, '
')}">
<div class="underbar">${s}</div>
</c:forEach></div><br /><br />
<font class="underbar">カテゴリ : <c:out value="${userPost.postsCategory}" /><br />
投稿者   : <c:out value="${userPost.usersName}" /><br />
投稿日時 : <fmt:formatDate value="${userPost.postsCreatedAt}" pattern="yyyy/MM/dd HH:mm:ss" />

				<c:if
					test="${loginUser.getId() == userPost.usersId || loginUser.getPositionId().equals('2')}">
<script>
									function submitChk() {
										/* 確認ダイアログ表示 */
										var flag = confirm("本当に削除しますか？\n\n削除を取り消す場合は[キャンセル]ボタンを押して下さい");
										/* send_flg が TRUEなら送信、FALSEなら送信しない */
										return flag;
									}
								</script>

								<form action="deletepost" method="post"
									onsubmit="return submitChk()">									<input type="hidden" name="deletePostId"
										value="${userPost.postsId}" />
									<button type="submit" name="deletePost">投稿を削除する</button>
								</form></c:if>

				<c:if
					test="${loginUser.getPositionId().equals('3') && userPost.usersPositionId.equals('4') && loginUser.getBranchId().equals(userPost.usersBranchId)}">
<script>
									function submitChk() {
										/* 確認ダイアログ表示 */
										var flag = confirm("本当に削除しますか？\n\n削除を取り消す場合は[キャンセル]ボタンを押して下さい");
										/* send_flg が TRUEなら送信、FALSEなら送信しない */
										return flag;
									}
								</script>

								<form action="deletepost" method="post"
									onsubmit="return submitChk()">
									<input type="hidden" name="deletePostId"
										value="${userPost.postsId}" />
									<button type="submit" name="deletePost">投稿を削除する</button>
								</form></c:if>
</font></div>


			<br /><br />
			<c:forEach items="${userComments}" var="userComment">
				<c:if test="${userComment.postsId == userPost.postsId}">
					<div class="comment">
<div class="element"><c:forEach var="s" items="${fn:split(userComment.commentsText, '
')}">
<div class="underbar">${s}</div>
</c:forEach></div>
<font class="underbar"><br />
<br />
投稿者   : <c:out value="${userComment.usersName}" /><br />
投稿日時 : <fmt:formatDate value="${userComment.commentsCreatedAt}" pattern="yyyy/MM/dd HH:mm:ss" /><br /></font>
<c:if test="${loginUser.getId() == userComment.usersId || loginUser.getPositionId().equals('2') ||( loginUser.getPositionId().equals('3') && userComment.usersPositionId.equals('4') && loginUser.getBranchId().equals(userComment.usersBranchId))}">

<script>
									function submitChk() {
										/* 確認ダイアログ表示 */
										var flag = confirm("本当に削除しますか？\n\n削除を取り消す場合は[キャンセル]ボタンを押して下さい");
										/* send_flg が TRUEなら送信、FALSEなら送信しない */
										return flag;
								</script>
								<form action="deletecomment" method="post"
									onsubmit="return submitChk()">
<input type="hidden" name="deleteCommentId" value="${userComment.commentsId}" />
<button type="submit" name="deleteComment">コメントを削除する</button>
										</form>
						</c:if><br /></div>
			<br />
					<br />
				</c:if>
			</c:forEach>
			<br />
			<form action="newcomment" method="post">
				<input type="hidden" name="comenttedPostId" value="${userPost.postsId}" />

				<textarea name="text" cols="100" rows="5"><c:if test="${userPost.postsId == missCommentId}"><c:out value="${text}" /><c:remove var="text" scope="session" /></c:if></textarea>
				<br /> <input type="submit" value="コメントする">（500文字まで）
			</form>
		<br /></div><br /><br /></c:forEach>
					<br />
<br />

		<c:remove var="text" scope="session" /></div>
</body>
</html>
