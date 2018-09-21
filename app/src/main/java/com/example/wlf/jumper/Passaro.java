package com.example.wlf.jumper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Passaro {

    public static final int X = 100;
    public static final  int RAIO = 50;
    private static final Paint vermelho = Cores.getCorDoPassaro();
    private Tela tela;
    private Bitmap passaro;

    private int altura;

    public Passaro(Tela tela, Context context)
    {
        this.tela = tela;
        this.setAltura(100);

        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.passaro);
        passaro = bp.createScaledBitmap(bp, RAIO*2, RAIO*2, false);
    }

    public void desenhaNo(Canvas canvas)
    {
        //canvas.drawCircle(X, getAltura(), RAIO, vermelho);
        canvas.drawBitmap(passaro, X-RAIO, altura-RAIO, null);
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
        }
    }


    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }


}
