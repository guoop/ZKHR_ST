package com.ruoyi.framework.web.page;

import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.constant.Constants;
import org.yaml.snakeyaml.scanner.Constant;

/**
 * 表格数据处理
 * 
 * @author admin
 */
public class TableSupport
{
    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain()
    {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(Constants.PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));
        pageDomain.setNotCamelCase(Boolean.valueOf(ServletUtils.getParameter(Constants.NOT_CAMELCASE)));
        return pageDomain;
    }

    public static PageDomain buildPageRequest()
    {
        return getPageDomain();
    }

}
