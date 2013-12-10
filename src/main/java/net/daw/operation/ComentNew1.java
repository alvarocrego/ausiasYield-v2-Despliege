/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.ComentBean;
import net.daw.helper.Contexto;
import net.daw.parameter.ComentParam;

/**
 *
 * @author Jordi Eslava Barrera
 */

public class ComentNew1 implements Operation{
     @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        ComentParam oComentParam = new ComentParam(request);
        ComentBean oComentBean = new ComentBean();
        try {
            oComentBean = oComentParam.load(oComentBean);

        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        oContexto.setVista("jsp/comentario/form.jsp");
        return oComentBean;
    }
    
}
