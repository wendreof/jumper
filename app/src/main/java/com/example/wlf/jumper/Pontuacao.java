package com.example.wlf.jumper;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Pontuacao {

    private static final Paint BRANCO =
            Cores.getCorDaPontuacao();
    private int pontos = 0;

    public void aumenta()
    {
        pontos++;
    }

    public void desenhaNo(Canvas canvas)
    {
        canvas.drawText(String.valueOf(pontos), 100, 100,
                BRANCO);
    }
}
