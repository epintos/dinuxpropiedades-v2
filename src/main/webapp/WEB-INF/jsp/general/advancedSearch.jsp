<div class="span4" id="search">
	<div class="hero-unit">
		<form:form class="form-vertical"
			action="../../public/publications/search" method="GET"
			commandName="searchForm">
			<fieldset>
				<legend>Búsqueda</legend>
				<div class="control-group">
					<label class="control-label" for="select01">Operación:</label>
					<div class="controls">
						<form:select path="operationType" class="span2">
							<form:option value="" label="Todos" />
							<form:options items='${searchForm.allOperationsType}'/>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="select01">Inmueble:</label>
					<div class="controls">
						<form:select path="propertyType" class="span2">
							<form:option value="" label="Todos" />
							<form:options items='${searchForm.allPropertyType}'/>

						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="input01">Precio (U$D):</label>
					<div class="controls">
						<form:input type="text" path="priceFrom" placeholder="desde"
							class="span2" />
						<p class="error">
							<form:errors path="priceFrom" />
						</p>
						<form:input type="text" path="priceTo" placeholder="hasta"
							class="span2" />
						<p class="error">
							<form:errors path="priceTo" />
						</p>
					</div>

				</div>
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn btn-primary">Buscar</button>
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>