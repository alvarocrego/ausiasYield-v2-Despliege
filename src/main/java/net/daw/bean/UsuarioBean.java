package net.daw.bean;

public class UsuarioBean {

    private int id = 0;
    private String login = "";
    private String password = "";
    private Enum tipoUsuario = null;

    public UsuarioBean() {

    }

    public UsuarioBean(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the tipoUsuario
     */
    public Enum getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipoUsuario the tipoUsuario to set
     */
    public void setTipoUsuario(Enum tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
