<#import "common.ftl" as c>

<#macro registration message>
    ${message!}
    <a href="/login">Sin in</a>
    <form action="/registration" method="post">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <@c._csrf_token/>
        <div>
            <button type="submit">Create User</button>
        </div>
    </form>
</#macro>

<#macro login>
    <form action="/login" method="post">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <@c._csrf_token/>
        <div><input type="submit" value="Sign In"/></div>
    </form>
    <a href="/registration">Registration (Add new User)</a>
</#macro>

<#macro logout>
    <#if RequestParameters.logout??>
        <strong>You have been logged out.</strong><br/>
    <#else >
        <form action="/logout" method="post">
            <@c._csrf_token/>
            <input type="submit" value="Sign Out"/>
        </form>
    </#if>
</#macro>