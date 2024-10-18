package org.example.report.table;

import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import static org.example.report.style.Stylesheet.DEFAULT_TABLE;

public class DefaultTable extends Table {
    public DefaultTable(final float[] columnWidths, final boolean largeTable) {
        super(columnWidths, largeTable);
        addStyle(DEFAULT_TABLE);
    }

    public DefaultTable(final UnitValue[] columnWidths, final boolean largeTable) {
        super(columnWidths, largeTable);
        addStyle(DEFAULT_TABLE);
    }

    public DefaultTable(final UnitValue[] columnWidths) {
        super(columnWidths);
        addStyle(DEFAULT_TABLE);
    }

    public DefaultTable(final float[] pointColumnWidths) {
        super(pointColumnWidths);
        addStyle(DEFAULT_TABLE);
    }

    public DefaultTable(final int numColumns, final boolean largeTable) {
        super(numColumns, largeTable);
        addStyle(DEFAULT_TABLE);
    }

    public DefaultTable(final int numColumns) {
        super(numColumns);
        addStyle(DEFAULT_TABLE);
    }
}
