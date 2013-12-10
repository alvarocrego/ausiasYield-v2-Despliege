/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.DocumentoBean;
import net.daw.dao.DocumentoDao;
import net.daw.helper.Contexto;
import net.daw.parameter.DocumentoParam;

/**
 *
 * @author Alvaro
 */
public class DocumentoNew2 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        if ("usuario".equals(oContexto.getSearchingFor())) {
            oContexto.setVista("jsp/usuario/list.jsp");
            oContexto.setClase("usuario");
            oContexto.setMetodo("list");
            oContexto.setFase("1");
            oContexto.setClaseRetorno("documento");
            oContexto.setMetodoRetorno("new");
            oContexto.setFaseRetorno("1");
            oContexto.removeParam("id_usuario");
            UsuarioList1 oOperacion = new UsuarioList1();
            return oOperacion.execute(request, response);
        } else {
            oContexto.setVista("jsp/mensaje.jsp");
            DocumentoBean oDocumentoBean = new DocumentoBean();
            DocumentoDao oDocumentoDao = new DocumentoDao(oContexto.getEnumTipoConexion());
            DocumentoParam oDocumentoParam = new DocumentoParam(request);
            oDocumentoBean = oDocumentoParam.loadId(oDocumentoBean);
            try {
                oDocumentoBean = oDocumentoParam.load(oDocumentoBean);
            } catch (NumberFormatException e) {
                return "<div class=\"alert alert-error\">Tipo de dato incorrecto en uno de los campos del formulario</div>";
            }
            try {
                oDocumentoDao.set(oDocumentoBean);
            } catch (Exception e) {
                throw new ServletException("DocumentoController: Update Error: Phase 2: " + e.getMessage());
            }
            String strMensaje = "<div class=\"alert alert-success\">Se ha añadido la información del documento con id=" + Integer.toString(oDocumentoBean.getId()) + "</div>";            
            strMensaje += "<a href=\"Controller?class=documento&method=view&id=" + oDocumentoBean.getId() + "\">Ver documento creado</a><br />";
            return strMensaje;
        }
    }
}
