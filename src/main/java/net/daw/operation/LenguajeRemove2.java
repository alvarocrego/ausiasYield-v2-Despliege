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
 * @author al037294
 */
public class LenguajeRemove2 implements Operation{
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");   
        LenguajeBean oLenguajeBean = new LenguajeBean(); 
        LenguajeParam oLenguajeParam = new LenguajeParam(request);
        oLenguajeBean = oLenguajeParam.loadId(oLenguajeBean);
        try {
            LenguajeDao oLenguajeDao = new LenguajeDao(oContexto.getEnumTipoConexion());
            oLenguajeDao.remove(oLenguajeBean);
        } catch (Exception e) {
            throw new ServletException("LenguajeController: Remove Error: " + e.getMessage());
        }
        String Mensaje = ("Se ha eliminado la información del repositorio con id=" + Integer.toString(oLenguajeBean.getId()));
        return Mensaje;
    }
}
