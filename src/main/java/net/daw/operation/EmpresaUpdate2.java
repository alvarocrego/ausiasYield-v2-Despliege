package net.daw.operation;

/**
 *
 * @author AntonioNP
 */
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.EmpresaBean;
import net.daw.bean.UsuarioBean;
import net.daw.dao.EmpresaDao;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;
import net.daw.parameter.EmpresaParam;
import net.daw.parameter.UsuarioParam;

public class EmpresaUpdate2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        
        EmpresaBean oEmpresaBean = new EmpresaBean();
        EmpresaDao oEmpresaDao = new EmpresaDao(oContexto.getEnumTipoConexion());
        EmpresaParam oEmpresaParam = new EmpresaParam(request);
        oEmpresaBean = oEmpresaParam.loadId(oEmpresaBean);
/*
        UsuarioBean oUsuarioBean = new UsuarioBean();
        UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());
        UsuarioParam oUsuarioParam = new UsuarioParam(request);
        oUsuarioBean = oUsuarioParam.loadId(oUsuarioBean);
*/
        try {
            oEmpresaBean = oEmpresaParam.load(oEmpresaBean);
    //        oUsuarioBean = oUsuarioParam.load(oUsuarioBean);
        } catch (NumberFormatException e) {
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        try {
            oEmpresaDao.set(oEmpresaBean);
    //        oUsuarioDao.set(oUsuarioBean);
        } catch (Exception e) {
            throw new ServletException("EmpresaController: Update Error: Phase 2: " + e.getMessage());
        }
        return "Se ha modificado la información del empresa con id= " + Integer.toString(oEmpresaBean.getId());
    }
}
