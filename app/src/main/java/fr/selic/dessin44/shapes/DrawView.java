package fr.selic.dessin44.shapes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fr.selic.dessin44.activity.OnTouchListenerI;
import fr.selic.dessin44.activity.TouchImageView;

/**
 * Created by stg2 on 06/04/2017.
 */

public class DrawView extends AppCompatImageView {


    private List<Shape> shapes;
    private List<TaskShape> taskShapes;
    private Bitmap bmp;
    private Canvas canvas;

    Rectangle rectB = new Rectangle(375,300,575,500);
    Rectangle rectB1 = new Rectangle(0,0,200,200);
    Circle c = new Circle(800,175,100);
    TaskShape taskRectB = new TaskShape(rectB,1,3,"b");
    TaskShape taskrectB1 = new TaskShape(rectB1,4,2,"r");
    TaskShape taskC = new TaskShape(c,6,2,"l");


    private OnTouchListenerI listener;
    private TouchImageView imageView;

    //constructors
    public DrawView(Context context, TouchImageView imageView, OnTouchListenerI listener) {
        super(context);
        this.imageView=imageView;
        bmp = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Bitmap.Config.ARGB_8888);
        this.listener=listener;
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }

    //méthod that draw on the ImageView
    public TouchImageView drawOnImage(final TouchImageView pImageView) {

        shapes = new ArrayList<>();
        taskShapes = new ArrayList<>();

        canvas = new Canvas(bmp);
        pImageView.draw(canvas);


        shapes.add(rectB);
        shapes.add(rectB1);
        shapes.add(c);

        taskShapes.add(taskRectB);
        taskShapes.add(taskrectB1);
        taskShapes.add(taskC);

        for(int i=0;i<shapes.size();i++){
            shapes.get(i).drawOnImage(canvas,shapes.get(i),taskShapes.get(i));
        }

        pImageView.setImageBitmap(bmp);

        //Listener on a touchEvent that check if the coordinates of the press are in a drawn shape or not
        pImageView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    float x = event.getX();
                    float y = event.getY();
                    float zoom = pImageView.getCurrentZoom();
                    RectF rect = imageView.getZoomedRect();
                    PointF point0Zoom = imageView.getTopLeft();

                    for(int i=0;i<shapes.size();i++) {
                        if (shapes.get(i).contains(x,y,point0Zoom,zoom)) {
                            listener.onTouch(shapes.get(i));
                        }
                    }
                }
                return true;
            }
        });
        return pImageView;
    }

    //method override in Shape that add a calc on a Shape
    public TouchImageView addOnImage(DrawView pDrawView, TouchImageView pImageView, Shape pShape, char pIndex){
        //if(pType=='R')
        canvas=pShape.addOnImage(pDrawView, pImageView, canvas, pIndex);
        /*else if(pType=='C')
            canvas=pShape.addOnImage(pDrawView, pImageView, canvas, pIndex);*/

        pImageView.draw(canvas);
        return pImageView;
    }

    @Override
    public Matrix getImageMatrix() {
        return super.getImageMatrix();
    }
}