/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.BolsaBean;
import net.daw.dao.BolsaDao;
import net.daw.helper.Contexto;
import net.daw.helper.Pagination;

/**
 *
 * @author Jacobo Segovia
 */
public class BolsaList1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/bolsa/list.jsp");
        try {
            BolsaDao oEstadoDAO = new BolsaDao(oContexto.getEnumTipoConexion());
            Integer intPages = oEstadoDAO.getPages(oContexto.getNrpp(), oContexto.getAlFilter(), oContexto.getHmOrder());
            Integer intRegisters = oEstadoDAO.getCount(oContexto.getAlFilter());
            if (oContexto.getPage() >= intPages) {
                oContexto.setPage(intPages);
            }
            if (oContexto.getPage() < 1) {
                oContexto.setPage(1);
            }
            ArrayList<BolsaBean> bolsa = oEstadoDAO.getPage(oContexto.getNrpp(), oContexto.getPage(), oContexto.getAlFilter(), oContexto.getHmOrder());
            String strUrl = "<a href=\"Controller?" + oContexto.getSerializedParamsExceptPage() + "&page=";
            ArrayList<String> vecindad = Pagination.getButtonPad(strUrl, oContexto.getPage(), intPages, 2);
            ArrayList<Object> a = new ArrayList<>();
            a.add(bolsa);
            a.add(vecindad);
            a.add(intRegisters);
            return a;
        } catch (Exception e) {
            throw new ServletException("BolsaList1: View Error: " + e.getMessage());
        }
    }
}
