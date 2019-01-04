package com.share.ControllerUtil;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author 牛自豪
 * @date 2018-12-31-15:28
 */
public class EmailPage extends Page {
    public EmailPage() {
    }

    public EmailPage(long pages) {
        this.pages = pages;
    }

    public EmailPage(long current, long size, long pages) {
        super(current, size);
        this.pages = pages;
    }

    public EmailPage(long current, long size, long total, long pages) {
        super(current, size, total);
        this.pages = pages;
    }

    public EmailPage(long current, long size, boolean isSearchCount, long pages) {
        super(current, size, isSearchCount);
        this.pages = pages;
    }

    public EmailPage(long current, long size, long total, boolean isSearchCount, long pages) {
        super(current, size, total, isSearchCount);
        this.pages = pages;
    }

    /**
     * 分页总页数
     */
    private long pages;

    @Override
    public long getPages() {
        if (this.getSize() == 0L) {
            return 0L;
        } else {
            pages = this.getTotal() / this.getSize();
            if (this.getTotal() % this.getSize() != 0L) {
                ++pages;
            }
            return pages;
        }
    }

    @Override
    public IPage setPages(long pages) {
        return this;
    }
}
