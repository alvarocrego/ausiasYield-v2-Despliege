/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean;

import java.util.Date;

/**
 *
 * @author Jordi Eslava Barrera
 */
public class ComentBean {

    private int id = 0;
    private UsuarioBean id_usuario = null;
    private DocumentoBean id_documento = null;
    private String titulo = "";
    private String contenido = "";
    private Date fecha = new Date();

    public ComentBean(Integer id) {
        this.id = id;
        this.id_documento = new DocumentoBean();
        this.id_usuario = new UsuarioBean();
        this.id_documento.setId(0);
        this.id_usuario.setId(0);
    }
    
    public ComentBean() {
        this.id_documento = new DocumentoBean();
        this.id_documento.setId(0);
        this.id_usuario = new UsuarioBean();
        this.id_usuario.setId(0);
    }

    
    public UsuarioBean getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(UsuarioBean id_usuario) {
        this.id_usuario = id_usuario;
    }

    public DocumentoBean getId_documento() {
        return id_documento;
    }

    public void setId_documento(DocumentoBean id_documento) {
        this.id_documento = id_documento;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

   
}
