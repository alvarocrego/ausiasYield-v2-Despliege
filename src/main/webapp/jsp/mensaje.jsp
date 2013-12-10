<%@page import="net.daw.helper.Contexto"%>
<% Contexto c= (Contexto) request.getAttribute("contexto"); %>
<div id='mensaje'>
<h1>Mensaje de información</h1>
<h5><%=c.getParametro() %></h5>
</div>