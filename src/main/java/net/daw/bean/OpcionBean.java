/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author al037721
 */

package net.daw.bean;


public class OpcionBean {

    private Integer id = 0;
    private String descripcion = "";
    private PreguntaBean pregunta = null;
    private boolean correcta;

    public OpcionBean() {
        this.pregunta = new PreguntaBean();
    }

    public OpcionBean(Integer id) {
        this.id = id;
        this.pregunta = new PreguntaBean();
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


    public PreguntaBean getPregunta() {
        return this.pregunta;
    }

    public void setPregunta(PreguntaBean pregunta) {
        this.pregunta = pregunta;
    }

    public boolean getCorrecta() {
        return correcta;
    }

    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }

}

