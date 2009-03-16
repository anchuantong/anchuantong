package com.act.util;

/*
 *  ----------------------------------------------------------------------------------------------------------
 *  $Id: PageBuilder.java,v 1.1.1.1 2005/07/24 04:18:38 hugo Exp $
 *  $Source: H:/cvsroot/./dxycms/webapps/WEB-INF/src/java/org/jute/util/context/PageBuilder.java,v $
 *  ----------------------------------------------------------------------------------------------------------
 */

import java.util.ArrayList;
import java.util.List;


/**
 * 翻页器.
 *
 * <p>此类对于在web页面上分页显示多项内容, 计算页码和当前页的偏移量, 十分方便实用.
 *
 * <p>该类只需要知道总共有多少项, 当前显示每几页, 每页显示几项, 就可以帮你计算出其它数据,
 * 而且保证所有计算都得出合理的值, 不用担心页码超出边界之类的问题.
 *
 * <p>使用方法如下:
 * <pre>
 *
 *   // 创建一个翻页器, 可以在此指定每页显示几项, 也可以以后再指定.
 *   // 如果没有指定, 则使用默认值每页最多显示10项.
 *   PageBuilder pf = new PageBuilder();    // 或 new PageBuilder(itemsPerPage);
 *
 *   // 告诉我总共有几项. 如果给的数字小于0, 就看作0.
 *   pf.items(123);
 *
 *   // 如果不知道有几项, 可以这样.
 *   pf.items(PageBuilder.UNKNOWN_ITEMS);
 *
 *   // 现在默认当前页是第一页, 但你可以改变它.
 *   pf.page(3);                            // 这样当前页就是3了, 不用担心页数会超过总页数.
 *
 *   // 现在你可以得到各种数据了.
 *   int currentPage = pf.page();           // 得到当前页
 *   int totalPages  = pf.pages();          // 总共有几页
 *   int totalItems  = pf.items();          // 总共有几项
 *   int beginIndex  = pf.beginIndex();     // 得到当前页第一项的序号(从1开始数的)
 *   int endIndex    = pf.endIndex();       // 得到当前页最后一项的序号(也是从1开始数)
 *   int offset      = pf.offset();         // offset和length可以作为mysql查询语句
 *   int length      = pf.length();         //     的limit offset, length子句.
 *
 *   // 还可以做调整.
 *   itemsPerPage(20);                      // 这样就每页显示20个了, 当前页的值会自动调整,
 *                                          //     使新页和原来的页显示同样的项, 这样用户不易迷惑.
 *   showItem(33);                          // 这样可以把页码调整到显示第33号项(从0开始计数)的那一页
 *
 *   // 高级功能, 开一个滑动窗口.  我们经常要在web页面上显示一串的相邻页码, 供用户选择.
 *   //        ____________________________________________________________
 *   // 例如:  <<     <       3     4    5    6    [7]    8    9    >    >>
 *   //        ^      ^                             ^               ^    ^
 *   //       第一页 前一页                       当前页          后一页 最后一页
 *   //
 *   // 以上例子就是一个大小为7的滑动窗口, 当前页码被尽可能摆在中间, 除非当前页位于开头或结尾.
 *   // 使用下面的调用, 就可以得到指定大小的滑动窗口中的页码数组.
 *   Integer[] slider = pf.slider(7);
 *
 *   // 这样可以判断指定页码是否有效, 或者是当前页.  无效的页码在web页面上不需要链接.
 *   if (pf.isDisabledPage(slider[i].intValue())) {
 *       show = "page " + slider[i];
 *   } else {
 *       show = "&lt;a href=#&gt; page " + slider[i] + " &lt;/a&gt;";
 *   }
 *
 *   // 可以直接打印出pf, 用于调试程序.
 *   System.out.println(pf);
 *   Log.info(pf.toString());
 *
 * </pre>
 *
 * @author Rainmanzhu
 * @version $Id: PageBuilder.java,v 1.1.1.1 2005/07/24 04:18:38 hugo Exp $
 */
public class PageBuilder {

    /** 每页默认的项数(10). */
    public final static int DEFAULT_ITEMS_PER_PAGE = 10;

    /** 滑动窗口默认的大小(7). */
    public final static int DEFAULT_SLIDER_SIZE = 7;

    /** 表示项数未知(<code>Integer.MAX_VALUE</code>). */
    public final static int UNKNOWN_ITEMS = Integer.MAX_VALUE;
    private int page;           // 当前页码. (1-based)
    private int items;          // 总共项数
    private int itemsPerPage;                   // 每页项数.

