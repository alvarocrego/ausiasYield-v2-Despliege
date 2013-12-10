<%-- 
    Document   : form
    Created on : 28-nov-2013, 9:24:34
    Author     : al037877
--%>

<%@page import="net.daw.bean.UsuarioBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="net.daw.bean.DocumentoBean"%>
<%@page import="net.daw.bean.BolsaBean"%>
<%@page import="net.daw.helper.Contexto"%>
<%
    Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    String id_documento1 = "";
    String id_documento2 = "";
    String fecha = "";
    String documento1 = "";
    String documento2 = "";

    BolsaBean oBolsaBean = (BolsaBean) oContexto.getParametro();

    id = oBolsaBean.getId();
    id_documento1 = Integer.toString(oBolsaBean.getDocumento1().getId());

    if (oBolsaBean.getDocumento1().getId() > 0) {
        documento1 = oBolsaBean.getDocumento1().getTitulo();
    }

    id_documento2 = Integer.toString(oBolsaBean.getDocumento2().getId());

    if (oBolsaBean.getDocumento2().getId() > 0) {
        documento2 = oBolsaBean.getDocumento2().getTitulo();
    }
    fecha = new SimpleDateFormat("yyyy-MM-dd").format(oBolsaBean.getFecha());

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


    //Permisos
    UsuarioBean oUsuarioBean = (UsuarioBean) request.getSession().getAttribute("usuarioBean");
    Integer idUsuario = oUsuarioBean.getId();
    java.lang.Enum tipoUsuario = oUsuarioBean.getTipoUsuario();
%>
<h1><%=strTitulo%> de bolsa</h1>
<form class="semantic" action="Controller" method="post" id="bolsaForm">
    <fieldset>
        <legend>Formulario de Bolsa</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="bolsa" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <div class="control-group">
            <label class="control-label" for="id">ID: </label> 
            <div class="controls">                
                <input readonly="true" id="id" class="input-mini"
                       name="id" type="text" size="5" maxlength="5"
                       value="<%=id%>" />  
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="id_documento1">Documento 1: </label> 
            <div class="controls">                
                <input readonly="true" id="id_documento1" class="input-mini"
                       name="id_documento1" type="text" size="5" maxlength="5"
                       value="<%=id_documento1%>" />  
                <%
                    //permisos
                    if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Empresa)) {
                %>
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="documento1" />
                <%
                    }//permisos
                %>
                <span class="alert alert-success"><%=documento1%></span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="id_documento2">Documento 2: </label> 
            <div class="controls">                
                <input readonly="true" id="id_documento2" class="input-mini"
                       name="id_documento2" type="text" size="5" maxlength="5"
                       value="<%=id_documento2%>" />  
                <%
                    if (tipoUsuario.equals(net.daw.helper.Enum.TipoUsuario.Alumno)) {
                %>
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="documento2" />
                <%
                    }
                %>
                <span class="alert alert-success"><%=documento2%></span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="fecha">Fecha 2: </label> 
            <div class="controls">                
                <input <%=strControlEnabled%>  id="fecha"
                                               name="fecha" type="date" size="30" maxlength="50"
                                               value="<%=fecha%>" /> 
            </div>
        </div>
        <div>
            <input type="submit" name="enviar" value="<%=strValueBoton%>" />
        </div>
    </fieldset>
</form>
