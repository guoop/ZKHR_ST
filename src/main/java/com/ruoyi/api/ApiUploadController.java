package com.ruoyi.api;


import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.cars.driverCar.service.IDriverCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
public class ApiUploadController {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private IDriverCarService driverCarService;
    /**
     * app登陆接口
     * */
    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxResult upload(MultipartFile[] files) throws IOException {
        /**出门逻辑*/
        List<String> list = new ArrayList<>();
        if(files!=null&&files.length>0){
            for (MultipartFile file:files){
                list.add(FileUploadUtils.upload(file));
            }
        }
       return AjaxResult.success().put("urls",list);
    }
}
