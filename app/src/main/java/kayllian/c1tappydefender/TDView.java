package kayllian.c1tappydefender;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by dvan_ on 11/10/2017.
 * The view for the Tappy Defender
 */

public class TDView extends SurfaceView implements Runnable {

    volatile boolean playing;
    private Thread gameThread;
    private PlayerShip player;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder ourHolder;

    public TDView(Context context) {
        super(context);
        ourHolder =  getHolder();
        paint = new Paint();
        player = new PlayerShip(context);
    }

    @Override
    public void run() {
        while(playing){
            update();
            draw();
            control();            
        }
    }

    public void pause(){
        playing = false;

        try{
            gameThread.join();
        }catch(InterruptedException e){

        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void control() {
        try {
            gameThread.sleep(17);
        }catch(InterruptedException ex){

        }

    }

    private void draw() {
        if(ourHolder.getSurface().isValid()) {
            canvas = ourHolder.lockCanvas();

            canvas.drawColor(Color.argb(255, 0, 0, 0));

            canvas.drawBitmap(player.getBitmap(), player.getX(), player.getY(), paint);

            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void update() {
        player.update();
    }



}
