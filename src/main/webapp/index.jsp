<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf"  uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.*"%>
<%@ page import="com.welljoint.entity.*"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	response.flushBuffer();
	session.removeAttribute("shoppingcart");
%>

<!DOCTYPE html>
<html lang="zh-Hant">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>行動線上訂購系統</title>
    <%@ include file="/templete/link.jsp" %>
    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath()%>/resource/css/full-width-pics.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/resource/css/index.css" rel="stylesheet">
  </head>
  <body>
  <%@include file="/templete/header.jsp" %>
    <header class="py-5 bg-image-full" style="background-image: url('<%=request.getContextPath()%>/img/banner/index_banner.jpg');">
      <img class="img-fluid d-block mx-auto " src="<%=request.getContextPath()%>/img/logo/img_home_logo.png" alt="img_home_logo.png">
    </header>

    <section>
      <div class="container indexForm">
        <div class="col-lg-6 offset-lg-3 col-md-12 col-sm-12">
<!-- ============================For輕井澤展示需要====================================== -->
<form method="post"  name="shoppingform" enctype="multipart/form-data">
	<div class="form-group">
		<p><small class="text-muted-color">* </small><span>請選擇內用或外帶</span></p>
		<div class="row btn_radio">
			 <div class="col-6">
				 <input type="radio" id="forhere" name="orderstatus" value="內用" checked >
				 <label for="forhere" class="btn btn-lg btn-warning">內用</label>
		 	 </div>
			 <div class="col-6">
				 <input type="radio" id="takeout" name="orderstatus" value="外帶">
				 <label for="takeout" class="btn btn-lg btn-warning">外帶</label>
			 </div>
    	</div>
		
		 <label for="internalNumber">桌號 (選填)</label>
		 <input name="internalNumber" class="form-control" id="internalNumber"  type="text" >
 	</div>
	 <input name="store" type="hidden" id="store" value="台中店">
	 <input name="storename" type="hidden" id="storename" value="輕井澤">
	 <input name="value" type="hidden" id="value" value="12345678">
	 <input name="action" type="hidden" value="ready">
	 <button type="submit" class="btn btn-primary btn-lg btn-block">開始訂購&nbsp;</button>
 </form>
<!-- ============================For輕井澤展示需要====================================== --> 
 		</div>
	</div>
    </section>
		<section class="bottomImg" style="text-align: center;background: #222;padding-top: 10px;">
			<img src="<%=request.getContextPath()%>/img/banner/img_home_bottom.png" style="max-width: 100%;height: auto;"></img>
		</section>
  </body>
   <script type="text/javascript">
      $(document).ready(function() {
    		//show errorMsgs
    		<c:if test="${not empty errorMsgs}">
    		<c:forEach var="message" items="${errorMsgs}">
    		toastr.error("${message}");
    		</c:forEach>
    		</c:if>
    	});
	</script>

</html>
