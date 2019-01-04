package com.share.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 好友请求状态了类
 * </p>
 *
 * @author 博博大人
 * @since 2019-01-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SharedlFriendRequestStatus
		extends Model<SharedlFriendRequestStatus> {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 1.同意，2.拒绝 0.未处理
	 */
	private String statusName;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
