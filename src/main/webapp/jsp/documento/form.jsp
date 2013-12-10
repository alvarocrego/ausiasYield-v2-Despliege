
<%@page import="net.daw.helper.Enum.TipoUsuario"%>
<%@page import="net.daw.bean.UsuarioBean"%>
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
    String fecha = "";
    Integer nota = 0;
    String id_usuario = "";
    String descId_usuario = "";
    String etiquetas = "";

    DocumentoBean oDocumentoBean = (DocumentoBean) oContexto.getParametro();
    id = oDocumentoBean.getId();
    titulo = oDocumentoBean.getTitulo();
    contenido = oDocumentoBean.getContenido();
    fecha = new SimpleDateFormat("yyyy-MM-dd").format(oDocumentoBean.getFecha());
    nota = oDocumentoBean.getNota();
    id_usuario = Integer.toString(oDocumentoBean.getUsuario().getId());
    if (oDocumentoBean.getUsuario().getId() > 0) {
        descId_usuario = oDocumentoBean.getUsuario().getLogin();
    }
    etiquetas = oDocumentoBean.getEtiquetas();

    if (oContexto.getMetodo().equals("update")) {
        strTitulo = "Edición";
        strValueBoton = "Modificar";
    }
    if (oContexto.getMetodo().equals("new")) {
        strTitulo = "Alta";
        strValueBoton = "Crear";
    }
%>
<h1><%=strTitulo%> de documento</h1>
<form class="form-horizontal" action="Controller" method="post" id="clienteForm">
    <fieldset>
        <legend>Formulario de documento</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="documento" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <div class="control-group">
            <label class="control-label" for="titulo">Titulo: </label> 
            <div class="controls">
                <input id="titulo" class="input_resize" name="titulo" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=titulo%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="contenido">Contenido: </label>
            <div class="controls">
                <textarea class="field input_resize" id="contenido" name="contenido" type="text" rows="10" ><%=contenido%></textarea><br />
            </div>
        </div>
        <div class="accordion " id="accordionDocumentoForm">
            <div class="accordion-group">
                <div class="accordion-heading alert-info">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionDocumentoForm" href="#collapseDocumentoForm">
                        Información Wiki-Parser
                    </a>
                </div>
                <div id="collapseDocumentoForm" class="accordion-body collapse" style="height: 0px;">
                    <div class="accordion-inner ">
                        <p>Puedes dar formato al texto usando una determinada simbologia: </p>
                        <ul>
                            <li>Texto en <i>Cursiva</i>: Se realiza con "$$" ej. <code>$$cursiva$$</code></li>
                            <li>Texto en <b>Negrita</b>: Se realiza con "**" ej. <code>**negrita**</code></li>
                            <li>Titulos en diferentes tamaños: </li>
                            <ul>
                                <li>H1: Se realiza con "=" ej. <code>=Titulo1=</code></li>
                                <li>H2: Se realiza con "==" ej. <code>==Titulo2==</code></li>
                                <li>H3: Se realiza con "===" ej. <code>===Titulo3===</code></li>
                                <li>H4: Se realiza con "====" ej. <code>====Titulo4====</code></li>
                                <li>H5: Se realiza con "=====" ej. <code>=====Titulo5=====</code></li>
                                <li>H6: Se realiza con "======" ej. <code>======Titulo6======</code></li>
                            </ul>
                            <li>Diferentes tipos de enlazes: </li>
                            <ul>
                                <li>Enlaze Interno: Se realiza con "['id del documento'|'Nombre del enlaze']" ej. <code>[8|Enlaze de Prueba]</code></li>
                                <li>Enlaze Externo: Se realiza con "['URL Externa'|'Nombre del enlaze']" ej. <code>[http://www.google.com|Google]</code></li>
                            </ul>
                            <li>Parrafo: Se realiza con nuevas lineas pulsando <code>&crarr; Enter</code></li>
                        </ul>
                        <!--Agradecimientos a Álvaro Crego por haber hecho posible este parser-->
                    </div>
                </div>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="fecha">Fecha: </label> 
            <div class="controls">
                <input id="fecha" name="fecha" type="date" size="30" maxlength="50" value="<%=fecha%>" /> <br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="nota">Nota: </label> 
            <div class="controls">
                <input id="nota" name="nota" type="text" size="30" maxlength="50" value="<%=nota%>" /> <br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="id_usuario">Usuario: </label> 
            <div class="controls">
                <%
                    if (session.getAttribute("usuarioBean") != null) {
                        UsuarioBean oUsuarioBean = (UsuarioBean) session.getAttribute("usuarioBean");
                        if (oUsuarioBean.getTipoUsuario().equals(TipoUsuario.Profesor)) {
                %>
                <input readonly="true" id="id_usuario" class="input-mini"
                       name="id_usuario" type="text" size="5" maxlength="5"
                       value="<%=id_usuario%>" />  
                <input type="submit" name="searchingfor" value="usuario" />
                <span class="alert alert-success"><%=descId_usuario%></span>
                <%
                } else {

                %>
                <input type="hidden" name="id_usuario" value="<%=oUsuarioBean.getId()%>" /> 
                <span class="alert alert-success"><%=oUsuarioBean.getLogin()%></span>
                <%                        }
                    }
                %>
            </div>
        </div> 
        <div class="control-group">
            <label class="control-label" for="etiquetas">Etiquetas: </label> 
            <div class="controls">
                <input id="etiquetas" name="etiquetas" type="text" size="30" maxlength="50" value="<%=etiquetas%>" placeholder="Etiquetas separadas por coma" /><br />
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <input type="submit" name="enviar" value="<%=strValueBoton%>" />
            </div>
        </div>   
    </fieldset>
</form>
