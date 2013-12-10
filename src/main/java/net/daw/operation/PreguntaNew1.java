package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.PreguntaBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.CuestionarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.PreguntaParam;

public class PreguntaNew1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        //

        PreguntaParam oPreguntaParam = new PreguntaParam(request);
        PreguntaBean oPreguntaBean = new PreguntaBean();

        CuestionarioDao oCuestionarioDao = new CuestionarioDao(oContexto.getEnumTipoConexion());
        //Validacion
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {
            try {
                oPreguntaBean = oPreguntaParam.load(oPreguntaBean);
                oPreguntaBean.setCuestionario(oCuestionarioDao.get(oPreguntaBean.getCuestionario()));

            } catch (NumberFormatException e) {
                oContexto.setVista("jsp/mensaje.jsp");
                return "Tipo de dato incorrecto en uno de los campos del formulario";
            }
            oContexto.setVista("jsp/pregunta/form.jsp");
            return oPreguntaBean;
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";

        }
    }
}
