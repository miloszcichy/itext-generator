package org.example.report.table;

import com.itextpdf.layout.element.Cell;

public class TrafficLightCell extends Cell {

    public TrafficLightCell(final int rowspan, final int colspan) {
        super(rowspan, colspan);
        this.add(new Cell());
        this.add(new Cell());
        this.add(new Cell());
    }

    public TrafficLightCell() {
        super();
        this.add(new Cell());
        this.add(new Cell());
        this.add(new Cell());
    }
}
