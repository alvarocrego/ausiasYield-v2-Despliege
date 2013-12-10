/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean;

import java.util.Date;

/**
 *
 * @author al037294
 */
public class DocumentoBean {

    private int id = 0;
    private String titulo = "";
    private String contenido = "";
    private String contenidoParse = "";
    private Date fecha = new Date();
    private int nota = 0;
    private UsuarioBean usuario = null;
    private String etiquetas = "";

    public DocumentoBean() {
        this.usuario = new UsuarioBean();
        this.usuario.setId(0);
    }

    public DocumentoBean(int id) {
        this.id = id;
        this.usuario = new UsuarioBean();
        this.usuario.setId(0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getContenidoParse() {
        return contenidoParse;
    }

    public void setContenidoParse(String contenidoParse) {
        this.contenidoParse = contenidoParse;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String etiquetas) {
        this.etiquetas = etiquetas;
    }

    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }

}
