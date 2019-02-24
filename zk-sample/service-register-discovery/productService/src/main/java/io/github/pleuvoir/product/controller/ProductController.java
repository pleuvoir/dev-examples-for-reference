package io.github.pleuvoir.product.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.pleuvoir.product.pojo.Product;

@RequestMapping("/product")
@RestController
public class ProductController {

	@RequestMapping("/getProduct/{id}")
	public Object getProduct(HttpServletRequest request, @PathVariable("id") String id) {
		int localPort = request.getLocalPort();
		return new Product(id, "productname:" + localPort);
	}
}
