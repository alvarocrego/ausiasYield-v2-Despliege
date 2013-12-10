
<%@page import="java.util.Calendar"%>
<%@page import="net.daw.bean.EstadoBean"%>
<%@page import="net.daw.helper.Contexto"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    String nombre = "";
   
    if (oContexto.getMetodo().equals("update") || oContexto.getMetodo().equals("view")) {
        EstadoBean oEstadoBean = (EstadoBean) oContexto.getParametro();
        id = oEstadoBean.getId();
        nombre = oEstadoBean.getNombre();
       
    }
    if (oContexto.getMetodo().equals("view")) {
        strTitulo = "Vista";
        strControlEnabled = "disabled=\"true\"";
        strValueBoton = "Cerrar";
    }
    if (oContexto.getMetodo().equals("update")) {
        strTitulo = "Edición";
        strValueBoton = "Modificar";
    }
    if (oContexto.getMetodo().equals("new")) {
        strTitulo = "Alta";
        strValueBoton = "Crear";
    }
%>
<h1><%=strTitulo%> de estado</h1>
<form class="semantic" action="Controller" method="post" id="estadoForm">
    <fieldset>
        <legend>Formulario de Estado</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="estado" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <div>
            <label for="nombre">Nombre: </label> 
            <input <%=strControlEnabled%> id="nombre" name="nombre" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=nombre%>" /><br />
        </div>
        <div>
            <input type="submit" name="enviar" value="<%=strValueBoton%>" />
        </div>
    </fieldset>
</form>
