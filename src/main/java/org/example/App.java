package org.example;

import org.example.report.model.TableReport;
import org.example.report.model.CellType;
import org.example.report.model.GenericCell;
import org.example.report.model.GenericRow;
import org.example.report.model.GenericTable;
;
import org.example.report.renderer.RendererFactory;
import org.example.report.table.CellFactory;
import org.example.report.table.DefaultTable;
import org.example.report.table.TitleTable;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.itextpdf.layout.renderer.CellRenderer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.example.report.style.Stylesheet.TITLE_TABLE;

public class App {

    private static final String PDF = "src/main/resources/default.pdf";

    private static final CellFactory cellFactory = CellFactory.getInstance();
    private static final RendererFactory rendererFactory = RendererFactory.getInstance();

    public static App INSTANCE = new App();

    public static void main(String[] args) {
        try {
            final TableReport table = INSTANCE.prepareTableData();
            INSTANCE.createPdf(table);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private TableReport prepareTableData() {
        GenericCell.cellBuilder().content("Header").type(CellType.HEADER).build();
        final GenericCell cell1Row1 = GenericCell.cellBuilder().content("Row 1 Cell 1").type(CellType.HEADER).build();
        final GenericCell cell2Row1 = GenericCell.cellBuilder().content("Row 1 Cell 2").type(CellType.HEADER).build();
        final GenericCell cell3Row1 = GenericCell.cellBuilder().content("Row 1 Cell 3").type(CellType.HEADER).build();

        final GenericCell cell1Row2 = GenericCell.cellBuilder().content("Row 2 Cell 1 Column Span 2").columnSpan(2)
                .type(CellType.TEXT)
                .build();
        final GenericCell cell2Row2 = GenericCell.cellBuilder().content("Row 2 Cell 2").type(CellType.TEXT)
                .editable(true).name("editable_fields_1").build();

        final GenericCell cell1Row3 = GenericCell.cellBuilder().content("Row 3 Cell 1 Row Span 2").rowSpan(2)
                .columnSpan(1).type(CellType.TEXT)
                .build();
        final GenericCell cell2Row3 = GenericCell.cellBuilder().content("Row 3 Cell 2").type(CellType.TEXT).build();
        final GenericCell cell3Row3 = GenericCell.cellBuilder().content("Row 3 Cell 3").type(CellType.TEXT).build();

        final GenericCell cell1Row4 = GenericCell.cellBuilder().content("Row 4 Cell 1 Column Span 3").columnSpan(3)
                .type(CellType.TEXT).build();

        final GenericCell cell1Row5 = GenericCell.cellBuilder().content("").editable(true).columnSpan(1)
                .type(CellType.TRAFFIC_LIGHT).name("traffic_light_1").build();
        final GenericCell cell2Row5 = GenericCell.cellBuilder().content("green").editable(true).columnSpan(2)
                .type(CellType.TRAFFIC_LIGHT).name("traffic_light_2").build();

        String[] options = {"Choose first option", "Choose second option", "Choose third option"};
        String[] exports = {"option1", "option2", "option3"};
        String[][] optionsArray = new String[options.length][];
        for (int i = 0; i < options.length; i++) {
            optionsArray[i] = new String[2];
            optionsArray[i][0] = exports[i];
            optionsArray[i][1] = options[i];
        }

        final GenericCell cell1Row6 = GenericCell.cellBuilder().editable(true).columnSpan(1)
                .type(CellType.SELECT).name("select_1").content(optionsArray).build();

        GenericRow rowN = GenericRow.rowBuilder().cells(new ArrayList<>(100)).build();
        for (int i = 0; i < 100; i++) {
            rowN.getCells().add(GenericCell.cellBuilder()
                    .type(CellType.TEXT).name("g_text_" + i).content("test_" + i
                            + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc at nunc dictum, varius massa sit amet, tincidunt eros. Integer vel finibus magna, sit amet tristique tortor. Sed leo purus, tempor ac placerat in, finibus id neque. Sed egestas a urna vitae tristique. Pellentesque pretium eros at tortor auctor porta. Proin egestas, lacus sed varius dapibus, dui dui venenatis dui, in sodales eros augue quis arcu. Praesent ornare suscipit ultrices. Nulla vel bibendum ipsum. Duis consequat nunc nec ante tincidunt porta. Morbi sagittis egestas lectus, a maximus massa sodales id. Aliquam mauris enim, egestas eu maximus non, viverra et velit. Duis ultricies tellus at bibendum condimentum. Praesent urna velit, rutrum vel sodales et, volutpat ac arcu. Sed maximus diam ac libero tincidunt fringilla. Donec sollicitudin, erat consectetur cursus vehicula, arcu nulla dictum mi, vitae mattis nunc mauris a turpis. Praesent bibendum elit sit amet egestas dignissim.")
                    .build());
        }
        final GenericRow row1 = GenericRow.rowBuilder().cells(Arrays.asList(cell1Row1, cell2Row1, cell3Row1)).build();
        final GenericRow row2 = GenericRow.rowBuilder().cells(Arrays.asList(cell1Row2, cell2Row2)).build();
        final GenericRow row3 = GenericRow.rowBuilder().cells(Arrays.asList(cell1Row3,
                cell2Row3,
                cell3Row3)).build();
        final GenericRow row4 = GenericRow.rowBuilder().cells(Arrays.asList(cell1Row4)).build();
        final GenericRow row5 = GenericRow.rowBuilder().cells(Arrays.asList(cell1Row5, cell2Row5)).build();
        final GenericRow row6 = GenericRow.rowBuilder().cells(Arrays.asList(cell1Row6)).build();

        final GenericTable table = GenericTable.tableBuilder()
                .rows(Arrays.asList(row1, row2, row3, row4, row5, row6, rowN))
                .numberOfColumns(3).build();

        return TableReport.reportBuilder().tables(Collections.singletonList(table))
                .title("iText PoC").build();
    }

    public void createPdf(final TableReport tableReport) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(PDF));
        Document document = new Document(pdf);
        this.drawTitleTable(tableReport, document);
        this.drawTables(tableReport, document);
//        this.drawWaterMark(pdf, document);
        document.close();
        pdf.close();
    }

    private void drawWaterMark(final PdfDocument pdf, final Document document) {
        Paragraph paragraph = new Paragraph("EC Watermark Test")
                .setFontSize(30);
        PdfExtGState gs1 = new PdfExtGState().setFillOpacity(0.2f);
        PdfPage pdfPage = pdf.getLastPage();
        Rectangle pageSize = pdfPage.getPageSizeWithRotation();
        pdfPage.setIgnorePageRotationForContent(true);
        float x = (pageSize.getLeft() + pageSize.getRight()) / 2;
        float y = (pageSize.getTop() + pageSize.getBottom()) / 2;
        PdfCanvas over = new PdfCanvas(pdf.getPage(1));
        over.saveState();
        over.setExtGState(gs1);
        document.showTextAligned(paragraph, x, y, 1, TextAlignment.CENTER, VerticalAlignment.TOP, 0);
        over.restoreState();
    }

    private void drawTables(final TableReport tableReport, final Document document) {
        tableReport.getTables().stream().forEachOrdered(genericTable -> {
            Table table = new DefaultTable(genericTable.getNumberOfColumns());
            genericTable.getRows().stream().flatMap(genericRow -> genericRow.getCells().stream())
                    .forEach(genericCell -> {
                        final Cell cell = cellFactory.getCell(genericCell.getType(), genericCell.getRowSpan(),
                                genericCell.getColumnSpan());
                        cell.setKeepTogether(true);
                        if (genericCell.isEditable()) {
                            final CellRenderer renderer =
                                    rendererFactory.getRenderer(genericCell.getType(), cell, genericCell.getName(),
                                            genericCell.getContent());
                            cell.setNextRenderer(renderer);
                        } else {
                            cell.add(new Paragraph(String.valueOf(genericCell.getContent())));
                        }
                        if (genericCell.getType().equals(CellType.HEADER)) {
                            table.addHeaderCell(cell);
                        } else {
                            table.addCell(cell);
                        }
                    });
            document.add(table);
        });
    }

    private void drawTitleTable(final TableReport tableReport, final Document document) {
        Table table = new TitleTable(1);
        table.addHeaderCell(tableReport.getTitle());
        table.addStyle(TITLE_TABLE);
        document.add(table);
    }
}
