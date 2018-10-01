<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../header.jsp"%>

	<form role="form" method="post">
		<div class="box-body">
			<div class="form-group">
				<label for="title">제목</label> <input type="text" id="title"
					name="title" class="form-control" placeholder="title..." />
			</div>

			<div class="form-group">
				<label for="content">내용</label>
				<textarea name="content" id="content" cols="30" rows="3"
					class="form-control" placeholder="내용.."></textarea>
			</div>

			<div class="form-group">
				<label for="writer">작성자</label>
				<input type="text" id="writer"
					name="writer" class="form-control" />
			</div>
			
			<div class="form-group">
			   <label for="">File Drop Here!</label>
			   <div class="fileDrop text-right">
			     <div id="percent">0 %</div>
                 <div id="status">ready</div>
			   </div>
			   
			     
		    </div>
		</div> <!-- end of box-body -->
		
		<div class="box-footer">
		  <ul class="mailbox-attachments clearfix uploadedList">
			<script id="template" type="text/x-handlebars-template">
              {{#each upFiles}} 
                <li>
                    <span class="mailbox-attachment-icon has-img">
                        <img src="{{imgsrc}}" alt="Attachement" />
                    </span>
                    <div class="mailbox-attachment-info">
                        <a href="{{getlink}}" class="mailbox-attachment-name">{{fileName}}</a>
                        <a href="{{fullName}}" class="btn btn-default btn-xs pull-right delbtn">
                            <i class="fa fa-fw fa-remove"></i>
                        </a>
                    </div>
                </li>
              {{else}}
                <li>첨부파일이 없습니다.</li>
              {{/each}}
            </script>
		  </ul>
		  
		  <button type="submit" class="btn btn-primary">Submit</button>
		</div>

	</form>

    <form action="/uploadAjax" id="form_attach" method="POST" enctype="multipart/form-data">
	   <input type="hidden" name="type" value="ajax" />
	   <input type="file" name="file" id="ajax-file" style="display: none;" />
	   <!-- <input type="submit" value="ajax로 제출" /> -->
    </form>


<script>
const $fileDrop = $('div.fileDrop');

$fileDrop.on('dragover dragenter', (evt) => {
    evt.preventDefault();
	$fileDrop.css("border", "2px dotted green");
});

$fileDrop.on('dragleave', (evt) => {
    evt.preventDefault();
    $fileDrop.css("border", "1px dotted gray");
});

$fileDrop.on('drop', (evt) => {
    evt.preventDefault();
	let files = evt.originalEvent.dataTransfer.files;
	console.debug("drop>>", files);
	$fileDrop.css("border", "1px dotted gray");
	$("#ajax-file").prop("files", evt.originalEvent.dataTransfer.files);
	$('#form_attach').submit();
});

const $percent = $('#percent'),
      $status = $('#status');
      
let upFiles = [];
$('#form_attach').ajaxForm({
    beforeSend: function() {
        let f = $('#ajax-file').val();
        console.debug("beforeSend!!", f);
        if (!f) return false;
        $status.empty();
        $percent.html('0%');
    },
    uploadProgress: function(event, position, total, percentComplete) {
        console.debug("progress...");
        $status.html('uploading...');
        $percent.html(percentComplete + '%');
    },
    complete: function(xhr) {
        console.debug("complete!!", xhr)
        let jsonData = getFileInfo(xhr.responseText);
        upFiles.push(jsonData);
        console.debug("QQQ>>", jsonData)
        $status.html(jsonData.fileName + ' Uploaded');
        renderHbs('template', {upFiles: upFiles });
    }
});

function checkImageType(fileName) {
    let pattern = /jpg$|png$|gif$/i;
    return fileName.match(pattern);
}

function getFileInfo(fullName) {
	let fileName, imgsrc, getLink, fileLink;
	
	if (checkImageType(fullName)) {
		imgsrc = "/displayFile?fileName=" + fullName;
		fileLink = fullName.substring(14); // 원본파일명/2018/09/00/s_
		let front = fullName.substring(0,12),
	        end = fullName.substring(14);
		getLink = "/displayFile?fileName=" + front + end; //원본파일보기용 URI
	
	} else {
		imgsrc = "/resources/dist/img/file_icon.jpeg";
		fileLink = fullName.substring(12); // 원본파일명/2018/09/00/
		getLink = "/displayFile?fileName=" + fullName;
	}
	
	// 실제파일명 (fileLink = asdfsafsdafdsaf_realname.ext)
	fileName = fileLink.substring(fileLink.indexOf('_') + 1);
	
	return {
	    fileName: fileName,
	    imgsrc: imgsrc,
	    getLink: getLink,
	    fullName: fullName
	};
}
</script>

<%@ include file="../footer.jsp"%>
</html>