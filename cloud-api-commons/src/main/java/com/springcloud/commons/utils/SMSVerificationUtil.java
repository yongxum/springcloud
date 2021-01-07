package com.springcloud.commons.utils;

import java.net.URLEncoder;

/**
 * 短信验证工具
 */
public class SMSVerificationUtil {

    private static String operation = "/distributor/sendSMS";

    private static String accountSid = Config.ACCOUNT_SID;
    private static String to = "15713688332";
    private static String smsContent = "【测试代码】登录验证码：{"+HttpUtil.runNumber()+"}，如非本人操作，请忽略此短信。";

    /**
     * 验证码通知短信
     */
    public static void execute() {
        String tmpSmsContent = null;
        try {
            tmpSmsContent = URLEncoder.encode(smsContent, "UTF-8");
        } catch (Exception e) {

        }
        String url = Config.BASE_URL + operation;
        String body = "accountSid=" + accountSid + "&to=" + to + "&smsContent=" + tmpSmsContent
                + HttpUtil.createCommonParam();

        // 提交请求
        String result = HttpUtil.post(url, body);
        System.out.println("result:" + System.lineSeparator() + result);
    }

    public static void main(String[] args) {
        execute();
    }
}
