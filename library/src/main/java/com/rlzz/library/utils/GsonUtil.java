package com.rlzz.wms.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monty on 2017/11/7.
 */

public class GsonUtil {
    private Gson gson;

    private GsonUtil() {
        gson = new GsonBuilder().create();
    }

    public static GsonUtil getInstance() {
        return Holder.gsonUtil;
    }

    static class Holder {
        public static GsonUtil gsonUtil = new GsonUtil();
    }


    public String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public <T> List<T> toList(String json, Class<T> classes) {
        List<T> list = new ArrayList<>();
        if (!TextUtils.isEmpty(json) && JsonUtil.isJson(json)) {
            try {
                JsonArray asJsonArray = new JsonParser().parse(json).getAsJsonArray();
                for (JsonElement jsonElement : asJsonArray) {
                    list.add(gson.fromJson(jsonElement, classes));
                }
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public <T> List toList(String json, TypeToken<List<T>> typeToken) {
        List<T> list = new ArrayList<>();
        try {
            list = gson.fromJson(json, typeToken.getType());
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return list;
    }
}
