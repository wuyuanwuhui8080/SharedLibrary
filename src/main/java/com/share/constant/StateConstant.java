package com.share.constant;

/**
 * 账号状态常量
 *
 * @author 博博
 * @Title: StateConstant
 * @ProjectName SharedLibrary
 * @time 2018/12/13 14:37
 */
public final class StateConstant {
    private StateConstant() {
    }

	// 账号正常
    public static final int NORMAL_USER = 1;
	// 冻结
    public static final int FREEZE_USER = 2;
	// 封号
    public static final int SETHONOR_USER = 3;
}
