/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.BacklogBean;
import net.daw.dao.UsuarioDao;
import net.daw.dao.RequerimientoDao;
import net.daw.helper.Contexto;
import net.daw.parameter.BacklogParam;

/**
 *
 * @author Edu Membrillas
 */
public class BacklogNew1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        BacklogParam oBacklogParam = new BacklogParam(request);
        BacklogBean oBacklogBean = new BacklogBean();
        UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
        RequerimientoDao oRequerimientoDao = new RequerimientoDao(oContexto.getEnumTipoConexion());
        try {
            oBacklogBean = oBacklogParam.load(oBacklogBean);
            oBacklogBean.setUsuario(oUsuarioDao.get(oBacklogBean.getUsuario()));
            oBacklogBean.setRequerimiento(oRequerimientoDao.get(oBacklogBean.getRequerimiento()));
        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        oContexto.setVista("jsp/backlog/form.jsp");
        return oBacklogBean;
    }

}
