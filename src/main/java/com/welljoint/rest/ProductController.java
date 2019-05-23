package com.welljoint.rest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.welljoint.entity.ProductVO;
import com.welljoint.service.ProductService;
@Scope("prototype")
@Controller
@RequestMapping("/productEShop")
public class ProductController {
	@Autowired
	private ProductService pSvc;
	@RequestMapping(method = RequestMethod.GET)
    public String listOneproduct(ModelMap model,@RequestParam(required = true) String id){
		model.addAttribute("productid",id);
		return "listOneProduct"; 
    }
	
	
	@RequestMapping(value="/getproductAmount",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getproductAmount(@RequestBody String jsonString){
		JSONObject returnjobj= new JSONObject();
		try {
			JSONObject jsonObj = new JSONObject(jsonString);
			String productid=(String)jsonObj.get("productid");
			ProductVO pVO=pSvc.findbyId(Integer.parseInt(productid));
			Integer productAmount=pVO.getProductAmount();
			returnjobj.put("productAmount", productAmount);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnjobj; 
    }
	
}
