package net.daw.operation;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.UsuarioBean;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;
import net.daw.helper.Pagination;

/**
 *
 * @author Sergio Martín Tárraga
 * @version v2.0
 * @since mie, 13 noviembre 2013
 */
public class UsuarioList1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/usuario/list.jsp");
        try {
            UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
            Integer intPages = oUsuarioDao.getPages(oContexto.getNrpp(), oContexto.getAlFilter(), oContexto.getHmOrder());
             Integer intRegisters = oUsuarioDao.getCount(oContexto.getAlFilter());
            if (oContexto.getPage() >= intPages) {
                oContexto.setPage(intPages);
            }
            ArrayList<UsuarioBean> listado = (ArrayList<UsuarioBean>) oUsuarioDao.getPage(oContexto.getNrpp(), oContexto.getPage(), oContexto.getAlFilter(), oContexto.getHmOrder());
            String strUrl = "<a href=\"Controller?" + oContexto.getSerializedParamsExceptPage() + "&page="; 
            ArrayList<String> botonera = Pagination.getButtonPad(strUrl, oContexto.getPage(), intPages, 2);
            ArrayList<Object> a = new ArrayList<>();
            
            a.add(listado);
            a.add(botonera);            
            a.add(intRegisters);
            return a;
        } catch (Exception e) {
            throw new ServletException("UsuarioList1: View Error: " + e.getMessage());
        }
    }
}
