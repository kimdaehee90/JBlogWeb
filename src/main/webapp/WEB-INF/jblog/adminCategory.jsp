<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html> 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>JBlog 카테고리 관리</title>
	<Link rel="stylesheet" href="css/theme.css">
</head>

<body background="images/kubrickbgcolor.jpg">
<center>
<table background="images/kubrickheader.jpg" width="760" height="200" border="0" cellpadding="0" cellspacing="0">
<tr><td height="60">&nbsp;</td></tr>
 
<!-- 블로그 제목과 태그 시작 -->
<tr><td height="60" class="blogtitle">${blog.title }</td></tr>
<tr><td height="20" class="blogtag">${blog.tag }</td></tr>
<!-- 블로그 제목과 태그 끝 -->      

<tr>
	<td align="right" height="60">
    	<a href="logout.do">로그아웃</a>&nbsp;&nbsp;<a href="blogMain.do">내 블로그 메인</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</td>
</tr>
</table>

<table background="images/kubrickbg.jpg" width="760" height="40" border="0" cellpadding="0" cellspacing="0">
<tr><td height="10" colspan="10">&nbsp;</td></tr>
<tr>
	<td height="10" width="20">&nbsp;</td>
	<td width="530" valign="top" class="tdcontent">
		<!-- 메뉴 시작 -->     
		<a class="title" href="adminBasic.do"><b>기본설정</b></a>&nbsp;&nbsp;
		<b>카테고리</b>&nbsp;&nbsp;
		<a class="title" href="adminPost.do"><b>글작성</b></a>&nbsp;&nbsp;
		<c:if test="${blog.status =='운영' }">
		<a class="title" href="blogDeleteRequest.do?blogId=${blog.blogId }"><b>삭제요청</b></a>&nbsp;&nbsp;
		</c:if>
		<c:if test="${blog.status =='삭제요청' }">
		<a class="title" href="blogDeleteRequest.do?blogId=${blog.blogId }"><b>삭제요청취소</b></a>&nbsp;&nbsp;
		</c:if>
		<!-- 메뉴 끝 -->           	 
	</td>
