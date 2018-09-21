package com.example.wlf.jumper.engine;

import com.example.wlf.jumper.Canos;
import com.example.wlf.jumper.Passaro;

public class VerificadorDeColisao {

    private final Passaro passaro;
    private final Canos canos;

    public VerificadorDeColisao(Passaro passaro, Canos canos)
    {
        this.passaro = passaro;
        this.canos = canos;
    }

    public boolean temColisao()
    {
        return canos.temColisaoCom(passaro);
    }



}
