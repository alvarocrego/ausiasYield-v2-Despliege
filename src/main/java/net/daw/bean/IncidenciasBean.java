/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean;

import java.util.Date;

/**
 *
 * @author al037877
 */
public class IncidenciasBean {

    private int id = 0;
    private EstadoBean estado = null;
    private RepositorioBean repositorio = null;
    private UsuarioBean usuario = null;
    private String resumen = "";
    private String cambios = "";
    private Date fechaAlta = new Date();
    private Date fechaResolucion = new Date();

    public IncidenciasBean(int id) {
        this.estado = new EstadoBean();
        this.estado.setId(0);
        this.repositorio = new RepositorioBean();
        this.repositorio.setId(0);
        this.usuario = new UsuarioBean();
        this.usuario.setId(0);
        this.id = id;
    }

    public IncidenciasBean() {
        this.estado = new EstadoBean();
        this.estado.setId(0);
        this.repositorio = new RepositorioBean();
        this.repositorio.setId(0);
        this.usuario = new UsuarioBean();
        this.usuario.setId(0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EstadoBean getEstado() {
        return estado;
    }

    public void setEstado(EstadoBean estado) {
        this.estado = estado;
    }

    public RepositorioBean getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(RepositorioBean repositorio) {
        this.repositorio = repositorio;
    }

    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getCambios() {
        return cambios;
    }

    public void setCambios(String cambios) {
        this.cambios = cambios;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }
}
