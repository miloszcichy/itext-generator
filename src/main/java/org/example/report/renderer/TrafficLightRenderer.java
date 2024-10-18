package org.example.report.renderer;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfButtonFormField;
import com.itextpdf.forms.fields.PdfFormAnnotation;
import com.itextpdf.forms.fields.PdfFormCreator;
import com.itextpdf.forms.fields.RadioFormFieldBuilder;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;

public class TrafficLightRenderer extends CellRenderer {
    public static final String GREEN = "green";
    public static final String YELLOW = "yellow";
    public static final String RED = "red";
    private final String value;
    protected String name;

    public TrafficLightRenderer(Cell modelElement, String fieldName, Object value) {
        super(modelElement);
        this.name = fieldName;
        this.value = (String) value;
    }

    @Override
    public IRenderer getNextRenderer() {
        return new TrafficLightRenderer((Cell) modelElement, name, value);
    }

    @Override
    public void draw(DrawContext drawContext) {

        RadioFormFieldBuilder builder = new RadioFormFieldBuilder(drawContext.getDocument(), name);
        PdfButtonFormField radioGroup = builder.createRadioGroup();

        final float x = getOccupiedAreaBBox().getX();
        final float y = getOccupiedAreaBBox().getY();
        final float width = getOccupiedAreaBBox().getWidth();
        final float height = getOccupiedAreaBBox().getHeight();

        final Rectangle greenBox = new Rectangle(x, y, width / 3, height);
        final Rectangle yellowBox = new Rectangle(x + width / 3, y, width / 3, height);
        final Rectangle redBox = new Rectangle(x + (width / 3) * 2, y, width / 3, height);

        PdfFormAnnotation green = getPdfFormAnnotation(drawContext, greenBox, GREEN, ColorConstants.GREEN);
        PdfFormAnnotation yellow = getPdfFormAnnotation(drawContext, yellowBox, YELLOW, ColorConstants.YELLOW);
        PdfFormAnnotation red = getPdfFormAnnotation(drawContext, redBox, RED, ColorConstants.RED);
        radioGroup.addKid(green);
        radioGroup.addKid(yellow);
        radioGroup.addKid(red);
        radioGroup.setValue(this.value);
        PdfAcroForm form = PdfFormCreator.getAcroForm(drawContext.getDocument(), true);
        form.addField(radioGroup);
    }

    private PdfFormAnnotation getPdfFormAnnotation(final DrawContext drawContext, final Rectangle greenBox,
                                                   final String appearance, final Color color) {
        return new RadioFormFieldBuilder(drawContext.getDocument(), this.value)
                .createRadioButton(appearance, greenBox)
                .setBackgroundColor(color);
    }
}
