package com.yxdtyut.rpc.domain;

import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author : yangxudong
 * @Description :   产品rpc查询的相关参数
 * @Date : 下午5:00 2018/6/8
 */
@Data
public class ProductRpcDomain {
    private List idList;
    private BigDecimal minRewardRate;
    private BigDecimal maxRewardRate;
    private List statusList;
//    private Integer page;
//    private Integer pagesize;
//    private Sort.Direction sort;
//    private String orderBy;
}
