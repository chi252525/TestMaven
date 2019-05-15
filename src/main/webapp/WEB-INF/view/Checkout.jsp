<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.welljoint.entity.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<!-- 訂購資訊 -->
		<form name="insert" id="submitForm" class="col-12" method="post" action="${contextPath}/Order/addOrder">
			<div class="col-sm-12 orderInfo">
				<div class="pageTitle"><h5>訂購資訊</h5></div>
					<div class="card bg-faded mt-3 mb-3">
						<div class="card-block px-3 py-3">
							<div class=" d-flex flex-wrap px-0 ">
								<div class="col-12 px-2">
											<div class="form-group">
												<small class="note">* </small><label for="payBy">付款方式:</label>
												<div class="custom-control custom-radio">
													<input type="radio" id="payByCash" name="payBy" value="現金" class="custom-control-input">
													<label class="custom-control-label" for="payByCash">自取到店付款</label>
												</div>
												<div class="custom-control custom-radio">
													<input type="radio" id="ApplePay" name="payBy" value="ApplePay" class="custom-control-input">
													<label class="custom-control-label" for="ApplePay">ApplePay</label>
												</div>
												<div class="custom-control custom-radio">
													<input type="radio" id="GooglePay" name="payBy" value="GooglePay" class="custom-control-input">
													<label class="custom-control-label" for="GooglePay">GooglePay</label>
												</div>
											</div>

											<div class="form-group">
												<small class="note">* </small><label for="taketime1">預計取餐日期:</label>
										<input name="taketime1" class="form-control" id="taketime1" value="預計起餐日期" type="text" >
											</div>
											<div class="form-group"><small class="note">* </small><label for="taketime2">預計取餐時間:</label>
										<input name="taketime2" class="form-control" id="taketime2" value="預計取餐時間" type="text" >
											</div>
											<div class="form-group"><small class="note">* </small><label for="orderstatus">取餐方式:</label>
										<input type="text" id="orderstatus" name="orderstatus"class="form-control" value="外帶" readonly  >
											</div>
										</div>
										<div class="col-lg-6 col-md-12 col-sm-12 px-2">
											<div class="form-group"><label for="orderer">訂購人:</label>
										<input type="text" value="王先生" id="orderer" maxlength="40" name="orderer" class="form-control" placeholder="選填 e.g.王先生">
											</div>	
											<div class="form-group"><small class="note">* </small><label for="phoneNum">訂購人手機:</label>
										<input type="text" id="phoneNum" value="0912456123" name="phoneNum"class="form-control" value="" placeholder="格式:09XXXXXXXX" pattern="[0-9]{10}" title="09XXXXXXXX">
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
     	</div> <!-- /.row -->
    </div> <!-- /.container -->
	<div class="modal fade" tabindex="3" role="dialog" id="myModal">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">總計NT${totalpriceInt} 元</h4>
				</div>
				<div class="modal-body">
<%-- 						<p>預計<%=taketime1%> <%=taketime2%>取餐</p> --%>
					<p>確定要送出訂單嗎?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" id="confirmbtn"  class="btn btn-primary">確定</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
    <script type="text/javascript">
	    $(document).ready(function() {
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
          	$( "#submitForm" ).submit();
          });
	    });
        $('#taketime1').datepicker({
        	startDate: '0',
            format: 'yyyy-mm-dd',
            endDate: '+3d',
            daysOfWeekDisabled: "0",
            disableTouchKeyboard:true,
            language:'zh-TW',
            todayBtn:'linked',
            clearBtn:true,
            orientation: "bottom auto",
            toggleActive: true,
            showOnFocus:true,
            todayHighlight:true,
            autoclose: true,
        });
        $( "#taketime2" ).timeDropper(
          { format:'HH:mm',
            setCurrentTime:false,
          }
        );

    </script>
  </body>
 
</html>
