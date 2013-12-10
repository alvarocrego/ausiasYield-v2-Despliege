<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.EntregaBean"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";

    Integer id = 0;
    String id_documento = "";
    String descDocumento = "";
    String id_actividad = "";
    String descActividad = "";
    String nota = "";
    String fecha = "";

    EntregaBean oEntregaBean = (EntregaBean) oContexto.getParametro();
    id = oEntregaBean.getId();
    id_documento = Integer.toString(oEntregaBean.getDocumento().getId());
    if (oEntregaBean.getDocumento().getId() > 0) {
        descDocumento = oEntregaBean.getDocumento().getTitulo();
    }
    id_actividad = Integer.toString(oEntregaBean.getActividad().getId());
    if(oEntregaBean.getDocumento().getId() > 0) {
        descActividad = oEntregaBean.getActividad().getEnunciado();
    }
    nota = Double.toString(oEntregaBean.getNota());
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    if (oEntregaBean.getFecha() != null) {
        fecha = formatoFecha.format(oEntregaBean.getFecha());
    } else {
        fecha = formatoFecha.format(new Date());
    }

    if (oContexto.getMetodo().equals("view")) {
        strTitulo = "Vista";
        strControlEnabled = "disabled=\"true\"";
        strValueBoton = "Cerrar";
    } else {
        strValueBoton = "Guardar";
    }
%>
<h1>Formulario de entrega</h1>
<hr/>
<form class="form-horizontal" action="Controller" method="post" id="entregaForm">
    <fieldset>
        <!-- <legend>Formulario de entrega</legend> -->
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="entrega" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
                   
        <div class="control-group">
            <label class="control-label" for="id_documento">Documento: </label> 
            <div class="controls">                
                <input readonly="true" id="id_documento" class="input-mini"
                       name="id_documento" type="text" size="5" maxlength="5"
                       value="<%=id_documento%>" />  
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="documento" />

                <span class="alert alert-success"><%=descDocumento%></span>
            </div>
        </div>          
        <div class="control-group">
            <label class="control-label" for="id_actividad">Actividad: </label> 
            <div class="controls">                
                <input readonly="true" id="id_actividad" class="input-mini"
                       name="id_actividad" type="text" size="5" maxlength="5"
                       value="<%=id_actividad%>" />  
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="actividad" />

                <span class="alert alert-success"><%=descActividad%></span>
            </div>
        </div> 
        <div class="control-group">
            <label class="control-label" for="nota">Nota: </label> 
            <div class="controls">                
                <input <%=strControlEnabled%> type="range" id="nota" name="nota" min="0" max="100" step="1" value="<%=nota%>">
                <span class="alert alert-success"><%=nota%>%</span>
            </div>
        </div>     
        <div class="control-group">
            <label class="control-label" for="fecha">Fecha: </label> 
            <div class="controls">                
                <input <%=strControlEnabled%> type="date" id="fecha" value="<%=fecha%>">
            </div>
        </div>         
        <div class="control-group">
            <div class="controls">
                <input type="submit" name="enviar" value="<%=strValueBoton%>" />
            </div>
        </div>
    </fieldset>
</form>
