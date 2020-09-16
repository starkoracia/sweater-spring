<#import "macros/common.ftl" as c>

<@c.page>
    <div>
        <form method="post" action="/main" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="Введите сообщение">
            <input type="text" name="tag" placeholder="Тэг">
            <input type="file" name="file">
            <@c._csrf_token/>
            <button type="submit">Добавить</button>
        </form>
    </div>
    <br/>
    <div>
        <form method="get" action="/main">
            <input type="text" name="filter" value="${RequestParameters.filter!}">
            <button type="submit">Найти</button>
        </form>
    </div>
    <br/>

    <div>
        <table border="1">
            <tr>
                <th></th>
                <th>Messages</th>
            </tr>
            <tr>
                <th>Id</th>
                <th>Message</th>
                <th>Teg</th>
            </tr>
            <#list messages as message>
                <tr>
                    <td>${message.id}</td>
                    <td>${message.text}</td>
                    <td>${message.tag}</td>
                    <td><strong>${message.authorName}</strong></td>
                    <#if message.filename??>
                        <td><img src="/img/${message.filename}"/></td>
                    </#if>
                </tr>
            </#list>
        </table>
    </div>
</@c.page>