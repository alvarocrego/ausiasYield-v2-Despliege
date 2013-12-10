/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.DocumentoBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.DocumentoDao;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.DocumentoParam;

/**
 *
 * @author al037294
 */
public class DocumentoUpdate1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/documento/form.jsp");
        DocumentoBean oDocumentoBean;
        DocumentoDao oDocumentoDao;
        oDocumentoBean = new DocumentoBean();
        DocumentoParam oDocumentoParam = new DocumentoParam(request);
        oDocumentoBean = oDocumentoParam.loadId(oDocumentoBean);
        oDocumentoDao = new DocumentoDao(oContexto.getEnumTipoConexion());

        try {
            oDocumentoBean = oDocumentoDao.get(oDocumentoBean);

        } catch (Exception e) {
            throw new ServletException("DocumentoController: Update Error: Phase 1: " + e.getMessage());
        }
        UsuarioBean oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        Integer idUsuario = oUsuarioBean.getId();
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {
            try {
                oDocumentoBean = oDocumentoParam.load(oDocumentoBean);
                UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
                oDocumentoBean.setUsuario(oUsuarioDao.get(oDocumentoBean.getUsuario()));
            } catch (NumberFormatException e) {
                oContexto.setVista("jsp/mensaje.jsp");
                return "<div class=\"alert alert-error\">Tipo de dato incorrecto en uno de los campos del formulario</div>";
            }
            return oDocumentoBean;
        } else {
            if (idUsuario == oDocumentoBean.getUsuario().getId()) {
                try {
                    oDocumentoBean = oDocumentoParam.load(oDocumentoBean);
                    UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
                    oDocumentoBean.setUsuario(oUsuarioDao.get(oDocumentoBean.getUsuario()));
                } catch (NumberFormatException e) {
                    oContexto.setVista("jsp/mensaje.jsp");
                    return "Tipo de dato incorrecto en uno de los campos del formulario";
                }
                return oDocumentoBean;
            } else {
                oContexto.setVista("jsp/mensaje.jsp");
                return "<div class=\"alert alert-error\">No se puede modificar este documento<br/><br/>Posibles razones más frecuentes<ul><li>No eres el propietario o no tienes los permisos suficientes en este documento.</li><li>El documento al que intentas acceder no exsiste.</li><li>Ha habido un error en el servidor.</li></ul></div>";
            }
        }
    }
}
