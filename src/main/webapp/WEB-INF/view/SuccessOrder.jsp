
<%@  page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.welljoint.entity.*"%>
<jsp:useBean id="dateValue" class="java.util.Date"/>
<%
String webprojectName =request.getContextPath().substring(1);
%>
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
    <title>完成訂單</title>
    <%@ include file="/templete/link.jsp" %>
    <link href="<%=request.getContextPath()%>/resource/css/cart.css" rel="stylesheet">
  </head>
  <body onload="onLoad()">
     <%@include file="/templete/header.jsp" %>
    <!-- Page Content -->
    <div class="container mt-5 pb-4">
     <div class="row">
     <div class="col-lg-4 col-md-4 col-sm-12 orderNumber">
          <div class="card text-center">
	  		  <div class="card-header">領餐單號</div>
			  <div class="card-body">
				   <p class="card-text" >取餐時間:
				   <jsp:setProperty name="dateValue" property="time" value="${successOrder.takeTime}"/>
					   	<span id="takeTime"><fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd HH:mm"/></span>
				  	</p>
				    <h1 class="card-title" id="mealNum">${successOrder.mealNum}</h1>
				    <p >
				    </p>
				    <p class="card-text">店家名稱: 
					    <span id="name" >${successOrder.name} </span>&nbsp; 
					    <span id="store" >${successOrder.store}</span>
				    </p>
					<p class="card-text" id="paymentStatus">付款狀態:
						${successOrder.paymentStatus==false?'未付款':'已付款'}
					 </p>
					<img src="<%=request.getContextPath()%>/img/QRcode/${successOrder.uuid}.jpg" id="mealQrcode">
			  </div>
	  		  <div class="card-footer text-muted">請持Qrcode至櫃台結帳</div>
	    </div>
		<div class="card mt-3 mb-3">
		  <h5 class="card-header">訂購資訊</h5>
		  <div class="card-body" >
			  <p class="card-text" >訂單成立時間: <span id="orderDate"><fmt:formatDate value="${successOrder.orderDate}" pattern="yyyy-MM-dd HH:mm" /></span>
			  </p>
			  <p class="card-text" >訂單號碼:<span id="ordersNum">${successOrder.ordersNum}</span></p>
			  <p class="card-text" >訂購人:<span id="orderer">${successOrder.orderer}</span></p>
			  <p class="card-text" >訂購人電話:<span id="phoneNum">${successOrder.phoneNum}</span></p>
			  <p class="card-text" >統一編號:<span id="customerValue">${successOrder.customerValue}</span></p>
			  <p class="card-text" >訂單備註:<span id="note">${successOrder.note}</span></p>    
		  </div>
		</div>            
</div>  
	<div class="col-lg-8 col-md-8 col-sm-12">
		<div class="pageTitle"><h5>購物明細</h5></div>
	<div id="prod_list ">
	<ul class="ulList pl-0">
	
	<c:forEach var="pVO" items="${successOrderdetil}" varStatus="s" >
		<li class="dtlist row">
				<div class="item_image col-4">
					<img src="<%=request.getContextPath()%>/img/product/${pVO.productImg}" 
					id="productImg${s.index}" data-productImg="productImg${s.index}">
				</div>
			<div class="my-0 col-8">
	  			<h5 id="productname${s.index}" data-productname="productname${s.index}">${pVO.productionName}</h5>
	  			<p id="attrnote${s.index}" data-attrnote="attrnote${s.index}">${pVO.shoppingNote}</p>
			  <div class="row">
				  <p class="col-6">$ <span id="realprice${s.index}" data-realprice="${pVO.shoppingPrice}" >${pVO.shoppingPrice}</span></p>
				  <p class="col-6" align="right">數量: <span class="note" data-qty="qty${s.index}" id="qty${s.index}">${pVO.shoppingQty}</span></p>
			  </div>
			<h5 class="float-right">小計:<span data-totalprice="totalprice${s.index}" id="totalprice${s.index}" class="note">${pVO.shoppingSubtotalprice}</span>元</h5>
			</div> <span class="clearfix"></span>
		</li>
	</c:forEach>
		
		<li class="totalQty" align="center">
			<p class="px-2">共 <span class="note" id="totalQty">${successtotalQty}</span> 項商品</p>
			<p class="px-2" >總金額 NT$
	      		<span class="note" id="invoicesTotal">${successtotalPrice}</span>元
			</p>
		</li>
	</ul>
	</div>
      </div><!-- /.col-lg-12 -->
      </div>
      <!-- /.row -->
    </div>
    <!-- /.container -->
    <!-- Footer -->
