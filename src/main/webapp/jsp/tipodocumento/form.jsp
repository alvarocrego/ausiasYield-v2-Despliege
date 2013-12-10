
<%@page import="net.daw.bean.TipodocumentoBean"%>
<%@page import="net.daw.helper.Contexto"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Boolean privado = false;
    Integer id = 0;
    String descripcion = "";
    if (oContexto.getMetodo().equals("update") || oContexto.getMetodo().equals("view")) {
        TipodocumentoBean oTipodocumentoBean = (TipodocumentoBean) oContexto.getParametro();
        id = oTipodocumentoBean.getId();
        descripcion = oTipodocumentoBean.getDescripcion();
        privado = oTipodocumentoBean.isPrivado();
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
<h1><%=strTitulo%> de tipodocumento</h1>
<form class="semantic" action="Controller" method="post" id="tipodocumentoForm">
    <fieldset>
        <legend>Formulario de Tipodocumento</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="tipodocumento" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <div>
            <label for="titulo">Descripción: </label> 
            <textarea <%=strControlEnabled%> id="descripcion" name="descripcion" autofocus="autofocus"><%=descripcion%></textarea>
        </div>
        <div>
            <label for="titulo">Privado: </label> 
            <input <%=strControlEnabled%> id="privado" name="privado" type="checkbox" autofocus="autofocus" value="1"  <% if(privado){ %><%="checked=\"checked\""%><% } %>/><br />
        </div>
        <div>
            <input type="submit" name="enviar" value="<%=strValueBoton%>" />
        </div>
    </fieldset>
</form>
