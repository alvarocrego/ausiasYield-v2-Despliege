/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.BolsaBean;
import net.daw.dao.BolsaDao;
import net.daw.helper.Contexto;
import net.daw.parameter.BolsaParam;

/**
 *
 * @author Jacobo Segovia
 */
public class BolsaUpdate2 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        switch (oContexto.getSearchingFor()) {
            case "documento1": {
                oContexto.setVista("jsp/documento/list.jsp");
                oContexto.setClase("documento");
                oContexto.setMetodo("list");
                oContexto.setFase("1");
                oContexto.setSearchingFor("documento1");
                oContexto.setClaseRetorno("bolsa");
                oContexto.setMetodoRetorno("update");
                oContexto.setFaseRetorno("1");
                oContexto.removeParam("id_documento1");
                DocumentoList1 oOperacion = new DocumentoList1();
                return oOperacion.execute(request, response);
            }
            case "documento2": {
                oContexto.setVista("jsp/documento/list.jsp");
                oContexto.setClase("documento");
                oContexto.setMetodo("list");
                oContexto.setFase("1");
                oContexto.setSearchingFor("documento2");
                oContexto.setClaseRetorno("bolsa");
                oContexto.setMetodoRetorno("update");
                oContexto.setFaseRetorno("1");
                oContexto.removeParam("id_documento2");
                DocumentoList1 oOperacion = new DocumentoList1();
                return oOperacion.execute(request, response);
            }
            case "metadocumento": {
                oContexto.setVista("jsp/metadocumento/list.jsp");
                oContexto.setClase("metadocumento");
                oContexto.setMetodo("list");
                oContexto.setFase("1");
                oContexto.setSearchingFor("metadocumento");
                oContexto.setClaseRetorno("bolsa");
                oContexto.setMetodoRetorno("update");
                oContexto.setFaseRetorno("1");
                oContexto.removeParam("id_metadocumento");
                MetadocumentoList1 oOperacion = new MetadocumentoList1();
                return oOperacion.execute(request, response);
            }
            default:
                oContexto.setVista("jsp/mensaje.jsp");
                BolsaBean oBolsaBean = new BolsaBean();
                BolsaDao oBolsaDao = new BolsaDao(oContexto.getEnumTipoConexion());
                BolsaParam oBolsaParam = new BolsaParam(request);
                oBolsaBean = oBolsaParam.loadId(oBolsaBean);
                try {
                    oBolsaBean = oBolsaParam.load(oBolsaBean);
                } catch (NumberFormatException e) {
                    return "Tipo de dato incorrecto en uno de los campos del formulario";
                }
                try {
                    oBolsaDao.set(oBolsaBean);
                } catch (Exception e) {
                    throw new ServletException("BolsaController: Update Error: Phase 2: " + e.getMessage());
                }
                String strMensaje = "Se ha añadido la información de bolsa con id=" + Integer.toString(oBolsaBean.getId()) + "<br />";
                //strMensaje += "<a href=\"Controller?class=bolsa&method=list&filter=id_cliente&filteroperator=equals&filtervalue=" + oBolsaBean.getMetadocumento().getId() + "\">Ver bolsas de este cliente</a><br />";
                strMensaje += "<a href=\"Controller?class=bolsa&method=view&id=" + oBolsaBean.getId() + "\">Ver bolsa actualizada en el formulario</a><br />";
                return strMensaje;
        }
    }
}
