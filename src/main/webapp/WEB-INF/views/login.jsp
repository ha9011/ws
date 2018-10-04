<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="header.jsp"%>

	<form role="form" action="/loginPost" method="post">
		<div class="box-body">
			<div class="form-group">
				<input type="text" id="uid"
					name="uid" class="form-control" placeholder="User ID..." />
			</div>

			<div class="form-group">
				<input type="password" name="upw" id="upw"
					class="form-control" placeholder="Password..."/>
			</div>
			
			<div class="form-group">
			     <label for="useCookie">
			         <input type="checkbox" id="useCookie" name="useCookie" /> Rememeber Me
			     </label>
			</div>
		
		</div> <!-- end of box-body -->
		
		<div class="box-footer">
		  <button type="submit" class="btn btn-primary">Sign In</button>
		</div>

	</form>
	
<%@ include file="footer.jsp"%>

</html>