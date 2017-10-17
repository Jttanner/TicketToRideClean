package ui.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

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
    public MapBaseView(Context context, AttributeSet st) {
        super(context, st);
    }

    @Override
    public void onDraw(Canvas canvas){

        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(mapBitmap, screenRect, screenRect, null);
    }


}
