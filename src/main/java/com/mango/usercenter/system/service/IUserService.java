package com.mango.usercenter.system.service;

import com.mango.usercenter.system.domain.dto.messaging.UserAddBonusMsgDTO;
import com.mango.usercenter.system.domain.entity.User;

public interface IUserService {
    User findById(Integer id);

    void addBonus(UserAddBonusMsgDTO msgDTO);
}
