package com.fisi.unmsm.sistemaencuestaestudiantil.pojos;

/**
 * Created by RICARDO on 13/07/2017.
 */

public class Login {
    private String codigoUsuario;
    private int permisoUsuario;
    private int participo;

    public Login() {
    }

    public Login(String codigoUsuario, int permisoUsuario, int participo) {
        this.codigoUsuario = codigoUsuario;
        this.permisoUsuario = permisoUsuario;
        this.participo = participo;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public int getPermisoUsuario() {
        return permisoUsuario;
    }

    public void setPermisoUsuario(int permisoUsuario) {
        this.permisoUsuario = permisoUsuario;
    }

    public int getParticipo() {
        return participo;
    }

    public void setParticipo(int participo) {
        this.participo = participo;
    }
}
