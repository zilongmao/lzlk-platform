package com.lzlk.mysql.manager.admin;


import com.lzlk.dao.mybatis.admin.bean.AdminRequestLogDo;

import java.util.List;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/6/24 20:54
 * @Created by 湖南达联
 */
public interface AdminRequestLogManager {

    /**
     * 添加日志
     * @param log
     */
    void addLog(AdminRequestLogDo log);

    /**
     * 查询后台日志
     * @param startTime
     * @param endTime
     * @param adminUserId
     * @param pageNo
     * @return
     */
    List<AdminRequestLogDo> findAdminLogByPage(Long startTime, Long endTime, Long adminUserId, Integer pageNo);
}
