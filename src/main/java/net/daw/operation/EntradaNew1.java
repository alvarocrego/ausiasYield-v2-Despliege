/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.EntradaBean;
import net.daw.dao.HiloDao;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.EntradaParam;

/**
 *
 * @author jose
 */
public class EntradaNew1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        EntradaParam oEntradaParam = new EntradaParam(request);
        EntradaBean oEntradaBean = new EntradaBean();
        HiloDao oHiloDao = new HiloDao(oContexto.getEnumTipoConexion());
        UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
        try {
            oEntradaBean = oEntradaParam.load(oEntradaBean);
            oEntradaBean.setHilo(oHiloDao.get(oEntradaBean.getHilo()));
            oEntradaBean = oEntradaParam.load(oEntradaBean);
            oEntradaBean.setUsuario(oUsuarioDao.get(oEntradaBean.getUsuario()));
        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        oContexto.setVista("jsp/entrada/form.jsp");
        return oEntradaBean;
    }

}
