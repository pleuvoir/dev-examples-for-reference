package io.github.pleuvoir.template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import freemarker.template.Configuration;
import freemarker.template.Template;

@SuppressWarnings("all")
public class FreeMarkerUtil {
	

	/**
	 * 渲染Template文件.
	 */
	public static String renderTemplate(Template template, Object model) {
		try {
			StringWriter result = new StringWriter();
			template.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 创建默认配置，设定模板目录.
	 */
	public static Configuration buildConfiguration(String directory) throws IOException {
		Configuration cfg = new Configuration();
		Resource path = new DefaultResourceLoader().getResource(directory);
		cfg.setDirectoryForTemplateLoading(path.getFile());
		return cfg;
	}
	
	
	public static void saveFile(Object model, String dirPath, String fileName, String ftlName) throws IOException {
		String templateFolder =  "classpath:/templates";
		Configuration cfg = FreeMarkerUtil.buildConfiguration(templateFolder);
		Template template = cfg.getTemplate(ftlName);
		String result = FreeMarkerUtil.renderTemplate(template, model);
		String ROOTPATH = getClasspath();
		String dirName = ROOTPATH + dirPath;
		File dir = new File(dirName);
		dir.mkdirs();
		String pathname = dirName + File.separator + fileName;
		File file = new File(pathname);
		file.createNewFile();
		IOUtils.write(result, new FileOutputStream(file));
	}
	
	
	/**
	 * 获取classpath
	 */
	public static  String getClasspath(){
		String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../").replaceAll("file:/", "").replaceAll("%20", " ").trim();	
		try {
			path = URLDecoder.decode(path, "utf-8");
			if(path.indexOf(":") != 1){
				path = File.separator + path;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return path;
	}
	
}
