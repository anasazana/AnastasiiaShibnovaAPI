package ru.training.at.hw10.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Membership {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("idMember")
    @Expose
    private String idMember;
    @SerializedName("memberType")
    @Expose
    private String memberType;
    @SerializedName("unconfirmed")
    @Expose
    private Boolean unconfirmed;
    @SerializedName("deactivated")
    @Expose
    private Boolean deactivated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public Boolean getUnconfirmed() {
        return unconfirmed;
    }

    public void setUnconfirmed(Boolean unconfirmed) {
        this.unconfirmed = unconfirmed;
    }

    public Boolean getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(Boolean deactivated) {
        this.deactivated = deactivated;
    }

}
