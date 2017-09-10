//package com.seven.virtual_currency_website.component;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SendToMQComponent {
//	
//	@Autowired
//	private AmqpTemplate rabbitTemplate;
//	
//	public void send(String str){
//		this.rabbitTemplate.convertAndSend(str);
//	}
//
//}
