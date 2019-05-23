package com.welljoint.rest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.util.Configproperties;
import com.welljoint.entity.ProductVO;
import com.welljoint.service.MyCart;
@Scope("prototype")
@Controller
@RequestMapping("/Cart")
public class ShoppingController {
	@Autowired
	private Configproperties Configproperties; //引用统一的参数配置类
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String addToCart(HttpServletRequest req,@ModelAttribute ProductVO pVO,RedirectAttributes attr) {
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
		attr.addAttribute("message", "成功加入購物車"); 
		return "redirect:/frontstage/productEShop.jsp"; 
	}
	
	@RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
	public String deletefromCart(HttpServletRequest req,@PathVariable("id") String id,RedirectAttributes attr) {
		MyCart myCart=(MyCart)req.getSession().getAttribute("myCart");
		myCart.delProduct(Integer.parseInt(id));
		req.getSession().setAttribute("shoppingList", myCart.showMyCart());
		req.getSession().setAttribute("totalPrice", myCart.getTotalPrice());
		req.getSession().setAttribute("totalQty", myCart.getTotalQty());
		attr.addAttribute("message", "成功刪除商品"); 
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
	public String checkoutfromCart(HttpServletRequest req,ModelMap model) {
		MyCart myCart=(MyCart)req.getSession().getAttribute("myCart");
		req.getSession().setAttribute("shoppingList", myCart.showMyCart());
		req.getSession().setAttribute("totalPrice", myCart.getTotalPrice());
		req.getSession().setAttribute("totalQty", myCart.getTotalQty());
		model.addAttribute("TAKEINFO",Configproperties.TAKEINFO);
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
