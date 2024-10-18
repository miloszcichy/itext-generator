package org.example.report.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.checkerframework.checker.units.qual.C;

import java.util.Objects;

import static java.util.Objects.nonNull;


/**
 * Represents a Generic Table Cell with value and additional attributes.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GenericCell {

    protected CellType type;

    protected String light;

    protected Integer columnSpan;

    protected Integer rowSpan;

    protected boolean editable;

    private Object content;

    private String name;

    public static GenericCellBuilder cellBuilder() {
        return builder();
    }

    public static class GenericCellBuilder {

        public GenericCellBuilder type(final CellType type) {
            this.type = type;
            return this;
        }

        public GenericCellBuilder type(final String type) {
            this.type = CellType.fromString(type);
            return this;
        }

        public GenericCellBuilder content(final Object content) {
            this.content = nonNull(content) ? content : " ";
            return this;
        }
    }

    public Integer getColumnSpan() {
        return Objects.isNull(columnSpan) ? 1 : columnSpan;
    }

    public Integer getRowSpan() {
        return Objects.isNull(rowSpan) ? 1 : rowSpan;
    }
}
