package com.example.wlf.jumper;

import android.graphics.Canvas;
import android.graphics.Paint;


public class Cano {

    private static final int TAMANHO_DO_CANO = 250;
    private static final int LARGURA_DO_CANO = 100;
    private Tela tela;
    private int alturaDoCanoInferior;
    private int posicao;
    private final Paint  verde = Cores.getCorDoCano();


    public Cano(Tela tela, int posicao)
    {
        this.tela = tela;
        this.posicao = posicao;
        this.alturaDoCanoInferior = tela.getAltura() - TAMANHO_DO_CANO;
    }


    public void desenhaNo(Canvas canvas)
    {
        desenhaCanoInferiorNo(canvas);
    }

    private void desenhaCanoInferiorNo(Canvas canvas)
    {
        canvas.drawRect(posicao, alturaDoCanoInferior, posicao + LARGURA_DO_CANO, tela.getAltura(), verde );

    }

    public void move()
    {
        posicao -=5;
    }
}
