/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.MetadocumentosBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.DocumentoDao;
import net.daw.dao.MetadocumentoDao;
import net.daw.dao.MetadocumentosDao;
import net.daw.helper.Contexto;
import net.daw.parameter.MetadocumentosParam;

/**
 *
 * @author Alvaro
 */
public class MetadocumentosView1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/metadocumentos/form.jsp");
        MetadocumentosBean oMetadocumentosBean;
        MetadocumentosDao oMetadocumentosDao;
        oMetadocumentosBean = new MetadocumentosBean();
        MetadocumentosParam oMetadocumentosParam = new MetadocumentosParam(request);
        oMetadocumentosBean = oMetadocumentosParam.loadId(oMetadocumentosBean);
        oMetadocumentosDao = new MetadocumentosDao(oContexto.getEnumTipoConexion());
        MetadocumentoDao oMetadocumentoDao = new MetadocumentoDao(oContexto.getEnumTipoConexion());
        DocumentoDao oDocumentoDao = new DocumentoDao(oContexto.getEnumTipoConexion());
        try {
                oMetadocumentosBean = oMetadocumentosDao.get(oMetadocumentosBean);
                oMetadocumentosBean.setMetadocumento(oMetadocumentoDao.get(oMetadocumentosBean.getMetadocumento()));
                oMetadocumentosBean.setDocumento(oDocumentoDao.get(oMetadocumentosBean.getDocumento()));
            } catch (Exception e) {
                throw new ServletException("MetadocumentoController: View Error: Phase 1: " + e.getMessage());
            }
        oMetadocumentosBean = oMetadocumentosParam.load(oMetadocumentosBean);
        
        UsuarioBean oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        Integer idUsuario = oUsuarioBean.getId();
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {
            
            return oMetadocumentosBean;
        } else {
            if (idUsuario == oMetadocumentosBean.getDocumento().getUsuario().getId()) {
                return oMetadocumentosBean;
            } else {
                oContexto.setVista("jsp/mensaje.jsp");
                return "<div class=\"alert alert-error\">No se puede visualizar este metadocumentos<br/><br/>Posibles razones más frecuentes<ul><li>No eres el propietario o no tienes los permisos suficientes en este metadocumentos.</li><li>El metadocumentos al que intentas acceder no exsiste.</li><li>Ha habido un error en el servidor.</li></ul></div>";
            }
        }
    }
}
