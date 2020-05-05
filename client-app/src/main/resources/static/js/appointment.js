const hospital_path = 'http://localhost:8080/hospitals';
const app_path_id = 'http://localhost:8080/appointments/';
const app_path_pid = 'http://localhost:8080/appointments/patients/';
const app_path_sid = 'http://localhost:8080/appointments/status/';
const session_path = 'http://localhost:8080/doctors/';
const patient_path = 'http://localhost:8080/patients/';
var id;
var typeIcons = [];
typeIcons['info'] = 'icon far fa-info-circle';
typeIcons['success'] = 'icon far fa-check';
typeIcons['warning'] = 'icon far fa-exclamation-triangle';
typeIcons['danger'] = 'icon far fa-ban';

$(document)
		.ready(
				function() {
					getHospitals();
					
					function showNotification(type, msg) {
						$.notify({
							icon : typeIcons[type],
							message : msg

						}, {
							type : type,
							timer : 5000,
							placement : {
								from : 'top',
								align : 'right'
							}
						});
					}
					
					function showProgress() {
					    $('#progress').modal({
					        backdrop: 'static',
					        keyboard: false
					    }).modal('show');
					    $('body').css('overflow-y', 'hidden');
					}

					function hideProgress(e) {
						$('#progress').on('shown.bs.modal', function () {
							  // Load up a new modal...
							  $('#progress').modal('hide')
							})
					}
					
					function showModal(modal) {
						$(modal).modal({
							backdrop : 'static',
							keyboard : false
						}).trigger('focus').modal('show');
					}
					
					function hideModal(modal) {
						$(modal).modal('hide');
					}

					function errorHandler(response, xhr) {
						showNotification('danger',"Problem with server call.<br>Please try again.<br>Technical" + " details: " + xhr.status + ':' + xhr.statusText);
					}

					function getHospitals(){
						showProgress();
						jQuery.ajax({
							url : hospital_path,
							type : 'GET',
							timeout : 5000,
							error :errorHandler,
							success : function(response, status) {
								var arr = [];
								$.each(response, function(index, value) {
									var opt = '<option value = "' + value.id + '">'
											+ value.hospitalName + '</option>';
									arr.push(opt);
								});
								$('#hospitals').append(arr);
								loadDoctors();
								$('#hospitals').selectpicker('refresh');
								hideProgress();
							}
						});
						
					}

					function getDoctors(responseData) {
						return jQuery.ajax({
							url : session_path,
							type : 'GET',
							timeout : 5000,
							error : errorHandler,
							success : function(response, status) {
								responseData(response);
							}
						});
					}
					
					function loadDoctors() {
						showProgress();
						$('#doctors').empty();
						getDoctors(function(response) {
							var arr = [];
							$.each(response, function(index, value) {
								var opt = '<option value = "' + value.id + '">'
										+ value.name + '</option>';
								arr.push(opt);
							});
							$('#doctors').append(arr);
							$('#doctors').selectpicker('refresh');
							loadSessions();
						});
						hideProgress();
					}
					
					function getPatient(responseData, id) {
						return jQuery.ajax({
							url : patient_path + id,
							type : 'GET',
							timeout : 5000,
							error : errorHandler,
							success : function(response, status) {
								responseData(response);
							}
						});
					}

					function getSessions(responseData) {
						showProgress();
						jQuery.ajax({
							url : session_path + $('#doctors option:selected').val() + '/hospitals/sessions',
							type : 'GET',
							timeout : 5000,
							error :errorHandler,
							success : function(response, status) {
								responseData(response);
							}
						});
						hideProgress();
					}
					
					function getSession(responseData,id) {
						jQuery.ajax({
							url : session_path + id +'/hospitals/sessions',
							type : 'GET',
							timeout : 5000,
							error : errorHandler,
							success : function(response, status) {
								responseData(response);
							}
						});
					}
					
					function loadSessions(){
						
						$('#sessions').empty();
						getSessions(function(response) {
							var opt = '<option value = "' + response.id + '">'
									+ response.from +'-'+ response.to + '</option>';
							$('#sessions').append(opt);
							$('#sessions').selectpicker('refresh');
						});
						
					}
					
					function getAppointmentById(responseData) {
						jQuery.ajax({
							url : app_path_id + $('#appointment_id').val(),
							type : 'GET',
							beforeSend : showProgress,
							timeout : 5000,
							error : function(response, xhr) {
								
								errorHandler;
							},
							success : function(response, status) {
								
								responseData(response);
							}
						});
					}
					
					function insertAppointment(){
						if($('#date').val() != ''){
							if($('#insert_pid').val() != ''){
								var appDate = new Date($('#date').val());
								var currDate = new Date();
								if(currDate < appDate){
									var obj = {sessionId: $('#sessions option:selected').val(), patientId: $('#insert_pid').val(), appointmentDate: $('#date').val()};
									jQuery.ajax({
										url : app_path_id,
										type : 'POST',
										data: JSON.stringify(obj),
										contentType: 'application/json',
										timeout : 5000,
										error : errorHandler,
										success : function(response, status) {
											showNotification('success', 'Insert Successful!');
										}
									});
								}
							}
						}
					}
					
					$("#hospitals").change(function() {
						loadDoctors();
					});
					
					$("#doctors").change(function() {
						loadSessions();
					});
					
					$('#make_appointment').click(function() {
						insertAppointment()
					});

					$('#getAppointmentById').click(function() {
						showModal('#appByIdModal');
					});

					$('#find_appointment_by_id').click(function() {
						loadAppointmentsById();
					});

					function loadAppointmentsById() {
						$('#appointment_tb tbody').empty();
						getAppointmentById(function(response) {
							var status;
							switch (response.status) {
							case 1:
								status = 'PENDING';
								break;
							case 2:
								status = 'COMPLETED';
								break;
							case 3:
								status = 'CANCELED';
								break;
							}
							var line;
							if(response.status == 3){ line = '</td></tr>'}
							else{line = '<button class="btn btn-danger btn-sm" id="delete_appointment_btn">Delete</button></td></tr>'}
							var opt = '<tr><td>'
									+ response.id
									+ '</td><td>'
									+ response.patient.name
									+ '</td><td>'
									+ response.session.doctor.name
									+ '</td><td>'
									+ response.session.room.roomNo
									+ '</td><td>'
									+ response.session.from
									+ ' - '
									+ response.session.to
									+ '</td><td>'
									+ response.appointmentDate
									+ '</td><td>'
									+ status
									+ '</td><td>'
									+ '<button class="btn btn-danger btn-sm">Update</button></td><td>'
									+ line;
							$('#appointment_tb tbody').append(opt);
							
						});
					}

					function getAppointmentByPatientId(responseData) {
						jQuery.ajax({
							url : app_path_pid + $('#patient_id').val(),
							type : 'GET',
							timeout : 5000,
							error : errorHandler,
							success : function(response, status) {
								responseData(response);
							}
						});
					}
					
					function loadAppointmentsByPatient(){
						
						$('#appointment_tb tbody').empty();
						getAppointmentByPatientId(function(response_a){
							$.each(response_a, function(index, value){
								getSession(function(response_s){
									getPatient(function(response_p){
										var line;
										if(value.status.id == 3){ line = '</td></tr>'}
										else{line = '<button class="btn btn-danger btn-sm" id="delete_appointment_btn">Delete</button></td></tr>'}
										var opt = '<tr><td>'
											+ value.id
											+ '</td><td>'
											+ response_p.name
											+ '</td><td>'
											+ response_s.doctor.name
											+ '</td><td>'
											+ response_s.roomId
											+ '</td><td>'
											+ response_s.from
											+ ' - '
											+ response_s.to
											+ '</td><td>'
											+ value.appointmentDate
											+ '</td><td>'
											+ value.status.name
											+ '</td><td>'
											+ '<button class="btn btn-danger btn-sm">Update</button></td><td>'
											+ line;
										$('#appointment_tb tbody').append(opt);
									},value.patientId);
								},value.sessionId);
							});
						});
						
					}
					
					$('#getAppointmentByPatient').click(function() {
						showModal('#appByPatientModal')
					});

					$('#find_appointment_by_patient').click(function() {
						loadAppointmentsByPatient();
					});
					
					function getAppointmentByStatus(responseData){
						jQuery.ajax({
							url : app_path_sid + $('#status_op option:selected').val(),
							type : 'GET',
							timeout : 5000,
							error :errorHandler,
							success : function(response, status) {
								responseData(response);
							}
						});
					}
					
					function loadAppointmentsByStatus(){
						
						$('#appointment_tb tbody').empty();
						getAppointmentByStatus(function(response){
							$.each(response, function(index, value){
								var status;
								switch (value.status) {
								case 1:
									status = 'PENDING';
									break;
								case 2:
									status = 'COMPLETED';
									break;
								case 3:
									status = 'CANCELED';
									break;
								}
								var line;
								if(value.status == 3){ line = '</td></tr>'}
								else{line = '<button class="btn btn-danger btn-sm" id="delete_appointment_btn">Delete</button></td></tr>'}
								var opt = '<tr><td><input type="hidden" value = "'+value.id+'" id="id">'
									+ value.id
									+ '</td><td>'
									+ value.patient.name
									+ '</td><td>'
									+ value.session.doctor.name
									+ '</td><td>'
									+ value.session.room.id
									+ '</td><td>'
									+ value.session.from
									+ ' - '
									+ value.session.to
									+ '</td><td>'
									+ value.appointmentDate
									+ '</td><td>'
									+ status
									+ '</td><td>'
									+ '<button class="btn btn-danger btn-sm" id="update_appointment_btn">Update</button></td><td>'
									+ line;
								$('#appointment_tb tbody').append(opt);
							});
						});
						
					}
					
					$('#getAppointmentByStatus').click(function(){
						showModal('#appByStatusModal');
					});
					
					$('#find_appointment_by_status').click(function(){
						loadAppointmentsByStatus();
					});
					
					$('#appointment_tb tbody').on('click', '#update_appointment_btn', function(){
						var item = $(this).closest("tr").find("#id").val();
						id = item;
						showModal('#updateModal');
					});
					
					function updateAppointments(){
						var obj = {status: $('#status_up option:selected').val()};
						jQuery.ajax({
							url : app_path_id + id,
							type : 'PUT',
							data:JSON.stringify(obj),
							contentType: "application/json",
							timeout : 5000,
							error : errorHandler,
							success : function(response, status) {
								if(response.hasOwnProperty('id')){
									showNotification('success', 'Update Successful!');
								}
							}
						});
					}
					
					$('#update_appointment').click(function(){
						
						updateAppointments();
						
					});
					
					function deleteAppointments(){
						jQuery.ajax({
							url : app_path_id + id,
							type : 'DELETE',
							timeout : 5000,
							error : errorHandler,
							success : function(response, status) {
								if(response.hasOwnProperty('id')){
									showNotification('success', 'Delete Successful!');
								}
							}
						});
					}
					
					$('#appointment_tb tbody').on('click', '#delete_appointment_btn', function(){
						var item = $(this).closest("tr").find("#id").val();
						id = item;
						showModal('#deleteModal');
					});
					
					$('#delete_appointment').click(function(){
						
						deleteAppointments();
						location.reload();
					});
				});