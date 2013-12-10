/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.ContestacionBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.UsuarioDao;
import net.daw.dao.ContestacionDao;
import net.daw.dao.OpcionDao;
import net.daw.dao.PreguntaDao;
import net.daw.helper.Contexto;
import net.daw.parameter.ContestacionParam;

/**
 *
 * @author rafa
 */
public class ContestacionView1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        //

        oContexto.setVista("jsp/contestacion/form.jsp");
        ContestacionBean oContestacionBean;
        ContestacionDao oContestacionDao;
        oContestacionBean = new ContestacionBean();
        ContestacionParam oContestacionParam = new ContestacionParam(request);
        oContestacionBean = oContestacionParam.loadId(oContestacionBean);
        oContestacionDao = new ContestacionDao(oContexto.getEnumTipoConexion());
        UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
        PreguntaDao oPreguntaDao = new PreguntaDao(oContexto.getEnumTipoConexion());
        OpcionDao oOpcionDao = new OpcionDao(oContexto.getEnumTipoConexion());

        //Validacion
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)
                || tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Alumno)) {
            try {
                oContestacionBean = oContestacionDao.get(oContestacionBean);
                oContestacionBean.setUsuario(oUsuarioDao.get(oContestacionBean.getUsuario()));
                oContestacionBean.setPregunta(oPreguntaDao.get(oContestacionBean.getPregunta()));
                oContestacionBean.setOpcion(oOpcionDao.get(oContestacionBean.getOpcion()));
            } catch (Exception e) {
                throw new ServletException("UsuarioController: View Error: Phase 1: " + e.getMessage());
            }

            return oContestacionBean;
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";
        }
    }
}
