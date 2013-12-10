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
import net.daw.bean.DocumentoBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.DocumentoDao;
import net.daw.helper.Contexto;
import net.daw.helper.Pagination;
import net.daw.helper.FilterBean;

/**
 *
 * @author al037294
 */
public class DocumentoList1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/documento/list.jsp");
        try {
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
            }
            
            
            DocumentoDao oDocumentoDAO = new DocumentoDao(oContexto.getEnumTipoConexion());
            Integer intPages = oDocumentoDAO.getPages(oContexto.getNrpp(), oContexto.getAlFilter(), oContexto.getHmOrder());
            Integer intRegisters = oDocumentoDAO.getCount(oContexto.getAlFilter());
            if (oContexto.getPage() >= intPages) {
                oContexto.setPage(intPages);
            }
            if (oContexto.getPage() < 1) {
                oContexto.setPage(1);
            }
            ArrayList<DocumentoBean> listado = oDocumentoDAO.getPage(oContexto.getNrpp(), oContexto.getPage(), oContexto.getAlFilter(), oContexto.getHmOrder());
            String strUrl = "<a href=\"Controller?" + oContexto.getSerializedParamsExceptPage() + "&page=";
            ArrayList<String> vecindad = Pagination.getButtonPad(strUrl, oContexto.getPage(), intPages, 2);
            ArrayList<Object> a = new ArrayList<>();
            a.add(listado);
            a.add(vecindad);
            a.add(intRegisters);
            return a;
        } catch (Exception e) {
            throw new ServletException("DocumentoList1: View Error: " + e.getMessage());
        }
    }
}
