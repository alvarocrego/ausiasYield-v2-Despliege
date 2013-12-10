<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.BacklogBean"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";

    Integer id = 0;
    String enunciado = "";
    String descripciondetallada = "";
    String id_requerimiento = "";
    String fechainicioprevista = "";
    String fechafinprevista = "";
    String fechainicio = "";
    String fechafin = "";
    String id_usuario = "";
    String horasduracionprevista = "";
    String porcentajecompletado = "";
    String fechaalta = "";
    String sprint = "";
    String release = "";
    String nomUsu = "";
    String nomReq = "";

    BacklogBean oBacklogBean = (BacklogBean) oContexto.getParametro();

    id = oBacklogBean.getId();

    id_usuario = Integer.toString(oBacklogBean.getUsuario().getId());
    id_requerimiento = Integer.toString(oBacklogBean.getRequerimiento().getId());

    if (oBacklogBean.getUsuario().getId() > 0) {
        nomUsu = oBacklogBean.getUsuario().getLogin();
    }

    if (oBacklogBean.getRequerimiento().getId() > 0) {
        nomReq = oBacklogBean.getRequerimiento().getEnunciado();
    }

    enunciado = oBacklogBean.getEnunciado();
    descripciondetallada = oBacklogBean.getDescripciondetallada();

    horasduracionprevista = Integer.toString(oBacklogBean.getHorasduracionprevista());
    porcentajecompletado = Integer.toString(oBacklogBean.getPorcentajecompletado());
    sprint = Integer.toString(oBacklogBean.getSprint());
    release = Integer.toString(oBacklogBean.getRelease());

    if (oContexto.getMetodo().equals("view")) {
        strTitulo = "Vista";
        strControlEnabled = "disabled=\"true\"";
        fechainicioprevista = new SimpleDateFormat("yyyy-MM-dd").format(oBacklogBean.getFechainicioprevista());
        fechafinprevista = new SimpleDateFormat("yyyy-MM-dd").format(oBacklogBean.getFechafinprevista());
        fechainicio = new SimpleDateFormat("yyyy-MM-dd").format(oBacklogBean.getFechainicio());
        fechafin = new SimpleDateFormat("yyyy-MM-dd").format(oBacklogBean.getFechafin());
        fechaalta = new SimpleDateFormat("yyyy-MM-dd").format(oBacklogBean.getFechaalta());
        strValueBoton = "Cerrar";
    }
    if (oContexto.getMetodo().equals("update")) {
        strTitulo = "Edicion";
        fechainicioprevista = new SimpleDateFormat("yyyy-MM-dd").format(oBacklogBean.getFechainicioprevista());
        fechafinprevista = new SimpleDateFormat("yyyy-MM-dd").format(oBacklogBean.getFechafinprevista());
        fechainicio = new SimpleDateFormat("yyyy-MM-dd").format(oBacklogBean.getFechainicio());
        fechafin = new SimpleDateFormat("yyyy-MM-dd").format(oBacklogBean.getFechafin());
        fechaalta = new SimpleDateFormat("yyyy-MM-dd").format(oBacklogBean.getFechaalta());
        strValueBoton = "Modificar";
    }
    if (oContexto.getMetodo().equals("new")) {
        strTitulo = "Alta";
        strValueBoton = "Crear";
    }
