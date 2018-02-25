package cn.wodesh.util;

import cn.wodesh.config.ResultInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TS on 2018/2/24.
 */
public class ResultUtil {

    /**
     * 返回消息模板
     * @param obj 返回数据
     * @return
     */
    public static Map<String , Object> success(Object obj){
        Map<String , Object> map = new HashMap<>();
        map.put("code" , ResultInfo.SUCCESS_CODE);
        map.put("msg" , ResultInfo.SUCCESS_MSG);
        map.put("data" , obj);
        return map;
    }


    /**
     * 返回无内容
     * @return
     */
    public static Map<String , Object> success(){
        Map<String , Object> map = new HashMap<>();
        map.put("code" , ResultInfo.SUCCESS_CODE);
        map.put("msg" , ResultInfo.SUCCESS_MSG);
        map.put("data" , "");
        return map;
    }

    /**
     * 定义返回码 返回信息
     * @param code
     * @param msg
     * @return
     */
    public static Map<String , Object> error(String code , String msg){
        Map<String , Object> map = new HashMap<>();
        map.put("code" , code);
        map.put("msg" , msg);
        map.put("data" , "");
        return map;
    }

}
