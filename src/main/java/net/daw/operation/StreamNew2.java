package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.StreamBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.StreamDao;
import net.daw.helper.Contexto;
import net.daw.parameter.StreamParam;

public class StreamNew2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();

        //Validación
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {

            if ("usuario".equals(oContexto.getSearchingFor())) {
                oContexto.setVista("jsp/stream/list.jsp");
                oContexto.setClase("usuario");
                oContexto.setMetodo("list");
                oContexto.setFase("1");
                oContexto.setClaseRetorno("stream");
                oContexto.setMetodoRetorno("new");
                oContexto.setFaseRetorno("1");
                oContexto.removeParam("id_usuario");
                UsuarioList1 oOperacion = new UsuarioList1();
                return oOperacion.execute(request, response);
            } else {
                oContexto.setVista("jsp/mensaje.jsp");
                StreamBean oStreamBean = new StreamBean();
                StreamDao oStreamDao = new StreamDao(oContexto.getEnumTipoConexion());
                StreamParam oStreamParam = new StreamParam(request);
                oStreamBean = oStreamParam.loadId(oStreamBean);
                try {
                    oStreamBean = oStreamParam.load(oStreamBean);
                } catch (NumberFormatException e) {
                    return "Tipo de dato incorrecto en uno de los campos del formulario";
                }
                try {
                    oStreamDao.set(oStreamBean);
                } catch (Exception e) {
                    throw new ServletException("StreamController: Update Error: Phase 2: " + e.getMessage());
                }
                String strMensaje = "Se ha añadido la información del stream con id=" + Integer.toString(oStreamBean.getId()) + "<br />";
                strMensaje += "<a href=\"Controller?class=stream&method=view&id=" + oStreamBean.getId() + "\">Ver stream creado</a><br />";
                return strMensaje;
            }
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";
        }
    }
}
