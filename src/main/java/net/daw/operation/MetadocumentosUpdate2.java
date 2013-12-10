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
public class MetadocumentosUpdate2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        switch (oContexto.getSearchingFor()) {
            case "documento": {
                oContexto.setVista("jsp/documento/list.jsp");
                oContexto.setClase("documento");
                oContexto.setMetodo("list");
                oContexto.setFase("1");
                oContexto.setSearchingFor("documento");
                oContexto.setClaseRetorno("metadocumentos");
                oContexto.setMetodoRetorno("update");
                oContexto.setFaseRetorno("1");
                oContexto.removeParam("id_documento");
                DocumentoList1 oOperacion = new DocumentoList1();
                return oOperacion.execute(request, response);
            }
            case "metadocumento": {
                oContexto.setVista("jsp/metadocumento/list.jsp");
                oContexto.setClase("metadocumento");
                oContexto.setMetodo("list");
                oContexto.setFase("1");
                oContexto.setSearchingFor("metadocumento");
                oContexto.setClaseRetorno("metadocumentos");
                oContexto.setMetodoRetorno("update");
                oContexto.setFaseRetorno("1");
                oContexto.removeParam("id_metadocumento");
                MetadocumentoList1 oOperacion = new MetadocumentoList1();
                return oOperacion.execute(request, response);
            }
            default:
                oContexto.setVista("jsp/mensaje.jsp");
                MetadocumentosBean oMetadocumentosBean = new MetadocumentosBean();
                MetadocumentosDao oMetadocumentosDao = new MetadocumentosDao(oContexto.getEnumTipoConexion());
                MetadocumentosParam oMetadocumentosParam = new MetadocumentosParam(request);
                oMetadocumentosBean = oMetadocumentosParam.loadId(oMetadocumentosBean);

                try {
                    oMetadocumentosBean = oMetadocumentosDao.get(oMetadocumentosBean);
                } catch (Exception e) {
                    throw new ServletException("MetadocumentosController: Update Error: Phase 1: " + e.getMessage());
                }
                try {
                    oMetadocumentosBean = oMetadocumentosParam.load(oMetadocumentosBean);

                } catch (NumberFormatException e) {
                    return "Tipo de dato incorrecto en uno de los campos del formulario";
                }
                try {
                    oMetadocumentosDao.set(oMetadocumentosBean);
                } catch (Exception e) {
                    throw new ServletException("MetadocumentosController: Update Error: Phase 2: " + e.getMessage());
                }
                String strMensaje = "Se ha añadido la información de metadocumentos con id=" + Integer.toString(oMetadocumentosBean.getId()) + "<br />";
                //strMensaje += "<a href=\"Controller?class=metadocumentos&method=list&filter=id_cliente&filteroperator=equals&filtervalue=" + oMetadocumentosBean.getMetadocumento().getId() + "\">Ver metadocumentoss de este cliente</a><br />";
                strMensaje += "<a href=\"Controller?class=metadocumentos&method=view&id=" + oMetadocumentosBean.getId() + "\">Ver metadocumentos actualizada en el formulario</a><br />";

                UsuarioBean oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
                Integer idUsuario = oUsuarioBean.getId();
                java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
                if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {

                    return strMensaje;
                } else {
                    if (idUsuario == oMetadocumentosBean.getDocumento().getUsuario().getId()) {

                        return strMensaje;
                    } else {
                        oContexto.setVista("jsp/mensaje.jsp");
                        return "<div class=\"alert alert-error\">No se puede modificar este metadocumentos<br/><br/>Posibles razones más frecuentes<ul><li>No eres el propietario o no tienes los permisos suficientes en este metadocumentos.</li><li>El metadocumentos al que intentas acceder no exsiste.</li><li>Ha habido un error en el servidor.</li></ul></div>";
                    }
                }
        }
    }
}
