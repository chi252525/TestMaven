package com.welljoint.rest;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.welljoint.entity.OrdersPhoneVO;



@Controller 
@SessionAttributes(value="shoppingcart")
@RequestMapping("/Order")
public class OrderController {
	
	@RequestMapping(value="/productEShop" ,method = RequestMethod.POST)
    public String getInit(HttpSession session,@RequestParam("orderstatus") String orderstatus ,@RequestParam("internalNumber") String internalNumber){ 
		System.out.println("orderstatus="+orderstatus+"internalNumber="+internalNumber);
		OrdersPhoneVO opVO= new OrdersPhoneVO();
		opVO.setOrderStatus(orderstatus);
		opVO.setInternalNumber(internalNumber);
		session.setAttribute("shoppingorder", opVO);
		return "redirect:/frontstage/productEShop.jsp"; 
    }
	
	@RequestMapping(value="/addOrder" ,method = RequestMethod.POST)
    public String addOrder(HttpSession session){ 
		
		return "SuccessOrder"; 
    }
	
	
}
