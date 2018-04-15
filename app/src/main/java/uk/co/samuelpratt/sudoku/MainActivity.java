package uk.co.samuelpratt.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import uk.co.samuelpratt.sudoku.puzzlesolver.Puzzle;
import uk.co.samuelpratt.sudoku.puzzlesolver.Solver;

public class MainActivity extends AppCompatActivity {

    private Puzzle puzzle;
    private sudokuGrid sudok;
    private Integer [][] values;

    public void updatePuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
        if(puzzle == null)
            sudok.generateNumbers();
        else {
            values = puzzle.fillValues();
            sudok.fillGrid(values);
            sudok.invalidate();
        }
       // GridView gridView = (GridView) findViewById(R.id.gridView1);
        //PuzzleAdaptor puzzleAdapter = new PuzzleAdaptor(this, this.puzzle);
        //gridView.setAdapter(puzzleAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sudok = (sudokuGrid) findViewById(R.id.sudoku_Grid);
        initPuzzleOrGetFromExtras();

        updatePuzzle(puzzle);
    }

    private void initPuzzleOrGetFromExtras() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.get("Puzzle") != null) {
            puzzle = new Puzzle((Integer[][]) bundle.get("Puzzle"));
        } else {
            puzzle = null;
        }

    }

    public void TakeAPicture(View v) throws Exception {
        Intent takeAPictureIntent = new Intent(this, TakeAPictureActivity.class);
        startActivity(takeAPictureIntent);
    }

    public void SolvePuzzle(View v) throws Exception {
        Solver puzzleSolver = new Solver(this.puzzle);
        Puzzle solvedPuzzle = puzzleSolver.solvePuzzle();
        updatePuzzle(solvedPuzzle);

    }
}
