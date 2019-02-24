package io.github.pleuvoir.order.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.pleuvoir.order.pojo.Order;
import io.github.pleuvoir.order.pojo.Product;
import io.github.pleuvoir.order.utils.LoadBalance;
import io.github.pleuvoir.order.utils.RamdomLoadBalance;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Resource
	private RestTemplate restTemplate;

	private LoadBalance loadBalance = new RamdomLoadBalance();

	@RequestMapping("/getOrder/{id}")
	public Object getProduct(HttpServletRequest request, @PathVariable("id") String id) {
		String host = loadBalance.choseServiceHost();
		if(host.equals("")){
			System.err.println("服务不可用");
			return "服务不可用";
		}
		Product product = restTemplate.getForObject("http://" + host + "/product/getProduct/1", Product.class);
		return new Order(id, "ordername", product);
	}
}
