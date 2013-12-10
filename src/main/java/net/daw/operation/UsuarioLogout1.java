package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.helper.Contexto;
import net.daw.parameter.ContextParam;

public class UsuarioLogout1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        request.getSession().invalidate();
        ContextParam oContextParam=new ContextParam(request);
        oContexto=oContextParam.loadSession(oContexto);        
        return "Has salido de la aplicación. Hasta pronto.";
    }

}
