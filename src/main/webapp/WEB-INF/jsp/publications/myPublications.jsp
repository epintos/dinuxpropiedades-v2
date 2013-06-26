<%@ include file="../general/headerInfo.jsp"%>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css"
	href="../../../css/searchResults.css">
<title>Dinux Properties</title>
</head>
<body>
	<div class="container">
		<%@ include file="../general/header.jsp"%>
		<div class="row" id="content">
			<%@ include file="../general/advancedSearch.jsp"%>

			<div class="span8">
				<c:if test="${empty publicationsList}">
					<p>No se encontraron resultados.</p>
				</c:if>
				<c:forEach var="publication" items="${publicationsList}">
					<div class="row">
						<div class="span8">

							<div class="hero-unit searchItem">
								<div class="row">
									<div class="span2">
										<ul class="thumbnails">
											<li class="span2">
												<div class="behind">
													<c:set var="str" value="RESERVED" />
													<c:if test="${publication.status eq str}">
														<div class="badge"></div>
													</c:if>
													<a href='<c:url value="#"/>' class="thumbnail"><c:if
															test="${empty publication.mainPhoto}">
															<img class="mainPhoto" src="../../../img/defaultImg.jpg"
																alt="">
														</c:if> <c:if test="${not empty  publication.mainPhoto.id}">
															<img class="mainPhoto"
																src="../../public/publications/viewPhoto?photoId=${publication.mainPhoto.id}&id=${publication.id}"
																alt="">
														</c:if> </a>
												</div>
											</li>
										</ul>
									</div>
									<div class="span4">
										<span class="infoTitle">Estado:</span>
										<c:set var="str" value="AVAILABLE" />
										<c:out value="${publication.status.status}"/>
										<br> <span class="infoTitle">Operación:</span>
										<c:out value="${publication.operationType.type}"/>
										<br> <span class="infoTitle">Inmueble:</span>
										<c:out value="${publication.propertyType.type}"/>
										<br> <span class="infoTitle">Dirección:</span>
										<c:out value="${publication.street}" />
										<c:out value="${publication.numbering}" />

										<c:if test="${not empty publication.floor}">
											<span class="infoTitle">Piso:</span>
											<c:out value="${publication.floor}" />
											<span class="infoTitle">Dep:</span>
											<c:out value="${publication.apartment}" />
										</c:if>

										<br> <span class="infoTitle">Barrio:</span>
										<c:out value="${publication.neighbourhood}" />
										<br> <span class="infoTitle">Precio:</span> U$D
										<c:out value="${publication.price}" />


										<c:set var="detail_information"
											value="../../public/publications/detail?id=${publication.id}"
											scope="request" />
										<br> <i class="icon-plus-sign"></i> <a
											href="<c:url value='${detail_information}'/>">Más
											Información</a> <br>
										<c:if test="${isPrivate eq true }">
											<c:set var="edit_publication"
												value="../../private/publications/edit?id=${publication.id}"
												scope="request" />
											<i class="icon-edit"></i>
											<a href="<c:url value='${edit_publication}'/>">Editar
												Información</a>
											<br>
											<c:set var="editPhotos"
												value="../../private/publications/editPhotos?id=${publication.id}"
												scope="request" />
											<i class=" icon-picture"></i>
											<a href="<c:url value='${editPhotos}'/>">Editar Fotos</a>
											<c:set var="finalize"
												value="modifyStatus?id=${publication.id}&status=Finished"
												scope="request" />
											<c:set var="enable"
												value="modifyStatus?id=${publication.id}&status=Available"
												scope="request" />
											<c:set var="reserve"
												value="modifyStatus?id=${publication.id}&status=Reserved"
												scope="request" />
											<br>
											<c:set var="str" value="AVAILABLE" />
											<c:if test="${publication.status eq str}">
												<i class="icon-edit"></i>
												<a href="<c:url value='${reserve}'/>">Reservar</a>
												<br>
												<i class="icon-check"></i>
												<a href="<c:url value='${finalize}'/>">Finalizar</a>
											</c:if>
											<c:set var="str" value="FINISHED" />
											<c:if test="${publication.status eq str}">

												<i class="icon-share"></i>
												<a href="<c:url value='${enable}'/>">Anunciar</a>
												<br>
												<i class="icon-edit"></i>
												<a href="<c:url value='${reserve}'/>">Reservar</a>
											</c:if>
											<c:set var="str" value="RESERVED" />
											<c:if test="${publication.status eq str}">

												<i class="icon-share"></i>
												<a href="<c:url value='${enable}'/>">Anunciar</a>
												<br>
												<i class="icon-check"></i>
												<a href="<c:url value='${finalize}'/>">Finalizar</a>
											</c:if>
										</c:if>
									</div>
								</div>

							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

		<%@ include file="../general/footer.jsp"%>
	</div>

</body>
</html>