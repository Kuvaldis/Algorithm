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

    private final Map<Cell, Map<Cell, Set<Integer>>> disabledValues = new HashMap<>();

    @Override
    protected void prepare(Board board) {
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
    protected Board constructResult(Board input) {
        return input;
    }

    @Override
    protected boolean isSolution(List<CellValue> solutionsList, int solutionsSize, Board input) {
        return input.getEmptyCells().size() == 0;
    }

    @Override
    protected boolean processSolution(List<CellValue> solutionsList, int solutionsSize, Board input) {
        return true;
    }

    @Override
    protected List<CellValue> constructCandidates(List<CellValue> solutionsList, int solutionsSize, Board input) {
        System.out.println(input);
        System.out.println();
        List<CellValue> result = possibleValues.entrySet().stream()
                .filter(entry -> input.getCellValue(entry.getKey()) == null)
                .map(entry -> entry.getValue().stream()
                        .filter(value -> !cellDisables(entry.getKey()).values().stream()
                                .flatMap(Collection::stream)
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
    protected boolean makeMove(List<CellValue> solutionsList, int solutionsSize, Board input) {
        final CellValue cellValue = solutionsList.get(solutionsSize - 1);
        fillCell(input, cellValue);
        return false;
    }

    @Override
    protected boolean unmakeMove(List<CellValue> solutionsList, int solutionsSize, Board input) {
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
                .filter(relatedCell -> isPossibleValue(value, relatedCell))
                .filter(relatedCell -> !isValueDisabled(relatedCell, cell, value))
                .forEach(relatedCellToDisableValue -> disableValue(relatedCellToDisableValue, cell, value));
    }

    private Boolean isPossibleValue(Integer value, Cell cell) {
        return ofNullable(possibleValues.get(cell))
                .map(relatedCellPossibleValues -> relatedCellPossibleValues.contains(value))
                .orElse(Boolean.FALSE);
    }

    private Boolean isValueDisabled(final Cell cell, final Cell forCell, final Integer value) {
        return ofNullable(disabledValues.get(cell))
                .map(relatedCellDisables -> relatedCellDisables.get(forCell))
                .map(values -> values.contains(value))
                .orElse(Boolean.FALSE);
    }

    private void disableValue(final Cell cell, final Cell forCell, final Integer value) {
        cellDisabledValues(cell, forCell).add(value);
    }

    private Set<Integer> cellDisabledValues(Cell cell, Cell forCell) {
        Map<Cell, Set<Integer>> cellDisables = cellDisables(cell);
        Set<Integer> cellDisabledValues = cellDisables.get(forCell);
        if (cellDisabledValues == null) {
            cellDisabledValues = new HashSet<>();
            cellDisables.put(forCell, cellDisabledValues);
        }
        return cellDisabledValues;
    }

    private Map<Cell, Set<Integer>> cellDisables(Cell cell) {
        Map<Cell, Set<Integer>> cellDisables = disabledValues.get(cell);
        if (cellDisables == null) {
            cellDisables = new HashMap<>();
            disabledValues.put(cell, cellDisables);
        }
        return cellDisables;
    }

    private void freeCell(final Board board, final CellValue cellValue) {
        final Cell cell = cellValue.getCell();
        final Integer value = board.getCellValue(cell);
        board.setCellValue(cell, null);
        enableValue(cell, cell, value);
        relatedCells(cell).stream()
                .filter(relatedCell -> isValueDisabled(relatedCell, cell, value))
                .forEach(relatedCellToDisableValue -> enableValue(relatedCellToDisableValue, cell, value));
    }

    private void enableValue(final Cell cell, final Cell forCell, final Integer value) {
        cellDisabledValues(cell, forCell).remove(value);
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

    private Set<Cell> col(Cell cell) {
        return IntStream.range(0, Board.SIZE)
                .mapToObj(y -> new Cell(cell.getX(), y))
                .collect(Collectors.toSet());
    }
}
