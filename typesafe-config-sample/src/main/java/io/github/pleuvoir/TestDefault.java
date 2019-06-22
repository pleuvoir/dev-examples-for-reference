package io.github.pleuvoir;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigValue;

import java.util.concurrent.TimeUnit;

public class TestDefault {

    public static void main(String[] args) throws InterruptedException {
        Config configInstance = ConfigFactory.load("broker.conf");

        Config user = configInstance.getObject("user").toConfig();

        System.out.println(user.getString("name"));
        System.out.println(user.getInt("age"));

    }
}
