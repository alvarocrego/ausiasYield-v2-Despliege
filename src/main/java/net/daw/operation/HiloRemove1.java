package net.daw.operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.HiloBean;
import net.daw.helper.Contexto;
import net.daw.parameter.HiloParam;

public class HiloRemove1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/confirmForm.jsp");
        HiloBean oHiloBean = new HiloBean();   
        HiloParam oHiloParam = new HiloParam(request);
        oHiloBean = oHiloParam.loadId(oHiloBean);
        return "Borrar el hilo " + oHiloBean.getId();

    }
}
