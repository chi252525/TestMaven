package com.welljoint.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.welljoint.entity.AttributeSingleVO;
import com.welljoint.entity.ProductVO;

//https://blog.csdn.net/hoho_12/article/details/51366724

public class MyCart {
	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
	HashMap<Integer, ProductVO> hm = new HashMap<Integer, ProductVO>();
	private ProductService productSvc;
	private AttributeSingleService attrsSvc;
	// 添加商品
	public void addProduct(String id, ProductVO pVO) {
		productSvc = applicationContext.getBean(ProductService.class);
		String shoppingNote = pVO.getShoppingNote();
		String[] shoppingNote_arr=shoppingNote.split(",");
		Arrays.sort(shoppingNote_arr);
		//============================比較是否為同一商品============================
		if (hm.containsKey(pVO.hashCode())) {
//			System.out.println("id="+id+"重複的商品");
			ProductVO pVO_inhm = hm.get(pVO.hashCode());
			String shoppingNote_inhm = pVO_inhm.getShoppingNote();
			String[] shoppingNote_inhm_arr=shoppingNote_inhm.split(",");
			Arrays.sort(shoppingNote_inhm_arr);
//			================================比較是否選購同屬性============================
			if(Arrays.equals(shoppingNote_arr,shoppingNote_inhm_arr)){ 
//				System.out.print("重複的品項");
				// 加入購物車，shoppingQty数量
				int shoppingQty = pVO_inhm.getShoppingQty();
				pVO_inhm.setShoppingQty(shoppingQty + pVO.getShoppingQty());
				//處裡屬性加價錢
				Double shoppingSubtotalprice=countSubtotalprice(pVO_inhm,shoppingQty + pVO.getShoppingQty(),true);
				pVO_inhm.setShoppingSubtotalprice(shoppingSubtotalprice);//更新小計
				hm.put(pVO.hashCode(), pVO_inhm);
				Set<Integer> set = hm.keySet();
				Iterator it= set.iterator();
				while(it.hasNext()) {
					Object myKey = it.next();
					System.out.println(myKey +"="+ hm.get(myKey));
				}
			};
		} else {
//			System.out.println("不重複的商品");
			ProductVO newpVO=productSvc.findbyId(Integer.parseInt(id));
			newpVO.setShoppingNote(shoppingNote);
			newpVO.setShoppingPrice(pVO.getShoppingPrice());
			newpVO.setShoppingQty(pVO.getShoppingQty());
			Double shoppingSubtotalprice=countSubtotalprice(newpVO,pVO.getShoppingQty(),true);
			newpVO.setShoppingSubtotalprice(shoppingSubtotalprice);
			hm.put(newpVO.hashCode(), newpVO);
			Set<Integer> set = hm.keySet();
			Iterator it= set.iterator();
			while(it.hasNext()) {
				Object myKey = it.next();
				System.out.println(myKey +"="+ hm.get(myKey));
			}
		}
	}

	public Double countSubtotalprice(ProductVO pVO,Integer shoppingQty, boolean actionbln) {
		attrsSvc = applicationContext.getBean(AttributeSingleService.class);
		Double shoppingSubtotalprice = 0.0;
		Double oneproduct_price=pVO.getShoppingPrice();
		String[] shoppingNote_arr = pVO.getShoppingNote().split(",");
		for (String oneattrName : shoppingNote_arr) {
			if(oneattrName!=null && oneattrName.trim().length()!=0) {
				AttributeSingleVO asVO = attrsSvc.findByAttributeName(oneattrName);
				Double asVOprice=asVO.getPrice();
				if (asVO.getPrice() != null && asVO.getPrice()!=0) {
					if (actionbln == true) {//true是加
						oneproduct_price+= asVOprice;
					}
				}
			}
			
		}
		shoppingSubtotalprice=(oneproduct_price*shoppingQty);
		return shoppingSubtotalprice;
	}
	// 返回該購物車的總價格
	public Double getTotalPrice() {
		Double totalPrice = 0.0;
		// 得到总价格
		ArrayList<ProductVO> al = new ArrayList<ProductVO>();
		Iterator it = hm.keySet().iterator();
		while (it.hasNext()) {
			Integer pVOId = (Integer) it.next();
			ProductVO pVO = hm.get(pVOId);
			totalPrice += pVO.getShoppingSubtotalprice();
		}
		return totalPrice;
	}
	
	// 返回該購物車的總數量
	public int getTotalQty() {
		int totalQty = 0;
		ArrayList<ProductVO> al = new ArrayList<ProductVO>();
		Iterator it = hm.keySet().iterator();
		while (it.hasNext()) {
			Integer pVOId = (Integer) it.next();
			ProductVO pVO = hm.get(pVOId);
			totalQty += pVO.getShoppingQty();
		}
		return totalQty;
	}
	
	// 删除商品
	public void delProduct(Integer strhashCode) {
		hm.remove(strhashCode);
//		Set<Integer> set = hm.keySet();
//		Iterator it= set.iterator();
//		while(it.hasNext()) {
//			Object myKey = it.next();
//			System.out.println("刪除後剩的"+myKey +"="+ hm.get(myKey));
//		}
		
	}

	// 更新商品
	public void updateProduct(Integer strhashCode, String quantity) {
		ProductVO pVO = hm.get(strhashCode);
		pVO.setShoppingQty(Integer.parseInt(quantity));
		Double shoppingSubtotalprice=countSubtotalprice(pVO,Integer.parseInt(quantity),true);
		pVO.setShoppingSubtotalprice(shoppingSubtotalprice);//更新小計
		hm.put(strhashCode, pVO);
	}

	// 顯示該購物中的所有商品信息
	public ArrayList showMyCart() {
		ArrayList<ProductVO> al = new ArrayList<ProductVO>();
		// 遍历HashMap
		Iterator it = hm.keySet().iterator();
		while (it.hasNext()) {
			Integer id_init = (Integer) it.next();
			ProductVO productVO = hm.get(id_init);
			al.add(productVO);
		}
		return al;
	}

	// 清空購物車
	public void clearProduct() {
		hm.clear();
	}

}
