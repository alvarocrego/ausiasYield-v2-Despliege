/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.EntradaBean;
import net.daw.dao.EntradaDao;
import net.daw.helper.Contexto;
import net.daw.parameter.EntradaParam;

/**
 *
 * @author jose
 */
public class EntradaNew2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        switch (oContexto.getSearchingFor()) {
            case "hilo": {
                oContexto.setVista("jsp/hilo/list.jsp");
                oContexto.setClase("hilo");
                oContexto.setMetodo("list");
                oContexto.setFase("1");
                oContexto.setSearchingFor("hilo");
                oContexto.setClaseRetorno("entrada");
                oContexto.setMetodoRetorno("new");
                oContexto.setFaseRetorno("1");
                oContexto.removeParam("id_hilo");
                HiloList1 oHilo = new HiloList1();
                return oHilo.execute(request, response);
            }
            case "usuario": {
                oContexto.setVista("jsp/usuario/list.jsp");
                oContexto.setClase("usuario");
                oContexto.setMetodo("list");
                oContexto.setFase("1");
                oContexto.setSearchingFor("usuario");
                oContexto.setClaseRetorno("entrada");
                oContexto.setMetodoRetorno("new");
                oContexto.setFaseRetorno("1");
                oContexto.removeParam("id_usuario");
                UsuarioList1 oOperacion = new UsuarioList1();
                return oOperacion.execute(request, response);
            }
            default:
                oContexto.setVista("jsp/mensaje.jsp");
                EntradaBean oEntradaBean = new EntradaBean();
                EntradaDao oEntradaDao = new EntradaDao(oContexto.getEnumTipoConexion());
                EntradaParam oEntradaParam = new EntradaParam(request);
                oEntradaBean = oEntradaParam.loadId(oEntradaBean);
                try {
                    oEntradaBean = oEntradaParam.load(oEntradaBean);
                } catch (NumberFormatException e) {
                    return "Tipo de dato incorrecto en uno de los campos del formulario";
                }
                try {
                    oEntradaDao.set(oEntradaBean);
                } catch (Exception e) {
                    throw new ServletException("EntradaController: Update Error: Phase 2: " + e.getMessage());
                }
                String strMensaje = "Se ha añadido la información de entrada con id=" + Integer.toString(oEntradaBean.getId()) + "<br />";
                strMensaje += "<a href=\"Controller?class=entrada&method=list&filter=id_usuario&filteroperator=equals&filtervalue=" + oEntradaBean.getUsuario().getId() + "\">Ver entradas de este usuario</a><br />";
                strMensaje += "<a href=\"Controller?class=entrada&method=view&id=" + oEntradaBean.getId() + "\">Ver entrada creada en el formulario</a><br />";
                return strMensaje;
        }
    }
}
