package com.welljoint.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/productEShop")
public class ProductController {
	
	@RequestMapping(method = RequestMethod.GET)
    public String listOneproduct(ModelMap model,@RequestParam(required = true) String id){
		model.addAttribute("productid",id);
		return "/listOneProduct"; 
    }
}
