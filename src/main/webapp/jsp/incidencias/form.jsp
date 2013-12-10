<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.mysql.jdbc.Statement"%>
<%@page import="net.daw.helper.Enum.Connection"%>
<%@page import="net.daw.data.Mysql"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="net.daw.bean.IncidenciasBean"%>
<%@page import="net.daw.helper.Contexto"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    String id_usuario = "";
    String resumen = "";
    String cambios = "";
    String id_estado = "";
    String id_repositorio = "";
    String fechaAlta = "";
    String fechaResolucion = "";
    
    String descUsuario ="";
    String descEstado ="";
    String descRepositorio ="";


    IncidenciasBean oIncidenciasBean = (IncidenciasBean) oContexto.getParametro();
    id = oIncidenciasBean.getId();
    resumen = oIncidenciasBean.getResumen();
    cambios = oIncidenciasBean.getCambios();
    
    id_usuario = Integer.toString(oIncidenciasBean.getUsuario().getId());
    if (oIncidenciasBean.getUsuario().getId() > 0) {
        descUsuario = oIncidenciasBean.getUsuario().getLogin();
    }
    id_estado = Integer.toString(oIncidenciasBean.getEstado().getId());
    if (oIncidenciasBean.getEstado().getId() > 0) {
        descEstado = oIncidenciasBean.getEstado().getNombre();
    }
    id_repositorio = Integer.toString(oIncidenciasBean.getRepositorio().getId());
     if (oIncidenciasBean.getRepositorio().getId() > 0) {
        descRepositorio = oIncidenciasBean.getRepositorio().getTitulo();
    }
     fechaAlta = new SimpleDateFormat("yyyy-MM-dd").format(oIncidenciasBean.getFechaAlta());
     fechaResolucion = new SimpleDateFormat("yyyy-MM-dd").format(oIncidenciasBean.getFechaResolucion());



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
<h1><%=strTitulo%> Formulario de Incidencias</h1>
<form class="form-horizontal" action="Controller" method="post" id="comentForm">
    <fieldset>
        <legend>Formulario de Incidencias</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="incidencias" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <div class="control-group">
            <label class="control-label" for="resumen">Resumen: </label> 
            <div class="controls"> 
            <input <%=strControlEnabled%> id="resumen" name="resumen" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=resumen%>" /><br />
        </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="cambios">Cambios: </label>
            <div class="controls">
            <textarea <%=strControlEnabled%> id="cambios" name="cambios" type="text" size="30" maxlength="50" ><%=cambios%></textarea><br />
        </div>
        </div>

        
        <div class="control-group">
            <label class="control-label" for="id_usuario">Usuario: </label> 
            <div class="controls">                
                <input readonly="true" id="id_usuario" class="input-mini" name="id_usuario" type="text" size="5" maxlength="5" value="<%=id_usuario%>" />  
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="usuario" />
                <span class="alert alert-success"><%=descUsuario%></span>
            </div>
        </div> 
        <div class="control-group">
            <label class="control-label" for="id_estado">Estado: </label> 
            <div class="controls">                
                <input readonly="true" id="id_estado" class="input-mini"
                       name="id_estado" type="text" size="5" maxlength="5" value="<%=id_estado%>" />  
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="estado" />
                <span class="alert alert-success"><%=descEstado%></span>
            </div>
        </div> 
            
             <div class="control-group">
            <label class="control-label" for="id_repositorio">Repositorio: </label> 
            <div class="controls">                
                <input readonly="true" id="id_repositorio" class="input-mini"
                       name="id_repositorio" type="text" size="5" maxlength="5" value="<%=id_repositorio%>" />  
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="repositorio" />
                <span class="alert alert-success"><%=descRepositorio%></span>
            </div>
        </div> 
            
            <div  class="control-group">
            <label class="control-label" for="fechaAlta">Fecha Alta: </label> 
            <div class="controls">
            <input <%=strControlEnabled%> id="fechaAlta" name="fechaAlta" type="date" size="30" maxlength="50" value="<%=fechaAlta%>" /> <br />
        </div>
         </div>
        <div class="control-group">
            <label class="control-label" for="fechaResolucion">Fecha Resolución: </label> 
            <div class="controls">
            <input <%=strControlEnabled%> id="fechaResolucion" name="fechaResolucion" type="date" size="30" maxlength="50" value="<%=fechaResolucion%>" /> <br />
        </div> 
        </div>         
        <div class="control-group">
            <div class="controls">
            <input type="submit" name="enviar" value="<%=strValueBoton%>" />
        </div>
        </div>
    </fieldset>
</form>
