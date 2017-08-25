package com.lay.shop.common.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController extends BaseController {

	@RequestMapping(value = "/errors/{errorCode}")
	public String errors(@PathVariable("errorCode") String errorCode) {
		return "errors/" + errorCode;
	}

}