/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.LenguajeBean;
import net.daw.dao.LenguajeDao;
import net.daw.helper.Contexto;
import net.daw.parameter.LenguajeParam;

/**
 *
 * @author Alvaro
 */
public class LenguajeNew2 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        LenguajeBean oLenguajeBean = new LenguajeBean();
        LenguajeDao oLenguajeDao = new LenguajeDao(oContexto.getEnumTipoConexion());
        LenguajeParam oLenguajeParam = new LenguajeParam(request);
        oLenguajeBean = oLenguajeParam.loadId(oLenguajeBean);
        try {
            oLenguajeBean = oLenguajeParam.load(oLenguajeBean);
        } catch (NumberFormatException e) {
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        try {
            oLenguajeDao.set(oLenguajeBean);
        } catch (Exception e) {
            throw new ServletException("LenguajeController: Update Error: Phase 2: " + e.getMessage());
        }
        String strMensaje = "Se ha añadido la información del lenguaje con id=" + Integer.toString(oLenguajeBean.getId()) + "<br />";
        strMensaje += "<a href=\"Controller?class=lenguaje&method=view&id=" + oLenguajeBean.getId() + "\">Ver lenguaje creado en el formulario</a><br />";
        return strMensaje;
    }
}
