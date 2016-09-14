/*
 * Copyright (c) 2016 智炎发展. All rights reserved.
 * @(#) DtSearch.java 2016-09-10 17:04
 */

package team.wuxie.crowdfunding.util;

/**
 * @author WuGang
 * @since 1.0
 */
public class DtSearch {
    private String value;
    private boolean regex;

    public DtSearch() {
    }

    public DtSearch(String value, boolean regex) {
        this.value = value;
        this.regex = regex;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean getRegex() {
        return regex;
    }

    public void setRegex(boolean regex) {
        this.regex = regex;
    }
}
