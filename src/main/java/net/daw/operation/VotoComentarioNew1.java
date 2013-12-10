/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.VotoComentarioBean;
import net.daw.dao.ComentDao;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.VotoComentarioParam;

/**
 *
 * @author Diana
 */
public class VotoComentarioNew1 implements Operation {
    
     @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        VotoComentarioParam oVotoComentarioParam = new VotoComentarioParam(request);
        VotoComentarioBean oVotoComentarioBean = new VotoComentarioBean();
        UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
        ComentDao oComentDao = new ComentDao(oContexto.getEnumTipoConexion());
        try {
            oVotoComentarioBean = oVotoComentarioParam.load(oVotoComentarioBean);
            oVotoComentarioBean.setId_usuario(oUsuarioDao.get(oVotoComentarioBean.getId_usuario()));
            
            oVotoComentarioBean = oVotoComentarioParam.load(oVotoComentarioBean);
            oVotoComentarioBean.setId_comentario(oComentDao.get(oVotoComentarioBean.getId_comentario()));
        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        oContexto.setVista("jsp/votocomentario/form.jsp");
        return oVotoComentarioBean;
    }
    
}
