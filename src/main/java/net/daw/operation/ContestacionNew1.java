/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.ContestacionBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.UsuarioDao;
import net.daw.dao.PreguntaDao;
import net.daw.dao.OpcionDao;
import net.daw.helper.Contexto;
import net.daw.parameter.ContestacionParam;

/**
 *
 * @author rafa
 */
public class ContestacionNew1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        //

        ContestacionParam oContestacionParam = new ContestacionParam(request);
        ContestacionBean oContestacionBean = new ContestacionBean();
        UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
        PreguntaDao oPreguntaDao = new PreguntaDao(oContexto.getEnumTipoConexion());
        OpcionDao oOpcionDao = new OpcionDao(oContexto.getEnumTipoConexion());
        //Validacion
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {

            try {
                oContestacionBean = oContestacionParam.load(oContestacionBean);
                oContestacionBean.setUsuario(oUsuarioDao.get(oContestacionBean.getUsuario()));
                oContestacionBean = oContestacionParam.load(oContestacionBean);
                oContestacionBean.setPregunta(oPreguntaDao.get(oContestacionBean.getPregunta()));
                oContestacionBean = oContestacionParam.load(oContestacionBean);
                oContestacionBean.setOpcion(oOpcionDao.get(oContestacionBean.getOpcion()));
            } catch (NumberFormatException e) {
                oContexto.setVista("jsp/mensaje.jsp");
                return "Tipo de dato incorrecto en uno de los campos del formulario";
            }
            oContexto.setVista("jsp/contestacion/form.jsp");
            return oContestacionBean;
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";

        }

    }
}