</tr>
<tr><td height="5">&nbsp;</td></tr>
<tr>
	<td height="10">&nbsp;</td>
    <td>
    <!-- 작업 화면  시작 --> 
    <c:if test="${!empty categoryList}">        	     	
    <table width="720"  border="1" cellpadding="0" cellspacing="0">
    <tr>
		<td width="50" class="tablelabel">번호</td>
		<td width="150" class="tablelabel">카테고리명</td>
		<td width="80" class="tablelabel">보이기 유형</td>
		<td width="70" class="tablelabel">포스트 수</td>
		<td width="280" class="tablelabel">설명</td>
		<td width="70" class="tablelabel">삭제</td>      			
	</tr>

	<!-- 미분류 카테고리는 삭제 불가 -->
	<c:forEach var="category" items="${categoryList }">
	<tr>
		<td class="tablecontent" align="center">${category.categoryId }</td>
		<td class="tablecontent"><a href="updateCategoryView.do?categoryId=${category.categoryId }&blogId=${category.blogId}">${category.categoryName }</a></td>
		<c:if test="${category.displayType=='TITLE' }">
		<td class="tablecontent" align="center">제목</td>
		</c:if>
		<c:if test="${category.displayType=='MIXED' }">
		<td class="tablecontent" align="center">제목+내용</td>
		</c:if>
		<td class="tablecontent" align="center">${category.cntDisplayPost }</td>
		<td class="tablecontent">${category.description }</td>
	
		<td class="tablecontent" align="center">&nbsp;
		<c:if test="${category.categoryName == '미분류' }">
		삭제불가
		</c:if>
		
		<c:if test="${category.categoryName != '미분류' }">
		<a href="deleteCategory.do?categoryId=${category.categoryId }"><img height="9" src="images/delete.jpg" border="0"></a>
		</c:if>
		</td>
		
	</tr>  
	</c:forEach>  		    					
    </table>
   	</c:if>
	
	<!-- 카테고리 등록화면 시작 -->
	<c:if test="${categoryFlag =='insertCategory'}">
    <form action="insertCategory.do" method="post">  
    <input name="blogId" value="${blog.blogId}" type="hidden"/> 
    <table width="720"  border="0" cellpadding="0" cellspacing="0">
		<tr><td height="5">&nbsp;</td></tr>
		<tr><td height="5">&nbsp;</td></tr>
		<tr><td class="tdcontent" height="5"><b>카테고리 추가</b></td></tr>
		<tr><td height="5">&nbsp;</td></tr>      		
		<tr>
			<td width="150" class="inputlabel">카테고리명 :</td>
			<td><input class="inputtext" type="text" size="40" name="categoryName"></td>
		</tr>
		<tr>
			<td width="150" class="inputlabel">보이기 유형 :</td>
			<td class="tdcontent">
 				<input type="radio" name="displayType" value="TITLE" checked>제목&nbsp;&nbsp;
				<input type="radio" name="displayType" value="MIXED">제목+내용&nbsp;&nbsp;
			</td>      			
		</tr>
		<tr>
			<td width="150" class="inputlabel">포스트 수 :</td>
			<td class="tdcontent"><input class="inputtext" type="text" size="4" name="cntDisplayPost">개(1~20)</td>      			
      	</tr>
		<tr>
			<td width="150" class="inputlabel">설명 :</td>
			<td><input class="inputtext" type="text" size="50" name="description"></td>
		</tr>
		<tr><td height="5">&nbsp;</td></tr>
		<tr>
			<td colspan="10" align="center">&nbsp;<input type="submit" value="카테고리 추가"></td>
		</tr>      		      		
	</table>    
	</form> 
	</c:if>
	<!-- 카테고리 등록화면 종료 --> 
      	
	<!-- 카테고리 수정화면 시작 -->
	<c:if test="${categoryFlag =='updateCategory'}">
	<form action="updateCategory.do" method="post">   
    <input type="hidden" name="categoryId" value="${category.categoryId }"/>
    <table width="720"  border="0" cellpadding="0" cellspacing="0">
	    <tr><td height="5">&nbsp;</td></tr>
	    <tr><td height="5">&nbsp;</td></tr>
	    <tr><td class="tdcontent" height="5"><b>카테고리 수정</b></td></tr>
	    <tr><td height="5">&nbsp;</td></tr>      		
	    <tr>
	    	<td width="150" class="inputlabel">카테고리명 :</td>
	    	<td><input class="inputtext" type="text" size="40" name="categoryName" value="${category.categoryName }" ></td>
	    </tr>
	    <tr>
	    	<td width="150" class="inputlabel">보이기 유형 :</td>
	      	<td class="tdcontent">
	      		<input type="radio" name="displayType" value="TITLE"  <c:if test="${category.displayType == 'TITLE' }">checked</c:if>>제목&nbsp;&nbsp;
	      		<input type="radio" name="displayType" value="MIXED" <c:if test="${category.displayType == 'MIXED' }">checked</c:if>>제목+내용&nbsp;&nbsp;
	      	</td>      			
		</tr>
		<tr>
			<td width="150" class="inputlabel">포스트 수 :</td>
			<td class="tdcontent"><input class="inputtext" type="text" size="4" name="cntDisplayPost" value="${category.cntDisplayPost }">개(1~20)</td>      			
		</tr>
		<tr>
			<td width="150" class="inputlabel">설명 :</td>
			<td><input class="inputtext" type="text" size="50" name="description" value="${category.description }"></td>
		</tr>
		<tr><td height="5">&nbsp;</td></tr>
		<tr>
			<td colspan="10" align="center">&nbsp;<input type="submit" value="카테고리 수정"></td>
		</tr>      		      		
	</table>    
	</form>
	</c:if>
	<!-- 카테고리 수정화면 종료 -->
         	      	
	</td>
</tr>
<tr><td height="10" colspan="10">&nbsp;</td></tr>
</table>

<table background="images/kubrickfooter.jpg" width="760" height="63" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td class="blogfooter">SPRING Stories is powered by JBlog</td>
	</tr>
</table>

</center>   	
</body>
</html>