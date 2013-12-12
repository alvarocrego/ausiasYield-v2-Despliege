<%-- 
    Document   : list
    Created on : 28-nov-2013, 9:25:11
    Author     : al037877
--%>

<%@page import="net.daw.bean.UsuarioBean"%>
<%@page import="net.daw.data.Mysql"%>
<%@page import="java.util.Arrays"%>
<%@page import="net.daw.helper.FilterBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="net.daw.bean.BolsaBean"%>
<%@ page import="net.daw.helper.Contexto"%>
<%
    Contexto oContexto = (Contexto) request.getAttribute("contexto");
    ArrayList<Object> alObjetoParametro = (ArrayList<Object>) oContexto.getParametro();
    ArrayList<BolsaBean> alPagina = (ArrayList<BolsaBean>) alObjetoParametro.get(0);
    Iterator<BolsaBean> oIterador = alPagina.listIterator();
%>
<div class="row-fluid">
    <div class="span8">
        <h1>Listado de bolsa</h1>
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
            out.print("<p>Mostrando " + oContexto.getNrpp().toString() + " registros de un total de " + registers.toString() + "</p>");
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
            <legend>Filtro de bolsa</legend> 
            <form class="navbar-form pull-right" action="Controller" method="post" id="filtroEstadoForm">
                <fieldset>                                               
                    <%=oContexto.getSerializedParamsExceptFilterFormFormat()%>       
                    <span>
                        <select id="filter" name="filter" width="80" style="width: 80px">
                            <option>id</option>
                            <option value="id_documento1">documento1</option>
                            <option value="id_documento2">documento2</option>
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
        <th>documento 1
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_documento1&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_documento1&ordervalue=desc"><i class="icon-arrow-down"></i></a>
        </th>
        <th>documento 2
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_documento2&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_documento2&ordervalue=desc"><i class="icon-arrow-down"></i></a>
        </th>
        <th>fecha
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=fecha&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=fecha&ordervalue=desc"><i class="icon-arrow-down"></i></a>
        </th>
        <th>Operaciones

        </th>


    </tr>
    <%        while (oIterador.hasNext()) {
            BolsaBean oBolsaBEAN = oIterador.next();

    %>
    <tr>
        <td><%=oBolsaBEAN.getId()%></td>
        <td>
            <%=oBolsaBEAN.getDocumento1().getId() + " " + oBolsaBEAN.getDocumento1().getTitulo()%>
            <div class="btn-group">
                <a class="btn btn-mini" href="Controller?class=documento&method=list&id=<%=oBolsaBEAN.getDocumento1().getId()%>&searchingfor=documento1&returnclass=bolsa&returnmethod=update&returnphase=2"><i class="icon-search"></i></a>                                        
            </div>
        </td>
        <td>
            <%=oBolsaBEAN.getDocumento2().getId() + " " + oBolsaBEAN.getDocumento2().getTitulo()%>
            <div class="btn-group">
                <a class="btn btn-mini" href="Controller?class=documento&method=list&id=<%=oBolsaBEAN.getDocumento2().getId()%>&searchingfor=documento2&returnclass=bolsa&returnmethod=update&returnphase=2"><i class="icon-search"></i></a>                                        
            </div>
        </td>
        <%
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");

        %>
        <td><%=formatoFecha.format(oBolsaBEAN.getFecha())%></td>


        <td>
            <div class="btn-toolbar">
                <div class="btn-group"> 
                    <%
                        //Permisos
                        UsuarioBean oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
                        Integer idUsuario = oUsuarioBean.getId();
                        java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();

                        if (oContexto.getSearchingFor().equals("bolsa")) {
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?" + oContexto.getSerializedParamsExcept(new ArrayList<String>(Arrays.asList("class", "method", "phase", "id_bolsa", "id", "returnclass", "returnmethod", "returnphase", "searchingfor"))) + "class=" + oContexto.getClaseRetorno() + "&method=" + oContexto.getMetodoRetorno() + "&phase=" + oContexto.getFaseRetorno() + "&id_bolsa=" + oBolsaBEAN.getId() + "&id=" + oContexto.getId() + "\"><i class=\"icon-ok\"></i></a>");
                        } else {
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?class=bolsa&method=view&id=" + oBolsaBEAN.getId() + "\"><i class=\"icon-eye-open\"></i></a>");
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?class=bolsa&method=update&id=" + oBolsaBEAN.getId() + "\"><i class=\"icon-pencil\"></i></a>");
                            if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Empresa)) {//permisos
                                out.print("<a class=\"btn btn-mini\" href=\"Controller?class=bolsa&method=remove&id=" + oBolsaBEAN.getId() + "\"><i class=\"icon-trash\"></i></a>");
                            }
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
