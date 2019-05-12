package com.welljoint.rest;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.welljoint.entity.OrderPhoneDetailVO;
import com.welljoint.entity.ProductVO;
@Controller
@RequestMapping("/Cart")
public class ShoppingController {
	@RequestMapping(value="/add",method = RequestMethod.POST)
	@ResponseBody  
	public String addToCart(HttpServletRequest req,@ModelAttribute OrderPhoneDetailVO opdVO,ProductVO pVO,HttpSession session,Model model) {
		System.out.println("addToCart in");
		System.out.println("Qty="+opdVO.getQty());
		System.out.println("Prices="+pVO.getPrices());
		System.out.println("id="+pVO.getId());
		String attribute_size=req.getParameter("attribute_size");
		for(int i=0;i<Integer.parseInt(attribute_size);i++) {
			String attr=req.getParameter("attr"+i);
			System.out.println("attr="+attr);
		}
		
//		Map<String, OrderPhoneDetailVO> cart = (Map<String, OrderPhoneDetailVO>)session.getAttribute("cart");
//		if(cart == null) {
//			cart = new HashMap<>();//创建购物车Map集合
//			cart.put(good, 1);//向购物车map集合中添加商品
//			session.setAttribute("cart", cart);//向session中添加购物车
//		}else {
//			Integer n = cart.get(good);
//			if(n == null) {
//				good = goodService.getById(good.getId());//从数据库中查询商品信息
//				cart.put(good, 1);				
//			}else {
//				cart.put(good, 1+n);
//			}
//		}
//		model.addAttribute("sum", sum);
		return "cart";
	}

}
