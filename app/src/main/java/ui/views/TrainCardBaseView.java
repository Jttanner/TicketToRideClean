package ui.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import teamjapannumbahone.tickettoride.R;

/**
 * Created by Hwang on 10/28/2017.
 */

public class TrainCardBaseView extends View{


    WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    Paint paint = new Paint();
    Rect screenRect = new Rect(0, 0, display.getWidth(), display.getHeight());
    Resources resources = getResources();
    Bitmap redTrainBitmap = BitmapFactory.decodeResource(resources, R.drawable.redtrain);
    Bitmap blueTrainBitmap = BitmapFactory.decodeResource(resources, R.drawable.bluetrain);
    Bitmap blackTrainBitmap = BitmapFactory.decodeResource(resources, R.drawable.blacktrain);
    Bitmap greenTrainBitmap = BitmapFactory.decodeResource(resources, R.drawable.greentrain);
    Bitmap orangeTrainBitmap = BitmapFactory.decodeResource(resources, R.drawable.orangetrain);
    Bitmap purpleTrainBitmap = BitmapFactory.decodeResource(resources, R.drawable.purpletrain);
    Bitmap whiteTrainBitmap = BitmapFactory.decodeResource(resources, R.drawable.whitetrain);
    Bitmap yellowTrainBitmap = BitmapFactory.decodeResource(resources, R.drawable.yellowtrain);
    Bitmap wildTrainBitmap = BitmapFactory.decodeResource(resources, R.drawable.wildtrain);
    Bitmap backCardBitmap = BitmapFactory.decodeResource(resources, R.drawable.backcard);


    Canvas baseCanvas;

    //Context callingContext = getContext();


    public TrainCardBaseView(Context context, AttributeSet st) {
        super(context, st);
    }

    @Override
    public void onDraw(Canvas canvas){
        baseCanvas = canvas;
        //canvas.drawColor(Color.BLACK); //Draw a paint color, not really needed.
        float thickness = 4;
        float textSize = 35;
        paint.setStrokeWidth(thickness);


        canvas.drawBitmap(redTrainBitmap, screenRect, screenRect, null);
        canvas.drawBitmap(blueTrainBitmap, screenRect, screenRect, null);
        canvas.drawBitmap(blackTrainBitmap, screenRect, screenRect, null);
        canvas.drawBitmap(greenTrainBitmap, screenRect, screenRect, null);
        canvas.drawBitmap(orangeTrainBitmap, screenRect, screenRect, null);
        canvas.drawBitmap(purpleTrainBitmap, screenRect, screenRect, null);
        canvas.drawBitmap(whiteTrainBitmap, screenRect, screenRect, null);
        canvas.drawBitmap(yellowTrainBitmap, screenRect, screenRect, null);
        canvas.drawBitmap(wildTrainBitmap, screenRect, screenRect, null);
        canvas.drawBitmap(backCardBitmap, screenRect, screenRect, null);


    }
}
