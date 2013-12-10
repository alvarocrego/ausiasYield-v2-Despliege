package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.PreguntaBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.PreguntaDao;
import net.daw.helper.Contexto;
import net.daw.parameter.PreguntaParam;

public class PreguntaUpdate2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        //

        //Validacion
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {

            if ("cuestionario".equals(oContexto.getSearchingFor())) {
                oContexto.setVista("jsp/pregunta/list.jsp");
                oContexto.setClase("cuestionario");
                oContexto.setMetodo("selectone");
                oContexto.setFase("1");
                oContexto.setClaseRetorno("pregunta");
                oContexto.setMetodoRetorno("update");
                oContexto.setFaseRetorno("1");
                oContexto.removeParam("id_cuestionario");
                oContexto.removeParam("page");
                CuestionarioList1 oOperacion = new CuestionarioList1();
                return oOperacion.execute(request, response);
            } else {
                oContexto.setVista("jsp/mensaje.jsp");
                PreguntaBean oPreguntaBean = new PreguntaBean();
                PreguntaDao oPreguntaDao = new PreguntaDao(oContexto.getEnumTipoConexion());
                PreguntaParam oPreguntaParam = new PreguntaParam(request);
                oPreguntaBean = oPreguntaParam.loadId(oPreguntaBean);
                oPreguntaBean = oPreguntaDao.get(oPreguntaBean);
                try {
                    oPreguntaBean = oPreguntaParam.load(oPreguntaBean);
                } catch (NumberFormatException e) {
                    return "Tipo de dato incorrecto en uno de los campos del formulario";
                }
                try {
                    oPreguntaDao.set(oPreguntaBean);
                } catch (Exception e) {
                    throw new ServletException("PreguntaController: Update Error: Phase 2: " + e.getMessage());
                }
                return "Se ha modificado la información del pregunta con id=" + Integer.toString(oPreguntaBean.getId());
            }
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";

        }
    }
}
