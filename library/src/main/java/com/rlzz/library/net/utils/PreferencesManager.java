package com.rlzz.library.net.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.rlzz.library.RLApplication;


/**
 * PreferencesUtils, easy to get or put data
 *
 * @author monty
 */
public class PreferencesManager {

    public static final String PREFERENCES_DEFAULT = "Preferences_Default";
    public static final String PREFERENCE_USER = "Preferences_User";


    protected static SharedPreferences settings =
            RLApplication.getInstance().getSharedPreferences(PREFERENCES_DEFAULT, Context.MODE_PRIVATE);

    private PreferencesManager() {
    }

    private static PreferencesManager instance;

    private static PreferencesManager getInstances() {
        if (instance == null) {
            synchronized (PreferencesManager.class) {
                if (instance == null) {
                    instance = new PreferencesManager();
                }
            }
        }
        return instance;
    }

    public static PreferencesManager getInstanceDefault() {
        settings = RLApplication.getInstance().getSharedPreferences(PREFERENCES_DEFAULT, Context.MODE_PRIVATE);
        return getInstances();
    }

    public static PreferencesManager getInstanceUser() {
        settings = RLApplication.getInstance().getSharedPreferences(PREFERENCE_USER, Context.MODE_PRIVATE);
        return getInstances();
    }

    /**
     * put string preferences
     *
     * @param key   The name of the preference to modify
     * @param value The new testQty for the preference
     * @return True if the new values were successfully written to persistent
     * storage.
     */
    public boolean putString(String key, String value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
        return true;
    }

    /**
     * get string preferences
     *
     * @param key The name of the preference to retrieve
     * @return The preference testQty if it exists, or null. Throws
     * ClassCastException if there is a preference with this name that
     * is not a string
     * @see #getString(String, String)
     */
    public String getString(String key) {
        return getString(key, null);
    }

    /**
     * get string preferences
     *
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference testQty if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a string
     */
    public String getString(String key, String defaultValue) {
        return settings.getString(key, defaultValue);
    }

    /**
     * put int preferences
     *
     * @param key   The name of the preference to modify
     * @param value The new testQty for the preference
     * @return True if the new values were successfully written to persistent
     * storage.
     */
    public boolean putInt(String key, int value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
        return true;
    }

    /**
     * get int preferences
     *
     * @param key The name of the preference to retrieve
     * @return The preference testQty if it exists, or -1. Throws
     * ClassCastException if there is a preference with this name that
     * is not a int
     * @see #getInt(String, int)
     */
    public int getInt(String key) {
        return getInt(key, -1);
    }

    /**
     * get int preferences
     *
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference testQty if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a int
     */
    public int getInt(String key, int defaultValue) {
        return settings.getInt(key, defaultValue);
    }

    /**
     * put long preferences
     *
     * @param key   The name of the preference to modify
     * @param value The new testQty for the preference
     * @return True if the new values were successfully written to persistent
     * storage.
     */
    public boolean putLong(String key, long value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        editor.apply();
        return true;
    }

    /**
     * get long preferences
     *
     * @param key The name of the preference to retrieve
     * @return The preference testQty if it exists, or -1. Throws
     * ClassCastException if there is a preference with this name that
     * is not a long
     * @see #getLong(String, long)
     */
    public long getLong(String key) {
        return getLong(key, -1);
    }

    /**
     * get long preferences
     *
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference testQty if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a long
     */
    public long getLong(String key, long defaultValue) {
        return settings.getLong(key, defaultValue);
    }

    /**
     * put float preferences
     *
     * @param key   The name of the preference to modify
     * @param value The new testQty for the preference
     * @return True if the new values were successfully written to persistent
     * storage.
     */
    public boolean putFloat(String key, float value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        editor.apply();
        return true;
    }

    /**
     * get float preferences
     *
     * @param key The name of the preference to retrieve
     * @return The preference testQty if it exists, or -1. Throws
     * ClassCastException if there is a preference with this name that
     * is not a float
     * @see #getFloat(String, float)
     */
    public float getFloat(String key) {
        return getFloat(key, -1);
    }

    /**
     * get float preferences
     *
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference testQty if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a float
     */
    public float getFloat(String key, float defaultValue) {
        return settings.getFloat(key, defaultValue);
    }

    /**
     * put boolean preferences
     *
     * @param key   The name of the preference to modify
     * @param value The new testQty for the preference
     * @return True if the new attr.xml were successfully written to persistent
     * storage.
     */
    public boolean putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
        return true;
    }

    public void putBooleanApply(String key, boolean value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * get boolean preferences, default is false
     *
     * @param key The name of the preference to retrieve
     * @return The preference testQty if it exists, or false. Throws
     * ClassCastException if there is a preference with this name that
     * is not a boolean
     * @see #getBoolean(String, boolean)
     */
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * get boolean preferences
     *
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference testQty if it exists, or defValue. Throws
     * ClassCastException if there is a preference with this name that
     * is not a boolean
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return settings.getBoolean(key, defaultValue);
    }
}