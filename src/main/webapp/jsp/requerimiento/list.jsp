<%-- 
    Document   : list
    Created on : 13-nov-2013, 13:30:09
    Author     : Jordi Eslava Barrera
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Arrays"%>
<%@page import="net.daw.helper.FilterBean"%>
<%@ page import="net.daw.helper.Contexto"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="net.daw.bean.RequerimientoBean"%>
<%
    Contexto oContexto = (Contexto) request.getAttribute("contexto");
    ArrayList<Object> alObjetoParametro = (ArrayList<Object>) oContexto.getParametro();
    ArrayList<RequerimientoBean> alPagina = (ArrayList<RequerimientoBean>) alObjetoParametro.get(0);
    Iterator<RequerimientoBean> oIterador = alPagina.listIterator();
%>
<div class="row-fluid">
    <div class="span8">
        <h1>Listado de Requerimientos</h1>
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
            <legend>Filtro de Requerimiento</legend> 
            <form class="navbar-form pull-right" action="Controller" method="post" id="RequerimientoForm">
                <fieldset>                                               
                    <%=oContexto.getSerializedParamsExceptFilterFormFormat()%>       
                    <span>
                        <select id="filter" name="filter" width="80" style="width: 80px">
                            <option>id</option>
                            <option>enunciado</option>
                            <option>fechaalta</option>                           
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
        </div>
    </div>
</div>
<table class="table table-hover table-condensed">
    <tr>
        <th>id
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th>enunciado
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=enunciado&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=enunciado&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th>fechaalta
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=fechaalta&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=fechaalta&ordervalue=desc"><i class="icon-arrow-down"></i></a>         
        </th>

        <th>Operaciones</th>
    </tr>
    <%
        while (oIterador.hasNext()) {
            RequerimientoBean oRequerimientoBean = oIterador.next();
    %>
    <tr>
        <td><%=oRequerimientoBean.getId()%></td>
        <td><%=oRequerimientoBean.getEnunciado()%></td>
        <%
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
        %>
        <td><%=formatoFecha.format(oRequerimientoBean.getFecha())%></td>
        <td>
            <div class="btn-toolbar">
                <div class="btn-group">
                    <%
                        if (oContexto.getSearchingFor().equals("requerimiento")) {
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?" + oContexto.getSerializedParamsExcept(new ArrayList<String>(Arrays.asList("class", "method", "phase", "id_requerimiento", "id", "returnclass", "returnmethod", "returnphase", "searchingfor"))) + "class=" + oContexto.getClaseRetorno() + "&method=" + oContexto.getMetodoRetorno() + "&phase=" + oContexto.getFaseRetorno() + "&id_requerimiento=" + oRequerimientoBean.getId() + "&id=" + oContexto.getId() + "\"><i class=\"icon-ok\"></i></a>");
                        } else {
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?class=requerimiento&method=view&id=" + oRequerimientoBean.getId() + "\"><i class=\"icon-eye-open\"></i></a>");
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?class=requerimiento&method=update&id=" + oRequerimientoBean.getId() + "\"><i class=\"icon-pencil\"></i></a>");
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?class=requerimiento&method=remove&id=" + oRequerimientoBean.getId() + "\"><i class=\"icon-trash\"></i></a>");
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


