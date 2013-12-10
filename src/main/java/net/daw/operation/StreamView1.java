package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.StreamBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.StreamDao;
import net.daw.helper.Contexto;
import net.daw.parameter.StreamParam;

/**
 *
 * @author rafael aznar
 */
public class StreamView1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/stream/form.jsp");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();

        //Validación
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)
                ||  tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Alumno)  ) {

            StreamBean oStreamBean;
            StreamDao oStreamDao;
            oStreamBean = new StreamBean();
            StreamParam oStreamParam = new StreamParam(request);
            oStreamBean = oStreamParam.loadId(oStreamBean);
            oStreamDao = new StreamDao(oContexto.getEnumTipoConexion());
            try {
                oStreamBean = oStreamDao.get(oStreamBean);
            } catch (Exception e) {
                throw new ServletException("StreamController: View Error: Phase 1: " + e.getMessage());
            }
            oStreamBean = oStreamParam.load(oStreamBean);
            return oStreamBean;
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";
        }
    }
}
