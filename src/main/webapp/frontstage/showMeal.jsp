<%@  page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
response.flushBuffer();
String webprojectName =request.getContextPath().substring(1);
pageContext.setAttribute("webprojectName",webprojectName);
%>	
<!DOCTYPE html>
<html lang="zh-Hant">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>訂單紀錄</title>
    <%@ include file="/templete/link.jsp" %>
    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath()%>/resource/css/cart.css" rel="stylesheet">
  </head>

  <body onload="onLoad()">
    <%@include file="/templete/header.jsp" %>
    <!-- Page Content -->
    <div class="container mt-4 pb-4" id="show-box">
      <div class="row">
	     <div class="col-lg-12 col-md-12 col-sm-12 ">
	      <button id="btn_clear" class="btn btn-primary float-right" >清除我的資料</button>
	     </div>
	     <div class="col-lg-12 col-md-12 col-sm-12 d-flex flex-wrap order_box px-0" id="order_box">
	<!-- append資料於此 -->
	     </div>  
      </div>
      <!-- /.row -->
    </div>
    <!-- /.container -->
    <!-- clear-box -->
    <div class="container mt-2 mb-5" id="clear-box" style="height:500px;width:100%;display: none;" >
	    <div class="row">
			<div class="col-lg-12 mb-5">
<!-- 		        <div class="title_style_01"><h5>通知</h5></div> -->
				 <figure style="text-align:center;height:300px;">
				 	<img src="<%=request.getContextPath()%>/img/logo/empaty_cart.png"></img>
				 	<h3>您尚無訂餐紀錄</h3>
				 </figure>
			  	<div class="col-lg-6 offset-lg-3 col-md-12 col-sm-12">
			  	   	<a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-success btn-lg btn-block" >回首頁</a>
				</div>
			 </div>
		 </div>
	</div>
       <!-- /clear-box -->
    <!-- Footer -->
