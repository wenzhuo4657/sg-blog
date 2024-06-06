package org.example.domain;

public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200, "操作成功"),
    // 登录
    NEED_LOGIN(401, "需要登录后操作"),
    NO_OPERATOR_AUTH(403, "无权限操作"),
    SYSTEM_ERROR(500, "出现错误"),
    USERNAME_EXIST(501, "用户名已存在"),
    PHONENUMBER_EXIST(502, "手机号已存在"),
    EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    CONTENT_NOT_NULL(506, "发送的评论内容不能为空"),
    LOGIN_ERROR(505, "用户名或密码错误"), FileSize_NO(500,"文件大小合格" ),
    FileEnd_No(500,"文件后缀不符合规定" ), Username_not_NULL(500,"注册用户名为空" ), Email_not_NULL(200,"注册邮箱为空")
    ,Password_not_NULL(500,"密码为空");
    public static String Article_comment="0";//文章评论
    public static String Link_Comment="1";//友链评论
    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage) {
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}