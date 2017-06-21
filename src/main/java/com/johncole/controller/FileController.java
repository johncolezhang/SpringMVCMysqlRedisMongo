package com.johncole.controller;

import com.johncole.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by johncole on 2017/6/21.
 */
@RequestMapping("/file")
@Controller
public class FileController {
    @RequestMapping("/upload")
    public String upload(@RequestParam(value = "file" , required = false) MultipartFile file,
                         HttpServletRequest request, ModelMap modelMap) {
        Map<String, String> map =  new HashMap<String, String>();
        String path = request.getSession().getServletContext().getRealPath("upload");
        System.out.println("path:" + path);
        String fileName = file.getOriginalFilename();
        FileUtil.transMFtoFile(path, fileName,file);
        map.put("message", "path:" + path + ", filename:" +fileName);
        modelMap.addAllAttributes(map);
        return "message";
    }
}
