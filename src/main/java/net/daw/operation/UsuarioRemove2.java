package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daw.bean.UsuarioBean;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.UsuarioParam;

/**
 *
 * @author Sergio Martín Tárraga
 * @version v2.0
 * @since mie, 27 noviembre 2013
 */
public class UsuarioRemove2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");   
        UsuarioBean oUsuarioBean = new UsuarioBean(); 
        UsuarioParam oUsuarioParam = new UsuarioParam(request);
        oUsuarioBean = oUsuarioParam.loadId(oUsuarioBean);
        try {
            UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
            oUsuarioDao.remove(oUsuarioBean);
        } catch (Exception e) {
            throw new ServletException("UsuarioController: Remove Error: " + e.getMessage());
        }
        String Mensaje = ("Se ha eliminado la información del usuario con id = " + Integer.toString(oUsuarioBean.getId()));
        return Mensaje;
    }

}
