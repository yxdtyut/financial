package com.yxdtyut.saller.rabbitmq;

import com.yxdtyut.entity.Product;
import com.yxdtyut.enums.ProductStatusEnum;
import com.yxdtyut.key.ProductKey;
import com.yxdtyut.rabbitmq.MQConfig;
import com.yxdtyut.rabbitmq.ProductMessage;
import com.yxdtyut.redis.RedisService;
import com.yxdtyut.saller.service.ProductRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class MQReceiver {

	@Autowired
	private RedisService redisService;

	@Autowired
	private ProductRpcService productRpcService;

	@RabbitListener(queues= MQConfig.STATUS_QUEUE)
	public void receive(ProductMessage pm) {
		log.info("saller端接收消息:{}", pm.toString());
		//清除缓存
		if (ProductStatusEnum.FINISHED.name().equals(pm.getStatus())) {
			log.info("产品停止销售");
			Boolean bo = redisService.del(ProductKey.id,pm.getId());
			log.info("删除产品，编号:{},是否成功:{}",pm.getId(),bo);
		} else if (ProductStatusEnum.IN_SELL.name().equals(pm.getStatus())) {
			log.info("产品销售中...");
			Product product = productRpcService.findOne(pm.getId());
			redisService.set(ProductKey.id, product.getId(), product);
			log.info("缓存添加产品:{}",product);
		}
	}

}
