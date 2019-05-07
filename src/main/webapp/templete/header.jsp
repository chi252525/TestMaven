<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.welljoint.entity.*"%>
<%@ page import="java.text.*"%>
<%
Vector<ProductCartVO> buylist = (Vector<ProductCartVO>) session.getAttribute("shoppingcart");
double invoicetotal_inloop=0.0;
String invoicetotal_inloop_s=new String();
int totalQty_inloop=0;
DecimalFormat dfm1 = new DecimalFormat("#");
if (buylist != null && (buylist.size() > 0)) {
	
	for (int i = 0; i < buylist.size(); i++) {
		ProductCartVO order = buylist.get(i);
		invoicetotal_inloop+=order.getSubtotalprice();
		totalQty_inloop+=order.getQty();
		invoicetotal_inloop_s=dfm1.format(invoicetotal_inloop);
	}
}

%>
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark" id="navbar">
      <div class="container">
      <c:if test="${not empty shoppingcart}">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp" 
       data-toggle="modal"data-target="#restart">
       <img src="<%=request.getContextPath()%>/img/logo/logo3.png" width="30" height="30" class="d-inline-block align-top" alt="">
       &nbsp;點餐系統</a>
      </c:if>
      <c:if test="${empty shoppingcart}">
       <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp" >
   		<img src="<%=request.getContextPath()%>/img/logo/logo3.png" width="30" height="30" class="d-inline-block align-top" alt="">
      &nbsp;點餐系統</a>
      </c:if> 
        <!--  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>-->
        <ul class="navbar-nav ml-auto  mt-lg-0">
            <li class="">
              <a class="nav-link" href="<%=request.getContextPath()%>/frontstage/AboutStore.jsp"><i class="fa fa-home"></i>&nbsp;&nbsp;關於我們</a>
            </li>
            <li class="">
              <a class="nav-link" href="<%=request.getContextPath()%>/frontstage/showmeal/showMeal.jsp"><i class=" fas fa-clipboard"></i>&nbsp; 訂購紀錄</a>
            </li>
            <li class="">
              <a class="nav-link" href="<%=request.getContextPath()%>/frontstage/Cart.jsp">
              <i class="fa fa-shopping-cart"></i><span class="sr-only">(current)</span>&nbsp;購物車<c:if test="${not empty shoppingcart}"> <span class=" badge badge-pill badge-danger"><%=buylist.size() %></span> </c:if>
              </a>
            </li>
          </ul>
       </div>
      
    </nav>

    <button type="button" id="BackTop" class="toTop-arrow" style="margin-bottom:50px"></button>
<script>
$(function(){
	$('#BackTop').click(function(){ 
		$('html,body').animate({scrollTop:0}, 333);
	});
	$(window).scroll(function() {
		if ( $(this).scrollTop() > 300 ){
			$('#BackTop').fadeIn(222);
		} else {
			$('#BackTop').stop().fadeOut(222);
		}
	}).scroll();
});
</script>
<%if (buylist != null && (buylist.size() > 0)) {%>
<!-- 清空購物車的Modal -->
	<div class="modal fade" id="restart" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">確定重新訂購?</h5>
				</div>
					<div class="modal-body">
						<p>您的購物車將會被清空</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
							<form action="<%=request.getContextPath() %>/index.jsp" method="get">
								<button type="submit" class="btn btn-primary">同意重新訂購</button>
							</form>
					</div>
			</div>
		</div>
	</div>
<%-- <c:if test="${not empty shoppingcart}"> --%>
<!-- <!-- fixed-bottom cart  -->
<!-- <nav class="navbar navbar-dark bg-dark  fixed-bottom" id="cart_navbar"> -->
<!--  <div class="navbar-header"> -->
<%--   <a class="navbar-brand" href="<%=request.getContextPath()%>/frontstage/Cart.jsp"> --%>
<!--   <i class="fa fa-shopping-cart text-danger" style="font-size:30px"></i> -->
<%--    <b>購物車:</b><%=buylist.size() %>總計:NT.<%=invoicetotal_inloop_s%> --%>
<!--   </a> -->
<!--   </div> -->
<!-- </nav> -->
<%-- </c:if> --%>
<%} %>