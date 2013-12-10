
<%@page import="net.daw.bean.MetadocumentoBean"%>
<%@page import="net.daw.helper.Contexto"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    String titulo = "";
    String fecha = "";
    
    if (oContexto.getMetodo().equals("update") || oContexto.getMetodo().equals("view")) {
        MetadocumentoBean oMetadocumentoBean = (MetadocumentoBean) oContexto.getParametro();
        id = oMetadocumentoBean.getId();
        titulo = oMetadocumentoBean.getTitulo();
        fecha = oMetadocumentoBean.getFecha();
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
<h1><%=strTitulo%> de lenguaje</h1>
<form class="semantic" action="Controller" method="post" id="metadocumentoForm">
    <fieldset>
        <legend>Formulario de Metadocumento</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="metadocumento" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <div>
            <label for="titulo">Titulo: </label> 
            <input <%=strControlEnabled%> id="nombre" name="titulo" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=titulo%>" /><br />
        </div>
        <div>
            <label for="fecha">Fecha: </label> 
            <input <%=strControlEnabled%> id="fecha" name="fecha" type="date" size="30" maxlength="50" value="<%=fecha%>" /> <br />
        </div>
        <div>
            <input type="submit" name="enviar" value="<%=strValueBoton%>" />
        </div>
    </fieldset>
</form>
