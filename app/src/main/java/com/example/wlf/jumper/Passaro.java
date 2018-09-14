package com.example.wlf.jumper;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Passaro {

    private static final int X = 100;
    private static final  int RAIO = 50;
    private static final Paint vermelho = Cores.getCorDoPassaro();
    private Tela tela;

    private int altura;

    public Passaro(Tela tela)
    {
        this.tela = tela;
        this.altura = 100;
    }

    public void desenhaNo(Canvas canvas)
    {
        canvas.drawCircle(X,   altura, RAIO, vermelho);
    }

    public void cai()
    {
        boolean checouNoChao = altura + RAIO > tela.getAltura();

        if ( ! checouNoChao )
        {
            altura +=5;

        }
    }

    public void  pula()
    {
        if(altura > RAIO) {
            altura -= 150;
        }
    }
}
