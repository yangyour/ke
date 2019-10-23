package cn.dblearn.dbblog.common.warpper;

import java.io.Serializable;


/**
 * 响应消息类
 * @author developer001
 *
 */
public class MessageWarpper implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 状态
	 */
	public enum Status {

		/** 成功 */
		success,

		/** 错误 */
		error
	}

	/** 类型 */
	private Status status;

	/** 消息 */
	private String message;

	/**
	 * 初始化一个新创建的 Message 对象，使其表示一个空消息。
	 */
	public MessageWarpper() {

	}

	/**
	 * @param status
	 *            状态
	 */
	public MessageWarpper(Status status) {
		this.status = status;
	}

	/**
	 * @param status
	 *            状态
	 * @param message
	 *             消息
	 */
	public MessageWarpper(Status status, String message) {
		this.status = status;
		this.message = message;
	}

	/**
	 * 返回成功消息
	 *
	 * @param message
	 *           消息内容
	 * @return 成功消息
	 */
	public static MessageWarpper success() {
		return new MessageWarpper(Status.success);
	}

	/**
	 * 返回成功消息
	 *
	 * @param message
	 *           消息内容
	 * @return 成功消息
	 */
	public static MessageWarpper success(String message) {
		return new MessageWarpper(Status.success, message);
	}

	/**
	 * 返回错误消息
	 *
	 * @param message
	 *            消息内容
	 * @return 错误消息
	 */
	public static MessageWarpper error(String message) {
		return new MessageWarpper(Status.error, message);
	}

	/**
	 * 获取状态
	 *
	 * @return 状态
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * 设置类型
	 *
	 * @param status
	 *            状态
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * 获取消息内容
	 *
	 * @return 消息内容
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设置消息内容
	 *
	 * @param message
	 *            消息内容
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}
}
