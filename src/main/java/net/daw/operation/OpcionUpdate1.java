package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daw.bean.OpcionBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.OpcionDao;
import net.daw.dao.PreguntaDao;
import net.daw.helper.Contexto;
import net.daw.parameter.OpcionParam;

public class OpcionUpdate1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();

        //Validación
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {

            oContexto.setVista("jsp/opcion/form.jsp");
            OpcionBean oOpcionBean;
            OpcionDao oOpcionDao;
            oOpcionBean = new OpcionBean();
            OpcionParam oOpcionParam = new OpcionParam(request);
            oOpcionBean = oOpcionParam.loadId(oOpcionBean);
            oOpcionDao = new OpcionDao(oContexto.getEnumTipoConexion());
            try {
                oOpcionBean = oOpcionDao.get(oOpcionBean);
            } catch (Exception e) {
                throw new ServletException("OpcionController: Update Error: Phase 1: " + e.getMessage());
            }

            try {
                oOpcionBean = oOpcionParam.load(oOpcionBean);
            } catch (NumberFormatException e) {
                oContexto.setVista("jsp/mensaje.jsp");
                return "Tipo de dato incorrecto en uno de los campos del formulario";
            }

            PreguntaDao oPreguntaDao = new PreguntaDao(oContexto.getEnumTipoConexion());
            oOpcionBean.setPregunta(oPreguntaDao.get(oOpcionBean.getPregunta()));
            return oOpcionBean;
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";
        }
    }
}