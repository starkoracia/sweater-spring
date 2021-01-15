<#include "security.ftl">

<button class="btn btn-primary " type="button" data-toggle="collapse" data-target="#newMessage"
        aria-expanded="false"
        aria-controls="newMessage">
    Message Editor
</button>
<div class="collapse <#if message??>show</#if>" id="newMessage">
    <form method="post" action="/user-messages/${currentUserId}" enctype="multipart/form-data">
        <div class="form-group my-2">
            <div class="my-3">
                <input class="form-control ${(textError??)?string("is-invalid", "")}"
                       value="<#if message??>${message.text}</#if>"
                       type="text" name="text" placeholder="Введите сообщение"
                       aria-describedby="validationTextFeedback">
                <div id="validationTextFeedback" class="invalid-feedback">
                    ${textError!}
                </div>
            </div>
            <div class="my-3">
                <input class="form-control ${(tagError??)?string("is-invalid", "")}"
                       value="<#if message??>${message.tag}</#if>"
                       type="text" name="tag" placeholder="Тэг" aria-describedby="validationTagFeedback">
                <div id="validationTagFeedback" class="invalid-feedback">
                    ${tagError!}
                </div>
            </div>
            <div class="custom-file mb-4">
                <input type="file" name="file" id="customFile" class="custom-file-input">
                <label class="custom-file-label" for="customFile">Choose file</label>
            </div>
            <@c._csrf_token/>
            <input type="hidden" name="id" value="<#if message??>${message.id}</#if>"/>
            <button class="btn btn-primary " type="submit">Save message</button>
        </div>
    </form>
</div>