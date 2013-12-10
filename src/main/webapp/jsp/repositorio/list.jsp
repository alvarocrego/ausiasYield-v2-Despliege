
<%@page import="java.util.Arrays"%>
<%@page import="net.daw.helper.TextParser"%>
<%@page import="net.daw.helper.FilterBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="net.daw.bean.RepositorioBean"%>
<%@ page import="net.daw.helper.Contexto"%>
<%
    Contexto oContexto = (Contexto) request.getAttribute("contexto");
    ArrayList<Object> alObjetoParametro = (ArrayList<Object>) oContexto.getParametro();
    ArrayList<RepositorioBean> alPagina = (ArrayList<RepositorioBean>) alObjetoParametro.get(0);
    Iterator<RepositorioBean> oIterador = alPagina.listIterator();
%>
<div class="row-fluid">
    <div class="span8">
        <h1>Listado de repositorios</h1>
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
                int bAddProductToClient = 0;
                while (iterator.hasNext()) {
                    FilterBean oFilterBean = (FilterBean) iterator.next();
                    out.print("(" + oFilterBean.getFilter() + " " + oFilterBean.getFilterOperator() + " " + oFilterBean.getFilterValue() + " )");
                    if (oFilterBean.getFilter().equals("id_usuario") && oFilterBean.getFilterOperator().equals("equals")) {
                        bAddProductToClient = Integer.parseInt(oFilterBean.getFilterValue());
                    }
                }
                out.print("<a href=\"Controller?" + oContexto.getSerializedParamsExceptFilter() + "\">(Quitar filtro)</a></p>");
                if (bAddProductToClient > 0) {
                    out.print("<a class=\"btn\" type=\"button\" href=\"Controller?searchingfor=cliente&class=compra&method=new&id_cliente=" + bAddProductToClient + "\">Añadir producto al cliente " + bAddProductToClient + "</a>");
                }
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
            <legend>Filtro de repositorio</legend> 
            <form class="navbar-form pull-right" action="Controller" method="post" id="filterForm">
                <fieldset>                                               
                    <%=oContexto.getSerializedParamsExceptFilterFormFormat()%>       
                    <span>
                        <select id="filter" name="filter" width="80" style="width: 80px">
                            <option>id</option>
                            <option>titulo</option>
                            <option>contenido</option>
                            <option>id_usuario</option>
                            <option>id_lenguaje</option>
                             <option>id_documento</option>
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
        <th>titulo
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=titulo&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=titulo&ordervalue=desc"><i class="icon-arrow-down"></i></a>
        </th>
        <th>contenido
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=contenido&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=contenido&ordervalue=desc"><i class="icon-arrow-down"></i></a>
        </th>
        <th>id_usuario
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_usuario&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_usuario&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th>id_lenguaje
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_lenguaje&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_lenguaje&ordervalue=desc"><i class="icon-arrow-down"></i></a>         
        </th>
        <th>id_documento
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_documento&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_documento&ordervalue=desc"><i class="icon-arrow-down"></i></a>         
        </th>
        <th>fecha
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=fecha&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=fecha&ordervalue=desc"><i class="icon-arrow-down"></i></a>         
        </th>
        <th>Operaciones</th>
    </tr>
    <%        while (oIterador.hasNext()) {
            RepositorioBean oRepositorioBean = oIterador.next();
            DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String contenido = "";
            if (oRepositorioBean.getContenido().length() >= 30) {
                contenido = oRepositorioBean.getContenido().substring(0, 30) + "... <a href=\"Controller?class=repositorio&method=view&id=" + oRepositorioBean.getId() + "\">Ver más</a>";
            } else {
                contenido = oRepositorioBean.getContenido();
            }
    %>
    <tr>
        <td><%=oRepositorioBean.getId()%></td>
        <td><%=oRepositorioBean.getTitulo()%></td>
        <td><%=contenido%></td>
        <td>
            <%=oRepositorioBean.getUsuario().getLogin()%> (<%=oRepositorioBean.getUsuario().getId()%>)
            <div class="btn-group">
                <a class="btn btn-mini" href="Controller?class=usuario&method=list&id=<%=oRepositorioBean.getId()%>&searchingfor=usuario&returnclass=repositorio&returnmethod=update&returnphase=2"><i class="icon-search"></i></a>                                        
            </div>
        </td>
        <td>
            <%=oRepositorioBean.getLenguaje().getNombre()%> (<%=oRepositorioBean.getLenguaje().getId()%>)
            <div class="btn-group">
                <a class="btn btn-mini" href="Controller?class=lenguaje&method=list&id=<%=oRepositorioBean.getId()%>&searchingfor=lenguaje&returnclass=repositorio&returnmethod=update&returnphase=2"><i class="icon-search"></i></a>                                        
            </div>
        </td>
        <td><%=oRepositorioBean.getDocumento().getTitulo()%> (<%=oRepositorioBean.getDocumento().getId()%>)
            <div class="btn-group">
                <a class="btn btn-mini" href="Controller?class=documento&method=list&id=<%=oRepositorioBean.getId()%>&searchingfor=documento&returnclass=repositorio&returnmethod=update&returnphase=2"><i class="icon-search"></i></a>                                        
            </div>
        </td>
        <td><%=formato.format(oRepositorioBean.getFecha())%></td>
        <td>
            <div class="btn-toolbar">
                <div class="btn-group">                    
                   <%
                        if (oContexto.getSearchingFor().equals("repositorio")) {
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?" + oContexto.getSerializedParamsExcept(new ArrayList<String>(Arrays.asList("class", "method", "phase", "id_repositorio", "id", "returnclass", "returnmethod", "returnphase", "searchingfor"))) + "class=" + oContexto.getClaseRetorno() + "&method=" + oContexto.getMetodoRetorno() + "&phase=" + oContexto.getFaseRetorno() + "&id_repositorio=" + oRepositorioBean.getId() + "&id=" + oContexto.getId() + "\"><i class=\"icon-ok\"></i></a>");
                        } else {
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?class=repositorio&method=view&id=" + oRepositorioBean.getId() + "\"><i class=\"icon-eye-open\"></i></a>");
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?class=repositorio&method=update&id=" + oRepositorioBean.getId() + "\"><i class=\"icon-pencil\"></i></a>");
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?class=repositorio&method=remove&id=" + oRepositorioBean.getId() + "\"><i class=\"icon-trash\"></i></a>");
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
