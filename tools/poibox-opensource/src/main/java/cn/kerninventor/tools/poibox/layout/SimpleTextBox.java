package cn.kerninventor.tools.poibox.layout;

import cn.kerninventor.tools.poibox.utils.JsMathUtil;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFTextbox;
import org.apache.poi.ss.usermodel.Shape;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFTextBox;

/**
 * @author Kern
 * @date 2020/5/7 9:48
 * @description
 */
public final class SimpleTextBox<Agent extends Shape> {

    private Agent agent;

    public SimpleTextBox(Agent agent) {
        if (!(agent instanceof HSSFTextbox) && !(agent instanceof XSSFTextBox)) {
            throw new IllegalArgumentException("TextBox not found!");
        }
        this.agent = agent;
    }

    public SimpleTextBox setString(String str) {
        if (agent instanceof HSSFTextbox) {
            ((HSSFTextbox)agent).setString(new HSSFRichTextString(str));
        } else if (agent instanceof XSSFTextBox) {
            ((XSSFTextBox)agent).clearText();
            ((XSSFTextBox)agent).setText(str);
        }
        return this;
    }

    public SimpleTextBox setVerticalAlignment(VerticalAlignment verticalAlignment) {
        if (agent instanceof HSSFTextbox) {
            ((HSSFTextbox)agent).setVerticalAlignment(verticalAlignment.getCode());
        } else if (agent instanceof XSSFTextBox) {
            ((XSSFTextBox)agent).setVerticalAlignment(verticalAlignment);
        }
        return this;
    }

    @Deprecated
    public SimpleTextBox setMargins(int left, int top, int right, int bottom) {
        boolean b = JsMathUtil.up2Standard(0, JsMathUtil.JsSimpleComparison.greaterThanOrEqualTo, left, top, right, bottom);
        if (!b) {
            throw new IllegalArgumentException("An invalid parameter was entered, margins must be greater than or equal to 0!");
        }
        //FIXME invalid when textbox is HSSFTextbox
        if (agent instanceof HSSFTextbox) {
            HSSFTextbox textbox = ((HSSFTextbox)agent);
            textbox.setMarginLeft(left);
            textbox.setMarginTop(top);
            textbox.setMarginRight(right);
            textbox.setMarginBottom(bottom);
        } else if (agent instanceof XSSFTextBox) {
            XSSFTextBox textBox = ((XSSFTextBox)agent);
            textBox.setLeftInset(left);
            textBox.setTopInset(top);
            textBox.setRightInset(right);
            textBox.setBottomInset(bottom);
        }
        return this;
    }

    public SimpleTextBox setFillColor(int red, int green, int blue) {
        agent.setFillColor(red, green, blue);
        return this;
    }

    public SimpleTextBox setFillColor(Palette palette) {
        agent.setFillColor(palette.getRed(), palette.getGreen(), palette.getBlue());
        return this;
    }

    public SimpleTextBox setLineStyle(int lineStyle){
        if (agent instanceof HSSFShape) {
            ((HSSFShape)agent).setLineStyle(lineStyle);
        } else if (agent instanceof XSSFShape) {
            ((XSSFShape)agent).setLineStyle(lineStyle);
        }
        return this;
    }

    public SimpleTextBox setLineColor(int red, int green, int blue){
        agent.setLineStyleColor(red, green, blue);
        return this;
    }

    public SimpleTextBox setLineColor(Palette palette){
        agent.setLineStyleColor(palette.getRed(), palette.getGreen(), palette.getBlue());
        return this;
    }

}
