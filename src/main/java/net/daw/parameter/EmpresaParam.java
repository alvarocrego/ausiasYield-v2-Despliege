package net.daw.parameter;

/**
 *
 * @author AntonioNP
 */
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.EmpresaBean;
import net.daw.bean.UsuarioBean;

public class EmpresaParam {

    private HttpServletRequest request;

    public EmpresaParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public EmpresaBean loadId(EmpresaBean oEmpresa) throws NumberFormatException {
        try {
            if (request.getParameter("id") != null) {
                oEmpresa.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oEmpresa.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oEmpresa;
    }

    public EmpresaBean load(EmpresaBean oEmpresa) throws NumberFormatException {
        UsuarioBean oUsuarioBean = new UsuarioBean();
        try {
            if ((request.getParameter("id_usuario") != null)) {
                oEmpresa.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
            }
            if ((request.getParameter("nombre") != null)) {
                oEmpresa.setNombre(request.getParameter("nombre"));
            }
            if ((request.getParameter("cif") != null)) {
                oEmpresa.setCif(request.getParameter("cif"));
            }
            if ((request.getParameter("direccion") != null)) {
                oEmpresa.setDireccion(request.getParameter("direccion"));
            }
            if ((request.getParameter("localidad") != null)) {
                oEmpresa.setLocalidad(request.getParameter("localidad"));
            }
            if ((request.getParameter("provincia") != null)) {
                oEmpresa.setProvincia(request.getParameter("provincia"));
            }
            if ((request.getParameter("pais") != null)) {
                oEmpresa.setPais(request.getParameter("pais"));
            }
            if ((request.getParameter("telefono") != null)) {
                oEmpresa.setTelefono(request.getParameter("telefono"));
            }
            if ((request.getParameter("fax") != null)) {
                oEmpresa.setFax(request.getParameter("fax"));
            }
            if ((request.getParameter("actividad") != null)) {
                oEmpresa.setActividad(request.getParameter("actividad"));
            }
            if ((request.getParameter("nombrecontacto") != null)) {
                oEmpresa.setNombrecontacto(request.getParameter("ombrecontacto"));
            }
            if ((request.getParameter("emailcontacto") != null)) {
                oEmpresa.setEmailcontacto(request.getParameter("emailcontacto"));
            }
            if ((request.getParameter("validada") != null)) {
                oEmpresa.setValidada(request.getParameter("validada"));
            }
            if ((request.getParameter("login") != null)) {
                oEmpresa.getUsuario().setLogin(request.getParameter("login"));
            }
            if ((request.getParameter("password") != null)) {
                oEmpresa.getUsuario().setPassword(request.getParameter("password"));
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oEmpresa;
    }
}
