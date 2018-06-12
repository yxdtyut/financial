package com.yxdtyut.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class MQSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendStatusMessage(ProductMessage pm) {
		log.info("产品状态变更，发送消息:{}",pm.toString());
		rabbitTemplate.convertAndSend(MQConfig.STATUS_QUEUE, pm);
	}

}
