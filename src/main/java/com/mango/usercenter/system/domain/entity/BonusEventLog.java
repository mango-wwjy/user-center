package com.mango.usercenter.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("bonus_event_log")
public class BonusEventLog {
    /**
     * Id
     */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Integer id;

    /**
     * user.id
     */
    private Integer userId;

    /**
     * 积分操作值
     */

    private Integer valueBonus;

    /**
     * 发生的事件
     */
    private String event;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 描述
     */
    private String description;
}