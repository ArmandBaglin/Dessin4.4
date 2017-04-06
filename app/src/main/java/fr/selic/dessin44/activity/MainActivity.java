package fr.selic.dessin44.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fr.selic.dessin44.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link with the views's button
        findViewById(R.id.single_touchimageview_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start of SingleTouchImageViewActivity.class
                startActivity(new Intent(MainActivity.this, SingleTouchImageViewActivity.class));
            }
        });
    }
}
