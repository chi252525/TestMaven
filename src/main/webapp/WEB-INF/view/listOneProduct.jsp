<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.welljoint.entity.*"%>
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
					$('#prices').html(jobj.prices);
					$('#productImg').attr('src','${contextPath}/img/product/'+jobj.productImg);
					if(jobj.productExist!==true || jobj.productAmount==0){
						$('#soldOut').html('<span class="badge badge-pill badge-danger soldOut"><b>已售完</b></span>');
					}
					if(jobj.isDiscountprice == true){
						$('#prices').html('<S>'+jobj.prices+'</S>&nbsp;<b style="color:Tomato;">'+jobj.discountPrice+'</b>');
					}
					
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
				<form name="shoppingForm" action="${contextPath}/ShoppingServlet.do" method="post" enctype="multipart/form-data">
					<div class="card proInfo">
						<img class="card-img-top img-fluid" id="productImg"	src="${contextPath}/img/product/noproduct.jpg" alt="noproduct.jpg">
						<div class="card-body">
						<div class="row">
							<h3 class="card-title col-12 col-sm-10" id="productionName"></h3>
							<div id="soldOut"></div>
									
							<div class="col-12 col-sm-2 proPrice">
								<h4>NT.<span id="prices"></span>元 &nbsp;</h4>
							</div>
						</div>
							<div class="buttonQty">
								<div><span>數量:</span>
									<button type="button" id="minus" class="btn btn-warning"><i class="fas fa-minus"></i>
									</button>
									<span id="qty">1</span> <input type="hidden" name="qty" id="qty_parm" value="1">
									<button type="button" id="add" class="btn btn-warning"><i class="fas fa-plus"></i>
									</button>								
								</div>
							</div>
							<p>
								<c:if test="${listonepVO.subordinate_Name != null}">
									<span class="badge badge-pill badge-info"><b>${listonepVO.subordinate_Name}</b></span>&nbsp;
			                    </c:if>
							</p>
							<c:if test="${not empty listonepVO.calories}">
							<p><span class="badge badge-info">熱量:${listonepVO.calories}</span></p>
							</c:if>
							
							<div class="accordion" id="accordionExample">
							${requestScope.productid}
								<c:forTokens items = "${listonepVO.choice_Item3}" delims = "," var = "attributegroupid" varStatus="one_attrg_status">
									<c:set var="one_attributeList" scope="page" value="${agSvc.getOneAttibuteGroup(attributegroupid).attributesList}"/>
									<c:set var="one_attribute_description" scope="page" value="${agSvc.getOneAttibuteGroup(attributegroupid).description}"/>
									<div class="choice">
										<h5 class="title">${one_attribute_description}:</h5>
								         <c:forTokens items = "${one_attributeList}" delims = "," var = "attributesingleid" varStatus="one_attrs_status">
									         <c:set var="one_attributesingle" scope="page" value="${asSvc.getOneAttributeSingle(attributesingleid).attributesName}"/>
									         <c:set var="one_attributesingleid" scope="page" value="${asSvc.getOneAttributeSingle(attributesingleid).id}"/>
											<!--radio部分-->
											<div class="attr_css">
												<input type="radio" name="attr${one_attrg_status.index}" id="radio${one_attributesingleid}"  value="${one_attributesingle}"  ${one_attrs_status.first? "checked":""}>
												<label	for="radio${one_attributesingleid}" >${one_attributesingle}</label>
											</div>		
								         </c:forTokens>
									</div>  	
								</c:forTokens>
							</div>
                            <input type="hidden" name="id" value="${listonepVO.id}"> 
                            <input type="hidden" name="productClass" value="${listonepVO.productClass}"> 
                            <input type="hidden" name="productClasskey" value="${listonepVO.productClasskey}">
                            <input type="hidden" name="productionName" value="${listonepVO.productionName}"> 
                            <input type="hidden" name="productImg" value="${listonepVO.productImg}">
                            <input type="hidden" name="productImg1" value="${listonepVO.productImg1}">
							<input type="hidden" name="productExist" value="${listonepVO.productExist}">
							<!-- 未處理 -->
                            <input type="hidden" name="productAmount" value="${listonepVO.productAmount}">
                            <input type="hidden" name="prices" id="prices" value="${priceInt}">
                            <input type="hidden" name="choice_Item1" value="${listonepVO.choice_Item1}">
							<!-- 未處理 -->
							<input type="hidden" name="choice_Item2" value="${listonepVO.choice_Item2}">
							<!-- 未處理 -->
                            <input type="hidden" name="choice_Item3" value="${listonepVO.choice_Item3}">
                            <input type="hidden" name="tax_Type" value="${listonepVO.tax_Type}">
							<!-- 未處理 -->
							<input type="hidden" name="tax_Rate" value="${listonepVO.tax_Rate}">
							<!-- 未處理 -->
							<input type="hidden" name="calories" value="${listonepVO.calories}">
							<c:set var="parsedisprice" value="${listonepVO.discountPrice}" />
							<fmt:parseNumber var="dispriceInt" integerOnly="true" type="number" value="${parsedisprice}" />
							<input type="hidden" name="discountPrice" value="${dispriceInt}">
							<input type="hidden" name="action" value="ADD">
							<div class="px-0">
								<input type="submit" class="btn btn-danger mt-3 btn-lg btn-block" id="addtoCartbtn"
                                    ${(listonepVO.productExist == false)|| (listonepVO.productAmount==0)?'disabled="disabled"':''}
                                    ${(listonepVO.productExist == false)|| (listonepVO.productAmount==0)?'value="已售完"':'value="加入訂單"'}>
							</div>
						</div>
					</div>
					
				</form>
				<c:if test="${listonepVO.productionContent0 != null || listonepVO.productionContent1 != null || listonepVO.productionContent2 != null || listonepVO.description != null}">
					<!-- /.card -->
					<div class="col-12 my-4">
						<ul class="nav nav-pills nav-fill" id="myTab" role="tablist">
							<li class="nav-item"><a class="nav-link active" id="productionContent-tab" data-toggle="tab"
								href="#productionContent" role="tab" aria-controls="productionContent" aria-selected="true">商品內容</a>
	                        </li>
							<li class="nav-item"><a class="nav-link" id="description-tab" data-toggle="tab" href="#description" role="tab"
	                            aria-controls="description" aria-selected="false">商品介紹</a>
	                        </li>
						</ul>
						<div class="tab-content" id="myTabContent">
							<div class="tab-pane fade show active" id="productionContent" role="tabpanel" aria-labelledby="productionContent-tab">
								<p>${listonepVO.productionContent0}</p>
								<p>${listonepVO.productionContent1}</p>
								<p>${listonepVO.productionContent2}</p>
							</div>
							<div class="tab-pane fade" id="description" role="tabpanel" aria-labelledby="description-tab">
								<p>${listonepVO.description}</p>
							</div>
						</div>
					</div><!-- /.card -->
				</c:if>
			</div><!-- /.col-lg-9 -->
		</div><!-- /.row -->
	</div><!-- /.container -->

<!-- <script type="text/javascript"> -->
<!-- // $(document).ready(function() { -->
<!-- // 	$("#add").click(function() { -->
<%-- // 		var productAmount = parseInt('${listonepVO.productAmount}'); --%>
<!-- // 		var qty = parseInt($('#qty').text()) -->
<!-- // 		if (qty >= productAmount) { -->
<!-- // 			toastr.warning("商品庫存數不足，無法訂購該數量"); -->
<!-- // 		} else { -->
<!-- // 			var qty_now = parseInt($('#qty').text()) + 1; -->
<!-- // 			$('#qty').text(qty_now); -->
<!-- // 			$('#qty_parm').val(qty_now); -->
<!-- // 		} -->
<!-- // 		}); -->
<!-- // 		$("#minus").click(function() { -->
<!-- // 			if (parseInt($('#qty').text()) > 1) { -->
<!-- // 				var qty_now = parseInt($('#qty').text()) - 1; -->
<!-- // 				$('#qty').text(qty_now); -->
<!-- // 				$('#qty_parm').val(qty_now); -->
<!-- // 			} else { -->
<!-- // 				toastr.warning("商品數量不得小於1");} -->
<!-- // 		}); -->
<!-- // }); -->
<!-- </script> -->
</body>
</html>
