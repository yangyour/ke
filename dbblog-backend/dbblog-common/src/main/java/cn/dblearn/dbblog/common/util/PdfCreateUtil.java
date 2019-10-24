//package cn.dblearn.dbblog.common.util;
//
//import java.io.File;
//
//import cn.dblearn.dbblog.common.Constant;
//import cn.dblearn.dbblog.common.support.pdf.component.PDFHeaderFooter;
//import cn.dblearn.dbblog.common.support.pdf.component.PDFKit;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
///**
//* <p>Title: PdfUtil</p>
//* <p>Description: </p>
//* @author YML
//* @date 2018年12月26日
//*/
//public class PdfCreateUtil {
//
//	private static final Logger logger = LoggerFactory.getLogger(PdfCreateUtil.class);
//
//	/**
//	 * <p>Title: createPDF</p>
//	 * <p>Description: </p>
//	 * @author YML
//	 * @param data 数据
//	 * @param fileName  生成pdf文件名称
//	 * @param templateName 模板名称(找resources下的资源例：contract/hello.ftl)
//	 * @return
//	 */
//	public static String createPDF(Object data, String fileName, String templateName){
//        //pdf保存路径
//        try {
//            //设置自定义PDF页眉页脚工具类
//            PDFHeaderFooter headerFooter=new PDFHeaderFooter();
//            PDFKit kit=new PDFKit();
//            kit.setHeaderFooterBuilder(headerFooter);
//            //设置输出路径
//            String tmpPath = System.getProperty("java.io.tmpdir") + File.separator + fileName;
//            logger.info("设置pdf输出路径："+tmpPath);
//            kit.setSaveFilePath(tmpPath);
//            String saveFilePath = kit.exportToFile(fileName, data, templateName);
//            logger.info("pdf输出路径："+saveFilePath);
//            return  saveFilePath;
//        } catch (Exception e) {
//        	e.printStackTrace();
//            return null;
//        }
//    }
//
//	/**
//	 * <p>Title: createPdfByFtlAbsolutePath</p>
//	 * <p>Description: </p>
//	 * @author YML
//	 * @param data 数据
//	 * @param fileName 生成pdf文件名称
//	 * @param templateAbsolutePath ftl模板绝对路径
//	 * @return
//	 */
//	public static String createPdfByFtlAbsolutePath(Object data, String fileName, String templateAbsolutePath){
//        //pdf保存路径
//        try {
//            //设置自定义PDF页眉页脚工具类
//            PDFHeaderFooter headerFooter=new PDFHeaderFooter();
//            PDFKit kit=new PDFKit();
//            kit.setHeaderFooterBuilder(headerFooter);
//            //设置输出路径
//            String tmpPath = System.getProperty("java.io.tmpdir") + File.separator + fileName;
//            logger.info("设置pdf输出路径："+tmpPath);
//            kit.setSaveFilePath(tmpPath);
//            String saveFilePath = kit.exportToFileByFtlAbsolutePath(fileName, data, templateAbsolutePath);
//            logger.info("pdf输出路径："+saveFilePath);
//            return  saveFilePath;
//        } catch (Exception e) {
//        	e.printStackTrace();
//            return null;
//        }
//    }
//
//	/**
//	 * <p>Title: createPDF</p>
//	 * <p>Description: </p>
//	 * @author YML
//	 * @param data 数据
//	 * @param fileName  生成pdf文件名称
//	 * @param templateName 模板内容
//	 * @return
//	 */
//	public static String createPDFByFtlContent(Object data, String fileName, String ftlContent){
//        //pdf保存路径
//        try {
//            //设置自定义PDF页眉页脚工具类
//            PDFHeaderFooter headerFooter=new PDFHeaderFooter();
//            PDFKit kit=new PDFKit();
//            kit.setHeaderFooterBuilder(headerFooter);
//            //设置输出路径
//            String uploadPath = Constant.UPLOAD_BASE_PATH + "/contract" + Constant.FILE_UPLOAD_PATH;
//            File destFile = new File(uploadPath);
//			if (!destFile.getParentFile().exists()) {
//				destFile.getParentFile().mkdirs();
//			}
//			String destPath = uploadPath + fileName;
//            String path = FreemarkerUtils.process(destPath, null);
//            //String tmpPath = System.getProperty("java.io.tmpdir") + File.separator + fileName;
//            logger.info("设置pdf输出路径："+path);
//            kit.setSaveFilePath(path);
//            String saveFilePath = kit.exportToFileByFtlContent(fileName, data, ftlContent);
//            logger.info("pdf输出路径："+saveFilePath);
//            return  saveFilePath;
//        } catch (Exception e) {
//        	e.printStackTrace();
//            return null;
//        }
//    }
//
//}
