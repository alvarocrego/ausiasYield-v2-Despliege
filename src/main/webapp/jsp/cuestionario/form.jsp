<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.CuestionarioBean"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    String descripcion = "";
    String fecha = "";
    String evaluacion = "";
    boolean activo;

    CuestionarioBean oCuestionarioBean = (CuestionarioBean) oContexto.getParametro();
    id = oCuestionarioBean.getId();
    descripcion = oCuestionarioBean.getDescripcion();
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    if (oCuestionarioBean.getFecha() != null) {
        fecha = formatoFecha.format(oCuestionarioBean.getFecha());
    } else {
        fecha = formatoFecha.format(new Date());
    }
    evaluacion = Integer.toString(oCuestionarioBean.getEvaluacion());
    activo = oCuestionarioBean.getActivo();

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
<h1><%=strTitulo%> de cuestionario</h1>
<form class="form-horizontal" action="Controller" method="post" id="cuestionarioForm">
    <fieldset>
        <legend>Formulario de cuestionario</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="cuestionario" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <div class="control-group">
            <label class="control-label" for="descripcion">Descripcion: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="descripcion" name="descripcion" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=descripcion%>" /><br />
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
                    if(oCuestionarioBean.getActivo()) {
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
