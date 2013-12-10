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
import net.daw.helper.Contexto;
import net.daw.parameter.DocumentoParam;

/**
 *
 * @author Alvaro
 */
public class DocumentoRemove2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        DocumentoBean oDocumentoBean = new DocumentoBean();
        DocumentoDao oDocumentoDao = new DocumentoDao(oContexto.getEnumTipoConexion());
        DocumentoParam oDocumentoParam = new DocumentoParam(request);
        oDocumentoBean = oDocumentoParam.loadId(oDocumentoBean);
        try {
            oDocumentoBean = oDocumentoDao.get(oDocumentoBean);
        } catch (Exception e) {
            throw new ServletException("DocumentoController: Remove Error: Phase 2: " + e.getMessage());
        }

        UsuarioBean oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        Integer idUsuario = oUsuarioBean.getId();
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {
            try {
                DocumentoDao oDocumentoDAO = new DocumentoDao(oContexto.getEnumTipoConexion());
                oDocumentoDAO.remove(oDocumentoBean);
            } catch (Exception e) {
                throw new ServletException("DocumentoController: Remove Error: " + e.getMessage());
            }
            String Mensaje = ("<div class=\"alert alert-success\">Se ha eliminado la información del documento con id=" + Integer.toString(oDocumentoBean.getId())+"</div>");
            return Mensaje;
        } else {
            if (idUsuario == oDocumentoBean.getUsuario().getId()) {
                try {
                    DocumentoDao oDocumentoDAO = new DocumentoDao(oContexto.getEnumTipoConexion());
                    oDocumentoDAO.remove(oDocumentoBean);
                } catch (Exception e) {
                    throw new ServletException("DocumentoController: Remove Error: " + e.getMessage());
                }
                String Mensaje = ("<div class=\"alert alert-success\">Se ha eliminado la información del documento con id=" + Integer.toString(oDocumentoBean.getId())+"</div>");
                return Mensaje;
            } else {
                oContexto.setVista("jsp/mensaje.jsp");
                return "<div class=\"alert alert-error\">No se puede eliminar este documento<br/><br/>Posibles razones más frecuentes<ul><li>No eres el propietario o no tienes los permisos suficientes en este documento.</li><li>El documento al que intentas acceder no exsiste.</li><li>Ha habido un error en el servidor.</li></ul></div>";
            }
        }
    }

}
