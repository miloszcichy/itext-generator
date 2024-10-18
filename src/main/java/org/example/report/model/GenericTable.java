package org.example.report.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Represents a Table with table header, column labels and values.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GenericTable {

    private Integer numberOfColumns;

    private List<GenericRow> rows;

    public static GenericTableBuilder tableBuilder() {
        return builder();
    }
}
