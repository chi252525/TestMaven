package com.welljoint.entity;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import com.welljoint.entity.OrderPhoneDetailVO;

public class OrdersPhoneVO {
	private Set<OrderPhoneDetailVO> opdVOs = new HashSet<OrderPhoneDetailVO>();
	private String uuid;
	private String ordersNum;
	private String name	;
	private String store;
	private String invoicePeriod;
	private String invoicesNum;
	private Integer mealNum;	
	private Timestamp orderDate;
	private String orderer;
	private String phoneNum;
	private Integer processStatus;
	private Boolean paymentStatus;
	private Timestamp invoicesDate;
	private Double invoicesTotal;
	private String value;
	private String note;
	private String customerValue;
	private String orderStatus;
	private String orderStatusKey;
	private Boolean status;
	private String toolCode;
	private String loveCode;
	private String tax;
	private String payBy;
	private String cancel;
	private String internalNumber;
	private String reprintNumber;
	private Long takeTime;
	private String orderBy;
	private String randomNum;
	private String sell_No;
	private String TC8_ReturnMessage;
	private String mealQrcode;
	
	public Set<OrderPhoneDetailVO> getOpdVOs() {
		return opdVOs;
	}
	public void setOpdVOs(Set<OrderPhoneDetailVO> opdVOs) {
		this.opdVOs = opdVOs;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getOrdersNum() {
		return ordersNum;
	}
	public void setOrdersNum(String ordersNum) {
		this.ordersNum = ordersNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getInvoicePeriod() {
		return invoicePeriod;
	}
	public void setInvoicePeriod(String invoicePeriod) {
		this.invoicePeriod = invoicePeriod;
	}
	public String getInvoicesNum() {
		return invoicesNum;
	}
	public void setInvoicesNum(String invoicesNum) {
		this.invoicesNum = invoicesNum;
	}
	public Integer getMealNum() {
		return mealNum;
	}
	public void setMealNum(Integer mealNum) {
		this.mealNum = mealNum;
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderer() {
		return orderer;
	}
	public void setOrderer(String orderer) {
		this.orderer = orderer;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public Integer getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(Integer processStatus) {
		this.processStatus = processStatus;
	}
	public Boolean getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(Boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Timestamp getInvoicesDate() {
		return invoicesDate;
	}
	public void setInvoicesDate(Timestamp invoicesDate) {
		this.invoicesDate = invoicesDate;
	}
	public Double getInvoicesTotal() {
		return invoicesTotal;
	}
	public void setInvoicesTotal(Double invoicesTotal) {
		this.invoicesTotal = invoicesTotal;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCustomerValue() {
		return customerValue;
	}
	public void setCustomerValue(String customerValue) {
		this.customerValue = customerValue;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderStatusKey() {
		return orderStatusKey;
	}
	public void setOrderStatusKey(String orderStatusKey) {
		this.orderStatusKey = orderStatusKey;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getToolCode() {
		return toolCode;
	}
	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}
	public String getLoveCode() {
		return loveCode;
	}
	public void setLoveCode(String loveCode) {
		this.loveCode = loveCode;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getPayBy() {
		return payBy;
	}
	public void setPayBy(String payBy) {
		this.payBy = payBy;
	}
	public String getCancel() {
		return cancel;
	}
	public void setCancel(String cancel) {
		this.cancel = cancel;
	}
	public String getInternalNumber() {
		return internalNumber;
	}
	public void setInternalNumber(String internalNumber) {
		this.internalNumber = internalNumber;
	}
	public String getReprintNumber() {
		return reprintNumber;
	}
	public void setReprintNumber(String reprintNumber) {
		this.reprintNumber = reprintNumber;
	}
	public Long getTakeTime() {
		return takeTime;
	}
	public void setTakeTime(Long takeTime) {
		this.takeTime = takeTime;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getRandomNum() {
		return randomNum;
	}
	public void setRandomNum(String randomNum) {
		this.randomNum = randomNum;
	}
	public String getSell_No() {
		return sell_No;
	}
	public void setSell_No(String sell_No) {
		this.sell_No = sell_No;
	}
	public String getTC8_ReturnMessage() {
		return TC8_ReturnMessage;
	}
	public void setTC8_ReturnMessage(String tC8_ReturnMessage) {
		TC8_ReturnMessage = tC8_ReturnMessage;
	}
	/**
	 * @return the mealQrcode
	 */
	public String getMealQrcode() {
		return mealQrcode;
	}
	/**
	 * @param mealQrcode the mealQrcode to set
	 */
	public void setMealQrcode(String mealQrcode) {
		this.mealQrcode = mealQrcode;
	}


}
