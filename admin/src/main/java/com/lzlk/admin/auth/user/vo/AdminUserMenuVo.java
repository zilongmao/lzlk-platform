package com.lzlk.admin.auth.user.vo;


import com.idol.admin.auth.role.vo.MenuVo;
import com.lzlk.admin.auth.role.vo.MenuVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 邻座旅客
 * @Description TODO
 * @Date 2019/7/16 12:03
 * @Created by 湖南达联
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserMenuVo implements Serializable {

    private AdminUserVo adminUserVo;


    private List<MenuVo> menuVoList;
}
