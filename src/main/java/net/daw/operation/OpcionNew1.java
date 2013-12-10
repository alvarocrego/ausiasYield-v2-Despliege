package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.OpcionBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.PreguntaDao;
import net.daw.helper.Contexto;
import net.daw.parameter.OpcionParam;

public class OpcionNew1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        OpcionParam oOpcionParam = new OpcionParam(request);
        OpcionBean oOpcionBean = new OpcionBean();
        PreguntaDao oPreguntaDao = new PreguntaDao(oContexto.getEnumTipoConexion());

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();

        //Validación
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {

            try {
                oOpcionBean = oOpcionParam.load(oOpcionBean);
                oOpcionBean.setPregunta(oPreguntaDao.get(oOpcionBean.getPregunta()));

            } catch (NumberFormatException e) {
                oContexto.setVista("jsp/mensaje.jsp");
                return "Tipo de dato incorrecto en uno de los campos del formulario";
            }
            oContexto.setVista("jsp/opcion/form.jsp");
            return oOpcionBean;
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";
        }
    }
}
