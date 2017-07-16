package com.fisi.unmsm.sistemaencuestaestudiantil.pojos;

/**
 * Created by RICARDO on 13/07/2017.
 */

public class Encuesta {
    private String nombreCurso;
    private String tipoCurso;
    private Profesor profesor;
    private int disponible;

    public Encuesta() {
    }

    public Encuesta(String nombreCurso, String tipoCurso, Profesor profesor, int disponible) {
        this.nombreCurso = nombreCurso;
        this.tipoCurso = tipoCurso;
        this.profesor = profesor;
        this.disponible = disponible;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }
}
