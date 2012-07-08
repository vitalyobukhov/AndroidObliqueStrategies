package com.vitalyobukhov.obliquestrategies;


public enum PreferenceTheme {
    DEFAULT,
    BLACK,
    WHITE;


    public static PreferenceTheme parse(String value) {
        return PreferenceTheme.valueOf(value);
    }
}
