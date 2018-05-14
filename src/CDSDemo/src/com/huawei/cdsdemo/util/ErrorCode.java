/**   
 * Copyright © 2016 Huawei. All rights reserved.
 * 
 * @Title: ErrorCode.java
 * @Prject: usm_jas
 * @Package: com.huawei.usm.jas.util
 * @Description: 错误码定义
 * @date: 2016年7月11日 下午7:58:48
 * @version: V1.0
 */
package com.huawei.cdsdemo.util;

import java.text.MessageFormat;

/** 
 * @Title: ErrorCode
 * @Description: 错误码定义
 * @date: 2016年7月11日 下午7:58:48
 */
public enum ErrorCode
{
    /**
     * 未知错误
     */
    UNKNOWN(-1, "Unknown error."),
    
    /**
     * 操作成功
     */
    SUCCESS(0, "Successful operation."),
    
    /**
     * 操作失败
     */
    FAILED(1, "Operation failed."),
    
    /**
     * SIP INFO need to be resend; 
     */
    RESEND(2, "Need resend"),
    
    /**
     * 参数为空。
     */
    PARAM_EMPTY(300000, "The parameter is empty."),
    
    /**
     * 参数非法。
     */
    PARAM_ILLEGAL(300001, "Invalid parameter."),
    
    /**
     * 内部错误。
     */
    INTERNAL_ERROR(300002, "Internal error."),
    
    /**
     * 重复的无线集群群组
     */
    DUPLICATE_RADIOTRUNK(300003, "Duplicate radio trunking group."),
    
    /**
     * 超出最大配置数量
     */
    // VALUE_EXCEEDS_MAXIMUM(300004, "Exceeded the maximum number of configuration."),
    
    /**
     * 操作数据库失败
     */
    FAILED_OPERATE_DB(300005, "Failure to operate the database."),
    
    /**
     * 无效集群群组名称
     */
    //INVALID_GROUP_NAME(300006, "Invalid trunking group name."),
    
    /**
     * 无效集群群组号码，号码中含有存在非法字符
     */
    INVALID_TALKGROUP_NUMBER(300007,
        "Invalid trunking group number because the trunking group number contains invalid characters."),
        
    /**
     * 无效集群群组类型
     */
    INVALID_TALKGROUP_TYPE(300008, "Invalid trunking group type."),
    
    /**
     * 无效无线集群群组号码
     */
    // INVALID_RADIO_TRUNK_GROUP_NUMBER(300009, "Invalid radio trunk group number."),
    
    /**
     * 无效调度席号码
     */
    // INVALID_DISPATCH_POS_NUMBER(300010, "Invalid dispatch position number."),
    
    /**
     * 无效其他用户号码
     */
    // INVALID_OTHER_USER_NUMBER(300011, "Invalid other user number."),
    
    /**
     * 录音文件存储相对地址超过最大长度 
     */
    RECORD_FILE_ADDR_EXCEEDS_MAXIMUM(300012,
        "The length of the relative address for storing recording files exceeds the maximum."),
        
    /**
     * 群组描述超过最大支持长度
     */
    // INVALID_GROUP_DESC(300013, "Description exceeds the maximum length."),
    
    /**
     * 号码为空
     */
    NUMBER_IS_EMPTY(300014, "The number is empty."),
    
    /**
     * 无效会议Id
     */
    INVALID_CONF_ID(300015, "Invalid conference ID."),
    
    /**
     * 无效与会人号码
     */
    INVALID_ATTNUMBER(300016, "Invalid participant number."),
    
    /**
     * 无效与会人角色
     */
    INVALID_ATTROLE(300017, "Invalid participant role."),
    
    /**
     * 无效与会人类型
     */
    INVALID_ATTTYPE(300018, "Invalid participant type."),
    
    /**
     * 无线集群群组被集群群组引用
     */
    RADIO_TRUNK_GROUP_IS_REFERENCED(300019, "The radio trunking group is used as the trunking group."),
    
    /**
     * 重复的集群群组
     */
    DUPLICATE_TALKGOUP(300020, "Duplicate trunking group."),
    
    /**
     * 预定会议失败
     */
    SCHEDULE_CONF_FAIL(300021, "Failure to schedule a conference."),
    
    /**
     * 邀请入会失败
     */
    INVITE_JOIN_CONF_FAIL(300022, "Failure to join a conference with an invitation."),
    
