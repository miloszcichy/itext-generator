package org.example.report.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Represents a Table row with headers, labels and cell values.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GenericRow {

    private List<GenericCell> cells;

    public static GenericRowBuilder rowBuilder() {
        return builder();
    }
}
