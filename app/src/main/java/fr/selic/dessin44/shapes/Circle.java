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



    //constructor
    public Circle(float cx, float cy, float radius){
        this.cx=cx;
        this.cy=cy;
        this.radius=radius;
    }


    //override method herited from From
    @Override
    public void drawOnImage(Canvas pCanvas, Shape pShape, TaskShape pTaskShape) {
        Paint paintCircle= new Paint();

        //settings of the different paints
        paintCircle.setStrokeWidth(4);
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setColor(Color.BLUE);

        //drawing of the blue circle
        pCanvas.drawCircle(cx,cy,radius,paintCircle);
        pTaskShape.addTaskRectOnShape(pTaskShape,pCanvas);
    }


    //method that add a calc on a Shape in function the shape's type and also of the button pressed in the tableLayout view
    @Override
    public Canvas addOnImage(DrawView drawView, TouchImageView imageView, Canvas canvas, char index) {


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
    public boolean contains( float pX, float pY, PointF point0Zoom, float zoom) {
        float cXZ = point0Zoom.x;
        float cYZ = point0Zoom.y;
        float cXZoom = (cx-cXZ)*zoom;
        float cYZoom = (cy-cYZ)*zoom;
        double dist = distance(pX,pY,cXZoom,cYZoom);

        return (dist<=radius*zoom);
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
