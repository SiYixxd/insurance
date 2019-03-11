package com.wanghuan.common;

import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.net.URL;
        import java.net.URLConnection;
        import java.net.URLEncoder;
        import java.util.Random;
public class SmsCode{
    //提取手机号码生成的验证码 phoneNumber手机号码，code随机验证码
    public static boolean sendCode(String phoneNumber , String code )throws Exception{
        String code_Str = URLEncoder.encode("#code#="+code, "utf-8");
        System.out.println(code_Str);
        //准备URL对象，将接口包装在此对象中
        URL url = new URL("http://v.juhe.cn/sms/send?mobile="+phoneNumber+
                "&tpl_id=短信模板id编号&tpl_value="+code_Str+"&key=你的AppKey密钥");
        //打开对象
        URLConnection connection = url.openConnection();
        //向服务器发送连接请求
        connection.connect();
        //获得服务器响应的数据
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
        StringBuffer buffer = new StringBuffer();
        String lineData = null;
        while((lineData=bufferedReader.readLine())!=null) {
            buffer.append(lineData);
        }
        System.out.println(buffer);
        //关闭连接对象
        bufferedReader.close();
        if(buffer.toString().indexOf("\"erroe_code\":0")>=0) {
            return true;//当调用改方法时返回true短信就发送成功了
        }
        return false;
    }
    //获取随机验证码
    public static String getCode(){
        //开始生成随机数字 -- 验证码
        StringBuffer buffer = new StringBuffer();
        Random random = new Random(); //随机数字
        for(int i =0;i<6 ;i++) {
            //生成一个6位数的随机数
            buffer.append(random.nextInt(10));//范围0到10，不包括10 ；0-9
        }
        return buffer.toString();
    }

}