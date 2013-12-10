<%@page import="net.daw.bean.UsuarioBean"%>
<%@page import="java.util.Arrays"%>
<%@page import="net.daw.helper.FilterBean"%>
<%@ page import="net.daw.helper.Contexto"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="net.daw.bean.MetadocumentosBean"%>
<%
    Contexto oContexto = (Contexto) request.getAttribute("contexto");
    ArrayList<Object> alObjetoParametro = (ArrayList<Object>) oContexto.getParametro();
    ArrayList<MetadocumentosBean> alPagina = (ArrayList<MetadocumentosBean>) alObjetoParametro.get(0);
    Iterator<MetadocumentosBean> oIterador = alPagina.listIterator();
%>
<div class="row-fluid">
    <div class="span8">
        <h1>Listado de metadocumentos</h1>
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
            <legend>Filtro de metadocumentos</legend> 
            <form class="navbar-form pull-right" action="Controller" method="post" id="clienteForm">
                <fieldset>                                               
                    <%=oContexto.getSerializedParamsExceptFilterFormFormat()%>       
                    <span>
                        <select id="filter" name="filter" width="80" style="width: 80px">
                            <option>id</option>
                            <option>id_metadoucmento</option>
                            <option>id_documento</option>
                            <option>orden</option>                      
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
        <th>id_metadoucmento
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_metadoucmento&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_metadoucmento&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th>id_documento
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_documento&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_documento&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th>orden
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=orden&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=orden&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th>Operaciones</th>
    </tr>
    <%
        UsuarioBean oUsuarioBean = null;
        if (session.getAttribute("usuarioBean") != null) {
            oUsuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");

        }
        while (oIterador.hasNext()) {
            MetadocumentosBean oMetadocumentosBEAN = oIterador.next();
            System.out.println("Docuemnto " + oMetadocumentosBEAN.getDocumento().getUsuario().getId());
            System.out.println("Usuario " + oUsuarioBean.getId());
    %>
    <tr>
        <td><%=oMetadocumentosBEAN.getId()%></td>
        <%
            if (oUsuarioBean != null) {
                if (oUsuarioBean.getId() == oMetadocumentosBEAN.getDocumento().getUsuario().getId()) {


        %>
        <td>
            <%=oMetadocumentosBEAN.getMetadocumento().getTitulo()%> (<%=oMetadocumentosBEAN.getMetadocumento().getId()%>)
            <div class="btn-group">
                <a class="btn btn-mini" href="Controller?class=metadocumento&method=list&id=<%=oMetadocumentosBEAN.getId()%>&searchingfor=metadocumento&returnclass=metadocumentos&returnmethod=update&returnphase=2"><i class="icon-search"></i></a>                                        
            </div>
        </td>
        <%
        } else {
        %>
        <td><%=oMetadocumentosBEAN.getMetadocumento().getTitulo()%></td>
        <%
                }
            }
        %>

        <%
            if (oUsuarioBean != null) {
                if (oUsuarioBean.getId() == oMetadocumentosBEAN.getDocumento().getUsuario().getId()) {
        %>
        <td>
            <%=oMetadocumentosBEAN.getDocumento().getTitulo()%> (<%=oMetadocumentosBEAN.getDocumento().getId()%>)
            <div class="btn-group">
                <a class="btn btn-mini" href="Controller?class=documento&method=list&id=<%=oMetadocumentosBEAN.getId()%>&searchingfor=metadocumento&returnclass=metadocumentos&returnmethod=update&returnphase=2"><i class="icon-search"></i></a>                                        
            </div>
        </td>
        <%
        } else {
        %>
        <td><%=oMetadocumentosBEAN.getDocumento().getTitulo()%></td>
        <%
                }
            }
        %>
        <td><%=oMetadocumentosBEAN.getOrden()%></td>
        <td>

   
                <%
                    if (oUsuarioBean != null) {
                        if (oUsuarioBean.getId() == oMetadocumentosBEAN.getDocumento().getUsuario().getId()) {
                            if (oContexto.getSearchingFor().equals("metadocumentos")) {
                                out.print("<div class=\"btn-toolbar\">");
                                out.print("<div class=\"btn-group\">");
                                out.print("<a class=\"btn btn-mini\" href=\"Controller?" + oContexto.getSerializedParamsExcept(new ArrayList<String>(Arrays.asList("class", "method", "phase", "id_metadocumentos", "id", "returnclass", "returnmethod", "returnphase", "searchingfor"))) + "class=" + oContexto.getClaseRetorno() + "&method=" + oContexto.getMetodoRetorno() + "&phase=" + oContexto.getFaseRetorno() + "&id_documento=" + oMetadocumentosBEAN.getId() + "&id=" + oContexto.getId() + "\"><i class=\"icon-ok\"></i></a>");
                                out.print("</div>");
                                out.print("</div>");
                            } else {
                                out.print("<div class=\"btn-toolbar\">");
                                out.print("<div class=\"btn-group\">");
                                out.print("<a class=\"btn btn-mini\" href=\"Controller?class=metadocumentos&method=view&id=" + oMetadocumentosBEAN.getId() + "\"><i class=\"icon-eye-open\"></i></a>");
                                out.print("<a class=\"btn btn-mini\" href=\"Controller?class=metadocumentos&method=update&id=" + oMetadocumentosBEAN.getId() + "\"><i class=\"icon-pencil\"></i></a>");
                                out.print("<a class=\"btn btn-mini\" href=\"Controller?class=metadocumentos&method=remove&id=" + oMetadocumentosBEAN.getId() + "\"><i class=\"icon-trash\"></i></a>");
                                out.print("</div>");
                                out.print("</div>");
                            }
                        } else {
                            if (oContexto.getSearchingFor().equals("metadocumentos")) {
                                out.print("<div class=\"btn-toolbar\">");
                                out.print("<div class=\"btn-group\">");
                                out.print("<a class=\"btn btn-mini\" href=\"Controller?" + oContexto.getSerializedParamsExcept(new ArrayList<String>(Arrays.asList("class", "method", "phase", "id_metadocumentos", "id", "returnclass", "returnmethod", "returnphase", "searchingfor"))) + "class=" + oContexto.getClaseRetorno() + "&method=" + oContexto.getMetodoRetorno() + "&phase=" + oContexto.getFaseRetorno() + "&id_documento=" + oMetadocumentosBEAN.getId() + "&id=" + oContexto.getId() + "\"><i class=\"icon-ok\"></i></a>");
                                out.print("</div>");
                                out.print("</div>");
                            } else {
                                out.print("<p class='text-error'>No tienes permisos</p>");
                            }
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
