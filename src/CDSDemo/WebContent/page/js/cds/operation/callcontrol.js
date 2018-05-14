$(function() {

	$("#voicecallInner").click(function() {
		var workNo = $("#workNo").val();
		var called = prompt("Called ID：", "");
		if (workNo == "") {
			return;
		}
		if (called == undefined || called == null || called == "") {
			return;
		}
		voiceCallInner(workNo, called);
	});

	$("#voicecallInner").click(function() {
		var workNo = $("#workNo").val();
		var called = prompt("Called ID：", "");
		if (workNo == "") {
			return;
		}
		if (called == undefined || called == null || called == "") {
			return;
		}
		voiceCallInner(workNo, called);
	});

	$("#voicecallOut").click(function() {
		var workNo = $("#workNo").val();
		var called = prompt("Called Num：", "");
		if (workNo == "") {
			return;
		}
		if (called == undefined || called == null || called == "") {
			return;
		}
		voiceCallout(workNo, called);
	});

	$("#voicecallTranstoAgent").click(function() {
		var workNo = $("#workNo").val();
		var address = prompt("Address：", "");
		if (workNo == "") {
			return;
		}
		if (address == undefined || address == null || address == "") {
			return;
		}
		voicecallTranstoAgent(workNo, address);
	});

	$("#voicecallTranstoSkill").click(function() {
		var workNo = $("#workNo").val();
		var address = prompt("Address：", "");
		if (workNo == "") {
			return;
		}
		if (address == undefined || address == null || address == "") {
			return;
		}
		voicecallTranstoSkills(workNo, address);
	});

	$("#textChatAnswer").click(function() {
		var workNo = $("#workNo").val();
		var callId = global_callId;
		if (workNo == "" || callId == "") {
			return;
		}
		textChatAnswer(workNo, callId);
	});

	$("#textChatDrop").click(function() {
		var workNo = $("#workNo").val();
		var callId = global_callId;
		if (workNo == "" || callId == "") {
			return;
		}
		drop(workNo, callId);
	});

	$("#textChatInner").click(function() {
		var workNo = $("#workNo").val();
		var called = prompt("Called ID：", "");
		if (workNo == "") {
			return;
		}
		if (called == undefined || called == null || called == "") {
			return;
		}
		if (workNo == called) {
			return;
		}
		interCall(workNo, called);
	});

	$("#textChatTranstoAgent").click(function() {
		var workNo = $("#workNo").val();
		var address = prompt("Address：", "");
		var callId = global_callId;
		if (workNo == "") {
			return;
		}
		if (address == undefined || address == null || address == "") {
			return;
		}
		if (callId == undefined || callId == null || callId == "") {
			return;
		}
		textTranstoagent(workNo, address, callId);
	});

	$("#textChatTranstoSkill").click(function() {
		var workNo = $("#workNo").val();
		var address = prompt("Address：", "");
		var callId = global_callId;
		if (workNo == "") {
			return;
		}
		if (address == undefined || address == null || address == "") {
			return;
		}
		if (callId == undefined || callId == null || callId == "") {
			return;
		}
		textTranstoskill(workNo, address, callId);
	});

	$("#textChatSend").click(function() {
		var content = $("#ChatMessage").val();
		if (content == undefined || content == null || content == "") {
			return;
		}
		var workNo = global_workNo;
		if (workNo == undefined || workNo == null || workNo == "") {
			return;
		}
		var callId = global_callId;
		if (callId == undefined || callId == null || callId == "") {
			return;
		}
		sendMessage(workNo, callId, content);
	});

});

/*
 * VoiceAnswer
 */
function voiceAnswer(workNo) {
	$.post("/" + WEB_NAME + "/voice.do", {
		workNo : workNo,
		type : ANSWER
	}, function(data) {
		writeLog("[VoiceAnswer]" + data);
		var retcode = (JSON.parse(data)).retcode;
		switch (retcode) {
		case global_resultCode_SUCCESSCODE:
			break;
		}
	});
}

/*
 * TextChatAnswer
 */
function textChatAnswer(workNo, callId) {
	$.post("/" + WEB_NAME + "/textchat.do", {
		workNo : workNo,
		callId : callId,
		type : ANSWER
	}, function(data) {
		writeLog("[TextChatAnswer]" + data);
		var retcode = (JSON.parse(data)).retcode;
		switch (retcode) {
		case global_resultCode_SUCCESSCODE:
			break;
		}
	});
}

/*
 * VoiceRelease
 */
