package com.leigq.www.shiro.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@SuppressWarnings(value = "all")
public class Response {

	/**
	 * 默认成功响应码
	 */
	private static final Integer DEAFAULT_SUCCESS_CODE = HttpStatus.OK.value();
	/**
	 * 默认成功响应信息
	 */
	private static final String DEAFAULT_SUCCESS_MSG = "请求/处理成功！";
	/**
	 * 默认失败响应码
	 */
	private static final Integer DEAFAULT_FAILURE_CODE = HttpStatus.INTERNAL_SERVER_ERROR.value();
	/**
	 * 默认失败响应信息
	 */
	private static final String DEAFAULT_FAILURE_MSG = "请求/处理失败！";

	/**
	 * 处理结果代码，与 HTTP 状态响应码对应
	 */
	private Integer code;

	/**
	 * 处理结果信息
	 */
	private String msg;

	private Object data;

	/* ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓成功↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ */

	/**
	 * 处理成功响应，返回自定义响应码、信息和数据。
	 *
	 * @param msg 处理结果信息
	 * @return 响应对象
	 *         <p>
	 * @author ：LeiGQ <br>
	 * @date ：2019-05-20 15:25 <br>
	 */
	public Response success(String msg) {
		this.code = DEAFAULT_SUCCESS_CODE;
		this.msg = msg;
		this.data = null;
		return this;
	}

	/**
	 * 处理成功响应，返回自定义响应码、信息和数据。
	 *
	 * @param data 返回数据
	 * @return 响应对象
	 *         <p>
	 * @author ：LeiGQ <br>
	 * @date ：2019-05-20 15:25 <br>
	 */
	public Response success(Object data) {
		this.code = DEAFAULT_SUCCESS_CODE;
		this.msg = DEAFAULT_SUCCESS_MSG;
		this.data = data;
		return this;
	}

	/**
	 * 处理成功响应，返回自定义响应码、信息和数据。
	 *
	 * @param msg  处理结果信息
	 * @param data 返回数据
	 * @return 响应对象
	 *         <p>
	 * @author ：LeiGQ <br>
	 * @date ：2019-05-20 15:25 <br>
	 */
	public Response success(String msg, Object data) {
		this.code = DEAFAULT_SUCCESS_CODE;
		this.msg = msg;
		this.data = data;
		return this;
	}

	/**
	 * 处理成功响应，返回自定义响应码、信息和数据。
	 *
	 * @param httpStatus HTTP 响应码
	 * @param msg        处理结果信息
	 * @param data       返回数据
	 * @return 响应对象
	 *         <p>
	 * @author ：LeiGQ <br>
	 * @date ：2019-05-20 15:25 <br>
	 */
	public Response success(HttpStatus httpStatus, String msg, Object data) {
		this.code = httpStatus.value();
		this.msg = msg;
		this.data = data;
		return this;
	}

	/* ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓失败↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ */

	/**
	 * 处理失败响应，返回自定义响应码、信息和数据。
	 *
	 * @param msg 处理结果信息
	 * @return 响应对象
	 *         <p>
	 * @author ：LeiGQ <br>
	 * @date ：2019-05-20 15:22 <br>
	 */
	public Response failure(String msg) {
		this.code = DEAFAULT_FAILURE_CODE;
		this.msg = msg;
		this.data = null;
		return this;
	}

	/**
	 * 处理失败响应，返回自定义响应码、信息和数据。
	 *
	 * @param data 返回数据
	 * @return 响应对象
	 *         <p>
	 * @author ：LeiGQ <br>
	 * @date ：2019-05-20 15:22 <br>
	 */
	public Response failure(Object data) {
		this.code = DEAFAULT_FAILURE_CODE;
		this.msg = DEAFAULT_FAILURE_MSG;
		this.data = data;
		return this;
	}

	/**
	 * 处理失败响应，返回自定义响应码、信息和数据。
	 *
	 * @param msg  处理结果信息
	 * @param data 返回数据
	 * @return 响应对象
	 *         <p>
	 * @author ：LeiGQ <br>
	 * @date ：2019-05-20 15:22 <br>
	 */
	public Response failure(String msg, Object data) {
		this.code = DEAFAULT_FAILURE_CODE;
		this.msg = msg;
		this.data = data;
		return this;
	}

	/**
	 * 处理失败响应，返回自定义响应码、信息和数据。
	 *
	 * @param httpStatus HTTP 响应码
	 * @param msg        处理结果信息
	 * @param data       返回数据
	 * @return 响应对象
	 *         <p>
	 * @author ：LeiGQ <br>
	 * @date ：2019-05-20 15:22 <br>
	 */
	public Response failure(HttpStatus httpStatus, String msg, Object data) {
		this.code = httpStatus.value();
		this.msg = msg;
		this.data = data;
		return this;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
