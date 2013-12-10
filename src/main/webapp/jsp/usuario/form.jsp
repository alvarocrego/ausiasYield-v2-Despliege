<%@page import="net.daw.helper.Enum.TipoUsuario"%>
<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.UsuarioBean"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    String login = "";
    String password = "";
    if (oContexto.getMetodo().equals("update") || oContexto.getMetodo().equals("view")) {
        UsuarioBean oUsuarioBean = (UsuarioBean) oContexto.getParametro();
        id = oUsuarioBean.getId();
        login = oUsuarioBean.getLogin();
        password = oUsuarioBean.getPassword();
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
    /*
     if (oContexto.getMetodo().equals("new")) {
     strTitulo = "Alta";
     strValueBoton = "Crear";
     }*/
%>
<h1><%=strTitulo%> de Usuario</h1>
<form class="form-horizontal" action="Controller" method="post" id="usuarioForm">
    <fieldset>
        <legend>Formulario de Usuario</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="usuario" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <div class="control-group">
            <label class="control-label" for="id">ID: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> readonly="" id="id" name="id" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=id%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="login">Login: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> readonly="" id="login" name="login" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=login%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="password">Contraseña: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="password" name="password" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=password%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <input type="submit" name="enviar" value="<%=strValueBoton%>" />
            </div>
        </div>   
    </fieldset>
</form>
