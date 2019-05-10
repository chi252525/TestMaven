package com.welljoint.entity;

public class AttributeGroupVO implements java.io.Serializable {

    private static final long serialVersionUID = 1703795663704335408L;
    private String id;
    private String attributesList;
    private Boolean multiple_choice;
    private String description;
    private Integer sequence;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAttributesList() {
        return attributesList;
    }
    public void setAttributesList(String attributesList) {
        this.attributesList = attributesList;
    }
    public Boolean getMultiple_choice() {
        return multiple_choice;
    }
    public void setMultiple_choice(Boolean multiple_choice) {
        this.multiple_choice = multiple_choice;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getSequence() {
        return sequence;
    }
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

}
