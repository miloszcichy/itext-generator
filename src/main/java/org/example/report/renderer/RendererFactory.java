package org.example.report.renderer;

import org.example.report.model.CellType;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.renderer.CellRenderer;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class RendererFactory {
    private static RendererFactory INSTANCE;
    private static final Map<CellType, TriFunction<Cell, String, Object, CellRenderer>> argConfig = Maps.newHashMap(
            ImmutableMap.of(
                    CellType.TEXT, InputCellRenderer::new,
                    CellType.SELECT, SelectCellRenderer::new,
                    CellType.TRAFFIC_LIGHT, TrafficLightRenderer::new));

    public static RendererFactory getInstance() {
        return Objects.nonNull(INSTANCE) ? INSTANCE : new RendererFactory();
    }

    public CellRenderer getRenderer(CellType type, Cell cell, String name, Object value) {
        final TriFunction<Cell, String, Object, CellRenderer> func = argConfig.get(type);
        return Objects.nonNull(func) ? func.apply(cell, name, value) : new CellRenderer(cell);
    }

    @FunctionalInterface
    interface TriFunction<A, B, C, R> {

        R apply(A a, B b, C c);

        default <V> TriFunction<A, B, C, V> andThen(
                Function<? super R, ? extends V> after) {
            Objects.requireNonNull(after);
            return (A a, B b, C c) -> after.apply(apply(a, b, c));
        }
    }
}
