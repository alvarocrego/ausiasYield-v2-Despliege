package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daw.bean.StreamBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.StreamDao;
import net.daw.helper.Contexto;
import net.daw.parameter.StreamParam;

public class StreamRemove2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();

        //Validación
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {
            StreamBean oStreamBean = new StreamBean();
            StreamParam oStreamParam = new StreamParam(request);
            oStreamBean = oStreamParam.loadId(oStreamBean);
            try {
                StreamDao oStreamDAO = new StreamDao(oContexto.getEnumTipoConexion());
                oStreamDAO.remove(oStreamBean);
            } catch (Exception e) {
                throw new ServletException("StreamController: Remove Error: " + e.getMessage());
            }
            String Mensaje = ("Se ha eliminado la información del stream con id=" + Integer.toString(oStreamBean.getId()));
            return Mensaje;
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";
        }
    }
}
