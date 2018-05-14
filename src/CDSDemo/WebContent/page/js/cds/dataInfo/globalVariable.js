var WEB_NAME = window.location.origin;

var LOGIN = "LOGIN";

var FORCELOGIN = "FORCELOGIN";

var LOGOUT = "LOGOUT";

var FORCELOGOUT = "FORCELOGOUT";

var READY = "READY";

var BUSY = "BUSY";

var WORK = "WORK";

var OUTWORK = "OUTWORK";

var RESETSKILL = "RESETSKILL";

var ANSWER = "ANSWER";

var RELEASE = "RELEASE";

var SAVE_CALLID = "SAVECALLID";

var DELETE_CALLID = "DELETECALLID";

var HOLD = "HOLD";

var GETHOLD = "GETHOLD";

var MUTE = "MUTE";

var CANCLEMUTE = "CANCLEMUTE";

var CALLINNER = "CALLINNER";

var CALLOUT = "CALLOUT";

var TRANSTOAGENT = "TRANSTOAGENT";

var TRANSTOSKILLS = "TRANSTOSKILLS";

var SENDMESSAGE = "SENDMESSAGE";

var global_resultCode_SUCCESSCODE = "0";

var global_resultCode_REPEAT = "70001";

var global_workNo = "";
var global_callId = "";
var global_dispatchNum = "";
var global_phoneNum = "";
var global_ws = null;
var emergencyCallFilePath = "";
var global_group_call_map = new HashMap();
//存放紧急呼叫提示音和紧急呼叫提示音句柄
var EmergencyCallList = new HashMap();
var EmergencyCallAlterHandle ="";
//音量增益
var involumeValue = 0;
var outvolumeValue = 0;
//声卡信息 bigan
//辅麦克风：
var aidMicrophoneDeviceName = ""; 
//辅扬声器：
var aidSpeakerDeviceName = "";
//主麦克风：
var mainMicrophoneDeviceName = "";
//辅扬声器：
var mainSpeakerDeviceName = "";

//辅助声卡上的呼叫
var callOnAidDevice = "";

//是否启动了辅助设备
var IsEnableAidDevice = false;

//是否切换到辅助设备
var isToAidDevice = false;

var aidMicDeviceName = "";

var aidSpeakDeviceName = "" ;

var mainIp = "127.0.0.1";

var mainControlPort = "19200";

var aidIp  = "127.0.0.1";

var aidControlPort = "19301";

//声卡信息 end
var global_SipServerIP = "127.0.0.1";
var global_SipServerPort = 5060;
var global_SipServerName = "example.com";
var global_LocalIP = "127.0.0.1";
var global_LocalSipPort = 7061;
var global_LocalAudioPort = 7062;

var global_UportalServerIP = "127.0.0.1";
var global_UportalServerPort = 8843;
var global_UportalWebPort = 28843;

var global_UportalWebBaseAddr = "http://" + window.location.host;

var global_session_id = null;
var global_heartbeat_handler = null;
