package ru.kev163rus.whatshouldiweartoday;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Random;

import static com.yandex.metrica.impl.q.a.r;

public class CanvasActivity extends Activity {

    public static Bitmap bitmapScreenshot;
    static Canvas canvas;
    private final int COUNT_OF_DRESS = 5;
    private final int DELAY = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new MySurfaceView(this));
    }

    class MySurfaceView extends SurfaceView  implements SurfaceHolder.Callback {

//        SurfaceHolder surfaceHolder;
        private DrawThread drawThread;
//        final int coordX, coordY;

        public MySurfaceView(Context context) {
            super(context);

            getHolder().addCallback(this);

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

            drawThread = new DrawThread(getHolder(), getResources());
            drawThread.setRunning(true);
            drawThread.start();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    drawRandomPicture(getHolder(), getResources());

                }
            }, 1500);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    drawRandomPicture(getHolder(), getResources());

                }
            }, 3000);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            // завершаем работу потока
            drawThread.setRunning(false);
            while (retry) {
                try {
                    drawThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                    // если не получилось, то будем пытаться еще и еще
                }
            }
        }
    }

    class DrawThread extends Thread{
        private boolean runFlag = false;
        private final SurfaceHolder surfaceHolder;
        private Resources resources;
        private Bitmap picture;
        private long prevTime;
        private int countOfDressMax;

        public DrawThread(SurfaceHolder surfaceHolder, Resources resources){
            this.surfaceHolder = surfaceHolder;
            this.resources = resources;
            this.countOfDressMax = COUNT_OF_DRESS;

            prevTime = System.currentTimeMillis();
        }

        public void setRunning(boolean run) {
            runFlag = run;
        }

        @Override
        public void run() {
            while (runFlag & countOfDressMax > 0) {
                long now = System.currentTimeMillis();
                long elapsedTime = now - prevTime;
                countOfDressMax--;
                if (elapsedTime > DELAY){//\ & countOfDressMax % 3 == 0
                    prevTime = now;
                    canvas = surfaceHolder.lockCanvas(null);
//                    try {
//                    synchronized (surfaceHolder) {
                    picture = BitmapFactory.decodeResource(resources, getRandDrawID());
                    Random r = new Random();
                    int coordX = r.nextInt(canvas.getWidth() + 50) - 100;
                    int coordY = r.nextInt(canvas.getHeight() + 100) - 200;
                    canvas.drawBitmap(picture, coordX, coordY, null);

//                            }
//                    }
//                    }
//                    finally {

                    if (picture != null && !picture.isRecycled()) {
                        picture.recycle();
                    }
//                    }
//                    if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
//                    }
                }
            }
        }
    }

    private int getRandDrawID(){

        int result = R.drawable.dress1_1_green;

        Random r = new Random();
        int newRandom = r.nextInt(9);

        switch (newRandom){
            case 0:
                getRandDrawID();
                break;
            case 1:
                result = R.drawable.dress1_1_green;
                break;
            case 2:
                result = R.drawable.dress1_1_pink;
                break;
            case 3:
                result = R.drawable.dress1_3;
                break;
            case 4:
                result = R.drawable.dress1_4;
                break;
            case 5:
                result = R.drawable.dress1_5;
                break;
            case 6:
                result = R.drawable.dress2_1;
                break;
            case 7:
                result = R.drawable.dress2_2;
                break;
            case 8:
                result = R.drawable.dress2_3;
                break;
            case 9:
                result = R.drawable.dress2_4;
                break;
            default:
                result = R.drawable.dress1_1_green;
                break;
        }

        return result;
    }

    private void drawRandomPicture(SurfaceHolder surfaceHolder, Resources resources){

        canvas = surfaceHolder.lockCanvas(null);
//        canvas = new Canvas();
        Bitmap picture = BitmapFactory.decodeResource(resources, getRandDrawID());
        Random r = new Random();
        int coordX = r.nextInt(canvas.getWidth() + 50) - 100;
        int coordY = r.nextInt(canvas.getHeight() + 100) - 200;
        canvas.drawBitmap(picture, coordX, coordY, null);
        canvas.save();

        if (picture != null && !picture.isRecycled()) {
            picture.recycle();
        }
        surfaceHolder.unlockCanvasAndPost(canvas);
    }
}
