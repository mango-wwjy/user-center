package com.mango.usercenter.system.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddBonseDTO {
    private Integer userId;
    /**
     * 积分
     */
    private Integer bonus;
}
