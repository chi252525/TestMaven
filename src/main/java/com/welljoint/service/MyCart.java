package com.welljoint.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.welljoint.entity.ProductVO;

public class MyCart {
//	https://blog.csdn.net/hoho_12/article/details/51366724
	
HashMap<String,ProductVO> hm=new HashMap<String,ProductVO>();
	
	//添加书的第二个方法
	public void addBook2(String id){
		if(hm.containsKey(id)){
			//hm已经有这个书
			ProductVO book=hm.get(id);
//			int shoppingNum=book.getShoppingNum();
//			book.setShoppingNum(shoppingNum+1);
		}else{
//			hm.put(id, new BookService().getBookById(id));
		}
	}
	
	//返回该购物车的总价格
	public float getTotalPrice(){
		float totalPrice=0.0f;
		
		//得到总价格
		ArrayList<ProductVO> al=new ArrayList<ProductVO>();
		Iterator it=hm.keySet().iterator();
		while(it.hasNext()){
			//取出书号
			String bookId=(String)it.next();
			//取出书号对应的Book
			ProductVO book=hm.get(bookId);
//			totalPrice+=book.getPrice()*book.getShoppingNum();
			
		}
		
		return totalPrice;
	}
	
	//添加书籍
	public void addBook(String id,ProductVO book){
		if(hm.containsKey(id)){
			book=hm.get(id);
			//如果这本书已经购买过，shoppingNum数量+1
//			int shoppingNum=book.getShoppingNum();
//			book.setShoppingNum(shoppingNum+1);
			//hm.put(id, book);
		}else{		
		   hm.put(id, book);
		}
		
	}
	//删除书籍
	public void delBook(String id){
		hm.remove(id);
	}
	
	//更新书籍(对于购物车，更新所买书籍的数量)
	public void updateBook(String id,String nums){
		//取出id对应的Book
		ProductVO book=hm.get(id);
//		book.setShoppingNum(Integer.parseInt(nums));	
		
	}
	//显示该购物车中的所有商品信息
	public ArrayList showMyCart(){
		ArrayList<ProductVO> al=new ArrayList<ProductVO>();
		
		//遍历HashMap		
		Iterator it=hm.keySet().iterator();
		while(it.hasNext()){
			//取出key
			String id=(String)it.next();
			//取出Book
			ProductVO book=hm.get(id);
			al.add(book);			
		}
		return al;
	}
	
	
	//清空书,清空购物车
	public void clearBook(){
		hm.clear();
	}

}
