package net.daw.operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daw.bean.HiloBean;
import net.daw.dao.HiloDao;
import net.daw.helper.Contexto;
import net.daw.parameter.HiloParam;

public class HiloNew2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        HiloBean oHiloBean = new HiloBean();
        HiloDao oHiloDao = new HiloDao(oContexto.getEnumTipoConexion());
        HiloParam oHiloParam = new HiloParam(request);
        oHiloBean = oHiloParam.loadId(oHiloBean);
        try {
            oHiloBean = oHiloParam.load(oHiloBean);
        } catch (NumberFormatException e) {
            return "Tipo de dato incorrecto en uno de los campos del formulario";
        }
        try {
            oHiloDao.set(oHiloBean);
        } catch (Exception e) {
            throw new ServletException("ClienteController: New Error: Phase 2: " + e.getMessage());
        }
        String strMensaje = "Se ha añadido la información del hilo con id=" + Integer.toString(oHiloBean.getId()) + "<br />";
        strMensaje += "<a href=\"Controller?class=hilo&method=view&id=" + oHiloBean.getId() + "\">Ver hilo creado en el formulario</a><br />";
        return strMensaje;
    }
}
