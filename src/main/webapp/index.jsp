<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf"  uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.*"%>
<%@ page import="com.welljoint.entity.*"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	response.flushBuffer();
	session.removeAttribute("shoppingList");
	session.removeAttribute("myCart");
%>

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
    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath()%>/resource/css/full-width-pics.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/resource/css/index.css" rel="stylesheet">
  </head>
  <body>
  <%@include file="/templete/header.jsp" %>
    <header class="py-5 bg-image-full" style="background-image: url('<%=request.getContextPath()%>/img/banner/index_banner.jpg');">
      <img class="img-fluid d-block mx-auto " src="<%=request.getContextPath()%>/img/logo/img_home_logo.png" alt="img_home_logo.png">
    </header>

    <section>
      <div class="container indexForm">
        <div class="col-lg-6 offset-lg-3 col-md-12 col-sm-12">
        
 <!-- 		===================================M01FORM============================================================= -->       
<form method="post" action="<%=request.getContextPath()%>/Order/productEShop" id="shoppingformM01" enctype="x-www-form-urlencoded">
	<div class="form-group">
		<p><small class="text-muted-color">* </small><span>請選擇內用或外帶</span></p>
		<div class="row btn_radio">
			 <div class="col-6">
				 <input type="radio" id="forhere" name="orderStatus" value="內用" checked >
				 <label for="forhere" class="btn btn-lg btn-warning">內用</label>
		 	 </div>
			 <div class="col-6">
				 <input type="radio" id="takeout" name="orderStatus" value="外帶">
				 <label for="takeout" class="btn btn-lg btn-warning">外帶</label>
			 </div>
    	</div>
<!-- 		 <label for="internalNumber">桌號 (選填)</label> -->
<%-- 		 <input name="internalNumber" class="form-control" id="internalNumber"  type="text" > --%>
 	</div>
	 <button type="submit" class="btn btn-primary btn-lg btn-block">開始訂購&nbsp;</button>
 </form>
 <!-- 		===================================M01FORM============================================================= -->
 
 <!-- 		===================================M02FORM============================================================= -->
 <form method="post"	action="<%=request.getContextPath()%>/Order/productEShop" id="shoppingformM02">
					<div class="form-group">
						<small class="text-muted-color">* </small> 
						<label for="taketime1"><i class='fas fa-calendar-alt'></i>&nbsp;預計取餐日期：</label>
						 <input	name="taketime1" class="form-control" id="taketime1" required type="text"> <br>
						 <small class="text-muted-color">* </small> 
						 <label for="taketime2"><i class='fas fa-clock'></i>&nbsp;預計取餐時間：</label>
						<input name="taketime2" class="form-control" id="taketime2"	type="text"> <br> 
						<small class="text-muted-color">*</small> 
						<label for="orderstatus"><i class='fas fa-concierge-bell'></i>&nbsp;取餐方式：</label>
						<select class="form-control" id="orderstatus" name="orderStatus">
							<option data-orderStatusKey="Y">外帶</option>
						</select> 
					</div>
					<input	type="hidden" name="orderStatusKey" id="orderStatusKey">
					<input	type="hidden" name="taketime" id="taketime"> 
					<button type="submit" class="btn btn-primary btn-lg btn-block">開始訂購&nbsp;</button>
				</form>
  <!-- 		===================================M02FORM============================================================= -->
 
 		</div>
	</div>
    </section>
		<section class="bottomImg" style="text-align: center;background: #222;padding-top: 10px;">
			<img src="<%=request.getContextPath()%>/img/banner/img_home_bottom.png" style="max-width: 100%;height: auto;"></img>
		</section>
  </body>
   <script type="text/javascript">
      $(document).ready(function() {
    		//show errorMsgs
    		<c:if test="${not empty errorMsgs}">
    		<c:forEach var="message" items="${errorMsgs}">
    		toastr.error("${message}");
    		</c:forEach>
    		</c:if>
	    	    jQuery.i18n.properties({// 加载properties文件  
	    	    name:'application', // properties文件名称  
	    	    path:'resource/settings/', // properties文件路径  
	    	    mode:'map', // 用 Map 的方式使用资源文件中的值  
	    	    callback: function() {// 加载成功后设置显示内容  
	    	        var takeinfo=$.i18n.prop("app.takeinfo");//其中isp_index为properties文件中需要查找到的数据的key值 
	    	        if(takeinfo==='M01'){
	    	        	$('#shoppingformM01').show();
	    	        	$('#shoppingformM02').hide();
	    	        }else if(takeinfo==='M02'){
	    	        	$('#shoppingformM02').show();
	    	        	$('#shoppingformM01').hide();
	    	        }
	    	    }  
	    	    });  
    	    	
    	});
    
	</script>
<script type="text/javascript">
   var d = new Date();
        $( "#taketime2" ).timeDropper(
                {format:'HH:mm',
                	setCurrentTime:false,
                    }
            );

        $('#taketime2').css("background-color","#fff");
        let orderStatusKey=$("#orderstatus option:selected").attr("data-orderStatusKey");
        $("#orderStatusKey").val(orderStatusKey);
        
        let value=$("#store option:selected").attr("data-value");
        $("#value").val(value);
        let storename=$("#store option:selected").attr("data-storename");
        $("#storename").val(storename);
        
	      $(document).ready(function() {
	    		//show errorMsgs
	    		<c:if test="${not empty errorMsgs}">
	    		<c:forEach var="message" items="${errorMsgs}">
	    		toastr.error("${message}");
	    		</c:forEach>
	    		</c:if>
	    	});
	    	
    	  if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
    		  var d =moment().format('YYYY-MM-DD');
  	          $('#taketime1').val(d);
	    	  $('#taketime1').datepicker('destroy');
	    	  $('#taketime1').prop('type', 'date');
	    	  //(請注意日期格式要調整成跟 JQ UI 的 datepicker 日期格式一樣)<br>
    	  }else{
    	        var d =moment().format('YYYY-MM-DD');
    	        $('#taketime1').val(d);
    	        $(function () {
    	        	var isValidDate = function(value, format) {
	        			format = format || false;
	        			// lets parse the date to the best of our knowledge
	        			if (format) {
	        				value = parseDate(value);
	        			}
	        			var timestamp = Date.parse(value);
	        			return isNaN(timestamp) == false;
	        	   }
	        	   var parseDate = function(value) {
	        			var m = value.match(/^(\d{1,2})(\/|-)?(\d{1,2})(\/|-)?(\d{4})$/);
	        			if (m)
	        				value = m[5] + '-' + ("00" + m[3]).slice(-2) + '-' + ("00" + m[1]).slice(-2);
	        			return value;
	        	   }
    	        	var bindDatePicker= function(){
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
    	                allowInputToggle: true,
//    	                 defaultViewDate: { year: d.getFullYear(), month: d.getMonth(), day: d.getDate() }
    		            }).find('input:first').on("blur",function () {
    		        				// check if the date is correct. We can accept dd-mm-yyyy and yyyy-mm-dd.
    		        				// update the format if it's yyyy-mm-dd
    		        				var date = parseDate($(this).val());
    		        				if (! isValidDate(date)) {
    		        					//create date based on momentjs (we have that)
    		        					date = moment().format('YYYY-MM-DD');
    		        				}
    		        				$(this).val(date);
    		        			});
    		        	}
    	        	   bindDatePicker();
    	        	 });
	  
    	  }

	</script>
</html>
