<%@page import="java.text.SimpleDateFormat"%>
<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.HiloBean"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";

    Integer id = 0;
    String nombre = "";
    String fecha = "";

    HiloBean oHiloBean = (HiloBean) oContexto.getParametro();
    id = oHiloBean.getId();
    nombre = oHiloBean.getNombre();

    if (oContexto.getMetodo().equals("view")) {
        strTitulo = "Vista";
        strControlEnabled = "disabled=\"true\"";
        strValueBoton = "Cerrar";
        fecha = new SimpleDateFormat("yyyy-MM-dd").format(oHiloBean.getFecha());
    }
    if (oContexto.getMetodo().equals("update")) {
        strTitulo = "Edición";
        strValueBoton = "Modificar";
        fecha = new SimpleDateFormat("yyyy-MM-dd").format(oHiloBean.getFecha());
    }
    if (oContexto.getMetodo().equals("new")) {
        strTitulo = "Alta";
        strValueBoton = "Crear";
    }
%>
<h1><%=strTitulo%> de hilo</h1>
<form class="form-horizontal" action="Controller" method="post" id="hiloForm">
    <legend>Formulario de hilo</legend>
    <input type="hidden" name="id" value="<%=id%>" /> 
    <input type="hidden" name="class" value="hilo" /> 
    <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
    <input type="hidden" name="phase" value="2" />
    <div class="control-group">
        <label class="control-label" for="id">Id: </label> 
        <div class="controls">                
            <input readonly="true" id="id_producto" class="input-mini"
                   name="id_producto" type="text" size="5" maxlength="5"
                   value="<%=id%>" />  
        </div>
    </div>             

    <div class="control-group">
        <label class="control-label" for="nombre">Nombre: </label> 
        <div class="controls">                
            <input <%=strControlEnabled%> id="nombre" 
                                          name="nombre" type="text" size="50" maxlength="50"
                                          value="<%=nombre%>" />  
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
