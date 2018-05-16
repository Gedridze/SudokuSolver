package uk.co.solveriai.sudoku.puzzlescanner;

public class PuzzleNotFoundException extends Exception {
    public PuzzleNotFoundException(String message) {
        super(message);
    }
}