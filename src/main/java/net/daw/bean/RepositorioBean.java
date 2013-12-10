/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean;

import java.util.Date;

/**
 *
 * @author Ana
 */
public class RepositorioBean {

    private int id = 0;
    private String titulo = "";
    private String contenido = "";
    private UsuarioBean usuario = null;
    private LenguajeBean lenguaje = null;
    private DocumentoBean documento = null;
    private Date fecha = new Date();

    public RepositorioBean() {
        this.lenguaje = new LenguajeBean();
        this.lenguaje.setId(0);
        this.documento = new DocumentoBean();
        this.documento.setId(0);
        this.usuario = new UsuarioBean();
        this.usuario.setId(0);
    }

    public RepositorioBean(int id) {
        this.lenguaje = new LenguajeBean();
        this.lenguaje.setId(0);
        this.documento = new DocumentoBean();
        this.documento.setId(0);
        this.usuario = new UsuarioBean();
        this.usuario.setId(0);
        this.id = id;
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

    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }

    public LenguajeBean getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(LenguajeBean lenguaje) {
        this.lenguaje = lenguaje;
    }

    public DocumentoBean getDocumento() {
        return documento;
    }

    public void setDocumento(DocumentoBean documento) {
        this.documento = documento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
