package com.heima;

import com.heima.redis.pojo.Person;

public class TestLombok {

    public static void main(String[] args) {
        Person person = new Person("name", "男", 19);
        System.out.println(person);

    }
}
