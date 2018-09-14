package com.example.wlf.jumper;

import android.graphics.Canvas;
import android.graphics.Paint;


public class Cano {

    private static final int TAMANHO_DO_CANO = 250;
    private static final int LARGURA_DO_CANO = 100;
    private Tela tela;
    private int alturaDoCanoInferior;
    private int alturaDoCanoSuperior;
    private int posicao;
    private final Paint VERDE = Cores.getCorDoCano();


    public Cano(Tela tela, int posicao)
    {
        this.tela = tela;
        this.posicao = posicao;
        this.alturaDoCanoInferior = tela.getAltura() - TAMANHO_DO_CANO - valorAleatorio();
        this.alturaDoCanoSuperior = 0 + TAMANHO_DO_CANO + valorAleatorio();
    }



    private void desenhaCanoSuperiorNo(Canvas canvas)
    {
        canvas.drawRect(posicao, 0,  posicao + LARGURA_DO_CANO,
                alturaDoCanoSuperior, VERDE);

    }

    private void desenhaCanoInferiorNo(Canvas canvas)
    {
        canvas.drawRect(posicao, alturaDoCanoInferior,
                posicao + LARGURA_DO_CANO, tela.getAltura(), VERDE );

    }


    public void desenhaNo(Canvas canvas)
    {
        desenhaCanoInferiorNo(canvas);
        desenhaCanoSuperiorNo(canvas);
    }


    public void move()
    {
        posicao -=5;
    }

    private int valorAleatorio()
    {
        return (int) (Math.random() * 150);
    }

    public boolean saiuDaTela()
    {
        return posicao + LARGURA_DO_CANO < 0;
    }

    public int getPosicao()
    {
        return posicao;
    }


}
