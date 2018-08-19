package io.github.pleuvoir.controller;

import java.time.LocalDate;

import io.github.pleuvoir.payload.annotation.Controller;
import io.github.pleuvoir.payload.annotation.ReponseBody;
import io.github.pleuvoir.payload.annotation.RequestMapping;

@Controller
@RequestMapping("go")
public class GoController {

	@RequestMapping(value = "/")
	public @ReponseBody String go() {
		return LocalDate.now() +" 也要划水鸭";
	}
	
}
