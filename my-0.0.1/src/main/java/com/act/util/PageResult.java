package com.act.util;

import java.util.ArrayList;
import java.util.List;

public class PageResult {
    private List        result;
    private PageBuilder pageBuilder;

    public PageResult() {
        result        = new ArrayList();
        pageBuilder = new PageBuilder();
    }

    /**
     * @return Returns the result.
     */
    public List getResult() {
        return result;
    }

    /**
     * @param result The result to set.
     */
    public void setResult(List result) {
        this.result = result;
    }

    /**
     * @return Returns the pageBuilder.
     */
    public PageBuilder getPageBuilder() {
        return pageBuilder;
    }

    /**
     * @param pageBuilder The pageBuilder to set.
     */
    public void setPageBuilder(PageBuilder pageBuilder) {
        this.pageBuilder = pageBuilder;
    }

    public void setParameters(int total,int pge,int limit) {
        this.pageBuilder.items(total);
        this.pageBuilder.itemsPerPage(limit);
        this.pageBuilder.page(pge);
    }

    public int getStart() {
        return this.pageBuilder.offset();
    }

    public int getLimit() {
        return this.pageBuilder.length();
    }
}
