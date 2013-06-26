<%@ include file="../general/headerInfo.jsp"%>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css"
	href="../../../css/newPublication.css">
<title>Dinux Propiedades</title>
</head>
<body>
	<div class="container"><%@ include file="../general/header.jsp"%>
		<div class="row" id="content">
			<div class="span10 offset1">
				<div class="hero-unit formHero">
					<form:form class="form-vertical" action="edit" method="POST"
						commandName="publicationForm">
						<fieldset>
							<legend>Formulario de Publicación</legend>
							<div class="row formRow">
								<div class="span4">
									<form:hidden path="publication" />
									<div class="control-group">
										<label class="control-label formItem" for="select01">Inmueble:
											<span class="required"> *</span>
										</label>
										<div class="controls">
											<form:select path="propertyType">
												<form:options items='${publicationForm.allPropertyType}' />
											</form:select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label formItem" for="select01">Operación:
											<span class="required"> *</span>
										</label>
										<div class="controls">
											<form:select path="operationType">
												<form:options items='${publicationForm.allOperationsType}' />
											</form:select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label formItem" for="input01">Dirección:</label>
										<div class="controls docs-input-sizes">
											<form:input class="span3" type="text" path="street"
												placeholder="Calle" />
											<span class="required"> * </span>
											<p class="error">
												&nbsp
												<form:errors path="street" />
											</p>
											<form:input class="span3" type="text" path="numbering"
												placeholder="Número" />
											<span class="required"> *</span>
											<p class="error">
												&nbsp
												<form:errors path="numbering" />
											</p>
											<c:if test="${not empty publication.floor}">
												<form:input class="span3" type="text" path="floor"
													placeholder="Piso" />
												<span class="required"> **</span>
												<p class="error">
													&nbsp
													<form:errors path="floor" />
												</p>
											</c:if>
											<c:if test="${empty publication.floor}">
												<form:input class="span3" type="text" path="floor"
													placeholder="Piso" />
												<span class="required"> **</span>
												<p class="error">
													&nbsp
													<form:errors path="floor" />
												</p>
											</c:if>
											<form:input class="span3" type="text" path="apartment"
												placeholder="Departamento" />
											<span class="required"> **</span>
											<p class="error">
												&nbsp
												<form:errors path="apartment" />
											</p>
											<form:input class="span3" type="text" path="neighbourhood"
												placeholder="Barrio" />
											<span class="required"> *</span>
											<p class="error">
												&nbsp
												<form:errors path="neighbourhood" />
											</p>

										</div>
									</div>
									<div class="control-group">
										<label class="control-label formItem" for="input01">Precio
											(U$D):</label>
										<div class="controls">
											<form:input class="span3" type="text" path="price" />
											<span class="required"> *</span>
											<p class="error">
												&nbsp
												<form:errors path="price" />
											</p>

										</div>
									</div>
									<div class="control-group">
										<label class="control-label formItem" for="input01">Cantidad
											de ambientes:</label>
										<div class="controls">
											<form:input class="span3" type="text" path="roomsQty" />
											<span class="required"> *</span>
											<p class="error">
												<form:errors path="roomsQty" />
											</p>
											<span class="required">Los mismos se editan al ver el
												detalle de la publicación.</span><br>

										</div>
										<br> <span class="required">(*) Campo obligatorio.</span><br>
										<span class="required">(**) Campo obligatorio para
											departamentos.</span>
									</div>
								</div>
								<div class="span4">
									<div class="control-group">
										<label class="control-label formItem" for="input01">Antigüedad
											(año/s):</label>
										<div class="controls">
											<form:input class="span3" type="text" path="age" />
											<span class="required"> *</span>
											<p class="error">
												&nbsp
												<form:errors path="age" />
											</p>

										</div>
									</div>
									<div class="control-group">
										<label class="control-label formItem" for="input01">Superficie
											cubierta (m2):</label>
										<div class="controls">
											<form:input class="span3" type="text" path="coveredSurface" />
											<span class="required"> *</span>
											<p class="error">
												&nbsp
												<form:errors path="coveredSurface" />
											</p>

										</div>
									</div>
									<div class="control-group">
										<label class="control-label formItem" for="input01">Superficie
											descubierta (m2):</label>
										<div class="controls">
											<form:input class="span3" type="text" path="uncoveredSurface" />
											<span class="required"> *</span>
											<p class="error">
												&nbsp
												<form:errors path="uncoveredSurface" />
											</p>

										</div>
									</div>

									<div class="control-group">
										<label class="control-label formItem" for="input01">Servicios:</label>
										<div class="controls" id="services">
											<form:checkboxes items="${publicationForm.allServices}"
												itemLabel="name" path="services" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label formItem" for="input01">Descripción:</label>
										<div class="controls">
											<form:textarea class="input x-large" id="description"
												rows="3" path="description"></form:textarea>
											<p class="error">
												&nbsp
												<form:errors path="description" />
											</p>
										</div>
									</div>
									<div class="control-group">
										<div class="controls">
											<button type="submit" class="btn btn-primary">Guardar
												Cambios</button>
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