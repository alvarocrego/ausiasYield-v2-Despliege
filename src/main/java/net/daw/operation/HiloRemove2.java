package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daw.bean.HiloBean;
import net.daw.dao.HiloDao;
import net.daw.helper.Contexto;
import net.daw.parameter.HiloParam;

public class HiloRemove2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        HiloBean oHiloBean = new HiloBean();
        HiloParam oHiloParam = new HiloParam(request);
        oHiloBean = oHiloParam.loadId(oHiloBean);
        try {
            HiloDao oHiloDAO = new HiloDao(oContexto.getEnumTipoConexion());
            oHiloDAO.remove(oHiloBean);
        } catch (Exception e) {
            throw new ServletException("ClienteController: Remove Error: " + e.getMessage());
        }
        String Mensaje = ("Se ha eliminado la información del hilo con id=" + Integer.toString(oHiloBean.getId()));
        return Mensaje;
    }

}
