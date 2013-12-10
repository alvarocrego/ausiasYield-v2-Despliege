package net.daw.operation;

import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.EntregaBean;
import net.daw.dao.EntregaDao;
import net.daw.helper.Contexto;
import net.daw.helper.Pagination;

public class EntregaList1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/entrega/list.jsp");
        try {

            EntregaDao oEntregaDao = new EntregaDao(oContexto.getEnumTipoConexion());

            Integer intPages = oEntregaDao.getPages(oContexto.getNrpp(), oContexto.getAlFilter(), oContexto.getHmOrder());

            if (oContexto.getPage() >= intPages) {
                oContexto.setPage(intPages);
            }
            if (oContexto.getPage() < 1) {
                oContexto.setPage(1);
            }
            
            ArrayList<EntregaBean> listado = oEntregaDao.getPage(oContexto.getNrpp(), oContexto.getPage(), oContexto.getAlFilter(), oContexto.getHmOrder());
            String strUrl = "<a href=\"Controller?" + oContexto.getSerializedParamsExceptPage() + "&page=";            
            //ArrayList<String> vecindad = oEntregaDao.getNeighborhood(strUrl, oContexto.getPage(), intPages, 2);
            ArrayList<String> vecindad = Pagination.getButtonPad(strUrl, oContexto.getPage(), intPages, 2);
            ArrayList<Object> a = new ArrayList<>();
            a.add(listado);
            a.add(vecindad);
            return a;
        } catch (Exception e) {
            throw new ServletException("EntregaList1: Error: " + e.getMessage());
        }
    }
}
