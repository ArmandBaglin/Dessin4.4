package fr.selic.dessin44.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fr.selic.dessin44.R;
import fr.selic.dessin44.shapes.Shape;

/**
 * Created by stg2 on 06/04/2017.
 */

public class TableTasksActivity extends Activity {

    final String EXTRA_TEXT = "button";
    final String EXTRA_SHAPE = "shape";
    private Shape shape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabletaskview);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        shape = (Shape) bundle.getSerializable(EXTRA_SHAPE);


        final Intent intent2 =  new Intent(TableTasksActivity.this,SingleTouchImageViewActivity.class);
        intent2.putExtra(EXTRA_SHAPE,shape);


        //link with the return button
        findViewById(R.id.buttonReturn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //link with the OK button
        findViewById(R.id.buttonOK).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent2.putExtra(EXTRA_TEXT,'o');
                setResult(Activity.RESULT_OK,intent2);
                finish();
            }
        });

        //link with the KO button
        findViewById(R.id.buttonKO).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent2.putExtra(EXTRA_TEXT,'k');
                setResult(Activity.RESULT_OK,intent2);
                finish();
            }
        });

        //link with the NotDone button
        findViewById(R.id.buttonND).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent2.putExtra(EXTRA_TEXT,'d');
                setResult(Activity.RESULT_OK,intent2);
                finish();
            }
        });

        //link with the OKaf button
        findViewById(R.id.buttonOkAf).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent2.putExtra(EXTRA_TEXT,'x');
                setResult(Activity.RESULT_OK,intent2);
                finish();
            }
        });

        //link with the KOaf button
        findViewById(R.id.buttonOkAn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent2.putExtra(EXTRA_TEXT,'t');
                setResult(Activity.RESULT_OK,intent2);
                finish();
            }
        });

        //link with the NO button
        findViewById(R.id.buttonNO).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent2.putExtra(EXTRA_TEXT,'n');
                setResult(Activity.RESULT_OK,intent2);
                finish();
            }
        });

        //link with the |||| button
        findViewById(R.id.buttonPipe).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent2.putExtra(EXTRA_TEXT,'p');
                setResult(Activity.RESULT_OK,intent2);
                finish();
            }
        });
    }
}
