/**
 * @(#)JsonResponse.java, 2018-05-31.
 * <p>
 * Copyright 2018 Stalary.
 */
package response;

import java.util.LinkedHashMap;


/**
 * JsonResponse
 * Json格式的返回数据
 * {
 *     "success": true, // 是否为正常返回，前端可读取该方法来判断是否返回正常
 *     "code": 0, // 返回的状态码，默认为0，即正常
 *     "msg": "", // 返回的信息，可以返回错误信息等
 *     "data": { // 返回的数据
 *
 *     }
 * }
 * @author lirongqian
 * @since 2018/05/31
 */
public class JsonResponse extends LinkedHashMap<String, Object> {

    /**
     * 默认的序列化编码
     */
    private static final long serialVersionUID = 1L;

    /**
     * 返回成功
     * @return
     */
    public static JsonResponse success() {
        return success(null, null);
    }

    public static JsonResponse success(Object data) {
        return success(data, null);
    }

    public static JsonResponse success(Object data, String msg) {
        JsonResponse response = new JsonResponse();
        response.put("success", true);
        response.put("code", ResponseCode.success.getCode());
        response.put("msg", msg);
        response.put("data", data);
        return response;
    }

    /**
     * 返回错误
     * @return
     */
    public static JsonResponse fail() {
        return fail(null);
    }

    public static JsonResponse fail(String msg) {
        return fail(ResponseCode.fail.getCode(), msg);
    }

    public static JsonResponse fail(Integer errorCode, String msg) {
        JsonResponse response = new JsonResponse();
        response.put("success", false);
        response.put("code", errorCode);
        response.put("msg", msg);
        response.put("data", null);
        return response;
    }

    public static void main(String[] args) {
        System.out.println(JsonResponse.fail());
    }
}