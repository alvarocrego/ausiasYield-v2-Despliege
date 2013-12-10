<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.PreguntaBean"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";

    Integer id = 0;
    String descripcion = "";
    String id_cuestionario = "";
    String descCuestionario = "";

    PreguntaBean oPreguntaBean = (PreguntaBean) oContexto.getParametro();
    id = oPreguntaBean.getId();
    descripcion = oPreguntaBean.getDescripcion();
    id_cuestionario = Integer.toString(oPreguntaBean.getCuestionario().getId());
    if (oPreguntaBean.getCuestionario().getId() > 0) {
        descCuestionario = oPreguntaBean.getCuestionario().getDescripcion();
    }

    if (oContexto.getMetodo().equals("view")) {
        strTitulo = "Vista";
        strControlEnabled = "disabled=\"true\"";
        strValueBoton = "Cerrar";
    } else {
        strValueBoton = "Guardar";
    }
%>
<h1>Formulario de pregunta</h1>
<hr/>
<form class="form-horizontal" action="Controller" method="post" id="clienteForm">
    <fieldset>
        <!-- <legend>Formulario de pregunta</legend> -->
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="pregunta" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        
        <div class="control-group">
            <label class="control-label" for="descripcion">Descripción: </label> 
            <div class="controls">
                <input <%=strControlEnabled%>  id="descripcion"
                                               name="descripcion" type="text" size="30" maxlength="50"
                                               value="<%=descripcion%>" />
            </div>
        </div>
                   
        <div class="control-group">
            <label class="control-label" for="id_cuestionario">Cuestionario: </label> 
            <div class="controls">                
                <input readonly="true" id="id_cuestionario" class="input-mini"
                       name="id_cuestionario" type="text" size="5" maxlength="5"
                       value="<%=id_cuestionario%>" />  
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="cuestionario" />

                <span class="alert alert-success"><%=descCuestionario%></span>
            </div>
        </div>             
        <div class="control-group">
            <div class="controls">
                <input type="submit" name="enviar" value="<%=strValueBoton%>" />
            </div>
        </div>
    </fieldset>
</form>
