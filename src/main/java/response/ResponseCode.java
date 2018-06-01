package response;

import lombok.Getter;

/**
 * @author Stalary
 * @description 定义返回的状态码
 * @date 2018/5/31
 */
public enum ResponseCode {

    /**
     * 成功返回0
     */
    success(0, "成功"),

    /**
     * 失败返回-1，其他错误自行定义
     */
    fail(-1, "失败");

    @Getter
    private Integer code;

    @Getter
    private String msg;

    ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
