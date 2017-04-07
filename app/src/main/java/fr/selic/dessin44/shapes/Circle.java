package fr.selic.dessin44.shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import fr.selic.dessin44.activity.TouchImageView;

/**
 * Created by stg2 on 06/04/2017.
 */

public class Circle implements Shape {

    private float cx;
    private float cy;
    private float radius;
    private float zoom;
    private int nbInit;
    private int nbTasks;
    private int nb=1;
    private String orientation;



    //constructor
    public Circle(float cx, float cy, float radius, int nbInit, int nbTasks, String orientation){
        this.cx=cx;
        this.cy=cy;
        this.radius=radius;
        this.nbInit=nbInit;
        this.nbTasks=nbTasks;
        this.orientation=orientation;
    }

    /*@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(cx);
        dest.writeFloat(cy);
        dest.writeFloat(radius);
        dest.writeInt(nbInit);
        dest.writeInt(nbTasks);
        dest.writeString(orientation);
    }*/


    //override method herited from From
    @Override
    public void drawOnImage(Canvas canvas) {
        int strokeWidth=3;
        int rectSize=30;
        int textSize=22;

        Paint paintCircle= new Paint();
        Paint paintText = new Paint();
        Paint paintRect = new Paint();
        Paint paint = new Paint();

        //settings of the different paints
        paintCircle.setStrokeWidth(4);
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setColor(Color.BLUE);

        paintRect.setColor(Color.RED);

        paintText.setStrokeWidth(strokeWidth);
        paintText.setStyle(Paint.Style.STROKE);
        paintText.setColor(Color.YELLOW);
        paintText.setTextSize(textSize);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);

        //drawing of the blue circle
        canvas.drawCircle(cx,cy,radius,paintCircle);

