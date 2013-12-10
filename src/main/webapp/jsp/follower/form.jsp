
<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.FollowerBean"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    String id_usuario1 = "";
    String nombreUsuario1 = "";
    String id_usuario2 = "";
    String nombreUsuario2 = "";

    FollowerBean oFollowerBean = (FollowerBean) oContexto.getParametro();
    id = oFollowerBean.getId();

    id_usuario1 = Integer.toString(oFollowerBean.getUsuario1().getId());
    if (oFollowerBean.getUsuario1().getId() > 0) {
        nombreUsuario1 = oFollowerBean.getUsuario1().getLogin();
    }

    id_usuario2 = Integer.toString(oFollowerBean.getUsuario2().getId());
    if (oFollowerBean.getUsuario2().getId() > 0) {
        nombreUsuario2 = oFollowerBean.getUsuario2().getLogin();
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
<h1><%=strTitulo%> de follower</h1>
<form class="form-horizontal" action="Controller" method="post" id="followerForm">
    <fieldset>
        <legend>Formulario de followers</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="follower" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <div class="control-group">
            <label class="control-label" for="id_usuario1">Usuario 1</label> 
            <div class="controls">                
                <input readonly="true" id="id_usuario1" class="input-mini"
                       name="id_usuario1" type="text" size="5" maxlength="5"
                       value="<%=id_usuario1%>" />  
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="usuario1" />
                <span class="alert alert-success"><%=nombreUsuario1%></span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="id_usuario2">Usuario 2 </label>
            <div class="controls">                
                <input readonly="true" id="id_usuario2" class="input-mini"
                       name="id_usuario2" type="text" size="5" maxlength="5"
                       value="<%=id_usuario2%>" />  
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="usuario2" />
                <span class="alert alert-success"><%=nombreUsuario2%></span>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <input type="submit" name="enviar" value="<%=strValueBoton%>" />
            </div>
        </div>   
    </fieldset>
</form>
