<#import "macros/common.ftl" as c>

<@c.page>
    <div class="mb-4">
        <form method="get" action="/main">
            <div class="form-row mt-4">
                <div class="mr-3 col-5">
                    <input class="form-control " type="text" name="filter" value="${RequestParameters.filter!}">
                </div>
                <button class="btn btn-primary" type="submit">Найти</button>
            </div>
        </form>
    </div>

    <#include "messageEditor.ftl" />
    <#include "messageList.ftl" />
</@c.page>