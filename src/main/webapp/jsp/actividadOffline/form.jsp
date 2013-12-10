<%@page import="java.text.SimpleDateFormat"%>
<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.ActividadofflineBean"%>
<%@page import="java.util.Date"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    String enunciado = "";
    String fecha = "";
    double calificacion = 0;
    Integer evaluacion = 0;
    int activo = 0;
    if (oContexto.getMetodo().equals("update") || oContexto.getMetodo().equals("view")) {
        ActividadofflineBean oActividadofflineBean = (ActividadofflineBean) oContexto.getParametro();

        id = oActividadofflineBean.getId();
        enunciado = oActividadofflineBean.getEnunciado();
        fecha = new SimpleDateFormat("yyyy-MM-dd").format(oActividadofflineBean.getFecha());
        calificacion = oActividadofflineBean.getCalificacion();
        evaluacion = oActividadofflineBean.getEvaluacion();
        activo = oActividadofflineBean.getActivo();
    }
    if (oContexto.getMetodo().equals("view")) {
        strTitulo = "Vista";
        strControlEnabled = "disabled=\"true\"";
        strValueBoton = "Cerrar";
    }
    if (oContexto.getMetodo().equals("update")) {
        strTitulo = "Edicion";
        strValueBoton = "Modificar";
    }
    if (oContexto.getMetodo().equals("new")) {
        strTitulo = "Alta";
        strValueBoton = "Crear";
    }
%>
<h1><%=strTitulo%> de Actividades Offline</h1>
<form class="form-horizontal" action="Controller" method="post" id="actividadofflineForm">
    <fieldset>
        <legend>Formulario de Actividades Offline</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="actividadoffline" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <div class="control-group">
            <label class="control-label" for="enunciado">Enunciado: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="enunciado" name="enunciado" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=enunciado%>" /><br />
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
            <label class="control-label" for="calificacion">Calificacion: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="calificacion" name="calificacion" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=calificacion%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="evaluacion">Evaluacion: </label>
            <div class="controls">
                <input <%=strControlEnabled%> id="evaluacion" name="evaluacion" type="text" size="30" maxlength="50" value="<%=evaluacion%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="activo">Activo: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="activo" name="activo" type="text" size="30" maxlength="50" value="<%=activo%>" /> <br />
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <input type="submit" name="enviar" value="<%=strValueBoton%>" />
            </div>
        </div>   
    </fieldset>
</form>