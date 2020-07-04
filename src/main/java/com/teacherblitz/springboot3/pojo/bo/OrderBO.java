package com.teacherblitz.springboot3.pojo.bo;

import lombok.Builder;
import lombok.Data;

/**
 * 订单业务传输对象
 *
 * @author: teacherblitz
 * @since: 2020/7/3
 */
@Data
@Builder
public class OrderBO {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 订单状态
     */
    private Integer status;
}
