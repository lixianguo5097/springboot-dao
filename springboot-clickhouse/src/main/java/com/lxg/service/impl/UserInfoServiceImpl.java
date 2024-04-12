package com.lxg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxg.entity.UserInfo;
import com.lxg.mapper.UserInfoMapper;
import com.lxg.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: tonghp
 * @create: 2021/07/26 16:48
 */

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public void saveData(UserInfo userInfo) {
        save(userInfo);
    }

    @Override
    public UserInfo selectById(Integer id) {
        return userInfoMapper.selectById(id);
    }

    @Override
    public List<UserInfo> selectList() {
        return userInfoMapper.selectList();
    }

    @Override
    public int delById(int id) {
        return userInfoMapper.deleteById(id);
    }
}