<%--      <%@include file="/templete/footer.jsp" %> --%>
<script type="text/javascript">
     function onLoad(){
    	 if(typeof(Storage)=="undefined"){
    		 alert("Sorry,您的瀏覽器不支援WebStorge 建議使用IE+8,Firefox,Opera,Chrome,Safari");
    	 }else{
    		 
    		 saveToLocalStorge();//存入訂單
    	 }
     }
     var itemstorageArray=null;
     function saveToLocalStorge(){
    	//當localStorage沒有資料陣列，指定一個空陣列放入資料庫
			if (localStorage.getItem("${contextPath}") === null) {
			    itemstorageArray = [];
			    localStorage.setItem("${contextPath}", JSON.stringify(itemstorageArray));
			//當localStorage已存在資料陣列，指定一個內容與陣列資料庫相同的陣列
			} else {
				itemstorageArray = JSON.parse(localStorage.getItem('${contextPath}'));
			}; 
			//處理明細部分
			var storageArray = [];
			for(var i=0;i<'${successOrderdetil.size()}';i++){
				   let productImg = $("#productImg"+i).attr("data-productImg");
				   let productname = $("#productname"+i).attr("data-productname");
				   let attrnote = $("#attrnote"+i).attr("data-attrnote");
				   let realprice = $("#realprice"+i).attr("data-realprice");
				   let qty = $("#qty"+i).attr("data-qty");
				   let totalprice = $("#totalprice"+i).attr("data-totalprice");
				
					//建立一個符合我們需求的物件資料
				    detailObject = { 
							'productImg' : $("#"+productImg).attr("src"),
							'productname': $("#"+productname).text(),
							'attrnote' : $("#"+attrnote).text(),
							'realprice' : realprice,
							'qty' : $("#"+qty).text(),
							'totalprice':$("#"+totalprice).text(),
						};
						//將新物件加入我們的陣列
					    storageArray.push(detailObject);
			 		   //將陣列修改成JSON字串
					    stringJson = JSON.stringify(storageArray);
					  console.log(stringJson);
			 		    //將處理後的JSON字串更新到資料庫中
					    localStorage.setItem(`detail${contextPath}`, stringJson);
			}

//     		 saveToLocalStorgedetail(storageArray);//存入訂單明細
			
			//建立一個符合我們需求的物件資料
		    itemObject = {
					'order':{ 'takeTime' : $("#takeTime").text(),
					'mealNum': $("#mealNum").text(),
					'name' : $("#name").html(),
					'store' : $("#store").html(),
					'orderDate' : $("#orderDate").text(),
					'ordersNum' : $("#ordersNum").html(),
					'orderer' : $("#orderer").text(),
					'phoneNum' : $("#phoneNum").text(),
					'customerValue' : $("#customerValue").text(),
					'note' : $("#note").text(),
					'mealQrcode' : $("#mealQrcode").attr("src"),
					'itemsqty' : $("#itemsqty").text(),
					'invoicesTotal' : $("#invoicesTotal").text(),
					'totalQty' : $("#totalQty").text(),
						},
					'detail': storageArray,
					'time':new Date().getTime(),
					'expire':5000,
			};
				//將新物件加入我們的陣列
			    itemstorageArray.push(itemObject);
				
			  //將陣列修改成JSON字串
			    itemstringJson = JSON.stringify(itemstorageArray);
			  console.log(itemstringJson);
			    //將處理後的JSON字串更新到資料庫中
			    localStorage.setItem(`${contextPath}`, itemstringJson);
     }
     $(document).ready(function (){
	   	  var orderershow=$("#orderer").text().trim();
	   	  if( orderershow.length==0){
	   		  $("#orderer").text("無填寫");
	   	  }
	   	  var customerValueshow=$("#customerValue").text().trim();
	   	  if( customerValueshow.length==0){
	   		  $("#customerValue").text("不需統編");
	   	  }
	   	  var noteshow=$("#note").text().trim();
	   	  if( noteshow.length==0){
	   		  $("#note").text("無備註");
	   	  }
	   	  
	   	//show errorMsgs
	        <c:if test="${not empty errorMsgs}">
	          <c:forEach var="message" items="${errorMsgs}">
	            toastr.error("${message}");
	          </c:forEach>
	        </c:if>
	        
	        <c:if test="${not empty successMsgs}">
	        <c:forEach var="successmessage" items="${successMsgs}">
	          toastr.success("${successmessage}");
	        </c:forEach>
	      </c:if>
     });
    
	</script>
	
  </body>

</html>
