
// AgentOther_InService
function Process_IN_SERVER() {
	$("#status").html("login");
	$("#status").css('color', 'blue');
}

// AgentOther_ShutdownService
function Process_SHUTDOWN() {

}

// state_Ready
function Process_READY() {
	$("#status").html("Idle");
	$("#status").css('color', 'green');
}

// state_SetNotReady_Success
function Process_notReady() {
	$("#status").html("Busy");
	$("#status").css('color', 'red');
}

// state_SetWork_Success
function Process_Work() {
	$("#status").html("working");
	$("#status").css('color', 'blue');
}

// textChat Ringing
function Process_Ring(content) {
	$("#status").html("Alerting");
	$("#status").css('color', 'blue');
	global_callId = content.callId;
	alert("Ring");
}

// textChat connected
function Process_Connected(content) {
	$("#status").html("Talking");
	$("#status").css('color', 'blue');
	global_callId = content.callId;
}

// textChat disconnected
function Process_Disconnected(content) {
	// deleteData(content.callId);
	global_callId = "";
	// $("#chat_sel_"+ content.callId).css("color","gray");
}

// AgentChat_DataRecved
function Process_RECEIVEDATA(sender, callId, content) {
	var oldValue = $("#ChatTextBox").val();
	$("#ChatTextBox").val(oldValue + sender + ": " + content + "\n");
	chatText.scrollTop = chatText.scrollHeight * 12;
}

// voiceCall Ringing
function Process_Ringing(content) {
	$("#status").html(content.caller + "|Ringing");
	$("#status").css('color', 'blue');
	alert("Ringing");
}

// voiceCall Talking
function Process_Talking(content) {
	$("#status").html("Talking");
	$("#status").css('color', 'blue');
	saveCallId(global_workNo, content.callid);
}

// AgentEvent_Hold
function Process_Hold() {
	$("#status").html("Hold");
	$("#status").css('color', 'red');
}

// voiceCall Release
function Process_Release(content) {

}

// Agent force logout by inspector(for Client-Server mode)
function Process_ExceptionLogout() {
	$("#status").html("Not login");
	$("#status").css('color', 'black');

	global_workNo = "";
	alert("You were forceLogout.");
	if ($("#voicePhone").attr("checked")) {
		voiceLogout(); // Phone logout
	}
}

// log print
function writeLog(content) {
	var oldValue = $("#LogInfo").val();
	var time = getNowTime();
	$("#LogInfo").val(oldValue +
			"[" + time + "][" + global_workNo + "]: " + content + "\n\n");
	
	document.getElementById('LogInfo').scrollTop = document.getElementById('LogInfo').scrollHeight;
}

// log clear
function clearLog() {
	$("#LogInfo").val("");
}

function getNowTime() {
	var date = new Date();
	return date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();
}
