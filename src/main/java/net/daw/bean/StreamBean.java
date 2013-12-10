/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author al037213
 */
package net.daw.bean;

import java.util.Date;

public class StreamBean {

    private Integer id = 0;
    private UsuarioBean usuario = null;
    private Date fecha;
    private String contenido = "";
    

    public StreamBean() {
        this.usuario = new UsuarioBean();
    }

    public StreamBean(Integer id) {
        this.id = id;
        this.usuario = new UsuarioBean();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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



}
