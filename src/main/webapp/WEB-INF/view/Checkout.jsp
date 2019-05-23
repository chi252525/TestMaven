<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.welljoint.entity.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
OrdersPhoneVO opVO=(OrdersPhoneVO)session.getAttribute("shoppingorder");
%>
<!DOCTYPE html>
<html lang="zh-Hant">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>購物車</title>
    <%@ include file="/templete/link.jsp" %>
    <link href="<%=request.getContextPath()%>/resource/css/cart.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resource/css/listOneProduct.css" rel="stylesheet">
  </head>
  <body>
    <%@include file="/templete/header.jsp" %>
    <div class="container mt-4 pb-4">
      <div class="row">
		<!-- 購物明細 -->
		<div class="col-sm-12">
			<div class="pageTitle my-2"><h5>購物明細</h5></div>
			<c:if test="${ not empty shoppingList}">
				<div id="prod_list" class="checkout">
					<ul class="ulList pl-0">
						<c:forEach varStatus="s" var="pVO" items="${shoppingList}">
						<li class="row">
							<div class="item_image col-4">
								<img src="<%=request.getContextPath()%>/img/product/${pVO.productImg}">
							</div>
							<div class="my-0 col-8">
								<h5>${pVO.productionName}</h5>
								<p>${pVO.shoppingNote}</p>
								<div class="row">
									<p class="col-6">NT. <span id="prices">${pVO.shoppingPrice}</span>元</p>
									<p class="col-6" align="right">數量: ${pVO.shoppingQty}</p>
								</div>
								<h5 class="float-right">小計:<span id="subtotalprice" class="note">${pVO.shoppingSubtotalprice}</span>元</h5>
							</div><span class="clearfix"></span><hr>
						</li>
						</c:forEach>
						
						<li class="totalQty" align="right">
							<p class="px-2">共 <span class="note">${totalQty}</span> 項商品
							</p>
							<p class="px-2">
								<span class="">總金額 NT$</span>
								<span class="subtotalnumber note">${totalPrice} 元</span>
							</p>
						</li>
					</ul>
				</div>
			</c:if>
		</div>		
