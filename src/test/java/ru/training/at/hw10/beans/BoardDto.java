package ru.training.at.hw10.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class BoardDto {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("descData")
    @Expose
    private DescData descData;
    @SerializedName("closed")
    @Expose
    private Boolean closed;
    @SerializedName("dateClosed")
    @Expose
    private Object dateClosed;
    @SerializedName("idOrganization")
    @Expose
    private String idOrganization;
    @SerializedName("idEnterprise")
    @Expose
    private Object idEnterprise;
    @SerializedName("limits")
    @Expose
    private Object limits;
    @SerializedName("pinned")
    @Expose
    private Object pinned;
    @SerializedName("shortLink")
    @Expose
    private String shortLink;
    @SerializedName("powerUps")
    @Expose
    private List<Object> powerUps = new ArrayList<Object>();
    @SerializedName("dateLastActivity")
    @Expose
    private Object dateLastActivity;
    @SerializedName("idTags")
    @Expose
    private List<Object> idTags = new ArrayList<Object>();
    @SerializedName("datePluginDisable")
    @Expose
    private Object datePluginDisable;
    @SerializedName("creationMethod")
    @Expose
    private String creationMethod;
    @SerializedName("ixUpdate")
    @Expose
    private Object ixUpdate;
    @SerializedName("enterpriseOwned")
    @Expose
    private Boolean enterpriseOwned;
    @SerializedName("idBoardSource")
    @Expose
    private Object idBoardSource;
    @SerializedName("idMemberCreator")
    @Expose
    private String idMemberCreator;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("starred")
    @Expose
    private Boolean starred;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("prefs")
    @Expose
    private Prefs prefs;
    @SerializedName("subscribed")
    @Expose
    private Boolean subscribed;
    @SerializedName("labelNames")
    @Expose
    private LabelNames labelNames;
    @SerializedName("shortUrl")
    @Expose
    private String shortUrl;
    @SerializedName("templateGallery")
    @Expose
    private Object templateGallery;
    @SerializedName("premiumFeatures")
    @Expose
    private List<Object> premiumFeatures = new ArrayList<Object>();
    @SerializedName("memberships")
    @Expose
    private List<Membership> memberships = new ArrayList<Membership>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public DescData getDescData() {
        return descData;
    }

    public void setDescData(DescData descData) {
        this.descData = descData;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public Object getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(Object dateClosed) {
        this.dateClosed = dateClosed;
    }

    public String getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(String idOrganization) {
        this.idOrganization = idOrganization;
    }

    public Object getIdEnterprise() {
        return idEnterprise;
    }

    public void setIdEnterprise(Object idEnterprise) {
        this.idEnterprise = idEnterprise;
    }

    public Object getLimits() {
        return limits;
    }

    public void setLimits(Object limits) {
        this.limits = limits;
    }

    public Object getPinned() {
        return pinned;
    }

    public void setPinned(Object pinned) {
        this.pinned = pinned;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public List<Object> getPowerUps() {
        return powerUps;
    }

    public void setPowerUps(List<Object> powerUps) {
        this.powerUps = powerUps;
    }

    public Object getDateLastActivity() {
        return dateLastActivity;
    }

    public void setDateLastActivity(Object dateLastActivity) {
        this.dateLastActivity = dateLastActivity;
    }

    public List<Object> getIdTags() {
        return idTags;
    }

    public void setIdTags(List<Object> idTags) {
        this.idTags = idTags;
    }

    public Object getDatePluginDisable() {
        return datePluginDisable;
    }

    public void setDatePluginDisable(Object datePluginDisable) {
        this.datePluginDisable = datePluginDisable;
    }

    public String getCreationMethod() {
        return creationMethod;
    }

    public void setCreationMethod(String creationMethod) {
        this.creationMethod = creationMethod;
    }

    public Object getIxUpdate() {
        return ixUpdate;
    }

    public void setIxUpdate(Object ixUpdate) {
        this.ixUpdate = ixUpdate;
    }

    public Boolean getEnterpriseOwned() {
        return enterpriseOwned;
    }

    public void setEnterpriseOwned(Boolean enterpriseOwned) {
        this.enterpriseOwned = enterpriseOwned;
    }

    public Object getIdBoardSource() {
        return idBoardSource;
    }

    public void setIdBoardSource(Object idBoardSource) {
        this.idBoardSource = idBoardSource;
    }

    public String getIdMemberCreator() {
        return idMemberCreator;
    }

    public void setIdMemberCreator(String idMemberCreator) {
        this.idMemberCreator = idMemberCreator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getStarred() {
        return starred;
    }

    public void setStarred(Boolean starred) {
        this.starred = starred;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Prefs getPrefs() {
        return prefs;
    }

    public void setPrefs(Prefs prefs) {
        this.prefs = prefs;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    public LabelNames getLabelNames() {
        return labelNames;
    }

    public void setLabelNames(LabelNames labelNames) {
        this.labelNames = labelNames;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Object getTemplateGallery() {
        return templateGallery;
    }

    public void setTemplateGallery(Object templateGallery) {
        this.templateGallery = templateGallery;
    }

    public List<Object> getPremiumFeatures() {
        return premiumFeatures;
    }

    public void setPremiumFeatures(List<Object> premiumFeatures) {
        this.premiumFeatures = premiumFeatures;
    }

    public List<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<Membership> memberships) {
        this.memberships = memberships;
    }

}
