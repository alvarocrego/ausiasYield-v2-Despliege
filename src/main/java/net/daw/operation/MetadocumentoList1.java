/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.MetadocumentoBean;
import net.daw.dao.MetadocumentoDao;
import net.daw.helper.Contexto;
import net.daw.helper.Pagination;

/**
 *
 * @author al037431
 */
public class MetadocumentoList1 implements Operation{
     @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/metadocumento/list.jsp");
        try {
            MetadocumentoDao oMetadocumentoDAO = new MetadocumentoDao(oContexto.getEnumTipoConexion());
            Integer intPages = oMetadocumentoDAO.getPages(oContexto.getNrpp(), oContexto.getAlFilter(), oContexto.getHmOrder());
            if (oContexto.getPage() >= intPages) {
                oContexto.setPage(intPages);
            }
            if (oContexto.getPage() < 1) {
                oContexto.setPage(1);
            }
            ArrayList<MetadocumentoBean> listado = oMetadocumentoDAO.getPage(oContexto.getNrpp(), oContexto.getPage(), oContexto.getAlFilter(), oContexto.getHmOrder());
            String strUrl = "<a href=\"Controller?" + oContexto.getSerializedParamsExceptPage() + "&page=";            
            ArrayList<String> vecindad = Pagination.getButtonPad(strUrl, oContexto.getPage(), intPages, 2);
            ArrayList<Object> a = new ArrayList<>();
            a.add(listado);
            a.add(vecindad);
            return a;
        } catch (Exception e) {
            throw new ServletException("MetadocumentoList1: View Error: " + e.getMessage());
        }
    }
}
