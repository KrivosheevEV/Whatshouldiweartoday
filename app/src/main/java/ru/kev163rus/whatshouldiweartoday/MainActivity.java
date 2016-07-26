package ru.kev163rus.whatshouldiweartoday;

import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;



public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private TextView tvLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        tvLogo = (TextView) findViewById(R.id.tvLogo);
        tvLogo.setShadowLayer(10f, 60f, 30f, ContextCompat.getColor(this, R.color._myColor_1));
        tvLogo.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/cheshirskiycat.ttf"));
    }

    @Override
    public void onClick(View v) {

    }
}
