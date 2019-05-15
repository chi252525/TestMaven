package com.web.listener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.welljoint.entity.AttributeGroupVO;
import com.welljoint.entity.AttributeSingleVO;
import com.welljoint.entity.BannerVO;
import com.welljoint.entity.DiscountVO;
import com.welljoint.entity.ProductVO;
import com.welljoint.service.AttributeGroupService;
import com.welljoint.service.AttributeSingleService;
import com.welljoint.service.BannerService;
import com.welljoint.service.DiscountService;
import com.welljoint.service.ProductService;

@WebListener
public class CustomListener implements ServletContextListener {
	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomListener.class);
	// 星期陣列
	private static final String[] week_array = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday","Saturday" };
	private ProductService productSvc;
	private AttributeGroupService attrgSvc;
	private AttributeSingleService attrsSvc;
	private DiscountService disSvc;
	private BannerService bSvc;
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		Enumeration<String> enumeration = servletContext.getInitParameterNames();
		while (enumeration.hasMoreElements()) {
			String key = enumeration.nextElement();
			String value = servletContext.getInitParameter(key);
			System.out.println("键：{" + key + "},值：{" + value + "}");
			LOGGER.info("键：{},值：{}", key, value);
		}
		String contextPath = servletContext.getContextPath();
		servletContext.setAttribute("contextPath", contextPath);
		String rootPath = servletContext.getRealPath("/");
		System.out.println("contextPath:{" + contextPath + "},rootPath:{" + rootPath + "}");
		LOGGER.info("contextPath:{},rootPath:{}", contextPath, rootPath);
		// =====================撈資料庫中產品資料=====================
		getAllproducttoJSON(servletContext);
		// =====================撈資料庫中Banner資料=====================
		List<BannerVO> blistshow=getAllbannertoList(servletContext);
		servletContext.setAttribute("blistshow", blistshow);
		// =====================撈資料庫中產品類別資料=====================
		getAllproductClasstoList(servletContext);
		SimpleDateFormat time_format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Date start_time = new Date();
		System.out.println("開啟伺服器時間：" + time_format.format(start_time) + ";排程器時間:" + new Date().getTime());
		LOGGER.info("開啟伺服器時間：" + time_format.format(start_time) + ";排程器時間:" + new Date().getTime());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("CustomListener contextDestroyed");
		LOGGER.info("CustomListener contextDestroyed");
	}
	public List<BannerVO> getAllbannertoList(ServletContext servletContext) {
		bSvc = applicationContext.getBean(BannerService.class);
		List<BannerVO> blistshow=null;
		Calendar now= Calendar.getInstance();
		Integer today= now.get(Calendar.DAY_OF_WEEK)-1;
		if(bSvc.getAll()!=null){
			List<BannerVO> blist=bSvc.getAll();
			blistshow=new ArrayList<BannerVO>();
			for(BannerVO bvo:blist){
				String banner_s=bvo.getWeek();
				String[] banner_arr=banner_s.split(",");
				for(int j=0;j<banner_arr.length;j++){
					if(week_array[today].equals(banner_arr[j])){
						blistshow.add(bvo);
					}
				}
			}
		}
		return blistshow;
	}
	public void getAllproductClasstoList(ServletContext servletContext){
		productSvc = applicationContext.getBean(ProductService.class);
		List<String> productClassKeylist=productSvc.getProductKeys();
		JSONObject jobj = new JSONObject();
		JSONArray arrayAll = new JSONArray();
		
		try {
			JSONObject jobj_forall = new JSONObject();
			jobj_forall.putOpt("ProductClass", "全部商品");
			jobj_forall.putOpt("ProductClassKey", "");
			jobj_forall.putOpt("size", productSvc.getAll().size());
			arrayAll.put(jobj_forall);
		for(String productClassKey :productClassKeylist) {
			JSONObject onejobj = new JSONObject();
			List<ProductVO> plistbyClassKey=productSvc.findbyProductClassKey(productClassKey);
			for(ProductVO pVO:plistbyClassKey) {
				onejobj.putOpt("ProductClass", pVO.getProductClass());
			}
			onejobj.putOpt("size",plistbyClassKey.size());
			onejobj.putOpt("ProductClassKey", productClassKey);
			arrayAll.put(onejobj);
		}
		
		jobj.put("eshopbar", arrayAll);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("eshopbar="+jobj.toString());
		String FilePath = servletContext.getRealPath("/") + "/resource/json/eshopbar.json";
		BufferedWriter fw = null;
		try {
			File file = new File(FilePath);
			fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8")); // 指點編碼格式，以免讀取時中文字符異常
			fw.append(jobj.toString());
			fw.flush(); // 全部寫入緩存中的內容
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public void getAllproducttoJSON(ServletContext servletContext) {
		productSvc = applicationContext.getBean(ProductService.class);
		attrgSvc = applicationContext.getBean(AttributeGroupService.class);
		attrsSvc = applicationContext.getBean(AttributeSingleService.class);
		JSONObject onejobj = new JSONObject();
		JSONArray arrayAll = new JSONArray();
		List<String> productKeyslist = productSvc.getProductKeys();
		try {
			for (String productClasskey : productKeyslist) {
				List<ProductVO> plist = productSvc.findbyProductClassKey(productClasskey);
				JSONObject classkeyjobj = new JSONObject();
				classkeyjobj.put("productClasskey", productClasskey);

				JSONArray arraybyclasskey = new JSONArray();
				for (ProductVO apVO : plist) {
					JSONObject oneproductjobj = new JSONObject();
					classkeyjobj.put("productClass", apVO.getProductClass());
					oneproductjobj.put("productid", apVO.getId());
					oneproductjobj.put("sequence", apVO.getSequence());
					oneproductjobj.put("productionName", apVO.getProductionName());
					oneproductjobj.putOpt("subordinate_Name", apVO.getSubordinate_Name());
					oneproductjobj.put("description", apVO.getDescription());
					// =====================判斷價格是否取整數=====================
					oneproductjobj.put("prices", apVO.getPrices());
					oneproductjobj.put("discountPrice", apVO.getDiscountPrice());
					oneproductjobj.put("productImg", apVO.getProductImg());
					oneproductjobj.put("productImg1", apVO.getProductImg1());
					oneproductjobj.put("productExist", apVO.getProductExist());
					oneproductjobj.put("productAmount", apVO.getProductAmount());
					oneproductjobj.put("week", apVO.getWeek());
					oneproductjobj.put("calories", apVO.getCalories());
					oneproductjobj.put("timeInterval", apVO.getTimeInterval());
					// 取得現在時間
					SimpleDateFormat dayformatter = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm:ss");
					java.util.Date current = new java.util.Date();
					String currentday_f = dayformatter.format(current);
					String currenttime_f = timeformatter.format(current);
					// =====================判斷是否為顯示天=====================
					Boolean bool_isShowday = isShowday(apVO.getWeek());
					oneproductjobj.put("isShowday", bool_isShowday);
					// =====================判斷是否為顯示時間=====================
					Boolean bool_isShowtime = isShowtime(apVO.getTimeInterval(), currenttime_f);
					oneproductjobj.put("isShowtime", bool_isShowtime);
					// =====================判斷是否為優惠價=====================
					Boolean bool_isDiscountprice = isDiscountprice(apVO.getId(), currentday_f, currenttime_f);
					oneproductjobj.put("isDiscountprice", bool_isDiscountprice);
					// =====================屬性處理===========================
					JSONArray choice_Item3jarr = new JSONArray();
					if (apVO.getChoice_Item3() != null) {
						String str_choice_Item3_arr = apVO.getChoice_Item3();
						String[] choice_Item3_arr = str_choice_Item3_arr.split(",");
						for (String attrgid : choice_Item3_arr) {
							JSONObject attrbuitejobj = new JSONObject();
							AttributeGroupVO agVO = attrgSvc.getOneAttibuteGroup(attrgid);
							attrbuitejobj.put("description", agVO.getDescription());
							attrbuitejobj.put("multiple_choice", agVO.getMultiple_choice());
							attrbuitejobj.put("sequence", agVO.getSequence());
							attrbuitejobj.put("attributeid", agVO.getId());
							if (agVO.getAttributesList() != null) {
								String str_attributesList_arr = agVO.getAttributesList();
								String[] attributesList_arr = str_attributesList_arr.split(",");
								JSONArray attrs_jarr = new JSONArray();
								for (String attrsid : attributesList_arr) {
									AttributeSingleVO asVO = attrsSvc.getOneAttributeSingle(attrsid);
									JSONObject attrsjobj = new JSONObject();
									attrsjobj.put("attributesName", asVO.getAttributesName());
									attrsjobj.put("id", asVO.getId());
									attrsjobj.put("price", asVO.getPrice());
									attrsjobj.put("sequence", asVO.getSequence());
									attrs_jarr.put(attrsjobj);
								}
							attrbuitejobj.put("attribute", attrs_jarr);
							choice_Item3jarr.put(attrbuitejobj);
							}
							
						}
					}
					oneproductjobj.put("choice_Item3", choice_Item3jarr);
					arraybyclasskey.put(oneproductjobj);
				}
				classkeyjobj.put("Data", arraybyclasskey);
				arrayAll.put(classkeyjobj);
			}

			onejobj.put("Allproduct", arrayAll);
			System.out.println(onejobj.toString());
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		String FilePath = servletContext.getRealPath("/") + "/resource/json/product.json";
		BufferedWriter fw = null;
		try {
			File file = new File(FilePath);
			fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8")); // 指點編碼格式，以免讀取時中文字符異常
			fw.append(onejobj.toString());
			fw.flush(); // 全部寫入緩存中的內容
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Boolean isShowday(String week) {
		Calendar cal = Calendar.getInstance();
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		String todat_w = week_array[w];// 今日星期
		String[] week_arr = week.split(",");
		for (String one_week_s : week_arr) {
			if (todat_w.equals(one_week_s.trim())) {
				return true;// 是顯示日期
			}
		}
		return false;

	};

	public Boolean isShowtime(String timeInterval, String currenttime_f) {
		String[] ti_arr = timeInterval.split("-");
		String starttime = ti_arr[0].trim();
		String endtime = ti_arr[1].trim();
		try {
			boolean now_start_compare = comparetimeonly(starttime, currenttime_f);
			boolean now_end_compare = comparetimeonly(currenttime_f, endtime);
			if (now_start_compare && now_end_compare) {
				return true;
			}
		} catch (ParseException e) {
			LOGGER.error("判斷是否為顯示時間失敗: " + e.getMessage().replaceAll("\r|\n", " "));
			e.printStackTrace();
		}
		return false;
	}

	public Boolean isDiscountprice(Integer productid, String currentday_f, String currenttime_f) {
		disSvc = applicationContext.getBean(DiscountService.class);
		if (disSvc.findByProductId(productid) != null) {

			List<DiscountVO> dlist = disSvc.findByProductId(productid);
			for (DiscountVO dVO : dlist) {
				if (dVO.getStatus()==true) {
//					取優惠區間時間
					String TimeInterval = dVO.getTimeInterval().trim();
					String[] ti_arr = TimeInterval.split("-");
					String starttime = ti_arr[0].trim();
					String endtime = ti_arr[1].trim();
//					取優惠區間日期
					String Validity_period = dVO.getValidity_period().trim();
					String[] vp_arr = Validity_period.split(" - ");
					String startday = vp_arr[0].trim();
					String endday = vp_arr[1].trim();
					try {
						boolean now_start_compare = compare(startday + " " + starttime,
								currentday_f + " " + currenttime_f);
						boolean now_end_compare = compare(currentday_f + " " + currenttime_f, endday + " " + endtime);
						if (now_start_compare && now_end_compare) {
							Calendar cal = Calendar.getInstance();
							int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
							String todat_w = week_array[w];
							String week = dVO.getWeek();
							String[] week_arr = week.split(",");
							for (String one_week_s : week_arr) {
								if (todat_w.equals(one_week_s.trim())) {
									return true;// 優惠價
								}
							}
						}
					} catch (ParseException e) {
						if (e.getMessage() != null) {
							LOGGER.error("優惠價取得失敗: " + e.getMessage().replaceAll("\r|\n", " "));
							e.printStackTrace();
						}
					}
				}
			}
//			
		}
		return false;
	}

	public boolean compare(String time1, String time2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		java.util.Date a = sdf.parse(time1);
		java.util.Date b = sdf.parse(time2);
		// Date类的一个方法，如果a早于b返回true，否则返回false
		if (a.getTime() - b.getTime() < 0)
			return true;
		else
			return false;
	}

	public boolean comparetimeonly(String time1, String time2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		java.util.Date a = sdf.parse(time1);
		java.util.Date b = sdf.parse(time2);
		// Date类的一个方法，如果a早于b返回true，否则返回false
		if (a.getTime() - b.getTime() < 0)
			return true;
		else
			return false;
	}
}
