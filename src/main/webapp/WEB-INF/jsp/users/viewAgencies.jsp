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
				<c:if test="${empty agenciesList}">
					<p>No se encontraron resultados.</p>
				</c:if>
				<c:forEach var="agency" items="${agenciesList}">
					<div class="row">
						<div class="span8">

							<div class="hero-unit searchItem">
								<div class="row">
									<div class="span2">
										<ul class="thumbnails">
											<li class="span2"><a href='<c:url value="#"/>'
												class="thumbnail"><c:if test="${empty agency.photo}">
														<img class="mainPhoto" src="../../../img/agency.jpg"
															alt="">
													</c:if> <c:if test="${not empty  agency.photo.id}">
														<img class="mainPhoto"
															src="../../public/user/viewPhoto?id=${agency.photo.id}"
															alt="">
													</c:if> </a></li>
										</ul>
									</div>
									<div class="span4">
										<span class="infoTitle">Nombre:</span>
										<c:out value="${agency.name}" />
										<br> <span class="infoTitle">Publicaciones:</span>
										<c:out value="${agency.count}" />

										<c:set var="agency_publications"
											value="../../public/publications/list?id=${agency.id}"
											scope="request" />
										<br> <i class="icon-plus-sign"></i> <a
											href="<c:url value='${agency_publications}'/>">Ver
											Publicaciones</a> <br>

									</div>
									<div class="span3"></div>
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