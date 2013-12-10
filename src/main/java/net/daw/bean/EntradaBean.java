/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean;

import java.util.Date;

/**
 *
 * @author jose
 */
public class EntradaBean {

    private int id = 0;
    private String titulo = "";
    private String contenido = "";
    private UsuarioBean usuario = null;
    private HiloBean hilo = null;
    private Date fecha;

    public EntradaBean() {
        this.usuario = new UsuarioBean();
        this.usuario.setId(0);
        this.hilo = new HiloBean();
        this.hilo.setId(0);
    }

    public EntradaBean(Integer intId) {
        this.id = intId;
        this.usuario = new UsuarioBean();
        this.usuario.setId(0);
        this.hilo = new HiloBean();
        this.hilo.setId(0);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the contenido
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * @return the usuario
     */
    public UsuarioBean getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the hilo
     */
    public HiloBean getHilo() {
        return hilo;
    }

    /**
     * @param hilo the hilo to set
     */
    public void setHilo(HiloBean hilo) {
        this.hilo = hilo;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCantidad(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
