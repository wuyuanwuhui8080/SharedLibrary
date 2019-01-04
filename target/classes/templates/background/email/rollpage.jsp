<%@ page contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 		<div align="center">
				<c:if test="${param.currentPageNo > 1}">
					<a href="javascript:page_nav(document.forms[0],1);">首页</a>&nbsp;&nbsp;
					<a href="javascript:page_nav(document.forms[0],${param.currentPageNo-1});">上一页</a>
				</c:if>&nbsp;&nbsp;
				<c:if test="${param.currentPageNo < param.totalPageCount }">
					<a href="javascript:page_nav(document.forms[0],${param.currentPageNo+1 });">下一页</a>
					&nbsp;&nbsp;
					<a href="javascript:page_nav(document.forms[0],${param.totalPageCount });">末页</a>
				</c:if>&nbsp;&nbsp;
				第 ${param.currentPageNo }页/共${param.totalPageCount }页
		</div> 
</body>
</html>