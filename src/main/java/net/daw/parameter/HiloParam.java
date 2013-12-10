package net.daw.parameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.HiloBean;

/**
 *
 * @author rafa
 */
public class HiloParam {

    private final HttpServletRequest request;

    public HiloParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public HiloBean loadId(HiloBean oCliente) throws NumberFormatException {
        try {
            if (request.getParameter("id") != null) {
                oCliente.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oCliente.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oCliente;
    }

    public HiloBean load(HiloBean oHilo) throws NumberFormatException, ParseException {
        try {
            if ((request.getParameter("nombre") != null)) {
                oHilo.setNombre(request.getParameter("nombre"));
            }
            if ((request.getParameter("fecha") != null)) {
                oHilo.setFecha(new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("fecha")));
            }

        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oHilo;
    }
}
