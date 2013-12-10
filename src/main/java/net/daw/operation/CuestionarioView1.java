package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.CuestionarioBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.CuestionarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.CuestionarioParam;

/**
 *
 * @author rafael aznar
 */
public class CuestionarioView1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        //

        oContexto.setVista("jsp/cuestionario/form.jsp");
        CuestionarioBean oCuestionarioBean;
        CuestionarioDao oCuestionarioDao;
        oCuestionarioBean = new CuestionarioBean();
        CuestionarioParam oCuestionarioParam = new CuestionarioParam(request);
        oCuestionarioBean = oCuestionarioParam.loadId(oCuestionarioBean);
        oCuestionarioDao = new CuestionarioDao(oContexto.getEnumTipoConexion());

        //Validacion
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)
                || tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Alumno)) {

            try {
                oCuestionarioBean = oCuestionarioDao.get(oCuestionarioBean);
            } catch (Exception e) {
                throw new ServletException("CuestionarioController: View Error: Phase 1: " + e.getMessage());
            }
            oCuestionarioBean = oCuestionarioParam.load(oCuestionarioBean);
            return oCuestionarioBean;
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";
        }
    }
}
