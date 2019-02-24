package io.github.pleuvoir.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.github.pleuvoir.order.listener.InitListener;

@SpringBootApplication
public class OrderApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class,args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	@Bean
    public ServletListenerRegistrationBean<InitListener> servletListenerRegistrationBean() {
        ServletListenerRegistrationBean<InitListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<InitListener>();
        servletListenerRegistrationBean.setListener(new InitListener());
        return  servletListenerRegistrationBean;
    }
}
