package com.example.wlf.jumper.elements;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.wlf.jumper.graphics.Cores;
import com.example.wlf.jumper.graphics.Tela;

public class GameOver {

    private final Tela tela;
    private static final Paint VERMELHO = Cores.getCorDoGameOver();

    public GameOver( Tela tela )
    {
        this.tela = tela;
    }

    public void desenhaNo( Canvas canvas )
    {
        String gameOver = "Game Over";
        int centroHorizontal = centralizaTexto( gameOver );

        canvas.drawText( gameOver, centroHorizontal,tela.getAltura() / 2, VERMELHO );
    }

    private int centralizaTexto( String texto )
    {
        Rect limiteDoTexto = new Rect();
        VERMELHO.getTextBounds(texto, 0, texto.length(), limiteDoTexto);
        int centroHorizontal = tela.getLargura()/2 - ( limiteDoTexto.right - limiteDoTexto.left ) /2;
        return centroHorizontal;
    }
}


