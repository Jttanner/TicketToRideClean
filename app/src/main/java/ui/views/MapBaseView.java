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

    static int DOUBLE_ROUTE_OFFSET = 6;

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
    Point HoustonPoint = new Point(1000, 999);
    Point DallasPoint = new Point(1051, 999);
    Point OklahomaCityPoint = new Point(1014, 725);
    Point KansasCityPoint = new Point(988, 640);
    Point OmahaPoint = new Point(1025, 480);
    Point ChicagoPoint = new Point(1114, 493);
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

    WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    Paint paint = new Paint();
    Rect screenRect = new Rect(0, 0, display.getWidth(), display.getHeight());
    Resources resources = getResources();
    Bitmap mapBitmap = BitmapFactory.decodeResource(resources, R.drawable.usamap);
    Paint cityNames = new Paint();
    List<Point> touchCoords = new ArrayList<>();

    Canvas baseCanvas;

    //Context callingContext = getContext();


    public MapBaseView(Context context, AttributeSet st) {
        super(context, st);
    }

    @Override
    public void onDraw(Canvas canvas){
        baseCanvas = canvas;
        //canvas.drawColor(Color.BLACK); //Draw a paint color, not really needed.
        fillCityPointList();
        float thickness = 4;
        float textSize = 35;
        paint.setStrokeWidth(thickness);
        paint.setColor(Color.LTGRAY);
        cityNames.setTextSize(textSize);
        cityNames.setColor(Color.RED);


        canvas.drawBitmap(mapBitmap, screenRect, screenRect, null);
        drawCityLines(canvas);
        drawCities(canvas);

        //claimRoute(Phoenix, Denver, "red");
        //claimRoute(LasVegas, SaltLakeCity, "blue");
        //claimRoute(NewYork, Nashville, "green");
        //claimRoute(LosAngeles, SanFrancisco, "yellow");
        //claimRoute(Nashville, Omaha, "black");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int xTouch = Math.round(event.getX());
        int yTouch = Math.round(event.getY());
        touchCoords.add(new Point(xTouch, yTouch));
        return true;
    }


    List<CityDrawData> cities = new ArrayList<>();
    CityDrawData Vancouver = new CityDrawData(VancouverPoint, "Vancouver");
    CityDrawData Seattle = new CityDrawData(SeattlePoint, "Seattle");
    CityDrawData Portland = new CityDrawData(PortlandPoint, "Portland");
    CityDrawData SanFrancisco = new CityDrawData(SanFranciscoPoint, "San Francisco");
    CityDrawData LosAngeles = new CityDrawData(LosAngelesPoint, "Los Angeles");
    CityDrawData Phoenix = new CityDrawData(PhoenixPoint, "Phoenix");
    CityDrawData LasVegas = new CityDrawData(LasVegasPoint, "Las Vegas");
    CityDrawData SaltLakeCity = new CityDrawData(SLCPoint, "Salt Lake City");
    CityDrawData Helena = new CityDrawData(HelenaPoint, "Helena");
    CityDrawData Calgary = new CityDrawData(CalgaryPoint, "Calgary");
    CityDrawData Winnipeg = new CityDrawData(WinnipegPoint, "Winnipeg");
    CityDrawData SaultStMarie = new CityDrawData(SaultStMariePoint, "Sault St Marie");
    CityDrawData Montreal = new CityDrawData(MontrealPoint, "Montreal");
    CityDrawData Boston = new CityDrawData(BostonPoint, "Boston");
    CityDrawData Toronto = new CityDrawData(TorontoPoint, "Toronto");
    CityDrawData Deluth = new CityDrawData(DeluthPoint, "Deluth");
    CityDrawData Denver = new CityDrawData(DenverPoint, "Denver");
    CityDrawData SantaFe = new CityDrawData(SantaFePoint, "Santa Fe");
    CityDrawData ElPaso = new CityDrawData(ElPasoPoint, "El Paso");
    CityDrawData Houston = new CityDrawData(HoustonPoint, "Houston");
    CityDrawData Dallas = new CityDrawData(DallasPoint, "Dallas");
    CityDrawData OklahomaCity = new CityDrawData(OklahomaCityPoint, "Oklahoma City");
    CityDrawData KansasCity = new CityDrawData(KansasCityPoint, "Kansas City");
    CityDrawData Omaha = new CityDrawData(OmahaPoint, "Omaha");
    CityDrawData Chicago = new CityDrawData(ChicagoPoint, "Chicago");
    CityDrawData Pittsburgh = new CityDrawData(PittsburghPoint, "Pittsburgh");
    CityDrawData Washington = new CityDrawData(WashingtonPoint, "Washington");
    CityDrawData NewYork = new CityDrawData(NewYorkPoint, "New York");
    CityDrawData Raleigh = new CityDrawData(RaleighPoint, "Raleigh");
    CityDrawData Nashville = new CityDrawData(NashvillePoint, "Nashville");
    CityDrawData Atlanta = new CityDrawData(AtlantaPoint, "Atlanta");
    CityDrawData Charleston = new CityDrawData(CharlestonPoint, "Charleston");
    CityDrawData Miami = new CityDrawData(MiamiPoint, "Miami");
    CityDrawData NewOrleans = new CityDrawData(NewOrleansPoint, "New Orleans");
    CityDrawData LittleRock = new CityDrawData(LittleRockPoint, "Little Rock");
    CityDrawData SaintLouis = new CityDrawData(SaintLouisPoint, "Saint Louis");

    private void fillCityPointList(){

        cities.add(Vancouver);

        cities.add(Seattle);

        cities.add(Portland);

        cities.add(SanFrancisco);

        cities.add(LosAngeles);

        cities.add(Phoenix);

        cities.add(LasVegas);

        cities.add(SaltLakeCity);

        cities.add(Helena);

        cities.add(Calgary);

        cities.add(Winnipeg);

        cities.add(SaultStMarie);

        cities.add(Montreal);

        cities.add(Boston);

        cities.add(Toronto);

        cities.add(Deluth);

        cities.add(Denver);

        cities.add(SantaFe);

        cities.add(ElPaso);

        cities.add(Houston);

        cities.add(Dallas);

        cities.add(OklahomaCity);

        cities.add(KansasCity);

        cities.add(Omaha);

        cities.add(Chicago);

        cities.add(Pittsburgh);

        cities.add(Washington);

        cities.add(NewYork);

        cities.add(Raleigh);

        cities.add(Nashville);

        cities.add(Atlanta);

        cities.add(Charleston);

        cities.add(Miami);

        cities.add(NewOrleans);

        cities.add(LittleRock);

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
        Chicago.addDoubleConnection(Pittsburgh); //x2
        Chicago.addConnection(Toronto);
        Chicago.addConnection(Deluth);
        Chicago.addConnection(Omaha);
        Chicago.addDoubleConnection(SaintLouis); //x2
        Dallas.addConnection(LittleRock);
        Dallas.addDoubleConnection(OklahomaCity); //x2
        Dallas.addConnection(ElPaso);
        Dallas.addDoubleConnection(Houston); //x2
        Denver.addDoubleConnection(KansasCity); //x2
        Denver.addConnection(Omaha);
        Denver.addConnection(Helena);
        Denver.addDoubleConnection(SaltLakeCity); //x2
        Denver.addConnection(Phoenix);
        Denver.addConnection(SantaFe);
        Denver.addConnection(OklahomaCity);
        Deluth.addDoubleConnection(Omaha); //c2
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
        KansasCity.addDoubleConnection(SaintLouis); //x2
        KansasCity.addDoubleConnection(Omaha); //x2
        KansasCity.addDoubleConnection(OklahomaCity); //x2
        LasVegas.addConnection(SaltLakeCity);
        LasVegas.addConnection(LosAngeles);
        LittleRock.addConnection(Nashville);
        LittleRock.addConnection(SaintLouis);
        LittleRock.addConnection(OklahomaCity);
        LittleRock.addConnection(NewOrleans);
        LosAngeles.addDoubleConnection(SanFrancisco); //2x
        LosAngeles.addConnection(Phoenix);
        Miami.addConnection(NewOrleans);
        Montreal.addConnection(NewYork);
        Montreal.addConnection(Toronto);
        Montreal.addConnection(SaultStMarie);
        Nashville.addConnection(Raleigh);
        Nashville.addConnection(Pittsburgh);
        Nashville.addConnection(SaintLouis);
        NewYork.addDoubleConnection(Washington); //2x
        NewYork.addDoubleConnection(Pittsburgh); //2x
        OklahomaCity.addConnection(SantaFe);
        Phoenix.addConnection(SantaFe);
        Pittsburgh.addConnection(Washington);
        Pittsburgh.addConnection(Raleigh);
        Pittsburgh.addConnection(SaintLouis);
        Pittsburgh.addConnection(Toronto);
        Portland.addDoubleConnection(Seattle); //2s
        Portland.addConnection(SaltLakeCity);
        Portland.addConnection(SanFrancisco);
        Raleigh.addConnection(Washington);
        SaltLakeCity.addDoubleConnection(SanFrancisco); //x2
        SaultStMarie.addConnection(Winnipeg);
        SaultStMarie.addConnection(Toronto);
        Seattle.addDoubleConnection(Vancouver); //2x



    }




    private void drawCityLines(Canvas canvas){
        for (CityDrawData data : cities){
            for(CityDrawData connection : data.connections){
                drawCityConnection(data.city, connection.city, canvas);
            }
            for(DoubleConnectionDrawData doubleConnection : data.doubleConnections){
                drawDoubleCityConnection(data.city, doubleConnection.connectedCityDrawData.city, canvas);
            }
        }
        for (ClaimedRoute route : claimedRoutes){
            claimRoute(route.city1, route.city2, route.claimedColor, route.doubleRoute, route.hasOneLine);
        }

    }

    List<ClaimedRoute> claimedRoutes = new ArrayList<>();

    public void addClaimedRoute(Point city1, String city1Name, Point city2, String city2Name, String color, boolean isDouble){
        ClaimedRoute claimedRoute = new ClaimedRoute(city1, city1Name, city2, city2Name);
        claimedRoute.setClaimedColor(color);
        claimedRoute.doubleRoute = isDouble;
        claimedRoutes.add(claimedRoute);
        this.invalidate();
    }

    public boolean claimRoute(Point p1, Point p2, String color, boolean isDoubleRoute, boolean hasOneRouteClaimed){
        switch (color){
            case "red":
                paint.setColor(Color.RED);
                break;
            case "blue":
                paint.setColor(Color.BLUE);
                break;
            case "black":
                paint.setColor(Color.BLACK);
                break;
            case "yellow":
                paint.setColor(Color.YELLOW);
                break;
            case "green":
                paint.setColor(Color.GREEN);
                break;
            default:
                paint.setColor(Color.LTGRAY);
        }
        if (isDoubleRoute){
            Point offsetP1 = new Point(p1);
            Point offsetP2 = new Point(p2);
            if (hasOneRouteClaimed){
                offsetP1.set(p1.x + DOUBLE_ROUTE_OFFSET, p1.y + DOUBLE_ROUTE_OFFSET);
                offsetP2.set(p2.x + DOUBLE_ROUTE_OFFSET, p2.y + DOUBLE_ROUTE_OFFSET);

            } else{
                offsetP1.set(p1.x - DOUBLE_ROUTE_OFFSET, p1.y - DOUBLE_ROUTE_OFFSET);
                offsetP2.set(p2.x - DOUBLE_ROUTE_OFFSET, p2.y - DOUBLE_ROUTE_OFFSET);
            }
            drawCityConnection(offsetP1, offsetP2, baseCanvas);

        } else{
            drawCityConnection(p1, p2, baseCanvas);
        }
        paint.setColor(Color.LTGRAY);
        return true;
    }

    private void drawCityConnection(Point p1, Point p2, Canvas canvas){
        canvas.drawLine(p1.x, p1.y, p2.x, p2.y, paint);
    }

    private void drawDoubleCityConnection(Point p1, Point p2, Canvas canvas){
        canvas.drawLine(p1.x - DOUBLE_ROUTE_OFFSET, p1.y - DOUBLE_ROUTE_OFFSET, p2.x - DOUBLE_ROUTE_OFFSET, p2.y - DOUBLE_ROUTE_OFFSET, paint);
        canvas.drawLine(p1.x + DOUBLE_ROUTE_OFFSET, p1.y + DOUBLE_ROUTE_OFFSET, p2.x + DOUBLE_ROUTE_OFFSET, p2.y + DOUBLE_ROUTE_OFFSET, paint);

    }

    private void drawCities(Canvas canvas){
        int diameter = 10;
        for (CityDrawData data: cities){
            canvas.drawCircle(data.getX(), data.getY(), diameter, paint);
            canvas.drawText(data.cityName, data.getX() - 50, data.getY() - 15, cityNames);
        }

    }


    private void drawFourCarLine(Point p1, Point p2, Canvas canvas){
        Point temp = new Point(p2.x/4, p2.y/4);
        for (int i = 0; i < 4; ++i){

            temp.set(temp.x * i - 10, temp.y *i - 10);
            canvas.drawLine(p1.x, p1.y, temp.x, temp.y, paint);

        }
    }


    private class DoubleConnectionDrawData{
        CityDrawData connectedCityDrawData;

        boolean oneClaimed = false;

        public DoubleConnectionDrawData(CityDrawData connectedCityDrawData){
            this.connectedCityDrawData = connectedCityDrawData;
        }
    }


    //TODO: TAKE THESE PRIVATE CLASSES AND PUT THEM IN CLINET MODELLING ONCE ALL IS WORKING WELL, EXPLAIN TO EVERYBODY
    //THIS MUST GO ON CLIENT-SIDE MODELLING UNLESS I WANT TO CHANGE POINTS TO X Y COORDS
    public class ClaimedRoute{
        Point city1;
        Point city2;
        String cityName1;
        String cityName2;

        String claimedColor;

        boolean doubleRoute;

        boolean hasOneLine = false;

        ClaimedRoute(Point city1, String cityName1, Point city2, String cityName2){
            this.city1 = city1;
            this.cityName1 = cityName1;
            this.city2 = city2;
            this.cityName2 = cityName2;
        }

        void setClaimedColor(String color){
            this.claimedColor = color;
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

        List<DoubleConnectionDrawData> doubleConnections = new ArrayList<>();

        public float getX(){
            return city.x;
        }

        public float getY(){
            return city.y;
        }

        public void addConnection(CityDrawData otherCity){
            connections.add(otherCity);
        }

        public void addDoubleConnection(CityDrawData otherCity){
            doubleConnections.add(new DoubleConnectionDrawData(otherCity));
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
