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
import net.daw.bean.ProfesorBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.ProfesorDao;
import net.daw.helper.Contexto;
import net.daw.helper.Pagination;

/**
 *
 * @author Pedro Benito
 */
public class ProfesorList1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        //Hasta aquí

        //Validación
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {
            oContexto.setVista("jsp/profesor/list.jsp");
            try {
                ProfesorDao oProfesorDao = new ProfesorDao(oContexto.getEnumTipoConexion());
                Integer intPages = oProfesorDao.getPages(oContexto.getNrpp(), oContexto.getAlFilter(), oContexto.getHmOrder());
                Integer intRegisters = oProfesorDao.getCount(oContexto.getAlFilter());
                if (oContexto.getPage() >= intPages) {
                    oContexto.setPage(intPages);
                }
                ArrayList<ProfesorBean> listado = (ArrayList<ProfesorBean>) oProfesorDao.getPage(oContexto.getNrpp(), oContexto.getPage(), oContexto.getAlFilter(), oContexto.getHmOrder());
                String strUrl = "<a href=\"Controller?" + oContexto.getSerializedParamsExceptPage() + "&page=";
                ArrayList<String> botonera = Pagination.getButtonPad(strUrl, oContexto.getPage(), intPages, 2);
                ArrayList<Object> a = new ArrayList<>();
                a.add(listado);
                a.add(botonera);
                a.add(intRegisters);
                return a;
            } catch (Exception e) {
                throw new ServletException("ProfesorList1: View Error: " + e.getMessage());
            }
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";
        }
    }
}
