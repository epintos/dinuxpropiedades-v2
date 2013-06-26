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

		<div class="row" id="content"><%@ include
				file="../general/advancedSearch.jsp"%>
			<div class="span8">
				<div class="btn-toolbar">
					<div class="btn-group">
						<c:set var="aux"
							value="../publications/search?operationType=${searchForm.operationType}&propertyType=${searchForm.propertyType}&priceFrom=${searchForm.priceFrom}&priceTo=${searchForm.priceTo}&order=ASC"
							scope="request" />
						<c:set var="aux1"
							value="../publications/search?operationType=${searchForm.operationType}&propertyType=${searchForm.propertyType}&priceFrom=${searchForm.priceFrom}&priceTo=${searchForm.priceTo}&order=DES"
							scope="request" />
						<a href="<c:url value="${aux}"/>">
							<button class="btn">Precio Ascendente</button>
						</a> <a href="<c:url value="${aux1}"/>">
							<button class="btn">Precio Descendente</button>
						</a>
					</div>
				</div>
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
														</c:if> <c:if test="${not empty  publication.mainPhoto}">
															<img class="mainPhoto"
																src="../../public/publications/viewPhoto?photoId=${publication.mainPhoto.id}"
																alt="">
														</c:if> </a>
												</div>
											</li>
										</ul>
									</div>
									<div class="span4">
										<span class="infoTitle">Estado:</span>
										${publication.status.status} <br> <span class="infoTitle">Operación:</span>
										<c:out value="${publication.operationType.type}" />
										<br> <span class="infoTitle">Inmueble:</span>
										<c:out value="${publication.propertyType.type}" />
										<br> <span class="infoTitle">Dirección:</span>
										<c:out value="${publication.street}" />
										<c:out value="${publication.numbering}" />
										<c:if test="${not empty publication.floor}">
											Piso: <c:out value="${publication.floor}" />
											Dep: <c:out value="${publication.apartment}" />
										</c:if>
										<br> <span class="infoTitle">Barrio:</span>
										<c:out value="${publication.neighbourhood}" />
										<br> <span class="infoTitle">Precio:</span> U$D
										<c:out value="${publication.price}" />
										<c:set var="aux" value="detail?id=${publication.id}"
											scope="request" />
										<br> <i class="icon-plus-sign"></i> <a
											href="<c:url value='${aux}'/>">Más Información</a>
									</div>
									<div class="span3"></div>
								</div>

							</div>
						</div>
					</div>
				</c:forEach>
				<div>
					<div class="pageNumbers left">
						<a
							href="../publications/search?operationType=${searchForm.operationType}&propertyType=${searchForm.propertyType}&priceFrom=${searchForm.priceFrom}&priceTo=${searchForm.priceTo}&order=${searchForm.order}&page=${searchForm.page - 1}">Anterior</a>
					</div>
					<div class="pageNumbers">
						<a
							href="../publications/search?operationType=${searchForm.operationType}&propertyType=${searchForm.propertyType}&priceFrom=${searchForm.priceFrom}&priceTo=${searchForm.priceTo}&order=${searchForm.order}&page=${searchForm.page}"><c:out
								value="${searchForm.page}"></c:out></a>
					</div>
					<c:if test="${not empty publicationsList && nextPage eq true}">
						<div class="pageNumbers right">
							<a
								href="../publications/search?operationType=${searchForm.operationType}&propertyType=${searchForm.propertyType}&priceFrom=${searchForm.priceFrom}&priceTo=${searchForm.priceTo}&order=${searchForm.order}&page=${searchForm.page + 1}">Siguiente</a>
						</div>
					</c:if>
				</div>
			</div>
		</div>

		<%@ include file="../general/footer.jsp"%>
	</div>
</body>
</html>