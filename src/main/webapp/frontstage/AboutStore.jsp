<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.welljoint.entity.*"%>
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
    <style>
	  	.cateTitle {
		    border-bottom: 2px solid #222;
		}
		.cateTitle h2 {
		    background-color: #222;
		    color: #fff;
		    display: inline;
		    padding: 5px 15px 2px;
		    border-radius: 4px 4px 0px 0px;
		}
		.card .fa,.card .fas{
			font-size: 18px;
    		font-weight:bolder;
    		min-width: 15px;
    	}
		hr{
			width: 50px;
		    border-top: 3px solid #222;
		    margin-left: 0;
		    margin-top: 0;
		}
		.card{
		    border-bottom: 4px solid #222;
		}

    </style>
  </head>
     <%@include file="/templete/header.jsp" %>
  <body>
    <header class="py-5 bg-image-full" style="background-image: url('<%=request.getContextPath()%>/img/banner/img_home_top.jpg');">
      <img class="img-fluid d-block mx-auto " src="<%=request.getContextPath()%>/img/logo/img_home_logo.png" alt="">
    </header>
    <section class="py-5">
	    <div class="container">
	    	<div class="cateTitle"><h2>關於商家</h2></div><br>
		      <c:forEach var="storeInVO" items="${allStoreInVOs}">
		       <div class="d-flex flex-column">
			       <div class="card mb-3">
					  <div class="card-body text-dark">
					    <h5 class="card-title">${storeInVO.storeCode}&nbsp;${storeInVO.name} &nbsp; ${storeInVO.store} </h5>
					    <hr>
					    <p class="card-text"><i class="fa fa-phone"></i>&nbsp;電話: ${storeInVO.storeNumber}</p>
					    <p class="card-text"><i class="fas fa-caret-right"></i>&nbsp;統編: ${storeInVO.value}</p>
					    <p class="card-text"><i class="fas fa-map-marker-alt"></i>&nbsp;地址: 
					    	<a href="https://www.google.com/maps/place/${storeInVO.storeAddress}">${storeInVO.storeAddress}</a>
					    </p>
					  </div>
					</div>
				</div>
		      </c:forEach>
	    </div>
    </section>
  </body>
</html>
