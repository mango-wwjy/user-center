package com.mango.usercenter.system.service.impl;

import com.mango.usercenter.system.domain.dto.messaging.UserAddBonusMsgDTO;
import com.mango.usercenter.system.domain.entity.BonusEventLog;
import com.mango.usercenter.system.domain.entity.User;
import com.mango.usercenter.system.mapper.BonusEventLogMapper;
import com.mango.usercenter.system.mapper.UserMapper;
import com.mango.usercenter.system.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements IUserService {

    private final UserMapper userMapper;
    private final BonusEventLogMapper bonusEventLogMapper;

    @Override
    public User findById(Integer id) {
        return this.userMapper.selectById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addBonus(UserAddBonusMsgDTO msgDTO) {
        // 1. 为用户加积分
        Integer userId = msgDTO.getUserId();
        Integer bonus = msgDTO.getBonus();
        User user = this.userMapper.selectById(userId);

        user.setBonus(user.getBonus() + bonus);
        this.userMapper.updateById(user);

        // 2. 记录日志到bonus_event_log表里面
        this.bonusEventLogMapper.insert(
                BonusEventLog.builder()
                        .userId(userId)
                        .valueBonus(bonus)
                        .event(msgDTO.getEvent())
                        .createTime(new Date())
                        .description(msgDTO.getDescription())
                        .build()
        );
        log.info("积分添加完毕...");

    }
}
