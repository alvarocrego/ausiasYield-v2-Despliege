<%@page import="net.daw.helper.TextParser"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="net.daw.bean.RepositorioBean"%>
<%@page import="net.daw.helper.Contexto"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    String titulo = "";
    String contenido = "";
    String usuario = "";
    String nombreUsuario = "";
    String lenguaje = "";
    String nombreLengueje = "";
    String documento = "";
    String tituloDocumento = "";
    String fecha = "";


    RepositorioBean oRepositorioBean = (RepositorioBean) oContexto.getParametro();
    titulo = oRepositorioBean.getTitulo();
    contenido=TextParser.textDecode(oRepositorioBean.getContenido());
    
    
    
    id = oRepositorioBean.getId();
    usuario = Integer.toString(oRepositorioBean.getUsuario().getId());
    if (oRepositorioBean.getUsuario().getId() > 0) {
        nombreUsuario = oRepositorioBean.getUsuario().getLogin();
    }
    lenguaje = Integer.toString(oRepositorioBean.getLenguaje().getId());
    if (oRepositorioBean.getLenguaje().getId() > 0) {
        nombreLengueje = oRepositorioBean.getLenguaje().getNombre();
    }
    documento = Integer.toString(oRepositorioBean.getDocumento().getId());
    if (oRepositorioBean.getDocumento().getId() > 0) {
        tituloDocumento = oRepositorioBean.getDocumento().getTitulo();
    }
    fecha = new SimpleDateFormat("yyyy-MM-dd").format(oRepositorioBean.getFecha());

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
<h1><%=strTitulo%> de repositorio</h1>
<form class="form-horizontal" action="Controller" method="post" id="repositorioForm">
    <legend>Formulario de Repositorio</legend>
    <input type="hidden" name="id" value="<%=id%>" /> 
    <input type="hidden" name="class" value="repositorio" /> 
    <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
    <input type="hidden" name="phase" value="2" />
    <div class="control-group">
        <label class="control-label" for="titulo">Titulo: </label> 
        <div class="controls">
            <input <%=strControlEnabled%> id="titulo" class="input_resize" name="titulo" type="text" size="30" maxlength="50" value="<%=titulo%>" /><br />

        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="contenido">Contenido: </label>
        <div class="controls">
        <textarea <%=strControlEnabled%> id="contenido" class="input_resize" name="contenido" type="text" size="30" ><%=contenido%></textarea><br />
    
        </div>
    </div>
     <div class="control-group">
        <label class="control-label" for="usuario">Id_usuario: </label>
        <div class="controls">  
            <input readonly="true" id="usuario" class="input-mini"
                   name="id_usuario" type="text" size="5" maxlength="5"
                   value="<%=usuario%>" />
            <input <%=strControlEnabled%> type="submit" name="searchingfor" value="usuario" />
            <span class="alert alert-success"><%=nombreUsuario%></span>
        </div>
     </div>
    <div class="control-group">
        <label class="control-label" for="lenguaje">Id_lenguaje: </label>
        <div class="controls">  
            <input readonly="true" id="lenguaje" class="input-mini"
                   name="id_lenguaje" type="text" size="5" maxlength="5"
                   value="<%=lenguaje%>" />
            <input <%=strControlEnabled%> type="submit" name="searchingfor" value="lenguaje" />
            <span class="alert alert-success"><%=nombreLengueje%></span>
        </div>
    </div>
        <div class="control-group">
        <label class="control-label" for="documento">Id_documento: </label>
        <div class="controls">  
            <input readonly="true" id="documento" class="input-mini"
                   name="id_documento" type="text" size="5" maxlength="5"
                   value="<%=documento%>" />
            <input <%=strControlEnabled%> type="submit" name="searchingfor" value="documento" />
            <span class="alert alert-success"><%=tituloDocumento%></span>
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
