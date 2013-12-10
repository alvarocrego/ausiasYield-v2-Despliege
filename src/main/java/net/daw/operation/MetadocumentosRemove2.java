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
import net.daw.dao.MetadocumentosDao;
import net.daw.helper.Contexto;
import net.daw.parameter.MetadocumentosParam;

/**
 *
 * @author Alvaro
 */
public class MetadocumentosRemove2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        MetadocumentosDao oMetadocumentosDao;
        MetadocumentosBean oMetadocumentosBean = new MetadocumentosBean();
        MetadocumentosParam oMetadocumentosParam = new MetadocumentosParam(request);
        oMetadocumentosBean = oMetadocumentosParam.loadId(oMetadocumentosBean);
        oMetadocumentosDao = new MetadocumentosDao(oContexto.getEnumTipoConexion());

        try {
            oMetadocumentosBean = oMetadocumentosDao.get(oMetadocumentosBean);
        } catch (Exception e) {
            throw new ServletException("MetadocumentosController: Update Error: Phase 1: " + e.getMessage());
        }

        UsuarioBean oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        Integer idUsuario = oUsuarioBean.getId();
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {
            try {
                MetadocumentosDao oMetadocumentosDAO = new MetadocumentosDao(oContexto.getEnumTipoConexion());
                oMetadocumentosDAO.remove(oMetadocumentosBean);
            } catch (Exception e) {
                throw new ServletException("MetadocumentosController: Remove Error: " + e.getMessage());
            }
            String strMensaje = "Se ha eliminado la información de la metadocumentos con id=" + Integer.toString(oMetadocumentosBean.getId()) + "<br />";
            strMensaje += "<a href=\"Controller?class=metadocumentos&method=list\">Ir al listado de metadocumentoss</a><br />";
            String Mensaje = strMensaje;
            return Mensaje;
        } else {
            if (idUsuario == oMetadocumentosBean.getDocumento().getUsuario().getId()) {
                try {
                    MetadocumentosDao oMetadocumentosDAO = new MetadocumentosDao(oContexto.getEnumTipoConexion());
                    oMetadocumentosDAO.remove(oMetadocumentosBean);
                } catch (Exception e) {
                    throw new ServletException("MetadocumentosController: Remove Error: " + e.getMessage());
                }
                String strMensaje = "Se ha eliminado la información de la metadocumentos con id=" + Integer.toString(oMetadocumentosBean.getId()) + "<br />";
                strMensaje += "<a href=\"Controller?class=metadocumentos&method=list\">Ir al listado de metadocumentoss</a><br />";
                String Mensaje = strMensaje;
                return Mensaje;
            } else {
                oContexto.setVista("jsp/mensaje.jsp");
                return "<div class=\"alert alert-error\">No se puede eliminar este metadocumentos<br/><br/>Posibles razones más frecuentes<ul><li>No eres el propietario o no tienes los permisos suficientes en este metadocumentos.</li><li>El metadocumentos al que intentas acceder no exsiste.</li><li>Ha habido un error en el servidor.</li></ul></div>";
            }
        }
    }
}
