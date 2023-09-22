package com.mango.usercenter.system.controller;

import com.mango.usercenter.system.domain.dto.messaging.UserAddBonusMsgDTO;
import com.mango.usercenter.system.domain.dto.user.UserAddBonseDTO;
import com.mango.usercenter.system.domain.entity.User;
import com.mango.usercenter.system.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BonusController {
    private final IUserService userService;

    @PutMapping("/add-bonus")
    public User addBonus(@RequestBody UserAddBonseDTO userAddBonseDTO) {
        Integer userId = userAddBonseDTO.getUserId();
        userService.addBonus(
                UserAddBonusMsgDTO.builder()
                        .userId(userId)
                        .bonus(userAddBonseDTO.getBonus())
                        .description("兑换分享...")
                        .event("BUY")
                        .build()
        );
        return this.userService.findById(userId);
    }
}
