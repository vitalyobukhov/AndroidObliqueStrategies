package com.vitalyobukhov.obliquestrategies;


public enum PreferenceTextSize {
    DEFAULT,
    SMALL,
    MEDIUM,
    LARGE;


    public static PreferenceTextSize parse(String value) {
        return PreferenceTextSize.valueOf(value);
    }
}
