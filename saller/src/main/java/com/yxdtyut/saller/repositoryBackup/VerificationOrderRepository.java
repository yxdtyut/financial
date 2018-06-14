package com.yxdtyut.saller.repositoryBackup;

import com.yxdtyut.entity.Order;
import com.yxdtyut.entity.VerificationOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @Author : yangxudong
 * @Description :   订单对账repository
 * @Date : 下午2:07 2018/6/12
 */

public interface VerificationOrderRepository extends JpaRepository<VerificationOrder,String>, JpaSpecificationExecutor<VerificationOrder> {
    /**
     * @Author : yangxudong
     * @Description : 查询某个时间段内[start,end)，渠道chanId对应的对账数据
     * @Date : 下午2:34 2018/6/13
     */
    @Query(value = "select concat_ws ('|',order_id,outer_order_id,chan_id,chan_user_id,product_id,order_type,amount,Date_format(create_at,'%Y-%m-%d %H:%i:%s')) \n" +
            "from `order_t` where order_status='success' and chan_id=?1 and create_at >= ?2 and create_at < ?3",
    nativeQuery = true)
    List<String> queryVerificationOrders(String chanId, Date start, Date end);

    /** 长款.*/
    @Query(value = "select t.order_id from order_t t left join verification_order v on t.chan_id=?1 and t.outer_order_id = v.order_id where v.order_id is null and t.create_at >= ?2 and t.create_at < ?3",
            nativeQuery = true)
    List<String> queryExcessOrder(String chanId, Date start, Date end);

    /** 漏单.*/
    @Query(value = "select t.order_id from verification_order v left join order_t t on v.chan_id=?1 and v.outer_order_id=t.order_id where t.order_id is null and v.create_at >= ?2 and v.create_at < ?3",
            nativeQuery = true)
    List<String> queryMissOrder(String chanId, Date start, Date end);

    /** 不一致.*/
    @Query(value = "select t.order_id from order_t t join verification_order v on t.chan_id=?1 and t.outer_order_id = v.order_id where \n" +
            "concat_ws ('|',t.chan_id,t.chan_user_id,t.product_id,t.order_type,t.amount,Date_format(t.create_at,'%Y-%m-%d %H:%i:%s')) !=\n" +
            "concat_ws ('|',v.chan_id,v.chan_user_id,v.product_id,v.order_type,v.amount,Date_format(v.create_at,'%Y-%m-%d %H:%i:%s')) and t.create_at >= ?2 and t.create_at < ?3",
            nativeQuery = true)
    List<String> queryDifferentOrder(String chanId, Date start, Date end);
}
