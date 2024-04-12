package com.lxg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxg.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: tonghp
 * @create: 2021/07/26 16:46
 */
public interface UserInfoService extends IService<UserInfo> {
    // 写入数据
    void saveData (UserInfo userInfo) ;
    // ID 查询
    UserInfo selectById (@Param("id") Integer id) ;
    // 查询全部
    List<UserInfo> selectList () ;

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    int delById(int id);
}
