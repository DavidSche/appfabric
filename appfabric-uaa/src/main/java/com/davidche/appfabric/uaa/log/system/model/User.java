package com.davidche.appfabric.uaa.log.system.model;

import lombok.Data;

import java.util.Date;

/**
 *
 */
@Data
public class User {
    private static final long serialVersionUID = 1L;

    private String id; // 工号
    private String username; // 账号
    private String name; // 姓名
    private String password; // 密码
    private String sex; // 性别
    private String email; // 邮箱
    private String phone; // 手机
    private Boolean locked; // 是否被锁定
    private String organizationId;// 部门ID仅用户接受参数
    private String loginIp; // 最后登入IP
    private String loginDate; // 最后登入日期
    private String photo; // 头像

    private String oldLoginIp; // 上次登入IP
    private String oldLoginDate;// 上次登入日期

    private String createBy;	//创建者ID
    private String updateBy;	//更新者ID
    private Date createDate;	// 创建日期

    private Date updateDate;	// 更新日期
}
