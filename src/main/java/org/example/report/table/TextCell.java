package org.example.report.table;

import org.example.report.style.Stylesheet;
import com.itextpdf.layout.element.Cell;

public class TextCell extends Cell {
    public TextCell(final int rowspan, final int colspan) {
        super(rowspan, colspan);
        this.addStyle(Stylesheet.TEXT_CELL_STYLE);
    }

    public TextCell() {
        super();
        this.addStyle(Stylesheet.TEXT_CELL_STYLE);
    }
}
