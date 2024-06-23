package cn.bugstack.test;

import cn.bugstack.infrastructure.persistent.redis.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RMap;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Resource
    private IRedisService redisService;
    //demo
    @Test
    public void test(){
        RMap<Object, Object> map = redisService.getMap("strategy_id_10001");
        map.put(1, 101);
        map.put(2, 101);
        map.put(3, 102);
        map.put(4, 102);
        map.put(5, 103);
        map.put(6, 103);
        map.put(7, 104);
        map.put(8, 104);
        map.put(9, 105);
        map.put(10, 105);
        log.info("测试结果：{}", redisService.getFromMap("strategy_id_10001", 1).toString());



    }


}
