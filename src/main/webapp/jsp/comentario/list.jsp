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
<%@ page import="net.daw.bean.ComentBean"%>
<%
    Contexto oContexto = (Contexto) request.getAttribute("contexto");
    ArrayList<Object> alObjetoParametro = (ArrayList<Object>) oContexto.getParametro();
    ArrayList<ComentBean> alPagina = (ArrayList<ComentBean>) alObjetoParametro.get(0);
    Iterator<ComentBean> oIterador = alPagina.listIterator();
%>
<div class="row-fluid">
    <div class="span8">
        <h1>Listado de Comentarios</h1>
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
            <legend>Filtro de documentoario</legend> 
            <form class="navbar-form pull-right" action="Controller" method="post" id="ComentForm">
                <fieldset>                                               
                    <%=oContexto.getSerializedParamsExceptFilterFormFormat()%>       
                    <span>
                        <select id="filter" name="filter" width="80" style="width: 80px">
                            <option>id</option>
                            <option>titulo</option>
                            <option>contenido</option>
                            <option>fecha</option>
                            <option>id_usuario</option>
                            <option>id_documento</option>                            
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
        <th>título
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
        <th>id de documento
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_documento&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?<%=oContexto.getSerializedParamsExceptOrder()%>&order=id_documento&ordervalue=desc"><i class="icon-arrow-down"></i></a>                            
        </th>
    <!--   <th>id de usuario
            <a href="Controller?</%=oContexto.getSerializedParamsExceptOrder()%>&order=id_usuario&ordervalue=asc"><i class="icon-arrow-up"></i></a>
            <a href="Controller?</%=oContexto.getSerializedParamsExceptOrder()%>&order=id_usuario&ordervalue=desc"><i class="icon-arrow-down"></i></a>                            
        </th>-->
        
        <th>Operaciones</th>
    </tr>
    <%
        while (oIterador.hasNext()) {
            ComentBean oComentBean = oIterador.next();
    %>
    <tr>
        <td><%=oComentBean.getId()%></td>
        <td><%=oComentBean.getTitulo()%></td>
        <td><%=oComentBean.getContenido()%></td>
         <%
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
        %>
        <td><%=formatoFecha.format(oComentBean.getFecha())%></td>
        <td>
            <%=oComentBean.getId_documento().getTitulo()%>(<%=oComentBean.getId_documento().getId()%>)
            <div class="btn-group">
                <a class="btn btn-mini" href="Controller?class=documento&method=list&id=<%=oComentBean.getId()%>&searchingfor=documento&returnclass=coment&returnmethod=update&returnphase=2"><i class="icon-search"></i></a>                                        
            </div>          
        </td>
        <td>
            <%=oComentBean.getId_usuario().getLogin()%>(<%=oComentBean.getId_usuario().getId()%>)
            <div class="btn-group">
                <a class="btn btn-mini" href="Controller?class=usuario&method=list&id=<%=oComentBean.getId()%>&searchingfor=usuario&returnclass=coment&returnmethod=update&returnphase=2"><i class="icon-search"></i></a>                                        
            </div>          
        </td>
        <td>
            <div class="btn-toolbar">
                 <%
                        if (oContexto.getSearchingFor().equals("comentario")) {
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?" + oContexto.getSerializedParamsExcept(new ArrayList<String>(Arrays.asList("class", "method", "phase", "id_comentario", "id", "returnclass", "returnmethod", "returnphase", "searchingfor"))) + "class=" + oContexto.getClaseRetorno() + "&method=" + oContexto.getMetodoRetorno() + "&phase=" + oContexto.getFaseRetorno() + "&id_comentario=" + oComentBean.getId() + "&id=" + oContexto.getId() + "\"><i class=\"icon-ok\"></i></a>");
                        } else {
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?class=coment&method=view&id=" + oComentBean.getId() + "\"><i class=\"icon-eye-open\"></i></a>");
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?class=coment&method=update&id=" + oComentBean.getId() + "\"><i class=\"icon-pencil\"></i></a>");
                            out.print("<a class=\"btn btn-mini\" href=\"Controller?class=coment&method=remove&id=" + oComentBean.getId() + "\"><i class=\"icon-trash\"></i></a>");
                        }
                    %>                 
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