function voiceRelease(workNo) {
	$.post("/" + WEB_NAME + "/voice.do", {
		workNo : workNo,
		type : RELEASE
	}, function(data) {
		writeLog("[VoiceRelease]" + data);
		var retcode = (JSON.parse(data)).retcode;
		switch (retcode) {
		case global_resultCode_SUCCESSCODE:
			deleteCallId(global_workNo);
			break;
		}
	});
}

/*
 * TextChatRelease
 */
function drop(workNo, callId) {
	$.post("/" + WEB_NAME + "/textchat.do", {
		workNo : workNo,
		type : RELEASE,
		callId : callId
	}, function(data) {
		writeLog("[TextChatRelease]" + data);
		var retcode = (JSON.parse(data)).retcode;
		switch (retcode) {
		case global_resultCode_SUCCESSCODE:
			break;
		}
	});
}

/*
 * Hold
 */
function voiceHold(workNo) {
	$.post("/" + WEB_NAME + "/voice.do", {
		workNo : workNo,
		type : HOLD
	}, function(data) {
		writeLog("[Hold]" + data);
		var retcode = (JSON.parse(data)).retcode;
		switch (retcode) {
		case global_resultCode_SUCCESSCODE:
			break;
		}
	});
}

/*
 * Unhold
 */
function voiceGetHold(workNo) {
	$.post("/" + WEB_NAME + "/voice.do", {
		workNo : workNo,
		type : GETHOLD
	}, function(data) {
		writeLog("[Unhold]" + data);
		var retcode = (JSON.parse(data)).retcode;
		switch (retcode) {
		case global_resultCode_SUCCESSCODE:
			break;
		}
	});
}

/*
 * Mute
 */
function voiceMute(workNo) {
	$.post("/" + WEB_NAME + "/voice.do", {
		workNo : workNo,
		type : MUTE
	}, function(data) {
		writeLog("[Mute]" + data);
		var retcode = (JSON.parse(data)).retcode;
		switch (retcode) {
		case global_resultCode_SUCCESSCODE:
			break;
		}
	});
}

/*
 * UnMute
 */
function voiceCancleMute(workNo) {
	$.post("/" + WEB_NAME + "/voice.do", {
		workNo : workNo,
		type : CANCLEMUTE
	}, function(data) {
		writeLog("[UnMute]" + data);
		var retcode = (JSON.parse(data)).retcode;
		switch (retcode) {
		case global_resultCode_SUCCESSCODE:
			break;
		}
	});
}

/*
 * InnerVoiceCall
 */
function voiceCallInner(workNo, called) {
	$.post("/" + WEB_NAME + "/voice.do", {
		workNo : workNo,
		called : called,
		type : CALLINNER
	}, function(data) {
		writeLog("[InnerVoiceCall]" + data);
		var retcode = (JSON.parse(data)).retcode;
		switch (retcode) {
		case global_resultCode_SUCCESSCODE:
			break;
		}
	});
}

/*
 * InnerTextChat
 */
function interCall(workNo, called) {
	$.post("/" + WEB_NAME + "/textchat.do", {
		workNo : workNo,
		called : called,
		type : CALLINNER
	}, function(data) {
		writeLog("[InnerTextChat]" + data);
		var retcode = (JSON.parse(data)).retcode;
		switch (retcode) {
		case global_resultCode_SUCCESSCODE:
			break;
		}
	});
}

/*
 * VoiceCallout
 */
function voiceCallout(workNo, called) {
	$.post("/" + WEB_NAME + "/voice.do", {
		workNo : workNo,
		called : called,
		type : CALLOUT
	}, function(data) {
		writeLog("[VoiceCallout]" + data);
		var retcode = (JSON.parse(data)).retcode;
		switch (retcode) {
		case global_resultCode_SUCCESSCODE:
			break;
		}
	});
}

/*
 * VoiceTransToAgent
 */
function voicecallTranstoAgent(workNo, address) {
	$.post("/" + WEB_NAME + "/voice.do", {
		workNo : workNo,
		address : address,
		type : TRANSTOAGENT
	}, function(data) {
		writeLog("[VoiceTransToAgent]" + data);
		var retcode = (JSON.parse(data)).retcode;
		switch (retcode) {
		case global_resultCode_SUCCESSCODE:
			break;
		}
	});
}

/*
 * VoiceTransToSkill
 */
function voicecallTranstoSkills(workNo, address) {
	$.post("/" + WEB_NAME + "/voice.do", {
		workNo : workNo,
		address : address,
		type : TRANSTOSKILLS
	}, function(data) {
		writeLog("[VoiceTransToSkill]" + data);
		var retcode = (JSON.parse(data)).retcode;
		switch (retcode) {
		case global_resultCode_SUCCESSCODE:
			break;
		}
	});
}