<!-- 		===================================M01FORM============================================================= -->
		<form name="insert" id="submitFormM01" class="col-12" method="post" action="${contextPath}/Order/addOrder">
			<div class="col-sm-12 orderInfo">
				<div class="pageTitle"><h5>訂購資訊</h5></div>
					<div class="card bg-faded mt-3 mb-3">
						<div class="card-block px-3 py-3">
							<div class=" d-flex flex-wrap px-0 ">
								<div class="col-12 px-2">
											<div class="form-group"><small class="note">* </small><label for="orderstatus">取餐方式:</label>
										<input type="text" id="orderstatus" name="orderstatus"class="form-control" value="<%=opVO.getOrderStatus()%>" readonly  >
											</div>
											<div class="form-group"><label for="note">備註:</label>
										<textarea class="form-control" id="note" rows="3" name="note" placeholder="輸入特別需求 限20字內"></textarea>
									</div>
										</div>
							</div>
						</div>
					</div><!-- /card -->
			</div>
			<!-- btn -->
			<div class="col-12 d-flex flex-wrap">
				<div class="col-md-6 col-sm-12 px-1 mt-1 mb-2">
					<a href="${contextPath}/frontstage/productEShop.jsp" class="btn btn-dark btn-lg btn-block">返回</a>
				</div>
				<div class="col-md-6 col-sm-12 px-1 mt-1 mb-2">
					<button type="button" class="btn btn-danger btn-block btn-lg" id="form_confirmbtn" data-toggle="modal" data-target="#myModal" id="confirmorderbtn">確認訂購</button>
				</div>
			</div>
			</form>	
		<!-- 		===================================M01FORM============================================================= -->
			<!-- 		===================================M02FORM============================================================= -->
		<form name="insert" id="submitFormM02" class="col-12" method="post" action="${contextPath}/Order/addOrder">
			<div class="col-sm-12 orderInfo">
				<div class="pageTitle"><h5>訂購資訊</h5></div>
					<div class="card bg-faded mt-3 mb-3">
						<div class="card-block px-3 py-3">
							<div class=" d-flex flex-wrap px-0 ">
								<div class="col-lg-6 col-12 px-2">
								<small class="note">* </small><label for="payBy">付款方式:</label>
											<div class="form-group">
												<div class="attr_css col-12">
												<input type="radio" name="payBy" id="payByCash"  value="現金" checked >
												<label for="payByCash" >現金</label>
												<input type="radio" name="payBy" id="ApplePay"  value="ApplePay"  >
												<label for="ApplePay" >ApplePay</label>
												<input type="radio" name="payBy" id="GooglePay"  value="GooglePay"  >
												<label for="GooglePay" >GooglePay</label>
												</div>
											</div>

											<div class="form-group">
												<small class="note">* </small><label for="taketimeDate">預計取餐日期:</label>
										<input name="taketimeDate" class="form-control" id="taketimeDate" required value="<%=opVO.getTaketimeDate()%>" type="text" >
											</div>
											<div class="form-group"><small class="note">* </small><label for="taketimeTime">預計取餐時間:</label>
										<input name="taketimeTime" class="form-control" id="taketimeTime" required value="<%=opVO.getTaketimeTime()%>" type="text" >
											</div>
											<div class="form-group"><small class="note">* </small><label for="orderstatus">取餐方式:</label>
										<input type="text" id="orderstatus" name="orderstatus"class="form-control" value="<%=opVO.getOrderStatus()%>" readonly  >
											</div>
										</div>
										<div class="col-lg-6 col-md-12 col-sm-12 px-2">
											<div class="form-group"><label for="orderer">訂購人:</label>
										<input type="text" value="" id="orderer" maxlength="40" name="orderer" class="form-control" placeholder="選填 e.g.王先生">
											</div>	
											<div class="form-group"><small class="note">* </small><label for="phoneNum">訂購人手機:</label>
										<input type="text" id="phoneNum" value="0912456123" required name="phoneNum"class="form-control" value="" placeholder="格式:09XXXXXXXX" pattern="[0-9]{10}" title="09XXXXXXXX">
											</div>
											<div class="form-group"><label for="customerValue">統一編號:</label>
										<input type="text"  class="form-control" name="customerValue" id="customerValue" placeholder="選填,限8位數字" pattern="[0-9]{8}">
											</div>
									<div class="form-group"><label for="note">備註:</label>
										<textarea class="form-control" id="note" rows="3" name="note" placeholder="輸入特別需求 限20字內"></textarea>
									</div>
								</div>
							</div>
						</div>
					</div><!-- /card -->
			</div>
			<!-- btn -->
			<div class="col-12 d-flex flex-wrap">
				<div class="col-md-6 col-sm-12 px-1 mt-1 mb-2">
					<a href="${contextPath}/frontstage/productEShop.jsp" class="btn btn-dark btn-lg btn-block">返回</a>
				</div>
				<div class="col-md-6 col-sm-12 px-1 mt-1 mb-2">
					<button type="button" class="btn btn-danger btn-block btn-lg" id="form_confirmbtn" data-toggle="modal" data-target="#myModal" id="confirmorderbtn">確認訂購</button>
				</div>
			</div>
			</form>	
				<!-- 		===================================M02FORM============================================================= -->
     	</div> <!-- /.row -->
    </div> <!-- /.container -->
	<div class="modal fade" tabindex="3" role="dialog" id="myModal">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">總計NT${totalPrice} 元</h4>
				</div>
				<div class="modal-body">
<%-- 						<p>預計<%=taketimeDate%> <%=taketimeTime%>取餐</p> --%>
					<p>確定要送出訂單嗎?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" id="confirmbtn"  class="btn btn-primary">確定</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	 <script src="<%=request.getContextPath()%>/resource/js/taketime.js"></script>
    <script type="text/javascript">
	    $(document).ready(function() {
	    	var takeinfo=null;
	    	if('${TAKEINFO}'==="M01"){
	    		takeinfo='M01';
	    		$('#submitFormM01').show();
	    		$('#submitFormM02').hide();
	    		
	    	}
	    	if('${TAKEINFO}'==="M02"){
	    		takeinfo='M02';
	    		$('#submitFormM02').show();
	    		$('#submitFormM01').hide();
	    		
	    	}
	    	
	        //show errorMsgs
	        <c:if test="${not empty errorMsgs}">
	          <c:forEach var="message" items="${errorMsgs}">
	            toastr.error("${message}");
	          </c:forEach>
					</c:if>

		$('#form_confirmbtn').on('click', function(e){
			$('#myModal').modal('show');
			return false;
       	  	});
          $( "#confirmbtn" ).click(function() {
        	  if(takeinfo==='M01'){
        		  $( "#submitFormM01" ).submit();
        	  }
        	  if(takeinfo==='M02'){
        		  $( "#submitFormM02" ).submit();
        	  }
          	
          });
	    });

    </script>
  </body>
 
</html>
