package vip.adog.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.ext.web.WebRenderExt;


public class BeetlConstants implements WebRenderExt{
    static long version = System.currentTimeMillis();
    


	@Override
	public void modify(Template template, GroupTemplate arg1, HttpServletRequest request,
			HttpServletResponse response) {
		//设置 web 路径
		template.binding("ctx", "http://localhost:9090/adog");
		/*template.binding(BaseConstants.REQUEST_CTX, ctx);
		//设置图片访问 路径
		template.binding(BaseConstants.REQUEST_IMG_PATH, ctx+PropKit.use("global.properties").get("image.view.base.path"));
		//设置图片服务器访问 路径
		template.binding(BaseConstants.REQUEST_IMG,  PropKit.use("global.properties").get("img.domain")+PropKit.use("global.properties").get("image.view.base.path"));
		//设置非图片访问路径
		template.binding(BaseConstants.REQUEST_NO_IMG_PATH, ctx+PropKit.use("global.properties").get("other.view.base.path"));*/


		
		
	}
}