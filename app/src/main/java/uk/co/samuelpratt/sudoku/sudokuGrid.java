package uk.co.samuelpratt.sudoku;

        import android.content.Context;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Point;
        import android.util.AttributeSet;
        import android.view.Display;
        import android.view.View;
        import android.view.WindowManager;

/**
 * Created by edvin on 3/22/2018.
 */

public class sudokuGrid extends View {


    private float scaleFactor;
    public sudokuGrid(Context context){
        super(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point pt = new Point();
        display.getSize(pt);
        scaleFactor = pt.x/720 + pt.x%720;
        generateNumbers();
        initPaints();
        setBackgroundColor(Color.rgb(255,255,153));
    }
    public sudokuGrid (Context context, AttributeSet attrs){
        super(context, attrs);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point pt = new Point();
        display.getSize(pt);
        scaleFactor = pt.x/720+ (pt.x - pt.x/720)%71;
        generateNumbers();
        initPaints();
        setBackgroundColor(Color.rgb(255,255,153));
    }
    Paint thickGridLine = new Paint();
    Paint thinGridLine = new Paint();
    Paint numberText = new Paint();

    private void initPaints(){
        thickGridLine.setColor(Color.BLACK);
        thickGridLine.setStrokeWidth(4);
        thickGridLine.setStyle(Paint.Style.STROKE);
        thinGridLine.setStyle(Paint.Style.STROKE);
        thinGridLine.setColor(Color.BLACK);
        thinGridLine.setStrokeWidth(Math.round(2));
        numberText.setTextSize((60*scaleFactor)/10);
    }

    Integer numbers[][] = new Integer[9][9];

    @Override
    protected void onDraw(Canvas canvas){

        createLines(canvas);
        printNumbers(canvas);
    }

    private void createLines(Canvas canvas){
        createHorizontalLines(canvas);
        createVerticalLines(canvas);
    }

    private void createHorizontalLines(Canvas canvas){
        for(int i = 0; i < 10; i++) {
            if (i % 3 == 0) {
                canvas.drawLine((0 + 60 * i+2)*scaleFactor/10, 2*scaleFactor/10, (0 + 60 *i+ 2)*scaleFactor/10, 542*scaleFactor/10, thickGridLine);
            }
            else canvas.drawLine((0+60*i+2)*scaleFactor/10,2*scaleFactor/10,(0+60*i+2)*scaleFactor/10,542*scaleFactor/10, thinGridLine);
        }
    }

    private void createVerticalLines(Canvas canvas){
        for(int i = 0; i < 10; i++) {
            if (i % 3 == 0) {
                canvas.drawLine(2*scaleFactor/10, (0 + 60 * i+2)*scaleFactor/10, 542*scaleFactor/10, (0 + 60 * i+2)*scaleFactor/10, thickGridLine);
            }
            else canvas.drawLine(2*scaleFactor/10,(0+60*i+2)*scaleFactor/10,542*scaleFactor/10,(0+60*i+2)*scaleFactor/10, thinGridLine);
        }
    }

    public void generateNumbers(){
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                numbers[i][j] = null;

    }

    public void fillGrid(Integer[][] values){
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                numbers[j][i]=values[j][i];
        invalidate();
    }

    private void printNumbers(Canvas canvas){
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if(numbers[j][i] != null)
                    canvas.drawText(Integer.toString(numbers[j][i]), (17 + 60 * j)*scaleFactor/10, (55 + 60 * i)*scaleFactor/10, numberText);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
