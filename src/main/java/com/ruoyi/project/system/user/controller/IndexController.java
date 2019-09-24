package com.ruoyi.project.system.user.controller;

import java.util.List;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.post.domain.Post;
import com.ruoyi.project.system.post.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.system.menu.domain.Menu;
import com.ruoyi.project.system.menu.service.IMenuService;
import com.ruoyi.project.system.user.domain.User;

/**
 * 首页 业务处理
 * 
 * @author admin
 */
@Controller
public class IndexController extends BaseController
{
    @Autowired
    private IMenuService menuService;

    @Autowired
    private RuoYiConfig ruoYiConfig;
    @Autowired
    private IPostService postService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        User user = getSysUser();
        boolean printFlag = false;
        List<Post> postList = postService.selectPostsByUserId(user.getUserId());
        List<Post> userPostList  = postService.selectDiaoduPostById(user.getUserId());
        for(Post post: userPostList){
            if(post.getPostCode().equalsIgnoreCase(Constants.POST_CODE_BANGFANG)){
                printFlag = true;
            }
        }
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("printFlag", printFlag);//打印权限
        mmap.put("copyrightYear", ruoYiConfig.getCopyrightYear());
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", ruoYiConfig.getVersion());
        return "main";
    }
}
