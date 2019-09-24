package com.ruoyi.project.cemslink.consumption.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConsumptionReport {

    @Excel(name="物料名称")
    private String label; // 标签
    @Excel(name="目标值(kg)")
    private BigDecimal designValue; //设计用量
    @Excel(name="称量值(kg)")
    private BigDecimal actualValue; //实际用量
    @Excel(name="误差值(kg)")
    private BigDecimal errorValue; //误差值
    @Excel(name="误差率",suffix = "%")
    private BigDecimal errorRate; //误差率

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public BigDecimal getDesignValue() {
        return designValue;
    }

    public void setDesignValue(BigDecimal designValue) {
        if(null!=designValue){
            this.designValue = designValue.setScale(2,BigDecimal.ROUND_HALF_UP);
        }
    }

    public BigDecimal getActualValue() {
        return actualValue;
    }

    public void setActualValue(BigDecimal actualValue) {
        if(null!=actualValue){
            this.actualValue = actualValue.setScale(2,BigDecimal.ROUND_HALF_UP);
        }
    }

    public BigDecimal getErrorValue() {
        return errorValue;
    }

    public void setErrorValue(BigDecimal errorValue) {
        if(null!=errorValue){
            this.errorValue = errorValue.setScale(2, RoundingMode.HALF_UP);
        }
    }

    public BigDecimal getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(BigDecimal errorRate) {
        if(null!=errorRate){
            this.errorRate = errorRate.multiply(new BigDecimal(100)).setScale(2,RoundingMode.HALF_UP);
        }
    }
}
