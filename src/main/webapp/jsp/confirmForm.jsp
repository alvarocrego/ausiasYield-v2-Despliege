<%@page import="net.daw.helper.Contexto"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");%>
<span class="label label-warning">¡Cuidado!</span>
<h1>Confirme, por favor.</h1>
<p>
    ¿Está vd. seguro de que desea <%=oContexto.getParametro()%>?
</p>
<a class="btn btn-danger" href="Controller?class=<%=oContexto.getClase()%>&method=<%=oContexto.getMetodo()%>&phase=<%=Integer.toString(Integer.parseInt(oContexto.getFase()) + 1)%>&id=<%=oContexto.getId()%>">Sí</a>
<a class="btn btn-primary" href="Controller?class=<%=oContexto.getClase()%>&method=list"> No</a>