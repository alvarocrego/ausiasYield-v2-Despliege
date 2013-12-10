/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.EntregaBean;
import net.daw.dao.EntregaDao;
import net.daw.helper.Contexto;
import net.daw.parameter.EntregaParam;

/**
 *
 * @author rafa
 */
public class EntregaNew2 implements Operation {

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
                oContexto.setClaseRetorno("entrega");
                oContexto.setMetodoRetorno("new");
                oContexto.setFaseRetorno("1");
                oContexto.removeParam("id_documento");
                DocumentoList1 oOperacion = new DocumentoList1();
                return oOperacion.execute(request, response);
            }
            case "actividad": {
                oContexto.setVista("jsp/actividad/list.jsp");
                oContexto.setClase("actividad");
                oContexto.setMetodo("list");
                oContexto.setFase("1");
                oContexto.setSearchingFor("actividad");
                oContexto.setClaseRetorno("entrega");
                oContexto.setMetodoRetorno("new");
                oContexto.setFaseRetorno("1");
                oContexto.removeParam("id_actividad");
                ActividadList1 oOperacion = new ActividadList1();
                return oOperacion.execute(request, response);
            }
            default:
                oContexto.setVista("jsp/mensaje.jsp");
                EntregaBean oEntregaBean = new EntregaBean();
                EntregaDao oEntregaDao = new EntregaDao(oContexto.getEnumTipoConexion());
                EntregaParam oEntregaParam = new EntregaParam(request);
                oEntregaBean = oEntregaParam.loadId(oEntregaBean);
                try {
                    oEntregaBean = oEntregaParam.load(oEntregaBean);
                } catch (NumberFormatException e) {
                    return "Tipo de dato incorrecto en uno de los campos del formulario";
                }
                try {
                    oEntregaDao.set(oEntregaBean);
                } catch (Exception e) {
                    throw new ServletException("EntregaController: Update Error: Phase 2: " + e.getMessage());
                }
                String strMensaje = "Se ha añadido la información de entrega con id=" + Integer.toString(oEntregaBean.getId()) + "<br />";
                strMensaje += "<a href=\"Controller?class=entrega&method=list&filter=id_documento&filteroperator=equals&filtervalue=" + oEntregaBean.getDocumento().getId() + "\">Ver entregas de este documento</a><br />";
                strMensaje += "<a href=\"Controller?class=entrega&method=view&id=" + oEntregaBean.getId() + "\">Ver entrega creada</a><br />";
                return strMensaje;

        }
    }
}
