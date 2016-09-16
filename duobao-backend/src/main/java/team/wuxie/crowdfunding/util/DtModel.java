/*
 * Copyright (c) 2016 智炎发展. All rights reserved.
 * @(#) DataTable2.java 2016-09-10 17:00
 */

package team.wuxie.crowdfunding.util;

import tk.mybatis.mapper.util.*;

import java.util.List;

/**
 * @author WuGang
 * @since 1.0
 */
public class DtModel {
    private int draw;
    private int start;
    private int length = 10;
    private DtSearch search;
    private List<DtColumns> columns;
    private List<DtOrder> order;

    public DtModel() {
    }

    public DtModel(int draw, int start, int length, DtSearch search, List<DtColumns> columns, List<DtOrder> order) {
        this.draw = draw;
        this.start = start;
        this.length = length;
        this.search = search;
        this.columns = columns;
        this.order = order;
    }

    public String getOrderBy() {
        String direction = getOrder().get(0).getDir();
        String column = getColumns().get(getOrder().get(0).getColumn()).getData();
        column = tk.mybatis.mapper.util.StringUtil.camelhumpToUnderline(column);
        return column + " " + direction;
    }

    public int getPageNum () {
        return start / length;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public DtSearch getSearch() {
        return search;
    }

    public void setSearch(DtSearch search) {
        this.search = search;
    }

    public List<DtColumns> getColumns() {
        return columns;
    }

    public void setColumns(List<DtColumns> columns) {
        this.columns = columns;
    }

    public List<DtOrder> getOrder() {
        return order;
    }

    public void setOrder(List<DtOrder> order) {
        this.order = order;
    }
}
