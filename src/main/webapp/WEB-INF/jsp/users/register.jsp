<%@ include file="../general/headerInfo.jsp"%>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="../../../css/register.css">
<title>Dinux Properties</title>
</head>
<body>
	<div class="container">
		<%@ include file="../general/header.jsp"%>
		<div class="row" id="content">
			<div class="span10 offset1">
				<div class="hero-unit formHero">
					<form:form class="form-vertical" action="register" method="POST" commandName="registerForm">
						<fieldset>
							<legend id="formTitle">Regístrate</legend>
							<div class="row formRow">
								<div class="span3 offset1">
									<div class="control-group">
										<label class="control-label formItem" for="select01">Categoría:
										</label>
										<div class="controls">
											<form:select path="userType">
												<form:options items="${registerForm.allUsersType}"/>
											</form:select>
										</div>
									</div>
									<div class="control-group">
										<div class="controls">
											<button type="submit" class="btn btn-primary">Siguiente</button>
										</div>
									</div>
								</div>
							</div>


						</fieldset>
					</form:form>
				</div>
			</div>
		</div>
		<%@ include file="../general/footer.jsp"%>
	</div>

</body>
</html>