/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.RepositorioBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.DocumentoDao;
import net.daw.dao.LenguajeDao;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.RepositorioParam;

/**
 *
 * @author al037793
 */
public class RepositorioNew1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        RepositorioParam oRepositorioParam = new RepositorioParam(request);
        RepositorioBean oRepositorioBean = new RepositorioBean();
        UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
        LenguajeDao oLenguajeDao = new LenguajeDao(oContexto.getEnumTipoConexion());
        DocumentoDao oDocumentoDao = new DocumentoDao(oContexto.getEnumTipoConexion());

        UsuarioBean oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Empresa)) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "<div class=\"alert alert-error\">No tienes acceso</div>";
        } else {


            try {
                oRepositorioBean = oRepositorioParam.load(oRepositorioBean);
                oRepositorioBean.setUsuario(oUsuarioDao.get(oRepositorioBean.getUsuario()));
                oRepositorioBean.setLenguaje(oLenguajeDao.get(oRepositorioBean.getLenguaje()));
                oRepositorioBean.setDocumento(oDocumentoDao.get(oRepositorioBean.getDocumento()));
            } catch (NumberFormatException e) {
                oContexto.setVista("jsp/mensaje.jsp");
                return "Tipo de dato incorrecto en uno delos campos del formulario";
            }
            oContexto.setVista("jsp/repositorio/form.jsp");
            return oRepositorioBean;
        }
    }
}
