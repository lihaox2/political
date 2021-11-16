package com.bayee.political.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xxl
 * @date 2021/4/7
 */
@Data
public class JsonResult<T> {

    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码")
    private Integer code;

    /**
     * 信息 success or error
     */
    @ApiModelProperty(value = "信息")
    private String message;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String desc;

    /**
     * 数据响应
     */
    @ApiModelProperty(value = "数据集")
    private T data;

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    /**
     * 成功放回
     * @return
     */
    public static JsonResult ok() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(200);
        jsonResult.setMessage(SUCCESS);
        return jsonResult;
    }

    /**
     * 成功放回
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> JsonResult<T> ok(T data) {
        JsonResult<T> jsonResult = new JsonResult<>();
        jsonResult.setCode(200);
        jsonResult.setMessage(SUCCESS);
        jsonResult.setData(data);
        return jsonResult;
    }

    /**
     * 异常返回
     * @param message
     * @param data
     * @return
     */
    public static JsonResult error(String message, String data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(500);
        jsonResult.setMessage(ERROR);
        jsonResult.setDesc(message);
        jsonResult.setData(data);
        return jsonResult;
    }

    /**
     * 异常返回
     * @param message
     * @return
     */
    public static JsonResult error(String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(500);
        jsonResult.setMessage(ERROR);
        jsonResult.setDesc(message);
        return jsonResult;
    }

    /**
     * 自定义异常处理
     * @param handlerException
     * @return
     */
    public static JsonResult error(HandlerException handlerException) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(500);
        jsonResult.setMessage(ERROR);
        jsonResult.setDesc(handlerException.getMessage());
        return jsonResult;
    }

    /**
     * java异常
     * @param throwable
     * @return
     */
    public static JsonResult error(Throwable throwable) {
        JsonResult jsonResult = new JsonResult<>();
        jsonResult.setCode(ResultEnum.ERROR.getCode());
        jsonResult.setMessage(ERROR);
        jsonResult.setDesc(ResultEnum.ERROR.getDesc());
        return jsonResult;
    }

    /**
     * 响应枚举
     */
    private enum ResultEnum {
        /**
         * 500异常
         */
        ERROR(500, "系统异常");

        private Integer code;
        private String desc;

        ResultEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return this.code;
        }

        public String getDesc() {
            return this.desc;
        }
    }

}
