package cn.dblearn.dbblog.common.support.pdf.util;

import com.google.common.collect.Maps;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import org.apache.commons.lang.StringUtils;
import cn.dblearn.dbblog.common.support.pdf.component.PDFKit;
import cn.dblearn.dbblog.common.support.pdf.exception.FreeMarkerException;
import cn.dblearn.dbblog.common.support.pdf.exception.PDFException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

/**
 * Created by fgm on 2017/4/22.
 * FREEMARKER 模板工具类
 */
public class FreeMarkerUtil {

	private static final Logger logger = LoggerFactory.getLogger(FreeMarkerUtil.class);

    private static final String WINDOWS_SPLIT = "\\";

    private static final String UTF_8="UTF-8";

    private static Map<String,FileTemplateLoader> fileTemplateLoaderCache=Maps.newConcurrentMap();

    private static  Map<String,Configuration> configurationCache= Maps.newConcurrentMap();

    public static Configuration getConfiguration(String templateFilePath){
        if(null!=configurationCache.get(templateFilePath)){
            return configurationCache.get(templateFilePath);
        }
        Configuration config = new Configuration(Configuration.VERSION_2_3_25);
        config.setDefaultEncoding(UTF_8);
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        config.setLogTemplateExceptions(false);
        FileTemplateLoader fileTemplateLoader=null;
        if(null!=fileTemplateLoaderCache.get(templateFilePath)){
            fileTemplateLoader=fileTemplateLoaderCache.get(templateFilePath);
        }
        try {
            fileTemplateLoader=new FileTemplateLoader(new File(templateFilePath));
            fileTemplateLoaderCache.put(templateFilePath,fileTemplateLoader);
        } catch (IOException e) {
            throw new FreeMarkerException("fileTemplateLoader init error!",e);
        }
        config.setTemplateLoader(fileTemplateLoader);
        configurationCache.put(templateFilePath,config);
        return config;

    }


    /**
     * @param templateName
     * @description 获取模板
     */
    public static String getContent(Object data, String templateName) {
        String templatePath = getPDFTemplatePath(templateName);
        String templateFileName = getTemplateName(templatePath);
        String templateFilePath = getTemplatePath(templatePath);
        if (StringUtils.isEmpty(templatePath)) {
            throw new FreeMarkerException("templatePath can not be empty!");
        }
        try {
            Template template = getConfiguration(templateFilePath).getTemplate(templateFileName);
            Configuration configuration = new Configuration();
            StringWriter writer = new StringWriter();
            template.process(data, writer);
            writer.flush();
            return  writer.toString();
        } catch (Exception ex) {
            throw new FreeMarkerException("FreeMarkerUtil process fail", ex);
        }
    }

    /**
     * <p>Title: getContentByFtlContent</p>
     * <p>Description: </p>
     * @author YML
     * @param data 数据
     * @param ftlContent ftl模板内容
     * @return
     */
    public static String getContentByFtlContent(Object data, String ftlContent) {
        if (StringUtils.isEmpty(ftlContent)) {
            throw new FreeMarkerException("ftlContent can not be empty!");
        }
        try {
    		Configuration config = new Configuration(Configuration.VERSION_2_3_25);
	        config.setDefaultEncoding(UTF_8);
	        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	        config.setLogTemplateExceptions(false);
    		StringWriter writer = new StringWriter();
    		Template template = new Template("contract", new StringReader(ftlContent), config);
            template.process(data, writer);
            writer.flush();
            return  writer.toString();
        } catch (Exception ex) {
            throw new FreeMarkerException("FreeMarkerUtil process fail", ex);
        }
    }

    /**
     * <p>Title: getContentByTemplateAbsolutePath</p>
     * <p>Description: </p>
     * @author YML
     * @param data
     * @param templateAbsolutePath 模板的绝对路径
     * @return
     */
    public static String getContentByTemplateAbsolutePath(Object data, String templateAbsolutePath) {
    	if (StringUtils.isEmpty(templateAbsolutePath)) {
    		throw new FreeMarkerException("templatePath can not be empty!");
    	}
        String templateFileName = getTemplateName(templateAbsolutePath);
        String templateFilePath = getTemplatePath(templateAbsolutePath);
        try {
            Template template = getConfiguration(templateFilePath).getTemplate(templateFileName);
            StringWriter writer = new StringWriter();
            template.process(data, writer);
            writer.flush();
            return  writer.toString();
        } catch (Exception ex) {
            throw new FreeMarkerException("FreeMarkerUtil process fail", ex);
        }
    }





    private static String getTemplatePath(String templatePath) {
        if (StringUtils.isEmpty(templatePath)) {
            return "";
        }
        if (templatePath.contains(WINDOWS_SPLIT)) {
            return templatePath.substring(0, templatePath.lastIndexOf(WINDOWS_SPLIT));
        }
        return templatePath.substring(0, templatePath.lastIndexOf("/"));
    }

    private static String getTemplateName(String templatePath) {
        if (StringUtils.isEmpty(templatePath)) {
            return "";
        }
        if (templatePath.contains(WINDOWS_SPLIT)) {
            return templatePath.substring(templatePath.lastIndexOf(WINDOWS_SPLIT) + 1);
        }
        return templatePath.substring(templatePath.lastIndexOf("/") + 1);
    }

    /**
     * @param fileName PDF文件名    (hello.pdf)
     * @param templateFtlName  (contract/hello.ftl)
     * @return 匹配到的模板名
     * @description 获取PDF的模板绝对路径,
     * 默认按照PDF文件名匹对应模板
     */
    public static String getPDFTemplatePath(String templateFtlName) {
    	logger.info("templateFtlName:"+templateFtlName);
        String classpath = PDFKit.class.getClassLoader().getResource("").getPath();
        logger.info("classpath:"+classpath);
        String templatePath = classpath + templateFtlName;
        logger.info("templatePath:"+templatePath);
        File templateFile = new File(templatePath);
        if (!templateFile.isFile()) {
            throw new PDFException("PDF模板文件不存在,请检查templates文件夹!");
        }else {
        	return templateFile.getAbsolutePath();
		}
    }


}
