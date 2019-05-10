package com.welljoint.entity;

public class AttributeSingleVO implements java.io.Serializable {


    private static final long serialVersionUID = -6253110519876038727L;
    private String id;
    private String attributesName;
    private Double price;
    private Integer sequence;
    private String syncCode;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAttributesName() {
        return attributesName;
    }
    public void setAttributesName(String attributesName) {
        this.attributesName = attributesName;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getSequence() {
        return sequence;
    }
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
    public String getSyncCode() {
        return syncCode;
    }
    public void setSyncCode(String syncCode) {
        this.syncCode = syncCode;
    }

}
