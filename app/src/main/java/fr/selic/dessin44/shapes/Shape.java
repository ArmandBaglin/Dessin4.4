package fr.selic.dessin44.shapes;

import android.graphics.Canvas;
import android.graphics.PointF;
import java.io.Serializable;

import fr.selic.dessin44.activity.TouchImageView;

/**
 * Created by stg2 on 06/04/2017.
 */

public interface Shape extends Serializable {
    void drawOnImage(Canvas pCanvas, Shape pShape, TaskShape pTaskShape);
    boolean contains(float pX, float pY, PointF point0Zoom, float pZoom);
    Canvas addOnImage(DrawView pDrawView, TouchImageView pImageView, Canvas pCanvas, char pIndex);
}
