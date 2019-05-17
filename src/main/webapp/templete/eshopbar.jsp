<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <!-- eshopbar -->
<div class="col-lg-3 col-sm-12 px-0 leftMeun">
      <div class="col-sm-12">
         <div class="list-group" id="listgroup">
          </div>
      </div>
</div>
<script>
$(document).ready(function() {
	   var eshopbarcontent='';
	   $.getJSON("${contextPath}/resource/json/eshopbar.json", function(json){
	   	$.each(json.eshopbar, function(k,jobj){
	   		eshopbarcontent+='<a href="${contextPath}/frontstage/productEShop.jsp#'+jobj.ProductClassKey+'" class="list-group-item list-group-item-action">';
	   		eshopbarcontent+=jobj.ProductClass+'&nbsp;<span class="badge badge-pill badge-dark float-right" ><b>'+jobj.size+'</b></span></a>';
	   	});
	   	$("#listgroup").append(eshopbarcontent);
	   	$("#listgroup a:first").addClass("active");
	   	$("#listgroup > a").click(function(){
	   	    $(this).siblings().removeClass("active");
	   	    $(this).addClass("active");   
	   	});
	   	
	   });
});
</script>
