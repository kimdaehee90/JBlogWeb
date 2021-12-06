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

<table><a href="blogmainView.do?blogId=${blog.blogId }">내 블로그 가기</a>&nbsp;
<c:if test="${user != null }">
<a href="logout.do">로그아웃</a><br>
</c:if>
</table>
<table>
<a href="adminView.do?blogId=${blog.blogId }">기본설정</a>&nbsp;
<a href="adminCategoryView.do?blogId=${blog.blogId }">카테고리 설정</a>&nbsp;
<a href="createPostView.do?blogId=${blog.blogId }">글 작성</a>&nbsp;

<form action="updateBlog.do?blogId=${blog.blogId }" method="post">

<table border="1" cellpadding="0" cellspacing="0">

<tr>
	<td bgcolor="orange" width="100">블로그 제목</td>
	<td align="left"><input name="title" type="text" value="${blog.title }"/></td>
</tr>
<tr>
	<td bgcolor="orange" width="100">블로그 태그</td>
	<td align="left"><input name="tag" type="text" value="${blog.tag }"/></td>
</tr>
<tr>
	<<td bgcolor="orange" width="100">메인페이지 포스트</td>
	<td align="left"><input name="cntDisplayPost" type="number" value="${blog.cntDisplayPost }"/></td>
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