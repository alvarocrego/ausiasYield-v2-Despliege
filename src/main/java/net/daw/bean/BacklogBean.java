package net.daw.bean;

import java.util.Date;

public class BacklogBean {

    private int id = 0;
    private String enunciado = "";
    private String descripciondetallada = "";
    private Date fechainicioprevista;
    private Date fechafinprevista;
    private Date fechainicio;
    private Date fechafin;
    private UsuarioBean usuario = null;
    private RequerimientoBean requerimiento = null;
    private int horasduracionprevista = 0;
    private int porcentajecompletado = 0;
    private Date fechaalta;
    private int sprint = 0;
    private int release = 0;

    public BacklogBean() {
        this.usuario = new UsuarioBean();
        this.usuario.setId(0);
        this.requerimiento = new RequerimientoBean();
        this.requerimiento.setId(0);
    }

    public BacklogBean(Integer id) {
        this.id = id;
        this.usuario = new UsuarioBean();
        this.usuario.setId(0);
        this.requerimiento = new RequerimientoBean();
        this.requerimiento.setId(0);
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
     * @return the enunciado
     */
    public String getEnunciado() {
        return enunciado;
    }

    /**
     * @param enunciado the enunciado to set
     */
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    /**
     * @return the descripciondetallada
     */
    public String getDescripciondetallada() {
        return descripciondetallada;
    }

    /**
     * @param descripciondetallada the descripciondetallada to set
     */
    public void setDescripciondetallada(String descripciondetallada) {
        this.descripciondetallada = descripciondetallada;
    }

    /**
     * @return the fechainicioprevista
     */
    public Date getFechainicioprevista() {
        return fechainicioprevista;
    }

    /**
     * @param fechainicioprevista the fechainicioprevista to set
     */
    public void setFechainicioprevista(Date fechainicioprevista) {
        this.fechainicioprevista = fechainicioprevista;
    }

    /**
     * @return the fechafinprevista
     */
    public Date getFechafinprevista() {
        return fechafinprevista;
    }

    /**
     * @param fechafinprevista the fechafinprevista to set
     */
    public void setFechafinprevista(Date fechafinprevista) {
        this.fechafinprevista = fechafinprevista;
    }

    /**
     * @return the fechainicio
     */
    public Date getFechainicio() {
        return fechainicio;
    }

    /**
     * @param fechainicio the fechainicio to set
     */
    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    /**
     * @return the fechafin
     */
    public Date getFechafin() {
        return fechafin;
    }

    /**
     * @param fechafin the fechafin to set
     */
    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    /**
     * @return the horasduracionprevista
     */
    public int getHorasduracionprevista() {
        return horasduracionprevista;
    }

    /**
     * @param horasduracionprevista the horasduracionprevista to set
     */
    public void setHorasduracionprevista(int horasduracionprevista) {
        this.horasduracionprevista = horasduracionprevista;
    }

    /**
     * @return the porcentajecompletado
     */
    public int getPorcentajecompletado() {
        return porcentajecompletado;
    }

    /**
     * @param porcentajecompletado the porcentajecompletado to set
     */
    public void setPorcentajecompletado(int porcentajecompletado) {
        this.porcentajecompletado = porcentajecompletado;
    }

    /**
     * @return the fechaalta
     */
    public Date getFechaalta() {
        return fechaalta;
    }

    /**
     * @param fechaalta the fechaalta to set
     */
    public void setFechaalta(Date fechaalta) {
        this.fechaalta = fechaalta;
    }

    /**
     * @return the sprint
     */
    public int getSprint() {
        return sprint;
    }

    /**
     * @param sprint the sprint to set
     */
    public void setSprint(int sprint) {
        this.sprint = sprint;
    }

    /**
     * @return the release
     */
    public int getRelease() {
        return release;
    }

    /**
     * @param release the release to set
     */
    public void setRelease(int release) {
        this.release = release;
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
     * @return the requerimiento
     */
    public RequerimientoBean getRequerimiento() {
        return requerimiento;
    }

    /**
     * @param requerimiento the requerimiento to set
     */
    public void setRequerimiento(RequerimientoBean requerimiento) {
        this.requerimiento = requerimiento;
    }

}