    /**
     * 到达集群群组monitor数量上限
     */
    UPPER_GROUP_MONITOR_LIMIT(300023,
        "The number of the Monitor operations reaches the maximum allowed by the trunking group."),
        
    /**
     * 到达调度席monitor数量上限
     */
    UPPER_USER_MONITOR_LIMIT(300024,
        "The number of the Monitor operations reaches the maximum allowed by the dispatcher."),
        
    /**
     * 已经处于Monitor状态
     */
    ALREADY_MONITOR(300025, "In Monitor state."),
    
    /**
     * 集群群组未配置
     */
    TALKGROUP_NOT_EXIST(300026, "The trunking group is not configured."),
    
    /**
     * 无线集群群组类型
     */
    INVALID_RADIO_TRUNK_GROUP_TYPE(300027, "Invalid radio trunking group type."),
    
    /**
     * 无线集群群组未配置
     */
    RADIO_TRUNK_GROUP_NOT_EXIST(300028, "The radio trunking group is not configured."),
    
    /**
     * 调度席未配置
     */
    DISPATCH_NOT_EXIST_IN_TRUNK_GROUP(300029, "The dispatcher is not configured."),
    
    /**
     * 不在Monitor状态
     */
    NOT_IN_MONITOR_STATUS(300030, "Not in Monitor state."),
    
    /**
     * 操作正在处理中
     */
    OPERATION_IN_PROCESS(300031, "The operation is in process."),
    
    /**
     * 取消monitor操作失败
     */
    // DELETE_USERNUM_FAIL(300032, "Fail to delete usernum."),
    
    /**
     * 无效无线集群模式
     */
    // INVALID_RADIO_TRUNK_MODE_TYPE(300033, "Invalid radio trunk mode."),
    
    /**
     * 配置的群组个数达到上限
     */
    UPPER_GROUP_COUNT_LIMIT(300034, "The number of configured groups reaches the maximum."),
    
    /**
     * 记录为空
     */
    NOT_FOUND_RECORD(300035, "The record is empty."),
    
    /**
     * 调度席号码个数无效
     */
    INVALID_DISPATCH_NUMBER_COUNT(300036, "The number of dispatcher numbers is invalid."),
    
    /**
     * 无线集群群组号码个数无效
     */
    INVALID_RADIO_GROUP_NUMBER_COUNT(300037, "The number of radio trunking group numbers is invalid."),
    
    /**
     * 其他用户号码个数无效
     */
    INVALID_OTHER_USER_NUMBER_COUNT(300038, "The number of other user numbers is invalid."),
    
    /**
     * 无线集群群组，调度席号码，其他用户号码包含重复的记录
     */
    REPEAT_RECODE(300039, "The duplicate numbers of radio trunking groups, dispatchers, or other types of users."),
    
    /**
     * 超过最大用户数目
     */
    OVER_THE_MAX_RECORD(300040,
        "The number of users exceeds the maximum. The number of dispatchers and other types of users cannot exceed 36."),
        
    /**
     * 公共参数表中的index字段值无效
     */
    // INVALID_INDEX_VALUE(300041, "The value of index is invalid."),
    
    /**
     * 集群群组用户账号不存在
     */
    // USER_NUMBER_IS_NOT_EXIST(300042, "The talk group user does not exist."),
    
    /**
     * 无效集群群组用户
     */
    INVALID_USER_NUMBER(300043, "Invalid trunking group user."),
    
    /**
     * 集群群组用户已存在
     */
    USER_NUMBER_HAS_EXISTED(300044, "The trunking group user exists."),
    
    /**
     * 集群群组用户未配置
     */
    USER_NUMBER_IS_NOT_CONFIGURED(300045, "The trunking group user is not configured."),
    
    /**
     * 号码长度超过32个字符
     */
    NUMBER_EXCEEDS_MAXIMUM(300047, "The length of the number exceeds 32 characters."),
    
    /**
     * 号码中包含非法字符，号码仅0~9字符
     */
    NUMBER_CONTAIN_ILLEGAL_CHARACTER(300048,
        "The number contains invalid characters. Only numbers from 0 to 9 are valid."),
        
    /**
     * 配置的群组个数达到上限
     */
    UPPER_GROUP_USER_COUNT_LIMIT(300053, "The number of configured trunking groups reaches the maximum."),
    