/*
 * TextTransToAgent
 */
function textTranstoagent(workNo, address, callId) {
	$.post("/" + WEB_NAME + "/textchat.do", {
		workNo : workNo,
		address : address,
		callId : callId,
		type : TRANSTOAGENT
	}, function(data) {
		writeLog("[TextTransToAgent]" + data);
		var retcode = (JSON.parse(data)).retcode;
		switch (retcode) {
		case global_resultCode_SUCCESSCODE:
			break;
		}
	});
}

/*
 * TextTransToSkill
 */
function textTranstoskill(workNo, address, callId) {
	$.post("/" + WEB_NAME + "/textchat.do", {
		workNo : workNo,
		address : address,
		callId : callId,
		type : TRANSTOSKILLS
	}, function(data) {
		writeLog("[TextTransToSkill]" + data);
		var retcode = (JSON.parse(data)).retcode;
		switch (retcode) {
		case global_resultCode_SUCCESSCODE:
			break;
		}
	});
}

/*
 * ChatContentSend
 */
function sendMessage(workNo, callId, content) {
	$.post("/" + WEB_NAME + "/textchat.do", {
		workNo : workNo,
		type : SENDMESSAGE,
		callId : callId,
		content : content
	}, function(data) {
		writeLog("[ChatContentSend]" + data);
		var retcode = (JSON.parse(data)).retcode;
		switch (retcode) {
		case global_resultCode_SUCCESSCODE:
			UpdateWhenChatContentSent($("#ChatMessage").val());
			break;
		}
	});
}

/*
 * Save call data callId
 */
function saveCallId(workNo, callId) {
	$.post("/" + WEB_NAME + "/voice.do", {
		workNo : workNo,
		callId : callId,
		type : SAVE_CALLID
	}, function(data) {
		var retcode = (JSON.parse(data)).retcode;
		switch (retcode) {
		case global_resultCode_SUCCESSCODE:
			break;
		default:
			writeLog(data);
			break;
		}
	});
}

/*
 * Delete call data callId
 */
function deleteCallId(workNo) {
	$.post("/" + WEB_NAME + "/voice.do", {
		workNo : workNo,
		type : DELETE_CALLID
	}, function(data) {
		var retcode = (JSON.parse(data)).retcode;
		switch (retcode) {
		case global_resultCode_SUCCESSCODE:
			break;
		default:
			writeLog(data);
			break;
		}
	});
}

/*
 * Show text chat message
 */
function UpdateWhenChatContentSent(content) {
	var oldValue = $("#ChatTextBox").val();
	$("#ChatTextBox").val(oldValue + global_workNo + ": " + content + "\n");
	$("#ChatMessage").val("");

	var chatText = document.getElementById("ChatTextBox");
	chatText.scrollTop = chatText.scrollHeight * 12;
}

/*
 * voice.ocx Interface
 */
function SetSipInfo() {
	var VTMCtl = $("#VoiceOcx");
	var SipSvrIp = global_SipServerIP;
	var SipSvrPort = global_SipServerPort;
	var result = VTMCtl.SetSipServerInfo(SipSvrIp, SipSvrPort, "");
}

function SetLocalInfo() {
	var VTMCtl = $("#VoiceOcx");
	var LocalIp = global_SipServerIP;
	var LocalSipPort = global_SipServerPort;
	var LocalAudioPort = global_LocalAudioPort;
	var result = VTMCtl.SetLocalInfo(LocalIp, LocalSipPort, LocalAudioPort);
}

