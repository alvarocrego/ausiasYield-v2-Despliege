/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.VotoComentarioBean;
import net.daw.dao.VotoComentarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.VotoComentarioParam;

/**
 *
 * @author Diana
 */
public class VotoComentarioNew2 implements Operation {
    
     @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        switch (oContexto.getSearchingFor()) {
            case "id_usuario": {
                oContexto.setVista("jsp/usuario/list.jsp");
                oContexto.setClase("usuario");
                oContexto.setMetodo("list");
                oContexto.setFase("1");
                oContexto.setSearchingFor("usuario");
                oContexto.setClaseRetorno("votoComentario");
                oContexto.setMetodoRetorno("new");
                oContexto.setFaseRetorno("1");
                oContexto.removeParam("usuario");
                UsuarioList1 oOperacion = new UsuarioList1();
                return oOperacion.execute(request, response);
            }
            case "id_comentario": {
                oContexto.setVista("jsp/comentario/list.jsp");
                oContexto.setClase("comentario");
                oContexto.setMetodo("list");
                oContexto.setFase("1");
                oContexto.setSearchingFor("comentario");
                oContexto.setClaseRetorno("votoComentario");
                oContexto.setMetodoRetorno("new");
                oContexto.setFaseRetorno("1");
                oContexto.removeParam("comentario");
                ComentList1 oOperacion = new ComentList1();
                return oOperacion.execute(request, response);
            }
            default:
                oContexto.setVista("jsp/mensaje.jsp");
                VotoComentarioBean oVotoComentarioBean = new VotoComentarioBean();
                VotoComentarioDao oVotoComentarioDao = new VotoComentarioDao(oContexto.getEnumTipoConexion());
                VotoComentarioParam oVotoComentarioParam = new VotoComentarioParam(request);
                oVotoComentarioBean = oVotoComentarioParam.loadId(oVotoComentarioBean);
                try {
                    oVotoComentarioBean = oVotoComentarioParam.load(oVotoComentarioBean);
                } catch (NumberFormatException e) {
                    return "Tipo de dato incorrecto en uno de los campos del formulario";
                }
                try {
                    oVotoComentarioDao.set(oVotoComentarioBean);
                } catch (Exception e) {
                    throw new ServletException("VotoComentarioController: Update Error: Phase 2: " + e.getMessage());
                }
                String strMensaje = "Se ha añadido la información de votocomentario con id=" + Integer.toString(oVotoComentarioBean.getId()) + "<br />";
                /*strMensaje += "<a href=\"Controller?class=compra&method=list&filter=id_cliente&filteroperator=equals&filtervalue=" + oVotoComentarioBean.getCliente().getId() + "\">Ver compras de este cliente</a><br />";*/
                strMensaje += "<a href=\"Controller?class=votoComentario&method=view&id=" + oVotoComentarioBean.getId() + "\">Ver votoComentario creado en el formulario</a><br />";
                return strMensaje;
        }
    }
    
}
