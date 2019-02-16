package com.example.wlf.jumper.elements;

import android.content.Context;
import android.graphics.Canvas;
import com.example.wlf.jumper.graphics.Tela;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Pipes {

    private static final int QUANTIDADE_DE_CANOS = 5;
    private static final int POSICAO_INICIAL = 400;
    private static final int DISTANCIA_ENTRE_CANOS = 250;
    private final List<Pipe> canos = new ArrayList<Pipe>();
    private Tela tela;
    private final Pontuacao pontuacao;
    private Context context;

    public Pipes(Tela tela, Pontuacao pontuacao, Context context ) {
        this.tela = tela;
        this.pontuacao = pontuacao;
        this.context = context;

        int posicaoInicial = POSICAO_INICIAL;

        for( int i=0; i<QUANTIDADE_DE_CANOS; i++)
        {
            posicaoInicial += DISTANCIA_ENTRE_CANOS;
            canos.add( new Pipe( tela, posicaoInicial, context ) );
        }
    }

    public void desenhaNo( Canvas canvas )
    {
        for( Pipe cano : canos )
            cano.desenhaNo( canvas );
    }

    public void move()
    {
        ListIterator<Pipe> iterator = canos.listIterator();
        while( iterator.hasNext() ) {
            Pipe cano = (Pipe) iterator.next();
            cano.move();

            if(cano.saiuDaTela())
            {
                pontuacao.aumenta();
                iterator.remove();
                Pipe outroCano =
                        new Pipe( tela, getMaximo() + DISTANCIA_ENTRE_CANOS, context );
                iterator.add( outroCano );
            }
        }
    }

    public int getMaximo()
    {
        int maximo = 0;

        for( Pipe cano : canos )
        {
            maximo = Math.max( cano.getPosicao(), maximo );
        }

        return maximo;
    }

    public boolean temColisaoCom( Passaro passaro )
    {
        for ( Pipe cano : canos )
        {
            if ( cano.temColisaoHorizontalCom(passaro) && cano.temColisaoVerticalCom(passaro) )
            {
                return true;
            }
        }
        return false;
    }
}

