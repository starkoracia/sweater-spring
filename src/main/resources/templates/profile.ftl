<#import "macros/common.ftl" as c>
<#include "security.ftl">

<@c.page>
    <div>
        <h4>${user.username}</h4><br/>
        <#if changedMessage??><h4>${changedMessage}</h4><br/></#if>
    </div>
    <form method="post">
        <div class="form-group">
            <div><label> Password<input type="text" class="form-control" name="password" placeholder="Password"/> </label></div>
            <div><label> Email<input type="email" name="email" class="form-control" value="${userEmail!''}" placeholder="Email"/> </label></div>
            <#if isEmailChanged!false><h5>Confirm the activation code by email</h5></#if>
            <@c._csrf_token/>
        </div>
        <div>
            <button type="submit" class="btn btn-primary mr-3">Save changes</button>
        </div>
    </form>
</@c.page>