package org.example.report.table;

import org.example.report.model.CellType;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.itextpdf.layout.element.Cell;

import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class CellFactory {
    private static CellFactory INSTANCE;
    private static final Map<CellType, Supplier<Cell>> config = Maps.newHashMap(ImmutableMap.of(
            CellType.TEXT, TextCell::new,
            CellType.HEADER, HeaderCell::new,
            CellType.TRAFFIC_LIGHT, TrafficLightCell::new));
    private static final Map<CellType, BiFunction<Integer, Integer, Cell>> argConfig = Maps.newHashMap(ImmutableMap.of(
            CellType.TEXT, TextCell::new,
            CellType.HEADER, HeaderCell::new,
            CellType.TRAFFIC_LIGHT, TrafficLightCell::new));

    public static CellFactory getInstance() {
        return Objects.nonNull(INSTANCE) ? INSTANCE : new CellFactory();
    }

    public Cell getCell(CellType type) {
        return config.get(type).get();
    }

    public Cell getCell(CellType type, int colSpan, int rowSpan) {
        final BiFunction<Integer, Integer, Cell> func = argConfig.get(type);

        return Objects.nonNull(func) ? func.apply(colSpan, rowSpan) : new TextCell(colSpan, rowSpan);
    }
}
