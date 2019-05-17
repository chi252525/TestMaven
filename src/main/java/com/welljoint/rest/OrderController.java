package com.welljoint.rest;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.util.Configproperties;
import com.welljoint.entity.MealNum_pofferVO;
import com.welljoint.entity.OrderPhoneDetailVO;
import com.welljoint.entity.OrdersPhoneVO;
import com.welljoint.entity.ProductVO;
import com.welljoint.entity.StoreInformationVO;
import com.welljoint.service.MealNum_pofferService;
import com.welljoint.service.MyCart;
import com.welljoint.service.OrdersPhoneService;
import com.welljoint.service.ProductService;



@Controller 
@RequestMapping("/")
public class OrderController {
	@Autowired
	private Configproperties Configproperties; 
	@Autowired
	private MealNum_pofferService mpSvc;
	@Autowired
	private OrdersPhoneService opSvc;
	@Autowired
	private ProductService pSvc;
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	@RequestMapping(value="/Order/productEShop" ,method = RequestMethod.POST)
    public String getInit(HttpSession session,@ModelAttribute  OrdersPhoneVO opVO){
		
		session.setAttribute("shoppingorder", opVO);
			return "redirect:/frontstage/productEShop.jsp";
    }
	
	@RequestMapping(value="/Order/addOrder" ,method = RequestMethod.POST)
    public String addOrder(HttpServletRequest req,@ModelAttribute  OrdersPhoneVO opVO){ 
		
		OrdersPhoneVO initopVO=(OrdersPhoneVO)req.getSession().getAttribute("shoppingorder");
		opVO.setOrderStatus(initopVO.getOrderStatus());
		opVO.setInternalNumber(initopVO.getInternalNumber());
		opVO.setOrderDate(new Timestamp(System.currentTimeMillis()));//訂購時間取現在
		opVO.setStore(initopVO.getStore());
		opVO.setName(initopVO.getName());
		opVO.setOrderBy("mobile");
		opVO.setProcessStatus(1);//1成立;2,接受訂單;3,出單;4,完成,;5取消
		opVO.setPaymentStatus(false);//未付款
		opVO.setStatus(false);//開立發票狀態 false未開
		opVO.setTax(Configproperties.TAXTYPE);//稅額
		opVO.setCancel("未取消");
		Double totalPrice=(Double)req.getSession().getAttribute("totalPrice");
		opVO.setInvoicesTotal(totalPrice);
		StoreInformationVO storeVO=(StoreInformationVO)req.getServletContext().getAttribute("activeStoreVO");
		opVO.setName(storeVO.getName());
		opVO.setStore(storeVO.getStore());
		opVO.setValue(storeVO.getValue());
		
		MyCart cart=(MyCart)req.getSession().getAttribute("myCart");
		//================================將購物車內容放置detailVO================================
		opVO=addCarttodetail(cart,opVO);
		//================================領餐單號================================
		Integer mealNum=getMealNum();
		opVO.setMealNum(mealNum);
//		==================================產生Qrcode================================
		String uuid=getQrcode(req);
		opVO.setUuid(uuid);
		//============== 產生訂單號碼=======================
		int n = (int)(Math.random()*(100))+1;
        String random=String.format("%04d", n);;
        String ordersNum="em"+System.currentTimeMillis()+random;
        opVO.setOrdersNum(ordersNum);
        opSvc.insert(opVO);
        //===========================清空購物車================================
        req.setAttribute("successOrder", opVO);
        req.setAttribute("successOrderdetil",cart.showMyCart());
        req.setAttribute("successtotalPrice",totalPrice);
        Integer totalQty=(Integer)req.getSession().getAttribute("totalQty");
        req.setAttribute("successtotalQty",totalQty);
       
        req.getSession().removeAttribute("myCart");
        req.getSession().removeAttribute("totalPrice");
        req.getSession().removeAttribute("totalQty");
        req.getSession().removeAttribute("shoppingList");
        req.getSession().removeAttribute("shoppingorder");
		return "SuccessOrder"; 
    }
	
