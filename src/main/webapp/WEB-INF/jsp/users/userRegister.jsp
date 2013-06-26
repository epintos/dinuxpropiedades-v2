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
					<form:form class="form-vertical" action="userRegister" method="POST"
						commandName="userRegisterForm">
						<fieldset>
							<legend id="formTitle">Regístrate</legend>
							<div class="row formRow">
								<div class="span3 offset1">
									<div class="control-group">
										<label class="control-label" for="input01">Nombre:</label>
										<div class="controls">
											<form:input type="text" path="name" class="span2" />
											<span class="required"> *</span>
											<p class="error">
												&nbsp
												<form:errors path="name" />
											</p>
										</div>

									</div>
									<div class="control-group">
										<label class="control-label" for="input01">Apelido:</label>
										<div class="controls">
											<form:input type="text" path="surname" class="span2" />
											<span class="required"> *</span>
											<p class="error">
												&nbsp
												<form:errors path="surname" />
											</p>
										</div>

									</div>
									<div class="control-group">
										<label class="control-label" for="input01">Email:</label>
										<div class="controls">
											<form:input type="text" path="email" class="span2"
												placeholder="Ej: user@domain.com" />
											<span class="required"> *</span>
											<p class="error">
												&nbsp
												<form:errors path="email" />
											</p>
										</div>

									</div>
									<div class="control-group">
										<label class="control-label" for="input01">Teléfono:</label>
										<div class="controls">
											<form:input type="text" path="phone" class="span2"
											 />
											<span class="required"> *</span>
											<p class="error">
												&nbsp
												<form:errors path="phone" />
											</p>
										</div>
										<span class="required">(*) Campo obligatorio.</span>
									</div>
								</div>
								<div class="span3 offset1">
									<div class="control-group">
										<label class="control-label" for="input01">Nombre de
											Usuario:</label>
										<div class="controls">
											<form:input type="text" path="username" class="span2" />
											<span class="required"> *</span>
											<p class="error">
												&nbsp
												<form:errors path="username" />
											</p>
										</div>

									</div>
									<div class="control-group">
										<label class="control-label" for="input01">Contraseña:</label>
										<div class="controls">
											<form:input type="password" path="password" class="span2" />
											<span class="required"> *</span>
											<p class="error">
												&nbsp
												<form:errors path="password" />
											</p>
										</div>

									</div>
									<div class="control-group">
										<label class="control-label" for="input01">Confirme
											Contraseña:</label>
										<div class="controls">
											<form:input type="password" path="passwordConfirm"
												class="span2" />
											<span class="required"> *</span>
											<p class="error">
												&nbsp
												<form:errors path="passwordConfirm" />
											</p>
										</div>

									</div>
									<div class="control-group">
										<div class="controls">
											<button type="submit" class="btn btn-primary">Finalizar</button>
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