    /**
     * 默认构造方法.
     */
    public PageBuilder () {
        this(0);
    }

    /**
     * 构造方法, 指定每页项数.
     *
     * @param int   每页项数.
     */
    public PageBuilder (int n) {
        page = 0;
        items = 0;
        itemsPerPage = (n > 0) ? n : DEFAULT_ITEMS_PER_PAGE;
    }

    /**
     * 取得总页数.
     *
     * @return int   总页数
     */
    public int pages () {
        return  (int)Math.ceil((double)items/itemsPerPage);
    }

    /**
     * 取得当前页.
     *
     * @return int   当前页
     */
    public int page () {
        return  page;
    }

    /**
     * 设置并取得当前页.  实际的当前页值被确保在正确的范围内.
     *
     * @param int   当前页
     * @return int   设置后的当前页
     */
    public int page (int n) {
        return  (page = calcPage(n));
    }

    /**
     * 取得总项数.
     *
     * @return int   总项数
     */
    public int items () {
        return  items;
    }

    /**
     * 设置并取得总项数.  如果指定的总项数小于0, 则被看作0.
     * 自动调整当前页, 确保当前页值在正确的范围内.
     *
     * @param int   总项数
     * @return int   设置以后的总项数
     */
    public int items (int n) {
        items = (n >= 0) ? n : 0;
        page(page);
        return  items;
    }

    /**
     * 取得每页项数.
     *
     * @return int   每页项数
     */
    public int itemsPerPage () {
        return  itemsPerPage;
    }

    /**
     * 设置并取得每页项数.  如果指定的每页项数小于等于0, 则使用默认值<code>DEFAULT_ITEMS_PER_PAGE</code>.
     * 并调整当前页使之在改变每页项数前后显示相同的项.
     *
     * @param int   每页项数
     * @return int   设置后的每页项数
     */
    public int itemsPerPage (int n) {
        int tmp = itemsPerPage;
        itemsPerPage = (n > 0) ? n : DEFAULT_ITEMS_PER_PAGE;
        if (page > 0) {
            page((int)((double)(page - 1)*tmp/itemsPerPage) + 1);
        }
        return  itemsPerPage;
    }

    /**
     * 取得当前页第一项在全部项中的偏移量 (0-based).
     *
     * @return int   偏移量
     */
    public int offset () {
        return  (page > 0) ? itemsPerPage*(page - 1) : 0;
    }

    /**
     * 取得当前页的长度, 即当前页的实际项数. 相当于
     * <code>endIndex() - beginIndex() + 1</code>
     *
     * @return int   当前页的长度
     */
    public int length () {
        if (page > 0) {
            return  Math.min(itemsPerPage*page, items) - itemsPerPage*(page - 1);
        } 
        else {
            return  0;
        }
    }

    /**
     * 取得当前页显示的项的起始序号 (1-based).
     *
     * @return int   起始序号
     */
    public int beginIndex () {
        if (page > 0) {
            return  itemsPerPage*(page - 1) + 1;
        } 
        else {
            return  0;
        }
    }

    /**
     * 取得当前页显示的末项序号 (1-based).
     *
     * @return int   末项序号
     */
    public int endIndex () {
        if (page > 0) {
            return  Math.min(itemsPerPage*page, items);
        } 
        else {
            return  0;
        }
    }

    /**
     * 设置当前页, 使之显示指定offset(0-based)的项.
     *
     * @param int   要显示的项的偏移量(0-based)
     * @return int   指定项所在的页
     */
    public int showItem (int n) {
        return  page(n/itemsPerPage + 1);
    }

    /**
     * 取得首页页码.
     *
     * @return int   首页页码
     */
    public int firstPage () {
        return  calcPage(1);
    }

    /**
     * 取得末页页码.
     *
     * @return int   末页页码
     */
    public int lastPage () {
        return  calcPage(pages());
    }

    /**
     * 取得前一页页码.
     *
     * @return int   前一页页码
     */
    public int previousPage () {
        return  calcPage(page - 1);
    }

    /**
     * 取得前n页页码
     *
     * @return int   前n页页码
     */
    public int previousPage (int n) {
        return  calcPage(page - n);
    }

    /**
     * 取得后一页页码.
     *
     * @return int   后一页页码
     */
    public int nextPage () {
        return  calcPage(page + 1);
    }

    /**
     * 取得后n页页码.
     *
     * @return int   后n页页码
     */
    public int nextPage (int n) {
        return  calcPage(page + n);
    }

