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
public class RequerimientoBean {

    private int id = 0;
    private String enunciado = "";
    private Date fechaalta = new Date();

    public RequerimientoBean(int id) {
        this.id = id;
    }

    public RequerimientoBean() {
  }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public Date getFecha() {
        return fechaalta;
    }

    public void setFecha(Date fechaalta) {
        this.fechaalta = fechaalta;
    }
}
