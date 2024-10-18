package org.example.report.style;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

public class Stylesheet {
    //COLORS
    public static final DeviceRgb CUSTOM_EU_BLUE = new DeviceRgb(33, 150, 243);
    //TABLES
    public static final Style TITLE_TABLE = new Style()
            .setTextAlignment(TextAlignment.CENTER)
            .setBackgroundColor(CUSTOM_EU_BLUE)
            .setFontColor(ColorConstants.WHITE)
            .setWidth(UnitValue.createPercentValue(100))
            .setMarginBottom(10)
            .setHorizontalAlignment(HorizontalAlignment.CENTER);
    public static final Style DEFAULT_TABLE = new Style()
            .setTextAlignment(TextAlignment.CENTER)
            .setBackgroundColor(ColorConstants.WHITE)
            .setFontColor(ColorConstants.BLACK)
            .setWidth(UnitValue.createPercentValue(100))
            .setHorizontalAlignment(HorizontalAlignment.CENTER);
    //CELLS
    public static final Style TEXT_CELL_STYLE = new Style()
            .setMaxWidth(UnitValue.createPercentValue(30))
            .setMinHeight(15)
            .setTextAlignment(TextAlignment.CENTER);
    public static final Style HEADER_CELL_STYLE = new Style()
            .setTextAlignment(TextAlignment.CENTER)
            .setBackgroundColor(CUSTOM_EU_BLUE)
            .setFontColor(ColorConstants.WHITE);
}
