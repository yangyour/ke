package cn.dblearn.dbblog.common.support.pdf.util;


import java.io.FileOutputStream;

import javax.swing.JLabel;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/**
* <p>Title: WaterMark</p>
* <p>Description: 文件添加水印</p>
* @author YML
* @date 2019年1月16日
*/
public class WaterMark {

	private static final Logger logger = LoggerFactory.getLogger(WaterMark.class);

	private static int interval = -5;

	/**
	 * <p>Title: waterMark</p>
	 * <p>Description: 给pdf文件添加文字及图片水印，文字加在pdf内容下方，图片加载pdf内容上方</p>
	 * @author YML
	 * @param inputFile pdf源文件路径
	 * @param outputFile pdf加水印后输出路径
	 * @param waterMarkName 水印文字
	 * @param imgPath 图片路径
	 * @param pageNum 图片放置页码
	 * @param absoluteX 图片横坐标
	 * @param absoluteY 图片纵坐标
	 */
	public static void waterMark(String inputFile,
            String outputFile, String waterMarkName, String imgPath, Integer pageNum, Float absoluteX, Float absoluteY) {
        try {
        	PdfReader reader = new PdfReader(inputFile);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(
                    outputFile));

            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",   BaseFont.EMBEDDED);

            Rectangle pageRect = null;
            PdfGState gs = new PdfGState();
            gs.setFillOpacity(0.3f);
            gs.setStrokeOpacity(0.4f);
            int total = reader.getNumberOfPages() + 1;

            JLabel label = new JLabel();
            label.setText(waterMarkName);

            PdfContentByte under;
            //每页添加文字水印
            for (int i = 1; i < total; i++) {
                pageRect = reader.getPageSizeWithRotation(i);
                under = stamper.getUnderContent(i);//在内容下方加水印
                under.saveState();
                under.setGState(gs);
                under.beginText();
                under.setFontAndSize(base, 30);
                under.setTextMatrix(30, 30);
                under.setColorFill(BaseColor.GRAY);
                for (int y = 0; y < 10; y++) {
					for (int x = 0; x < 8; x++) {
						// 水印文字成45度角倾斜
		                under.showTextAligned(Element.ALIGN_LEFT
		                        , waterMarkName, 100 + 300 * x, 300 * y, 45);
		            }
				}
                // 添加水印文字
                under.endText();
                under.setLineWidth(1f);
                under.stroke();
            }

            //添加图片水印
            if (pageNum != null && StringUtils.isNotBlank(imgPath)) {
            	under = stamper.getOverContent(pageNum);// 在内容上方加水印
            	Image image = Image.getInstance(imgPath);//"D:\\印章.png"
            	/*
                  img.setAlignment(Image.LEFT | Image.TEXTWRAP);
                  img.setBorder(Image.BOX); img.setBorderWidth(10);
                  img.setBorderColor(BaseColor.WHITE); img.scaleToFit(100072);//大小
                  img.setRotationDegrees(-30);//旋转
            	 */
            	//image.setAbsolutePosition(60, 600);
            	image.setAbsolutePosition(absoluteX, absoluteY); // set the first background
            	// image of the absolute
            	image.scaleToFit(200, 200);
            	under.addImage(image);
			}

            stamper.close();
            reader.close();
            logger.info("水印文件输出：" + outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public static void receiptWaterMark(String inputFile,
            String outputFile, String waterMarkName, String imgPath, Integer pageNum, Float absoluteX, Float absoluteY) {
        try {
        	PdfReader reader = new PdfReader(inputFile);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(
                    outputFile));

            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",   BaseFont.EMBEDDED);


            PdfGState gs = new PdfGState();
            gs.setFillOpacity(0.3f);
            gs.setStrokeOpacity(0.4f);
            int total = reader.getNumberOfPages() + 1;

            JLabel label = new JLabel();
            label.setText(waterMarkName);

            PdfContentByte under;

            //每页添加文字水印
            Rectangle pageRect = null;
            for (int i = 1; i < total; i++) {
                pageRect = reader.getPageSizeWithRotation(i);
                under = stamper.getUnderContent(i);//在内容下方加水印
                under.saveState();
                under.setGState(gs);
                under.beginText();
                under.setFontAndSize(base, 30);
                under.setTextMatrix(30, 30);
                under.setColorFill(BaseColor.GRAY);
                for (int y = 0; y < 10; y++) {
					for (int x = 0; x < 8; x++) {
						// 水印文字成45度角倾斜
		                under.showTextAligned(Element.ALIGN_LEFT
		                        , waterMarkName, 100 + 300 * x, 300 * y, 45);
		            }
				}
                // 添加水印文字
                under.endText();
                under.setLineWidth(1f);
                under.stroke();
            }

            //添加图片水印
            if (pageNum != null && StringUtils.isNotBlank(imgPath)) {
            	under = stamper.getOverContent(pageNum);// 在内容上方加水印
            	Image image = Image.getInstance(imgPath);//
            	image.setAbsolutePosition(absoluteX, absoluteY); // set the first background
            	image.scaleToFit(60, 60);
            	under.addImage(image);
			}

            stamper.close();
            reader.close();
            logger.info("水印文件输出：" + outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
