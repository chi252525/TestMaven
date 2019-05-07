<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.welljoint.*"%>
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
    <!-- Page Content -->
    <div class="container mt-4 pb-4">
      <div class="">
		<div class="col-12 mb-5">
        <div class="pageTitle"><h2>購物明細</h2></div><br>

	<div id="prod_list row">
	<ul class="ulList pl-0 mb-5">
		<li class="row">
			<div class="item_image col-12 col-sm-4">
				<img src="<%=request.getContextPath()%>/img/product/11529464933.jpg">
			</div>
			<div class="my-0 col-12 col-sm-8">
				<h5>咖哩豬排飯</h5>
				<p><span data-note="note" id="note">加飯</span></p>
				<input id="id" data-id="id" type="hidden" value="2915">
				<input type="hidden" data-usediscountprice="usediscountprice" name="usediscountprice" id="usediscountprice" value="false">
				<input type="hidden" data-prices="prices" name="prices" id="prices" value="80">
				<input type="hidden" data-discountPrice="discountPrice" name="discountPrice" id="discountPrice" value="70">
				<input type="hidden" data-productAmount="productAmount" name="productAmount" id="productAmount" value="100">
				<p>$<span id="showprices">&nbsp;元</span></p>
					<div class="d-flex flex-wrap justify-content-between">
						<div>
								<button type="button" id="minus" data-minus="minus" class="btn btn-warning minusbtn"
								data-id="id" data-usediscountprice="usediscountprice" data-prices="prices"
								data-discountPrice="discountPrice" data-productAmount="productAmount" data-note="note"
								data-subtotalprice="subtotalprice" data-qty="qty" ><i class="fa fa-minus"></i>
								</button>
								<span id="qty" data-qty="qty" class="qty">2</span>
								<button type="button" id="add" data-add="add" class="btn btn-warning addbtn" 
								data-id="id" data-usediscountprice="usediscountprice" data-prices="prices"
								data-discountPrice="discountPrice" data-productAmount="productAmount" data-note="note"
								data-subtotalprice="subtotalprice" data-qty="qty"><i class="fa fa-plus"></i>
								</button>
						</div>
					 <div> 
					 	<a href="<%=request.getContextPath()%>/ShoppingServlet.do?action=DELETE&del=" class=" delbtn btn btn-light " data-del="del" id="del"><i class="fa fa-trash text-danger" ></i></a>    
	        		 </div> 
	        		</div>
        		<h5 class="float-right mt-2">小計:<span id="subtotalprice" class="note" data-subtotalprice="subtotalprice">20</span>元</h5>
			</div><span class="clearfix"></span><hr>
		</li>
				<script>
				 $('#showprices').html("NT.<S> <c:out value='20' /> </S> &nbsp; <b class="note">30</b> 元  ");
				 </script>
		<li class="totalQty px-2" align="right">
				共 <span class="note">1</span> 項商品 總計:<span class="note allPrice">60</span>元
		</li>
	</ul>
	</div>
	<div class="col-12 d-flex flex-wrap px-0 mb-5">
		<div class="col-md-6 col-sm-12 px-1 mt-1 mb-2">
	 		<a href="<%=request.getContextPath()%>/frontstage/productEShop.jsp" class="btn btn-lg btn-dark btn-block" >繼續點餐</a>
		</div>
		<div class="col-md-6 col-sm-12 px-1 mt-1 mb-2">
			<a href="<%=request.getContextPath() %>/ShoppingServlet.do?action=CHECKOUT" class="btn btn-lg btn-danger btn-block">去結帳</a>
		</div>
	</div>
 <c:if test="${empty shoppingcart}">
 <div class="col-12 " style="text-align:center;min-height:300px;">
	 <figure>
	 	<img src="<%=request.getContextPath()%>/img/logo/empaty_cart.png"></img>
	 	<figcaption>購物車目前尚無商品</figcaption>
	 </figure>
 </div>
 <div class="col-lg-6 offset-lg-3 col-md-12 col-sm-12">
  	<a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-success btn-lg btn-block">開始點餐</a>
 </div>
 </c:if>        

      </div><!-- /.col-lg-12 -->
      </div><!-- /.row -->
    </div><!-- /.container -->
