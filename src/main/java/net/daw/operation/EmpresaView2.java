package net.daw.operation;

/**
 *
 * @author AntonioNP
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.helper.Contexto;

public class EmpresaView2 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/empresa/list.jsp");
        oContexto.setClase("empresa");
        oContexto.setMetodo("list");
        oContexto.setFase("1");
        EmpresaList1 oOperacion = new EmpresaList1();
        return oOperacion.execute(request, response);
    }
}
