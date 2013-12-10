<%@page import="java.text.SimpleDateFormat"%>
<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.EntradaBean"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";

    Integer id = 0;
    String titulo = "";
    String contenido = "";
    String id_hilo = "";
    String id_usuario = "";
    String fecha = "";
    String descHilo = "";
    String descUsuario = "";

    EntradaBean oEntradaBean = (EntradaBean) oContexto.getParametro();
    id = oEntradaBean.getId();
    id_hilo = Integer.toString(oEntradaBean.getHilo().getId());
    if (!(oEntradaBean.getHilo().getNombre().equals(""))) {
        descHilo = oEntradaBean.getHilo().getNombre();
    }
    id_usuario = Integer.toString(oEntradaBean.getUsuario().getId());
    if (oEntradaBean.getUsuario().getId() > 0) {
        descUsuario = oEntradaBean.getUsuario().getLogin();
    }
    titulo = oEntradaBean.getTitulo();
    contenido = oEntradaBean.getContenido();
    

    if (oContexto.getMetodo().equals("view")) {
        strTitulo = "Vista";
        strControlEnabled = "disabled=\"true\"";
        strValueBoton = "Cerrar";
        fecha = new SimpleDateFormat("yyyy-MM-dd").format(oEntradaBean.getFecha());
    }
    if (oContexto.getMetodo().equals("update")) {
        strTitulo = "Edición";
        strValueBoton = "Modificar";
        fecha = new SimpleDateFormat("yyyy-MM-dd").format(oEntradaBean.getFecha());
    }
    if (oContexto.getMetodo().equals("new")) {
        strTitulo = "Alta";
        strValueBoton = "Crear";
    }
%>
<h1><%=strTitulo%> de Entrada</h1>
<form class="form-horizontal" action="Controller" method="post" id="entradaForm">
    <legend>Formulario de Entrada</legend>
    <input type="hidden" name="id" value="<%=id%>" /> 
    <input type="hidden" name="class" value="entrada" /> 
    <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
    <input type="hidden" name="phase" value="2" />
    <div class="control-group">
        <label class="control-label" for="id_hilo">Id Hilo: </label> 
        <div class="controls">                
            <input readonly="true" id="id_hilo" class="input-mini"
                   name="id_hilo" type="text" size="5" maxlength="5"
                   value="<%=id_hilo%>" />  
            <input <%=strControlEnabled%> type="submit" name="searchingfor" value="hilo" />
            <span class="alert alert-success"><%=descHilo%></span>
        </div>
    </div>             

    <div class="control-group">
        <label class="control-label" for="id_usuario">Id Usuario: </label> 
        <div class="controls">                
            <input readonly="true" id="id_usuario" class="input-mini"
                   name="id_usuario" type="text" size="5" maxlength="5"
                   value="<%=id_usuario%>" />  
            <input <%=strControlEnabled%> type="submit" name="searchingfor" value="usuario" />
            <span class="alert alert-success"><%=descUsuario%></span>
        </div>
    </div>             
    <div class="control-group">
        <label class="control-label" for="titulo">Título: </label> 
        <div class="controls">
            <input <%=strControlEnabled%>  id="titulo"
                                           name="titulo" type="text" size="30" maxlength="50"
                                           value="<%=titulo%>"  /> 
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="contenido">Contenido: </label> 
        <div class="controls">
            <input <%=strControlEnabled%>  id="contenido"
                                           name="contenido" type="text" size="30" maxlength="50"
                                           value="<%=contenido%>"  /> 
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="fecha">Fecha: </label> 
        <div class="controls">
            <input <%=strControlEnabled%>  id="fecha"
                                           name="fecha" type="date" size="30" maxlength="50"
                                           value="<%=fecha%>" /> 
        </div> 
    </div>
    <div class="control-group">
        <div class="controls">
            <input type="submit" name="enviar" value="<%=strValueBoton%>" />
        </div>
    </div>
</form>