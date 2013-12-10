<%@page import="net.daw.helper.Enum.TipoUsuario"%>
<%@page import="net.daw.bean.UsuarioBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Arrays"%>
<%@page import="net.daw.helper.FilterBean"%>
<%@ page import="net.daw.helper.Contexto"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="net.daw.bean.DocumentoBean"%>
<%
    Contexto oContexto = (Contexto) request.getAttribute("contexto");
    ArrayList<Object> alObjetoParametro = (ArrayList<Object>) oContexto.getParametro();
    ArrayList<DocumentoBean> alPagina = (ArrayList<DocumentoBean>) alObjetoParametro.get(0);
    Iterator<DocumentoBean> oIterador = alPagina.listIterator();
%>
<div class="row-fluid">
    <div class="span8">
        <h1>Listado de documentos</h1>
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
            String strFiltro = "";
            if (oContexto.getAlFilter() != null) {
                strFiltro += "<p>Listado filtrado: ";
                ArrayList<FilterBean> alFilter = oContexto.getAlFilter();
                Iterator iterator = alFilter.iterator();
                while (iterator.hasNext()) {
                    FilterBean oFilterBean = (FilterBean) iterator.next();
                    if (oFilterBean.getFilterOrigin() != "system") {
                        strFiltro += "(" + oFilterBean.getFilter() + " " + oFilterBean.getFilterOperator() + " " + oFilterBean.getFilterValue() + ") ";
                    }
                }
                if (!strFiltro.equals("<p>Listado filtrado: ")) {
                    strFiltro += "<a href=\"Controller?" + oContexto.getSerializedParamsExceptFilter() + "\">(Quitar filtro)</a></p>";
                } else {
                    strFiltro = "<p>Sin filtrar</p>";
                }
            } else {
                strFiltro = "<p>Sin filtrar</p>";
            }
            out.print(strFiltro);
            
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
            <legend>Filtro de documento</legend> 
            <form class="navbar-form pull-right" action="Controller" method="post" id="clienteForm">
                <fieldset>                                               
                    <%=oContexto.getSerializedParamsExceptFilterFormFormat()%>       
                    <span>
                        <select id="filter" name="filter" width="80" style="width: 80px">
                            <option>id</option>
                            <option>titulo</option>
                            <option>contenido</option>
                            <option>fecha</option>
                            <option>nota</option>
                            <option>id_usuario</option>
                            <option>etiquetas</option>                            
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
        <th>titulo
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=titulo&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=titulo&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th>contenido
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=contenido&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=contenido&ordervalue=desc"><i class="icon-arrow-down"></i></a>        
        </th>
        <th>fecha
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=fecha&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=fecha&ordervalue=desc"><i class="icon-arrow-down"></i></a>                
        </th>
        <th>nota
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=nota&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=nota&ordervalue=desc"><i class="icon-arrow-down"></i></a>                
        </th>
        <th>id_usuario
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_usuario&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_usuario&ordervalue=desc"><i class="icon-arrow-down"></i></a>                
        </th>
        <th>etiquetas
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=etiquetas&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=etiquetas&ordervalue=desc"><i class="icon-arrow-down"></i></a>                            
        </th>
        <th>Operaciones</th>
    </tr>
    <%
        while (oIterador.hasNext()) {
            DocumentoBean oDocumentoBEAN = oIterador.next();
            String contenido = "";
            if (oDocumentoBEAN.getContenido().length() >= 50) {
                contenido = oDocumentoBEAN.getContenido().substring(0, 50) + "... <a href=\"Controller?class=documento&method=view&id=" + oDocumentoBEAN.getId() + "\">Ver más</a>";
            } else {
                contenido = oDocumentoBEAN.getContenido();
            }
            String classNota = "";
            if (oDocumentoBEAN.getNota() == 5) {
                classNota = "class=\"warning\"";
            } else if (oDocumentoBEAN.getNota() <= 4) {
                classNota = "class=\"error\"";
            } else if (oDocumentoBEAN.getNota() >= 6) {
                classNota = "class=\"success\"";
            }
    %>
    <tr <%=classNota%>>
        <td><%=oDocumentoBEAN.getId()%></td>
        <td><%=oDocumentoBEAN.getTitulo()%></td>
        <td><%=contenido%></td>
        <%
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
        %>
        <td><%=formatoFecha.format(oDocumentoBEAN.getFecha())%></td>
        <td><%=oDocumentoBEAN.getNota()%></td>

        <%
            String usuario = oDocumentoBEAN.getUsuario().getLogin();
            if (usuario.isEmpty() != true) {
                usuario = usuario.substring(0, 1).toUpperCase() + usuario.substring(1, usuario.length());
            }
            if (session.getAttribute("usuarioBean") != null) {
                UsuarioBean oUsuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");
                System.out.println(oUsuarioBean.getTipoUsuario());
                if (oUsuarioBean.getTipoUsuario().equals(TipoUsuario.Profesor)) {
        %>
        <td>    
            <%=usuario%> (<%=oDocumentoBEAN.getUsuario().getId()%>)
            <div class="btn-group">
                <a class="btn btn-mini" href="Controller?class=usuario&method=list&id=<%=oDocumentoBEAN.getId()%>&searchingfor=usuario&returnclass=documento&returnmethod=update&returnphase=2"><i class="icon-search"></i></a>                                        
            </div>
        </td>
        <%
        } else {
        %>
        <td>
            <%=usuario%>
        </td>
        <%
                }
            }
        %>

        <td><%=oDocumentoBEAN.getEtiquetas()%></td>
        <td>
            <div class="btn-toolbar">
                <div class="btn-group">
                    <%
                        if (oContexto.getSearchingFor().equals("documento")) {
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?" + oContexto.getSerializedParamsExcept(new ArrayList<String>(Arrays.asList("class", "method", "phase", "id_documento", "id", "returnclass", "returnmethod", "returnphase", "searchingfor"))) + "class=" + oContexto.getClaseRetorno() + "&method=" + oContexto.getMetodoRetorno() + "&phase=" + oContexto.getFaseRetorno() + "&id_documento=" + oDocumentoBEAN.getId() + "&id=" + oContexto.getId() + "\"><i class=\"icon-ok\"></i></a>");
                        } else {
                            if (oContexto.getSearchingFor().equals("documento1")) {
                                out.print("<a class=\"btn btn-mini\" href=\"Controller?" + oContexto.getSerializedParamsExcept(new ArrayList<String>(Arrays.asList("class", "method", "phase", "id_documento1", "id", "returnclass", "returnmethod", "returnphase", "searchingfor"))) + "class=" + oContexto.getClaseRetorno() + "&method=" + oContexto.getMetodoRetorno() + "&phase=" + oContexto.getFaseRetorno() + "&id_documento1=" + oDocumentoBEAN.getId() + "&id=" + oContexto.getId() + "\"><i class=\"icon-ok\"></i></a>");
                            } else {
                                if (oContexto.getSearchingFor().equals("documento2")) {
                                    out.print("<a class=\"btn btn-mini\" href=\"Controller?" + oContexto.getSerializedParamsExcept(new ArrayList<String>(Arrays.asList("class", "method", "phase", "id_documento2", "id", "returnclass", "returnmethod", "returnphase", "searchingfor"))) + "class=" + oContexto.getClaseRetorno() + "&method=" + oContexto.getMetodoRetorno() + "&phase=" + oContexto.getFaseRetorno() + "&id_documento2=" + oDocumentoBEAN.getId() + "&id=" + oContexto.getId() + "\"><i class=\"icon-ok\"></i></a>");
                                } else {
                                    out.print("<a class=\"btn btn-mini\" href=\"Controller?class=documento&method=view&id=" + oDocumentoBEAN.getId() + "\"><i class=\"icon-eye-open\"></i></a>");
                                    out.print("<a class=\"btn btn-mini\" href=\"Controller?class=documento&method=update&id=" + oDocumentoBEAN.getId() + "\"><i class=\"icon-pencil\"></i></a>");
                                    out.print("<a class=\"btn btn-mini\" href=\"Controller?class=documento&method=remove&id=" + oDocumentoBEAN.getId() + "\"><i class=\"icon-trash\"></i></a>");
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
