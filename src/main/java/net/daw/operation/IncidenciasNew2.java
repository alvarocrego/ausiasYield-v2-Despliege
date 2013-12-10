/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author al037431
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daw.bean.IncidenciasBean;
import net.daw.dao.IncidenciasDao;
import net.daw.helper.Contexto;
import net.daw.parameter.IncidenciasParam;

public class IncidenciasNew2 implements Operation {

   @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        switch (oContexto.getSearchingFor()) {
            case "estado": {
                oContexto.setVista("jsp/estado/list.jsp");
                oContexto.setClase("estado");
                oContexto.setMetodo("list");
                oContexto.setFase("1");
                oContexto.setSearchingFor("estado");
                oContexto.setClaseRetorno("incidencias");
                oContexto.setMetodoRetorno("new");
                oContexto.setFaseRetorno("1");
                oContexto.removeParam("id_estado");
                EstadoList1 oOperacion = new EstadoList1();
                return oOperacion.execute(request, response);
            }
            case "repositorio": {
                oContexto.setVista("jsp/repositorio/list.jsp");
                oContexto.setClase("repositorio");
                oContexto.setMetodo("list");
                oContexto.setFase("1");
                oContexto.setSearchingFor("repositorio");
                oContexto.setClaseRetorno("incidencias");
                oContexto.setMetodoRetorno("new");
                oContexto.setFaseRetorno("1");
                oContexto.removeParam("id_repositorio");
                RepositorioList1 oOperacion = new RepositorioList1();
                return oOperacion.execute(request, response);
            }
                 case "usuario": {
                oContexto.setVista("jsp/usuario/list.jsp");
                oContexto.setClase("usuario");
                oContexto.setMetodo("list");
                oContexto.setFase("1");
                oContexto.setSearchingFor("usuario");
                oContexto.setClaseRetorno("incidencias");
                oContexto.setMetodoRetorno("new");
                oContexto.setFaseRetorno("1");
                oContexto.removeParam("id_usuario");
                UsuarioList1 oOperacion = new UsuarioList1();
                return oOperacion.execute(request, response);
            }
            default:
                oContexto.setVista("jsp/mensaje.jsp");
                IncidenciasBean oIncidenciasBean = new IncidenciasBean();
                IncidenciasDao oIncidenciasDao = new IncidenciasDao(oContexto.getEnumTipoConexion());
                IncidenciasParam oIncidenciasParam = new IncidenciasParam(request);
                oIncidenciasBean = oIncidenciasParam.loadId(oIncidenciasBean);
                try {
                    oIncidenciasBean = oIncidenciasParam.load(oIncidenciasBean);
                } catch (NumberFormatException e) {
                    return "Tipo de dato incorrecto en uno de los campos del formulario";
                }
                try {
                    oIncidenciasDao.set(oIncidenciasBean);
                } catch (Exception e) {
                    throw new ServletException("IncidenciasController: Update Error: Phase 2: " + e.getMessage());
                }
                String strMensaje = "Se ha añadido la información de compra con id=" + Integer.toString(oIncidenciasBean.getId()) + "<br />";
                //strMensaje += "<a href=\"Controller?class=incidencias&method=list&filter=id_cliente&filteroperator=equals&filtervalue=" + oIncidenciasBean.getCliente().getId() + "\">Ver compras de este cliente</a><br />";
                strMensaje += "<a href=\"Controller?class=incidencias&method=view&id=" + oIncidenciasBean.getId() + "\">Ver incidencias creada en el formulario</a><br />";
                return strMensaje;
        }
    }
}
