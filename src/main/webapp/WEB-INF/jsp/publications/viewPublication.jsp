<%@ include file="../general/headerInfo.jsp"%>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css"
	href="../../../css/viewPublication.css">
<title>Dinux Properties</title>
</head>
<body>
	<div class="container"><%@ include file="../general/header.jsp"%>
		<div class="row" id="content">
			<div class="span10 offset1">
				<div class="hero-unit formHero">
					<div class="row">
						<div class="span4">
							<ul class="thumbnails">
								<li class="span4">
									<div class="behind">
										<c:set var="str" value="RESERVED" />
										<c:if test="${publication.status eq str}">
											<div class="badge"></div>
										</c:if>
										<a href='<c:url value="#"/>' class="thumbnail"> <c:if
												test="${empty publication.mainPhoto}">
												<img class="mainPhoto" src="../../../img/defaultImg.jpg"
													alt="">
											</c:if> <c:if test="${not empty  publication.mainPhoto.id}">
												<img class="mainPhoto"
													src="../../public/publications/viewPhoto?photoId=${publication.mainPhoto.id}&id=${publication.id}"
													alt="">
											</c:if></a>
									</div>
									<p>
										<span class="infoTitle">Descripción:</span>
									</p>
									<p>
										<c:out value="${publication.description}" />
									</p>
								</li>
								<c:set var="map"
									value="http://maps.google.com/maps/api/staticmap?center=${publication.street} ${publication.numbering}, ${publication.neighbourhood}&zoom=14&size=300x268&markers=${publication.street} ${publication.numbering}, ${publication.neighbourhood}&sensor=false" />
								<li class="span4"><a class="thumbnail" target="_blank"
									href="http://mapof.it/${publication.street} ${publication.numbering}, ${publication.neighbourhood}, Argentina/"><img
										src='${map}' alt=""></a></li>
							</ul>
						</div>
						<div class="span4">
							<c:set var="str" value="AGENCY" />
							<c:if test="${publication.user.userType eq str}">
								<div class="span4">
									<ul class="thumbnails">
										<li class="span2"><a href='<c:url value="#"/>'
											class="thumbnail"><c:if
													test="${empty publication.user.photo}">
													<img src="../../../img/agency.jpg" height="230" width="420">
												</c:if> <c:if test="${not empty  publication.user.photo}">
													<img
														src="../../public/user/viewPhoto?id=${publication.user.photo.id}"
														height="230" width="420">
												</c:if> </a></li>
									</ul>
								</div>
								<div class="span4">
									<p>
										<span class="infoTitle">Inmobiliaria:</span>
										<c:out value="${publication.user.name}" />
									</p>
								</div>
							</c:if>
							<div class="span4">
								<p>
									<span class="infoTitle">Operación:</span>
									${publication.operationType.type}
								</p>
								<p>
									<span class="infoTitle">Inmueble:</span>
									${publication.propertyType.type}
								</p>
								<p>
									<span class="infoTitle">Estado:</span>
									${publication.status.status}
								</p>
								<p>
									<span class="infoTitle">Dirección:</span>
									<c:out value="${publication.street}" />
									<c:out value="${publication.numbering}" />
									<c:if test="${not empty publication.floor}">
										<span class="infoTitle">Piso:</span>
										<c:out value="${publication.floor}" />
										<br>
										<span class="infoTitle">Dep</span>: <c:out
											value="${publication.apartment}" />
									</c:if>
								</p>
								<p>
									<span class="infoTitle">Barrio:</span>
									<c:out value="${publication.neighbourhood}" />
								</p>
								<p>
									<span class="infoTitle">Superficie Cubierta:</span>
									<c:out value="${publication.coveredSurface}" />
									m2
								</p>
								<p>
									<span class="infoTitle">Superficie Descubierta:</span>
									<c:out value="${publication.uncoveredSurface}" />
									m2
								</p>
								<p>
									<span class="infoTitle">Antigüedad:</span>
									<c:out value="${publication.age}" />
									año/s
								</p>
								<p>
									<span class="infoTitle">Cantidad de Ambientes:</span>
									<c:out value="${publication.roomsQty}" />
								</p>
								<c:if test="${empty publication.rooms}">
									<p>
										<span class="infoTitle">Ambientes:</span> No posee.
									</p>
								</c:if>
								<c:if test="${not empty publication.rooms}">
									<p>
										<span class="infoTitle">Ambientes:</span>
									</p>
									<ul>
										<c:forEach var="room" items="${publication.rooms}">
											<li><c:out value="${room.type.description}" />: <c:out
													value="${room.length}" />m x <c:out value="${room.width}" />m</li>
										</c:forEach>
									</ul>
								</c:if>
								<c:if test="${empty publication.services }">
									<p>
										<span class="infoTitle">Servicios:</span> No posee.
									</p>
								</c:if>
								<c:if test="${not empty publication.services}">
									<p>
										<span class="infoTitle">Servicios:</span>
									</p>
									<ul>
										<c:forEach var="service" items="${publication.services}">
											<li><c:out value="${service.name}" /></li>
										</c:forEach>
									</ul>
								</c:if>

								<p>
									<span class="infoTitle">Precio:</span> U$D
									<c:out value="${publication.price}" />
								</p>
								<p>
									Esta publicación ha sido visitada
									<c:out value="${publication.visitCount}" />
									veces.
								</p>
							</div>
						</div>
						<c:if
							test="${ not empty sessionManager.user.id && publication.user.id == sessionManager.user.id}">
							<div class="span9">
								<c:set var="addRoomDesc"
									value="../../private/publications/addRoomDescription?id=${publication.id}"
									scope="request" />
								<i class="icon-plus-sign icon-white"></i> <a
									href="<c:url value='${addRoomDesc}'/>">Agregar descripción
									para algún ambiente</a>
							</div>
						</c:if>

						<div class="span9">

							<fieldset>
								<legend class="formTitle">Fotos</legend>
							</fieldset>
							<c:if test="${empty publication.photos}">
								<p>No hay más fotos cargadas.</p>
							</c:if>
							<ul class="photos">
								<c:forEach var="photo" items="${publication.photos}">
									<li class="span2"><a
										href="../../public/publications/viewPhoto?photoId=${photo.id}&id=${publication.id}"
										target="_blank" class="thumbnail"> <img
											src="../../public/publications/viewPhoto?photoId=${photo.id}&id=${publication.id}"
											alt="">
									</a></li>
								</c:forEach>
							</ul>
						</div>
						<c:if
							test="${ not empty sessionManager.user.id && publication.user.id == sessionManager.user.id}">
							<div class="span9">
								<c:set var="editPhotos"
									value="../../private/publications/editPhotos?id=${publication.id}"
									scope="request" />
								<i class=" icon-edit icon-white"></i> <a
									href="<c:url value='${editPhotos}'/>">Editar Fotos</a>
							</div>
						</c:if>
						<c:if
							test="${( not empty sessionManager.user.id && publication.user.id != sessionManager.user.id) || empty sessionManager.user.id }">
							<div class="span9">
								<c:set var="viewPublications"
									value="../../public/publications/list?id=${publication.user.id}"
									scope="request" />
								<i class="icon-list-alt icon-white"></i> <a
									href="<c:url value='${viewPublications}'/>">Ver
									publicaciones del anunciante</a>
							</div>
							<div class="span9">
								<form:form class="form-vertical"
									action="addComment?id=${publication.id}" method="post"
									commandName="commentForm">
									<fieldset>
										<legend class="formTitle">¿Estás interesado?</legend>

										<div class="row formRow">
											<c:if test="${empty sessionManager.user}">
												<div class="span3">
													<div class="control-group">
														<label class="control-label" for="input01">Nombre</label>
														<div class="controls">
															<form:input type="text" path="name" class="span2" />
															<span class="required"> *</span>
															<p class="error">
																&nbsp
																<form:errors path="name" />
															</p>
														</div>
													</div>
												</div>
												<div class="span3">
													<div class="control-group">
														<label class="control-label" for="input01">Email</label>
														<div class="controls">
															<form:input type="text" path="email" class="span2"
																placeholder="Ej: a@mail.com" />
															<span class="required"> *</span>
															<p class="error">
																&nbsp
																<form:errors path="email" />
															</p>
														</div>
													</div>
												</div>
												<div class="span3">
													<div class="control-group">
														<label class="control-label" for="input01">Teléfono</label>
														<div class="controls">
															<form:input type="text" path="phone" class="span2" />
															<span class="required"> *</span>
															<p class="error">
																&nbsp
																<form:errors path="phone" />
															</p>
														</div>
													</div>
												</div>
											</c:if>
											<c:if test="${not empty sessionManager.user}">
												<form:input type="hidden"
													value="${sessionManager.user.name}" path="name" />
												<form:input type="hidden"
													value="${sessionManager.user.email}" path="email" />
												<form:input type="hidden"
													value="${sessionManager.user.phone}" path="phone" />
											</c:if>
											<div class="span7">
												<div class="control-group">
													<label class="control-label" for="input01">Comentario</label>
													<div class="controls">
														<form:textarea class="input x-large" id="description"
															rows="3" cols="9" path="comment"></form:textarea>
														<p class="error">
															&nbsp
															<form:errors path="comment" />
														</p>
														<form:input type="hidden" value="${publication.id}"
															path="publicationId" />
													</div>
													<button type="submit" class="btn btn-primary">Enviar</button>

													<span class="required">(*) Campos obligatorios.</span>
												</div>
											</div>
										</div>
									</fieldset>
								</form:form>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../general/footer.jsp"%>
	</div>

</body>
</html>