/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jordi Eslava Barrera
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daw.bean.RequerimientoBean;
import net.daw.dao.RequerimientoDao;
import net.daw.helper.Contexto;
import net.daw.parameter.RequerimientoParam;

public class RequerimientoNew2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        RequerimientoBean oRequerimientoBean = new RequerimientoBean();
        RequerimientoDao oRequerimientoDao = new RequerimientoDao(oContexto.getEnumTipoConexion());
        RequerimientoParam oRequerimientoParam = new RequerimientoParam(request);
        oRequerimientoBean = oRequerimientoParam.loadId(oRequerimientoBean);
        try {
            oRequerimientoBean = oRequerimientoParam.load(oRequerimientoBean);
        } catch (NumberFormatException e) {
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        try {
            oRequerimientoDao.set(oRequerimientoBean);
        } catch (Exception e) {
            throw new ServletException("RequerimientoController: Update Error: Phase 2: " + e.getMessage());
        }
        String strMensaje = "Se ha añadido la información del lenguaje con id=" + Integer.toString(oRequerimientoBean.getId()) + "<br />";
        strMensaje += "<a href=\"Controller?class=requerimiento&method=view&id=" + oRequerimientoBean.getId() + "\">Ver Requerimiento creado en el formulario</a><br />";
        return strMensaje;
    }
}