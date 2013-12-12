<%@page import="java.util.Arrays"%>
<%@page import="net.daw.helper.FilterBean"%>
<%@ page import="net.daw.helper.Contexto"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="net.daw.bean.EntregaBean"%>
<%
    Contexto oContexto = (Contexto) request.getAttribute("contexto");
    ArrayList<Object> alObjetoParametro = (ArrayList<Object>) oContexto.getParametro();
    ArrayList<EntregaBean> alPagina = (ArrayList<EntregaBean>) alObjetoParametro.get(0);
    Iterator<EntregaBean> oIterador = alPagina.listIterator();
%>
<div class="row-fluid">
    <div class="span8">
        <h1>Listado de entregas</h1>
        <%
            if (!oIterador.hasNext()) {
                out.print("<h4>Listado vacío</h4>");
            } else {
        %>
        <%
            if (oContexto.getHmOrder() != null) {
                out.print("<p>Listado ordenado por " + oContexto.getHmOrder().keySet().toArray()[0].toString() + "    ");
                out.print("<a href=\"Controller?" + oContexto.getSerializedParamsExceptOrder() + "\">(Quitar orden)</a></p>");
            } else {
                out.print("<p>Sin ordenar</p>");
            }
        %>
        <%
            if (oContexto.getAlFilter() != null) {
                out.print("<p>Listado filtrado: ");
                ArrayList<FilterBean> alFilter = oContexto.getAlFilter();
                Iterator iterator = alFilter.iterator();
                while (iterator.hasNext()) {
                    FilterBean oFilterBean = (FilterBean) iterator.next();
                    out.print("(" + oFilterBean.getFilter() + " " + oFilterBean.getFilterOperator() + " " + oFilterBean.getFilterValue() + ") ");
                }
                out.print("<a href=\"Controller?" + oContexto.getSerializedParamsExceptFilter() + "\">(Quitar filtro)</a></p>");
            } else {
                out.print("<p>Sin filtrar</p>");
            }
        %> 
        <a  class="btn" href="Controller?class=<%=oContexto.getClase()%>&method=new">Crear <%=oContexto.getClase()%></i></a>   
        <%
            ArrayList<String> paginacion = (ArrayList<String>) alObjetoParametro.get(1);
            Iterator<String> iterador2 = paginacion.listIterator();
            while (iterador2.hasNext()) {
                String o = iterador2.next();
                out.print(o);
            }
        %>
    </div>
    <div class="span4">
        <div class="text-right">
            <legend>Filtro de entrega</legend> 
            <form class="navbar-form pull-right" action="Controller" method="post" id="clienteForm">
                <fieldset>                                               
                    <%=oContexto.getSerializedParamsExceptFilterFormFormat()%>       
                    <span>
                        <select id="filter" name="filter" width="80" style="width: 80px">
                            <option>id</option>
                            <option>id_documento</option>
                            <option>id_actividad</option>
                            <option>nota</option>
                            <option>fecha</option>
                        </select>  
                    </span>
                    <span>
                        <select id="filteroperator" name="filteroperator" width="80" style="width: 80px">
                            <option>like</option>
                            <option>notlike</option>
                            <option>equals</option>
                            <option>notequalto</option>
                            <option>less</option>
                            <option>lessorequal</option>
                            <option>greater</option>
                            <option>greaterorequal</option>                            
                        </select>
                        <input id="filtervalue" name="filtervalue" type="text" size="20" maxlength="50" value=""  width="100" style="width: 100px"/>
                    </span>
                    <span>
                        <input type="submit" name="enviar" value="Filtrar" />
                    </span>
                </fieldset>
            </form>
        </div>
        <div class="text-right">
            <legend>Registros por página</legend> 
            <form class="navbar-form pull-right" action="Controller" method="post" id="nrrpForm" >
                <fieldset>                                               
                    <%=oContexto.getSerializedParamsExceptNrppFormFormat()%>       
                    <span>
                        <select  id="nrpp" name="nrpp" value="select" style="width: 80px">                        
                            <option <%if (oContexto.getNrpp() == 5) {
                                    out.print("selected");
                                }%>>5</option>
                            <option <%if (oContexto.getNrpp() == 10) {
                                    out.print("selected");
                                }%>>10</option>
                            <option <%if (oContexto.getNrpp() == 20) {
                                    out.print("selected");
                                }%>>20</option>
                            <option <%if (oContexto.getNrpp() == 50) {
                                    out.print("selected");
                                }%>>50</option>
                            <option <%if (oContexto.getNrpp() == 100) {
                                    out.print("selected");
                                }%>>100</option>
                        </select>  
                    </span>
                    <span>
                        <input type="submit" name="enviar" value="Establecer" />
                    </span>                    
                </fieldset>
            </form>
        </div>
    </div>
</div>
<table class="table table-hover table-condensed">
    <tr>
        <th>id
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th>documento
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_documento&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_documento&ordervalue=desc"><i class="icon-arrow-down"></i></a>                            
        </th>
        <th>actividad
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_actividad&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_actividad&ordervalue=desc"><i class="icon-arrow-down"></i></a>                            
        </th>
        <th>nota
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=nota&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=nota&ordervalue=desc"><i class="icon-arrow-down"></i></a>                            
        </th>
        <th>fecha
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=fecha&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=fecha&ordervalue=desc"><i class="icon-arrow-down"></i></a>                            
        </th>
        <th>Operaciones</th>
    </tr>
    <%
        while (oIterador.hasNext()) {
            EntregaBean oEntregaBEAN = oIterador.next();
    %>
    <tr>
        <td><%=oEntregaBEAN.getId()%></td>
        <td>
            <%=oEntregaBEAN.getDocumento().getTitulo()%> (<%=oEntregaBEAN.getDocumento().getId()%>)
            <div class="btn-group">
                <a class="btn btn-mini" href="Controller?class=documento&method=list&id=<%=oEntregaBEAN.getId()%>&searchingfor=documento&returnclass=entrega&returnmethod=update&returnphase=2"><i class="icon-search"></i></a>                                        
            </div>
        </td>
        <td>
            <%=oEntregaBEAN.getActividad().getEnunciado()%> (<%=oEntregaBEAN.getActividad().getId()%>)
            <div class="btn-group">
                <a class="btn btn-mini" href="Controller?class=actividad&method=list&id=<%=oEntregaBEAN.getId()%>&searchingfor=actividad&returnclass=entrega&returnmethod=update&returnphase=2"><i class="icon-search"></i></a>                                        
            </div>
        </td>
        <td><%=oEntregaBEAN.getNota()%></td>
        <td><%=oEntregaBEAN.getFecha()%></td>
        <td>
            <div class="btn-toolbar">
                <div class="btn-group">
                    <%
                        if (oContexto.getSearchingFor().equals("entrega")) {
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?" + oContexto.getSerializedParamsExcept(new ArrayList<String>(Arrays.asList("class", "method", "phase", "id_entrega", "id", "returnclass", "returnmethod", "returnphase", "searchingfor"))) + "class=" + oContexto.getClaseRetorno() + "&method=" + oContexto.getMetodoRetorno() + "&phase=" + oContexto.getFaseRetorno() + "&id_entrega=" + oEntregaBEAN.getId() + "&id=" + oContexto.getId() + "\"><i class=\"icon-ok\"></i></a>");
                        } else {
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?class=entrega&method=view&id=" + oEntregaBEAN.getId() + "\"><i class=\"icon-eye-open\"></i></a>");
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?class=entrega&method=update&id=" + oEntregaBEAN.getId() + "\"><i class=\"icon-pencil\"></i></a>");
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?class=entrega&method=remove&id=" + oEntregaBEAN.getId() + "\"><i class=\"icon-trash\"></i></a>");
                        }
                    %>                 
                </div>                
            </div>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%
    }
%>
