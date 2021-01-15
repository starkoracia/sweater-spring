<#import "common.ftl" as c>
<#include "../security.ftl">

<#macro logout>
    <#if RequestParameters.logout??>
        <strong>You have been logged out.</strong><br/>
    <#elseif name == "unknown">
        <a href="/login">Login</a>
    <#else >
        <form action="/logout" method="post">
            <@c._csrf_token/>
            <label class="xxx"><input type="submit" hidden="hidden" value="Sign Out">Sing Out</label>
        </form>
    </#if>
</#macro>