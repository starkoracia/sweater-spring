<#import "macros/common.ftl" as c>

<@c.page>
    <form method="post" action="/user">
        <div><label> User Name : <input type="text" value="${user.username}" name="username"/> </label></div>
        <input type="hidden" value="${user.id}" name="userId"/>
        <#list roles as role>
            <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
        </#list>
        <@c._csrf_token/>
        <button type="submit">Save</button>
    </form>
</@c.page>