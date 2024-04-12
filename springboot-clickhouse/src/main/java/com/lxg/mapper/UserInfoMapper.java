package com.lxg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxg.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: tonghp
 * @create: 2021/07/26 16:32
 */
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    // 写入数据
    void saveData (UserInfo userInfo) ;
    // ID 查询
    UserInfo selectById (@Param("id") Integer id) ;
    // 查询全部
    List<UserInfo> selectList () ;
}