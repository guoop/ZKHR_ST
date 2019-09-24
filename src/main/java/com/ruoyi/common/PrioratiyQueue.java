package com.ruoyi.common;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PrioratiyQueue {

    public static void main(String[] args){
        PriorityQueue<Customer> priorityQueue = new PriorityQueue<Customer>(100, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return o1.getSort()>o2.getSort()?1:o1.getSort()<o2.getSort()?-1:0;
            }
        });
        Customer c = new Customer();
        c.setCarNo("35号");
        c.setSort(1);
        priorityQueue.add(c);


        Customer c2 = new Customer();
        c2.setCarNo("1号");
        c2.setSort(2);
        priorityQueue.add(c2);


        Customer c3 = new Customer();
        c3.setCarNo("8号");
        c3.setSort(3);
        priorityQueue.add(c3);


        Customer c4 = new Customer();
        c4.setCarNo("77号");
        c4.setSort(4);
        priorityQueue.add(c4);


        Customer c5= new Customer();
        c5.setCarNo("88号");
        c5.setSort(5);
        priorityQueue.add(c5);


        Customer c6= new Customer();
        c6.setCarNo("66号");
        c6.setSort(6);
        priorityQueue.add(c6);
        while (priorityQueue.size() != 0) {
            Customer ct = priorityQueue.remove();
            System.out.println(ct.getCarNo());
        }
        /*while (priorityQueue.size() != 0) {
            System.out.println(priorityQueue.peek().getCarNo());
        }*/
    }
}