%>
<h1><%=strTitulo%> de Backlog</h1>
<form class="form-horizontal" action="Controller" method="post" id="backlogForm">
    <legend>Formulario de Backlog</legend>
    <fieldset>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="backlog" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <div class="control-group">
            <label class="control-label" for="codigo">Enunciado: </label>
            <div class="controls">
                <input <%=strControlEnabled%>  id="enunciado" name="enunciado"
                                               type="text" size="50" maxlength="75" autofocus="autofocus"
                                               value="<%=enunciado%>" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="id_usuario">Usuario: </label> 
            <div class="controls">                
                <input readonly="true" id="id_usuario" class="input-mini"
                       name="id_usuario" type="text" size="5" maxlength="5"
                       value="<%=id_usuario%>" />  
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="usuario" />
                <span class="alert alert-success"><%=nomUsu%></span>
            </div>
        </div> 
        <div class="control-group">
            <label class="control-label" for="id_requerimiento">Requerimiento </label> 
            <div class="controls">                
                <input readonly="true" id="id_requerimiento" class="input-mini"
                       name="id_requerimiento" type="text" size="5" maxlength="5"
                       value="<%=id_requerimiento%>" />  
                <input <%=strControlEnabled%> type="submit" name="searchingfor" value="requerimiento" />
                <span class="alert alert-success"><%=nomReq%></span>
            </div>
        </div> 
        <div class="control-group">
            <label class="control-label" for="descripcion">Descripción: </label> 
            <div class="controls">
                <input <%=strControlEnabled%>  id="descripciondetallada"
                                               name="descripciondetallada" type="text" size="50" maxlength="50"
                                               value="<%=descripciondetallada%>" ></input>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="fecha">Fecha Alta: </label> 
            <div class="controls">
                <input <%=strControlEnabled%>  id="fechaalta"
                                               name="fechaalta" type="date" size="30" maxlength="50"
                                               value="<%=fechaalta%>" /> 
            </div> 
        </div>

        <div class="control-group">
            <label class="control-label" for="fecha">Fecha Inicio Prevista: </label> 
            <div class="controls">
                <input <%=strControlEnabled%>  id="fechainicioprevista"
                                               name="fechainicioprevista" type="date" size="30" maxlength="50"
                                               value="<%=fechainicioprevista%>" /> 
            </div> 
        </div>

        <div class="control-group">
            <label class="control-label" for="fecha">Fecha Inicio: </label> 
            <div class="controls">
                <input <%=strControlEnabled%>  id="fechainicio"
                                               name="fechainicio" type="date" size="30" maxlength="50"
                                               value="<%=fechainicio%>" /> 
            </div> 
        </div>

        <div class="control-group">
            <label class="control-label" for="fecha">Fecha Fin Prevista: </label> 
            <div class="controls">
                <input <%=strControlEnabled%>  id="fechafinprevista"
                                               name="fechafinprevista" type="date" size="30" maxlength="50"
                                               value="<%=fechafinprevista%>" /> 
            </div> 
        </div>

        <div class="control-group">
            <label class="control-label" for="fecha">Fecha Fin: </label> 
            <div class="controls">
                <input <%=strControlEnabled%>  id="fechafin"
                                               name="fechafin" type="date" size="30" maxlength="50"
                                               value="<%=fechafin%>" /> 
            </div> 
        </div>

        <div class="control-group">
            <label class="control-label">Porcentaje Completado: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> type="range" id="porcentajecompletado" name="porcentajecompletado" min="0" max="100" step="5" value="<%=porcentajecompletado%>">
                <span class="alert alert-success"><%=porcentajecompletado%>%</span>
            </div>
        </div>         

        <div class="control-group">
            <label class="control-label">Horas Duracion: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> type="range" id="horasduracionprevista" name="horasduracionprevista" min="0" max="100" step="5" value="<%=horasduracionprevista%>">
                <span class="alert alert-success"><%=horasduracionprevista%> horas</span>
            </div>
        </div>        

        <div class="control-group">
            <label class="control-label" for="codigo">Sprint: </label>
            <div class="controls">
                <input <%=strControlEnabled%>  id="codigo" name="sprint"
                                               type="text" size="50" maxlength="75" autofocus="autofocus"
                                               value="<%=sprint%>" />
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="codigo">Release: </label>
            <div class="controls">
                <input <%=strControlEnabled%>  id="codigo" name="release"
                                               type="text" size="50" maxlength="75" autofocus="autofocus"
                                               value="<%=release%>" />
            </div>
        </div>

        </div>         
        <div class="control-group">
            <div class="controls">
                <input type="submit" name="enviar" value="<%=strValueBoton%>" />
            </div>
        </div>
    </fieldset>
</form>
