<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>blogmain!!</title>
<Link rel="stylesheet" href="css/theme.css">
</head>
<body>
<center>
<article>
		<img src="images/logo.jpg" border="0">
</article><br>
<a>${blog.tag }</a><br><br>

<a>${blog.title }의 블로그 </a><br>
<table><a href="adminView.do?blogId=${blog.blogId }">블로그 관리</a><br>
<c:if test="${user != null }">
<a href="logout.do">로그아웃</a><br>
</c:if>
<c:if test="${user == null }">
<a href="loginView.do">로그인</a>
</c:if>
</table>

<%-- <c:forEach var="post" items="${postList }">
<tr>
	<td>${post.postID }</td>
	<td align="left"><a href="getblog.do?blogId=${post.postID }">${post.title }</a></td>
	<td>${post.categooryID }</td>
	<td>${post.content }</td>
	<td>${post.createDate }</td>
	<a href="createPostView.do">등록</a>
	<a href="updatePostView.do">수정</a>
</tr>
</c:forEach> --%>


</center>
</body>
</html>