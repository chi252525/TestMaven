package com.welljoint.entity;

public class ProductVO implements java.io.Serializable{
	
	public ProductVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private Integer id;
	private String productClass;
	private String productClasskey;
	private String productionName;
	private String subordinate_Name;
	private String productionContent0;
	private String productionContent1;
	private String productionContent2;
	private Double prices;
	private Double discountPrice;
	private String productImg;
	private String productImg1;
	private Boolean productExist;
	private Integer productAmount;
	private String choice_Item1;
	private String choice_Item2;
	private String choice_Item3;
	private Boolean orderDisplay;
	private Integer sequence;
	private Double calories;
	private String week;
	private String timeInterval;
	private String tax_Type;
	private Double tax_Rate;
	private String description;
	//購物車需要
	private Integer shoppingQty;
	private Double shoppingSubtotalprice;
	private Double shoppingPrice;
	private String shoppingNote;
	
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(!(obj instanceof ProductVO)) {
			return false;
		}
		ProductVO pVO =(ProductVO)obj;
		if(this.id==pVO.id) {
			return true;
		}else {
			return false;	
		}
	}
	public int hashCode() {
		return this.shoppingNote.hashCode() + this.id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductClass() {
		return productClass;
	}
	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}
	public String getProductClasskey() {
		return productClasskey;
	}
	public void setProductClasskey(String productClasskey) {
		this.productClasskey = productClasskey;
	}
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}
	public String getSubordinate_Name() {
		return subordinate_Name;
	}
	public void setSubordinate_Name(String subordinate_Name) {
		this.subordinate_Name = subordinate_Name;
	}
	public String getProductionContent0() {
		return productionContent0;
	}
	public void setProductionContent0(String productionContent0) {
		this.productionContent0 = productionContent0;
	}
	public String getProductionContent1() {
		return productionContent1;
	}
	public void setProductionContent1(String productionContent1) {
		this.productionContent1 = productionContent1;
	}
	public String getProductionContent2() {
		return productionContent2;
	}
	public void setProductionContent2(String productionContent2) {
		this.productionContent2 = productionContent2;
	}
	public Double getPrices() {
		return prices;
	}
	public void setPrices(Double prices) {
		this.prices = prices;
	}
	public Double getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public String getProductImg1() {
		return productImg1;
	}
	public void setProductImg1(String productImg1) {
		this.productImg1 = productImg1;
	}
	public Boolean getProductExist() {
		return productExist;
	}
	public void setProductExist(Boolean productExist) {
		this.productExist = productExist;
	}
	public Integer getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(Integer productAmount) {
		this.productAmount = productAmount;
	}
	public String getChoice_Item1() {
		return choice_Item1;
	}
	public void setChoice_Item1(String choice_Item1) {
		this.choice_Item1 = choice_Item1;
	}
	public String getChoice_Item2() {
		return choice_Item2;
	}
	public void setChoice_Item2(String choice_Item2) {
		this.choice_Item2 = choice_Item2;
	}
	public String getChoice_Item3() {
		return choice_Item3;
	}
	public void setChoice_Item3(String choice_Item3) {
		this.choice_Item3 = choice_Item3;
	}
	public Boolean getOrderDisplay() {
		return orderDisplay;
	}
	public void setOrderDisplay(Boolean orderDisplay) {
		this.orderDisplay = orderDisplay;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public Double getCalories() {
		return calories;
	}
	public void setCalories(Double calories) {
		this.calories = calories;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getTimeInterval() {
		return timeInterval;
	}
	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}
	public String getTax_Type() {
		return tax_Type;
	}
	public void setTax_Type(String tax_Type) {
		this.tax_Type = tax_Type;
	}
	public Double getTax_Rate() {
		return tax_Rate;
	}
	public void setTax_Rate(Double tax_Rate) {
		this.tax_Rate = tax_Rate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getShoppingQty() {
		return shoppingQty;
	}
	public void setShoppingQty(Integer shoppingQty) {
		this.shoppingQty = shoppingQty;
	}
	public Double getShoppingSubtotalprice() {
		return shoppingSubtotalprice;
	}
	public void setShoppingSubtotalprice(Double shoppingSubtotalprice) {
		this.shoppingSubtotalprice = shoppingSubtotalprice;
	}
	public Double getShoppingPrice() {
		return shoppingPrice;
	}
	public void setShoppingPrice(Double shoppingPrice) {
		this.shoppingPrice = shoppingPrice;
	}
	public String getShoppingNote() {
		return shoppingNote;
	}
	public void setShoppingNote(String shoppingNote) {
		this.shoppingNote = shoppingNote;
	}

	
	
}
