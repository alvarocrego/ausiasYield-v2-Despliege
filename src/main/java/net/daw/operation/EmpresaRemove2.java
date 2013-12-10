package net.daw.operation;

/**
 *
 * @author AntonioNP
 */
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daw.bean.EmpresaBean;
import net.daw.dao.EmpresaDao;
import net.daw.helper.Contexto;
import net.daw.parameter.EmpresaParam;

public class EmpresaRemove2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        EmpresaBean oEmpresaBean = new EmpresaBean();
        EmpresaParam oEmpresaParam = new EmpresaParam(request);
        oEmpresaBean = oEmpresaParam.loadId(oEmpresaBean);
        try {
            EmpresaDao oEmpresaDAO = new EmpresaDao(oContexto.getEnumTipoConexion());
            oEmpresaDAO.remove(oEmpresaBean);
        } catch (Exception e) {
            throw new ServletException("EmpresaController: Remove Error: " + e.getMessage());
        }
        String Mensaje = ("Se ha eliminado la información del empresa con id= " + Integer.toString(oEmpresaBean.getId()));
        return Mensaje;
    }
}
