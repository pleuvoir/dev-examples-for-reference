package io.github.pleuvoir.normal;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShowTimeController {

	@RequestMapping("/time")
	public String normal() {
		return "showtime";
	}

	@RequestMapping(value = "/showTime", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(new Date());
	}
}