	@RequestMapping(value="/Order/updateProccess" ,method = RequestMethod.POST ,headers = {"content-type=application/json"})
	@ResponseBody
    public JSONObject updateProcess(@RequestBody String jsonString){
		System.out.println("updateProcess in");
		System.out.println("json="+jsonString);
		JSONObject resjobj=null;
		try {
			resjobj= new JSONObject(jsonString);
			JSONObject jobj= new JSONObject(jsonString);
			String ordersNum=jobj.getString("ordersNum");
			OrdersPhoneVO opVO=opSvc.findByPrimaryKey(ordersNum);
			resjobj.put("processStatus", opVO.getProcessStatus());
			resjobj.put("paymentStatus", opVO.getPaymentStatus());
			resjobj.put("takeTime", opVO.getTakeTime());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resjobj;
	}
	
	public String getQrcode(HttpServletRequest req) {
		String s = UUID.randomUUID().toString();
		String uuid = s.substring(0, 8) + s.substring(9, 13);
		// 設定 QRCode 圖片要顯示的文字內容
//		String address = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
//		+ req.getContextPath() + "/ordersphone/OrderAPIService/getorder?uuid=;
		String address =uuid;
//		System.out.println("address="+address);
		// 用不會重複的 UUID 當作圖片檔名
		String TempFileName = uuid + ".jpg";
		// 指定圖片存檔路徑， request.getServletContext().getRealPath 是伺服器端網站架設的最上層路徑
		String TempFilePath = req.getServletContext().getRealPath("/img/QRcode/");
//		System.out.println("TempFilePath="+TempFilePath);
		LOGGER.info("==============訂單Qrcode TempFilePath=" + TempFilePath+"======================");
		// 在 JSP 伺服器端產生一個長寬都是140的圖檔, genQRCode 函式內容在本文後面
		String TempResult = genQRCode(140, 140, address, TempFilePath + TempFileName);

		if (TempResult.equals("")) {// 成功
			req.getSession().setAttribute("TempFileName", TempFileName);
			LOGGER.info("===================uuid" + uuid + "訂單Qrcode created====================");
			System.out.println("===================uuid" + uuid + "訂單Qrcode created====================");
		} else {// 失敗
			System.out.println("====================TempResult=" + TempResult + "Qrcode產生失敗=================");
			LOGGER.error("====================TempResult=" + TempResult + "Qrcode產生失敗=================");
			// 顯示錯誤訊息
		}
		return uuid;
	}
	public OrdersPhoneVO addCarttodetail(MyCart cart,OrdersPhoneVO opVO) {
		Set<OrderPhoneDetailVO> opdset = new HashSet<OrderPhoneDetailVO>();
		List<ProductVO> plist=(List<ProductVO>)cart.showMyCart();
		for(ProductVO pVO :plist) {
			OrderPhoneDetailVO opdVO= new OrderPhoneDetailVO();
			opdVO.setProductclass(pVO.getProductClass());
			opdVO.setProductclasskey(pVO.getProductClasskey());
			opdVO.setProductname(pVO.getProductionName());
			opdVO.setQty(pVO.getShoppingQty());
			opdVO.setPrice(pVO.getShoppingPrice());
			opdVO.setTotalprice(pVO.getShoppingSubtotalprice());
			opdVO.setNote(pVO.getShoppingNote());
			opdVO.setTax_type(pVO.getTax_Type());//依產品設定的稅種類
			opdVO.setTax(pVO.getShoppingPrice()*pVO.getTax_Rate());
			opdVO.setOpVO(opVO);
			//=====================更新庫存=================================
			ProductVO orginpVO=pSvc.findbyId(pVO.getId());
			if(orginpVO.getProductAmount()!=10000) {//庫存為無上限
				orginpVO.setProductAmount(orginpVO.getProductAmount()-pVO.getShoppingQty());
			}
			pSvc.update(orginpVO);
			opdset.add(opdVO);
		}
		opVO.setOpdVOs(opdset);
		return opVO;
	}
	
	
	
	public String genQRCode(int Img_Width, int Img_Height, String Text_In, String File_Path) {
		try {
			byte[] TempByte = Text_In.getBytes("Utf-8");// 將要製圖的內容轉成 byte 矩陣
			System.out.println("TempByte="+TempByte+" TempByte.length"+TempByte.length);
			java.awt.image.BufferedImage bi = new java.awt.image.BufferedImage(Img_Width, Img_Height,
					java.awt.image.BufferedImage.TYPE_INT_RGB);// 設定圖檔寬度予高度

			// 所下載元件之參數設定
			com.swetake.util.Qrcode TempQRCode = new com.swetake.util.Qrcode();
			TempQRCode.setQrcodeErrorCorrect('M');// 容錯率L M Q H
			TempQRCode.setQrcodeEncodeMode('B');// 字元模式,N A 或其它的A是英文,N是數字,其它是8 byte
			TempQRCode.setQrcodeVersion(7);// 可使用的字串長短跟所設定的QrcodeVersion有關,越大可設定的字越多, 0-40,0是自動

			// 建立一個 java 的 2D 畫布，並設定畫布的背景色、大小與前景色
			java.awt.Graphics2D g2D = (java.awt.Graphics2D) bi.getGraphics();
			g2D.setBackground(java.awt.Color.WHITE);// 背景是白色
			g2D.clearRect(0, 0, Img_Width, Img_Height);
			g2D.setColor(java.awt.Color.BLACK);// 用黑色來畫 QRCode

			// 在畫布中畫入 QRCode 圖
			if (TempByte.length > 0 && TempByte.length < 130) {
				boolean[][] TempBoolean = TempQRCode.calQrcode(TempByte);// QRCode 圖只有黑白兩色，呼叫元件建立 QRCode
																			// 圖點布林矩陣,false不塗色,True塗入黑色
				for (int XINDEX = 0; XINDEX < TempBoolean.length; XINDEX++) {
					for (int YINDEX = 0; YINDEX < TempBoolean.length; YINDEX++) {
						if (TempBoolean[YINDEX][XINDEX]) {// 元件有成功建構圖點者
							g2D.fillRect(YINDEX * 3 + 2, XINDEX * 3 + 2, 3, 3);// 就在畫布中塗一個方形點
						}
					}
				}
			} else {
				return "Content length too long";
			}
			g2D.dispose();
			// bi.flush();
			// 存檔
			java.io.File TempFile = new java.io.File(File_Path);
			javax.imageio.ImageIO.write(bi, "jpg", TempFile);
		} catch (Exception e) {
			return "" + e;// .printStackTrace();
		}
		return "";
	}
	
	public Integer getMealNum() {
		// 取MealNum -判斷是否為同一天
		MealNum_pofferVO mpVO = mpSvc.findByToday();// 取得現在時間
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today_s = dateFormat.format(mpVO.getDate());
		String orderdate_s = dateFormat.format(System.currentTimeMillis());//訂購時間取現在
		Integer mealnum_current = 0;
		Integer mealNum = 0;
		Timestamp changeStamp = new Timestamp(System.currentTimeMillis());
		MealNum_pofferVO onlyVO = mpSvc.findByToday();//取得唯一的一組資料
		String counter=onlyVO.getCounter();
		if (orderdate_s.equals(today_s)) {//Table中的Date如果是今天
			if (onlyVO.getMealnum_current() == onlyVO.getMealnum_initial()) {//如果已達上限
				mealnum_current = 1;//重設為1
				onlyVO.setMealnum_current(mealnum_current);
				onlyVO.setChangeStamp(changeStamp);
				mpSvc.update(onlyVO);
			} else {
				mealnum_current = onlyVO.getMealnum_current() + 1;
				onlyVO.setMealnum_current(mealnum_current);
				onlyVO.setChangeStamp(changeStamp);
				mpSvc.update(onlyVO);
			}
		} else {//Table中的Date如果不是今天
			onlyVO.setDate(new Timestamp(System.currentTimeMillis()));//date設為今天
			mealnum_current = 1;//重設為1
			onlyVO.setMealnum_current(mealnum_current);
			onlyVO.setChangeStamp(changeStamp);
			mpSvc.update(onlyVO);// 重設為今天 從0開始
		}
		System.out.println("mealnum_current="+mealnum_current);
		int length = getNumLenght(onlyVO.getMealnum_initial());
		String format = "%0" + length + "d";
		String mealNum_s = String.format(format, mealnum_current);
		String mealNum_f = counter + mealNum_s;
		mealNum = Integer.valueOf(mealNum_f).intValue();
		System.out.println("===========================訂單領餐單號mealNum=" + mealNum+"=========================");
		LOGGER.info("===========================訂單領餐單號mealNum=" + mealNum+"=========================");
		return mealNum;
	}
	private static int getNumLenght(long num) {
		if (num == 0) {
			return 1;
		}
		int lenght = 0;
		for (long temp = num; temp != 0; temp /= 10) {
			lenght++;
		}
		return lenght;
	}
}	
