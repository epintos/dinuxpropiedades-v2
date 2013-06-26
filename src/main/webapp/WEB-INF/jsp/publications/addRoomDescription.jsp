<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="../general/headerInfo.jsp"%>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css"
	href="../../../css/viewPublication.css">
<title>Dinux Properties</title>
</head>
<body>
	<div class="container">
		<%@ include file="../general/header.jsp"%>
		<div class="row" id="content">
			<div class="span10 offset1">
				<div class="hero-unit formHero">
					<fieldset>
						<legend class="formTitle">Nueva descripción de ambiente</legend>
					</fieldset>
					<form:form class="form-vertical"
						action="../../private/publications/addRoomDescription"
						method="POST" commandName="addRoomDescriptionForm">
						<form:hidden path="publication" />
						<div class="control-group">
						<label class="control-label" for="select01">Ambiente:</label>
							<div class="controls">
								<form:select path="selected" class="span2">
									<form:options items='${addRoomDescriptionForm.allRooms}' />
								</form:select>
							</div>
							<label class="control-label" for="input01">Largo:</label>
							<div class="controls">
								<form:input type="text" path="length" class="span2" />
								<span class="required"> *</span>
								<p class="error">
									<form:errors path="length" />
								</p>
							</div>
							<label class="control-label" for="input01">Ancho:</label>
							<div class="controls">
								<form:input type="text" path="width" class="span2" />
								<span class="required"> *</span>
								<p class="error">
									<form:errors path="width" />
								</p>
							<span class="required">(*) Campo obligatorio.</span><br>
							</div>
							<br>
							<div class="controls">
								<button type="submit" class="btn btn-primary">Guardar
									Cambios</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<%@ include file="../general/footer.jsp"%>
	</div>
</body>
</html>