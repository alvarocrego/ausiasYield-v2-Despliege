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
public class BolsaRemove2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        BolsaBean oBolsaBean = new BolsaBean();
        BolsaParam oBolsaParam = new BolsaParam(request);
        oBolsaBean = oBolsaParam.loadId(oBolsaBean);
        try {
            BolsaDao oBolsaDAO = new BolsaDao(oContexto.getEnumTipoConexion());
            oBolsaDAO.remove(oBolsaBean);
        } catch (Exception e) {
            throw new ServletException("BolsaController: Remove Error: " + e.getMessage());
        }
        String strMensaje = "Se ha eliminado la información de la bolsa con id=" + Integer.toString(oBolsaBean.getId()) + "<br />";
        strMensaje += "<a href=\"Controller?class=bolsa&method=list\">Ir al listado de bolsas</a><br />";
        String Mensaje = strMensaje;
        return Mensaje;
    }
}
