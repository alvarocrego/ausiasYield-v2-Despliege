package net.daw.operation;

import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.ContestacionBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.ContestacionDao;
import net.daw.helper.Contexto;
import net.daw.helper.Pagination;

public class ContestacionList1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        //

        oContexto.setVista("jsp/contestacion/list.jsp");

        //Validacion
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)
                || tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Alumno)) {
            try {

                ContestacionDao oContestacionDao = new ContestacionDao(oContexto.getEnumTipoConexion());

                Integer intPages = oContestacionDao.getPages(oContexto.getNrpp(), oContexto.getAlFilter(), oContexto.getHmOrder());

                if (oContexto.getPage() >= intPages) {
                    oContexto.setPage(intPages);
                }
                if (oContexto.getPage() < 1) {
                    oContexto.setPage(1);
                }

                ArrayList<ContestacionBean> listado = oContestacionDao.getPage(oContexto.getNrpp(), oContexto.getPage(), oContexto.getAlFilter(), oContexto.getHmOrder());
                String strUrl = "<a href=\"Controller?" + oContexto.getSerializedParamsExceptPage() + "&page=";
                //ArrayList<String> vecindad = oContestacionDao.getNeighborhood(strUrl, oContexto.getPage(), intPages, 2);
                ArrayList<String> vecindad = Pagination.getButtonPad(strUrl, oContexto.getPage(), intPages, 2);
                ArrayList<Object> a = new ArrayList<>();
                a.add(listado);
                a.add(vecindad);
                return a;
            } catch (Exception e) {
                throw new ServletException("ContestacionList1: Error: " + e.getMessage());
            }
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";
        }
    }
}
