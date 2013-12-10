package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.EntregaBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.ActividadDao;
import net.daw.dao.DocumentoDao;
import net.daw.dao.PreguntaDao;
import net.daw.helper.Contexto;
import net.daw.parameter.EntregaParam;

public class EntregaNew1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        EntregaParam oEntregaParam = new EntregaParam(request);
        EntregaBean oEntregaBean = new EntregaBean();
        DocumentoDao oDocumentoDao = new DocumentoDao(oContexto.getEnumTipoConexion());
        ActividadDao oActividadDao = new ActividadDao(oContexto.getEnumTipoConexion());

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();

        //Validación
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {

            try {
                oEntregaBean = oEntregaParam.load(oEntregaBean);
                oEntregaBean.setDocumento(oDocumentoDao.get(oEntregaBean.getDocumento()));
                oEntregaBean.setActividad(oActividadDao.get(oEntregaBean.getActividad()));

            } catch (NumberFormatException e) {
                oContexto.setVista("jsp/mensaje.jsp");
                return "Tipo de dato incorrecto en uno de los campos del formulario";
            }
            oContexto.setVista("jsp/entrega/form.jsp");
            return oEntregaBean;
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";
        }
    }
}
