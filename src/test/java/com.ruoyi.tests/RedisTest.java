package com.ruoyi.tests;

import com.ruoyi.api.PushUtils;
import com.ruoyi.common.Customer;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
	@Autowired
	private RedisUtils redisUtils;

	public class TestA{
		private String a;
		private String b;

		public String getA() {
			return a;
		}

		public void setA(String a) {
			this.a = a;
		}

		public String getB() {
			return b;
		}

		public void setB(String b) {
			this.b = b;
		}
	}


	@Test
	public void contextLoads() {
		TestA a = new TestA();
		a.setA("a");
		a.setB("B");
		TestA b = new TestA();
		b.setA("ab");
		b.setB("ba");
		redisUtils.set("CONT_1",a);
		redisUtils.set("CONT_2",b);
		System.out.println(redisUtils.get("order"));
		redisUtils.set("order1","aaaa");
		System.out.println(redisUtils.get("order"));
		System.out.println(redisUtils.getLists("CONT_*", TestA.class));
	}


	@Test
	public void testNotify(){
		Map<String,String> pa = new HashMap<>();
		pa.put("taskType","POP");
		PushUtils.pushSendTasking("通知测试","1145627534098030592",pa);
	}

	@Test
	public void savePriority(){
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
		redisUtils.set("priorityQueue",priorityQueue);
		PriorityQueue<Customer> priorityQueue1 = redisUtils.get("priorityQueue",PriorityQueue.class);
		while(priorityQueue1.size()!=0){
			System.out.println(priorityQueue.remove());
		}
	}



}
