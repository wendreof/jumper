package com.example.wlf.jumper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.wlf.jumper.elementos.GameOver;
import com.example.wlf.jumper.engine.VerificadorDeColisao;


public class Game extends SurfaceView implements Runnable, View.OnTouchListener {

    private Tela tela;
    private Bitmap background;
    private Cano cano;
    private Canos canos;
    private Canvas canvas;
    private boolean isRunning = true;
    private final SurfaceHolder holder = getHolder();
    private Passaro passaro;
    private Pontuacao pontuacao;

    public Game(Context context)
    {
        super(context);
        this.tela = new Tela(context);

        inicializaElementos();
        setOnTouchListener(this);
    }

    private void inicializaElementos()
    {
        this.pontuacao = new Pontuacao();
        this.passaro = new Passaro(tela, getContext());
        this.canos = new Canos(tela, pontuacao, getContext());
        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        this.background = Bitmap.createScaledBitmap(back, back.getWidth(), tela.getAltura(), false);
        //this.setBackgroundResource(R.drawable.background);

    }

    @Override
    public void run() {
        while (isRunning){

            if ( ! holder.getSurface().isValid() ) continue;

            canvas = holder.lockCanvas();

            canvas.drawBitmap(background, 0, 0, null);

            passaro.desenhaNo(canvas);
            passaro.cai();

            canos.desenhaNo(canvas);
            canos.move();

            pontuacao.desenhaNo(canvas);

            holder.unlockCanvasAndPost(canvas);

            if ( new VerificadorDeColisao(passaro, canos).temColisao() )
            {
                new GameOver(tela).desenhaNo(canvas);
                isRunning = false;
            }
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
