package com.pos.app;

public class DetailVO {
    private String Id;
    private String InvoicesNum;
    private String ProductClass;
    private String ProductClassKey;
    private String ProductName;
    private String Qty;
    private String Price;
    private String TotalPrice;
    private String Note;
    private String Tax_Type;
    private String Tax;

    public String getId() {
        return Id;
    }
    public void setId(String id) {
        Id = id;
    }
    public String getInvoicesNum() {
        return InvoicesNum;
    }
    public void setInvoicesNum(String invoicesNum) {
        InvoicesNum = invoicesNum;
    }
    public String getProductClass() {
        return ProductClass;
    }
    public void setProductClass(String productClass) {
        ProductClass = productClass;
    }
    public String getProductClassKey() {
        return ProductClassKey;
    }
    public void setProductClassKey(String productClassKey) {
        ProductClassKey = productClassKey;
    }
    public String getProductName() {
        return ProductName;
    }
    public void setProductName(String productName) {
        ProductName = productName;
    }
    public String getQty() {
        return Qty;
    }
    public void setQty(String qty) {
        Qty = qty;
    }
    public String getPrice() {
        return Price;
    }
    public void setPrice(String price) {
        Price = price;
    }
    public String getTotalPrice() {
        return TotalPrice;
    }
    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }
    public String getNote() {
        return Note;
    }
    public void setNote(String note) {
        Note = note;
    }
    public String getTax_Type() {
        return Tax_Type;
    }
    public void setTax_Type(String tax_Type) {
        Tax_Type = tax_Type;
    }
    public String getTax() {
        return Tax;
    }
    public void setTax(String tax) {
        Tax = tax;
    }
}
