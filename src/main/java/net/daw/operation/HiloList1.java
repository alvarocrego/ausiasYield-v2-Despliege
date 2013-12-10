package net.daw.operation;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.HiloBean;
import net.daw.dao.HiloDao;
import net.daw.helper.Contexto;
import net.daw.helper.Pagination;

public class HiloList1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/hilo/list.jsp");
        try {

            HiloDao oHiloDao = new HiloDao(oContexto.getEnumTipoConexion());

            Integer intPages = oHiloDao.getPages(oContexto.getNrpp(), oContexto.getAlFilter(), oContexto.getHmOrder());
            Integer intRegisters = oHiloDao.getCount(oContexto.getAlFilter());
            if (oContexto.getPage() >= intPages) {
                oContexto.setPage(intPages);
            }
            if (oContexto.getPage() < 1) {
                oContexto.setPage(1);
            }

            ArrayList<HiloBean> listado = oHiloDao.getPage(oContexto.getNrpp(), oContexto.getPage(), oContexto.getAlFilter(), oContexto.getHmOrder());
            String strUrl = "<a href=\"Controller?" + oContexto.getSerializedParamsExceptPage() + "&page=";
            ArrayList<String> botonera = Pagination.getButtonPad(strUrl, oContexto.getPage(), intPages, 2);
            ArrayList<Object> a = new ArrayList<>();
            a.add(listado);
            a.add(botonera);
            a.add(intRegisters);
            return a;
        } catch (Exception e) {
            throw new ServletException("HiloList1: Error: " + e.getMessage());
        }
    }
}
