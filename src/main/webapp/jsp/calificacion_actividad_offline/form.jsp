<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.CalificacionActividadOfflineBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    String id_usuario = "";
    String fecha = "";
    String nombreUsuario = "";
    String id_actividadoffline = "";  /* Una vez este implementada la tabla actividad_offline cambiar esto. */
    String actividadOffline = ""; /* Una vez este implementada la tabla actividad_offline cambiar esto. */

    CalificacionActividadOfflineBean oCalificacionActividadOfflineBean = (CalificacionActividadOfflineBean) oContexto.getParametro();
    id = oCalificacionActividadOfflineBean.getId();
    
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    if (oCalificacionActividadOfflineBean.getFecha() != null) {
        fecha = formatoFecha.format(oCalificacionActividadOfflineBean.getFecha());
    } else {
        fecha = formatoFecha.format(new Date());
    }
    
    id_usuario = Integer.toString(oCalificacionActividadOfflineBean.getUsuario().getId());
    if ( oCalificacionActividadOfflineBean.getUsuario().getId() > 0 ) {
        nombreUsuario = oCalificacionActividadOfflineBean.getUsuario().getLogin();
    }
    
    id_actividadoffline = Integer.toString(oCalificacionActividadOfflineBean.getActividad_offline().getId()); /* Una vez este implementada la tabla actividad_offline cambiar esto. */
    if( oCalificacionActividadOfflineBean.getActividad_offline().getId() > 0 ) { /* Una vez este implementada la tabla actividad_offline cambiar esto. */
        actividadOffline = oCalificacionActividadOfflineBean.getActividad_offline().getEnunciado(); /* Una vez este implementada la tabla actividad_offline cambiar esto. */
    }
    

    if (oContexto.getMetodo().equals("view")) {
        strTitulo = "Vista";
        strControlEnabled = "disabled=\"true\"";
        strValueBoton = "Cerrar";
    }
    if (oContexto.getMetodo().equals("update")) {
        strTitulo = "Edici?n";
        strValueBoton = "Modificar";
    }
    if (oContexto.getMetodo().equals("new")) {
        strTitulo = "Alta";
        strValueBoton = "Crear";
    }
%>
<h1><%=strTitulo%> de calificacion</h1>
<form class="form-horizontal" action="Controller" method="post" id="CalificacionActividadOfflineForm">
    <fieldset>
        <legend>Formulario de Calificacion Actividad Offline</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="CalificacionActividadOffline" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <div class="control-group">
        <label class="control-label" for="id_usuario">Usuario </label> 
        <div class="controls">                
            <input readonly="true" id="id_usuario" class="input-mini"
                   name="id_usuario" type="text" size="5" maxlength="5"
                   value="<%=id_usuario%>" />  
            <input <%=strControlEnabled%> type="submit" name="searchingfor" value="usuario" />
            <span class="alert alert-success"><%=nombreUsuario%></span>
        </div>
        </div>
        <div class="control-group">
        <label class="control-label" for="id_actividadoffline">Actividad Offline </label>
        <div class="controls">                
            <input readonly="true" id="id_actividadoffline" class="input-mini"
                   name="id_actividadoffline" type="text" size="5" maxlength="5"
                   value="<%=id_actividadoffline%>" />  
            <input <%=strControlEnabled%> type="submit" name="searchingfor" value="actividadoffline" />
            <span class="alert alert-success"><%=actividadOffline%></span>
        </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="fecha">Fecha: </label>
            <div class="controls">
                <input <%=strControlEnabled%> id="fecha" name="fecha" type="date" size="30" maxlength="50" value="<%=fecha%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <input type="submit" name="enviar" value="<%=strValueBoton%>" />
            </div>
        </div>   
    </fieldset>
</form>
