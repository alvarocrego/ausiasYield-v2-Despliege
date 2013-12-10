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
import net.daw.dao.MetadocumentosDao;
import net.daw.helper.Contexto;
import net.daw.parameter.MetadocumentosParam;

/**
 *
 * @author al037294
 */
public class MetadocumentosNew2 implements Operation{
    
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
                oContexto.setMetodoRetorno("new");
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
                oContexto.setMetodoRetorno("new");
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
                strMensaje += "<a href=\"Controller?class=metadocumentos&method=view&id=" + oMetadocumentosBean.getId() + "\">Ver metadocumentos creada en el formulario</a><br />";
                return strMensaje;
        }
    }
}
