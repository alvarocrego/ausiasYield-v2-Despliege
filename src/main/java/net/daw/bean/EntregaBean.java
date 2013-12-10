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
 * @author al037721
 */
package net.daw.bean;

import java.util.Date;

public class EntregaBean {

    private Integer id = 0;
    private DocumentoBean documento = null;
    private ActividadBean actividad = null;
    private Integer nota = 0;
    private Date fecha;

    public EntregaBean() {
        this.documento = new DocumentoBean();
        this.actividad = new ActividadBean();
    }

    public EntregaBean(Integer id) {
        this.id = id;
        this.documento = new DocumentoBean();
        this.actividad = new ActividadBean();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DocumentoBean getDocumento() {
        return documento;
    }

    public void setDocumento(DocumentoBean documento) {
        this.documento = documento;
    }

    public ActividadBean getActividad() {
        return actividad;
    }

    public void setActividad(ActividadBean actividad) {
        this.actividad = actividad;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
}
