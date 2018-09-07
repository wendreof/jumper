package com.example.wlf.jumper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class Game extends SurfaceView implements Runnable, View.OnTouchListener {

    private Tela tela;
    private Bitmap background;
    private Cano cano;

    private Canvas canvas;

    private boolean isRunning = true;
    private final SurfaceHolder holder = getHolder();
    private Passaro passaro;

    public Game(Context context)
    {
        super(context);
        this.tela = new Tela(context);

        inicializaElementos();
        setOnTouchListener(this);
    }

    private void inicializaElementos()
    {
        Bitmap back = BitmapFactory.decodeResource(getResources(),
                R.drawable.background);
        background = Bitmap.createScaledBitmap(back, back.getWidth(), tela.getAltura(), false);
        //this.setBackgroundResource(R.drawable.background);


        this.passaro = new Passaro();
        this.cano = new Cano(tela, 200);

    }


    @Override
    public void run() {
        while (isRunning){

            if ( ! holder.getSurface().isValid() ) continue;

            canvas = holder.lockCanvas();

            canvas.drawBitmap(background, 0, 0, null);



            passaro.desenhaNo(canvas);
            passaro.cai();

            cano.desenhaNo(canvas);
            cano.move();

            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void cancela()
    {
        this.isRunning = false;
    }

    public void inicia()
    {
        this.isRunning = true;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        passaro.pula();
        return false;
    }
}
