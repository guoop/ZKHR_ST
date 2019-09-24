package com.ruoyi.project.cemslink.preproduction.domain;

public class ProductLineQueue {
    /** 生产线 */
    private Integer productLine;
    //排队车辆数目
    private String carNo;
    private Integer state;//1.正在打灰，null，准备中
    private String productNumber; //运输编号taskcar.getid

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public Integer getProductLine() {
        return productLine;
    }

    public void setProductLine(Integer productLine) {
        this.productLine = productLine;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
