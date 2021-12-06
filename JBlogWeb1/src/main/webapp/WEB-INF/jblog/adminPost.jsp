<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<table width="100%" height=320 border="0" cellpadding="0" cellspacing="0">
<tr><td height=40 colspan="10">&nbsp;</td></tr>
<div>
<tr>
	<td width="100%" height="120" colspan="10" align="center">
		<img src="images/logo.jpg" border="0">
	</td>
</tr>
</table>

<table>
<td>${blog.title }의 블로그 </td><br>
</table>
<table><a href="blogmainView.do">내 블로그 가기</a>&nbsp;
<c:if test="${user != null }">
<a href="logout.do">로그아웃</a><br>
</c:if>
</table>
<table>
<a href="adminView.do">기본설정</a>&nbsp;
<a href="adminCategoryView.do">카테고리 설정</a>&nbsp;
<a href="createPostView.do">글 작성</a>&nbsp;
</table>

<form action="insertPost.do?blogId=${blog.blogId }" method="post">
<table>

<table border="1" cellpadding="0" cellspacing="0">
<tr>
	<td bgcolor="orange" width="70">제목</td>
	<td align="left"><input name="title" type="text"></td>
</tr>
<tr>
	<td bgcolor="orange">내용</td>
	<td align="left"><textarea name="content" cols="40" rows="10">${board.content }</textarea></td>
</tr>

<tr>
	<td colspan="2" align="center">
		<input type="submit" value="확인"/>
	</td>
</tr>
</table>
</form>
</center>
</body>
</html>