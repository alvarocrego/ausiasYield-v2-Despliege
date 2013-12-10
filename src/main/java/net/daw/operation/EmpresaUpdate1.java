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

public class EmpresaUpdate1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/empresa/form.jsp");
        EmpresaBean oEmpresaBean;
        EmpresaDao oEmpresaDao;
        oEmpresaBean = new EmpresaBean();
        EmpresaParam oEmpresaParam = new EmpresaParam(request);
        oEmpresaBean = oEmpresaParam.loadId(oEmpresaBean);
        oEmpresaDao = new EmpresaDao(oContexto.getEnumTipoConexion());
        try {
            oEmpresaBean = oEmpresaDao.get(oEmpresaBean);
        } catch (Exception e) {
            throw new ServletException("EmpresaController: Update Error: Phase 1: " + e.getMessage());
        }
        try {
            oEmpresaBean = oEmpresaParam.load(oEmpresaBean);
        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        return oEmpresaBean;
    }
}
