/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.RequerimientoBean;
import net.daw.helper.Contexto;
import net.daw.parameter.RequerimientoParam;

/**
 *
 * @author Jordi Eslava Barrera
 */
public class RequerimientoNew1 implements Operation{
     @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        RequerimientoParam oRequerimientoParam = new RequerimientoParam(request);
        RequerimientoBean oRequerimientoBean = new RequerimientoBean();
        try {
            oRequerimientoBean = oRequerimientoParam.load(oRequerimientoBean);

        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        oContexto.setVista("jsp/requerimiento/form.jsp");
        return oRequerimientoBean;
    }
    
}
