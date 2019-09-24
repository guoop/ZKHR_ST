package com.ruoyi.api.domain;

import java.math.BigDecimal;
import java.util.Date;

public class PrintDomain {
    private Long taskCarId;
    //序号
    private String orderNo;
    //当前时间
    private Date dateTime;
    //收货单位
    private String receivingCompany;
    //收货人电话
    private String telephone;
    //到达时间
    private Date arrivalTime;
    //工程名称
    private String engineeringName;
    //强度等级
    private String strengthGrade;
    //单价
    private BigDecimal unitPrice;
    //浇筑部位
    private String pouringSite;
    //浇筑方式
    private String pouringMethod;
    //备注
    private String remarks;
    //车号
    private Integer licenseNumber;
    //车次
    private Integer trainNumber;
    //本车方量
    private BigDecimal squareQuantity;
    //收货人
    private String consignee;
    //毛重
    private BigDecimal grossWeight;
    //皮重
    private BigDecimal tare;
    /**累计方量*/
    private BigDecimal accumulatedVolume;
    //净重
    private BigDecimal stdWeight;
    //业务经理
    private String serviceManager;
    //业务经理电话
    private String managerTelephone;

    public Long getTaskCarId() {
        return taskCarId;
    }

    public void setTaskCarId(Long taskCarId) {
        this.taskCarId = taskCarId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getReceivingCompany() {
        return receivingCompany;
    }

    public void setReceivingCompany(String receivingCompany) {
        this.receivingCompany = receivingCompany;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getEngineeringName() {
        return engineeringName;
    }

    public void setEngineeringName(String engineeringName) {
        this.engineeringName = engineeringName;
    }

    public String getStrengthGrade() {
        return strengthGrade;
    }

    public void setStrengthGrade(String strengthGrade) {
        this.strengthGrade = strengthGrade;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getPouringSite() {
        return pouringSite;
    }

    public void setPouringSite(String pouringSite) {
        this.pouringSite = pouringSite;
    }

    public String getPouringMethod() {
        return pouringMethod;
    }

    public void setPouringMethod(String pouringMethod) {
        this.pouringMethod = pouringMethod;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(Integer licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Integer getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(Integer trainNumber) {
        this.trainNumber = trainNumber;
    }

    public BigDecimal getSquareQuantity() {
        return squareQuantity;
    }

    public void setSquareQuantity(BigDecimal squareQuantity) {
        this.squareQuantity = squareQuantity;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public BigDecimal getTare() {
        return tare;
    }

    public void setTare(BigDecimal tare) {
        this.tare = tare;
    }

    public BigDecimal getAccumulatedVolume() {
        return accumulatedVolume;
    }

    /**累计方量*/
    public void setAccumulatedVolume(BigDecimal accumulatedVolume) {
        this.accumulatedVolume = accumulatedVolume;
    }

    public BigDecimal getStdWeight() {
        return stdWeight;
    }

    public void setStdWeight(BigDecimal stdWeight) {
        this.stdWeight = stdWeight;
    }

    public String getServiceManager() {
        return serviceManager;
    }

    public void setServiceManager(String serviceManager) {
        this.serviceManager = serviceManager;
    }

    public String getManagerTelephone() {
        return managerTelephone;
    }

    public void setManagerTelephone(String managerTelephone) {
        this.managerTelephone = managerTelephone;
    }
}
