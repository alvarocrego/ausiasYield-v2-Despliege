/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.UsuarioBean;
import net.daw.helper.Contexto;

/**
 *
 * @author al037213
 */
public class StreamView2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");

        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();

        //Validación
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Profesor)
                ||  tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Alumno)  ) {

            oContexto.setVista("jsp/stream/list.jsp");
            oContexto.setClase("stream");
            oContexto.setMetodo("list");
            oContexto.setFase("1");
            StreamList1 oOperacion = new StreamList1();
            return oOperacion.execute(request, response);
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";
        }
    }
}
