package com.example.wlf.jumper.engine;

import com.example.wlf.jumper.elements.Pipes;
import com.example.wlf.jumper.elements.Passaro;

public class VerificadorDeColisao {

    private final Passaro passaro;
    private final Pipes canos;

    public VerificadorDeColisao( Passaro passaro, Pipes canos )
    {
        this.passaro = passaro;
        this.canos = canos;
    }

    public boolean temColisao()
    {
        return canos.temColisaoCom( passaro );
    }
}
