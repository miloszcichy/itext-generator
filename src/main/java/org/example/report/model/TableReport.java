package org.example.report.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Represents a Rom/Eval Table Report.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TableReport {

    private String title;

    private List<GenericTable> tables;

    public static TableReportBuilder reportBuilder() {
        return builder();
    }
}
