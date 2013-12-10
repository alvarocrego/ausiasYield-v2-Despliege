/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

/**
 *
 * @author Javier Bonet
 */

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.ActividadofflineBean;
import net.daw.dao.ActividadofflineDao;
import net.daw.helper.Contexto;
import net.daw.helper.Pagination;



public class ActividadofflineList1 implements Operation{
    
    
     @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/actividadOffline/list.jsp");
        try {
            ActividadofflineDao oActividadOfflineDao = new  ActividadofflineDao(oContexto.getEnumTipoConexion());
            Integer intPages = oActividadOfflineDao.getPages(oContexto.getNrpp(), oContexto.getAlFilter(), oContexto.getHmOrder());
            if (oContexto.getPage() >= intPages) {
                oContexto.setPage(intPages);
            }
            ArrayList<ActividadofflineBean> listado = (ArrayList<ActividadofflineBean>) oActividadOfflineDao.getPage(oContexto.getNrpp(), oContexto.getPage(), oContexto.getAlFilter(), oContexto.getHmOrder());
            String strUrl = "<a href=\"Controller?" + oContexto.getSerializedParamsExceptPage() + "&page="; 
            ArrayList<String> botonera = Pagination.getButtonPad(strUrl, oContexto.getPage(), intPages, 2);
            ArrayList<Object> a = new ArrayList<>();
            a.add(listado);
            a.add(botonera);            
            return a;
        } catch (Exception e) {
            throw new ServletException("ActividadOfflineList1: View Error: " + e.getMessage());
        }
    }
    
    
    
    
}
