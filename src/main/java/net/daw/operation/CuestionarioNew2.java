package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daw.bean.CuestionarioBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.CuestionarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.CuestionarioParam;

public class CuestionarioNew2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        //


        oContexto.setVista("jsp/mensaje.jsp");
        CuestionarioBean oCuestionarioBean = new CuestionarioBean();
        CuestionarioDao oCuestionarioDao = new CuestionarioDao(oContexto.getEnumTipoConexion());
        CuestionarioParam oCuestionarioParam = new CuestionarioParam(request);
        oCuestionarioBean = oCuestionarioParam.loadId(oCuestionarioBean);

        //Validacion
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {

            try {
                oCuestionarioBean = oCuestionarioParam.load(oCuestionarioBean);
            } catch (NumberFormatException e) {
                return "Tipo de dato incorrecto en uno de los campos del formulario";
            }
            try {
                oCuestionarioDao.set(oCuestionarioBean);
            } catch (Exception e) {
                throw new ServletException("CuestionarioController: New Error: Phase 2: " + e.getMessage());
            }
            return "Se ha añadido la información del cuestionario con id=" + Integer.toString(oCuestionarioBean.getId());
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";

        }
    }
}
