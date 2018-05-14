$(function() {
	$("#Login").click(function() {
		var dispatchNum = $("#dispatchNum").val();
		var password = $("#password").val();
		var phonenum = $("#phonenumber").val();
		var serverAddr = $("#serverAddr").val();
		var isAutoanswer;
		if ($("#AutoAnswer").attr("checked")) {
			isAutoanswer = true;
		} else {
			isAutoanswer = false;
		}
		if (dispatchNum == "" || phonenum == "") {
			writeLog("[The parameter is empty.]");
			return;
		}

		$("#status").html("logging...");
		$("#status").css('color', 'blank');

		login(dispatchNum, password, phonenum, isAutoanswer);
	});

	$("#registerSoftPhone").click(function() {
		var value = $("#registerSoftPhone").val();
		if("软电话注册" == value)
		{
			voiceLogin();
		}
		else
		{
			voiceLogout()
		}
		
	});
	
	$("#registerCDS").click(function() {
		var isRegister = $(this).attr("isRegister");
		register(global_dispatchNum, isRegister);
	});

	$("#unRegisterCDS").click(function() {
		var isRegister = $(this).attr("isRegister");
		register(global_dispatchNum, isRegister);
	});

	$("#Logout").click(function() {
		var dispatchNum = $("#dispatchNum").val();
		if (dispatchNum == "") {
			return;
		}
		logout(dispatchNum);
	});

	$("#downloadFile").click(function() {
		var url = global_UportalWebBaseAddr + "/CDSDemo/download.do?fileName=emergencyNote.wav";
		window.location.href = url;
	});
	
	// Clear Log Button
	$("#clearLog").click(function() {
		$("#LogInfo").val("");
	});

	// Clear chat record Button
	$("#textChatClear").click(function() {
		$("#ChatTextBox").val("");
	});

	$("#callbackButton").click(function() {
		var res = prompt("event");
		userStateNotify(res);
	});

	$("#tb_voice_controller input").click(
			function() {
				var submitType = $("#tb_voice_controller_form").attr(
						"submitType");

				if (submitType == $(this).attr("submitType")) {
					$("#tb_voice_controller_form").css("display", "none");
					$("#tb_voice_controller_form tbody").html();
					$("#tb_voice_controller_form").attr("submitType", "");
					return;
				}

				$("#tb_voice_controller_form").css("display", "block");
				var submitType = $(this).attr("submitType");
				$("#tb_voice_controller_form h4").html(
						FromConfig[submitType].opName);
				$("#tb_voice_controller_form").attr("submitType", submitType);

				var html = "";

				$.each(FromConfig[submitType].fileds, function(index, item) {
					if (item.filedId == "radio" || item.filedId == "radios") {
						
						var radioValue = item.defaultValue == undefined ? ""
								: "checked";
						
						html += "<tr>" + "<td>" + item.filedName + "</td>"
								+ '<td><input type="radio" value="" '+ radioValue + ' name="'
								+ item.filedId + '"/></td>' + "</tr>";
					} else {
						var defaultValue = item.defaultValue == undefined ? ""
								: item.defaultValue;
						
						html += "<tr>" + "<td>" + item.filedName + "</td>"
								+ '<td><input type="text" value="'
								+ defaultValue + '" name="' + item.filedId
								+ '"/></td>' + "</tr>";
					}
				});

				$("#tb_voice_controller_form tbody").html(html);
			});

	$('#tb_voice_controller_form input[type="submit"]').click(function() {
		var submitType = $("#tb_voice_controller_form").attr("submitType");

		FromConfig[submitType].callback();
	});

	$("#tb_power_controller input").click(
			function() {
				var submitType = $("#tb_power_controller_form").attr(
						"submitType");

				if (submitType == $(this).attr("submitType")) {
					$("#tb_power_controller_form").css("display", "none");
					$("#tb_power_controller_form tbody").html();
					$("#tb_power_controller_form").attr("submitType", "");
					return;
				}

				$("#tb_power_controller_form").css("display", "block");
				var submitType = $(this).attr("submitType");
				$("#tb_power_controller_form h4").html(
						FromConfig[submitType].opName);
				$("#tb_power_controller_form").attr("submitType", submitType);

				var html = "";

				$.each(FromConfig[submitType].fileds, function(index, item) {
					
					html += "<tr>" + "<td>" + item.filedName + "</td>"
							+ '<td><input type="text" value="" filed="'
							+ item.filedId + '"/></td>' + "</tr>";
				});

				$("#tb_power_controller_form tbody").html(html);
			});

	$('#tb_power_controller_form input[type="submit"]').click(function() {
		var submitType = $("#tb_power_controller_form").attr("submitType");

		FromConfig[submitType].callback();
	});

	$("#tb_config input").click(
			function() {
				var submitType = $("#tb_config_form").attr("submitType");
				
				//当已经显示当前接口的参数输入界面时，再次点击按钮隐藏该接口的输入界面
				if (submitType == $(this).attr("submitType")) {
					$("#tb_config_form").css("display", "none");
					$("#tb_config_form tbody").html();
					$("#tb_config_form").attr("submitType", "");
					return;
				}
				
				//显示当前接口的信息到界面，并将当前input元素的submitType属性赋值给table的submitType属性
				$("#tb_config_form").css("display", "block");
				var submitType = $(this).attr("submitType");
				$("#tb_config_form h4").html(FromConfig[submitType].opName);
				$("#tb_config_form").attr("submitType", submitType);

				var html = "";
				$.each(FromConfig[submitType].fileds, function(index, item) {
					
					var defaultValue = item.defaultValue == undefined ? ""
							: item.defaultValue;
					
						html += "<tr>" + "<td>" + item.filedName + "</td>"
						+ '<td><input type="text" value="'
						+ defaultValue + '" name="' + item.filedId
						+ '"/></td>' + "</tr>";
					
				});

				$("#tb_config_form tbody").html(html);
			});

	$('#tb_config_form input[type="submit"]').click(function() {
		var submitType = $("#tb_config_form").attr("submitType");

		FromConfig[submitType].callback();
	});

	$("#tb_call_controller input").click(
			function() {
				var submitType = $("#tb_call_controller_form").attr(
						"submitType");

				if (submitType == $(this).attr("submitType")) {
					$("#tb_call_controller_form").css("display", "none");
					$("#tb_call_controller_form tbody").html();
					$("#tb_call_controller_form").attr("submitType", "");
					return;
				}

				$("#tb_call_controller_form").css("display", "block");
				var submitType = $(this).attr("submitType");
				$("#tb_call_controller_form h4").html(
						FromConfig[submitType].opName);
				$("#tb_call_controller_form").attr("submitType", submitType);

				var html = "";

				$.each(FromConfig[submitType].fileds, function(index, item) {
					html += "<tr>" + "<td>" + item.filedName + "</td>"
							+ '<td><input type="text" value="" filed="'
							+ item.filedId + '"/></td>' + "</tr>";
				});

				$("#tb_call_controller_form tbody").html(html);
			});

	$('#tb_call_controller_form input[type="submit"]').click(function() {
		var submitType = $("#tb_call_controller_form").attr("submitType");

		FromConfig[submitType].callback();
	});
});

