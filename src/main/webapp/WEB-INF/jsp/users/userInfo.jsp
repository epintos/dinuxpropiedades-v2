<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../general/headerInfo.jsp"%>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css"
	href="../../../css/viewPublication.css">
<title>Dinux Propiedades</title>
</head>
<body>
	<div class="container">
		<%@ include file="../general/header.jsp"%>
		<div class="row" id="content">
			<div class="span10 offset1">
				<div class="hero-unit formHero">
					<fieldset>
						<legend class="formTitle">Datos del Publicador</legend>
					</fieldset>
					<div class="row">
						<div class="span2">
							<ul class="thumbnails">
								<li class="span2"><a href='<c:url value="#"/>'
									class="thumbnail"> <c:if test="${empty user.photo}">
											<c:set var="str" value="AGENCY" />
											<c:if test="${user.userType eq str}">
												<img src="../../../img/agency.jpg" height="230" width="420">
											</c:if>
											<c:set var="str" value="USER" />
											<c:if test="${user.userType eq str}">
												<img src="../../../img/User.png" height="230" width="420">
											</c:if>
										</c:if> <c:if test="${not empty  user.photo}">
											<img src="../../public/user/viewPhoto?id=${user.photo.id}"
												height="230" width="420">
										</c:if>
								</a></li>
							</ul>
						</div>
						<div class="span4">
							<p>
								<span class="infoTitle">Email:</span>
								<c:out value="${user.email}" />
							</p>
							<p>
								<span class="infoTitle">Teléfono:</span>
								<c:out value="${user.phone}" />
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../general/footer.jsp"%>
	</div>
</body>
</html>