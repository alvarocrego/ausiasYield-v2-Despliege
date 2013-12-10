
<%@page import="java.util.Calendar"%>
<%@page import="net.daw.bean.VotoComentarioBean"%>
<%@page import="net.daw.helper.Contexto"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    Integer id_usuario = 0;
    String descId_usuario = "";
    Integer id_comentario = 0;
    String descId_comentario = "";
    Integer valor = 0;

    VotoComentarioBean oVotoComentarioBean = (VotoComentarioBean) oContexto.getParametro();

    id = oVotoComentarioBean.getId();
    id_usuario = oVotoComentarioBean.getId_usuario().getId();
    if (!(oVotoComentarioBean.getId_usuario().getLogin().equals(""))) {
        descId_usuario = oVotoComentarioBean.getId_usuario().getLogin();
    }
    id_comentario = oVotoComentarioBean.getId_comentario().getId();
    if (!(oVotoComentarioBean.getId_comentario().getTitulo().equals(""))) {
        descId_comentario = oVotoComentarioBean.getId_comentario().getTitulo();
    }

    valor = oVotoComentarioBean.getValor();

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
<h1><%=strTitulo%> de votoComentario</h1>
<form class="semantic" action="Controller" method="post" id="votoComentarioForm">
    <fieldset>
        <legend>Formulario de VotoComentario</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="votoComentario" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />

        <div class="control-group">
            <label class="control-label" for="id_usuario">Id_usuario: </label> 
            <div class="controls">                
                <input readonly="true" id="id_usuario" class="input-mini"
                       name="id_usuario" type="text" size="5" maxlength="5"
                       value="<%=id_usuario%>" />  
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="id_usuario" />
                <span class="alert alert-success"><%=descId_usuario%></span>
            </div>
        </div>   

        <div class="control-group">
            <label class="control-label" for="id_comentario">Id_comentario: </label> 
            <div class="controls">                
                <input readonly="true" id="id_comentario" class="input-mini"
                       name="id_comentario" type="text" size="5" maxlength="5"
                       value="<%=id_comentario%>" />  
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="id_comentario" />
                <span class="alert alert-success"><%=descId_comentario%></span>
            </div>
        </div>  

        <div>
            <label for="nombre">Valor: </label> 
            <input <%=strControlEnabled%> id="valor" name="valor" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=valor%>" /><br />
        </div>
        <div>
            <input type="submit" name="enviar" value="<%=strValueBoton%>" />
        </div>
    </fieldset>
</form>
