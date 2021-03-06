package com.zl.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author junqi
 * 系统通知表
 */
public class SysInfo implements Serializable {
    /**
     * 转入账号
     */
    private String accIn;
    /**
     * 转入用户名
     */
    private String accInName;
    /**
     * 支付金额
     */
    private BigDecimal transFund;
    /**
     * 到账时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dealDate;
}
