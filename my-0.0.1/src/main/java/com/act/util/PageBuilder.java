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
 * ��ҳ��.
 *
 * <p>���������webҳ���Ϸ�ҳ��ʾ��������, ����ҳ��͵�ǰҳ��ƫ����, ʮ�ַ���ʵ��.
 *
 * <p>����ֻ��Ҫ֪���ܹ��ж�����, ��ǰ��ʾÿ��ҳ, ÿҳ��ʾ����, �Ϳ��԰���������������,
 * ���ұ�֤���м��㶼�ó������ֵ, ���õ���ҳ�볬���߽�֮�������.
 *
 * <p>ʹ�÷�������:
 * <pre>
 *
 *   // ����һ����ҳ��, �����ڴ�ָ��ÿҳ��ʾ����, Ҳ�����Ժ���ָ��.
 *   // ���û��ָ��, ��ʹ��Ĭ��ֵÿҳ�����ʾ10��.
 *   PageBuilder pf = new PageBuilder();    // �� new PageBuilder(itemsPerPage);
 *
 *   // �������ܹ��м���. �����������С��0, �Ϳ���0.
 *   pf.items(123);
 *
 *   // �����֪���м���, ��������.
 *   pf.items(PageBuilder.UNKNOWN_ITEMS);
 *
 *   // ����Ĭ�ϵ�ǰҳ�ǵ�һҳ, ������Ըı���.
 *   pf.page(3);                            // ������ǰҳ����3��, ���õ���ҳ���ᳬ����ҳ��.
 *
 *   // ��������Եõ�����������.
 *   int currentPage = pf.page();           // �õ���ǰҳ
 *   int totalPages  = pf.pages();          // �ܹ��м�ҳ
 *   int totalItems  = pf.items();          // �ܹ��м���
 *   int beginIndex  = pf.beginIndex();     // �õ���ǰҳ��һ������(��1��ʼ����)
 *   int endIndex    = pf.endIndex();       // �õ���ǰҳ���һ������(Ҳ�Ǵ�1��ʼ��)
 *   int offset      = pf.offset();         // offset��length������Ϊmysql��ѯ���
 *   int length      = pf.length();         //     ��limit offset, length�Ӿ�.
 *
 *   // ������������.
 *   itemsPerPage(20);                      // ������ÿҳ��ʾ20����, ��ǰҳ��ֵ���Զ�����,
 *                                          //     ʹ��ҳ��ԭ����ҳ��ʾͬ������, �����û������Ի�.
 *   showItem(33);                          // �������԰�ҳ���������ʾ��33����(��0��ʼ����)����һҳ
 *
 *   // �߼�����, ��һ����������.  ���Ǿ���Ҫ��webҳ������ʾһ��������ҳ��, ���û�ѡ��.
 *   //        ____________________________________________________________
 *   // ����:  <<     <       3     4    5    6    [7]    8    9    >    >>
 *   //        ^      ^                             ^               ^    ^
 *   //       ��һҳ ǰһҳ                       ��ǰҳ          ��һҳ ���һҳ
 *   //
 *   // �������Ӿ���һ����СΪ7�Ļ�������, ��ǰҳ�뱻�����ܰ����м�, ���ǵ�ǰҳλ�ڿ�ͷ���β.
 *   // ʹ������ĵ���, �Ϳ��Եõ�ָ����С�Ļ��������е�ҳ������.
 *   Integer[] slider = pf.slider(7);
 *
 *   // ���������ж�ָ��ҳ���Ƿ���Ч, �����ǵ�ǰҳ.  ��Ч��ҳ����webҳ���ϲ���Ҫ����.
 *   if (pf.isDisabledPage(slider[i].intValue())) {
 *       show = "page " + slider[i];
 *   } else {
 *       show = "&lt;a href=#&gt; page " + slider[i] + " &lt;/a&gt;";
 *   }
 *
 *   // ����ֱ�Ӵ�ӡ��pf, ���ڵ��Գ���.
 *   System.out.println(pf);
 *   Log.info(pf.toString());
 *
 * </pre>
 *
 * @author Rainmanzhu
 * @version $Id: PageBuilder.java,v 1.1.1.1 2005/07/24 04:18:38 hugo Exp $
 */
public class PageBuilder {

    /** ÿҳĬ�ϵ�����(10). */
    public final static int DEFAULT_ITEMS_PER_PAGE = 10;

    /** ��������Ĭ�ϵĴ�С(7). */
    public final static int DEFAULT_SLIDER_SIZE = 7;

    /** ��ʾ����δ֪(<code>Integer.MAX_VALUE</code>). */
    public final static int UNKNOWN_ITEMS = Integer.MAX_VALUE;
    private int page;           // ��ǰҳ��. (1-based)
    private int items;          // �ܹ�����
    private int itemsPerPage;                   // ÿҳ����.

    /**
     * Ĭ�Ϲ��췽��.
     */
    public PageBuilder () {
        this(0);
    }

    /**
     * ���췽��, ָ��ÿҳ����.
     *
     * @param int   ÿҳ����.
     */
    public PageBuilder (int n) {
        page = 0;
        items = 0;
        itemsPerPage = (n > 0) ? n : DEFAULT_ITEMS_PER_PAGE;
    }

    /**
     * ȡ����ҳ��.
     *
     * @return int   ��ҳ��
     */
    public int pages () {
        return  (int)Math.ceil((double)items/itemsPerPage);
    }

    /**
     * ȡ�õ�ǰҳ.
     *
     * @return int   ��ǰҳ
     */
    public int page () {
        return  page;
    }

