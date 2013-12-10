package net.daw.bean;

/**
 *
 * @author AntonioNP
 */
public class EmpresaBean {

    private int id = 0;
    private int id_usuario = 0;
    private String nombre = "";
    private String cif = "";
    private String direccion = "";
    private String localidad = "";
    private String provincia = "";
    private String pais = "";
    private String telefono = "";
    private String fax = "";
    private String actividad = "";
    private String nombrecontacto = "";
    private String emailcontacto = "";
    private String validada = "";
    private UsuarioBean usuario;

    public EmpresaBean() {
        this.usuario = new UsuarioBean();
        this.usuario.setId(0);
    }

    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }

    public EmpresaBean(Integer id) {
        this.id = id;
        this.usuario = new UsuarioBean();
        this.usuario.setId(0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getNombrecontacto() {
        return nombrecontacto;
    }

    public void setNombrecontacto(String nombrecontacto) {
        this.nombrecontacto = nombrecontacto;
    }

    public String getEmailcontacto() {
        return emailcontacto;
    }

    public void setEmailcontacto(String emailcontacto) {
        this.emailcontacto = emailcontacto;
    }

    public String getValidada() {
        return validada;
    }

    public void setValidada(String validada) {
        this.validada = validada;
    }

}
