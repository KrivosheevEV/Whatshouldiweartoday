package ru.kev163rus.whatshouldiweartoday;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static ru.kev163rus.whatshouldiweartoday.CanvasActivity.bitmapScreenshot;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private TextView tvLogo;
    private Intent canvasActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        tvLogo = (TextView) findViewById(R.id.tvLogo);
        tvLogo.setShadowLayer(10f, 60f, 30f, ContextCompat.getColor(this, R.color._myColor_1));
        tvLogo.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/kotleopold.ttf"));

        Button butStart = (Button) findViewById(R.id.buttonStart);
        butStart.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/cheshirskiycat.ttf"));

        Button butExit = (Button) findViewById(R.id.buttonExit);
        butExit.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/cheshirskiycat.ttf"));

        butStart.setOnClickListener(this);
        butExit.setOnClickListener(this);

        canvasActivity = new Intent(this, CanvasActivity.class);


    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.buttonStart:
//                getScreenShot();
                startActivity(canvasActivity);
                finish();
                break;
            case R.id.buttonExit:
                finish();
                System.exit(0);
                break;
            default:
                startActivity(canvasActivity);
                finish();
                break;
        }
    }

    private void getScreenShot(){

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        View relativeLayoutRootView = relativeLayout.getRootView();
        relativeLayoutRootView.setDrawingCacheEnabled(true);

        Bitmap bitmapScreenshot_ = relativeLayoutRootView.getDrawingCache();
        CanvasActivity.canvas = new Canvas(bitmapScreenshot_);
        bitmapScreenshot_.recycle();
    }
}