function voiceLogin() {
	var VTMCtl = document.getElementById("VoiceOcx");
	var SipSvrIp = global_SipServerName == "" ? global_SipServerIP
			: global_SipServerIP + "|" + global_SipServerName;
	var SipSvrPort = global_SipServerPort;
	var LocalIp = global_LocalIP;
	var LocalSipPort = global_LocalSipPort;
	var LocalAudioPort = global_LocalAudioPort;
	var passwd = $("#Phonepassword").val();
	var Account = $("#phonenumber").val();
	global_phoneNum = Account;
	var Mode = "1";
	var result = VTMCtl.SetSipServerInfo(SipSvrIp, SipSvrPort, "");
	var info = VTMCtl.SetLocalInfo(LocalIp, LocalSipPort, LocalAudioPort);
	if(info != 0 || info != "0")
	{
		writeLog("[SetLocalinfo]result:{result :" + info + ", retDesc :Set local info failed);}");
	}
	var result = VTMCtl.Register(Account, passwd, Mode);
	if(result == "0")
	{
		$("#registerSoftPhone").attr("value", "取消软电话注册");
		var re = aidSoundDeviceInfo();
		if(re == 0 || re == "0")
		{
			writeLog("[voiceLogin]result:{result :" + re + ", retDesc : Success.}");
		}
		else
		{    
			//防止事件串扰，状态出错。
			sleep(5);
			var resu = VTMCtl.Deregister();
			if(resu == 0 || resu == "0")
			{
				$("#registerSoftPhone").attr("value", "软电话注册");	
				writeLog("[voiceLogin]result:{result :" + re + ", retDesc : Failed to set the sound card.}");
				return;
			}
			else
			{
				//防止资源吊死
				$("#registerSoftPhone").attr("value", "软电话注册");
				writeLog("Unknown error...");
				return;
			}
		}
		
		if(mainMicrophoneDeviceName == ""&& mainSpeakerDeviceName == "")
		{
			return;
		}

		if(mainMicrophoneDeviceName != "")
		{
			var res = VTMCtl.SetDeviceIndex("0","1");
			if(res != 0 || res != "0")
			{
				writeLog("[SetDevice]result:{result :" + res + ", retDesc : Failed to set the main sound card.}");
				return;
			}
		}
		
		if(mainSpeakerDeviceName != "")
		{
			var res = VTMCtl.SetDeviceIndex("1","1");
			if(res != 0 || res != "0")
			{
				writeLog("[SetDevice]result:{result :" + res + ", retDesc : Failed to set the main sound card.}");
				return;
			}
		}	
	}
	else
	{
		writeLog("[voiceLogin]result:{result :" + result + " ,retDesc : The softphone fails to be registered.}");
	}

}
//异常保护，从事件中判断
function RegisterPhone(result){
	if (result.resultCode != 0) {
		$("#registerSoftPhone").attr("value", "软电话注册");
		writeLog("[voiceLogin]result:{result :" + result.resultCode + ", retDesc : Failure.}");
		return;
	}
}

function voiceLogout() {
	var VTMCtl = document.getElementById("VoiceOcx");
	
	var result = VTMCtl.Deregister();
	if(result == "0")
	{
		writeLog("[voiceLogout]result:{result :" + result + ", retDesc : Success.}");
	    EmergencyCallList = new HashMap();
		EmergencyCallAlterHandle ="";
		clear();
		//辅助声卡上的呼叫
	    callOnAidDevice = "";
		//是否启动了辅助设备
	    nableAidDevice = false;
		//是否切换到辅助设备
	    isToAidDevice = false;
		$("#registerSoftPhone").attr("value", "软电话注册");		
	}
	else
	{
		//防止资源吊死
		$("#registerSoftPhone").attr("value", "软电话注册");		
		writeLog("[voiceLogout]result:{result :" + result + ", retDesc : Failure.}");
	}
}

function VoiceInit(result) {
	if (result.resultCode != 0) {
		writeLog("[VoiceInit] VoiceInit is error: " + result.resultCode);
		return;
	}	
}

//获取辅声卡信息
function aidSoundDeviceInfo()
{	
	var re = 0;
	if(aidMicrophoneDeviceName == "" || aidSpeakerDeviceName == "")
	{
		//无辅助声卡直接返回
		return re;
	}	  
    IsEnableAidDevice = true;	
	var VTMCtl = document.getElementById("VoiceOcx");      
    aidMicDeviceName = aidMicrophoneDeviceName;
    aidSpeakDeviceName = aidSpeakerDeviceName;
    var obj = new Object();
    obj.aidMicDeviceName = aidMicDeviceName;
    obj.aidSpeakDeviceName = aidSpeakDeviceName;
    obj.mainIp = mainIp;
    obj.mainControlPort = mainControlPort;
    obj.aidIp = aidIp;
    obj.aidControlPort =aidControlPort;   
    writeLog("[aidSoundDevice]result:{result: "+JSON.stringify(obj)+"}");
	re = VTMCtl.SetSoundCardAidConfig(JSON.stringify(obj));				 
	return re;
}

function phoneAnswer(callId)
{
	var VTMCtl = document.getElementById("VoiceOcx");
	global_callId = callId;
	if ($("#AutoAnswer").attr("checked")) {
		writeLog("[phoneAnswer]isAutoAnswer is true: " + callId);
		var result = VTMCtl.Answer(callId);
		if(result == 0)
		{
			writeLog("[phoneAnswer]result:{callId:" + callId + ", errorCode:" + result + ", retDesc : Success.}");
		}
		else
		{
			writeLog("[phoneAnswer]result:{callId:" + callId + ", errorCode:" + result + ", retDesc : Failure.}");
		}
	} 
	else
    {
		writeLog("[phoneAnswer]isAutoAnswer is false: "+ callId);
    }

}

