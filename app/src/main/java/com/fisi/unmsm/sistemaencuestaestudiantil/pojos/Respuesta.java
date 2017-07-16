package com.fisi.unmsm.sistemaencuestaestudiantil.pojos;

/**
 * Created by RICARDO on 14/07/2017.
 */

public class Respuesta {
    private String codigoProfesor;
    private String nombreProfesor;
    private String nombreCurso;
    private String tipoCurso;
    private int pregunta;
    private double respuesta;

    public Respuesta(String codigoProfesor, String nombreProfesor, String nombreCurso, String tipoCurso, int pregunta, double respuesta) {
        this.codigoProfesor = codigoProfesor;
        this.nombreProfesor = nombreProfesor;
        this.nombreCurso = nombreCurso;
        this.tipoCurso = tipoCurso;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public Respuesta() {

    }

    public String getCodigoProfesor() {
        return codigoProfesor;
    }

    public void setCodigoProfesor(String codigoProfesor) {
        this.codigoProfesor = codigoProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
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

    public int getPregunta() {
        return pregunta;
    }

    public void setPregunta(int pregunta) {
        this.pregunta = pregunta;
    }

    public double getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(double respuesta) {
        this.respuesta = respuesta;
    }
}