    /**
     * 判断指定页码是否被禁止, 也就是说指定页码超出了范围或等于当前页码.
     *
     * @return boolean   是否为禁止的页码
     */
    public boolean isDisabledPage (int n) {
        return  (n==0 || n > pages() || n == page);
    }

    public boolean isSeparator (int n) {
        return  n<0;
    }

    /**
     * 取得默认大小(<code>DEFAULT_SLIDER_SIZE</code>)的页码滑动窗口, 并将当前页尽可能地放在滑动窗口的中间部位.
     * 参见{@link #slider(int n)}.
     *
     * @return Integer[]   包含页码的数组
     */
    public Integer[] slider () {
        return  slider(DEFAULT_SLIDER_SIZE);
    }

    /**
     * 取得指定大小的页码滑动窗口, 并将当前页尽可能地放在滑动窗口的中间部位.
     * 例如: 总共有13页, 当前页是第5页, 取得一个大小为5的滑动窗口, 将包括
     * 3, 4, 5, 6, 7这几个页码, 第5页被放在中间.  如果当前页是12, 则返回页码为
     * 9, 10, 11, 12, 13.
     *
     * @param int   滑动窗口大小
     * @return Integer[]   包含页码的数组, 如果指定滑动窗口大小小于1或总页数为0, 则返回空数组.
     */
    public Integer[] slider (int n) {
        int pages = pages();
        if (pages < 1 || n < 1) {
            return  new Integer[0];
        } 
        else {
            if (n > pages) {
                n = pages;
            }
            Integer[] slider = new Integer[n];
            int first = page - (n - 1)/2;
            if (first < 1) {
                first = 1;
            }
            if (first + n - 1 > pages) {
                first = pages - n + 1;
            }
            for (int i = 0; i < n; i++) {
                slider[i] = new Integer(first + i);
            }
            return  slider;
        }
    }

    public Integer[] slider2() {
        int pages = pages();
        if (pages <= 1) {
            return  new Integer[0];
        }
        else {
            int onPage = page();
            List list = new ArrayList();
            if(pages>10) {
                int initPageMax = 3;
                for(int i=1;i<=initPageMax;i++) {
                    list.add(new Integer(i));
                }
                if(onPage>1 && onPage<pages) {
                    if(onPage>5) {
                        list.add(new Integer(-1));
                    }
                    int initPageMin = (onPage>4)?onPage:5;
                    initPageMax = (onPage<pages-4)?onPage:pages-4;
                    for(int i=initPageMin-1;i<initPageMax+2;i++) {
                        list.add(new Integer(i));
                    }
                    if(onPage<pages-4) {
                        list.add(new Integer(-1));
                    }
                }
                else {
                    list.add(new Integer(-1));
                }
                for(int i=pages-2;i<pages+1;i++) {
                    list.add(new Integer(i));
                }
            }
            else {
                for(int i=1;i<pages+1;i++) {
                    list.add(new Integer(i));
                }
            }
            return (Integer[]) list.toArray(new Integer[list.size()]);
        }
    }

    public boolean hasNextPage(Integer[] pages, int index) {
        return (index<pages.length-1) && pages[index+1].intValue()!=-1;
    }
    /**
     * 转换成字符串表示.
     *
     * @return String   字符串表示.
     */
    public String toString () {
        StringBuffer sb = new StringBuffer("PageBuilder: page ");
        if (pages() < 1) {
            sb.append(page());
        } 
        else {
            Integer[] slider = slider();
            for (int i = 0; i < slider.length; i++) {
                if (isDisabledPage(slider[i].intValue())) {
                    sb.append('[').append(slider[i]).append(']');
                } 
                else {
                    sb.append(slider[i]);
                }
                if (i < slider.length - 1) {
                    sb.append('\t');
                }
            }
        }
        sb.append(" of ").append(pages()).append(",\n");
        sb.append("    Showing items ").append(beginIndex()).append(" to ").append(endIndex()).append(" (total ").append(items()).append(" items), ");
        sb.append("offset=").append(offset()).append(", length=").append(length());
        return  sb.toString();
    }

    /**
     * 计算页数, 但不改变当前页.
     *
     * @param int   页码
     * @return int   返回正确的页码(保证不会出边界)
     */
    protected int calcPage (int n) {
        int pages = pages();
        if (pages > 0) {
            return  (n < 1) ? 1 : (n > pages) ? pages : n;
        }
        return  0;
    }
}