    /**
     * ���ò�ȡ�õ�ǰҳ.  ʵ�ʵĵ�ǰҳֵ��ȷ������ȷ�ķ�Χ��.
     *
     * @param int   ��ǰҳ
     * @return int   ���ú�ĵ�ǰҳ
     */
    public int page (int n) {
        return  (page = calcPage(n));
    }

    /**
     * ȡ��������.
     *
     * @return int   ������
     */
    public int items () {
        return  items;
    }

    /**
     * ���ò�ȡ��������.  ���ָ����������С��0, �򱻿���0.
     * �Զ�������ǰҳ, ȷ����ǰҳֵ����ȷ�ķ�Χ��.
     *
     * @param int   ������
     * @return int   �����Ժ��������
     */
    public int items (int n) {
        items = (n >= 0) ? n : 0;
        page(page);
        return  items;
    }

    /**
     * ȡ��ÿҳ����.
     *
     * @return int   ÿҳ����
     */
    public int itemsPerPage () {
        return  itemsPerPage;
    }

    /**
     * ���ò�ȡ��ÿҳ����.  ���ָ����ÿҳ����С�ڵ���0, ��ʹ��Ĭ��ֵ<code>DEFAULT_ITEMS_PER_PAGE</code>.
     * ��������ǰҳʹ֮�ڸı�ÿҳ����ǰ����ʾ��ͬ����.
     *
     * @param int   ÿҳ����
     * @return int   ���ú��ÿҳ����
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
     * ȡ�õ�ǰҳ��һ����ȫ�����е�ƫ���� (0-based).
     *
     * @return int   ƫ����
     */
    public int offset () {
        return  (page > 0) ? itemsPerPage*(page - 1) : 0;
    }

    /**
     * ȡ�õ�ǰҳ�ĳ���, ����ǰҳ��ʵ������. �൱��
     * <code>endIndex() - beginIndex() + 1</code>
     *
     * @return int   ��ǰҳ�ĳ���
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
     * ȡ�õ�ǰҳ��ʾ�������ʼ��� (1-based).
     *
     * @return int   ��ʼ���
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
     * ȡ�õ�ǰҳ��ʾ��ĩ����� (1-based).
     *
     * @return int   ĩ�����
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
     * ���õ�ǰҳ, ʹ֮��ʾָ��offset(0-based)����.
     *
     * @param int   Ҫ��ʾ�����ƫ����(0-based)
     * @return int   ָ�������ڵ�ҳ
     */
    public int showItem (int n) {
        return  page(n/itemsPerPage + 1);
    }

    /**
     * ȡ����ҳҳ��.
     *
     * @return int   ��ҳҳ��
     */
    public int firstPage () {
        return  calcPage(1);
    }

    /**
     * ȡ��ĩҳҳ��.
     *
     * @return int   ĩҳҳ��
     */
    public int lastPage () {
        return  calcPage(pages());
    }

    /**
     * ȡ��ǰһҳҳ��.
     *
     * @return int   ǰһҳҳ��
     */
    public int previousPage () {
        return  calcPage(page - 1);
    }

    /**
     * ȡ��ǰnҳҳ��
     *
     * @return int   ǰnҳҳ��
     */
    public int previousPage (int n) {
        return  calcPage(page - n);
    }

    /**
     * ȡ�ú�һҳҳ��.
     *
     * @return int   ��һҳҳ��
     */
    public int nextPage () {
        return  calcPage(page + 1);
    }

    /**
     * ȡ�ú�nҳҳ��.
     *
     * @return int   ��nҳҳ��
     */
    public int nextPage (int n) {
        return  calcPage(page + n);
    }

    /**
     * �ж�ָ��ҳ���Ƿ񱻽�ֹ, Ҳ����˵ָ��ҳ�볬���˷�Χ����ڵ�ǰҳ��.
     *
     * @return boolean   �Ƿ�Ϊ��ֹ��ҳ��
     */
    public boolean isDisabledPage (int n) {
        return  (n==0 || n > pages() || n == page);
    }

    public boolean isSeparator (int n) {
        return  n<0;
    }

    /**
     * ȡ��Ĭ�ϴ�С(<code>DEFAULT_SLIDER_SIZE</code>)��ҳ�뻬������, ������ǰҳ�����ܵط��ڻ������ڵ��м䲿λ.
     * �μ�{@link #slider(int n)}.
     *
     * @return Integer[]   ����ҳ�������
     */
    public Integer[] slider () {
        return  slider(DEFAULT_SLIDER_SIZE);
    }

    /**
     * ȡ��ָ����С��ҳ�뻬������, ������ǰҳ�����ܵط��ڻ������ڵ��м䲿λ.
     * ����: �ܹ���13ҳ, ��ǰҳ�ǵ�5ҳ, ȡ��һ����СΪ5�Ļ�������, ������
     * 3, 4, 5, 6, 7�⼸��ҳ��, ��5ҳ�������м�.  �����ǰҳ��12, �򷵻�ҳ��Ϊ
     * 9, 10, 11, 12, 13.
     *
     * @param int   �������ڴ�С
     * @return Integer[]   ����ҳ�������, ���ָ���������ڴ�СС��1����ҳ��Ϊ0, �򷵻ؿ�����.
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
     * ת�����ַ�����ʾ.
     *
     * @return String   �ַ�����ʾ.
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
     * ����ҳ��, �����ı䵱ǰҳ.
     *
     * @param int   ҳ��
     * @return int   ������ȷ��ҳ��(��֤������߽�)
     */
    protected int calcPage (int n) {
        int pages = pages();
        if (pages > 0) {
            return  (n < 1) ? 1 : (n > pages) ? pages : n;
        }
        return  0;
    }
}



