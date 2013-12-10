
<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.MetadocumentosBean"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";

    Integer id = 0;
    String id_metadocumento = "";
    String descid_metadocumento = "";
    String id_documento = "";
    String descId_documento = "";
    String orden = "0";

    
    MetadocumentosBean oMetadocumentosBean = (MetadocumentosBean) oContexto.getParametro();
    id = oMetadocumentosBean.getId();
    id_metadocumento = Integer.toString(oMetadocumentosBean.getMetadocumento().getId());
    if (oMetadocumentosBean.getMetadocumento().getId() > 0) {
       descid_metadocumento = oMetadocumentosBean.getMetadocumento().getTitulo();
    }
    id_documento = Integer.toString(oMetadocumentosBean.getDocumento().getId());
    
        if(oMetadocumentosBean.getDocumento().getTitulo() != null){
        descId_documento = oMetadocumentosBean.getDocumento().getTitulo();
        }
    
    orden = Integer.toString(oMetadocumentosBean.getOrden());
    
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
<h1><%=strTitulo%> de metadocumentos</h1>
<form class="form-horizontal" action="Controller" method="post" id="clienteForm">
    <legend>Formulario de metadocumentos</legend>
    <input type="hidden" name="id" value="<%=id%>" /> 
    <input type="hidden" name="class" value="metadocumentos" /> 
    <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
    <input type="hidden" name="phase" value="2" />
    <div class="control-group">
        <label class="control-label" for="id_metadocumento">Id_Metadocumento: </label> 
        <div class="controls">                
            <input readonly="true" id="id_metadocumento" class="input-mini"
                   name="id_metadocumento" type="text" size="5" maxlength="5"
                   value="<%=id_metadocumento%>" />  
            <input <%=strControlEnabled%> type="submit" name="searchingfor" value="metadocumento" />
            <span class="alert alert-success"><%=descid_metadocumento%></span>
        </div>
    </div>   
    <div class="control-group">
        <label class="control-label" for="id_documento">Id_Documento: </label> 
        <div class="controls">                
            <input readonly="true" id="id_documento" class="input-mini"
                   name="id_documento" type="text" size="5" maxlength="5"
                   value="<%=id_documento%>" />  
            <input <%=strControlEnabled%> type="submit" name="searchingfor" value="documento" />
            <span class="alert alert-success"><%=descId_documento%></span>
        </div>
    </div>             
    <div class="control-group">
        <label class="control-label" for="orden">Orden: </label> 
        <div class="controls">
            <input <%=strControlEnabled%>  id="orden"
                                           name="orden" type="text" size="30" maxlength="50"
                                           value="<%=orden%>"  /> 
        </div>
    </div>
    <div class="control-group">
        <div class="controls">
            <input type="submit" name="enviar" value="<%=strValueBoton%>" />
        </div>
    </div>
</form>