package com.mustache.bbs1.controller.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "")
@RequiredArgsConstructor
@Slf4j
public class IndexController {

	//메인페이지
	@GetMapping(value = "")
	public String main() {
		return "index/main";
	}

}
