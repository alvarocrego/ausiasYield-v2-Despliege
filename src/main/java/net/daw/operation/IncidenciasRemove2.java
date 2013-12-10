/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.IncidenciasBean;
import net.daw.dao.IncidenciasDao;
import net.daw.helper.Contexto;
import net.daw.parameter.IncidenciasParam;
/**
 *
 * @author Enrique
 */
public class IncidenciasRemove2 implements Operation{
    
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");   
        IncidenciasBean oIncidenciasBean = new IncidenciasBean(); 
        IncidenciasParam oIncidenciasParam = new IncidenciasParam(request);
        oIncidenciasBean = oIncidenciasParam.loadId(oIncidenciasBean);
        try {
            IncidenciasDao oIncidenciasDao = new IncidenciasDao(oContexto.getEnumTipoConexion());
            oIncidenciasDao.remove(oIncidenciasBean);
        } catch (Exception e) {
            throw new ServletException("RepositorioController: Remove Error: " + e.getMessage());
        }
        String Mensaje = ("Se ha eliminado la información de incidencias con id=" + Integer.toString(oIncidenciasBean.getId()));
        return Mensaje;
    }
    
}
