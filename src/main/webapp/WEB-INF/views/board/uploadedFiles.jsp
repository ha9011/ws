<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="/resources/upload.js?ver=2"></script> 
  
<script id="template" type="text/x-handlebars-template">
              {{#each upFiles}} 
                <li id="{{fileId}}">
                    <input type="hidden" name="files" value="{{fullName}}" />
                    <span class="mailbox-attachment-icon has-img">
                        <img src="{{imgsrc}}" alt="Attachement" />
                    </span>
                    <div class="mailbox-attachment-info">
                        <a href="{{getlink}}" class="mailbox-attachment-name">{{fileName}}</a>
                      {{#if isEditing}}
                        <a href="javascript:;" onclick="deleteFile('{{fullName}}', '${board.bno}')" class="btn btn-default btn-xs pull-right delbtn">
                            <i class="fa fa-fw fa-remove"></i>
                        </a>
                      {{/if}}
                    </div>
                </li>
              {{else}}
                <li>첨부파일이 없습니다.</li>
              {{/each}}
       </script>
       
<script>
function showAttaches(bno) {
	sendAjax("/board/getAttach/" + bno, (isSuccess, res) => {
	    if (isSuccess) {
	        let upfiles = []; // array of jsonData
	        res.forEach( rj => {
	            let jsonData = getFileInfo(rj);
	            upfiles.push(jsonData);
	        });
	        renderHbs('template', {upFiles: upfiles });
	    } else {
	        console.debug("Error on getAttach>>", res);
	    }
	});	
}

</script>