        //placement of the rectangle(s) around the circle
        do{
            float dec = (nb-1)*rectSize;
            switch(orientation){
                case "b" :
                    //bottom
                    canvas.drawRect(cx, cy+dec+radius, cx+rectSize, cy+radius+rectSize+dec, paintRect);
                    canvas.drawText(Integer.toString(nbInit),cx+(rectSize/3),cy+dec+radius+rectSize-strokeWidth*3,paintText);
                    canvas.drawRect(cx+rectSize,cy+dec+radius,cx+rectSize*2,cy+radius+dec+rectSize,paint);
                    break;
                case "t" :
                    //top
                    canvas.drawRect(cx-rectSize+dec,cy-radius-rectSize,cx+dec,cy-radius,paintRect);
                    canvas.drawText(Integer.toString(nbInit),cx-(rectSize/3)-strokeWidth*3+dec,cy-radius-strokeWidth*3,paintText);
                    canvas.drawRect(cx-rectSize+dec,cy-radius-rectSize*2,cx+dec,cy-radius-rectSize,paint);
                    break;
                case "r" :
                    //right
                    canvas.drawRect(cx+radius, cy-rectSize+dec, cx+radius+rectSize, cy+dec, paintRect);
                    canvas.drawText(Integer.toString(nbInit),cx+radius+strokeWidth*3,cy-strokeWidth*3+dec,paintText);
                    canvas.drawRect(cx+radius+rectSize,cy-rectSize+dec,cx+radius+rectSize*2,cy+dec,paint);
                    break;
                case "l" :
                    //left
                    canvas.drawRect(cx-radius-rectSize, cy-rectSize+dec, cx-radius, cy+dec, paintRect);
                    canvas.drawText(Integer.toString(nbInit),cx-radius-rectSize+strokeWidth*3,cy-strokeWidth*3+dec,paintText);
                    canvas.drawRect(cx-radius-rectSize*2,cy-rectSize+dec,cx-radius-rectSize,cy+dec,paint);
                    break;
            }

            nb++;
            nbInit++;
        }while(nb<=nbTasks);
    }


    //method that add a calc on a Shape in function the shape's type and also of the button pressed in the tableLayout view
    @Override
    public Canvas addOnImage(DrawView drawView, TouchImageView imageView, Canvas canvas, char index) {

        //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.SRC);

        Paint paintAdd = new Paint();
        Paint paintB = new Paint();

        paintAdd.setAlpha(255);

        paintB.setColor(Color.BLACK);
        paintB.setStrokeWidth(5);
        paintB.setStyle(Paint.Style.STROKE);

        switch(index){
            //OK
            case 'o' :
                paintAdd.setColor(Color.GREEN);
                canvas.drawCircle(cx,cy,radius,paintAdd);
                canvas.drawLine(cx-(radius-30),cy-(radius-30),cx+(radius-30),cy+(radius-30),paintB);
                canvas.drawLine(cx+(radius-30),cy-(radius-30),cx-(radius-30),cy+(radius-30),paintB);
                break;
            //KO
            case 'k' :
                paintAdd.setColor(Color.RED);
                canvas.drawCircle(cx,cy,radius,paintAdd);
                canvas.drawCircle(cx,cy,radius-30,paintB);
                break;
            //OK(x)
            case 'x' :
                paintAdd.setColor(Color.GREEN);
                canvas.drawCircle(cx,cy,radius,paintAdd);
                canvas.drawCircle(cx,cy,radius-30,paintB);
                canvas.drawLine(cx-(radius-30),cy-(radius-30),cx+(radius-30),cy+(radius-30),paintB);
                canvas.drawLine(cx+(radius-30),cy-(radius-30),cx-(radius-30),cy+(radius-30),paintB);
                break;
            //OK(t)
            case 't' :
                paintAdd.setColor(Color.RED);
                canvas.drawCircle(cx,cy,radius,paintAdd);
                canvas.drawCircle(cx,cy,radius-30,paintB);
                canvas.drawLine(cx-(radius-60),cy-(radius-75),cx-(radius-60)+85,cy-(radius-75),paintB);
                canvas.drawLine(cx,cy-(radius-75),cx,cy-(radius-75)+80,paintB);
                break;
            //NOT DONE
            case 'd' :
                paintAdd.setColor(Color.WHITE);
                canvas.drawCircle(cx,cy,radius,paintAdd);
                canvas.drawCircle(cx,cy,radius-15,paintB);
                break;
            //NO
            case 'n' :
                int dec = 15;
                paintAdd.setColor(Color.RED);
                canvas.drawCircle(cx,cy,radius,paintAdd);
                canvas.drawLine(cx-(radius/3)-dec,cy+(radius/3),cx-(radius/3)-dec,cy-(radius/3),paintB);
                canvas.drawLine(cx-(radius/3)-dec,cy-(radius/3),cx-dec,cy+(radius/3),paintB);
                canvas.drawLine(cx-dec,cy+(radius/3),cx-dec,cy-(radius/3),paintB);
                canvas.drawCircle(cx+(radius/3),cy,radius/3,paintB);
                break;
            //|||||||
            case 'p' :
                paintAdd.setColor(Color.WHITE);
                paintB.setStrokeWidth(2);
                canvas.drawCircle(cx,cy,radius,paintAdd);
                for(int i=0;i<20;i++){
                    canvas.drawLine(cx-(radius/2)+5*i,cy-(radius/2),cx-(radius/2)+5*i,cy+(radius/2),paintB);
                }
                break;
        }
        return canvas;
    }

    //check if the touchEvent is in the Circle
    @Override
    public boolean contains( float x, float y, PointF pointZoom, float zoom) {
        double dist = distance(x,y,cx,cy);
        if(dist<=radius)
            return true;
        else
            return false;
    }

    public double distance(double x1, double y1, double x2, double y2) {
        return ( Math.sqrt(Math.pow(x1-x2,2)+ Math.pow(y1-y2,2))) ;
    }

    public float getCy() {
        return cy;
    }

    public float getRadius() {
        return radius;
    }

    public float getCx() {
        return cx;
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }


}
