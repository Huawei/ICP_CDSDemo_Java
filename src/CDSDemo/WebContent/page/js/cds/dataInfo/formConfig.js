var FromConfig = {		
   AudioConfig : {
		opName : "音频配置信息",
		fileds : [ {
			filedName : "主麦克风",
			filedId : "",
			defaultValue : "麦克风 (Citrix HDX Audio)"
		},{
			filedName : "主扬声器",
			filedId : "",
			defaultValue : "扬声器 (Citrix HDX Audio)"

		},{
			filedName : "辅麦克风",
			filedId : "",
			defaultValue : ""
		},{
			filedName : "辅扬声器",
			filedId : "",
			defaultValue : ""
		},{
			filedName : "本地地址",
			filedId : "",
			defaultValue : "128.120.100.31"
		}],
		callback : function() {
		var mainMicrophone = $($("#tb_voice_controller_form_body input")[0]).val();		
		var mainSpeaker = $($("#tb_voice_controller_form_body input")[1]).val();
		var aidMicrophone = $($("#tb_voice_controller_form_body input")[2]).val();
		var aidSpeaker= $($("#tb_voice_controller_form_body input")[3]).val();
		var LocalIP= $($("#tb_voice_controller_form_body input")[4]).val();

		mainMicrophoneDeviceName = mainMicrophone;
	    mainSpeakerDeviceName = mainSpeaker;
	    aidMicrophoneDeviceName = aidMicrophone; 
	    aidSpeakerDeviceName = aidSpeaker;
	    global_LocalIP = LocalIP;
	    writeLog("Audio config is complete.");
	    var VTMCtl = document.getElementById("VoiceOcx");
	    var re= VTMCtl.GetDevices("0")
	    var re1= VTMCtl.GetDevices("1")

	    writeLog("[re0]result:result "+ re);
	    writeLog("[re1]result:result"+re1);		
		}
	},
	voicecallAnswer : {
		opName : "语音应答",
		fileds : [ {
			filedName : "CallID",
			filedId : "CallID"
		} ],
		callback : function() {
			var callId = $($("#tb_voice_controller_form_body input")[0]).val();
			var VTMCtl = document.getElementById("VoiceOcx");
			var result = VTMCtl.Answer(callId);
			if(result == 0)
			{
				writeLog("[phoneAnswer]result:{callId:" + callId + ", result:" + result + ".}");
			}
			else
			{
				writeLog("[phoneAnswer]result:{callId:" + callId + ", result:" + result + ".}");
			}
		}
	},
	voicecallRelease : {
		opName : "语音挂断",
		fileds : [ {
			filedName : "CallID",
			filedId : "CallID"
		} ],
		callback : function() {
			var callId = $($("#tb_voice_controller_form_body input")[0]).val();
			var VTMCtl = document.getElementById("VoiceOcx");
			var result = VTMCtl.Release(callId);
			global_callId = "";
			if(result == 0)
			{
				writeLog("[voiceRelease]result:{callId:" + callId + ", result:" + result + ".}");
			}
			else
			{
				writeLog("[voiceRelease]result:{callId:" + callId + ", result:" + result + ".}");
			}
		}
	},
	voicecallMute : {
		opName : "语音静音",
		fileds : [ {
			filedName : "CallID",
			filedId : "CallID"
		} ],
		callback : function() {
			var callId = $($("#tb_voice_controller_form_body input")[0]).val();
			var VTMCtl = document.getElementById("VoiceOcx");
			var result = VTMCtl.Mute(callId);
			writeLog("result:" + result);
		}
	},
	voicecallUnMute : {
		opName : "取消语音静音",
		fileds : [ {
			filedName : "CallID",
			filedId : "CallID"
		} ],
		callback : function() {
			var callId = $($("#tb_voice_controller_form_body input")[0]).val();
			var VTMCtl = document.getElementById("VoiceOcx");
			var result = VTMCtl.UnMute(callId);
			writeLog("result:" + result);
		}
	},
	changeVoice1 : {
		opName : "切换辅声卡设备",
		fileds : [ {
			filedName : "callId",
			filedId : "callId"
		} ],
		callback : function() {
			var callId = $($("#tb_voice_controller_form_body input")[0]).val();
			var VTMCtl = document.getElementById("VoiceOcx");
			var result = VTMCtl.ControlAudioAidDevice(callId, 1, "");
			writeLog("result:" + result);
		}
	},
	changeVoice2 : {
		opName : "切换主声卡设备",
		fileds : [ {
			filedName : "callId",
			filedId : "callId"
		} ],
		callback : function() {
			var callId = $($("#tb_voice_controller_form_body input")[0]).val();
			var VTMCtl = document.getElementById("VoiceOcx");
			var result = VTMCtl.ControlAudioAidDevice(callId, 0, "");
			writeLog("result:" + result);
		}
	},
	getAudio : {
		opName : "获取音频设备",
		fileds : [ {
			filedName : "输入",
			filedId : "radio",
			defaultValue : "checked"
		}, {
			filedName : "输出",
			filedId : "radio"
		} ],
		callback : function() {
			var audioIn = $($("#tb_voice_controller_form_body input")[0]).attr(
					"checked");
			var audioOut = $($("#tb_voice_controller_form_body input")[1])
					.attr("checked");
			var flag = 0;
			if (audioOut == "checked") {
				flag = 1;
			}
			var VTMCtl = document.getElementById("VoiceOcx");
			var result = VTMCtl.GetDevices(flag);
			writeLog("result:" + result);
		}
	},
	setAudio : {
		opName : "设置音频设备",
		fileds : [ {
			filedName : "输入",
			filedId : "radio",
			defaultValue : "checked"
		}, {
			filedName : "输出",
			filedId : "radio"
		}, {
			filedName : "设备索引",
			filedId : "index"
		} ],
		callback : function() {
			var audioIn = $($("#tb_voice_controller_form_body input")[0]).attr(
					"checked");
			var audioOut = $($("#tb_voice_controller_form_body input")[1])
					.attr("checked");
			var index = $($("#tb_voice_controller_form_body input")[2]).val();
			var flag = 0;
			if (audioOut == "checked") {
				flag = 1;
			}
			var VTMCtl = document.getElementById("VoiceOcx");
			var result = VTMCtl.SetDeviceIndex(flag, index);
			writeLog("result:" + result);
		}
	},
	getVolume : {
		opName : "获取音量",
		fileds : [ {
			filedName : "输入",
			filedId : "radio",
			defaultValue : "checked"
		}, {
			filedName : "输出",
			filedId : "radio"
		} ],
		callback : function() {
			var audioIn = $($("#tb_voice_controller_form_body input")[0]).attr(
					"checked");
			var audioOut = $($("#tb_voice_controller_form_body input")[1])
					.attr("checked");
			var flag = 0;
			if (audioOut == "checked") {
				flag = 1;
			}
			var VTMCtl = document.getElementById("VoiceOcx");
			var result = VTMCtl.GetVolume(flag);
			writeLog("result:" + result);
		}
	},
	setVolume : {
		opName : "设置音量",
		fileds : [ {
			filedName : "输入",
			filedId : "radio",
			defaultValue : "checked"
		}, {
			filedName : "输出",
			filedId : "radio"
		}, {
			filedName : "音量",
			filedId : "setVol"
		} ],
		callback : function() {
			var audioIn = $($("#tb_voice_controller_form_body input")[0]).attr(
					"checked");
			var audioOut = $($("#tb_voice_controller_form_body input")[1])
					.attr("checked");
			var setVol = $($("#tb_voice_controller_form_body input")[2]).val();
			var flag = 0;
			if (audioOut == "checked") {
				flag = 1;
			}
			var VTMCtl = document.getElementById("VoiceOcx");
			var result = VTMCtl.SetVolume(flag, setVol);
			writeLog("result:" + result);
		}
	},
	getVolumeX : {
		opName : "查询音量增益",
		fileds : [ {
			filedName : "输入",
			filedId : "radio",
			defaultValue : "checked"
		}, {
			filedName : "输出",
			filedId : "radio"
		} ],
		callback : function() {
			var audioIn = $($("#tb_voice_controller_form_body input")[0]).attr(
					"checked");
			var audioOut = $($("#tb_voice_controller_form_body input")[1])
					.attr("checked");
			var flag = 0;
			if (audioOut == "checked") {
				flag = 1;
			}
			var VTMCtl = document.getElementById("VoiceOcx");
			var result = VTMCtl.GetGainByCall(global_callId, flag);
			writeLog("result:" + result);

		}
	},
	setVolumeX : {
		opName : "设置音量增益",
		fileds : [ {
			filedName : "输入",
			filedId : "radio",
			defaultValue : "checked"
		}, {
			filedName : "输出",
			filedId : "radio"
		}, {
			filedName : "音量",
			filedId : "setVol"
		} ],
		callback : function() {
			var audioIn = $($("#tb_voice_controller_form_body input")[0]).attr(
					"checked");
			var audioOut = $($("#tb_voice_controller_form_body input")[1])
					.attr("checked");
			var setVol = $($("#tb_voice_controller_form_body input")[2]).val();
			var flag = 0;
			if (audioOut == "checked") {
				flag = 1;
			}
			var VTMCtl = document.getElementById("VoiceOcx");
			var result = VTMCtl.SetGainByCall(global_callId, flag, setVol);
			writeLog("result:" + result);
		}
	},
	getAudioVolume : {
		opName : "查询音频设备音量",
		fileds : [ {
			filedName : "输入",
			filedId : "radio",
			defaultValue : "checked"
		}, {
			filedName : "输出",
			filedId : "radio"
		}, {
			filedName : "主音频设备",
			filedId : "radios",
			defaultValue : "checked"
		}, {
			filedName : "辅音频设备",
			filedId : "radios"
		} ],
		callback : function() {
			var audioIn = $($("#tb_voice_controller_form_body input")[0]).attr(
					"checked");
			var audioOut = $($("#tb_voice_controller_form_body input")[1])
					.attr("checked");
			var audioZhu = $($("#tb_voice_controller_form_body input")[2])
					.attr("checked");
			var audioFu = $($("#tb_voice_controller_form_body input")[3]).attr(
					"checked");
			var IOflag = 0;
			var ZFflag = "MainDevice";
			if (audioOut == "checked") {
				IOflag = 1;
			}
			if (audioFu == "checked") {
				ZFflag = "AidDevice";
			}

			var VTMCtl = document.getElementById("VoiceOcx");
			var result = VTMCtl.GetVolumeEx(ZFflag, IOflag);
			writeLog("result:" + result);
		}
	},
	setAudioVolume : {
		opName : "调整音频设备音量",
		fileds : [ {
			filedName : "输入",
			filedId : "radio",
			defaultValue : "checked"
		}, {
			filedName : "输出",
			filedId : "radio"
		}, {
			filedName : "主音频设备",
			filedId : "radios",
			defaultValue : "checked"
		}, {
			filedName : "辅音频设备",
			filedId : "radios"
		}, {
			filedName : "音量",
			filedId : "vol"
		} ],
		callback : function() {
			var audioIn = $($("#tb_voice_controller_form_body input")[0]).attr(
					"checked");
			var audioOut = $($("#tb_voice_controller_form_body input")[1])
					.attr("checked");
			var audioZhu = $($("#tb_voice_controller_form_body input")[2])
					.attr("checked");
			var audioFu = $($("#tb_voice_controller_form_body input")[3]).attr(
					"checked");
			var vol = $($("#tb_voice_controller_form_body input")[4]).val();
			var IOflag = 0;
			var ZFflag = MainDevice;
			if (audioOut == "checked") {
				IOflag = 1;
			}
			if (audioFu == "checked") {
				ZFflag = AidDevice;
			}
			var VTMCtl = document.getElementById("VoiceOcx");
			var result = VTMCtl.SetVolumeEx(ZFflag, IOflag, IOflag);
			writeLog("result:" + result);
		}
	},
	getGroupCallInfo : {
		opName : "查询群组呼叫信息",
		fileds : [ {
			filedName : "集群群组号码",
			filedId : "groupNumber"
		} ],
		callback : function() {
			var groupNumber = $($("#tb_voice_controller_form_body input")[0])
					.val();
			var callID = global_group_call_map.get(groupNumber);
						
			if(callID == null || callID == "" || callID == undefined)
			{
				writeLog("[voiceRelease]result:{callId:" + callID + ", errorCode:70006.}");
			}
			else
			{
				writeLog("[voiceRelease]result:{callId:" + callID + ", errorCode:0.}");
			}
		}
	},
	getConfigItem : {
		opName : "获取配置项",
		fileds : [ {
			filedName : "配置项Key",
			filedId : "key",
			defaultValue : "CONFIG_EMERGENCYCALL_FILE_PATH"	
		} ],
		callback : function() {
			var key = $($("#tb_voice_controller_form_body input")[0]).val();
			if ("CONFIG_EMERGENCYCALL_FILE_PATH" == key)
            {
                writeLog("[getConfig]result: {configValue:"+ emergencyCallFilePath+", resultCode : 0.}");
            }
			else
			{
				var VTMCtl = document.getElementById("VoiceOcx");
				var result = VTMCtl.GetConfig(key);
				if(JSON.parse(result).resultCode == 0)
				{
					writeLog("[getConfig]result:{configValue : " + JSON.parse(result).configValue + ", resultCode : 0.}");
				}
				else
				{
					writeLog("[getConfig]result:{configValue : " + JSON.parse(result).configValue + ", resultCode : "+JSON.parse(result).resultCode + "}");
				}				
			}			
		}
	},
	setConfigItem : {
		opName : "设置配置项",
		fileds : [ {
			filedName : "配置项Key",
			filedId : "key",
			defaultValue : "CONFIG_EMERGENCYCALL_FILE_PATH"	
		}, {
			filedName : "配置项值",
			filedId : "value",
			defaultValue : "D:/emergencyNote.wav"	
		} ],
		callback : function() {
			var key = $($("#tb_voice_controller_form_body input")[0]).val();
			var value = $($("#tb_voice_controller_form_body input")[1]).val();
			if("CONFIG_EMERGENCYCALL_FILE_PATH" == key)
			{
				emergencyCallFilePath = value;
				writeLog("[setConfig]result:{key : " + key +", configValue : " + emergencyCallFilePath + ", resultCode : 0.}");
			}
			else
			{
				var VTMCtl = document.getElementById("VoiceOcx");
				var result = VTMCtl.SetConfig(key, value);
				if(result== 0)
				{
					writeLog("[setConfig]result:{key : " + key +", configValue : " + value + ", resultCode : 0.}");
				}
				else
				{
					writeLog("[setConfig]result:{key : " + key +", configValue : " + value + ", resultCode : "+ result +".}");					
				}			
			}
		}
	},
	recordList : {
		opName : "录音列表",
		fileds : [ {
			filedName : "会议预定者",
			filedId : "confer",
			defaultValue : "a6311"
		}, {
			filedName : "开始时间",
			filedId : "startTime"
		}, {
			filedName : "结束时间",
			filedId : "stopTime"
		}, {
			filedName : "页码",
			filedId : "pageNumber",
			defaultValue : "1"
		}, {
			filedName : "页大小",
			filedId : "pageSize",
			defaultValue : "5"
		}, {
			filedName : "仅本站点录音",
			filedId : "radio",
			defaultValue : "checked"
		}, {
			filedName : "非仅本站点录音",
			filedId : "radio"
		}, {
			filedName : "仅会议录音",
			filedId : "radios",
			defaultValue : "checked"
		}, {
			filedName : "非仅会议录音",
			filedId : "radios"
		} ],
		callback : function() {
			var confer = $($("#tb_voice_controller_form_body input")[0]).val();
			var startTime = $($("#tb_voice_controller_form_body input")[1])
					.val();
			var stopTime = $($("#tb_voice_controller_form_body input")[2])
					.val();
			var pageNumber = $($("#tb_voice_controller_form_body input")[3])
					.val();
			var pageSize = $($("#tb_voice_controller_form_body input")[4])
					.val();
			var audioOnly = $($("#tb_voice_controller_form_body input")[5])
					.attr("checked");
			var audioNoOnly = $($("#tb_voice_controller_form_body input")[6])
					.attr("checked");
			var confOnly = $($("#tb_voice_controller_form_body input")[7])
					.attr("checked");
			var confNoOnly = $($("#tb_voice_controller_form_body input")[8])
					.attr("checked");
			var audioflag = true;
			var confflag = true;
			if (audioNoOnly == "checked") {
				audioflag = false;
			}
			if (confNoOnly == "checked") {
				confflag = false;
			}
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/record.do", {
				type : "POST",
				data : {
					userId : global_dispatchNum,
					confer : confer,
					startTime : startTime,
					stopTime : stopTime,
					pageNumber : pageNumber,
					pageSize : pageSize,
					audioflag : audioflag,
					confflag : confflag
				},
				success : function(data, textStatus) {
					writeLog("[RecordList]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	},
	getOnceCard : {
		opName : "获取一次性口令",
		fileds : [],
		callback : function() {
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/once.do", {
				type : "POST",
				data : {
					userId : global_dispatchNum
				},
				success : function(data, textStatus) {
					writeLog("[Once]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	},
	recordRepeat : {
		opName : "录音回放",
		fileds : [ {
			filedName : "文件路径",
			filedId : "filePath"
		}, {
			filedName : "录音文件",
			filedId : "recordFile"
		}, {
			filedName : "动态口令",
			filedId : "dynamicKey"
		}, {
			filedName : "服务器地址",
			filedId : "serverAddr"
		} ],
		callback : function() {
			var filePath = $($("#tb_voice_controller_form_body input")[0])
					.val();
			var recordFile = $($("#tb_voice_controller_form_body input")[1])
					.val();
			var dynamicKey = $($("#tb_voice_controller_form_body input")[2])
					.val();
			var serverAddr = $($("#tb_voice_controller_form_body input")[3])
					.val();
			var url = serverAddr + "/portal/playRecordFile.sraction?nonce="
					+ dynamicKey + "&filefolder=" + filePath + "&recordfile="
					+ recordFile;
			window.open(url);
			
			
		}
	},
	stopEmergencyVoice : {
		opName : "停止播放紧急呼叫提示音",
		fileds : [ {
			filedName : "集群群组号码",
			filedId : "groupNumber"
		} ],
		callback : function() {
			var groupNumber = $($("#tb_voice_controller_form_body input")[0]).val();
			var VTMCtl = document.getElementById("VoiceOcx");
			if (groupNumber == null ||groupNumber=="" || groupNumber=="undefined")
			{
				if(EmergencyCallAlterHandle !=="")
				{
					var result = VTMCtl.StopPlayMediaFile(EmergencyCallAlterHandle);
					if(result != "0")
					{
						writeLog("stopEmergencyVoice failed");
						return;
					}
					EmergencyCallAlterHandle = "";
					writeLog("[stopEmergencyVoice]result:{groupNumber: null, result:" + result + ".}");
				}
				EmergencyCallList.clear();		
				writeLog("[stopEmergencyVoice]result:{groupNumber: null, result: 0.}");
				
			}
			else
			{ 		
				var patrn = /^\d{1,32}$/;			
				if(!patrn.test(groupNumber))
				{
					writeLog("[stopEmergencyVoice]result:{groupNumber:" + groupNumber + ", result: 70002");
					return;
				}
				
                if (EmergencyCallList.containsKey(groupNumber))
                {
					EmergencyCallList.remove(groupNumber);
				}

                if(EmergencyCallList.size() <= 0 && EmergencyCallAlterHandle !== "")
                {
                	var result = VTMCtl.StopPlayMediaFile(EmergencyCallAlterHandle);
                	if(result != "0")
					{
						writeLog("stopEmergencyVoice failed");
						return;
					}
                	writeLog("[stopEmergencyVoice]result:{groupNumber:" + groupNumber + ", result:" + result + ".}");
				    EmergencyCallAlterHandle = "";
				}  
                writeLog("[stopEmergencyVoice]result:{groupNumber: " + groupNumber + ", result: 0.}");
			}
		}
	},

	select : {
		opName : "群组选择",
		fileds : [ {
			filedName : "集群群组号码",
			filedId : "groupNumber"
		} ],
		callback : function() {
			//在进行select之前，若辅助声卡上通话则把通话切换到主声卡
			var result;
				var groupNumber = $($("#tb_power_controller_form_body input")[0]).val();
				$.ajax(global_UportalWebBaseAddr + "/CDSDemo/select.do", {
					type : "POST",
					data : {
						isSelect : true,
						userId : global_dispatchNum,
						groupNumber : groupNumber
					},
					success : function(data, textStatus) {
						data = JSON.parse(data);
						switch (data.retcode.toString()) {
						case global_resultCode_SUCCESSCODE:						
							//切换声卡操作
							result = PhoneSwitchSoundDevice(global_callId, true);
							if (result != 0)
				            {
								//返回切换声卡失败
				                if (result == 70003)
				                {
				                    //无辅助声卡，返回成功
				                	writeLog("[select]result{retcode : 0}");
				                    return;
				                }	
				                writeLog("[SwitchSoundDevice]result{retcode :" + result + "}");
				                //回退select状态
				                $.ajax(global_UportalWebBaseAddr + "/CDSDemo/select", {
				    				type : "POST",
				    				data : {
				    					isSelect : false,
				    					userId : global_dispatchNum,
				    					groupNumber : groupNumber
				    				},
				    				success : function(data, textStatus) {
				    					data = JSON.parse(data);			    					
				    					if(data.retcode == "0")
				    					{		
				    						writeLog("[select]result{retcode : 0}");
						    			    return;
				    					}
				    					else
				    					{
				    						writeLog("[select]result{retcode :" + data.retcode +"}");
				    						return;
				    					}
				    				}	
				    			});					                
				            }
							writeLog("[select]result{retcode : 0}");
							writeLog("[SwitchSoundDevice]result{retcode :0}");
							break;
						default:
							alert("Error:" + data.retcode + ", Description:"
									+ data.retDesc);
						}
					}
				});
			//}
		}
	},
	deSelect : {
		opName : "取消群组选择",
		fileds : [ {
			filedName : "集群群组号码",
			filedId : "groupNumber"
		} ],
		callback : function() {			
			var result;
			var groupNumber = $($("#tb_power_controller_form_body input")[0]).val();
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/select.do", {
				type : "POST",
				data : {
					isSelect : false,
					userId : global_dispatchNum,
					groupNumber : groupNumber
				},
				success : function(data, textStatus) {
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						result = PhoneSwitchSoundDevice(global_callId, false);	
						if (result != 0)
			            {
							//返回切换声卡失败
			                if (result == 70003)
			                {
			                    //无辅助声卡，返回成功
			                	writeLog("[deSelect]result{retcode : 0}");
			                    return;
			                }	
			                writeLog("[deSelect]result{retcode : 0}");
			                writeLog("[SwitchSoundDevice]result{retcode :" + result+"}");
			                return;
			            }
						writeLog("[deSelect]result{retcode : 0}");
						writeLog("[SwitchSoundDevice]result{retcode :0}");
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	},
	ptt : {
		opName : "抢占话权",
		fileds : [ {
			filedName : "集群群组号码",
			filedId : "groupNumber"
		} ],
		callback : function() {
			var groupNumber = $($("#tb_power_controller_form_body input")[0])
					.val();
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/ptt.do", {
				type : "POST",
				data : {
					isPtt : true,
					userId : global_dispatchNum,
					groupNumber : groupNumber
				},
				success : function(data, textStatus) {
					writeLog("[PTT]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	},
	dePtt : {
		opName : "释放话权",
		fileds : [ {
			filedName : "集群群组号码",
			filedId : "groupNumber"
		} ],
		callback : function() {
			var groupNumber = $($("#tb_power_controller_form_body input")[0])
					.val();
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/ptt.do", {
				type : "POST",
				data : {
					isPtt : false,
					userId : global_dispatchNum,
					groupNumber : groupNumber
				},
				success : function(data, textStatus) {
					writeLog("[DePtt]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	},
	radioGet : {
		opName : "查询无线群组",
		fileds : [ {
			filedName : "无线集群群群组号码",
			filedId : "radioTKGroupNumber"
		} ],
		callback : function() {
			var radiogroupNumber = $($("#tb_config_form_body input")[0]).val();
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/radio.do", {
				type : "POST",
				data : {
					method : "GET",
					userId : global_dispatchNum,
					radiogroupNumber : radiogroupNumber
				},
				success : function(data, textStatus) {
					writeLog("[GetRadio]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	},
	radioList : {
		opName : "分页查询无线群组",
		fileds : [ {
			filedName : "页码",
			filedId : "pageNumber",
			defaultValue : 1
		}, {
			filedName : "页大小",
			filedId : "pageSize",
			defaultValue : 5
		} ],
		callback : function() {
			var pageNumber = $($("#tb_config_form_body input")[0]).val();
			var pageSize = $($("#tb_config_form_body input")[1]).val();
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/radio.do", {
				type : "POST",
				data : {
					method : "GETPAGE",
					userId : global_dispatchNum,
					pageNumber : pageNumber,
					pageSize : pageSize
				},
				success : function(data, textStatus) {
					writeLog("[GetPageRadio]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	},
	
		groupGet : {
		opName : "查询集群群组",
		fileds : [ {
			filedName : "集群群组号码",
			filedId : "talkGroupNumber"
		} ],
		callback : function() {
			var talkGroupNumber = $($("#tb_config_form_body input")[0]).val();
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/group.do", {
				type : "POST",
				data : {
					method : "GET",
					userId : global_dispatchNum,
					talkGroupNumber : talkGroupNumber
				},
				success : function(data, textStatus) {
					writeLog("[GetGroup]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	},
	groupList : {
		opName : "分页查询集群群组",
		fileds : [ {
			filedName : "页码",
			filedId : "pageNumber",
			defaultValue : 1
		}, {
			filedName : "页大小",
			filedId : "pageSize",
			defaultValue : 5
		}, {
			filedName : "查询类型",
			filedId : "queryType",
			defaultValue : 0
		} ],
		callback : function() {
			var pageNumber = $($("#tb_config_form_body input")[0]).val();
			var pageSize = $($("#tb_config_form_body input")[1]).val();
			var queryType = $($("#tb_config_form_body input")[2]).val();
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/group.do", {
				type : "POST",
				data : {
					method : "GETPAGE",
					userId : global_dispatchNum,
					pageNumber : pageNumber,
					pageSize : pageSize,
					queryType : queryType
				},
				success : function(data, textStatus) {
					writeLog("[GetPageGroup]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	},

	proxyGet : {
		opName : "查询接入号码",
		fileds : [ {
			filedName : "接入号码",
			filedId : "proxyNumber"
		} ],
		callback : function() {
			var proxyNumber = $($("#tb_config_form_body input")[0]).val();
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/jasProxy.do", {
				type : "POST",
				data : {
					method : "GET",
					userId : global_dispatchNum,
					proxyNumber : proxyNumber
				},
				success : function(data, textStatus) {
					writeLog("[GetProxy]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	},
	proxyList : {
		opName : "全量查询接入号码",
		fileds : [],
		callback : function() {
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/jasProxy.do", {
				type : "POST",
				data : {
					method : "GETALL",
					userId : global_dispatchNum,
				},
				success : function(data, textStatus) {
					writeLog("[GetAllProxy]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	},
	pubParamtersGet : {
		opName : "查询公参",
		fileds : [],
		callback : function() {
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/pubParam.do", {
				type : "POST",
				data : {
					method : "GET",
					userId : global_dispatchNum,
				},
				success : function(data, textStatus) {
					writeLog("[GetPubParam]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	},
	groupStateQuery : {
		opName : "查询群组状态",
		fileds : [ {
			filedName : "集群群组号码",
			filedId : "groupNumber"
		} ],
		callback : function() {
			var groupNumber = $($("#tb_config_form_body input")[0]).val();
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/state.do", {
				type : "POST",
				data : {
					userId : global_dispatchNum,
					groupNumber : groupNumber
				},
				success : function(data, textStatus) {
					writeLog("[GetGroupState]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	},
	subscribe : {
		opName : "订阅",
		fileds : [ {
			filedName : "集群群组号码",
			filedId : "groupNumber"
		} ],
		callback : function() {
			var groupNumber = $($("#tb_call_controller_form_body input")[0])
					.val();
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/subscribe.do", {
				type : "POST",
				data : {
					userId : global_dispatchNum,
					groupNumber : groupNumber,
					subscribeExpired : 3600
				},
				success : function(data, textStatus) {
					writeLog("[Subscribe]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	},
	deSubscribe : {
		opName : "取消订阅",
		fileds : [ {
			filedName : "集群群组号码",
			filedId : "groupNumber"
		} ],
		callback : function() {
			var groupNumber = $($("#tb_call_controller_form_body input")[0])
					.val();
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/subscribe.do", {
				type : "POST",
				data : {
					userId : global_dispatchNum,
					groupNumber : groupNumber,
					subscribeExpired : 0
				},
				success : function(data, textStatus) {
					writeLog("[DeSubscribe]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	},
	monitor : {
		opName : "群组监听",
		fileds : [ {
			filedName : "集群群组号码",
			filedId : "groupNumber"
		} ],
		callback : function() {
			var groupNumber = $($("#tb_call_controller_form_body input")[0])
					.val();
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/monitor.do", {
				type : "POST",
				data : {
					userId : global_dispatchNum,
					groupNumber : groupNumber,
					isMonitor : true

				},
				success : function(data, textStatus) {
					writeLog("[Monitor]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	},
	deMonitor : {
		opName : "取消群组监听",
		fileds : [ {
			filedName : "集群群组号码",
			filedId : "groupNumber"
		} ],
		callback : function() {
			var groupNumber = $($("#tb_call_controller_form_body input")[0])
					.val();
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/monitor.do", {
				type : "POST",
				data : {
					userId : global_dispatchNum,
					groupNumber : groupNumber,
					isMonitor : false
				},
				success : function(data, textStatus) {
					writeLog("[DeMonitor]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	},
	mixedGroupCallStart : {
		opName : "开始混编呼叫",
		fileds : [ {
			filedName : "集群群组号码",
			filedId : "groupNumber"
		}, {
			filedName : "邀请用户信息",
			filedId : "users"
		}, {
			filedName : "是否全场静音",
			filedId : "mute",
			defaultValue : false
		} , {
			filedName : "群组别名",
			filedId : "groupTempName"
		}, {
			filedName : "预留功能开关",
			filedId : "reservedSwitch",
			defaultValue : false
		}  ],
		callback : function() {
			var groupNumber = $($("#tb_call_controller_form_body input")[0])
					.val();
			var users = $($("#tb_call_controller_form_body input")[1]).val();
			var mute = $($("#tb_call_controller_form_body input")[2]).val();
			var groupTempName = $($("#tb_call_controller_form_body input")[3]).val();
			var reservedSwitch = $($("#tb_call_controller_form_body input")[4]).val();
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/mixGroupCall.do", {
				type : "POST",
				data : {
					userId : global_dispatchNum,
					groupNumber : groupNumber,
					users : users,
					mute : mute,
					groupTempName : groupTempName,
					reservedSwitch : reservedSwitch
				},
				success : function(data, textStatus) {
					writeLog("[MixGroupCall]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	},
	mixedGroupCallEnd : {
		opName : "结束混编呼叫",
		fileds : [ {
			filedName : "集群群组号码",
			filedId : "groupNumber"
		} ],
		callback : function() {
			var groupNumber = $($("#tb_call_controller_form_body input")[0])
					.val();
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/mixGroupCall.do", {
				type : "POST",
				data : {
					method : "DELETE",
					userId : global_dispatchNum,
					groupNumber : groupNumber
				},
				success : function(data, textStatus) {
					writeLog("[MixGroupCallEnd]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	},
	mixedGroupUserStateChange : {
		opName : "混编群组成员状态变更",
		fileds : [ {
			filedName : "集群群组号码",
			filedId : "groupNumber"
		}, {
			filedName : "用户",
			filedId : "users"
		} ],
		callback : function() {
			var groupNumber = $($("#tb_call_controller_form_body input")[0])
					.val();
			var users = $($("#tb_call_controller_form_body input")[1]).val();
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/mixGroupCall.do", {
				type : "POST",
				data : {
					method : "PUT",
					userId : global_dispatchNum,
					groupNumber : groupNumber,
					users : users
				},
				success : function(data, textStatus) {
					writeLog("[MixGroupCallChange]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	},
	emergencyAnswer : {
		opName : "紧急呼叫应答",
		fileds : [ {
			filedName : "集群群组号码",
			filedId : "groupNumber"
		} ],
		callback : function() {
			var groupNumber = $($("#tb_call_controller_form_body input")[0])
					.val();
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/emergencyAnswer.do", {
				type : "POST",
				data : {
					method : "POST",
					userId : global_dispatchNum,
					groupNumber : groupNumber
				},
				success : function(data, textStatus) {
					writeLog("[EmergencyAnswer]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	},
	emergencyExit : {
		opName : "紧急呼叫离会",
		fileds : [ {
			filedName : "集群群组号码",
			filedId : "groupNumber"
		} ],
		callback : function() {
			var groupNumber = $($("#tb_call_controller_form_body input")[0])
					.val();
			$.ajax(global_UportalWebBaseAddr + "/CDSDemo/emergencyAnswer.do", {
				type : "POST",
				data : {
					method : "DELETE",
					userId : global_dispatchNum,
					groupNumber : groupNumber
				},
				success : function(data, textStatus) {
					writeLog("[EmergencyAnswer]ret=" + data);
					data = JSON.parse(data);
					switch (data.retcode.toString()) {
					case global_resultCode_SUCCESSCODE:
						break;
					default:
						alert("Error:" + data.retcode + ", Description:"
								+ data.retDesc);
					}
				}
			});
		}
	}
};
