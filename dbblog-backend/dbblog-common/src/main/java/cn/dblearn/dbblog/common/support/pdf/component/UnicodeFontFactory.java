package cn.dblearn.dbblog.common.support.pdf.component;

import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactoryImp;
import com.itextpdf.text.pdf.BaseFont;

/**
* <p>Title: UnicodeFontFactory</p>
* <p>Description: 字体设置</p>
* @author YML
* @date 2019年1月3日
*/
public class UnicodeFontFactory extends FontFactoryImp{

	public static final String SIMSUN_PATH = "/org/interim/common/support/pdf/fonts/simsun.ttc";
	public static final String PING_FANG_BOLD_PATH = "/org/interim/common/support/pdf/fonts/ping_fang_bold.ttf";
	public static final String PING_FANG_LIGHT_PATH = "/org/interim/common/support/pdf/fonts/ping_fang_light.ttf";

	@Override
	public Font getFont(final String fontname, final String encoding, final boolean embedded, final float size, final int style, final BaseColor color) {
        BaseFont baseFont = null;
		try {
			baseFont = BaseFont.createFont(PING_FANG_LIGHT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
        return new Font(baseFont, size, style, color);
    }

}
