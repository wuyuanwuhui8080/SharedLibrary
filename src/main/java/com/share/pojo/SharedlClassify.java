package com.share.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 论坛分类
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedlClassify extends Model<SharedlClassify> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 帖子分类名称
	 */
	private String classifyName;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