<!-- Footer -->
<%--      <%@include file="/templete/footer.jsp" %> --%>
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
        <button type="button" id="confirmbtn"  class="btn btn-primary">確定</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript">
	function priceChange() {
		 var printPrice=0;
		 var price = 0;
		 var allprice = [];
		     $("h5.mt-2 span").each(function(){
		       price = Number($(this).html());
		       allprice.push(price);
		     });
	
		     for (var i = 0; i < allprice.length; i++) {
		         printPrice += allprice[i];
		     };
		     $("span.allPrice").html(printPrice);
	};
	
	$(document).ready(function(){
		<c:if test="${not empty remindMsgs}">
		    <c:forEach var="remindmessage" items="${remindMsgs}">
		      toastr.info("${remindmessage}");
		    </c:forEach>
	    </c:if>
		 $(document).on('click', '.addbtn', function (event) {
			  let tid = event.currentTarget.id;
			  let which_id = $("#"+tid).attr("data-id");
			  let which_qty = $("#"+tid).attr("data-qty");
			  let which_prices = $("#"+tid).attr("data-prices");
			  let which_discountPrice = $("#"+tid).attr("data-discountPrice");
			  let which_note = $("#"+tid).attr("data-note");
			  let which_productAmount = $("#"+tid).attr("data-productAmount");
			  let which_subtotalprice = $("#"+tid).attr("data-subtotalprice");
			  let which_usediscountprice = $("#"+tid).attr("data-usediscountprice");
		 $.ajax({
               type: "POST",
               cache: false,
               url: "<%=request.getContextPath()%>/ShoppingServlet.do",
               data: {'action': 'addCart',
               	'id': $("#"+which_id).val(),
               	'qty': $("#"+which_qty).text(),
               	  'prices': $("#"+which_prices).val(),
               	  'discountPrice':$("#"+which_discountPrice).val(),
               	  'note':$("#"+which_note).text(),
               	  'productAmount':$("#"+which_productAmount).val(),
               	  'alertMsg':"",
               	  'subtotalprice':$("#"+which_subtotalprice).text(),
               	  'usediscountprice':$("#"+which_usediscountprice).val(),
               },
               dataType: 'json',
               beforeSend: function (xhr) {
               	$("#"+which_subtotalprice).text("計算中...");
               	 $(".addbtn").prop('disabled', true);
               }
           }).done(function (orderadd, textStatus) {
//               if (order.status == "done") {
               	$("#"+which_qty).html(orderadd.qty);
                   $("#"+which_prices).val(orderadd.prices);
                   $("#"+which_discountPrice).val(orderadd.discountPrice);
                   $("#"+which_id).val(orderadd.id);
                   $("#"+which_note).html(orderadd.note);
                   $("#"+which_subtotalprice).text(orderadd.subtotalprice);
                   
                   if(orderadd.alertMsg.length!=0){
                   	 toastr.warning(orderadd.alertMsg);
                   }
                   $(".addbtn").prop('disabled', false);
//               }
                /*總金額*/
           		priceChange();
	           }).fail(function (jqXHR, textStatus, errorThrown) {
	                console.log('jqXHR.status: ' + jqXHR.status);
	            	console.log('jqXHR.readyState'+ jqXHR.readyState);
	            	console.log('jqXHR.statusText'+jqXHR.statusText);
	            	console.log('textStatus'+textStatus);
	            	console.log('errorThrown'+errorThrown);
	            	$("#"+which_subtotalprice).text("計算中...");
	        	 	$(".addbtn").prop('disabled', true);
	
	           }).always(function (jqXHR, textStatus) {
	               //$('#loding_spinner').fadeOut(300);
	           });
		
	});
		 $(document).on('click', '.minusbtn', function (event) {
			 let tid = event.currentTarget.id;
			  let which_id = $("#"+tid).attr("data-id");
			  let which_qty = $("#"+tid).attr("data-qty");
			  let which_prices = $("#"+tid).attr("data-prices");
			  let which_discountPrice = $("#"+tid).attr("data-discountPrice");
			  let which_note = $("#"+tid).attr("data-note");
			  let which_productAmount = $("#"+tid).attr("data-productAmount");
			  let which_subtotalprice = $("#"+tid).attr("data-subtotalprice");
			  let which_usediscountprice = $("#"+tid).attr("data-usediscountprice");
			$.ajax({
	           type: "POST",
	           cache: false,
	           url: "<%=request.getContextPath()%>/ShoppingServlet.do",
	           data: {'action': 'minusCart',
	           	'id': $("#"+which_id).val(),
	           	'qty': $("#"+which_qty).text(),
	           	  'prices': $("#"+which_prices).val(),
	           	  'discountPrice':$("#"+which_discountPrice).val(),
	           	  'note':$("#"+which_note).text(),
	           	  'productAmount':$("#"+which_productAmount).val(),
	           	  'alertMsg':"",
	           	  'subtotalprice':$("#"+which_subtotalprice).text(),
	           	  'usediscountprice':$("#"+which_usediscountprice).val(),
	           },
	           dataType: 'json',
	           beforeSend: function (xhr) {
		           $("#"+which_subtotalprice).text("計算中...");
		           $(".minusbtn").prop('disabled', true);
	           }
	    	}).done(function (orderminus, textStatus) {
	//               if (order.status == "done") {
			   $(".minusbtn").prop('disabled', false);
	           $("#"+which_qty).html(orderminus.qty);
               $("#"+which_prices).val(orderminus.prices);
               $("#"+which_discountPrice).val(orderminus.discountPrice);
               $("#"+which_id).val(orderminus.id);
               $("#"+which_note).html(orderminus.note);
               $("#"+which_subtotalprice).text(orderminus.subtotalprice);
               if(orderminus.alertMsg.length!=0){
               	 toastr.warning(orderminus.alertMsg);
               }
               /*總金額*/
          		priceChange();
	//               }
	       }).fail(function (jqXHR, textStatus, errorThrown) {
	            console.log('jqXHR.status: ' + jqXHR.status);
	          	console.log('jqXHR.readyState'+ jqXHR.readyState);
	          	console.log('jqXHR.statusText'+jqXHR.statusText);
	          	console.log('textStatus'+textStatus);
	          	console.log('errorThrown'+errorThrown);
	          	$("#"+which_subtotalprice).text("計算中...");
	       	 $(".minusbrn").prop('disabled', true);
	
	       }).always(function (jqXHR, textStatus) {
	           //$('#loding_spinner').fadeOut(300);
	       });
	});
	
});
	 
    $(document).ready(function(){	
    	 let delid=null;
	   	 $(document).on('click', '.delbtn', function (event) {
	   		 delid = event.currentTarget.id;
	   		  $('#delModal').modal('show');
	   		  return false;
	   	 });
	     $( "#confirmbtn" ).click(function() {
	    	 var href = $("#"+delid).attr('href');
	         window.location.href = href;
	   	 });
    });

</script>	
	
</body>

</html>
