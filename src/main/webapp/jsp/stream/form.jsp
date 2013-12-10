<%-- 
    Document   : form
    Created on : 12-nov-2013, 11:50:35
    Author     : al037213
--%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.StreamBean"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    String contenido = "";
    String id_usuario = "";
    String fecha = "";
    String descUsuario = "";
    


    StreamBean oStreamBean = (StreamBean) oContexto.getParametro();
    id = oStreamBean.getId();
    contenido = oStreamBean.getContenido();
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    if (oStreamBean.getFecha() != null) {
        fecha = formatoFecha.format(oStreamBean.getFecha());
    } else {
        fecha = formatoFecha.format(new Date());
    }
    id_usuario = String.valueOf(oStreamBean.getUsuario().getId());
    if (oStreamBean.getUsuario().getId() > 0) {
        descUsuario = oStreamBean.getUsuario().getLogin();
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
<h1><%=strTitulo%> de stream</h1>
<form class="form-horizontal" action="Controller" method="post" id="streamForm">
    <fieldset>
        <legend>Formulario de stream</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="stream" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
       
        <div class="control-group">
            <label class="control-label" for="id_usuario">Usuario </label> 
            <div class="controls">                
                <input readonly="true" id="id_usuario" class="input-mini"
                       name="id_usuario" type="text" size="5" maxlength="5"
                       value="<%=id_usuario%>" />  
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="usuario" />

                <span class="alert alert-success"><%=descUsuario%></span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="fecha">Fecha: </label>
            <div class="controls">
                <input <%=strControlEnabled%> id="fecha" name="fecha" type="date" size="30" maxlength="50" value="<%=fecha%>" /><br />
            </div>
        </div> 
         <div class="control-group">
            <label class="control-label" for="contenido">Descripcion: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="contenido" name="contenido" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=contenido%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <input type="submit" name="enviar" value="<%=strValueBoton%>" />
            </div>
        </div>   
    </fieldset>
</form>