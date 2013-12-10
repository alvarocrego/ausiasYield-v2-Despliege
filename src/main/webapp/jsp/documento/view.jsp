
<%@page import="net.daw.helper.TextParser"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.File"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.DocumentoBean"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    String titulo = "";
    String contenido = "";
    String contenidoParse = "";
    String fecha = "";
    Integer nota = 0;
    String usuario = "";
    String etiquetas = "";

    DocumentoBean oDocumentoBean = (DocumentoBean) oContexto.getParametro();
    id = oDocumentoBean.getId();
    titulo = oDocumentoBean.getTitulo();
    contenido = oDocumentoBean.getContenido();
    contenidoParse = oDocumentoBean.getContenidoParse();
    fecha = new SimpleDateFormat("dd-MM-yyyy").format(oDocumentoBean.getFecha());
    nota = oDocumentoBean.getNota();
    usuario = oDocumentoBean.getUsuario().getLogin();
    if (usuario.isEmpty()!=true) {
        usuario = usuario.substring(0, 1).toUpperCase() + usuario.substring(1, usuario.length());
    }
    etiquetas = oDocumentoBean.getEtiquetas();

    strTitulo = "Vista";
    strValueBoton = "Cerrar";
%>
<%
    String classNota = "";
    if (nota == 5) {
        classNota = "class=\"text-warning\"";
    } else if (nota <= 4) {
        classNota = "class=\"text-error\"";
    } else if (nota >= 6) {
        classNota = "class=\"text-success\"";
    }
%>
<%
    String[] etiquetasSeparadas = etiquetas.split(",");
    String etiquetasProcesadas = "";
    for (int j = 0; j < etiquetasSeparadas.length; j++) {
        etiquetasProcesadas += " <a href=\"Controller?nrpp=10&enviar=Filtrar&filteroperator=like&filtervalue=" + etiquetasSeparadas[j] + "&page=1&class=documento&method=list&filter=etiquetas\"><span class=\"label label-info\">" + etiquetasSeparadas[j] + "</span></a> ";
    }
%>
<h1><%=strTitulo%> de documento</h1>
<form class="form-horizontal" action="Controller" method="post" id="clienteForm">
    <fieldset>
        <legend>
            <p>Fecha: <%=fecha%></p><p <%=classNota%>>Nota: <%=nota%></p><p>Autor: <%=usuario%></p>
        </legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="documento" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <h1><%=titulo%></h1>
        <%=contenidoParse%>
        <div class="control-group">
            <br/>
            <p>Etiquetas: <span class="alert alert-info"><%=etiquetasProcesadas%></span></p>
        </div>
        <div class="control-group">
            <div class="controls">
                <input type="submit" name="enviar" value="<%=strValueBoton%>" />
            </div>
        </div>   
    </fieldset>
</form>
