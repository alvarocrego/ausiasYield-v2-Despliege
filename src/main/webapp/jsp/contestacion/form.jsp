<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.ContestacionBean"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    String id_usuario = "";
    String nombreUsuario = "";
    String id_pregunta = "";
    String descPregunta = "";
    String id_opcion = "";
    String descOpcion = "";

    ContestacionBean oContestacionBean = (ContestacionBean) oContexto.getParametro();
    id = oContestacionBean.getId();
    
    id_usuario = Integer.toString(oContestacionBean.getUsuario().getId());
    if ( oContestacionBean.getUsuario().getId() > 0 ) {
        nombreUsuario = oContestacionBean.getUsuario().getLogin();
    }
    
    id_pregunta = Integer.toString(oContestacionBean.getPregunta().getId());
    if( oContestacionBean.getPregunta().getId() > 0 ) {
        descPregunta = oContestacionBean.getPregunta().getDescripcion();
    }
    
    id_opcion = Integer.toString(oContestacionBean.getOpcion().getId());
    if( oContestacionBean.getOpcion().getId() > 0 ) {
        descOpcion = oContestacionBean.getOpcion().getDescripcion();
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
<h1><%=strTitulo%> de contestacion</h1>
<form class="form-horizontal" action="Controller" method="post" id="contestacionForm">
    <fieldset>
        <legend>Formulario de contestacion</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="contestacion" /> 
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
        <label class="control-label" for="id_pregunta">Pregunta </label>
        <div class="controls">                
            <input readonly="true" id="id_pregunta" class="input-mini"
                   name="id_pregunta" type="text" size="5" maxlength="5"
                   value="<%=id_pregunta%>" />  
            <input <%=strControlEnabled%> type="submit" name="searchingfor" value="pregunta" />
            <span class="alert alert-success"><%=descPregunta%></span>
        </div>
        </div>
        <div class="control-group">
        <label class="control-label" for="id_opcion">Opcion </label>
        <div class="controls">                
            <input readonly="true" id="id_opcion" class="input-mini"
                   name="id_opcion" type="text" size="5" maxlength="5"
                   value="<%=id_opcion%>" />  
            <input <%=strControlEnabled%> type="submit" name="searchingfor" value="opcion" />
            <span class="alert alert-success"><%=descOpcion%></span>
        </div>
        </div> 
        <div class="control-group">
            <div class="controls">
                <input type="submit" name="enviar" value="<%=strValueBoton%>" />
            </div>
        </div>   
    </fieldset>
</form>
