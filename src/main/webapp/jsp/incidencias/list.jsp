<%-- 
    Document   : list
    Created on : 09-nov-2013, 17:32:12
    Author     : Enrique
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Arrays"%>
<%@page import="net.daw.helper.FilterBean"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="net.daw.bean.IncidenciasBean"%>
<%@ page import="net.daw.helper.Contexto"%>
<%
    Contexto oContexto = (Contexto) request.getAttribute("contexto");
    ArrayList<Object> alObjetoParametro = (ArrayList<Object>) oContexto.getParametro();
    ArrayList<IncidenciasBean> alPagina = (ArrayList<IncidenciasBean>) alObjetoParametro.get(0);
    Iterator<IncidenciasBean> oIterador = alPagina.listIterator();
%>
<div class="row-fluid">
    <div class="span8">
        <h1>Listado de Incidencias</h1>
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
            <legend>Filtro de Incidencias</legend> 
            <form class="navbar-form pull-right" action="Controller" method="post" id="filtroIncidenciasForm">
                <fieldset>                                               
                    <%=oContexto.getSerializedParamsExceptFilterFormFormat()%>       
                    <span>
                        <select id="filter" name="filter" width="80" style="width: 80px">
                            <option>id</option>
                            <option>resumen</option>
                            <option>cambios</option>
                            <option>id_estado</option>
                            <option>id_repositorio</option>
                            <option>id_usuario</option>
                            <option>fechaalta</option>
                            <option>fecharesolucion</option>
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
                        <input id="filtervalue" name="filtervalue" type="search" size="20" maxlength="50" value=""  width="100" style="width: 100px"/>
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
        <th>resumen
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=resumen&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=resumen&ordervalue=desc"><i class="icon-arrow-down"></i></a>
        </th>
        <th>cambios
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=cambios&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=cambios&ordervalue=desc"><i class="icon-arrow-down"></i></a>
        </th>
        <th>id_estado
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_estado&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_estado&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th>id_repositorio
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_repositorio&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_repositorio&ordervalue=desc"><i class="icon-arrow-down"></i></a>         
        </th>
        <th>id_usuario
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_usuario&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_usuario&ordervalue=desc"><i class="icon-arrow-down"></i></a>         
        </th>
        <th>feachaalta
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=feachaalta&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=feachaalta&ordervalue=desc"><i class="icon-arrow-down"></i></a>         
        </th>
        <th>feacharesolucion
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=feacharesolucion&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=feacharesolucion&ordervalue=desc"><i class="icon-arrow-down"></i></a>         
        </th>
        
        <th>Operaciones</th>
    </tr>
    <%        while (oIterador.hasNext()) {
            IncidenciasBean oIncidenciasBEAN = oIterador.next();
    %>
    <tr>
        <td><%=oIncidenciasBEAN.getId()%></td>
        <td><%=oIncidenciasBEAN.getResumen()%></td>
        <td><%=oIncidenciasBEAN.getCambios()%></td>
        
        
        <td>
            <%=oIncidenciasBEAN.getEstado().getNombre()%> (<%=oIncidenciasBEAN.getEstado().getId()%>)
            <div class="btn-group">
                <a class="btn btn-mini" href="Controller?class=estado&method=list&id=<%=oIncidenciasBEAN.getId()%>&searchingfor=estado&returnclass=incidencias&returnmethod=update&returnphase=2"><i class="icon-search"></i></a>                                        
            </div>
        </td>
        
        

         
         <td>
            <%=oIncidenciasBEAN.getRepositorio().getTitulo()%> (<%=oIncidenciasBEAN.getRepositorio().getId()%>)
            <div class="btn-group">
                <a class="btn btn-mini" href="Controller?class=repositorio&method=list&id=<%=oIncidenciasBEAN.getId()%>&searchingfor=repositorio&returnclass=incidencias&returnmethod=update&returnphase=2"><i class="icon-search"></i></a>                                        
            </div>
        </td>
         
       
        
        
        <td>
            <%=oIncidenciasBEAN.getUsuario().getId()%> (<%=oIncidenciasBEAN.getUsuario().getId()%>)
            <div class="btn-group">
                <a class="btn btn-mini" href="Controller?class=usuario&method=list&id=<%=oIncidenciasBEAN.getId()%>&searchingfor=usuario&returnclass=incidencias&returnmethod=update&returnphase=2"><i class="icon-search"></i></a>                                        
            </div>
        </td>
        
        
        
        
        <%
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
        %>
        <td><%=formatoFecha.format(oIncidenciasBEAN.getFechaAlta())%></td>
        <td><%=formatoFecha.format(oIncidenciasBEAN.getFechaResolucion())%></td>
               
        <td>
            <div class="btn-toolbar">
                <div class="btn-group"> 
                    <%
                        if (oContexto.getSearchingFor().equals("incidencias")) {
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?" + oContexto.getSerializedParamsExcept(new ArrayList<String>(Arrays.asList("class", "method", "phase", "id_incidencias", "id", "returnclass", "returnmethod", "returnphase", "searchingfor"))) + "class=" + oContexto.getClaseRetorno() + "&method=" + oContexto.getMetodoRetorno() + "&phase=" + oContexto.getFaseRetorno() + "&id_incidencias=" + oIncidenciasBEAN.getId() + "&id=" + oContexto.getId() + "\"><i class=\"icon-ok\"></i></a>");
                        } else {
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?class=incidencias&method=view&id=" + oIncidenciasBEAN.getId() + "\"><i class=\"icon-eye-open\"></i></a>");
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?class=incidencias&method=update&id=" + oIncidenciasBEAN.getId() + "\"><i class=\"icon-pencil\"></i></a>");
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?class=incidencias&method=remove&id=" + oIncidenciasBEAN.getId() + "\"><i class=\"icon-trash\"></i></a>");
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
