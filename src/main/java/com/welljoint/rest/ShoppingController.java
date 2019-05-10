package com.welljoint.rest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.welljoint.service.AttributeSingleService;
import com.welljoint.entity.AttributeSingleVO;
import com.welljoint.entity.ProductCartVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 8 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ShoppingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public ShoppingController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		Vector<ProductCartVO> buylist = (Vector<ProductCartVO>) session.getAttribute("shoppingcart");
		List<String> remindMsgs = new LinkedList<String>();
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("addsuccessMsgs", remindMsgs);
		String action = req.getParameter("action");
		// 還沒要結帳情況
		if (!action.equals("CHECKOUT") && (!("addCart").equals(action)) && (!("minusCart").equals(action))) {
			// 刪除購物車中的品項
			if (action.equals("DELETE")) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.remove(d);
				remindMsgs.add("刪除商品成功");
				
//					System.out.println("刪除後 目前購物車內容:");
//					if (buylist.size() != 0) {
//						for (ProductCartVO x : buylist) {
//							System.out.println(x.getId() + "有" + x.getQty() + "個");
//						}
//					} else {
//						System.out.println("沒東西 !");
//					}
				req.setAttribute("remindMsgs",remindMsgs);
				String url = "/frontstage/Cart.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
//				res.sendRedirect(req.getContextPath() + "/frontstage/Cart.jsp");
				return;
			}
			// 新增品項至購物車
			else if (action.equals("ADD")) {
				
				boolean match = false;
				// 取得後來新增的品項
				ProductCartVO aproduct_incart = getProductCartVO(req);
				// 品項為第一個的情況
				if (buylist == null) {
					buylist = new Vector<ProductCartVO>();
					buylist.add(aproduct_incart);
					remindMsgs.add("成功加入購物車");
				}
				// 品項已經在購物車情況
				else {
					for (int i = 0; i < buylist.size(); i++) {
						ProductCartVO product_incart = buylist.get(i);
						if (product_incart.getProductionName().equals(aproduct_incart.getProductionName())
								&& product_incart.getNote().equals(aproduct_incart.getNote())) {
							if(product_incart.getQty()+aproduct_incart.getQty()<(product_incart.getProductAmount()-product_incart.getQty())) {
								product_incart.setQty(product_incart.getQty() + aproduct_incart.getQty());
								product_incart.setSubtotalprice(product_incart.getSubtotalprice()+aproduct_incart.getSubtotalprice());
								match = true;
							}else {
								errorMsgs.add("超過可點數量!無法選購");
								match = false;
								req.setAttribute("errorMsgs",errorMsgs);
								req.setAttribute("remindMsgs",remindMsgs);
								session.setAttribute("shoppingcart", buylist);
								String url = "/frontstage/productEShop.jsp";
								RequestDispatcher rd = req.getRequestDispatcher(url);
								rd.forward(req, res);
								return;
							}
						}

					}
					if (!match) {
						buylist.add(aproduct_incart);
						remindMsgs.add("成功加入購物車");
					}

				}
			}
