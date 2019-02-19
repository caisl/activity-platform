package com.caisl.ap.activity.constant;

/**
 * Result
 *
 * @author caisl
 * @since 2019-01-22
 */
public class Result<T> {
    /**
     * 请求是否成功
     */
    private Boolean success;
    /**
     * 错误编码
     */
    private String resultCode;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 业务返回值
     */
    private T model;

    public Result(){

    }

    /**
     * 请求是否成功。
     *
     * @return 如果成功，则返回<code>true</code>
     */
    public boolean isSuccess() {
        return this.success;
    }

    /**
     * 设置请求成功标志。
     *
     * @param success 成功标志
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 当出现业务异常或系统异常时，返回相应的错误码
     *
     * @return 返回码
     */
    public String getResultCode() {
        return this.resultCode;
    }


    /**
     * 设置返回码
     *
     * @param code
     */
    public void setResultCode(String code) {
        this.resultCode = code;
    }

    /**
     * 取得model对象
     *
     * @return model对象
     */
    public T getModel() {
        return this.model;
    }

    /**
     * 设置model对象。
     *
     * @param model model对象
     */
    public void setModel(T model) {
        this.model = model;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
