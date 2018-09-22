package com.example.wlf.jumper.elementos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.wlf.jumper.R;
import com.example.wlf.jumper.graficos.Cores;
import com.example.wlf.jumper.graficos.Tela;

public class Cano {

    private final Paint VERDE = Cores.getCorDoCano();
    private static final int TAMANHO_DO_CANO = 250;
    private static final int LARGURA_DO_CANO = 100;
    private  final Bitmap canoInferior;
    private  final Bitmap canoSuperior;
    private Tela tela;
    private Passaro passaro;
    private int alturaDoCanoInferior;
    private int alturaDoCanoSuperior;
    private int posicao;
    private Context context;

    public Cano( Tela tela, int posicao, Context context )
    {
        this.tela = tela;
        this.posicao = posicao;
        this.context = context;

        this.alturaDoCanoInferior = tela.getAltura() - TAMANHO_DO_CANO - valorAleatorio();
        this.alturaDoCanoSuperior = 0 + TAMANHO_DO_CANO + valorAleatorio();

        Bitmap bp = BitmapFactory.decodeResource( context.getResources(), R.drawable.cano );
        canoInferior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, alturaDoCanoInferior, false);
        canoSuperior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, alturaDoCanoSuperior, false);
    }

    private int valorAleatorio()
    {
        return (int) (Math.random() * 150);
    }

    public void desenhaNo( Canvas canvas )
    {
        desenhaCanoInferiorNo(canvas);
        desenhaCanoSuperiorNo(canvas);
    }

    private void desenhaCanoSuperiorNo( Canvas canvas )
    {
       // canvas.drawRect(posicao, 0,  posicao + LARGURA_DO_CANO,alturaDoCanoSuperior, VERDE);
        canvas.drawBitmap( canoSuperior, posicao, 0, null );

    }

    private void desenhaCanoInferiorNo( Canvas canvas )
    {
        //canvas.drawRect(posicao, alturaDoCanoInferior,posicao + LARGURA_DO_CANO, tela.getAltura(), VERDE );
        canvas.drawBitmap(canoInferior, posicao, alturaDoCanoInferior, null);
    }

    public void move()
    {
        posicao -=5;
    }

    public boolean saiuDaTela()
    {
        return posicao + LARGURA_DO_CANO < 0;
    }

    public int getPosicao()
    {
        return posicao;
    }

    public boolean temColisaoVerticalCom( Passaro passaro )
    {
        return passaro.getAltura() - passaro.RAIO < this.alturaDoCanoSuperior ||
                passaro.getAltura() + passaro.RAIO > this.alturaDoCanoInferior;
    }

    public boolean temColisaoHorizontalCom( Passaro passaro )
    {
        return this.posicao - passaro.X < passaro.RAIO;
    }
}
