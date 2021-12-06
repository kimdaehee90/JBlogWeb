<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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


<table width="720" height="100" border="1" cellpadding="0" cellspacing="0">
<tr>
	<td width="320" class="tablelabel">번호</td>
	<td width="100" class="tablelabel">카테고리명</td>
	<td width="100" class="tablelabel">보이기 유형</td>
	<td width="100" class="tablelabel">포스트 수</td>
	<td width="100" class="tablelabel">설명</td>
	<td width="100" class="tablelabel">삭제</td>
	
</tr>

	
<c:forEach var="category" items="${categoryList}">
	<tr>
		<td class="tablecontent" align="center">${category.categoryId }</td>
		<td class="tablecontent">&nbsp;&nbsp;&nbsp;<a href="updatecategoryView.do?blogId=${blog.blogId}">${category.categoryName }</a></td>
		<td class="tablecontent" align="center">${category.displayType }</td>
		<td class="tablecontent" align="center">${category.cntDisplayPost }</td>
		<td class="tablecontent" align="center">${category.description }</td>
	
<!-- 		<td class="tablecontent" align="center"><a >삭제불가</a></td> -->
	
		
<td class="tablecontent" align="center">
			<a href="deleteCategory.do?blogId=${blog.blogId}"><img height="9"	src="images/delete.jpg" border="0"></a>
		</td> 
	</tr>
	

</c:forEach> 
</table>

<form action="insertCategory.do?blogId=${blog.blogId }" method="post">

<table border="1" cellpadding="0" cellspacing="0">

<tr>
	<td bgcolor="orange" width="100">카테고리명</td>
	<td align="left"><input name="categoryName" type="text" /></td>
</tr>
<tr>
	<td height="20" colspan="10" align="center" class="tdcontent">
		<input type="radio" name="displayType"   checked="checked">제목&nbsp;&nbsp; 
		<input type="radio"	name="displayType" >제목 + 내용 &nbsp;&nbsp; 
	</td>
</tr>
<tr>
	<<td bgcolor="orange" width="100">포스트 수</td>
	<td align="left"><input name="cntDisplayPost" type="number"/></td>
</tr>
<tr>
	<<td bgcolor="orange" width="100">설명</td>
	<td align="left"><input name="description" type="text" /></td>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="submit" value="카테고리 추가 "/>
	</td>
</tr>
</table>
 
 </form>
</center>
</body>
</html>