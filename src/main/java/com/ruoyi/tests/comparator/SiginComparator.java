package com.ruoyi.tests.comparator;

import com.ruoyi.tests.domain.SigInCar;

import java.util.Comparator;

public class SiginComparator implements Comparator<SigInCar> {
    @Override
    public int compare(SigInCar o1, SigInCar o2) {
        return o1.getSeq()>o2.getSeq()?1:o1.getSeq()<o2.getSeq()?-1:0;
    }
}
