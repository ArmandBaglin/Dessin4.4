package fr.selic.dessin44.shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;

import fr.selic.dessin44.activity.TouchImageView;

/**
 * Created by stg2 on 06/04/2017.
 */

public class Rectangle implements Shape {
    private float x0;
    private float y0;
    private float x1;
    private float y1;


    //constructor
    public Rectangle(float x0, float y0, float x1, float y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }

    //override method herited from Shape interface
    @Override
    public void drawOnImage(Canvas pCanvas, Shape pShape, TaskShape pTaskShape) {
        int strokeWidth = 3;
        float rectSize = 30;
        int textSize = 22;

        Paint paint = new Paint();
        Paint paintRectangle = new Paint();
        Paint paintRect = new Paint();
        Paint paintText = new Paint();

        //settings of the different paints
        paintRectangle.setStyle(Paint.Style.STROKE);
        paintRectangle.setStrokeWidth(4);
        paintRectangle.setColor(Color.BLACK);

        //paintRect.
        paintRect.setColor(Color.RED);

        paintText.setStrokeWidth(strokeWidth);
        paintText.setStyle(Paint.Style.STROKE);
        paintText.setColor(Color.YELLOW);
        paintText.setTextSize(textSize);


        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        //drawing of a Rectangle
        pCanvas.drawRect(x0, y0, x1, y1, paintRectangle);
        pTaskShape.addTaskRectOnShape(pTaskShape,pCanvas);

    }

    //method that add a calc on a Shape in function the shape's type and also of the button pressed in the tableLayout view
    @Override
    public Canvas addOnImage(DrawView pDrawView, TouchImageView pImageView, Canvas pCanvas, char pIndex) {

        Paint paintAdd = new Paint();
        Paint paintB = new Paint();
        Paint paintT = new Paint();
        paintT.setColor(Color.TRANSPARENT);

        paintAdd.setAlpha(255);

        paintB.setStyle(Paint.Style.STROKE);
        paintB.setColor(Color.BLACK);
        paintB.setStrokeWidth(5);

        //in function of the pressed button
        switch(pIndex){
            //OK
            case 'o' :
                paintAdd.setColor(Color.GREEN);
                pCanvas.drawRect(x0,y0,x1,y1,paintAdd);
                pCanvas.drawLine(x0,y0,x1,y1,paintB);
                pCanvas.drawLine(x1,y0,x0,y1,paintB);
                break;
            //KO
            case 'k' :
                paintAdd.setColor(Color.RED);
                pCanvas.drawRect(x0,y0,x1,y1,paintAdd);
                pCanvas.drawCircle((x1+x0)/2,(y1+y0)/2,(y1-y0)/2,paintB);
                break;
            //OK(x)
            case 'x' :
                paintAdd.setColor(Color.GREEN);
                pCanvas.drawRect(x0,y0,x1,y1,paintAdd);
                pCanvas.drawCircle((x1+x0)/2,(y1+y0)/2,(y1-y0)/2,paintB);
                pCanvas.drawLine(x0,y0,x1,y1,paintB);
                pCanvas.drawLine(x1,y0,x0,y1,paintB);
                break;
            //OK(t)
            case 't' :
                paintAdd.setColor(Color.RED);
                pCanvas.drawRect(x0,y0,x1,y1,paintAdd);
                pCanvas.drawLine(x0+60,y0+40,x0+140,y0+40,paintB);
                pCanvas.drawLine((x0+x1)/2,y0+40,(x0+x1)/2,y0+140,paintB);
                break;
            //NOT DONE
            case 'd' :
                paintAdd.setColor(Color.WHITE);
                pCanvas.drawRect(x0,y0,x1,y1,paintAdd);
                pCanvas.drawCircle((x1+x0)/2,(y1+y0)/2,(x1-x0)/2,paintB);
                break;
            //NO
            case 'n' :
                float distX=x1-x0;
                float distY=y1-y0;
                float quartDX=distX/4;
                float quartDY=distY/4;
                int dec=20;
                paintAdd.setColor(Color.RED);
                pCanvas.drawRect(x0,y0,x1,y1,paintAdd);
                pCanvas.drawLine(quartDX+x0-dec,quartDY+y0,quartDX+x0-dec,quartDY*3+y0,paintB);
                pCanvas.drawLine(quartDX+x0-dec,quartDY+y0,distX/2+x0-dec,quartDY*3+y0,paintB);
                pCanvas.drawLine(distX/2+x0-dec,quartDY*3+y0,distX/2+x0-dec,quartDY+y0,paintB);
                pCanvas.drawCircle(x1-quartDX,y1-distY/2,(quartDX*3)/4,paintB);
                break;
            //||||||||
            case 'p' :
                paintAdd.setColor(Color.WHITE);
                paintB.setStrokeWidth(2);
                pCanvas.drawRect(x0,y0,x1,y1,paintAdd);
                for(int i=0;i<(x1-x0)/5;i++){
                    pCanvas.drawLine(x0+5*i,y0,x0+5*i,y1,paintB);
                }
                break;
        }
        return pCanvas;
    }

    //check if the touchEvent is in the Rectangle
    @Override
    public boolean contains(float pX, float pY, PointF point0Zoom, float zoom ) {
        float x0Z = point0Zoom.x;
        float y0Z = point0Zoom.y;
        float x0Zoom = (x0-x0Z)*zoom;
        float y0Zoom = (y0-y0Z)*zoom;
        float x1Zoom = (x1-x0Z)*zoom;
        float y1Zoom = (y1-y0Z)*zoom;
        float x0T = pX;
        float y0T = pY;

        Log.i("Message","x0: "+x0Z+" y0: "+y0Z);
        Log.i("Message","Zoom x0: "+x0Zoom+"    x0T: "+x0T+"    x1: "+x1Zoom);
        Log.i("Message","Zoom y0: "+y0Zoom+"    y0T: "+y0T+"    y1: "+y1Zoom);
        return (x0T > x0Zoom || x0T == x0Zoom) && (x0T < x1Zoom || x0T == x1Zoom) && (y0T > y0Zoom || y0T == y0Zoom) && (y0T < y1Zoom || y0T == y1Zoom);
    }

    public float getX0() {
        return x0;
    }

    public float getY0() {
        return y0;
    }

    public float getX1() {
        return x1;
    }

    public float getY1() {
        return y1;
    }

}