/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import java.util.regex.MatchResult;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.DocumentoBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.DocumentoDao;
import net.daw.helper.Contexto;
import static net.daw.helper.Enum.TipoUsuario.Profesor;
import net.daw.helper.TextParser;
import net.daw.parameter.DocumentoParam;

/**
 *
 * @author al037294
 */
public class DocumentoView1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UsuarioBean oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        Integer idUsuario = oUsuarioBean.getId();
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        Contexto oContexto = (Contexto) request.getAttribute("contexto");

        DocumentoBean oDocumentoBean;
        DocumentoDao oDocumentoDao;
        oDocumentoBean = new DocumentoBean();
        DocumentoParam oDocumentoParam = new DocumentoParam(request);
        oDocumentoBean = oDocumentoParam.loadId(oDocumentoBean);
        oDocumentoDao = new DocumentoDao(oContexto.getEnumTipoConexion());
        try {
            oDocumentoBean = oDocumentoDao.get(oDocumentoBean);
            oDocumentoBean.setContenidoParse(TextParser.toHtml(oDocumentoBean.getContenido(), oContexto.getSerializedParamsExceptOrder()));
        } catch (Exception e) {
            throw new ServletException("DocumentoController: View Error: Phase 1: " + e.getMessage());
        }
        oDocumentoBean = oDocumentoParam.load(oDocumentoBean);

        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)) {
            oContexto.setVista("jsp/documento/view.jsp");
            return oDocumentoBean;
        } else {
            if (idUsuario == oDocumentoBean.getUsuario().getId()) {

                oContexto.setVista("jsp/documento/view.jsp");
                return oDocumentoBean;
            } else {
                oContexto.setVista("jsp/mensaje.jsp");
                return "<div class=\"alert alert-error\">No se puede visualizar este documento<br/><br/>Posibles razones más frecuentes<ul><li>No eres el propietario o no tienes los permisos suficientes en este documento.</li><li>El documento al que intentas acceder no exsiste.</li><li>Ha habido un error en el servidor.</li></ul></div>";
            }
        }
    }
}
