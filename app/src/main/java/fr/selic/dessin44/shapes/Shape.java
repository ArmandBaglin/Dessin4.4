package fr.selic.dessin44.shapes;

import android.graphics.Canvas;
import android.graphics.PointF;
import java.io.Serializable;

import fr.selic.dessin44.activity.TouchImageView;

/**
 * Created by stg2 on 06/04/2017.
 */

public interface Shape extends Serializable {
    void drawOnImage(Canvas canvas);
    boolean contains(double  x, double y, PointF pointZoom, float zoom);
    Canvas addOnImage(DrawView drawView, TouchImageView imageView, Canvas canvas, char index);
}
