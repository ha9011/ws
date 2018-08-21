<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../header.jsp"%>

<script>
    var result = '${msg}';
    if (result === 'save-ok') {
        alert("Update Completed.");
    }
</script>

    <section class="content">
        <div class="box-body">
            <div class="form-group">
                <label for="title">제목</label> 
                <span>${ board.title }</span>
            </div>

            <div class="form-group">
                <label for="content">내용</label>
                <span>${ board.content }</span>
            </div>

            <div class="form-group">
                <label for="writer">작성자</label>
                <span>${ board.writer }</span>
            </div>
            
            <div class="form-group">
                <label for="writer">조회수</label>
                <span>${ board.viewcnt }</span>
            </div>
        </div>

        <div class="box-footer text-center">
            <button id="btn-remove-read" class="btn btn-danger">삭제</button>
            <a href="/board/update?bno=${ board.bno }" class="btn btn-primary">수정</a>
            <a href="/board/listAll" class="btn btn-default">목록</a>
        </div>
    </section>
    
    <script>
    $(document).ready( function() {
    	// var $boxFooter = $("section.content div.box-footer");
    	
    	$('#btn-remove-read').on('click', function() {
    		if (confirm("Are u sure??")) {
    			self.location.href = "/board/remove?bno=${board.bno}";
    		}
    	});
    	
    });
    </script>

<%@ include file="../footer.jsp"%>
</html>