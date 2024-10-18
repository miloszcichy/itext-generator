package org.example.report.table;

import org.example.report.style.Stylesheet;
import com.itextpdf.layout.element.Cell;

public class HeaderCell extends Cell {
    public HeaderCell(final int rowspan, final int colspan) {
        super(rowspan, colspan);
        this.addStyle(Stylesheet.HEADER_CELL_STYLE);
    }

    public HeaderCell() {
        this.addStyle(Stylesheet.HEADER_CELL_STYLE);
    }
}