    /**
     * 已经处于PTT状态
     */
    ALREADY_PTT(300054, "The dispatcher is in PTT state."),
    
    /**
     * 不在PTT状态
     */
    NOT_IN_PTT_STATUS(300055, "The dispatcher is not in PTT state."),
    
    /**
     * PTT操作正在处理
     */
    // ALREADY_PTT_PROCESS(300056, "PTT is being processed."),
    
    /**
     * PTT操作需要先monitor
     */
    PTT_NEED_MONITOR(300057, "The Monitor operation should be performed before the PTT operation."),
    
    /**
     * PTT操作需要先select
     */
    PTT_NEED_SELECT(300058, "The Select operation should be performed before the PTT operation."),
    
    /**
     * 话权冲突
     */
    PTT_CONFLICT(300059, "Floor conflict."),
    
    /**
     * 群组中无有效无线集群群组
     */
    NONE_VALID_RADIOGROUP(300060, "There are invalid trunking groups in the group."),
    
    /**
     *  PTT群组信息无效
     */
    INVALID_PTT(300061, "Invalid PTT group information."),
    
    /**
     *  Monitor超时
     */
    MONITOR_TIMEOUT(300062, "Timeout of the Monitor operation."),
    
    /**
     *  PTT超时
     */
    PTT_TIMEOUT(300063, "Timeout of the PTT operation."),
    
    /**
     * 集群群组用户被集群群组引用 
     */
    TALK_GROUP_USER_IS_REFERENCED(300064, "The account of the trunking group user is used."),
    
    /**
     * 查询用户状态信息失败
     */
    FAILED_FIND_GROUP_USER_STATUS(300065, "Failure to query user status information."),
    
    /**
     *  下行PTT群组信息无效
     */
    INVALID_DOWNPTT(300066, "Invalid downlink PTT group information."),
    
    /**
     * PTT请求拒绝
     */
    PTT_REJECT(300067, "The PTT request is rejected."),
    
    /**
     * 内部通信错误
     */
    INTERNAL_COMM_ABNORMAL(300068, "Internal communication error."),
    
    /**
     *  select群组信息无效
     */
    INVALID_SELECT(300069, "Invalid Select group information."),
    
    /**
     *  deSelect群组信息无效
     */
    INVALID_DESELECT(300070, "Invalid Deselect group information."),
    
    /**
     * 调度席不在select状态
     */
    NOT_IN_SELECT(300071, "The dispatcher is not in Select state."),
    
    /**
     * 调度席已经在select状态
     */
    ALREADY_IN_SELECT(300072, "The dispatcher is in Select state."),
    
    /**
     * 到达集群群组select数量上限
     */
    UPPER_USER_SELECT_LIMIT(300073,
        "The number of the Select operations reaches the maximum allowed by the trunking group."),
        
    /**
     * 更新用户状态信息失败
     */
    FAILED_UPDATE_GROUP_USER_STATUS(300074, "Failure to update the user state information."),
    
    /**
     * 到达集群群组subscribe数量上限
     */
    UPPER_GROUP_SUBSCRIBE_LIMIT(300075,
        "The number of Subscribe operations reaches the maximum allowed by the trunking group."),
        
    /**
     * 用户已经在订阅状态
     */
    USER_ALREADY_IN_THIS_STATE_ERROR(300076, "The user is in Subscribe state."),
    
    /**
     * 调度席已在当前混编群组呼叫中
     */
    ALREADY_IN_MIXCALL(300077, "The dispatcher is already in the current hybrid group call."),
    
    /**
     *非会议预订者无此操作权限
     */
    UNCONFSCHEDULER_NO_RIGHT(300078,
        "Users who are not the conference scheduler do not have the right to perform this operation."),
        
    /**
     * 集群群组类型不能被修改
     */
    GROUPTYPE_CANT_CHANGE(300079, "The trunking group type cannot be modified."),
    
    /**
     * 用户当前没有订阅状态
     */
    USER_NOT_IN_SUBSCRIBER_STATE(300080, "The user is not in Subscribe state."),
    
    /**
     * 当集群群组类型为普通集群群组时，其他用户号码必须为空
     */
    NUMBER_MUST_BE_BLANK(300081, "The number of the other type user must be empty in a common trunking group."),
    
