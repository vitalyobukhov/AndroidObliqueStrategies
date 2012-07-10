package com.vitalyobukhov.obliquestrategies;


public enum PreferenceTheme {
    DEFAULT,
    DARK,
    LIGHT;


    public static PreferenceTheme parse(String value) {
        return PreferenceTheme.valueOf(value);
    }
}
