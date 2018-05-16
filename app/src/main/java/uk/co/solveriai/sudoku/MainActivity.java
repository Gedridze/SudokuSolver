package uk.co.solveriai.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import uk.co.solveriai.sudoku.puzzlesolver.Puzzle;
import uk.co.solveriai.sudoku.puzzlesolver.Solver;

public class MainActivity extends AppCompatActivity {

    private Puzzle puzzle;
    private sudokuGrid sudok;
    private Integer [][] values;

    public void updatePuzzle(Puzzle puzzle, boolean solved) {
        if(!solved) {
            this.puzzle = puzzle;
            if (puzzle == null)
                sudok.generateNumbers();
            else {
                values = puzzle.fillValues();
                sudok.fillGrid(values, solved);
                sudok.invalidate();
            }
        }
        else {
             values = puzzle.fillValues();
             sudok.fillGrid(values, solved);
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
        sudok.generateNumbers();
        initPuzzleOrGetFromExtras();
        Button btn = (Button) findViewById(R.id.SolvePuzzleButton);
        if(puzzle == null)
        {

            btn.setEnabled(false);
        }
        else{
            Integer[][] correctness = puzzle.fillValues();
            boolean correct = isSudokuCorrect(correctness);
            if(!correct){
                Toast.makeText(this, "Klaida! Neteisingai nufotografuotas galvosūkis", Toast.LENGTH_LONG).show();
                btn.setEnabled(false);
                puzzle = null;
            }
        }
        updatePuzzle(puzzle, false);
    }

    private void initPuzzleOrGetFromExtras() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.get("Puzzle") != null) {
            puzzle = new Puzzle((Integer[][]) bundle.get("Puzzle"));
        } else {
            puzzle = null;
        }

    }

    private boolean isSudokuCorrect(Integer[][] puzzle){
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++) {
            if(puzzle[i][j] != null)
                if (puzzle[i][j] == -1)
                    return false;
            }
        return true;
    }

    @Override
    public void onBackPressed() {

    }


    public void TakeAPicture(View v) throws Exception {
        Intent takeAPictureIntent = new Intent(this, TakeAPictureActivity.class);
        startActivity(takeAPictureIntent);
    }

    public void SolvePuzzle(View v) throws Exception {
        Solver puzzleSolver = new Solver(this.puzzle);
        Puzzle solvedPuzzle = puzzleSolver.solvePuzzle();
        updatePuzzle(solvedPuzzle, true);

    }
}
