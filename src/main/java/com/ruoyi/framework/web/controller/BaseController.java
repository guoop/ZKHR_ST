package com.ruoyi.framework.web.controller;

import java.beans.PropertyEditorSupport;
import java.util.*;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.DateUtil;
import com.ruoyi.common.utils.Md5Utils;
import com.ruoyi.project.locator.common.LocatorConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.domain.AjaxResult.Type;
import com.ruoyi.framework.web.page.PageDomain;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.page.TableSupport;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.web.client.RestTemplate;

/**
 * web层通用数据处理
 * 
 * @author admin
 */
public class BaseController
{

    @Autowired
    private RestTemplate restTemplate;
    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**获取定位器验证token值*/
    public  String getToken(){
        String  appid = Global.getConfig("locator.appid");
        String secret = Global.getConfig("locator.secret");
        Map m = new HashMap<String , Object >();
        m.put("appid",appid);
        m.put("time", DateUtil.getSecondTimestamp(new Date()));
        m.put("signature", Md5Utils.hash(Md5Utils.hash(secret)+m.get("time")));
        ResponseEntity<Map> result  = restTemplate.postForEntity(LocatorConstant.FKZX_API + LocatorConstant.AUTH,m,Map.class);
        if(result.getStatusCodeValue() == 200){
            return result.getBody().get("accessToken").toString();
        }
        return null;
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo errorTable(String msg)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setMsg(msg);
        rspData.setRows(new ArrayList<>());
        rspData.setTotal(new PageInfo(new ArrayList<>()).getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     * 
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? success() : error();
    }

    /**
     * 响应返回结果
     * 
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result)
    {
        return result ? success() : error();
    }

    /**
     * 返回成功
     */
    public AjaxResult success()
    {
        return AjaxResult.success();
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error()
    {
        return AjaxResult.error();
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(String message)
    {
        return AjaxResult.success(message);
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error(String message)
    {
        return AjaxResult.error(message);
    }

    /**
     * 返回错误码消息
     */
    public AjaxResult error(Type type, String message)
    {
        return new AjaxResult(type, message);
    }

    /**
     * 页面跳转
     */
    public String redirect(String url)
    {
        return StringUtils.format("redirect:{}", url);
    }

    public User getSysUser()
    {
        return ShiroUtils.getSysUser();
    }

    public void setSysUser(User user)
    {
        ShiroUtils.setSysUser(user);
    }

    public Long getUserId()
    {
        return getSysUser().getUserId();
    }

    public String getLoginName()
    {
        return getSysUser().getLoginName();
    }
}
