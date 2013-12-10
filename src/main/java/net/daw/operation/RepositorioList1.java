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
import net.daw.bean.RepositorioBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.RepositorioDao;
import net.daw.helper.Contexto;
import net.daw.helper.Pagination;

/**
 *
 * @author al037793
 */
public class RepositorioList1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/repositorio/list.jsp");

        UsuarioBean oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Empresa)) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "<div class=\"alert alert-error\">No tienes acceso</div>";
        } else {
            try {
                RepositorioDao oRepositorioDao = new RepositorioDao(oContexto.getEnumTipoConexion());
                Integer intPages = oRepositorioDao.getPages(oContexto.getNrpp(), oContexto.getAlFilter(), oContexto.getHmOrder());
                Integer intRegisters = oRepositorioDao.getCount(oContexto.getAlFilter());
                if (oContexto.getPage() >= intPages) {
                    oContexto.setPage(intPages);
                }
                if (oContexto.getPage() < 1) {
                    oContexto.setPage(1);
                }
                ArrayList<RepositorioBean> listado = oRepositorioDao.getPage(oContexto.getNrpp(), oContexto.getPage(), oContexto.getAlFilter(), oContexto.getHmOrder());
                String strUrl = "<a href=\"Controller?" + oContexto.getSerializedParamsExceptPage() + "&page=";
                ArrayList<String> vecindad = (ArrayList<String>) Pagination.getButtonPad(strUrl, oContexto.getPage(), intPages, 2);
                ArrayList<Object> a = new ArrayList<>();
                a.add(listado);
                a.add(vecindad);
                a.add(intRegisters);
                return a;
            } catch (Exception e) {
                throw new ServletException("RepositorioList1: View Error: " + e.getMessage());
            }
        }
    }
}
