package cn.dblearn.blog.common.util;

import java.util.HashMap;

/**
 * MapUtils
 *
 * @author com.wu
 * @date 2018/10/22 19:38
 * @email 312054439@qq.com
 * @description Map工具类
 */
public class MapUtils extends HashMap<String,Object> {

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key,value);
        return this;
    }
}
