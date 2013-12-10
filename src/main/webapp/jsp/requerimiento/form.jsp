<%-- 
    Document   : form
    Created on : 13-nov-2013, 11:28:59
    Author     : Jordi Eslava Barrera
--%>


<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.mysql.jdbc.Statement"%>
<%@page import="net.daw.helper.Enum.Connection"%>
<%@page import="net.daw.data.Mysql"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="net.daw.bean.RequerimientoBean"%>
<%@page import="net.daw.helper.Contexto"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    String enunciado = "";
    String fechaalta = "";

    RequerimientoBean oRequerimientoBean = (RequerimientoBean) oContexto.getParametro();
    id = oRequerimientoBean.getId();
    enunciado = oRequerimientoBean.getEnunciado();
    fechaalta = new SimpleDateFormat("yyyy-MM-dd").format(oRequerimientoBean.getFecha());

    if (oContexto.getMetodo().equals("update")) {
        strTitulo = "Edición";
        strValueBoton = "Modificar";
    }
    if (oContexto.getMetodo().equals("new")) {
        strTitulo = "Alta";
        strValueBoton = "Crear";
    }

    if (oContexto.getMetodo().equals("view")) {
        strTitulo = "Vista";
        strControlEnabled = "disabled=\"true\"";
        strValueBoton = "Cerrar";
    } else {
        strValueBoton = "Guardar";
    }
%>
<h1><%=strTitulo%> de Requerimiento</h1>
<form class="semantic" action="Controller" method="post" id="RequerimientoForm">
    <fieldset>
        <legend>Formulario de Requerimiento</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="requerimiento" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <div class="control-group">
            <label for="enunciado">Enunciado: </label> 
            <input <%=strControlEnabled%> id="enunciado" name="enunciado" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=enunciado%>" /><br />
        </div>
        <div class="control-group">
            <label class="control-label" for="fechaalta">Fecha: </label> 
            <div class="controls">
                <input <%=strControlEnabled%>  id="fechaalta" name="fechaalta" type="date" size="30" maxlength="50" value="<%=fechaalta%>" /></div> 
        </div>
        <div>
            <input type="submit" name="enviar" value="<%=strValueBoton%>" />
        </div>
    </fieldset>
</form>
