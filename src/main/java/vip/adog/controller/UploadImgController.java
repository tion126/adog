
package vip.adog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import vip.adog.common.JsonMsg;
import vip.adog.model.Photo;
import vip.adog.model.User;
import vip.adog.service.PhotoService;
import vip.adog.utils.ImageUtil;
import vip.adog.utils.StringUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

@Controller
public class UploadImgController {
    @Resource
    private PhotoService photoService;

    @PostMapping("/upload/uploadImg")
    @ResponseBody
    public JsonMsg uploadImg(HttpServletRequest request) {

        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if(multipartResolver.isMultipart(request)){
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            User u = (User) request.getSession().getAttribute("user_info");
            while(iter.hasNext()){
                //记录上传过程起始时的时间，用来计算上传时间
                int pre = (int) System.currentTimeMillis();
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if(file != null){
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if(myFileName.trim() !=""){
                        System.out.println(myFileName);
                        //重命名上传后的文件名
                        String fileName = u.getName() + StringUtil.genUnitCode("");
                        //定义上传路径
                        String path = "E:\\images\\photo\\"+fileName+StringUtil.getFileType(myFileName);
                        File localFile = new File(path);
                        try {
                            //保存到服务器
                            file.transferTo(localFile);
                            //大于100k的进行压缩
                            if(localFile.length()>300*1024){
                                //压缩图片
                                ImageUtil.resize(localFile, fileName, null, null);
                            }
                            //保存到数据库路径
                            Photo p = new Photo();
                            p.setTitle(u.getName()+"'s photos");
                            p.setImgPath(path);
                            p.setCreateTime(new Date());
                            p.setStatus(1);
                            p.setUserId(u.getId());
                            photoService.save(p);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                //记录上传该文件后的时间
                int finaltime = (int) System.currentTimeMillis();
                System.out.println(finaltime - pre);
            }

        }

        return new JsonMsg();
    }
}

