/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.TipodocumentoBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.TipodocumentoDao;
import net.daw.helper.Contexto;
import net.daw.helper.FilterBean;
import net.daw.helper.Pagination;

/**
 *
 * @author Jacobo Segovia
 */
public class TipodocumentoList1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/tipodocumento/list.jsp");
        try {
            /*
             * PERMISOS
            UsuarioBean oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
            java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
            ArrayList<FilterBean> alFilter = oContexto.getAlFilter();
            if (alFilter==null) {
                alFilter = new ArrayList<>();
            }
            FilterBean oFilterBean = new FilterBean();
            if (!tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {
                oFilterBean.setFilter("id_usuario");
                oFilterBean.setFilterOperator("equals");
                oFilterBean.setFilterValue(Integer.toString(oUsuarioBean.getId()));
                oFilterBean.setFilterOrigin("system");
                alFilter.add(oFilterBean);
                oContexto.setAlFilter(alFilter);
            }*/
            
            
            TipodocumentoDao oEstadoDAO = new TipodocumentoDao(oContexto.getEnumTipoConexion());
            Integer intPages = oEstadoDAO.getPages(oContexto.getNrpp(), oContexto.getAlFilter(), oContexto.getHmOrder());
            Integer intRegisters = oEstadoDAO.getCount(oContexto.getAlFilter());
            if (oContexto.getPage() >= intPages) {
                oContexto.setPage(intPages);
            }
            if (oContexto.getPage() < 1) {
                oContexto.setPage(1);
            }
            ArrayList<TipodocumentoBean> tipodocumento = oEstadoDAO.getPage(oContexto.getNrpp(), oContexto.getPage(), oContexto.getAlFilter(), oContexto.getHmOrder());
            String strUrl = "<a href=\"Controller?" + oContexto.getSerializedParamsExceptPage() + "&page=";
            ArrayList<String> vecindad = Pagination.getButtonPad(strUrl, oContexto.getPage(), intPages, 2);
            ArrayList<Object> a = new ArrayList<>();
            a.add(tipodocumento);
            a.add(vecindad);
            a.add(intRegisters);
            return a;
        } catch (Exception e) {
            throw new ServletException("TipodocumentoList1: View Error: " + e.getMessage());
        }
    }
}
