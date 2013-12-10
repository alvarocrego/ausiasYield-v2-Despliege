/**
 *
 * @author al037431
 */
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.IncidenciasBean;
import net.daw.dao.EstadoDao;
import net.daw.dao.RepositorioDao;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.IncidenciasParam;

public class IncidenciasNew1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        IncidenciasParam oIncidenciasParam = new IncidenciasParam(request);
        IncidenciasBean oIncidenciasBean = new IncidenciasBean();
        UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
        EstadoDao oEstadoDao = new EstadoDao(oContexto.getEnumTipoConexion());
        RepositorioDao oRepositorioDao = new RepositorioDao(oContexto.getEnumTipoConexion());
        try {
            oIncidenciasBean = oIncidenciasParam.load(oIncidenciasBean);
            oIncidenciasBean.setUsuario(oUsuarioDao.get(oIncidenciasBean.getUsuario()));
            oIncidenciasBean.setEstado(oEstadoDao.get(oIncidenciasBean.getEstado()));
            if (oIncidenciasBean.getRepositorio().getId() != 0) {
                oIncidenciasBean.setRepositorio(oRepositorioDao.get(oIncidenciasBean.getRepositorio()));
            }

        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        oContexto.setVista("jsp/incidencias/form.jsp");
        return oIncidenciasBean;
    }
}
