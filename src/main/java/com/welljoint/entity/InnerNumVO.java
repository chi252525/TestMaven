package com.welljoint.entity;


public class InnerNumVO implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5128921634984728103L;
    private Integer id;
    private String tableName;
    private Integer tabaleCapacity;
    private Integer sequence;
    private Boolean status;
    private String tableContinue;
    private byte[] changeStamp;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public Integer getTabaleCapacity() {
        return tabaleCapacity;
    }
    public void setTabaleCapacity(Integer tabaleCapacity) {
        this.tabaleCapacity = tabaleCapacity;
    }
    public Integer getSequence() {
        return sequence;
    }
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public String getTableContinue() {
        return tableContinue;
    }
    public void setTableContinue(String tableContinue) {
        this.tableContinue = tableContinue;
    }
    public byte[] getChangeStamp() {
        return changeStamp;
    }
    public void setChangeStamp(byte[] changeStamp) {
        this.changeStamp = changeStamp;
    }
}
