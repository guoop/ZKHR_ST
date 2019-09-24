package com.ruoyi.common;

import org.apache.logging.log4j.util.PropertySource;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class Customer implements Comparator<Customer>{

    private int sort;
    private String carNo;

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    @Override
    public int compare(Customer o1, Customer o2) {
        int ret = 0;
        if(o1.getSort()<o2.getSort()){
            ret = -1;
        }else if(o1.getSort()>o2.getSort()){
            ret = 1;
        }
        return ret;
    }
}
