package com.bayee.political.json;

/**
 * @author tlt
 * @date 2021/10/26
 */
public class UserSaveParam {

    /**
     * id
     */
    private Integer id;

    /**
     *用户名
     */
    private String userName;

    /**
     *密码
     */
    private String password;

    /**
     *用户姓名
     */
    private String name;

    /**
     *家属警号
     */
    private String familyPoliceId;

    /**
     *家属姓名
     */
    private String familyName;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyPoliceId() {
        return familyPoliceId;
    }

    public void setFamilyPoliceId(String familyPoliceId) {
        this.familyPoliceId = familyPoliceId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
