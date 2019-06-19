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
	session.removeAttribute("shoppingList");
	session.removeAttribute("myCart");
	ResourceBundle res = ResourceBundle.getBundle("application");
	String takeinfo=res.getString("app.takeinfo");
	pageContext.setAttribute("takeinfo",takeinfo);
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
        <a  href="<%=request.getContextPath()%>/index?lang=zh_TW"><spring:message code="language.cn"/>｜</a>
        <a  href="<%=request.getContextPath()%>/index?lang=en_US"><spring:message code="language.en"/></a>
    </div>
        <div class="col-lg-6 offset-lg-3 col-md-12 col-sm-12">
          
 <!-- 		===================================M01FORM============================================================= -->       
<form method="post" action="<%=request.getContextPath()%>/Order/productEShop" id="shoppingformM01" enctype="x-www-form-urlencoded">
	<div class="form-group">
		<p><small class="text-muted-color">* </small><span><spring:message code="language.index.hintorderstatus"/></span></p>
		<div class="row btn_radio">
			 <div class="col-6">
				 <input type="radio" id="forhere" name="orderStatus" value="內用" checked >
				 <label for="forhere" class="btn btn-lg btn-warning"><spring:message code="language.index.eatin"/></label>
		 	 </div>
			 <div class="col-6">
				 <input type="radio" id="takeout" name="orderStatus" value="外帶">
				 <label for="takeout" class="btn btn-lg btn-warning"><spring:message code="language.index.takeout"/></label>
			 </div>
    	</div>
<!-- 		 <label for="internalNumber">桌號 (選填)</label> -->
<%-- 		 <input name="internalNumber" class="form-control" id="internalNumber"  type="text" > --%>
 	</div>
	 <button type="submit" class="btn btn-primary btn-lg btn-block"><spring:message code="language.index.startbtn"/>&nbsp;</button>
 </form>
 <!-- 		===================================M01FORM============================================================= -->
 
 <!-- 		===================================M02FORM============================================================= -->
 <form method="post"	action="<%=request.getContextPath()%>/Order/productEShop" id="shoppingformM02">
					<div class="form-group">
						<small class="text-muted-color">* </small> 
						<label for="taketimeDate"><i class='fas fa-calendar-alt'></i>&nbsp;預計取餐日期：</label>
						 <input	name="taketimeDate" class="form-control" id="taketimeDate" required type="text"> <br>
						 <small class="text-muted-color">* </small> 
						 <label for="taketimeTime"><i class='fas fa-clock'></i>&nbsp;預計取餐時間：</label>
						<input name="taketimeTime" class="form-control" id="taketimeTime"	type="text"> <br> 
						<small class="text-muted-color">*</small> 
						<label for="orderstatus"><i class='fas fa-concierge-bell'></i>&nbsp;取餐方式：</label>
						<select class="form-control" id="orderstatus" name="orderStatus">
							<option data-orderStatusKey="Y">外帶</option>
						</select> 
					</div>
					<input	type="hidden" name="orderStatusKey" id="orderStatusKey">
					<input	type="hidden" name="taketime" id="taketime"> 
					<button type="submit" class="btn btn-primary btn-lg btn-block">開始訂購&nbsp;</button>
				</form>
  <!-- 		===================================M02FORM============================================================= -->
 
 		</div>
	</div>
    </section>
		<section class="bottomImg" style="text-align: center;background: #222;padding-top: 10px;">
			<img src="<%=request.getContextPath()%>/img/banner/img_home_bottom.png" style="max-width: 100%;height: auto;"></img>
		</section>
  </body>
  <script src="<%=request.getContextPath()%>/resource/js/taketime.js"></script>
   <script type="text/javascript">
      $(document).ready(function() {
    		//show errorMsgs
    		<c:if test="${not empty errorMsgs}">
    		<c:forEach var="message" items="${errorMsgs}">
    		toastr.error("${message}");
    		</c:forEach>
    		</c:if>
    		if('${takeinfo}'==='M01'){
	        	$('#shoppingformM01').show();
	        	$('#shoppingformM02').hide();
	        }else if('${takeinfo}'==='M02'){
	        	$('#shoppingformM02').show();
	        	$('#shoppingformM01').hide();
	        }
    	    	
    	});
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
