package com.rlzz.library.net.callback;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;

/**
 * @author monty
 * @package com.rlzz.library.net.callback
 * @date 2018/3/27  下午2:29
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */

public abstract class JsonCallback<T> extends BaseCallback<T> {
    private Class<T> clazz;

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        ResponseBody body = response.body();
        if (null == body) return null;

        T data = null;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());

        Type genType = getClass().getGenericSuperclass();
        Type type = ((ParameterizedType)genType).getActualTypeArguments()[0];
        data = gson.fromJson(jsonReader,type);

        return data;
    }



}
