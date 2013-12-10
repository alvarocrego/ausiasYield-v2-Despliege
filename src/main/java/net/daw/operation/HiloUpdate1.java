package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.HiloBean;
import net.daw.dao.HiloDao;
import net.daw.helper.Contexto;
import net.daw.parameter.HiloParam;

public class HiloUpdate1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/hilo/form.jsp");
        HiloBean oHiloBean;
        HiloDao oHiloDao;
        oHiloBean = new HiloBean();
        HiloParam oHiloParam = new HiloParam(request);
        oHiloBean = oHiloParam.loadId(oHiloBean);
        oHiloDao = new HiloDao(oContexto.getEnumTipoConexion());
        try {
            oHiloBean = oHiloDao.get(oHiloBean);
        } catch (Exception e) {
            throw new ServletException("HiloController: Update Error: Phase 1: " + e.getMessage());
        }

        try {
            oHiloBean = oHiloParam.load(oHiloBean);
        } catch (NumberFormatException e) {
            oContexto.setVista("jsp/mensaje.jsp");
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }

        return oHiloBean;

    }
}
