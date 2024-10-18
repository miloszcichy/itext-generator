package org.example.report.table;

import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import static org.example.report.style.Stylesheet.TITLE_TABLE;

public class TitleTable extends Table {
    public TitleTable(final float[] columnWidths, final boolean largeTable) {
        super(columnWidths, largeTable);
        addStyle(TITLE_TABLE);
    }

    public TitleTable(final UnitValue[] columnWidths, final boolean largeTable) {
        super(columnWidths, largeTable);
        addStyle(TITLE_TABLE);
    }

    public TitleTable(final UnitValue[] columnWidths) {
        super(columnWidths);
        addStyle(TITLE_TABLE);
    }

    public TitleTable(final float[] pointColumnWidths) {
        super(pointColumnWidths);
        addStyle(TITLE_TABLE);
    }

    public TitleTable(final int numColumns, final boolean largeTable) {
        super(numColumns, largeTable);
        addStyle(TITLE_TABLE);
    }

    public TitleTable(final int numColumns) {
        super(numColumns);
        addStyle(TITLE_TABLE);
    }
}
