package ui.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import teamjapannumbahone.tickettoride.R;

/**
 * Created by jontt on 10/17/2017.
 */

public class MapBaseView extends View {
    WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    Paint paint = new Paint();
    Rect screenRect = new Rect(0, 0, display.getWidth(), display.getHeight());
    Resources resources = getResources();
    Bitmap mapBitmap = BitmapFactory.decodeResource(resources, R.drawable.usamap);

    List<Point> touchCoords = new ArrayList<>();
    //Context callingContext = getContext();


    public MapBaseView(Context context, AttributeSet st) {
        super(context, st);
    }

    @Override
    public void onDraw(Canvas canvas){
        //canvas.drawColor(Color.BLACK); //Draw a paint color, not really needed.
        paint.setColor(Color.BLACK);
        paint.setTextSize(12341234);
        canvas.drawBitmap(mapBitmap, screenRect, screenRect, null);
        drawCities(canvas);
        //Point p1 = new Point(50, 50);
        //Point p2 = new Point(10000, 10000);
        //drawFourCarLine(p1, p2, canvas);
        //canvas.drawLine(p1.x, p1.y, p2.x, p2.y, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int xTouch = Math.round(event.getX());
        int yTouch = Math.round(event.getY());
        touchCoords.add(new Point(xTouch, yTouch));
        return true;
    }

    private void drawTrackSquare(Point p1, Point p2, Canvas canvas){

    }

    Point Vancouver = new Point(159, 135);
    Point Seattle = new Point(167, 290);
    Point Portland = new Point(104,375);
    Point SanFrancisco = new Point(185, 696);
    Point LosAngeles = new Point(306, 815);
    Point Phoenix = new Point(520, 825);
    Point LasVegas =new Point(399, 696);
    Point SLC = new Point(517, 559);
    Point Helena = new Point(627, 309);
    Point Calgary = new Point(391, 88);
    Point Winnipeg = new Point(914, 80);
    Point SaultStMarie = new Point(1384, 290);
    Point Montreal = new Point(1818, 143);
    Point IDontKNow = new Point(1873, 448);
    Point Toronto = new Point(1578, 382);
    Point Deluth = new Point(1118, 364);
    Point Denver = new Point(722, 612);
    Point SantaFe = new Point(685, 744);
    Point ElPaso = new Point(730, 917);
    Point Houston = new Point(1043, 999);
    Point Dallas = new Point(1051, 999);
    Point OklahomaCity = new Point(1014, 925);
    Point KansasCity = new Point(988, 723);
    Point Omaha = new Point(1025, 595);
    Point Chicago = new Point(1014, 493);
    Point Pittsburgh = new Point(1347, 511);
    Point Boston = new Point(1597, 538);
    Point Washington = new Point(1662, 686);
    Point NewYork = new Point(1773, 485);
    Point Raleigh = new Point(1589, 733);
    Point Nashville = new Point(1339, 733);
    Point Atlanta = new Point(1457, 833);
    Point Charleston = new Point(1578, 815);
    Point Miami = new Point(1541, 1138);
    Point NewOrleans = new Point(1199, 862);
    Point LittleRock = new Point(1155, 760);
    Point SaintLouis = new Point(1191, 622);


    private void drawCityLines(Point p1, Point p2, Canvas canvas){

    }

    private void drawCities(Canvas canvas){
        canvas.drawCircle(Vancouver.x, Vancouver.y, 10, paint);
        canvas.drawCircle(Seattle.x, Seattle.y, 10, paint);
        canvas.drawCircle(Portland.x, Portland.y, 10, paint);
        canvas.drawCircle(SanFrancisco.x, SanFrancisco.y, 10, paint);
        canvas.drawCircle(LosAngeles.x, LosAngeles.y, 10, paint);

        canvas.drawCircle(Phoenix.x, Phoenix.y, 10, paint);
        canvas.drawCircle(LasVegas.x, LasVegas.y, 10, paint);
        canvas.drawCircle(SLC.x, SLC.y, 10, paint);
        canvas.drawCircle(Helena.x, Helena.y, 10, paint);
        canvas.drawCircle(Calgary.x, Calgary.y, 10, paint);
        canvas.drawCircle(Winnipeg.x, Winnipeg.y, 10, paint);
        canvas.drawCircle(SaultStMarie.x, SaultStMarie.y, 10, paint);
        canvas.drawCircle(Montreal.x, Montreal.y, 10, paint);
        canvas.drawCircle(IDontKNow.x, IDontKNow.y, 10, paint);

        canvas.drawCircle(Toronto.x, Toronto.y, 10, paint);
        canvas.drawCircle(Deluth.x, Deluth.y, 10, paint);
        canvas.drawCircle(Denver.x, Denver.y, 10, paint);
        canvas.drawCircle(SantaFe.x, SantaFe.y, 10, paint);
        canvas.drawCircle(Houston.x, Houston.y, 10, paint);
        canvas.drawCircle(Dallas.x, Dallas.y, 10, paint);
        canvas.drawCircle(OklahomaCity.x, OklahomaCity.y, 10, paint);

        canvas.drawCircle(KansasCity.x, KansasCity.y, 10, paint);
        canvas.drawCircle(Omaha.x, Omaha.y, 10, paint);
        canvas.drawCircle(Chicago.x, Chicago.y, 10, paint);
        canvas.drawCircle(Pittsburgh.x, Pittsburgh.y, 10, paint);
        canvas.drawCircle(Boston.x, Boston.y, 10, paint);
        canvas.drawCircle(Washington.x, Washington.y, 10, paint);
        canvas.drawCircle(NewYork.x, NewYork.y, 10, paint);

        canvas.drawCircle(Raleigh.x, Raleigh.y, 10, paint);
        canvas.drawCircle(Nashville.x, Nashville.y, 10, paint);
        canvas.drawCircle(Atlanta.x, Atlanta.y, 10, paint);
        canvas.drawCircle(Charleston.x, Charleston.y, 10, paint);
        canvas.drawCircle(Miami.x, Miami.y, 10, paint);
        canvas.drawCircle(NewOrleans.x, NewOrleans.y, 10, paint);
        canvas.drawCircle(LittleRock.x, LittleRock.y, 10, paint);
        canvas.drawCircle(SaintLouis.x, SaintLouis.y, 10, paint);

    }


    private void drawFourCarLine(Point p1, Point p2, Canvas canvas){
        Point temp = new Point(p2.x/4, p2.y/4);
        for (int i = 0; i < 4; ++i){

            temp.set(temp.x * i - 10, temp.y *i - 10);
            canvas.drawLine(p1.x, p1.y, temp.x, temp.y, paint);

        }
    }

}
