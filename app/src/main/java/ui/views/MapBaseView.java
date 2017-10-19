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
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import teamjapannumbahone.tickettoride.R;

/**
 * Created by jontt on 10/17/2017.
 */

public class MapBaseView extends View {
    WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    Paint background = new Paint();
    Rect screenRect = new Rect(0, 0, display.getWidth(), display.getHeight());
    Resources resources = getResources();
    Bitmap mapBitmap = BitmapFactory.decodeResource(resources, R.drawable.usamap);

    ImageButton mImageButton = new ImageButton(this.getContext());

    public MapBaseView(Context context, AttributeSet st) {
        super(context, st);
    }

    @Override
    public void onDraw(Canvas canvas){
        //canvas.drawColor(Color.BLACK); //Draw a background color, not really needed.
        background.setColor(Color.BLACK);
        background.setTextSize(12341234);
        canvas.drawBitmap(mapBitmap, screenRect, screenRect, null);
        Point p1 = new Point(50, 50);
        Point p2 = new Point(1000, 1000);
        drawFourCarLine(p1, p2, canvas);
        //canvas.drawLine(p1.x, p1.y, p2.x, p2.y, background);
    }


    private void drawFourCarLine(Point p1, Point p2, Canvas canvas){
        Point temp = new Point(p2.x/4, p2.y/4);
        for (int i = 0; i < 4; ++i){

            temp.set(temp.x * i - 10, temp.y *i - 10);
            canvas.drawLine(p1.x, p1.y, temp.x, temp.y, background);

        }
    }

}
