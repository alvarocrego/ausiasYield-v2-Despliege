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
 * @since jue, 28 noviembre 2013
 */
public class UsuarioUpdate2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");   
        UsuarioBean oUsuarioBean = new UsuarioBean();
        UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());

        UsuarioParam oUsuarioParam = new UsuarioParam(request);
        oUsuarioBean = oUsuarioParam.loadId(oUsuarioBean);
        try {
            oUsuarioBean = oUsuarioParam.load(oUsuarioBean);
        } catch (NumberFormatException e) {
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }         
        try {
            oUsuarioDao.set(oUsuarioBean);
        } catch (Exception e) {
            throw new ServletException("UsuarioController: Update Error: Phase 2: " + e.getMessage());
        }
        return "Se ha modificado la información del usuario con id = " + Integer.toString(oUsuarioBean.getId());
    }
}
