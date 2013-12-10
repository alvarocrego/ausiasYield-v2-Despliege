package net.daw.operation;

/**
 *
 * @author AntonioNP
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.EmpresaBean;
import net.daw.helper.Contexto;
import net.daw.parameter.EmpresaParam;

public class EmpresaRemove1 implements Operation{
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/confirmForm.jsp");
        EmpresaBean oEmpresaBean = new EmpresaBean();   
        EmpresaParam oEmpresaParam = new EmpresaParam(request);
        oEmpresaBean = oEmpresaParam.loadId(oEmpresaBean);
        return "Borrar al empresa " + oEmpresaBean.getId();

    }
}
