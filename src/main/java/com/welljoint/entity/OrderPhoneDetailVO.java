package com.welljoint.entity;

import com.welljoint.entity.OrdersPhoneVO;

public class OrderPhoneDetailVO implements java.io.Serializable{
	
	public OrderPhoneDetailVO() {
		 id=0;
		 ordersnum="";
		 productclass="";
		 productclasskey="";
		 productname="";
		 qty=0;
		 price=0.0;
		 totalprice=0.0;
		 note="";
		 tax_type="";
		 tax=0.0;
	}
	private Integer id;
	private String ordersnum;
	private String productclass;
	private String productclasskey;
	private String productname;
	private Integer qty;
	private Double price;
	private Double totalprice;
	private String note;
	private String tax_type;
	private Double tax;
	private Boolean usediscountprice;
	private OrdersPhoneVO opVO;
	public OrdersPhoneVO getOpVO() {
		return opVO;
	}
	public void setOpVO(OrdersPhoneVO opVO) {
		this.opVO = opVO;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrdersnum() {
		return ordersnum;
	}
	public void setOrdersnum(String ordersnum) {
		this.ordersnum = ordersnum;
	}
	public String getProductclass() {
		return productclass;
	}
	public void setProductclass(String productclass) {
		this.productclass = productclass;
	}
	public String getProductclasskey() {
		return productclasskey;
	}
	public void setProductclasskey(String productclasskey) {
		this.productclasskey = productclasskey;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getTax_type() {
		return tax_type;
	}
	public void setTax_type(String tax_type) {
		this.tax_type = tax_type;
	}
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	public Boolean getUsediscountprice() {
		return usediscountprice;
	}
	public void setUsediscountprice(Boolean usediscountprice) {
		this.usediscountprice = usediscountprice;
	}
	
}
