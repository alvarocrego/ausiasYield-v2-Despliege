package net.daw.operation;

/**
 *
 * @author AntonioNP
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.helper.Contexto;

public class EmpresaNew1 implements Operation{
 @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/empresa/form.jsp");        
        return null;
    }

}
