package com.ruoyi.tests;

import com.ruoyi.tests.comparator.SiginComparator;
import com.ruoyi.tests.domain.SigInCar;

import java.util.PriorityQueue;

public class TestClass {

    public static void main(String[] args) {
        PriorityQueue<SigInCar> queue = new PriorityQueue<>(7,new SiginComparator());
        System.out.println("d");
    }
}
