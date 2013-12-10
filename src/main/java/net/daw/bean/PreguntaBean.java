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

public class PreguntaBean {

    private Integer id = 0;
    private String descripcion = "";
    private CuestionarioBean cuestionario = null;

    public PreguntaBean() {
        this.cuestionario = new CuestionarioBean();
    }

    public PreguntaBean(Integer id) {
        this.id = id;
        this.cuestionario = new CuestionarioBean();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CuestionarioBean getCuestionario() {
        return this.cuestionario;
    }
    
    public void setCuestionario(CuestionarioBean cuestionario) {
        this.cuestionario = cuestionario;
    }
}
