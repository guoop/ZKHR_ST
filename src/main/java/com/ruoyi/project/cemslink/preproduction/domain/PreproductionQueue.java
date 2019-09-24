package com.ruoyi.project.cemslink.preproduction.domain;

public class PreproductionQueue {
    /** 生产线 */
    private Integer productLine;
    //排队车辆数目
    private Integer cntCar;

    public Integer getProductLine() {
        return productLine;
    }

    public void setProductLine(Integer productLine) {
        this.productLine = productLine;
    }

    public Integer getCntCar() {
        return cntCar;
    }

    public void setCntCar(Integer cntCar) {
        this.cntCar = cntCar;
    }
}