function StartPlayMediaFiles(userNumber, groupNumber) {
	var VTMCtl = document.getElementById("VoiceOcx");
	var filepath = emergencyCallFilePath;

	EmergencyCallList.put(groupNumber, userNumber);

	if (EmergencyCallAlterHandle == "" && EmergencyCallList.size() > 0) {
		var fileHandleResult = VTMCtl.StartPlayMediaFile(filepath, "0");
		if (JSON.parse(fileHandleResult).resultCode == 0) {
			writeLog("[StartPlayMediaFiles]result: {resultCode : 0, result" + JSON.parse(fileHandleResult).fileHandle+"}");
			EmergencyCallAlterHandle = JSON.parse(fileHandleResult).fileHandle;
		} else {
			writeLog("[StartPlayMediaFiles]result: {resultCode:" + JSON.parse(fileHandleResult).resultCode + ", result: " + JSON.parse(fileHandleResult).fileHandle+"}");
		}
	}
}

function StopPlayMediaFiles(userNumber, groupNumber) {
	var VTMCtl = document.getElementById("VoiceOcx");
	if (EmergencyCallList.containsKey(groupNumber)) {
		EmergencyCallList.remove(groupNumber);
	}

	if (EmergencyCallList.size() <= 0 && EmergencyCallAlterHandle !== "") {
		var result = VTMCtl.StopPlayMediaFile(EmergencyCallAlterHandle);
		EmergencyCallAlterHandle = "";
	}
}

//切换声卡操作
function PhoneSwitchSoundDevice(callSessionId, isToAidDevice)
{
	if (callSessionId =="")
    {
        return 70002;
    }

    //没配置声卡时，操作切换声卡，返回切换声卡设备失败
    if (aidMicrophoneDeviceName == "" && aidSpeakerDeviceName == "")
    {
    	//切换声卡失败
        return 70003;
    }
    
    var state = 0;
    var result;
    var VTMCtl = document.getElementById("VoiceOcx");
    
    if (isToAidDevice)
    {
        state = 1;
        if (callSessionId == callOnAidDevice)
        {
        	//已处于此状态
            return 70004;
        }

        if (callOnAidDevice != "")
        {               	
        	//辅助声卡有呼叫，直接返回错误码
        	return 70005;
        	
       /*   result = VTMCtl.ControlAudioAidDevice(callOnAidDevice, 0,aidMicrophoneDeviceName == "" ? aidMicrophoneDeviceName : aidSpeakerDeviceName);
            if (0 != result)
            {
                //writeLog("{result :" + result + "," + "callOnAidDevice :" +  callOnAidDevice + "}");
                callOnAidDevice = "";
                return result;
            }
            callOnAidDevice = callSessionId;
           // writeLog("{result :" + result + "}");
            return result;*/
        }
        callOnAidDevice = callSessionId;
    }
    else
    {
        if (callSessionId != callOnAidDevice)
        {
        	//writeLog("{result : 0}");
            return 0;
        }
        if (callOnAidDevice == "")
        {
            return 70004;
        }
        callOnAidDevice = "";
    }

    result = VTMCtl.ControlAudioAidDevice(callSessionId, state, aidMicrophoneDeviceName == "" ? aidMicrophoneDeviceName : aidSpeakerDeviceName);
    if (0 != result)
    {
        callOnAidDevice = "";
    }
    //writeLog("{result :" + result + "}");
    return result;
}

function sleep(n)
{
	var start = new Date().getTime();
	while (true)
	{
		if(new Date().getTime()-start > 1000*n)		
		{
			return;		
		}						
	}		
}

function clearCallId(callid)
{
	var value = global_group_call_map.get(callid);
	
	if(value != "" || value != null)
	{
		global_group_call_map.remove(callid)
	}
}

function clear()
{
	global_group_call_map.clear()
}

function phoneRelease(callId) {
	var VTMCtl = document.getElementById("VoiceOcx");
	var result = VTMCtl.Release("");
}
function SetConfig() {
	var VTMCtl = document.getElementById("VoiceOcx");
	var cfgkey = ValueOf("CfgKey");
	var cfgValue = ValueOf("CfgValue");
	var result = VTMCtl.SetConfig(cfgkey, cfgValue);
}

function GetConfig() {
	var VTMCtl = document.getElementById("VoiceOcx");
	var cfgkey = ValueOf("CfgKey");
	var result = VTMCtl.GetConfig(cfgkey);
}