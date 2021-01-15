<#import "macros/common.ftl" as c>

<@c.page>
    <form method="post" action="/user">
        <div class="form-inline mb-3">
            <label> User Name </label>
            <input class="form-control ml-3" type="text" value="${user.username}" name="username"/>
        </div>
        <input type="hidden" value="${user.id}" name="userId"/>
        <label>Roles :</label>
        <#list roles as role>
            <div class="form-check">
                <label>
                    <input type="checkbox" name="${role}"
                            ${user.roles?seq_contains(role)?string("checked", "")}>${role}
                </label>
            </div>
        </#list>

        <@c._csrf_token/>
        <button class="btn btn-primary mt-3" type="submit">Save</button>
    </form>
</@c.page>