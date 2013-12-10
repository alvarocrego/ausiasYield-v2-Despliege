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
<%@page import="net.daw.bean.ComentBean"%>
<%@page import="net.daw.helper.Contexto"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    String id_usuario = "";
    String titulo = "";
    String contenido = "";
    String id_documento = "";
    String fecha = "";
    String titDocumento = "";
    String nomUsuario = "";


    ComentBean oComentBean = (ComentBean) oContexto.getParametro();
    id = oComentBean.getId();
    titulo = oComentBean.getTitulo();
    contenido = oComentBean.getContenido();

    id_usuario = Integer.toString(oComentBean.getId_usuario().getId());
    if (oComentBean.getId_usuario().getId() > 0) {
        nomUsuario = oComentBean.getId_usuario().getLogin();
    }
    id_documento = Integer.toString(oComentBean.getId_documento().getId());
    if (oComentBean.getId_documento().getId() > 0) {
        titDocumento = oComentBean.getId_documento().getTitulo();
    }

    fecha = new SimpleDateFormat("yyyy-MM-dd").format(oComentBean.getFecha());

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
<h1><%=strTitulo%> de Comentario</h1>
<form class="form-horizontal" action="Controller" method="post" id="comentForm">
    <fieldset>
        <legend>Formulario de Comentario</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="coment" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <div class="control-group">
            <label class="control-label" for="titulo">Título: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="titulo" name="titulo" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=titulo%>" /><br />
            </div>
        </div>
         <div class="control-group">
            <label class="control-label" for="contenido">Contenido: </label>
            <div class="controls">
                <textarea <%=strControlEnabled%>  class="field span6" id="contenido" name="contenido" type="text" rows="10" ><%=contenido%></textarea><br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="fecha">Fecha: </label> 
            <div class="controls">
                <input <%=strControlEnabled%>  id="fecha" name="fecha" type="date" size="30" maxlength="50" value="<%=fecha%>" /></div> 
        </div>

        <div class="control-group">
            <label class="control-label" for="id_usuario">Usuario: </label> 
            <div class="controls">                
                <input readonly="true" id="id_usuario" class="input-mini"
                       name="id_usuario" type="text" size="5" maxlength="5"
                       value="<%=id_usuario%>" />  
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="usuario" />
                <span class="alert alert-success"><%=nomUsuario%></span>
            </div>
        </div>  

        <div class="control-group">
            <label class="control-label" for="id_usuario">Documento: </label> 
            <div class="controls">                
                <input readonly="true" id="id_documento" class="input-mini"
                       name="id_documento" type="text" size="5" maxlength="5"
                       value="<%=id_documento%>" />  
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="documento" />
                <span class="alert alert-success"><%=titDocumento%></span>
            </div>
        </div>  

        <div>
            <input type="submit" name="enviar" value="<%=strValueBoton%>" />
        </div>
    </fieldset>
</form>
