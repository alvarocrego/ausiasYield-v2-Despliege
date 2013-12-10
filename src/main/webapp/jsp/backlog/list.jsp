<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Arrays"%>
<%@page import="net.daw.helper.FilterBean"%>
<%@ page import="net.daw.helper.Contexto"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="net.daw.bean.BacklogBean"%>
<%
    Contexto oContexto = (Contexto) request.getAttribute("contexto");
    ArrayList<Object> alObjetoParametro = (ArrayList<Object>) oContexto.getParametro();
    ArrayList<BacklogBean> alPagina = (ArrayList<BacklogBean>) alObjetoParametro.get(0);
    Iterator<BacklogBean> oIterador = alPagina.listIterator();
%>
<div class="row-fluid">
    <div class="span8">
        <h1>Listado de BackLog</h1>
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
        <%
            Integer registers = (Integer) alObjetoParametro.get(2);
            out.print("Mostrando " + oContexto.getNrpp().toString() + " registros de un total de " + registers.toString());
        %>                
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
            <legend>Filtro de Backlog</legend> 
            <form class="navbar-form pull-right" action="Controller" method="post" id="clienteForm">
                <fieldset>                                               
                    <%=oContexto.getSerializedParamsExceptFilterFormFormat()%>       
                    <span>
                        <select id="filter" name="filter" width="80" style="width: 80px">
                            <option>id_usuario</option>                       
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
        <th>Usuario
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_usuario&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_usuario&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th>Requerimiento
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_requerimiento&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_requerimiento&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th>Enunciado
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=enunciado&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=enunciado&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th>Descripcion
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=descripciondetallado&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=descripciondetallado&ordervalue=desc"><i class="icon-arrow-down"></i></a>                
        </th>
        <th>Fecha de Alta
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=fechaalta&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=fechaalta&ordervalue=desc"><i class="icon-arrow-down"></i></a>                            
        </th>
        <th>Operaciones</th>
    </tr>
    <%
        while (oIterador.hasNext()) {
            BacklogBean oBacklogBEAN = oIterador.next();
    %>
    <tr>
        <td><%=oBacklogBEAN.getId()%></td>
        <td>
            <%=oBacklogBEAN.getUsuario().getLogin()%>(<%=oBacklogBEAN.getUsuario().getId()%>)
            <div class="btn-group">
                <a class="btn btn-mini" href="Controller?class=usuario&method=list&id=<%=oBacklogBEAN.getId()%>&searchingfor=usuario&returnclass=backlog&returnmethod=update&returnphase=2"><i class="icon-search"></i></a>                                        
            </div>          
        </td>
        <td>
            <%=oBacklogBEAN.getRequerimiento().getEnunciado()%>(<%=oBacklogBEAN.getRequerimiento().getId()%>)
            <div class="btn-group">
                <a class="btn btn-mini" href="Controller?class=requerimiento&method=list&id=<%=oBacklogBEAN.getId()%>&searchingfor=requerimiento&returnclass=backlog&returnmethod=update&returnphase=2"><i class="icon-search"></i></a>                                        
            </div>          
        </td>
        <td><%=oBacklogBEAN.getEnunciado()%></td>
        <td><%=oBacklogBEAN.getDescripciondetallada()%></td>
        <% SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");%>
        <td><%=formatoFecha.format(oBacklogBEAN.getFechaalta())%></td>
        <td>
            <div class="btn-toolbar">
                <div class="btn-group">
                    <%
                        if (oContexto.getSearchingFor().equals("backlog")) {
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?" + oContexto.getSerializedParamsExcept(new ArrayList<String>(Arrays.asList("class", "method", "phase", "id_producto", "id", "returnclass", "returnmethod", "returnphase", "searchingfor"))) + "class=" + oContexto.getClaseRetorno() + "&method=" + oContexto.getMetodoRetorno() + "&phase=" + oContexto.getFaseRetorno() + "&id_producto=" + oBacklogBEAN.getId() + "&id=" + oContexto.getId() + "\"><i class=\"icon-ok\"></i></a>");
                        } else {
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?class=backlog&method=view&id=" + oBacklogBEAN.getId() + "\"><i class=\"icon-eye-open\"></i></a>");
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?class=backlog&method=update&id=" + oBacklogBEAN.getId() + "\"><i class=\"icon-pencil\"></i></a>");
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?class=backlog&method=remove&id=" + oBacklogBEAN.getId() + "\"><i class=\"icon-trash\"></i></a>");
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
