package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.OpcionBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.OpcionDao;
import net.daw.helper.Contexto;
import net.daw.parameter.OpcionParam;

public class OpcionNew2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();

        //Validación
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {

            if ("pregunta".equals(oContexto.getSearchingFor())) {
                oContexto.setVista("jsp/opcion/list.jsp");
                oContexto.setClase("pregunta");
                oContexto.setMetodo("list");
                oContexto.setFase("1");
                oContexto.setClaseRetorno("opcion");
                oContexto.setMetodoRetorno("new");
                oContexto.setFaseRetorno("1");
                oContexto.removeParam("id_pregunta");
                PreguntaList1 oOperacion = new PreguntaList1();
                return oOperacion.execute(request, response);
            } else {
                oContexto.setVista("jsp/mensaje.jsp");
                OpcionBean oOpcionBean = new OpcionBean();
                OpcionDao oOpcionDao = new OpcionDao(oContexto.getEnumTipoConexion());
                OpcionParam oOpcionParam = new OpcionParam(request);
                oOpcionBean = oOpcionParam.loadId(oOpcionBean);
                try {
                    oOpcionBean = oOpcionParam.load(oOpcionBean);
                } catch (NumberFormatException e) {
                    return "Tipo de dato incorrecto en uno de los campos del formulario";
                }
                try {
                    oOpcionDao.set(oOpcionBean);
                } catch (Exception e) {
                    throw new ServletException("OpcionController: Update Error: Phase 2: " + e.getMessage());
                }
                String strMensaje = "Se ha añadido la información del opcion con id=" + Integer.toString(oOpcionBean.getId()) + "<br />";
                strMensaje += "<a href=\"Controller?class=opcion&method=view&id=" + oOpcionBean.getId() + "\">Ver opcion creado</a><br />";
                return strMensaje;
            }
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";
        }
    }
}
