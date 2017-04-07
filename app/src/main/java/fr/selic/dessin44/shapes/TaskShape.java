package fr.selic.dessin44.shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by stg2 on 07/04/2017.
 */

//class that draw the task rectangles around a Shape
public class TaskShape {
    private Shape shape;
    private int nbInit;
    private int nbTasks;
    private String orientation;

    //constructor
    public TaskShape(Shape shape, int nbInit, int nbTasks, String orientation ){
        this.shape=shape;
        this.nbInit=nbInit;
        this.nbTasks=nbTasks;
        this.orientation=orientation;
    }

    //method that draw the tasks rectangles
    public void addTaskRectOnShape(TaskShape pTaskShape, Canvas pCanvas){
        int strokeWidth=3;
        int rectSize=30;
        int textSize=22;
        int nb=0;

        //retrieval of the Shape
        Shape pShape = pTaskShape.getShape();

        //Paints
        Paint paintText = new Paint();
        Paint paintRect = new Paint();
        Paint paint = new Paint();

        paintRect.setColor(Color.RED);

        paintText.setStrokeWidth(strokeWidth);
        paintText.setStyle(Paint.Style.STROKE);
        paintText.setColor(Color.YELLOW);
        paintText.setTextSize(textSize);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);

        //if the Shape is a Circle
        if(pShape instanceof Circle){

            //retrieval of the parameters of the circle
            float cx=((Circle) pShape).getCx();
            float cy=((Circle) pShape).getCy();
            float radius=((Circle) pShape).getRadius();

            //placement of the rectangle(s) around the circle
            do{
                float dec = nb*rectSize;
                switch(orientation){
                    case "b" :
                        //bottom
                        pCanvas.drawRect(cx, cy+dec+radius, cx+rectSize, cy+radius+rectSize+dec, paintRect);
                        pCanvas.drawText(Integer.toString(nbInit),cx+(rectSize/3),cy+dec+radius+rectSize-strokeWidth*3,paintText);
                        pCanvas.drawRect(cx+rectSize,cy+dec+radius,cx+rectSize*2,cy+radius+dec+rectSize,paint);
                        break;
                    case "t" :
                        //top
                        pCanvas.drawRect(cx-rectSize+dec,cy-radius-rectSize,cx+dec,cy-radius,paintRect);
                        pCanvas.drawText(Integer.toString(nbInit),cx-(rectSize/3)-strokeWidth*3+dec,cy-radius-strokeWidth*3,paintText);
                        pCanvas.drawRect(cx-rectSize+dec,cy-radius-rectSize*2,cx+dec,cy-radius-rectSize,paint);
                        break;
                    case "r" :
                        //right
                        pCanvas.drawRect(cx+radius, cy-rectSize+dec, cx+radius+rectSize, cy+dec, paintRect);
                        pCanvas.drawText(Integer.toString(nbInit),cx+radius+strokeWidth*3,cy-strokeWidth*3+dec,paintText);
                        pCanvas.drawRect(cx+radius+rectSize,cy-rectSize+dec,cx+radius+rectSize*2,cy+dec,paint);
                        break;
                    case "l" :
                        //left
                        pCanvas.drawRect(cx-radius-rectSize, cy-rectSize+dec, cx-radius, cy+dec, paintRect);
                        pCanvas.drawText(Integer.toString(nbInit),cx-radius-rectSize+strokeWidth*3,cy-strokeWidth*3+dec,paintText);
                        pCanvas.drawRect(cx-radius-rectSize*2,cy-rectSize+dec,cx-radius-rectSize,cy+dec,paint);
                        break;
                }
                nb++;
                nbInit++;
            }while(nb<=nbTasks);

        //if the SHape is a Rectangle
        }else if(pShape instanceof Rectangle){

            //retrieval of the parameters of the Rectangle
            float x0= ((Rectangle) pShape).getX0();
            float y0= ((Rectangle) pShape).getY0();
            float x1= ((Rectangle) pShape).getX1();
            float y1= ((Rectangle) pShape).getY1();
            do{
                float dec = nb*rectSize;
                //red rectangles around the rectangle
                switch(orientation){
                    case "b" :
                        //bottom
                        pCanvas.drawRect(((x1+x0)/2), y1+dec, ((x1+x0)/2)+rectSize, y1+dec+rectSize, paintRect);
                        pCanvas.drawText(Integer.toString(nbInit), (x1+x0)/2+strokeWidth*3, y1+dec+rectSize-strokeWidth*3, paintText);
                        pCanvas.drawRect((x1+x0)/2+rectSize, y1+dec, ((x1+x0)/2)+rectSize*2, y1+dec+rectSize,paint);
                        break;
                    case "t" :
                        //top
                        pCanvas.drawRect(((x1+x0)/2)-rectSize+dec,y0-rectSize,((x1+x0)/2)+dec,y0,paintRect);
                        pCanvas.drawText(Integer.toString(nbInit), ((x1+x0)/2)-(rectSize/3)+dec-2*strokeWidth, (y0-strokeWidth), paintText);
                        pCanvas.drawRect(((x1+x0)/2)-rectSize+dec,y0-rectSize*2,((x1+x0)/2)+dec,y0-rectSize,paint);
                        break;
                    case "r" :
                        //right
                        pCanvas.drawRect(x1,((y1+y0)/2)-rectSize+dec,x1+rectSize,((y0+y1)/2)+dec,paintRect);
                        pCanvas.drawText(Integer.toString(nbInit), x1+strokeWidth*3, ((y1+y0)/2)-strokeWidth*3+dec, paintText);
                        pCanvas.drawRect(x1+rectSize,((y1+y0)/2)-rectSize+dec,x1+rectSize*2,((y0+y1)/2)+dec,paint);
                        break;
                    case "l" :
                        //left
                        pCanvas.drawRect(x0-rectSize,(((y0+y1)/2)+dec)-rectSize,x0,((y0+y1)/2)+dec,paintRect);
                        pCanvas.drawText(Integer.toString(nbInit), (x0-rectSize)+strokeWidth*3, ((y1+y0)/2)-strokeWidth*3+dec, paintText);
                        pCanvas.drawRect(x0-rectSize*2,(((y0+y1)/2)+dec)-rectSize,x0-rectSize,((y0+y1)/2)+dec,paint);
                        break;
                }
                nb++;
                nbInit++;
            }while(nb<nbTasks);
        }
    }

    //getters
    public Shape getShape() { return shape; }

    public int getNbInit() { return nbInit; }

    public int getNbTasks() { return nbTasks; }

    public String getOrientation() { return orientation; }

    //setters
    public void setShape(Shape shape) { this.shape = shape; }

    public void setNbInit(int nbInit) { this.nbInit = nbInit; }

    public void setNbTasks(int nbTasks) { this.nbTasks = nbTasks; }

    public void setOrientation(String orientation) { this.orientation = orientation; }

}
