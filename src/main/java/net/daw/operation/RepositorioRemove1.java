/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.RepositorioBean;
import net.daw.bean.UsuarioBean;
import net.daw.helper.Contexto;
import net.daw.parameter.RepositorioParam;

/**
 *
 * @author Ana
 */
public class RepositorioRemove1 implements Operation{
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/confirmForm.jsp");
        RepositorioBean oRepositorioBean = new RepositorioBean();   
        RepositorioParam oRepositorioParam = new RepositorioParam(request);
        oRepositorioBean = oRepositorioParam.loadId(oRepositorioBean);
        
         UsuarioBean oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Empresa)) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "<div class=\"alert alert-error\">No tienes acceso</div>";
        } else {
        
        return "Borrar el Repositorio " + oRepositorioBean.getId();
        }
    }
}
