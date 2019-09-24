package com.ruoyi.project.runner;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.tests.domain.SigInCar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.PriorityQueue;

@Order(11)
@Component
public class PriorityQueueRunner implements CommandLineRunner {
    @Autowired
    private RedisUtils redisUtils;
    private static final Logger logger = LoggerFactory.getLogger(PriorityQueueRunner.class);
    @Override
    public void run(String... args) throws Exception {
        PriorityQueue<SigInCar> queue = redisUtils.get(Constants.TASKINGCAR_PATTEN,PriorityQueue.class);
        if(null==queue){
            queue = new PriorityQueue<SigInCar>(100, new Comparator<SigInCar>() {
                @Override
                public int compare(SigInCar o1, SigInCar o2) {
                    return o1.getSeq()>o2.getSeq()?1:o1.getSeq()<o2.getSeq()?-1:0;
                }
            });
        }
    }

}
