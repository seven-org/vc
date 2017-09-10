//package com.seven.virtual_currency_website_font.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.seven.virtual_currency_website.dao.mysql.VirtualCurrencyMysqlDao;
//
//@Controller
//public class BaseVirtualCurrencyController {
//	
//	@Autowired
//	private VirtualCurrencyMysqlDao virtualCurrencyMysqlDao;
//	
//	@RequestMapping("/home")
//	public String home(Model model){
//		model.addAttribute("vcs", virtualCurrencyMysqlDao.findAll());
//		return "home";
//	}
//
//}
