/*
 * Copyright (c) 2016 智炎发展. All rights reserved.
 * @(#) DbColumns.java 2016-09-10 17:05
 */

package team.wuxie.crowdfunding.util;

/**
 * @author WuGang
 * @since 1.0
 */
public class DtColumns {
    private String data;
    private String name;
    private boolean searchable;
    private boolean orderable;
    private DtSearch search;

    public DtColumns() {
    }

    public DtColumns(String data, String name, boolean searchable, boolean orderable, DtSearch search) {
        this.data = data;
        this.name = name;
        this.searchable = searchable;
        this.orderable = orderable;
        this.search = search;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public boolean isOrderable() {
        return orderable;
    }

    public void setOrderable(boolean orderable) {
        this.orderable = orderable;
    }

    public DtSearch getSearch() {
        return search;
    }

    public void setSearch(DtSearch search) {
        this.search = search;
    }
}
