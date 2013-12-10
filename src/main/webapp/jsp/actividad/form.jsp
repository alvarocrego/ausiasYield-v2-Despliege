<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.ActividadBean"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    String enunciado = "";
    String fecha = "";
    String evaluacion = "";
    boolean activo;

    ActividadBean oActividadBean = (ActividadBean) oContexto.getParametro();
    id = oActividadBean.getId();
    enunciado = oActividadBean.getEnunciado();
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    if (oActividadBean.getFecha() != null) {
        fecha = formatoFecha.format(oActividadBean.getFecha());
    } else {
        fecha = formatoFecha.format(new Date());
    }
    evaluacion = Integer.toString(oActividadBean.getEvaluacion());
    activo = oActividadBean.getActivo();

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
<h1><%=strTitulo%> de actividad</h1>
<form class="form-horizontal" action="Controller" method="post" id="actividadForm">
    <fieldset>
        <legend>Formulario de actividad</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="actividad" /> 
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
                <input <%=strControlEnabled%> id="fecha" name="fecha" type="date" size="30" maxlength="50" value="<%=fecha%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="evaluacion">Evaluación: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="evaluacion" name="evaluacion" type="text" size="30" maxlength="50" value="<%=evaluacion%>" /> <br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="activo">Activo: </label> 
            <div class="controls">
                <%
                    String selected = "selected";
                    String op1 = "";
                    String op2 = "";
                    if(oActividadBean.getActivo()) {
                        op1 = " selected='selected'";
                    }else{
                        op2 = " selected='selected'";
                    }
                %>
                <select <%=strControlEnabled%> id="activo" name="activo" >
                    <option value="true" <%=op1%> > Sí </option>
                    <option value="false" <%=op2%> > No </option>
                </select>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <input type="submit" name="enviar" value="<%=strValueBoton%>" />
            </div>
        </div>   
    </fieldset>
</form>

