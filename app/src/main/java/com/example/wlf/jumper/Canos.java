package com.example.wlf.jumper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Canos {


    private static final int QUANTIDADE_DE_CANOS = 5;
    private static final int POSICAO_INICIAL = 400;
    private static final int DISTANCIA_ENTRE_CANOS = 250;
    private final List<Cano> canos = new ArrayList<Cano>();
    private Tela tela;
    private final Pontuacao pontuacao;


    public Canos(Tela tela, Pontuacao pontuacao) {
        this.tela = tela;
        this.pontuacao = pontuacao;

        int posicaoInicial = POSICAO_INICIAL;

        for(int i=0; i<QUANTIDADE_DE_CANOS; i++)
        {
            posicaoInicial += DISTANCIA_ENTRE_CANOS;
            canos.add(new Cano(tela, posicaoInicial));
        }
    }


    public void desenhaNo(Canvas canvas)
    {
        for(Cano cano : canos)
            cano.desenhaNo(canvas);
    }


    public void move()
    {
        ListIterator<Cano> iterator = canos.listIterator();
        while(iterator.hasNext()) {
            Cano cano = (Cano) iterator.next();
            cano.move();

            if(cano.saiuDaTela())
            {
                pontuacao.aumenta();
                iterator.remove();
                Cano outroCano =
                        new Cano(tela, getMaximo() + DISTANCIA_ENTRE_CANOS);
                iterator.add(outroCano);
            }
        }
    }




    public int getMaximo()
    {
        int maximo = 0;

        for(Cano cano : canos)
        {
            maximo = Math.max(cano.getPosicao(), maximo);
        }

        return maximo;
    }




}

