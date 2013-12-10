/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jordi Eslava Barrera
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daw.bean.ComentBean;
import net.daw.dao.ComentDao;
import net.daw.helper.Contexto;
import net.daw.parameter.ComentParam;

public class ComentNew2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        ComentBean oComentBean = new ComentBean();
        ComentDao oComentDao = new ComentDao(oContexto.getEnumTipoConexion());
        ComentParam oComentParam = new ComentParam(request);
        oComentBean = oComentParam.loadId(oComentBean);
        try {
            oComentBean = oComentParam.load(oComentBean);
        } catch (NumberFormatException e) {
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        try {
            oComentDao.set(oComentBean);
        } catch (Exception e) {
            throw new ServletException("ComentController: Update Error: Phase 2: " + e.getMessage());
        }
        String strMensaje = "Se ha añadido la información del comentario con id=" + Integer.toString(oComentBean.getId()) + "<br />";
        strMensaje += "<a href=\"Controller?class=coment&method=view&id=" + oComentBean.getId() + "\">Ver Comentario creado en el formulario</a><br />";
        return strMensaje;
    }
}
