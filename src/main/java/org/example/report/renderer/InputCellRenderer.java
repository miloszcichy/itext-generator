package org.example.report.renderer;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormCreator;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.forms.fields.TextFormFieldBuilder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;

import java.util.Objects;

public class InputCellRenderer extends CellRenderer {
    private final String name;
    private final String value;

    public InputCellRenderer(final Cell modelElement, String name, Object value) {
        super(modelElement);
        this.name = name;
        this.value = (String) value;
    }

    @Override
    public IRenderer getNextRenderer() {
        return new InputCellRenderer((Cell) modelElement, name, value);
    }

    @Override
    public void draw(final DrawContext drawContext) {
        super.draw(drawContext);
        final PdfTextFormField field = new TextFormFieldBuilder(drawContext.getDocument(),
                name).setWidgetRectangle(getOccupiedAreaBBox()).createText();
        field.setValue(value);
        PdfAcroForm form = PdfFormCreator.getAcroForm(drawContext.getDocument(), true);
        form.addField(field);
    }
}
