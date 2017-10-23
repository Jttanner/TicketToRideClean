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
        fillCityPointList();
        float thickness = 10;
        paint.setStrokeWidth(thickness);
        paint.setColor(Color.BLACK);

        canvas.drawBitmap(mapBitmap, screenRect, screenRect, null);
        drawCities(canvas);
        drawCityLines(canvas);

        paint.setTextSize(thickness);
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

    List<CityDrawData> cities = new ArrayList<>();

    private void fillCityPointList(){
        CityDrawData Vancouver = new CityDrawData(VancouverPoint, "Vancouver");
        cities.add(Vancouver);
        CityDrawData Seattle = new CityDrawData(SeattlePoint, "Seattle");
        cities.add(Seattle);
        CityDrawData Portland = new CityDrawData(PortlandPoint, "Portland");
        cities.add(Portland);
        CityDrawData SanFrancisco = new CityDrawData(SanFranciscoPoint, "San Francisco");
        cities.add(SanFrancisco);
        CityDrawData LosAngeles = new CityDrawData(LosAngelesPoint, "Los Angeles");
        cities.add(LosAngeles);
        CityDrawData Phoenix = new CityDrawData(PhoenixPoint, "Phoenix");
        cities.add(Phoenix);
        CityDrawData LasVegas = new CityDrawData(LasVegasPoint, "Las Vegas");
        cities.add(LasVegas);
        CityDrawData SaltLakeCity = new CityDrawData(SLCPoint, "Salt Lake City");
        cities.add(SaltLakeCity);
        CityDrawData Helena = new CityDrawData(HelenaPoint, "Helena");
        cities.add(Helena);
        CityDrawData Calgary = new CityDrawData(CalgaryPoint, "Calgary");
        cities.add(Calgary);
        CityDrawData Winnipeg = new CityDrawData(WinnipegPoint, "Winnipeg");
        cities.add(Winnipeg);
        CityDrawData SaultStMarie = new CityDrawData(SaultStMariePoint, "Sault St Marie");
        cities.add(SaultStMarie);
        CityDrawData Montreal = new CityDrawData(MontrealPoint, "Montreal");
        cities.add(Montreal);
        CityDrawData Boston = new CityDrawData(BostonPoint, "Boston");
        cities.add(Boston);
        CityDrawData Toronto = new CityDrawData(TorontoPoint, "Toronto");
        cities.add(Toronto);
        CityDrawData Deluth = new CityDrawData(DeluthPoint, "Deluth");
        cities.add(Deluth);
        CityDrawData Denver = new CityDrawData(DenverPoint, "Denver");
        cities.add(Denver);
        CityDrawData SantaFe = new CityDrawData(SantaFePoint, "Santa Fe");
        cities.add(SantaFe);
        CityDrawData ElPaso = new CityDrawData(ElPasoPoint, "El Paso");
        cities.add(ElPaso);
        CityDrawData Houston = new CityDrawData(HoustonPoint, "Houston");
        cities.add(Houston);
        CityDrawData Dallas = new CityDrawData(DallasPoint, "Dallas");
        cities.add(Dallas);
        CityDrawData OklahomaCity = new CityDrawData(OklahomaCityPoint, "Oklahoma City");
        cities.add(OklahomaCity);
        CityDrawData KansasCity = new CityDrawData(KansasCityPoint, "Kansas City");
        cities.add(KansasCity);
        CityDrawData Omaha = new CityDrawData(OmahaPoint, "Omaha");
        cities.add(Omaha);
        CityDrawData Chicago = new CityDrawData(ChicagoPoint, "Chicago");
        cities.add(Chicago);
        CityDrawData Pittsburgh = new CityDrawData(PittsburghPoint, "Pittsburgh");
        cities.add(Pittsburgh);
        CityDrawData Washington = new CityDrawData(WashingtonPoint, "Washington");
        cities.add(Washington);
        CityDrawData NewYork = new CityDrawData(NewYorkPoint, "New York");
        cities.add(NewYork);
        CityDrawData Raleigh = new CityDrawData(RaleighPoint, "Raleigh");
        cities.add(Raleigh);
        CityDrawData Nashville = new CityDrawData(NashvillePoint, "Nashville");
        cities.add(Nashville);
        CityDrawData Atlanta = new CityDrawData(AtlantaPoint, "Atlanta");
        cities.add(Atlanta);
        CityDrawData Charleston = new CityDrawData(CharlestonPoint, "Charleston");
        cities.add(Charleston);
        CityDrawData Miami = new CityDrawData(MiamiPoint, "Miami");
        cities.add(Miami);
        CityDrawData NewOrleans = new CityDrawData(NewOrleansPoint, "New Orleans");
        cities.add(NewOrleans);
        CityDrawData LittleRock = new CityDrawData(LittleRockPoint, "Little Rock");
        cities.add(LittleRock);
        CityDrawData SaintLouis = new CityDrawData(SaintLouisPoint, "Saint Louis");
        cities.add(SaintLouis);

        //add connections
        Atlanta.addConnection(Miami);
        Atlanta.addConnection(NewOrleans);
        //second atlanta NO
        Boston.addConnection(Montreal);
        //second Boston Montreal
        Boston.addConnection(NewYork);
        //second B-NY
        Calgary.addConnection(Vancouver);
        Calgary.addConnection(Winnipeg);
        Calgary.addConnection(Helena);
        Calgary.addConnection(Seattle);
        Charleston.addConnection(Raleigh);
        Charleston.addConnection(Atlanta);
        Charleston.addConnection(Miami);
        Chicago.addConnection(Pittsburgh); //x2
        Chicago.addConnection(Toronto);
        Chicago.addConnection(Deluth);
        Chicago.addConnection(Omaha);
        Chicago.addConnection(SaintLouis); //x2
        Dallas.addConnection(LittleRock);
        Dallas.addConnection(OklahomaCity); //x2
        Dallas.addConnection(ElPaso);
        Dallas.addConnection(Houston); //x2
        Denver.addConnection(KansasCity); //x2
        Denver.addConnection(Omaha);
        Denver.addConnection(Helena);
        Denver.addConnection(SaltLakeCity); //x2
        Denver.addConnection(Phoenix);
        Denver.addConnection(SantaFe);
        Denver.addConnection(OklahomaCity);
        Deluth.addConnection(Omaha); //c2
        Deluth.addConnection(Chicago);
        Deluth.addConnection(Toronto);
        Deluth.addConnection(SaultStMarie);
        Deluth.addConnection(Winnipeg);
        Deluth.addConnection(Helena);
        ElPaso.addConnection(Houston);
        ElPaso.addConnection(OklahomaCity);
        ElPaso.addConnection(SantaFe);
        ElPaso.addConnection(Phoenix);
        ElPaso.addConnection(LosAngeles);
        Helena.addConnection(Winnipeg);
        Helena.addConnection(Omaha);
        Helena.addConnection(SaltLakeCity);
        Helena.addConnection(Seattle);
        Houston.addConnection(NewOrleans);
        Helena.addConnection(Winnipeg);
        Helena.addConnection(Omaha);
        Helena.addConnection(SaltLakeCity);
        Helena.addConnection(Seattle);
        KansasCity.addConnection(SaintLouis); //x2
        KansasCity.addConnection(Omaha); //x2
        KansasCity.addConnection(OklahomaCity); //x2
        LasVegas.addConnection(SaltLakeCity);
        LasVegas.addConnection(LosAngeles);
        LittleRock.addConnection(Nashville);
        LittleRock.addConnection(SaintLouis);
        LittleRock.addConnection(OklahomaCity);
        LittleRock.addConnection(NewOrleans);
        LosAngeles.addConnection(SanFrancisco); //2x
        LosAngeles.addConnection(Phoenix);
        Miami.addConnection(NewOrleans);
        Montreal.addConnection(NewYork);
        Montreal.addConnection(Toronto);
        Montreal.addConnection(SaultStMarie);
        Nashville.addConnection(Raleigh);
        Nashville.addConnection(Pittsburgh);
        Nashville.addConnection(SaintLouis);
        NewYork.addConnection(Washington); //2x
        NewYork.addConnection(Pittsburgh); //2x
        OklahomaCity.addConnection(SantaFe);
        Phoenix.addConnection(SantaFe);
        Pittsburgh.addConnection(Washington);
        Pittsburgh.addConnection(Raleigh);
        Pittsburgh.addConnection(SaintLouis);
        Pittsburgh.addConnection(Toronto);
        Portland.addConnection(Seattle); //2s
        Portland.addConnection(SaltLakeCity);
        Portland.addConnection(SanFrancisco);
        Raleigh.addConnection(Washington);
        SaltLakeCity.addConnection(SanFrancisco); //x2
        SaultStMarie.addConnection(Winnipeg);
        SaultStMarie.addConnection(Toronto);
        Seattle.addConnection(Vancouver); //2x



    }

    Point VancouverPoint = new Point(159, 135);
    Point SeattlePoint = new Point(167, 290);
    Point PortlandPoint = new Point(104,375);
    Point SanFranciscoPoint = new Point(185, 696);
    Point LosAngelesPoint = new Point(306, 815);
    Point PhoenixPoint = new Point(520, 825);
    Point LasVegasPoint =new Point(399, 696);
    Point SLCPoint = new Point(517, 559);
    Point HelenaPoint = new Point(627, 309);
    Point CalgaryPoint = new Point(391, 88);
    Point WinnipegPoint = new Point(914, 80);
    Point SaultStMariePoint = new Point(1384, 290);
    Point MontrealPoint = new Point(1818, 143);
    Point IDontKNowPoint = new Point(1873, 448);
    Point TorontoPoint = new Point(1578, 382);
    Point DeluthPoint = new Point(1118, 364);
    Point DenverPoint = new Point(722, 612);
    Point SantaFePoint = new Point(685, 744);
    Point ElPasoPoint = new Point(730, 917);
    Point HoustonPoint = new Point(1043, 999);
    Point DallasPoint = new Point(1051, 999);
    Point OklahomaCityPoint = new Point(1014, 925);
    Point KansasCityPoint = new Point(988, 723);
    Point OmahaPoint = new Point(1025, 595);
    Point ChicagoPoint = new Point(1014, 493);
    Point PittsburghPoint = new Point(1347, 511);
    Point BostonPoint = new Point(1597, 538);
    Point WashingtonPoint = new Point(1662, 686);
    Point NewYorkPoint = new Point(1773, 485);
    Point RaleighPoint = new Point(1589, 733);
    Point NashvillePoint = new Point(1339, 733);
    Point AtlantaPoint = new Point(1457, 833);
    Point CharlestonPoint = new Point(1578, 815);
    Point MiamiPoint = new Point(1541, 1138);
    Point NewOrleansPoint = new Point(1199, 862);
    Point LittleRockPoint = new Point(1155, 760);
    Point SaintLouisPoint = new Point(1191, 622);


    private void drawCityLines(Canvas canvas){
        for (CityDrawData data: cities){
            for(CityDrawData connection : data.connections){
                drawCityLine(data.city, connection.city, canvas);
                //canvas.drawLine(data.getX(), data.getY(), connection.getX(), connection.getY(), paint);
            }
        }
    }

    private void drawCityLine(Point p1, Point p2, Canvas canvas){
        canvas.drawLine(p1.x, p1.y, p2.x, p2.y, paint);
    }

    private void drawCities(Canvas canvas){
        int diameter = 10;
        for (CityDrawData data: cities){
            canvas.drawCircle(data.getX(), data.getY(), diameter, paint);
            canvas.drawText(data.cityName, data.getX(), data.getY(), paint);
        }
        /*canvas.drawCircle(Vancouver.x, Vancouver.y, 10, paint);
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
        canvas.drawCircle(ElPaso.x, ElPaso.y, 10, paint);
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
        */
    }


    private void drawFourCarLine(Point p1, Point p2, Canvas canvas){
        Point temp = new Point(p2.x/4, p2.y/4);
        for (int i = 0; i < 4; ++i){

            temp.set(temp.x * i - 10, temp.y *i - 10);
            canvas.drawLine(p1.x, p1.y, temp.x, temp.y, paint);

        }
    }


    private class CityDrawData{
        CityDrawData(Point city, String cityName){
            this.city = city;
            this.cityName = cityName;
        }

        String cityName;

        Point city;
        List<CityDrawData> connections = new ArrayList<>();

        public float getX(){
            return city.x;
        }

        public float getY(){
            return city.y;
        }

        public void addConnection(CityDrawData otherCity){
            connections.add(otherCity);
        }

        public void drawCity(Canvas canvas){
            canvas.drawCircle(city.x, city.y, 10, paint);
        }

        public void drawCityConnections(Canvas canvas){
            for (CityDrawData p : connections){
                canvas.drawLine(city.x, city.y, p.getX(), p.getY(), paint);
            }
        }

    }

}
