package com.web.apiService;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.welljoint.entity.*;
import com.welljoint.service.OrderPhoneDetailService;
import com.welljoint.service.OrdersPhoneService;
import com.welljoint.service.StoreInformationService;
import com.pos.app.DetailVO;
import com.pos.app.MasterVO;
import com.web.util.Configproperties;


@Controller 
@RequestMapping("/orderphone/OrderAPIService")
public class OrderAPIService {
	@Autowired
	 private Configproperties Configproperties; //引用统一的参数配置类
	@Autowired
	private OrdersPhoneService oSvc;
	@Autowired
	private StoreInformationService siSvc;
	@Autowired
	private OrderPhoneDetailService dSvc;
	
	@RequestMapping(value="/test" ,method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getisPaidorder2() {
		System.out.println("in");
		JSONObject returnObj = new JSONObject();
		String a="test";
		try {
			returnObj.put("aa", a);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnObj;
		
	}
	
	@RequestMapping(value="/isPaidorder" ,method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getisPaidorder(@RequestParam("uuid") String uuid ) {
		JSONObject returnObj = new JSONObject();
		JSONObject dataJsonObj = new JSONObject();
		String jsonStr=null;
		try {
			if (!(uuid == null || uuid.trim().length() == 0)) {
				if (oSvc.findByUuid(uuid) != null) {
					OrdersPhoneVO vo = oSvc.findByUuid(uuid);
					vo.setPaymentStatus(true);
					oSvc.update(vo);
					returnObj.put("blnStatus", true);
					returnObj.put("strMsg", "已更改狀態為已付款");
				} else {
					returnObj.put("blnStatus", false);
					returnObj.put("strMsg", "更改狀態失敗");
				}
			} else {
				returnObj.put("blnStatus", false);
				returnObj.put("strMsg", "更改狀態失敗");
			}
			dataJsonObj.put("returnMsg", returnObj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		jsonStr = returnObj.toString();
		return returnObj;
	}

	
	@RequestMapping(value="/getorder" ,method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getOrder(@RequestParam("uuid") String uuid) {
		MasterVO mVo = new MasterVO();
		JSONObject dataJsonObj = new JSONObject();
		JSONObject mVojsonObj = new JSONObject();
		JSONObject returnObj = new JSONObject();
		List<DetailVO> dList = new ArrayList<DetailVO>();
		String jsonStr = null;
		try {
			if (!(uuid == null || uuid.trim().length() == 0)) {
				OrdersPhoneVO vo = oSvc.findByUuid(uuid);
				if (vo != null) {
					Timestamp ordersDate = vo.getOrderDate();
					long orderDate_long =ordersDate.getTime();
					if(Configproperties.ORDERTIMEOUT!=null) {
						long settingtime=Integer.parseInt(Configproperties.ORDERTIMEOUT)*60*1000;
						long currenttime= System.currentTimeMillis();
						if(currenttime>(orderDate_long+settingtime)) {
							returnObj.put("blnStatus", false);
							returnObj.put("strMsg", "查詢失敗,訂單已過期");
						}else {
							String ordersNum = vo.getOrdersNum();
							mVo.setInvoicesNum(ordersNum);
							List<StoreInformationVO> siList = siSvc.getAll();
							String name = null;
							String store = null;
							String value = null;
							if (siList != null) {
								name = siList.get(0).getName(); // 公司
								store = siList.get(0).getStore(); // 店家
								value = siList.get(0).getValue(); // 統編
							}
							mVo.setName(name);
							mVo.setStore(store);
							String invoicePeriod = null;
							mVo.setInvoicePeriod(invoicePeriod);
							java.util.Date utilDate = new java.util.Date(vo.getOrderDate().getTime());
							DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String invoicesDate = dateFormat.format(utilDate);
							System.out.println("invoicesDate : " + invoicesDate);
							mVo.setInvoicesDate(invoicesDate);
							Double invoicesTotalDouble = vo.getInvoicesTotal();
							String invoicesTotal = String.valueOf(invoicesTotalDouble.doubleValue());
							mVo.setInvoicesTotal(invoicesTotal);
							mVo.setValue(value);
							String note = vo.getNote();
							mVo.setNote(note);
							String customerValue = vo.getCustomerValue();
							mVo.setCustomerValue(customerValue);
							String orderStatus = vo.getOrderStatus();
							mVo.setOrderStatus(orderStatus);
							String orderStatusKey = vo.getOrderStatusKey();
							mVo.setOrderStatusKey(orderStatusKey);
							Boolean statusBoolean = vo.getStatus();
							String status = String.valueOf(statusBoolean.booleanValue());
							mVo.setStatus(status);
							String toolCode = vo.getToolCode();
							mVo.setToolCode(toolCode);
							String loveCode = vo.getLoveCode();
							mVo.setLoveCode(loveCode);
							String tax = vo.getTax();
							mVo.setTax(tax);
							String payBy = vo.getPayBy();
							mVo.setPayBy(payBy);
							String cancel = ""; // 不可為null
							mVo.setCancel(cancel);
							String internalNumber = vo.getInternalNumber();
							mVo.setInternalNumber(internalNumber);
							String reprintNumber = vo.getReprintNumber();
							mVo.setReprintNumber(reprintNumber);
							java.util.Date takeTimeDate = new java.util.Date(vo.getTakeTime());
							String takeTime = dateFormat.format(takeTimeDate);
							mVo.setTakeTime(takeTime);
							String orderBy = vo.getOrderBy();
							mVo.setOrderBy(orderBy);
							String randomNum = vo.getRandomNum();
							mVo.setRandomNum(randomNum);
							Integer mOrderStatusInteger = 1; // 1.儲存 2.完成 3.取消
							String mOrderStatus = String.valueOf(mOrderStatusInteger.intValue());
							mVo.setMOrderStatus(mOrderStatus);
							String sell_no = null; // set null
							mVo.setSell_no(sell_no);
							String mealNum = String.valueOf(vo.getMealNum().intValue());
							mVo.setMealNum(mealNum);

							// handle null values for JSON object
							mVojsonObj.put("invoicesNum", vo.getOrdersNum() == null ? JSONObject.NULL : vo.getOrdersNum());
							mVojsonObj.put("name", name == null ? JSONObject.NULL : name);
							mVojsonObj.put("store", store == null ? JSONObject.NULL : store);
							mVojsonObj.put("invoicePeriod", invoicePeriod == null ? JSONObject.NULL : invoicePeriod);
							mVojsonObj.put("invoicesDate", invoicesDate == null ? JSONObject.NULL : invoicesDate);
							mVojsonObj.put("invoicesTotal", invoicesTotal == null ? JSONObject.NULL : invoicesTotal);
							mVojsonObj.put("value", value == null ? JSONObject.NULL : value);
							mVojsonObj.put("note", note == null ? JSONObject.NULL : note);
							mVojsonObj.put("customerValue", customerValue == null ? JSONObject.NULL : customerValue);
							mVojsonObj.put("orderStatus", orderStatus == null ? JSONObject.NULL : orderStatus);
							mVojsonObj.put("orderStatusKey", orderStatusKey == null ? JSONObject.NULL : orderStatusKey);
							mVojsonObj.put("status", status == null ? JSONObject.NULL : status);
							mVojsonObj.put("toolCode", toolCode == null ? JSONObject.NULL : toolCode);
							mVojsonObj.put("loveCode", loveCode == null ? JSONObject.NULL : loveCode);
							mVojsonObj.put("tax", tax == null ? JSONObject.NULL : tax);
							mVojsonObj.put("payBy", payBy == null ? JSONObject.NULL : payBy);
							mVojsonObj.put("cancel", cancel == null ? JSONObject.NULL : cancel);
							mVojsonObj.put("internalNumber", internalNumber == null ? JSONObject.NULL : internalNumber);
							mVojsonObj.put("reprintNumber", reprintNumber == null ? JSONObject.NULL : reprintNumber);
							mVojsonObj.put("takeTime", takeTime == null ? JSONObject.NULL : takeTime);
							mVojsonObj.put("orderBy", orderBy == null ? JSONObject.NULL : orderBy);
							mVojsonObj.put("randomNum", randomNum == null ? JSONObject.NULL : randomNum);
							mVojsonObj.put("mOrderStatus", mOrderStatus == null ? JSONObject.NULL : mOrderStatus);
							mVojsonObj.put("sellNo", sell_no == null ? JSONObject.NULL : sell_no);
							mVojsonObj.put("mealNum", mealNum == null ? JSONObject.NULL : mealNum);
							List<OrderPhoneDetailVO> opdList = dSvc.findByOrderNum(ordersNum);
							fillDetailVOToList(opdList, dList);
							returnObj.put("blnStatus", true);
							returnObj.put("strMsg", "查詢成功");
						}
					}
				} else {
					returnObj.put("blnStatus", false);
					returnObj.put("strMsg", "查詢失敗,訂單不存在或已刪除");
				}
			} else {
				returnObj.put("blnStatus", false);
				returnObj.put("strMsg", "查詢失敗,訂單不存在或已刪除");
			}
			
			JSONArray ja = new JSONArray(dList);
			dataJsonObj.put("returnMsg", returnObj);
			dataJsonObj.put("detail", ja);
			dataJsonObj.put("master", mVojsonObj);
			
			
			jsonStr = dataJsonObj.toString();
			System.out.println("jsonStr=" + jsonStr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataJsonObj;
	}

	protected void fillDetailVOToList(List<OrderPhoneDetailVO> opdList, List<DetailVO> dList) {
		for (OrderPhoneDetailVO opdVo : opdList) {
			DetailVO dVo = new DetailVO();
			Long idLong = System.currentTimeMillis() / 1000; // milliseconds to seconds
			String id = String.valueOf(idLong.longValue());
			dVo.setId(id);
			dVo.setInvoicesNum(opdVo.getOrdersnum()); // 訂單號碼
			dVo.setProductClass(opdVo.getProductclass());
			dVo.setProductClassKey(opdVo.getProductclasskey());
			dVo.setProductName(opdVo.getProductname());
			dVo.setQty(String.valueOf(opdVo.getQty().intValue()));
			dVo.setPrice(String.valueOf(opdVo.getPrice().intValue()));
			dVo.setTotalPrice(String.valueOf(opdVo.getTotalprice().doubleValue()));
			dVo.setNote(opdVo.getNote() == null ? "" : opdVo.getNote());
			dVo.setTax_Type(opdVo.getTax_type());
			// dVo.setTax(opdVo.getTax());
			dVo.setTax(String.valueOf(opdVo.getTax().doubleValue()));
			dList.add(dVo);
		}
	}
}
