<#import "macros/common.ftl" as c>
<#import "macros/registr-login-out.ftl" as l>

<@c.page>
    <div>
        <h4>Login page</h4><br/>
    </div>
    <div>
        <#if RequestParameters.error??>
            <div class="alert alert-primary" role="alert">
                <strong>Invalid username or password.</strong>
            </div>
        </#if>
    </div>
    <#if message?? && message != "">
        <div class="alert alert-primary" role="alert">
            <strong>${message}</strong>
        </div>
    </#if>
    <form action="/login" method="post">
        <div class="form-group">
            <div><label> User Name<input type="text" class="form-control" name="username" placeholder="Username"/> </label></div>
            <div><label> Password<input type="password" name="password" class="form-control" placeholder="Password"/> </label></div>
            <@c._csrf_token/>
        </div>
        <div class="mt-3">
            <button type="submit" class="btn btn-primary mr-3">Login</button>
            <strong>or</strong>
            <a href="/registration" class="ml-3">Registration</a>
        </div>
    </form>
</@c.page>