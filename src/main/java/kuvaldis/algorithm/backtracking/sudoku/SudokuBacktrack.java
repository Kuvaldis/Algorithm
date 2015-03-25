package kuvaldis.algorithm.backtracking.sudoku;

import kuvaldis.algorithm.backtracking.AbstractBacktrack;
import lombok.Data;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;
import static kuvaldis.algorithm.backtracking.sudoku.Board.SQUARE_SIZE;

public class SudokuBacktrack extends AbstractBacktrack<SudokuBacktrack.CellValue, Board, Board> {

    @Data
    protected static class CellValue {
        private final Cell cell;
        private final Integer value;
    }

    private static final Set<Integer> ALL_POSSIBLE_VALUES = IntStream.range(1, Board.SIZE + 1)
            .mapToObj(Integer::new)
            .collect(toSet());

    private Map<Cell, Set<Integer>> possibleValues = new HashMap<>();

    private final Map<Cell, Map<Cell, Integer>> disabledValues = new HashMap<>();

    @Override
    protected void prepare(final Board board) {
        possibleValues = board.getEmptyCells().stream()
                .collect(toMap(Function.<Cell>identity(), (Cell cell) -> {
                    final Set<Integer> impossibleValues = relatedCells(cell).stream()
                            .map(board::getCellValue)
                            .filter(value -> value != null)
                            .collect(Collectors.toSet());
                    return ALL_POSSIBLE_VALUES.stream()
                            .filter(possibleValue -> !impossibleValues.contains(possibleValue))
                            .collect(toSet());
                }));
    }

    @Override
    protected Board constructResult(final Board input) {
        return input;
    }

    @Override
    protected boolean isSolution(final List<CellValue> solutionsList, final int solutionsSize, final Board input) {
        return input.getEmptyCells().size() == 0;
    }

    @Override
    protected boolean processSolution(final List<CellValue> solutionsList, final int solutionsSize, final Board input) {
        return true;
    }

    @Override
    protected List<CellValue> constructCandidates(final List<CellValue> solutionsList, final int solutionsSize, final Board input) {
        List<CellValue> result = possibleValues.entrySet().stream()
                .filter(entry -> input.getCellValue(entry.getKey()) == null)
                .map(entry -> entry.getValue().stream()
                        .filter(value -> !cellDisabledValues(entry.getKey()).values().stream()
                                .filter(value::equals)
                                .findAny()
                                .isPresent())
                        .findAny()
                        .map(value -> new CellValue(entry.getKey(), value))
                        .orElse(null))
                .filter(value -> value != null)
                .sorted((cellValue1, cellValue2) -> possibleValues.get(cellValue1.getCell()).size() -
                        possibleValues.get(cellValue2.getCell()).size())
                .collect(toList());
        if (result.size() < input.getEmptyCells().size()) {
            result = Collections.emptyList();
        }
        return result;
    }

    @Override
    protected boolean makeMove(final List<CellValue> solutionsList, final int solutionsSize, final Board input) {
        final CellValue cellValue = solutionsList.get(solutionsSize - 1);
        fillCell(input, cellValue);
        return false;
    }

    @Override
    protected boolean unmakeMove(final List<CellValue> solutionsList, final int solutionsSize, final Board input) {
        freeCell(input, solutionsList.get(solutionsSize - 1));
        return false;
    }

    private void fillCell(final Board board, final CellValue cellValue) {
        final Cell cell = cellValue.getCell();
        final Integer value = cellValue.getValue();
        board.setCellValue(cell, value);
        disableValue(cell, cell, value);
        relatedCells(cell).stream()
                .filter(relatedCell -> board.getCellValue(relatedCell) == null)
                .filter(relatedCell -> isPossibleValue(relatedCell, value))
                .filter(relatedCell -> !isValueDisabled(relatedCell, cell, value))
                .forEach(relatedCellToDisableValue -> disableValue(relatedCellToDisableValue, cell, value));
    }

    private Boolean isPossibleValue(final Cell cell, final Integer value) {
        return ofNullable(possibleValues.get(cell))
                .map(relatedCellPossibleValues -> relatedCellPossibleValues.contains(value))
                .orElse(Boolean.FALSE);
    }

    private Boolean isValueDisabled(final Cell cell, final Cell forCell, final Integer value) {
        return ofNullable(disabledValues.get(cell))
                .map(relatedCellDisables -> relatedCellDisables.get(forCell))
                .map(value::equals)
                .orElse(Boolean.FALSE);
    }

    private void disableValue(final Cell cell, final Cell forCell, final Integer value) {
        cellDisabledValues(cell).put(forCell, value);
    }

    private Map<Cell, Integer> cellDisabledValues(final Cell cell) {
        Map<Cell, Integer> cellDisabledValues = disabledValues.get(cell);
        if (cellDisabledValues == null) {
            cellDisabledValues = new HashMap<>();
            disabledValues.put(cell, cellDisabledValues);
        }
        return cellDisabledValues;
    }

    private void freeCell(final Board board, final CellValue cellValue) {
        final Cell cell = cellValue.getCell();
        final Integer value = board.getCellValue(cell);
        board.setCellValue(cell, null);
        enableValue(cell, cell);
        relatedCells(cell).stream()
                .filter(relatedCell -> isValueDisabled(relatedCell, cell, value))
                .forEach(relatedCellToDisableValue -> enableValue(relatedCellToDisableValue, cell));
    }

    private void enableValue(final Cell cell, final Cell forCell) {
        cellDisabledValues(cell).remove(forCell);
    }

    private Set<Cell> relatedCells(final Cell cell) {
        final Set<Cell> result = new HashSet<>();
        result.addAll(row(cell));
        result.addAll(col(cell));
        result.addAll(square(cell));
        result.remove(cell);
        return result;
    }

    private Set<Cell> square(final Cell cell) {
        final int leftX = (cell.getX() / SQUARE_SIZE) * SQUARE_SIZE;
        final int upY = (cell.getY() / SQUARE_SIZE) * SQUARE_SIZE;
        return IntStream.range(leftX, leftX + SQUARE_SIZE)
                .mapToObj(x -> IntStream.range(upY, upY + SQUARE_SIZE).mapToObj(y -> new Cell(x, y)))
                .flatMap(Function.<Stream<Cell>>identity())
                .collect(Collectors.toSet());
    }

    private Set<Cell> row(final Cell cell) {
        return IntStream.range(0, Board.SIZE)
                .mapToObj(x -> new Cell(x, cell.getY()))
                .collect(Collectors.toSet());
    }

    private Set<Cell> col(final Cell cell) {
        return IntStream.range(0, Board.SIZE)
                .mapToObj(y -> new Cell(cell.getX(), y))
                .collect(Collectors.toSet());
    }
}
