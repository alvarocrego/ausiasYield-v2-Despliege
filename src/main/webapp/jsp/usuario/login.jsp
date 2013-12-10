<form id="loginForm" class="form-horizontal" method="post" action="Controller">   
    <h1>Formulario de entrada al sistema</h1> 
    <br />
    <input type="hidden" name="class" value="usuario" />
    <input type="hidden" name="method" value="login" />
    <input type="hidden" name="phase" value="2" />
    <div class="control-group">
        <label class="control-label" for="inputEmail">Usuario:</label>
        <div class="controls">
            <input type="text" id="text" autofocus="autofocus" placeholder="Nombre de usuario" name="login">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="inputPassword">Password:</label>
        <div class="controls">
            <input type="password" id="inputPassword" placeholder="Password" name="password">
        </div>
    </div>
    <div class="control-group">
        <div class="controls">
            <!--
            <label class="checkbox"> <input type="checkbox">
                Remember me
            </label>
            -->
            <button type="submit" class="btn">Entrar</button>
        </div>
    </div>
</form>
