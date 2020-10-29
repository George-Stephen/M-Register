
package com.iconic.services.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Member {

    @SerializedName("id_number")
    @Expose
    private String idNumber;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("date_birth")
    @Expose
    private String dateBirth;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("county")
    @Expose
    private String county;
    @SerializedName("sub_county")
    @Expose
    private String subCounty;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Member() {
    }

    /**
     * 
     * @param phoneNumber
     * @param gender
     * @param county
     * @param fullName
     * @param subCounty
     * @param idNumber
     * @param dateBirth
     */
    public Member(String idNumber, String fullName, String gender, String dateBirth, String phoneNumber, String county, String subCounty) {
        super();
        this.idNumber = idNumber;
        this.fullName = fullName;
        this.gender = gender;
        this.dateBirth = dateBirth;
        this.phoneNumber = phoneNumber;
        this.county = county;
        this.subCounty = subCounty;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(String subCounty) {
        this.subCounty = subCounty;
    }

}
