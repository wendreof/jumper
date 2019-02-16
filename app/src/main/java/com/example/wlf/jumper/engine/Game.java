package com.example.wlf.jumper.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.example.wlf.jumper.elements.Pipes;
import com.example.wlf.jumper.elements.Passaro;
import com.example.wlf.jumper.elements.Pontuacao;
import com.example.wlf.jumper.R;
import com.example.wlf.jumper.elements.GameOver;
import com.example.wlf.jumper.graphics.Tela;


public class Game extends SurfaceView implements Runnable, View.OnTouchListener {

    private boolean isRunning = true;
    private final SurfaceHolder holder = getHolder();
    private Tela tela;
    private Bitmap background;
    private Pipes canos;
    private Canvas canvas;
    private Passaro passaro;
    private Pontuacao pontuacao;
    private Som som;
    private Context context;

    public Game( Context context )
    {
        super( context );
        this.context = context;
        tela = new Tela( context );

        inicializaElementos();
        setOnTouchListener( this );
    }

    private void inicializaElementos()
    {
        this.passaro = new Passaro(tela, context);
        this.pontuacao = new Pontuacao();
        this.canos = new Pipes( tela, pontuacao, context );
        Bitmap back = BitmapFactory.decodeResource( getResources(), R.drawable.background );
        this.background = Bitmap.createScaledBitmap( back, back.getWidth(), tela.getAltura(), false );
        //this.setBackgroundResource(R.drawable.background);
        som = new Som(context);
    }

    @Override
    public void run() {
        while ( isRunning ){

            if ( ! holder.getSurface().isValid() )
            {
                continue;
            }

            canvas = holder.lockCanvas();

            canvas.drawBitmap(background, 0, 0, null);

            passaro.desenhaNo(canvas);
            passaro.cai();

            canos.desenhaNo(canvas);
            canos.move();

            pontuacao.desenhaNo(canvas);

            if ( new VerificadorDeColisao(passaro, canos).temColisao() )
            {
                som.tocaSom(Som.COLISAO);
                new GameOver(tela).desenhaNo(canvas);
                isRunning = false;
            }

            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void inicia()
    {
        this.isRunning = true;
    }

    public void cancela()
    {
        this.isRunning = false;
    }

    @Override
    public boolean onTouch( View view, MotionEvent motionEvent ) {
        passaro.pula();
        return false;
    }
}