    /**
     * 该集群群组类型不是混编集群群组
     */
    TALKGROUP_TYPE_MUST_BE_MIXGROUP(300082, "This trunking group is not a hybrid trunking group."),
    
    /**
     * 混编集群群组不支持该操作
     */
    MIXGROUP_NOT_SUPPORT_THIS_OPERATION(300083, "The hybrid trunking group does not support this operation."),
    
    /**
     * 调度席达到最大订阅数
     */
    UPPER_USER_SUBSCRIBE_LIMIT(300084, "The number of subscriptions reaches the maximum allowed by the dispatcher."),
    
    /**
     * 录音文件路径不合法
     */
    // RECORD_FILE_ADDR_CONTAIN_ILLEGAL_CHARACTER(300085, "The recording file address contains illegal characters."),
    
    /**
     * 主机处理数据失败
     */
    HOST_FAILED_TO_PROCESS_DATA(300086, "The host fails to process the data."),
    
    /**
     * 调度席账号未开户
     */
    DISPATCH_USER_NOT_CONFIGURATE(300087, "The dispatcher account is not registered."),
    
    /**
     * 鉴权失败
     */
    FAILED_TO_AUTHENTICATION(300088, "Authentication failure."),
    
    /**
     * 录音文件地址有效字符为0~9、A~Z、a~z、/或空字符串。当参数不为空时，必须以0~9、A~Z、a~z开头，以/字符结尾。
     */
    INVALID_RECORD_FILE_ADDRESS(300089,
        "The valid characters of the recording file address are numbers from 0 to 9, upper-case letters from A to Z, lower-case letters from a to z, and slashes (/), or the character string is empty. If this parameter is not empty, it must start with numbers from 0 to 9, upper-case letters from A to Z, or lower-case letters from a to z, and ends with a slash (/)."),
        
    /**
     * 混编群组正在呼叫中，请在呼叫结束后操作。
     */
    MIXGROUP_IS_CALLING(300090,
        "The hybrid trunking group is in call state and this operation should be performed after the call ends."),
        
    /**
     * monitor状态下不允许取消订阅
     */
    MONITOR_STATE_NOT_ALLOW_CANCEL_SUBSCRIBE(300091, "The subscription cannot be canceled in Monitor state."),
    
    /**
     * 系统在ptt状态下不允许取消订阅
     */
    PTT_STATE_NOT_ALLOW_CANCEL_SUBSCRIBE(300092, "The subscription cannot be canceled in PTT state."),
    
    /**
     * 一个无线集群群组只能配置在一个普通集群群组中。
     */
    RADIO_TRUNK_GROUP_IS_REFERENCED_BY_TALK_GROUP(300093,
        "A radio trunking group can only be configured in a common trunking group."),
        
    /**
     * ReceiveOnly属性为True的集群群组中，调度席不能进行PTT操作
     */
    TALK_GROUP_IS_RECEIVE_ONLY(300094,
        "When the ReceiveOnly field of the trunking group is set to True, the dispatcher cannot perform the PTT operation in this trunking group."),
        
    /**
     * 调度席已PTT其他集群群组
     */
    ALREADY_PTT_ANOTHER_TALKGROUP(300095, "The dispatcher has the talk right of other trunking groups."),
    
    /**
     * 混编群组不在呼叫状态
     */
    NOT_IN_MIXCALL_STATUS(300096, "The hybrid trunking group is not in call state."),
    
    /**
     * 更新共享IFC模板失败
     */
    UPDATE_SIFC_FAILED(300097, "Failure to update the shared initial filter criteria (iFC) template."),
    
    /**
     * 共享IFC模板冲突
     */
    SIFC_CONFLICT(300098, "Conflict of sharing the iFC template."),
    
    /**
     * 接入号码不存在
     */
    JAS_PROXY_NOT_EXIST(300099, "The access number does not exist."),
    
    /**
     * 重复的接入号码
     */
    DUPLICATE_JASPROXY(300100, "Duplicate access number."),
    
    /**
     * 接入号码用户未配置
     */
    USER_PROXY_IS_NOT_CONFIGURED(300101, "The user of the access number is not configured."),
    
    /**
     * 接入号码未配置
     */
    JAS_PROXY_NOT_CONFIGURED(300102, "The access number is not configured."),
    
    /**
     * 接入号码被集群群组号码占用
     */
    NUMBER_IS_REFERENCED_BY_TALKGROUP(300103, "The access number is used as the trunking group number."),
    