<%--     <%@include file="/templete/footer.jsp" %> --%>
  </body>
 <div class="modal fade" tabindex="3" role="dialog" id="clearModal">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
      
        <h4 class="modal-title">提醒</h4>
      </div>
      <div class="modal-body">
        <p>確定要清除訂單紀錄?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" id="confirmbtn"  class="btn btn-primary">確定</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</html>
     <script type="text/javascript">
     function onLoad(){
    	 if(typeof(Storage)=="undefined"){
    		 alert("Sorry,您的瀏覽器不支援WebStorge 建議使用IE+8,Firefox,Opera,Chrome,Safari");
    	 }else{
    		
			if(localStorage.length!=0){//如果localStorage不為空
				//如果localStorage.getItem('${contextPath}') 是contextPath
				if(localStorage.getItem('${contextPath}')!=null){
					$("#clear-box").hide(); 
					$("#show-box").show();
					loadFromLocalStorage();
				}
    	    	else{
    	    		$("#clear-box").show();
    	    		$("#show-box").hide();
    	    	}
		         $('#btn_clear').on('click', function(e){
		      	  $('#clearModal').modal('show');
		      		return false;
		    	  });
		         $( "#confirmbtn" ).click(function() {
		        	$("#clear-box").show();
		      		$("#show-box").hide();
		      		localStorage.removeItem('${contextPath}');
		      		$('#clearModal').modal('hide');
		       	});
			
// 			 btn_clear.addEventListener("click", clearLocalStorage); 
//     		 clearLocalStorage();
    	 }else{
    		 console.log("進到localStorage.length!=0else");
    		 $("#clear-box").show();
	    		$("#show-box").hide();
    	 }
     }
     function clearLocalStorage(){	
			$("#clear-box").show();
     		$("#show-box").hide();
     		localStorage.removeItem('${contextPath}');
     }
     function loadFromLocalStorage(){	
    	
		//將資料庫的陣列取出
    	    let itemarrayJason = JSON.parse(localStorage.getItem('${contextPath}'));
    	  //假如資料庫內的陣列有內容存在，執行以下的程式碼
    	    if (itemarrayJason.length !== 0) {
    	    	//對陣列裡的每個元素執行函式
    	        let content = "";
    	        for(var i = itemarrayJason.length-1; i >=0 ; i--) {
    			var obj = itemarrayJason[i];
    			var orderobj =obj.order;
    			  content +="<div class=\'col-lg-4 col-md-6 col-sm-12 px-1 \'>";
    			  content +="<div class=\'card mt-2\'>";
    			  content +=" <div class=\'card-header\'>領餐單號 </div>";
    			  content+= " <div class=\'card-body\'>";
    			  content+= " <p class=\'card-text text-center\' id=\'ordersNum\'>訂單號碼:"+orderobj.ordersNum+" </p>";
    			  content+=" <p class=\'card-text text-center\' >取餐時間:<span id=\'takeTime"+i+"\'>"+orderobj.takeTime+"</span></p>";
    			  content+=" <h1 class=\'card-title text-center\' id=\'mealNum\'>"+orderobj.mealNum+"</h1>";
//     			  content+=" <p class=\'text-center\'><span class=\'badge badge-pill badge-info\'id=\'processStatus"+i+"\'> </span></p>";
    			  content+="<p class=\'card-text text-center\'><span id=\'name\' >店家名稱:  "+orderobj.name+"</span>&nbsp; <span id=\'store\' >"+orderobj.store+"</span></p>";
    			  content+="<p class=\'card-text text-center\' >付款狀態:<span id=\'paymentStatus"+i+"\'></span></p>";
    			  content+="<img style=\'display:block; margin:auto;\' src=\'"+orderobj.mealQrcode+"\' id=\'mealQrcode\'>";
    			  content+="<p class=\'card-text text-center\' >請持QRCode至櫃台結帳</p>";
    			  content+="<div class=\'checkOrder\'><div id=\'headingOne\'>";
    			  content+="<a href=\'#\' class=\'btn btn-warning\' data-toggle=\'collapse\' data-target=\'#collapseOne"+i+"\' aria-expanded=\'true\' aria-controls=\'collapseOne"+i+"\'>";
    			  content+="查看明細</a></div>";
    			  content+="<div id=\'collapseOne"+i+"\' class=\'collapse \' aria-labelledby=\'headingOne\' data-parent=\'#accordionExample\'>";
    			  content+="<div class=\'card-body px-0 py-0\'><div class=\'col-12\'>";
    			  content+="<div id=\'prod_list \'>";
    			  content+="<ul class=\'form_style_02 pl-0 detailul\' ></ul>";
    			  var detailjsonArray =obj.detail;
    			  for(var j=0;j<detailjsonArray.length;j++){
      				var obj=detailjsonArray[j];
//       				console.log(obj);
      				content+="<li><div class=\'grid_3 item_image\'>"+"<img src=\'"+obj.productImg+"\' id=\'productImg\'></div>"
  					+"<div class=\'\'><h5 id=\'productname\'>"+obj.productname+"<span id=\'attrnote\'>"
  					+obj.attrnote+"</span></h5>$<span id=\'realprice\'>"+obj.realprice+"</span></p>"
  					+"<p>數量: <span class=\'\' id=\'qty\'>"+obj.qty+"，</span>小計:<span id=\'totalprice\'>"
  					+obj.totalprice+"</span>元</p></div><span class=\'clearfix\'></span></li>";
      				
      			}
    			  
    			  content+="<div class=\'pl-0 allInfo\'><p>";
    			  content+="共<span class=\'note\' id=\'totalQty\'>"+orderobj.totalQty+"</span> 項商品 </p>";
    			  content+="<p class=\'note\'>總金額 NT$<span class=\'subtotalnumber note\' id=\'invoicesTotal\'>"+orderobj.invoicesTotal+"</span>元</p>";
    			  content+="</div></div></div></div></div></div></div>";
    			  content+="<div class=\'card-footer text-muted\'><p id=\'orderDate\'>訂單成立時間: "+orderobj.orderDate+"</p>";
    			  content+="<p id=\'orderer\'>訂購人:"+orderobj.orderer+"</p>";
    			  content+="<p id=\'phoneNum\'>訂購人電話:"+orderobj.phoneNum+"</p>";
    			  content+="<p id=\'customerValue\'>統一編號:"+orderobj.customerValue+"</p>";
    			  content+="<p id=\'note\'>訂單備註:"+orderobj.note+"</p></div></div></div>";
    	        
    	        //更新狀態
    	        var eachtakeTime ="#takeTime"+i;
    	        var ordersNum=orderobj.ordersNum;
//     	        console.log("ordersNum"+ordersNum);
    	        var eachprocessStatus="#processStatus"+i;
    	        var eachpaymentStatus= "#paymentStatus"+i
    	        updateProcess(eachtakeTime,ordersNum,eachprocessStatus,eachpaymentStatus);
    	        }
    	        $("#order_box").append(content);
//     	        console.log("訂單讀取完");
    	        
     	}
     }

     
     function updateProcess(eachtakeTime,ordersNum,eachprocessStatus,eachpaymentStatus){
    	 $.ajax({
             type: "POST",
             cache: false,
             contentType:'application/json;charset=UTF-8',
             url: "<%=request.getContextPath()%>/Order/updateProccess",
             data: JSON.stringify({ "ordersNum": ordersNum
             }),
             dataType: 'json',
             beforeSend: function (xhr) {
             }
         }).done(function (resjobj, textStatus) {
//              console.log(JSON.stringify(resjobj, undefined, 2));
             var res=resjobj.map;
//              if (res.status == "done") {
             	var processStatus_s =res.processStatus;
    	        switch(processStatus_s) { 
    	        case 0:
    	        	$(eachprocessStatus).text("店家尚未確認訂單");
    	        	$(eachprocessStatus).removeClass('badge-info');
    	        	$(eachprocessStatus).addClass('badge-danger');
    	        	break;
    	        case 1: 
                	$(eachprocessStatus).text("店家確認中");
                	$(eachprocessStatus).removeClass('badge-info');
                	$(eachprocessStatus).addClass('badge-secondary');
                    break; 
                case 1: 
                	$(eachprocessStatus).text("訂單成立");
                	$(eachprocessStatus).addClass('badge-info');
                    break; 
                case 2: 
                	$(eachprocessStatus).text("店家接受訂單"); 
                	$(eachprocessStatus).removeClass('badge-info');
                	$(eachprocessStatus).addClass('badge-success');
                    break; 
                case 3: 
                	$(eachprocessStatus).text("餐點準備中"); 
                	$(eachprocessStatus).removeClass('badge-info');
                	$(eachprocessStatus).removeClass('badge-success');
                	$(eachprocessStatus).addClass('badge-warning');
                    break; 
                case 4: 
                	$(eachprocessStatus).text("叫號中"); 
                	$(eachprocessStatus).removeClass('badge-info');
                	$(eachprocessStatus).removeClass('badge-success');
                	$(eachprocessStatus).removeClass('badge-warning');
                	$(eachprocessStatus).addClass('badge-primary');
                    break; 
                case 5: 
                	$(eachprocessStatus).text("訂單已取消"); 
                	$(eachprocessStatus).removeClass('badge-info');
                	$(eachprocessStatus).addClass('badge-danger');
                    break; 
                
            }
    	        var paymentStatus_s =res.paymentStatus;
    	        switch(paymentStatus_s) { 
                case true: 
                	$(eachpaymentStatus).text("已付款");
                    break; 
                case false: 
                	$(eachpaymentStatus).text("未付款"); 
                    break; 
            }
    	        
    	        var takeTime=res.takeTime;
    	        $(eachtakeTime).text(takeTime);
//              }

         }).fail(function (jqXHR, textStatus, errorThrown) {
//              console.log('jqXHR.responseText: ' + jqXHR.responseText);
             	console.log('jqXHR.status: ' + jqXHR.status);
            	 console.log('jqXHR.readyState'+ jqXHR.readyState);
            	 console.log('jqXHR.statusText'+jqXHR.statusText);
            	 console.log('textStatus'+textStatus);
            	 console.log('errorThrown'+errorThrown);
         }).always(function (jqXHR, textStatus) {
             //$('#loding_spinner').fadeOut(300);
         });
    	 
     }
  
     }
     </script>
