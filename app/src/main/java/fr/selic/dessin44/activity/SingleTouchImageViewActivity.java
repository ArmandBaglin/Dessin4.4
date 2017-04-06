package fr.selic.dessin44.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewTreeObserver;

import fr.selic.dessin44.R;
import fr.selic.dessin44.shapes.DrawView;
import fr.selic.dessin44.shapes.Shape;

/**
 * Created by stg2 on 06/04/2017.
 */

public class SingleTouchImageViewActivity extends Activity implements OnTouchListenerI{

    private TouchImageView imageView;
    private DrawView drawView;
    final String EXTRA_TEXT = "button";
    final String EXTRA_SHAPE = "shape";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singletouchimageview);

        //new TouchImageView
        imageView = (TouchImageView) findViewById(R.id.image);

        //method that allows to wait until the image is fully drawn in order to manipulate it correctly
        imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                //remove the onGlobalLayoutListener so that it cannot be called many times (ex: if the application is exited without been closed)
                imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                //new DrawView
                drawView = new DrawView(SingleTouchImageViewActivity.this,imageView,SingleTouchImageViewActivity.this);
                imageView=drawView.drawOnImage(imageView);
            }
        });
    }

    //start of a new Activity. Method called is DrawView in the onTouchListener
    @Override
    public void onTouch(Shape pShape) {
        Intent intent = new Intent(new Intent(SingleTouchImageViewActivity.this, TableTasksActivity.class));
        intent.putExtra(EXTRA_SHAPE,pShape);
        startActivityForResult(intent,1);
    }

    //get the result of the Activity
    @Override
    protected void onActivityResult(int pRequestCode, int pResultCode, Intent pData) {

        if(pRequestCode == 1){
            if(pResultCode == Activity.RESULT_OK){
                char index = pData.getCharExtra(EXTRA_TEXT,' ');
                Bundle bundle = pData.getExtras();
                Shape shape = (Shape)bundle.getSerializable(EXTRA_SHAPE);
                imageView=drawView.addOnImage(drawView, imageView, shape, index);
            }
        }
    }
}
