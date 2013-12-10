/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.ProfesorBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.ProfesorDao;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.ProfesorParam;
import net.daw.parameter.UsuarioParam;

/**
 *
 * @author Pedro Benito
 */
public class ProfesorNew2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        ProfesorBean oProfesorBean = new ProfesorBean();
        ProfesorDao oProfesorDao = new ProfesorDao(oContexto.getEnumTipoConexion());
        ProfesorParam oProfesorParam = new ProfesorParam(request);
        UsuarioParam oUsuarioParam = new UsuarioParam(request);
        oProfesorBean = oProfesorParam.loadId(oProfesorBean);
        oProfesorBean.setUsuario(oUsuarioParam.load(oProfesorBean.getUsuario()));
        UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
        oProfesorBean.setUsuario(oUsuarioDao.getFromLogin(oProfesorBean.getUsuario()));

        if (oProfesorBean.getUsuario().getId() != 0) {
            return "Lo sentimos. Ya existe el \'Login = " + oProfesorBean.getUsuario().getLogin()
                     +"\'. Por favor, introduzca un \'Login distinto a "
                     +oProfesorBean.getUsuario().getLogin() + "\'.";
        }
        try {
            oProfesorBean = oProfesorParam.load(oProfesorBean);
        } catch (NumberFormatException e) {
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        try {
            oProfesorDao.set(oProfesorBean);
        } catch (Exception e) {
            throw new ServletException("ProfesorController: Update Error: Phase 2: " + e.getMessage());
        }
        return "Se ha añadido la información del profesor con id=" + Integer.toString(oProfesorBean.getId());
    }
}
