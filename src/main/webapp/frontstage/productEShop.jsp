<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.welljoint.entity.*"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	response.flushBuffer();
%>
<%
	if(request.getParameter("message")!=null){
	String	message=request.getParameter("message");
	pageContext.setAttribute("message",message);
	}
%>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>商品一覽</title>
	<%@ include file="/templete/link.jsp" %>
    <link href="<%=request.getContextPath()%>/resource/css/producteshop.css" rel="stylesheet">
  </head>
<body>
	<%@include file="/templete/header.jsp"%>
	<!-- Page Content -->

	<div class="container pb-4">
		<!-- ======slider======== -->
			 <!-- ======slider======== -->
        <div class="col-sm-12 d-flex flex-wrap px-0 slider">
            <c:if test="${not empty blistshow}">
                <div id="carouselExampleIndicators" class="carousel slide mt-3" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <c:forEach varStatus="s" items="${blistshow}">
                            <li data-target="#carouselExampleIndicators" id="${s.index}" data-slide-to="${s.index}"	class=" ${s.first?'active':''}"></li>
                        </c:forEach>
                    </ol>
                    <div class="carousel-inner">
                        <c:forEach varStatus="s" var="bVO" items="${blistshow}">
                            <div class="carousel-item ${s.first?'active':''}" id="item${s.index}">
                                <img class="d-block w-100" src="<%=request.getContextPath()%>/img/banner/${bVO.img}" data-holder-rendered="true" alt="${bVO.img}">
                            </div>
                        </c:forEach>
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </c:if>
        </div> <!-- slider end -->
		<div class="row">
			<!-- ======左側商品選單======== -->
			<!-- eshopbar -->
			<%@include file="/templete/eshopbar.jsp"%>
			 <!-- eshopbar -->
			<!-- ======右側商品列======== -->
			<div class="col-lg-9 col-sm-12 d-flex flex-wrap px-0 proList"
				id="showbox">
				<!-- /. productClassKey forEach -->
			</div>
		</div>
	</div>
	<!-- /.container -->
</body>

<script type="text/javascript">
$(document).ready(function() {
let content = "";		
$.getJSON("${contextPath}/resource/json/product.json", function(json){
     $.each(json.Allproduct, function(i,classjobj){
        content +='<div class="bg-white proRow" id="'+classjobj.productClasskey+'" data-productClasskey="FCHYW">';
        content +='<div class="cateTitle"><h5 class="border-bottom mb-0"  >'+classjobj.productClass+'</h5></div>';
        content +='<div class="d-flex flex-wrap">';
        $.each(classjobj.Data ,function(j,jobj){
        	content +='<div class="col-lg-4 col-md-6 col-sm-6  col-xs-6 col-6 mt-2 px-1" style="cursor:pointer">';
            content +='<div class="card rounded shadow-sm ">';
            content +='<a href="${contextPath}/productEShop?id='+jobj.productid+'" class="proImg">';
               content +='<img class="card-img-top" src="${contextPath}/img/product/'+jobj.productImg+'" alt="'+jobj.productImg+'"></a>';
               content +='<div class="card-body" ><h5 class="card-title my-0"><b>'+jobj.productionName+'</b></h5>';
            if(jobj.isDiscountprice==true){
              content +='<h5 class="card-title my-0 proPrice">N.T<S>'+jobj.prices+'</S>&nbsp;<b style="color:Tomato;">'+jobj.discountPrice+'</b>元</h5>';   
            }else{
               content +='<h5 class="card-title my-0 proPrice">N.T<span>'+jobj.prices+'</span>元</h5>';  
            }
            if(jobj.subordinate_Name!==null && jobj.subordinate_Name!== undefined){
                content +='<p class="card-text"><span class="badge badge-pill badge-info "><b>'+jobj.subordinate_Name+'</b></span></p>';
            }
            
            if(jobj.productExist!==true || jobj.productAmount==0){
               content +='<p class="card-text"><span class="badge badge-pill badge-danger soldOut"><b>已售完</b></span></p>';
            }
            content +='</div></div></div>';
               });
        content +='</div></div>';
				});
		$("#showbox").append(content);
	});

});
</script>
<script type="text/javascript">
    //slider
    $('.carousel').carousel({
	    interval: false,
	}); 
	$(document).ready(function() {
		$("#listgroup > a").click(function(){
            $(this).siblings().removeClass("active");
            $(this).addClass(" active");
            
        });
    });
	 var touchSensitivity = 5;//手機裝置滑動
	  $(".carousel").on("touchstart", function (event) {
	      var xClick = event.originalEvent.touches[0].pageX;
	      $(this).one("touchmove", function (event) {
	          var xMove = event.originalEvent.touches[0].pageX;
	          if (Math.floor(xClick - xMove) > touchSensitivity) {
	              $(this).carousel('next');
	          } else if (Math.floor(xClick - xMove) < -(touchSensitivity)) {
	              $(this).carousel('prev');
	          }
	      });
	      $(".carousel").on("touchend", function () {
	          $(this).off("touchmove");
	      });
	  });
	  <c:if test="${not empty message}">
	      toastr.success("${message}");
  </c:if>
	</script>
</html>
