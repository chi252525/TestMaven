package com.welljoint.rest;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.welljoint.entity.StoreInformationVO;
import com.welljoint.service.StoreInformationService_interface;

@Controller  
public class StoreInController {
	@Autowired
    StoreInformationService_interface storeSvc;  //注入service層
	
	@RequestMapping(path="/AboutStore",method = RequestMethod.GET)
    public String getStoreInformationVOs(ModelMap model){  //用來返回一個頁面
		List<StoreInformationVO> allStoreInVOs = storeSvc.getAll();
		model.addAttribute("allStoreInVOs",allStoreInVOs);
        return "AboutStore";  //返回指向login.jsp頁面
    }
}
