package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.daw.bean.HiloBean;
import net.daw.bean.UsuarioBean;
import net.daw.helper.Contexto;
import net.daw.helper.Enum;
import net.daw.parameter.HiloParam;

public class HiloNew1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        //Parte para saber el tipo de usuario
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        //Hasta aquí
        HiloParam oHiloParam = new HiloParam(request);
        HiloBean oHiloBean = new HiloBean();

        //Validación
        if (tipoUsuario.equals(Enum.TipoUsuario.Profesor)) {

            try {
                oHiloBean = oHiloParam.load(oHiloBean);
            } catch (NumberFormatException e) {
                oContexto.setVista("jsp/mensaje.jsp");
                return "Tipo de dato incorrecto en uno de los campos del formulario";
            }
            oContexto.setVista("jsp/hilo/form.jsp");
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡¡¡ No estás autorizado a entrar aquí !!!<span>";
        }
        return oHiloBean;
    }

}