    /**
     * 集群群组号码被接入号码占用
     */
    NUMBER_IS_REFERENCED_BY_PROXY(300104, "The trunking group number is used as the access number."),
    
    /**
     * 系统中有混编群组会议时，不允许修改混编抢权标识
     */
    FAILED_TO_MODIFY_PUBPARAM(300105,
        "Users are not allowed to change the value of Whether the hybrid group supports floor preemption if a hybrid group is in a conference."),
        
    /**
     * 混编抢权时，混编群组中未配置无线集群群组用户且主席没有邀请其他无线集群群组用户
     */
    NO_RADIO_IN_MIXGROUP(300106,
        "The floor preemption failed as no radio trunking group users are configured for or invited to the hybrid group."),
        
    /**
     *混编抢权时，只有主席可以下行PTT操作,其他调度不可以下行PTT
     */
    ONLY_SCHEDULER_DOWNPTT_HYBRID_GRANT(300109,
        "When the value of Whether the hybrid group supports floor preemption is set to Yes, only the chairman can perform the downlink PTT operation."),
        
    /**
     * 混编抢权时，一个调度席只可以发起一个混编会议
     */
    ONE_DISPATCH_ONLY_CREATE_ONE_CONF(300110,
        "When the value of Whether the hybrid group supports floor preemption is set to Yes, a dispatcher can initiate only one hybrid group call."),
        
    /**
     * 超过混编最大成员数
     */
    UPPER_MIXGROUP_MEMBER_COUNT_LIMIT(300111, "The number of members reaches the maximum allowed by the hybrid group."),
    
    /**
     * 已经有调度席对该群组发起混编呼叫
     */
    ALAREADY_HAVE_SCHEDULING_IN_MIX_GROUP_CALL(3000112, "A dispatcher has initiated a call to the hybrid group."),
    
    /**********************************************************************************************************************************
    **********************************************内部使用错误码从400000开始，不对外体现***********************************************
    ***********************************************************************************************************************************/
    
    /**
     * 群组会议已经存在
     */
    GROUP_CONF_ALREADY_EXIST(400000, "The Group Conference Already Exist."),
    
    /**
     *混编群组支持抢权时，不再支持抢人操作
     */
    MIXGROUP_NO_ROB_RADIO(400001,
        "Users cannot preep the floor when the value of Whether the hybrid group supports floor preemption is set to Yes."),
        
    /**
     * 混编支持抢权时，不支持放权操作
     */
    MIXGROUP_NOT_GRANT_IN_SUPPORT(400002,
        "The chairman cannot grant the floor to a user when the value of Whether the hybrid group supports floor preemption is set to Yes."),
        
    /**
     * 混编支持抢权时，不支持收权操作
     */
    MIXGROUP_NOT_RETAKE_RIGHT_IN_SUPPORT(400003,
        "The chairman cannot revoke the floor when the value of Whether the hybrid group supports floor preemption is set to Yes."),
        
    /**
     * 无线上行PTT时，CDB的组呼状态未刷到In Operation
     */
    UP_PTT_NEED_GROUPCALL(400004, "The radio group need group call before up ptt process"),
    
    /**
     * 话权优先抢占
     */
    PTT_PREEMPTION(400005, "Ptt preemption."),
    
    /*
     * 收到同一无线用户抢权保护
     */
    PTT_SAME_USER_CONFLICT(400006, "Same user Ptt CONFLICT."),
    
    ERR_SDK_UPORTAL_REPEAT_LOGIN(70001, "Repeated logins to the uPortal."),
    
    ERR_SDK_UPORTAL_NOT_LOGIN(70000, "The user is not login.");
    
    /**
     * 错误码
     */
    private int code;
    
    /**
     * 错误描述，国际化KEY
     */
    private String desc;
    
    /**
     * 
     * @Title:ErrorCode
     * @Description:构造函数
     * @param code
     * @param desc
     */
    private ErrorCode(int code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }
    
    /**
     * 
     * @Title: getCode
     * @Description:
     * @return: int
     */
    public int getCode()
    {
        return code;
    }
    
    /**
     * 
     * @Title: getCode
     * @Description: 
     * @param locale
     * @param args
     * @return: int
     */
    public String getDesc(Object... args)
    {
        return MessageFormat.format(desc, args);
    }
}
