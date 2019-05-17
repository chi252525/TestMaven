<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>單一商品瀏覽</title>
<%@ include file="/templete/link.jsp"%>
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath()%>/resource/css/listOneProduct.css" rel="stylesheet">
</head>
<body>
	<%@include file="/templete/header.jsp"%>
	
	<script type="text/javascript">
	$(document).ready(function() {
		$.get( "${contextPath}/resource/json/product.json", function( json ) {
			$.each(json.Allproduct, function(i,classjobj){
			$.each(classjobj.Data ,function(j,jobj){
				if('${requestScope.productid}'==jobj.productid){
					$('#productionName').html(jobj.productionName);
					$('#showprices').html(jobj.prices);
					$('#shoppingPrice').val(jobj.prices);
					$('#id').val('${requestScope.productid}');
					$('#productImg').attr('src','${contextPath}/img/product/'+jobj.productImg);
					
					if(jobj.productExist!==true || jobj.productAmount==0){
						$('#soldOut').html('<span class="badge badge-pill badge-danger soldOut"><b>已售完</b></span>');
					}
					if(jobj.isDiscountprice == true){
						$('#showprices').html('<S>'+jobj.prices+'</S>&nbsp;<b style="color:Tomato;">'+jobj.discountPrice+'</b>');
						$('#shoppingPrice').val(jobj.discountPrice);
					}
					 if(jobj.subordinate_Name!==null && jobj.subordinate_Name!== undefined){
						 $('#subordinate_Name').html('<p class="card-text py-3"><span class="badge badge-pill badge-info "><b>'+jobj.subordinate_Name+'</b></span></p>');
			            }
					 if(jobj.calories!==null && jobj.calories!== undefined){
						 $('#calories').html('<p><span class="badge badge-info">熱量:'+jobj.calories+'</span></p>');
					 }
					 var attr_num=0;	
					 $.each(jobj.choice_Item3, function(k,innerjobj){
							 if(innerjobj!=null && innerjobj!== undefined){
								 var choice_content='';
								 if(innerjobj.multiple_choice==false){
									 choice_content+='<div class="choice">';
									 choice_content+='<h5 class="title">'+innerjobj.description+':</h5>';
									 choice_content+='<div class="attr_css">';
									 
									 $.each(innerjobj.attribute, function(l,attrsjobj){
										 if(attrsjobj!=null && attrsjobj!== undefined){
											 choice_content+='<input type="radio" name="attr'+attr_num+'" id="radio'+attrsjobj.id+'"  value="'+attrsjobj.attributesName+'"  >';
											 choice_content+='<label for="radio'+attrsjobj.id+'" >'+attrsjobj.attributesName+'</label>';
											 
										 }
									 });
									 attr_num+=1;
									 choice_content+='</div>';
									 choice_content+='</div>';
								 }else if(innerjobj.multiple_choice==true){
									 choice_content+='<div class="choice">';
									 choice_content+='<h5 class="title">'+innerjobj.description+'</h5>';
									 choice_content+='<div class="attr_css">';
									 $.each(innerjobj.attribute, function(l,attrsjobj){
										 if(attrsjobj!=null && attrsjobj!== undefined){
											 choice_content+='<input type="checkbox" name="attr'+attr_num+'" id="checkbox'+attrsjobj.id+'"  value="'+attrsjobj.attributesName+'"  >';
											 choice_content+='<label for="checkbox'+attrsjobj.id+'" >'+attrsjobj.attributesName+'</label>';
											 attr_num+=1;
										 }
									 });
									
									 choice_content+='</div>';
									 choice_content+='</div>'; 
								 } 
								 //append
								 $("#accordionExample").append(choice_content);
							 }
						 });
					var cartbtncontent='';
					if(jobj.productExist!==true || jobj.productAmount==0){
						cartbtncontent+='<input type="submit" class="btn btn-danger mt-3 btn-lg btn-block" disabled value="已售完">';
											}else{
						cartbtncontent+='<input type="submit" class="btn btn-danger mt-3 btn-lg btn-block"  value="加入訂單">';
					}
					$('#addtoCartbtn').html(cartbtncontent);
					 
				}
			});
			});
			});
	});
	</script>
	<!-- Page Content -->
	<div class="container mt-4 pb-5">
		<div class="row">
			<!-- ======eshopbar======== -->
			<%@include file="/templete/eshopbar.jsp"%>
			<!-- ======eshopbar======== -->
			<div class="col-lg-9">
				<form  action="${contextPath}/Cart/add" method=post enctype="x-www-form-urlencoded">
					<div class="card proInfo">
						<img class="card-img-top img-fluid" id="productImg"	src="" alt="noproduct.jpg">
						<div class="card-body px-4">
						<div class="row">
							<h3 class="card-title col-12 col-sm-10" id="productionName"></h3>
							<div id="soldOut"></div>
							<div class="col-12 col-sm-2 proPrice">
								<h4>NT.<span id="showprices"></span>元 &nbsp;</h4>
							</div>
						</div>
							<div class="buttonQty">
								<div><span>數量:</span>
									<button type="button" id="minus" class="btn btn-warning"><i class="fas fa-minus"></i>
									</button>
									<span id="qty">1</span> <input type="hidden" name="shoppingQty" id="qty_parm" value="1">
									<button type="button" id="add" class="btn btn-warning"><i class="fas fa-plus"></i>
									</button>								
								</div>
							</div>
							<div id="subordinate_Name"></div>
							<div id="description"></div>
							<div id="calories"></div>
							<div class="accordion" id="accordionExample">
							</div>
							<div class="px-0" id="addtoCartbtn">
							</div>
							<input type="hidden" name="shoppingPrice" id="shoppingPrice" value="">
							<input type="hidden" name="id" id="id" value="">
						</div>
					</div>
					
				</form>
			</div><!-- /.col-lg-9 -->
		</div><!-- /.row -->
	</div><!-- /.container -->

<script type="text/javascript">
 $(document).ready(function() { 
 	$("#add").click(function() { 
 		var qty = parseInt($('#qty').text()) 
 		if (qty >= 5) { 
 			toastr.warning("已達單次放入購物車上限"); 
 		} else { 
 			var qty_now = parseInt($('#qty').text()) + 1; 
 			$('#qty').text(qty_now); 
 			$('#qty_parm').val(qty_now); 
 		} 
 		}); 
		$("#minus").click(function() { 
 			if (parseInt($('#qty').text()) > 1) { 
 				var qty_now = parseInt($('#qty').text()) - 1; 
 				$('#qty').text(qty_now); 
				$('#qty_parm').val(qty_now); 
			} else { 
 				toastr.warning("商品數量不得小於1");} 
 		}); 
 });
</script>
</body>
</html>
