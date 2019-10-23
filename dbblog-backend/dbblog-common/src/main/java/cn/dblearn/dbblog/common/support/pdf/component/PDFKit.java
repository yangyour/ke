package cn.dblearn.dbblog.common.support.pdf.component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import cn.dblearn.dbblog.common.support.pdf.exception.PDFException;
import cn.dblearn.dbblog.common.support.pdf.util.FreeMarkerUtil;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class PDFKit {

    //PDF页眉、页脚定制工具
    private HeaderFooterBuilder headerFooterBuilder;

    //文件保存路径
    private String saveFilePath;

    /**
     * @description     导出pdf到文件
     * @param fileName  输出PDF文件名
     * @param data      模板所需要的数据
     * @param templateName 模板名称
     *
     */
    public String exportToFile(String fileName, Object data, String templateName){

        String htmlData = FreeMarkerUtil.getContent(data, templateName);
        if(StringUtils.isEmpty(saveFilePath)){
            saveFilePath = getDefaultSavePath(fileName);
        }
        File file=new File(saveFilePath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        FileOutputStream outputStream=null;
        try{
            //设置输出路径
            outputStream=new FileOutputStream(saveFilePath);
            //设置文档大小
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            //设置页眉页脚
            PDFBuilder builder = new PDFBuilder(headerFooterBuilder,data);
            builder.setPresentFontSize(10);
            writer.setPageEvent(builder);

            //输出为PDF文件
            convertToPDF(writer, document, htmlData);
        }catch(Exception ex){
        	ex.printStackTrace();
            throw new PDFException("PDF export to File fail",ex);
        }finally{
            IOUtils.closeQuietly(outputStream);
        }
        return saveFilePath;

    }

    /**
     * <p>Title: exportToFileByFtlContent</p>
     * <p>Description: 导出pdf到文件</p>
     * @author YML
     * @param fileName
     * @param data 数据
     * @param ftlContent fitl模板内容
     * @return 生成的pdf路径
     */
    public String exportToFileByFtlContent(String fileName, Object data, String ftlContent){
        String htmlData = FreeMarkerUtil.getContentByFtlContent(data, ftlContent);
        if(StringUtils.isEmpty(saveFilePath)){
            saveFilePath = getDefaultSavePath(fileName);
        }
        File file=new File(saveFilePath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        FileOutputStream outputStream=null;
        try{
            //设置输出路径
            outputStream=new FileOutputStream(saveFilePath);
            //设置文档大小
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            //设置页眉页脚
            PDFBuilder builder = new PDFBuilder(headerFooterBuilder,data);
            builder.setPresentFontSize(10);
            writer.setPageEvent(builder);

            //输出为PDF文件
            convertToPDF(writer, document, htmlData);
        }catch(Exception ex){
        	ex.printStackTrace();
            throw new PDFException("PDF export to File fail",ex);
        }finally{
            IOUtils.closeQuietly(outputStream);
        }
        return saveFilePath;

    }




    /**
     * 生成PDF到输出流中（ServletOutputStream用于下载PDF）
     * @param data 输入到FTL中的数据
     * @param response HttpServletResponse
     * @return
     */
    public  OutputStream exportToResponse(Object data,
                                                     HttpServletResponse response, String templateName){

        String html= FreeMarkerUtil.getContent(data, templateName);

        try{
            OutputStream out = null;
            ITextRenderer render = null;
            out = response.getOutputStream();
            //设置文档大小
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, out);
            //设置页眉页脚
            PDFBuilder builder = new PDFBuilder(headerFooterBuilder,data);
            writer.setPageEvent(builder);
            //输出为PDF文件
            convertToPDF(writer,document,html);
            return out;
        }catch (Exception ex){
            throw  new PDFException("PDF export to response fail",ex);
        }

    }

    /**
     * @description PDF文件生成
     */
    private  void convertToPDF(PdfWriter writer,Document document,String htmlString){
        //获取字体路径
        String fontPath=getFontPath();
        System.out.println(fontPath);
        document.open();
        try {
            XMLWorkerHelper.getInstance().parseXHtml(writer,document,
                    new ByteArrayInputStream(htmlString.getBytes("UTF-8")),
                    XMLWorkerHelper.class.getResourceAsStream("/default.css"),
                    Charset.forName("UTF-8"),new UnicodeFontFactory());
        } catch (IOException e) {
            e.printStackTrace();
            throw new PDFException("PDF文件生成异常",e);
        }finally {
            document.close();
        }

    }

    /**
     * @description 创建默认保存路径
     */
    private String getDefaultSavePath(String fileName){
        String classpath = PDFKit.class.getClassLoader().getResource("").getPath();
        String saveFilePath= classpath + "pdf/" + fileName;
        File f=new File(saveFilePath);
        if(!f.getParentFile().exists()){
            f.mkdirs();
        }
        return saveFilePath;
    }

    /**
     * @description 获取字体设置路径
     */
    public static String getFontPath() {
        String classpath=PDFKit.class.getClassLoader().getResource("").getPath();
        String fontpath=classpath+"fonts";
        return fontpath;
    }

    public HeaderFooterBuilder getHeaderFooterBuilder() {
        return headerFooterBuilder;
    }

    public void setHeaderFooterBuilder(HeaderFooterBuilder headerFooterBuilder) {
        this.headerFooterBuilder = headerFooterBuilder;
    }
    public String getSaveFilePath() {
        return saveFilePath;
    }

    public void setSaveFilePath(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

	/**
	 * <p>Title: exportToFileByFtlAbsolutePath</p>
	 * <p>Description: 导出pdf到文件</p>
	 * @author YML
	 * @param fileName输出PDF文件名
	 * @param data模板所需要的数据
	 * @param templateAbsolutePath  ftl模板绝对路径
	 * @return
	 */
	public String exportToFileByFtlAbsolutePath(String fileName, Object data,
			String templateAbsolutePath) {
		String htmlData = FreeMarkerUtil.getContentByTemplateAbsolutePath(data, templateAbsolutePath);
        if(StringUtils.isEmpty(saveFilePath)){
            saveFilePath = getDefaultSavePath(fileName);
        }
        File file=new File(saveFilePath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        FileOutputStream outputStream=null;
        try{
            //设置输出路径
            outputStream=new FileOutputStream(saveFilePath);
            //设置文档大小
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            //设置页眉页脚
            PDFBuilder builder = new PDFBuilder(headerFooterBuilder,data);
            builder.setPresentFontSize(10);
            writer.setPageEvent(builder);

            //输出为PDF文件
            convertToPDF(writer, document, htmlData);
        }catch(Exception ex){
        	ex.printStackTrace();
            throw new PDFException("PDF export to File fail",ex);
        }finally{
            IOUtils.closeQuietly(outputStream);
        }
        return saveFilePath;
	}

}
