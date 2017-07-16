package com.fisi.unmsm.sistemaencuestaestudiantil.pojos;



import java.util.ArrayList;

/**
 * Created by RICARDO on 30/06/2017.
 */

public class Alumno {
    private String codigo;
    private String nombres;
    private String apellidos;
    private ArrayList<Encuesta> encuestas;

    public Alumno() {
    }

    public Alumno(String codigo, String nombres, String apellidos, ArrayList<Encuesta> encuestas) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.encuestas = encuestas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public ArrayList<Encuesta> getEncuestas() {
        return encuestas;
    }

    public void setEncuestas(ArrayList<Encuesta> encuestas) {
        this.encuestas = encuestas;
    }
}
