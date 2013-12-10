/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.operation;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.MetadocumentosBean;
import net.daw.dao.MetadocumentosDao;
import net.daw.helper.Contexto;
import net.daw.helper.Pagination;

/**
 *
 * @author al037294
 */
public class MetadocumentosList1 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/metadocumentos/list.jsp");
        try {

            MetadocumentosDao oMetadocumentosDao = new MetadocumentosDao(oContexto.getEnumTipoConexion());

            Integer intPages = oMetadocumentosDao.getPages(oContexto.getNrpp(), oContexto.getAlFilter(), oContexto.getHmOrder());
            Integer intRegisters = oMetadocumentosDao.getCount(oContexto.getAlFilter());
            if (oContexto.getPage() >= intPages) {
                oContexto.setPage(intPages);
            }
            if (oContexto.getPage() < 1) {
                oContexto.setPage(1);
            }

            ArrayList<MetadocumentosBean> listado = oMetadocumentosDao.getPage(oContexto.getNrpp(), oContexto.getPage(), oContexto.getAlFilter(), oContexto.getHmOrder());
            String strUrl = "<a href=\"Controller?" + oContexto.getSerializedParamsExceptPage() + "&page=";
            ArrayList<String> vecindad = Pagination.getButtonPad(strUrl, oContexto.getPage(), intPages, 2);
            ArrayList<Object> a = new ArrayList<>();
            a.add(listado);
            a.add(vecindad);
            a.add(intRegisters);
            return a;
        } catch (Exception e) {
            throw new ServletException("MetadocumentosList1: Error: " + e.getMessage());
        }
    }
}
