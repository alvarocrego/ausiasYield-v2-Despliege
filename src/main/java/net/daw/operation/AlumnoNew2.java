package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daw.bean.AlumnoBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.AlumnoDao;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.AlumnoParam;
import net.daw.parameter.UsuarioParam;

/**
 *
 * @author Sergio Martín Tárraga
 * @version v2.0
 * @since mie, 12 noviembre 2013
 */
public class AlumnoNew2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        AlumnoBean oAlumnoBean = new AlumnoBean();
        AlumnoDao oAlumnoDao = new AlumnoDao(oContexto.getEnumTipoConexion());
        AlumnoParam oAlumnoParam = new AlumnoParam(request);
        UsuarioParam oUsuarioParam = new UsuarioParam(request);
        oAlumnoBean = oAlumnoParam.loadId(oAlumnoBean);
        oAlumnoBean.setUsuario(oUsuarioParam.load(oAlumnoBean.getUsuario()));
        UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
        oAlumnoBean.setUsuario(oUsuarioDao.getFromLogin(oAlumnoBean.getUsuario()));

        if (oAlumnoBean.getUsuario().getId() != 0) {
            return "Lo sentimos. Ya existe el \'Login = " + oAlumnoBean.getUsuario().getLogin()
                    + "\'. Por favor, introduzca un \'Login distinto a "
                     + oAlumnoBean.getUsuario().getLogin() + "\'.";
        }

        try {
            oAlumnoBean = oAlumnoParam.load(oAlumnoBean);
        } catch (NumberFormatException e) {
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        try {
            oAlumnoDao.set(oAlumnoBean);
        } catch (Exception e) {
            throw new ServletException("AlumnoController: Update Error: Phase 2: " + e.getMessage());
        }
        return "Se ha añadido la información del alumno con id=" + Integer.toString(oAlumnoBean.getId());
    }
}
