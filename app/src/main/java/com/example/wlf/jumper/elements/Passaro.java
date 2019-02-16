package com.example.wlf.jumper.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import com.example.wlf.jumper.R;
import com.example.wlf.jumper.engine.Som;
import com.example.wlf.jumper.graphics.Tela;

public class Passaro {

    public static final int X = 100;
    public static final  int RAIO = 80;
    private Tela tela;
    private Bitmap passaro;
    private Som som;

    private int altura;

    public Passaro( Tela tela, Context context )
    {
        this.tela = tela;
        this.setAltura( 100 );

        Bitmap bp = BitmapFactory.decodeResource( context.getResources(), R.drawable.passaro );

        passaro = bp.createScaledBitmap( bp, RAIO*2, RAIO*2, false );
        som = new Som(context);
    }

    public void desenhaNo( Canvas canvas )
    {
        //canvas.drawCircle(X, getAltura(), RAIO, vermelho);
        canvas.drawBitmap( passaro, X - RAIO, altura - RAIO, null );
    }

    public void cai()
    {
        boolean checouNoChao = getAltura() + RAIO > tela.getAltura();

        if ( ! checouNoChao )
        {
            setAltura(getAltura() + 5);

        }
    }

    public void  pula()
    {
        if(getAltura() > RAIO) {
            setAltura(getAltura() - 150);
            som.tocaSom(Som.PULO);
        }
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura( int altura ) {
        this.altura = altura;
    }
}
