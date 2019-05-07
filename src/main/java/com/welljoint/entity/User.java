package com.welljoint.entity;
/** 
 * @version  
 * @time 2018-2-28 下午6:02:03 
 * @describe:使用者的bean
 */
public class User {
    private Integer id;
    private String userName;
    private String password;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", password="
                + password + "]";
    }
}