<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@ page import="com.welljoint.entity.*"%>
<%@ page import="java.text.*"%>

    <nav class="navbar navbar-expand-sm navbar-dark bg-dark" id="navbar">
      <div class="container">
      <c:if test="${not empty shoppingList}">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/frontstage/productEShop.jsp" 
       data-toggle="modal"data-target="#restart">
       <img src="<%=request.getContextPath()%>/img/logo/logo3.png" width="30" height="30" class="d-inline-block align-top" alt="">
       &nbsp;<spring:message code="language.shoptitle"/></a>
      </c:if>
      <c:if test="${empty shoppingList}">
       <a class="navbar-brand" href="<%=request.getContextPath()%>/frontstage/productEShop.jsp" >
   		<img src="<%=request.getContextPath()%>/img/logo/logo3.png" width="30" height="30" class="d-inline-block align-top" alt="">
      &nbsp;<spring:message code="language.shoptitle"/></a>
      
      </c:if> 
        <!--  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>-->
        <ul class="navbar-nav ml-auto  mt-lg-0">
        
            <li class="">
              <a class="nav-link" href="<%=request.getContextPath()%>/AboutStore"><i class="fa fa-home"></i>&nbsp;&nbsp;<spring:message code="language.header.aboutstore"/></a>
            </li>
            <li class="">
              <a class="nav-link" href="<%=request.getContextPath()%>/frontstage/showMeal.jsp"><i class=" fas fa-clipboard"></i>&nbsp; <spring:message code="language.header.orderrecord"/></a>
            </li>
            <li class="">
              <a class="nav-link" href="<%=request.getContextPath()%>/frontstage/Cart.jsp">
              <i class="fa fa-shopping-cart"></i><span class="sr-only">(current)</span>&nbsp;<spring:message code="language.header.cart"/><c:if test="${not empty shoppingList}"> <span class=" badge badge-pill badge-danger">${fn:length(shoppingList)}</span> </c:if>
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