//				System.out.println("ShoppingServlet 目前購物車內容:");
//				if (buylist != null) {
//					for (ProductCartVO x : buylist) {
//						System.out.println(x.getProductionName() + "有" + x.getQty() + "個");
//					}
//					
//				}
//				else {
//					System.out.println("沒東西 !");
//				}

			req.setAttribute("remindMsgs",remindMsgs);
			session.setAttribute("shoppingcart", buylist);
			String url = "/frontstage/productEShop.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			return;
		}

		// 要結帳的情況
		else if (action.equals("CHECKOUT")) {
			double invoicetotal_inloop = 0;
			int totalQty_inloop = 0;
			// 取得現在時間
			SimpleDateFormat dayformatter = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat timeformatter = new SimpleDateFormat("HH:mm:ss");
			java.util.Date current = new java.util.Date();
			String currentday_f = dayformatter.format(current);
			String currenttime_f = timeformatter.format(current);
			logger.info("結帳:今天=" + currentday_f + "現在時間=" + currenttime_f);

			for (int i = 0; i < buylist.size(); i++) {
				ProductCartVO order = buylist.get(i);
				invoicetotal_inloop+=order.getSubtotalprice();
				totalQty_inloop+=order.getQty();
			}
			String invoicetotal = String.valueOf(invoicetotal_inloop);
			String totalQty = String.valueOf(totalQty_inloop);
			session.setAttribute("invoicetotal", invoicetotal);
			session.setAttribute("totalQty", totalQty);
			String url = "/frontstage/Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			return;
		}
		// 同商品加數量
		else if ("addCart".equals(action)) {
			try {
				ProductCartVO click_pdcVO = getProductCartVO_forAdd(req);
				ProductCartVO inner_pdcVO = buylist.get(buylist.indexOf(click_pdcVO));
				JSONObject jso = new JSONObject();
				if(inner_pdcVO.getQty()>=inner_pdcVO.getProductAmount()) {
					jso.put("alertMsg", "商品庫存數不足，無法訂購該數量");
					
				}else {
					inner_pdcVO.setQty(inner_pdcVO.getQty() + 1);// 將其數量+1
					jso.put("alertMsg", "");
					inner_pdcVO.setSubtotalprice(click_pdcVO.getSubtotalprice());
				}
				res.setCharacterEncoding("UTF-8");
				res.setContentType("text/plain");
				
				jso.put("id", inner_pdcVO.getId());
				jso.put("qty", inner_pdcVO.getQty());
				jso.put("prices", inner_pdcVO.getPrices());
				jso.put("discountPrice", inner_pdcVO.getDiscountPrice());
				jso.put("note", inner_pdcVO.getNote());
				jso.put("choice_Item3", inner_pdcVO.getChoice_Item3());
				jso.put("subtotalprice", inner_pdcVO.getSubtotalprice());
				res.getWriter().print(jso);
				res.getWriter().flush();
			} catch (JSONException e) {
				logger.error("商品數量相加失敗" + e.getMessage());
				e.printStackTrace();
			}
			session.setAttribute("shoppingcart", buylist);

		}

		else if ("minusCart".equals(action)) {
			try {
				ProductCartVO click_pdcVO = getProductCartVO_forAdd(req);
				ProductCartVO inner_pdcVO = buylist.get(buylist.indexOf(click_pdcVO));
			
				JSONObject jso = new JSONObject();
				if (inner_pdcVO.getQty() > 1) { // 大於0才可以減
					inner_pdcVO.setQty(inner_pdcVO.getQty() - 1);// 將其數量-1
					inner_pdcVO.setSubtotalprice(click_pdcVO.getSubtotalprice());
					jso.put("alertMsg", "");
				}else {
					jso.put("alertMsg", "商品數量不得小於1");
					
				}
				res.setCharacterEncoding("UTF-8");
				res.setContentType("text/plain");
				jso.put("id", inner_pdcVO.getId());
				jso.put("qty", inner_pdcVO.getQty());
				jso.put("prices", inner_pdcVO.getPrices());
				jso.put("discountPrice", inner_pdcVO.getDiscountPrice());
				jso.put("note", inner_pdcVO.getNote());
				jso.put("choice_Item3", inner_pdcVO.getChoice_Item3());
				jso.put("subtotalprice", inner_pdcVO.getSubtotalprice());
				res.getWriter().print(jso);
				res.getWriter().flush();
			} catch (JSONException e) {
				logger.error("商品數量相減失敗" + e.getMessage());
				e.printStackTrace();
			}
			session.setAttribute("shoppingcart", buylist);

		}

	}

	private ProductCartVO getProductCartVO_forAdd(HttpServletRequest req) {
		String action = req.getParameter("action");
		String id = req.getParameter("id");
		String prices = req.getParameter("prices");
		String discountPrice = req.getParameter("discountPrice");
		String productAmount = req.getParameter("productAmount");
		
		// detail所需
		String qty = req.getParameter("qty");
		//subtotalprice處理
		Double subtotalprice_recal = 0.0;
		String usediscountprice = req.getParameter("usediscountprice");
		String note = req.getParameter("note");
		//處理屬性加價錢
		String[] notearr = note.split(",");
		AttributeSingleService asSvc = new AttributeSingleService();
		for (String attrname : notearr) {
			if (asSvc.findByAttributeName(attrname) != null) {
				AttributeSingleVO asVO = asSvc.findByAttributeName(attrname);
				double attrsprice = asVO.getPrice();
				if(action.equals("addCart") && Integer.parseInt(qty)<Integer.parseInt(productAmount))
				subtotalprice_recal+=new Double(attrsprice*(Integer.parseInt(qty)+1)).doubleValue();	
				if(action.equals("minusCart")&& Integer.parseInt(qty)>1)
				subtotalprice_recal+=new Double(attrsprice*(Integer.parseInt(qty)-1)).doubleValue();
			}
		}
		//if是使用優惠價
		if(usediscountprice.equals("true")) {
			if(action.equals("addCart") && Integer.parseInt(qty)<Integer.parseInt(productAmount))
				subtotalprice_recal+=new Double(Double.parseDouble(discountPrice)*(Integer.parseInt(qty)+1)).doubleValue();
			if(action.equals("minusCart")&& Integer.parseInt(qty)>1)
				subtotalprice_recal+=new Double(Double.parseDouble(discountPrice)*(Integer.parseInt(qty)-1)).doubleValue();
		}else {
			if(action.equals("addCart") && Integer.parseInt(qty)<Integer.parseInt(productAmount))
				subtotalprice_recal+=new Double(Double.parseDouble(prices)*(Integer.parseInt(qty)+1)).doubleValue();
				if(action.equals("minusCart")&& Integer.parseInt(qty)>1)
					subtotalprice_recal+=new Double(Double.parseDouble(prices)*(Integer.parseInt(qty)-1)).doubleValue();
		}
		
		ProductCartVO pVO = new ProductCartVO();
		pVO.setId(new Integer(id).intValue());
		pVO.setPrices(new Double(prices).doubleValue());
		pVO.setDiscountPrice(new Double(discountPrice).doubleValue());
		pVO.setProductAmount(new Integer(productAmount).intValue());
		// detail
		pVO.setQty(new Integer(qty).intValue());
		pVO.setNote(note);
		pVO.setSubtotalprice(subtotalprice_recal);
		// attrs
		return pVO;

	}

	private ProductCartVO getProductCartVO(HttpServletRequest req) {
		
		String id = req.getParameter("id");
		String productClass = req.getParameter("productClass");
		String productClasskey = req.getParameter("productClasskey");
		String productionName = req.getParameter("productionName");
		String prices = req.getParameter("prices");
		String discountPrice = req.getParameter("discountPrice");
		String productImg = req.getParameter("productImg");
		String productImg1 = req.getParameter("productImg1");
		String productExist = req.getParameter("productExist");
		String productAmount = req.getParameter("productAmount");
		String choice_Item1 = req.getParameter("choice_Item1");
		String choice_Item2 = req.getParameter("choice_Item2");
		String choice_Item3 = req.getParameter("choice_Item3");
		String tax_Type = req.getParameter("tax_Type");
		String tax_Rate = req.getParameter("tax_Rate");
		String calories = req.getParameter("calories");
		
		if (productImg == null || productImg.trim().length() == 0) {
			productImg="noproduct.jpg";
		}
		// detail所需
		String qty = req.getParameter("qty");
		// 屬性字串處理
		String[] choice_Item3_arr = null;
		if (choice_Item3 != null ) {
			choice_Item3_arr = choice_Item3.split(",");
		}
		//處理屬性的加價錢
		String note = "";
		List<String> note_list = new ArrayList<String>();
		Double subtotalprice=0.0;
		AttributeSingleService asSvc = new AttributeSingleService();
		if (choice_Item3_arr != null) {
			for (int i = 0; i < choice_Item3_arr.length; i++) {
				String a_note = req.getParameter("attr" + i);
				if (a_note == null) {
					continue;
				} else
					note_list.add(a_note);
				if (asSvc.findByAttributeName(a_note) != null) {
					AttributeSingleVO asVO = asSvc.findByAttributeName(a_note);
					double attrsprice = asVO.getPrice();
					if (attrsprice != 0.0) {
						subtotalprice += new Double(attrsprice * Integer.parseInt(qty)).doubleValue();
					}
				}
				note = join(note_list, ",");
			}
		} else {
			note = "";
		}
		
		// attrs
		String attrs_id = req.getParameter("attrs_id");
		String attributesName = req.getParameter("attributesName");
		ProductCartVO pVO = new ProductCartVO();
		
		boolean usediscountprice=false;
		//判斷優惠價格是否為啟用未寫
		//判斷優惠價格是否為啟用
		pVO.setUsediscountprice(usediscountprice);
		pVO.setSubtotalprice(subtotalprice);
		pVO.setId(new Integer(id).intValue());
		pVO.setProductClass(productClass);
		pVO.setProductClasskey(productClasskey);
		pVO.setProductionName(productionName);
		pVO.setPrices(new Double(prices).doubleValue());//無條件捨去
		pVO.setDiscountPrice(new Double(discountPrice).doubleValue());
		pVO.setProductImg(productImg);
		pVO.setProductImg1(productImg1);
		pVO.setProductExist(new Boolean(productExist).booleanValue());
		pVO.setProductAmount(new Integer(productAmount).intValue());
		pVO.setChoice_Item1(choice_Item1);
		pVO.setChoice_Item2(choice_Item2);
		pVO.setChoice_Item3(choice_Item3);
		pVO.setTax_Type(tax_Type);
		pVO.setTax_Rate(new Double(tax_Rate).doubleValue());
		// detail
		pVO.setQty(new Integer(qty).intValue());
		pVO.setNote(note);

		// attrs
		pVO.setAttrs_id(attrs_id);
		pVO.setAttributesName(attributesName);
		return pVO;
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

