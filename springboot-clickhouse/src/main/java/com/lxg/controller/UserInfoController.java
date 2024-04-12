package com.lxg.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lxg.entity.UserInfo;
import com.lxg.service.UserInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @create: 2021/07/26 16:45
 */
@RestController
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService ;

    //localhost:7010/saveData?id=1
    @GetMapping("/saveData")
    public String saveData (int id){
        UserInfo userInfo = new UserInfo () ;
        userInfo.setId(id);
        userInfo.setUserName("xiaolin");
        userInfo.setPassWord("54321");
        userInfo.setPhone("18500909876");
        userInfo.setCreateDay("2022-02-06");
        LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserInfo::getId, id);
        userInfoService.saveOrUpdate(userInfo,wrapper);
        return "success";
    }

    //localhost:7010/getById?id=1
    @GetMapping("/getById")
    public UserInfo getById (int id) {
        return userInfoService.selectById(id) ;
    }

    @GetMapping("/getList")
    public List<UserInfo> getList () {
        return userInfoService.selectList() ;
    }
    @GetMapping("/delById")
    public int delById (int id) {
        return userInfoService.delById(id) ;
    }

}
