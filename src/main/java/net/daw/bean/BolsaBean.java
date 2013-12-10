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
public class BolsaBean {

    private int id = 0;
    private Date fecha = new Date();
    private DocumentoBean documento1 = new DocumentoBean();
    private DocumentoBean documento2 = new DocumentoBean();

    public BolsaBean() {
    }

    public BolsaBean(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public DocumentoBean getDocumento1() {
        return documento1;
    }

    public void setDocumento1(DocumentoBean documento1) {
        this.documento1 = documento1;
    }

    public DocumentoBean getDocumento2() {
        return documento2;
    }

    public void setDocumento2(DocumentoBean documento2) {
        this.documento2 = documento2;
    }
}
