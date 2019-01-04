package com.share.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 好友请求表
 * </p>
 *
 * @author Bean
 * @since 2018-12-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharedlFriendRequest extends Model<SharedlFriendRequest> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;

	/**
	 * 被请求者的id
	 */
	private String meId;

	/**
	 * 请求者的id
	 */
	private String requestId;

	/**
	 * 请求状态 请求状态(2.同意，3.拒绝 1.未处理)
	 */
	private Integer status;

	/**
	 * 用来查询请求者 的基础信息
	 */
	@TableField(exist = false)
	private SharedUsers sharedUsers;

	/**
	 * 用来处理请求状态
	 */
	@TableField(exist = false)
	private SharedlFriendRequestStatus friendRequestStatus;

	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date creationDate;

	/**
	 * 修改时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date updateDate;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
