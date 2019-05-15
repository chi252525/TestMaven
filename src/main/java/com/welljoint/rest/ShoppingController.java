package com.welljoint.rest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.welljoint.entity.ProductVO;
import com.welljoint.service.MyCart;
@Controller
@RequestMapping("/Cart")
public class ShoppingController {
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String addToCart(HttpServletRequest req,@ModelAttribute ProductVO pVO) {
		//==========接收屬性並處理字串=============
		String shoppingNote = "";
		Enumeration en=req.getParameterNames();
		List<String> note_list = new ArrayList<String>();
		while(en.hasMoreElements())
		{
			Object objOri=en.nextElement();
			String param=(String)objOri;
			String value=req.getParameter(param);
			String isattr=param.substring(0,param.length()-1);
			if(isattr.equals("attr")) {
				note_list.add(value);
				shoppingNote=join(note_list, ",");
//				System.out.println("shoppingNote="+shoppingNote);
			}
//			System.out.println("Parameter Name is '"+param+"' and Parameter Value is '"+value+"'");
		}	
		//接收想購買的ID
		String id=req.getParameter("id");
		System.out.println("id="+id);
		//==========取出購物車==========
		MyCart myCart=null;
		if(req.getSession().getAttribute("myCart")!=null) {
			myCart=(MyCart)req.getSession().getAttribute("myCart");
		}else {
			myCart=new MyCart();
			req.getSession().setAttribute("myCart",myCart);
		}
		//==========相當於把商品加到購物車==========
		pVO.setShoppingNote(shoppingNote);
		myCart.addProduct(id,pVO);	
//		//把要顯示的List放入session。
		req.getSession().setAttribute("shoppingList", myCart.showMyCart());
		req.getSession().setAttribute("totalPrice", myCart.getTotalPrice());
		req.getSession().setAttribute("totalQty", myCart.getTotalQty());
		return "redirect:/frontstage/productEShop.jsp"; 
	}
	
	@RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
	public String deletefromCart(HttpServletRequest req,@PathVariable("id") String id) {
		MyCart myCart=(MyCart)req.getSession().getAttribute("myCart");
		myCart.delProduct(Integer.parseInt(id));
		req.getSession().setAttribute("shoppingList", myCart.showMyCart());
		req.getSession().setAttribute("totalPrice", myCart.getTotalPrice());
		req.getSession().setAttribute("totalQty", myCart.getTotalQty());
		return "redirect:/frontstage/Cart.jsp"; 
	}
	
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public String updatefromCart(HttpServletRequest req,@RequestParam("hashid") String hashid ,@RequestParam("quantity") String quantity ) {
		MyCart myCart=(MyCart)req.getSession().getAttribute("myCart");
		myCart.updateProduct(Integer.parseInt(hashid), quantity);
		req.getSession().setAttribute("shoppingList", myCart.showMyCart());
		req.getSession().setAttribute("totalPrice", myCart.getTotalPrice());
		req.getSession().setAttribute("totalQty", myCart.getTotalQty());
		return "redirect:/frontstage/Cart.jsp"; 
	}
	
	@RequestMapping(value="/Checkout",method = RequestMethod.GET)
	public String checkoutfromCart(HttpServletRequest req) {
		MyCart myCart=(MyCart)req.getSession().getAttribute("myCart");
		req.getSession().setAttribute("shoppingList", myCart.showMyCart());
		req.getSession().setAttribute("totalPrice", myCart.getTotalPrice());
		req.getSession().setAttribute("totalQty", myCart.getTotalQty());
		return "Checkout"; 
	}
	static public String join(List<String> list, String conjunction) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String item : list) {
			if (first)
				first = false;
			else
				sb.append(conjunction);
			sb.append(item);
		}
		return sb.toString();
	}
}
