<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.welljoint.*"%>
<%-- <%  --%>
<!-- // ArrayList<ProductVO> shoppingList=(ArrayList<ProductVO>)session.getAttribute("shoppingList"); -->
<!-- // pageContext.setAttribute("shoppingList",shoppingList); -->
<!-- // System.out.println("shoppingList="+shoppingList); -->
<%-- %> --%>
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
    <style>
    .qtyinput {
  width: 40px;
  height: 35px;
  text-align: center;
  border: 0;
  border-top: 1px solid #aaa;
  border-bottom: 1px solid #aaa;
}
    
    </style>
   </head>
  <body>
      <%@include file="/templete/header.jsp" %>
    <!-- Page Content -->
    <div class="container mt-4 pb-4">
     <c:if test="${ not empty shoppingList}">
		<div class="col-12 mb-5">
		
		
        <div class="pageTitle"><h2>購物明細</h2></div><br>

	<div id="prod_list row">
	<ul class="ulList pl-0 mb-5">
	<c:forEach varStatus="s" var="pVO" items="${shoppingList}">
		<li class="row">
			<div class="item_image col-12 col-sm-4">
				<img
					src="<%=request.getContextPath()%>/img/product/${pVO.productImg}">
			</div>
			<div class="my-0 col-12 col-sm-8">
				<h5>${pVO.productionName}</h5>
				<p>
					<span data-note="note" id="note">${pVO.shoppingNote}</span>
				</p>
				<input id="id" type="hidden" value="${pVO.id}">
				<p>
					NT.<span id="showprices">&nbsp; ${pVO.shoppingPrice} 元</span>
				</p>
				<div class="d-flex flex-wrap justify-content-between">
					<div>
						數量: <span id="qty${pVO.hashCode()}" class="qty">${pVO.shoppingQty}</span>
						<button type="button" class="btn btn-warning updatebtn"
							id="${pVO.hashCode()}">
							<i class="fa fa-pencil"></i>
						</button>
					</div>
					<div>
						<a
							href="<%=request.getContextPath()%>/Cart/delete/${pVO.hashCode()}"
							class=" delbtn btn btn-light " data-del="del" id="del"><i
							class="fa fa-trash text-danger"></i></a>
					</div>
				</div>
				<h5 class="float-right mt-2">
					小計:<span id="subtotalprice" class="note"
						data-subtotalprice="subtotalprice">${pVO.shoppingSubtotalprice}</span>元
				</h5>
			</div>
			<span class="clearfix"></span>
		<hr>
		</li>
	</c:forEach>
						<li class="totalQty px-2" align="right">
				共 <span class="note">${totalQty}</span> 項商品 總計:<span class="note allPrice">${totalPrice}</span>元
		</li>
	</ul>
	</div>
	<div class="col-12 d-flex flex-wrap px-0 mb-5">
		<div class="col-md-6 col-sm-12 px-1 mt-1 mb-2">
	 		<a href="<%=request.getContextPath()%>/frontstage/productEShop.jsp" class="btn btn-lg btn-dark btn-block" >繼續點餐</a>
		</div>
		<div class="col-md-6 col-sm-12 px-1 mt-1 mb-2">
			<a href="<%=request.getContextPath() %>/Cart/Checkout" class="btn btn-lg btn-danger btn-block">去結帳</a>
		</div>
	</div>
      </div>
      </c:if>
		<c:if test="${empty shoppingList}">
			<div class="col-12 " style="text-align: center; min-height: 300px;">
				<figure>
					<img src="<%=request.getContextPath()%>/img/logo/empaty_cart.png"></img>
					<figcaption>購物車目前尚無商品</figcaption>
				</figure>
			</div>
			<div class="col-lg-6 offset-lg-3 col-md-12 col-sm-12">
				<a href="<%=request.getContextPath()%>/index.jsp"
					class="btn btn-success btn-lg btn-block">開始點餐</a>
			</div>
		</c:if>
	</div>
<div class="modal fade" tabindex="3" role="dialog" id="delModal">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">提醒</h4>
      </div>
      <div class="modal-body">
        <p>確定要刪除此商品嗎?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" id="confirmdelbtn"  class="btn btn-primary">確定</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" tabindex="3" role="dialog" id="updateModal">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">修改數量</h4>
      </div>
		<div class="modal-body">
			<div class="d-flex flex-wrap justify-content-between">
				<div>
				
				<form method="post" action="<%=request.getContextPath()%>/Cart/update" id="updateQtyform"  >
				
					<button type="button" id="minus" class="btn btn-warning qtyminus" field='quantity'><i class="fa fa-minus"></i>
					</button>
					<input type="text" name="quantity" class="qtyinput" id="qtyinput" value="" readonly>	
					<input type="hidden" name="hashid" id="hashid" value="">					
					<button type="button" id="add" class="btn btn-warning qtyplus" field='quantity'><i class="fa fa-plus"></i>
					</button>
					</form>
				</div>
			</div>
		</div>
				<div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" id="confirmupdatebtn"  class="btn btn-primary">更新購物車</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript">
$(function() {
	  // This button will increment the value
	  $('.qtyplus').click(function(e) {
	    // Stop acting like a button
	    e.preventDefault();
	    // Get the field name
	    fieldName = $(this).attr('field');
	    // Get its current value
	    var currentVal = parseInt($('input[name=' + fieldName + ']').val());
	    // If is not undefined
	    if (!isNaN(currentVal)) {
	      // Increment
	      $('input[name=' + fieldName + ']').val(currentVal + 1);
	    } else {
	      // Otherwise put a 0 there
	      $('input[name=' + fieldName + ']').val(1);
	    }
	  });
	  // This button will decrement the value till 0
	  $(".qtyminus").click(function(e) {
	    // Stop acting like a button
	    e.preventDefault();
	    // Get the field name
	    fieldName = $(this).attr('field');
	    // Get its current value
	    var currentVal = parseInt($('input[name=' + fieldName + ']').val());
	    // If it isn't undefined or its greater than 0
	    if (!isNaN(currentVal) && currentVal > 1) {
	      // Decrement one
	      $('input[name=' + fieldName + ']').val(currentVal - 1);
	    } else {
	      // Otherwise put a 0 there
	      $('input[name=' + fieldName + ']').val(1);
	    }
	  });
	});



	$(document).ready(
	
	function() {
		<c:if test="${not empty remindMsgs}">
		<c:forEach var="remindmessage" items="${remindMsgs}">
		toastr.info("${remindmessage}");
		</c:forEach>
		</c:if>
	});
	$(document).ready(function() {
		let delid = null;
		$(document).on('click', '.delbtn', function(event) {
			delid = event.currentTarget.id;
			$('#delModal').modal('show');
			return false;
		});
		$("#confirmdelbtn").click(function() {
			var href = $("#" + delid).attr('href');
			window.location.href = href;
		});
	});
	$(document).ready(function() {
		let updateid = null;
		$(document).on('click', '.updatebtn', function(event) {
			updatehashCode = event.currentTarget.id;
			var outqty=$("#qty"+updatehashCode).html();
			$("#qtyinput").val(outqty);
			$("#hashid").val(updatehashCode);
			console.log($("#qtyinput").val());
			console.log("updatehashCode="+updatehashCode)
			$('#updateModal').modal('show');
			return false;
		});
		$("#confirmupdatebtn").click(function() {
			$("#updateQtyform").submit();
			$('#updateModal').modal('hide');
			toast.success("更新成功!");
		});
	});
</script>	
	
</body>

</html>
