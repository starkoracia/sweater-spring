<#import "macros/common.ftl" as c>
<#import "macros/registr-login-out.ftl" as l>

<@c.page>
    <div>
        <p3>Login page</p3>
    </div>
    <div>
        <#if RequestParameters.error??>
            <strong>Invalid username or password.</strong><br/>
        </#if>
    </div>
    <@l.login/>
</@c.page>