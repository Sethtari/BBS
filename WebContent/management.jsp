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
<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body><div class="bodies"><div  class="postTitle" align="left">ユーザー管理</div><br />


		<div class="menuTitle">
			<a href="./">ホーム画面へ</a></div>
			<div class="menuTitle"><a href="signup">ユーザーの新規登録画面へ</a>

		</div>
<br />		<c:if test="${ not empty errorMessages }">
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
			<table border="1" bordercolor="#cccccc" bgcolor="#FFFFFF">
				<tr align="center">
					<td><div class="postTitle">ID</div></td>
					<td>ログインID</td>
					<td>名称</td>
					<td>支店</td>
					<td>部署・役職</td>
					<td colspan="3">ユーザー</td>
				</tr>
				<c:forEach items="${users}" var="user">
					<tr align="center">
						<td><div class="management"><c:out value="${user.id}" /></div></td>
						<td><c:out value="${user.loginId}" /></td>
						<td><c:out value="${user.name}" /></td>
						<td><c:forEach items="${branches}" var="branch">
								<c:if test="${branch.id == user.branchId}">
									<c:out value="${branch.name}" />
								</c:if>
							</c:forEach></td>
						<td><c:forEach items="${positions}" var="position">
								<c:if test="${position.id == user.positionId}">
									<c:out value="${position.name}" />
								</c:if>
							</c:forEach></td>
						<td><c:if test="${user.id != loginUser.getId()}">
								<c:if test="${user.isStopped == '0'}">
								<script>
									function submitStop() {
										/* 確認ダイアログ表示 */
										var flag = confirm("本当に停止しますか？\n\n停止を取り消す場合は[キャンセル]ボタンを押して下さい");
										/* send_flg が TRUEなら送信、FALSEなら送信しない */
										return flag;
									}
								</script><form action="management" method="post" onsubmit="return submitStop()">
									<input type="hidden" name="isStopped" value="1" /><input type="hidden" name="name" value="${user.name }" />
									<button type="submit" name="id" value="${user.id}">停止</button></form>
								</c:if>
								<c:if test="${user.isStopped == '1'}">
								<script>
									function submitReborn() {
										/* 確認ダイアログ表示 */
										var flag = confirm("本当に復活しますか？\n\n復活を取り消す場合は[キャンセル]ボタンを押して下さい");
										/* send_flg が TRUEなら送信、FALSEなら送信しない */
										return flag;
									}
								</script><form action="management" method="post" onsubmit="return submitReborn()">
									<input type="hidden" name="isStopped" value="0" /><input type="hidden" name="name" value="${user.name }" />
									<button type="submit" name="id" value="${user.id}">復活</button></form>
								</c:if>
							</c:if></td>

						<td><form action="settings" method="get">
								<button type="submit" name="settings" value="${user.id}">編集</button>
							</form></td>

						<td><c:if test="${user.id != loginUser.getId()}">
								<script>
									function submitChk() {
										/* 確認ダイアログ表示 */
										var flag = confirm("本当に削除しますか？\n\n削除を取り消す場合は[キャンセル]ボタンを押して下さい");
										/* send_flg が TRUEなら送信、FALSEなら送信しない */
										return flag;
									}
								</script>

								<form action="deleteuser" method="post"
									onsubmit="return submitChk()">
									<input type="hidden" name="deleteId" value="${user.id}" />
									<input type="hidden" name="name" value="${user.name }" />
									<button type="submit" name="deleteButton">削除</button>
								</form>
							</c:if></td>
					</tr>
				</c:forEach>
			</table>
		</div>
</div></body>
</html>
