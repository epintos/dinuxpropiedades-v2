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
						<legend class="formTitle">Agregar Foto</legend>
					</fieldset>
					<form:form class="form-vertical" enctype="multipart/form-data"
						action="../../private/publications/addPhoto?id=${id}"
						method="POST" commandName="newPhotoForm">
						<div class="control-group">
							<div class="controls">
								<form:input id="photoInput" class="span3" type="file"
									name="photo" accept="image/*" path="file" />
							</div>
							<div class="controls">
								<button type="submit" class="btn btn-primary">Guardar
									Cambios</button>
								<p class="error">
									<c:out value="${MissingInformationError}" />
								</p>
								<p class="error">
									<c:out value="${BadInformationError}" />
								</p>
							</div>
						</div>
					</form:form>
					<fieldset>
						<legend class="formTitle">Eliminar Fotos</legend>
					</fieldset>
					<form:form class="form-horizontal"
						action="../../private/publications/deletePhoto?id=${id}"
						method="POST" commandName="deletePhotoForm">
						<div class="control-group">
							<c:if test="${empty photos}">
								<p>No hay más fotos cargadas.</p>
							</c:if>
							<ul class="photos">
								<c:forEach var="photo" items="${photos}">
									<li class="span2"><img
										src="../../public/publications/viewPhoto?photoId=${photo.id}&id=${id}"
										alt="" width="200" height="150"> <form:input
											type="checkbox" name="photoId=${photo}" path="photoId"
											value='${photo.id}' /></li>
								</c:forEach>
							</ul>
						</div>
						<div class="controls" id="deletePhotos">
							<button type="submit" class="btn btn-primary" name="deletePhotos">Eliminar
								Fotos</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<%@ include file="../general/footer.jsp"%>
	</div>
</body>
</html>