function userStateNotify(res) {
	$.post(global_UportalWebBaseAddr + "/CDSDemo/callback", res,
			function(data) {
				writeLog("[CallBack]" + data);
			})
}

/*
 * login to uportal
 */
function login(dispatchNum, password, phonenum, isAutoanswer) {
	$.post(global_UportalWebBaseAddr + "/CDSDemo/loginToUportal.do", {
		userId : dispatchNum,
		password : password,
		userNumber : phonenum
	}, function(data) {
		writeLog("[Login]" + data);
		var retcode = (JSON.parse(data)).retcode.toString();
		switch (retcode) {
		case global_resultCode_SUCCESSCODE:
			global_dispatchNum = $("#dispatchNum").val();
			setTimeout('getEventLisnter()', 500);// Start a thread to get

			$("#status").html("login");
			$("#status").css('color', 'red');

			global_ws = createWebSocket(dispatchNum);
			break;
		case global_resultCode_REPEAT:
			alert("Error:" + retcode + ", Description:"
					+ (JSON.parse(data)).retDesc);
		
			$("#status").html("login");
			break;
		default:
			alert("Error:" + retcode + ", Description:"
					+ (JSON.parse(data)).retDesc);
		
			$("#status").html("Not login");
			$("#status").css('color', 'blank');
		}
	});
}

/**
 * logout from uportal
 * 
 * @param dispatchNum
 */
function logout(dispatchNum) {
	$.ajax(global_UportalWebBaseAddr + "/CDSDemo/logoutFromUportal.do",
			{
				type : "POST",
				data : {
					userId : dispatchNum
				},
				success : function(data, textStatus) {
					writeLog("[Logout]" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						$("#status").html("Not login");
						$("#status").css('color', 'black');
						global_dispatch = "";
						if ($("#voicePhone").attr("checked")) {
							voiceLogout(); // Phone logout
						}
						disconnect(global_ws);
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
}

function heartbeats() {
	$.ajax(global_UportalWebBaseAddr + "/CDSDemo/heartbeat.do",
			{
				type : "POST",
				data : {
					userId : global_dispatchNum,
					sessionID : global_session_id
				},
				success : function(data, textStatus) {
					writeLog("[Heartbeats]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
						$("#registerCDS").val("CDS服务注册");
						$("#registerCDS").attr("isRegister", true);

						register(global_dispatchNum, false);
					}
				}
			});
}

function register(dispatchNum, isRegister) {
	$.ajax(global_UportalWebBaseAddr + "/CDSDemo/register.do",
			{
				type : "POST",
				data : {
					userId : dispatchNum,
					isRegister : isRegister
				},
				success : function(data, textStatus) {
					writeLog("[Register]ret=" + data + ", isRegister="
							+ isRegister);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						if (isRegister == "true") {
							global_session_id = data.data.newSessionID;

							if (global_heartbeat_handler == null) {
								global_heartbeat_handler = setInterval(
										heartbeats, 60000);
							}

						} else {
							if (global_heartbeat_handler != null) {
								clearInterval(global_heartbeat_handler);
								global_heartbeat_handler = null;
							}

							global_session_id = null;
						}

						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);

						if (data.retcode == 300118) {
							$("#registerCDS").val("CDS服务注册");
							$("#registerCDS").attr("isRegister", true);
						}
					}
				}
			});
}


