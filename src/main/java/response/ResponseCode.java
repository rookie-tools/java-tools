package response;

import lombok.Getter;

/**
 * @author Stalary
 * @description
 * @date 2018/5/31
 */
public enum ResponseCode {

    success(0, "成功");

    @Getter
    private Integer code;

    @Getter
    private String msg;

    ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
