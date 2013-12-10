package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.AlumnoBean;
import net.daw.bean.UsuarioBean;
import net.daw.helper.Contexto;
import net.daw.parameter.AlumnoParam;

/**
 *
 * @author Sergio Martín Tárraga
 * @version v3.0
 * @since mie, 25 noviembre 2013
 * Añadido control de usuarios
 */
public class AlumnoNew1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        
        /*
         * Para saber quien se ha logueado
         */
        UsuarioBean oUsuarioBean;
        oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
        
        AlumnoParam oAlumnoParam = new AlumnoParam(request);
        AlumnoBean oAlumnoBean = new AlumnoBean();
        
       /*
        * Validación
        */
        if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Alumno)) {

            try {
                oAlumnoBean = oAlumnoParam.load(oAlumnoBean);
            } catch (NumberFormatException e) {
                oContexto.setVista("jsp/mensaje.jsp");
                return "Tipo de dato incorrecto en uno de los campos del formulario";
            }
            oContexto.setVista("jsp/alumno/form.jsp");
        } else {
            //Mostramos el MENSAJE
            oContexto.setVista("jsp/mensaje.jsp");
            return "<span class=\"label label-important\">¡No estás autorizado a entrar aquí!<span>";
        }
        return oAlumnoBean;
    }

}
