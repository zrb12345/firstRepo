package com.lamp.allUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author RollingStone-jianyijun
 * 通用枚举
 */
@Getter
@AllArgsConstructor
public enum CommonEnum {

	DEL_STATUS_0(0,"未删除"),
	DEL_STATUS_1(1,"已删除"),

	SHOW_STATUS_0(0,"未显示"),
	SHOW_STATUS_1(1,"显示"),

	GRADE_0(0,"一级用户");


	private Integer value;
	private String desc;


}
