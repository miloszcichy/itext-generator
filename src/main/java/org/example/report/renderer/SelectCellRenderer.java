package org.example.report.renderer;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.ChoiceFormFieldBuilder;
import com.itextpdf.forms.fields.PdfChoiceFormField;
import com.itextpdf.forms.fields.PdfFormAnnotation;
import com.itextpdf.forms.fields.PdfFormCreator;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.exceptions.PdfException;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;

import java.io.IOException;

public class SelectCellRenderer extends CellRenderer {
    protected String name;
    protected String[][] options;

    public SelectCellRenderer(Cell modelElement, String name, Object options) {
        super(modelElement);
        this.name = name;
        this.options = (String[][]) options;
    }

    @Override
    public IRenderer getNextRenderer() {
        return new SelectCellRenderer((Cell) modelElement, name, options);
    }

    @Override
    public void draw(DrawContext drawContext) {

        PdfAcroForm form = PdfFormCreator.getAcroForm(drawContext.getDocument(), true);
        PdfChoiceFormField choice = new ChoiceFormFieldBuilder(drawContext.getDocument(), name)
                .setWidgetRectangle(getOccupiedAreaBBox()).setOptions(options).createComboBox();
        form.addField(choice);
    }
}