package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.UsuarioBean;
import net.daw.helper.Contexto;
import net.daw.parameter.UsuarioParam;

/**
 *
 * @author Sergio Martín Tárraga
 * @version v2.0
 * @since mie, 27 noviembre 2013
 */
public class UsuarioRemove1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/confirmForm.jsp");
        UsuarioBean oUsuarioBean = new UsuarioBean();   
        UsuarioParam oUsuarioParam = new UsuarioParam(request);
        oUsuarioBean = oUsuarioParam.loadId(oUsuarioBean);
        return "Borrar al usuario " + oUsuarioBean.getId();

    }
}
