<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <!-- eshopbar -->
<div class="col-lg-3 col-sm-12 px-0 leftMeun">
      <div class="col-sm-12">
         <div class="list-group" id="listgroup">
          	  <a href="#" class="list-group-item list-group-item-action active">全部商品&nbsp;<span class="badge badge-pill badge-dark float-right"><b>${productSvc1.getAll().size()}</b></span></a>
	          <c:forEach var="pVO" items="${pSvc.getProductKeys()}" varStatus="u">
		          <a href="<%=request.getContextPath()%>/frontstage/productEShop.jsp#productClasskeyindiv${u.index}" class="list-group-item list-group-item-action ">${pVO.productClass}&nbsp;
		            <span class="badge badge-pill badge-dark float-right" id="productClasskey${u.index}"><b>${productSvc1.findbyProductClassKey(pVO.productClasskey).size()}</b></span>
		          </a>
	          </c:forEach>
          </div>
      </div>
</div>