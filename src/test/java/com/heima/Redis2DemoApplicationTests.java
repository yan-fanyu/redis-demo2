package com.heima;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.heima.redis.pojo.User;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class Redis2DemoApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    // JSON 工具
    public static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testString() {
        // 添加
        stringRedisTemplate.opsForValue().set("name2", "Hello2");
        // 查询
        Object name = stringRedisTemplate.opsForValue().get("name2");
        // 打印查看
        System.out.println("name = " + name);
    }




    @Test
    void testSaveUser() throws JsonProcessingException {
        // 创建对象
        User user = new User("yan", 18);

        // 手动序列化
        String json = mapper.writeValueAsString(user);
        stringRedisTemplate.opsForValue().set("user_json", json);

        // 获取数据（注意这里的 key 改成了 "user-json"）
        String val = stringRedisTemplate.opsForValue().get("user_json");

        // 反序列化
        User user1 = mapper.readValue(val, User.class);
        System.out.println(user1);
    }


}
