
package com.huawei.cdsdemo.bean;
public class RecordData
{
    private String corpIDs = null;

    private String recordID;

    private String confId;

    private String recordFile;

    private String recordFolder;

    private String callerRecordFile = null;

    private String calleeRecordFile = null;

    private String userAccount;

    private String groupName;

    private String confSubject;

    private String caller = null;

    private String callee = null;

    private String startTime;

    private int duration;

    private boolean localRecord;
    
    public RecordData()
    {
    
    }

    public RecordData(String corpIDs, String recordID, String confId, String recordFile, String recordFolder,
        String callerRecordFile, String calleeRecordFile, String userAccount, String groupName, String confSubject,
        String caller, String callee, String startTime, int duration, boolean localRecord)
    {
        super();
        this.corpIDs = corpIDs;
        this.recordID = recordID;
        this.confId = confId;
        this.recordFile = recordFile;
        this.recordFolder = recordFolder;
        this.callerRecordFile = callerRecordFile;
        this.calleeRecordFile = calleeRecordFile;
        this.userAccount = userAccount;
        this.groupName = groupName;
        this.confSubject = confSubject;
        this.caller = caller;
        this.callee = callee;
        this.startTime = startTime;
        this.duration = duration;
        this.localRecord = localRecord;
    }

    /**
     * @return the corpID
     */
    public String getCorpIDs()
    {
        return corpIDs;
    }

    /**
     * @param corpID the corpID to set
     */
    public void setCorpIDs(String corpIDs)
    {
        this.corpIDs = corpIDs;
    }

    /**
     * @return the recordID
     */
    public String getRecordID()
    {
        return recordID;
    }

    /**
     * @param recordID the recordID to set
     */
    public void setRecordID(String recordID)
    {
        this.recordID = recordID;
    }

    /**
     * @return the confId
     */
    public String getConfId()
    {
        return confId;
    }

    /**
     * @param confId the confId to set
     */
    public void setConfId(String confId)
    {
        this.confId = confId;
    }

    /**
     * @return the recordFile
     */
    public String getRecordFile()
    {
        return recordFile;
    }

    /**
     * @param recordFile the recordFile to set
     */
    public void setRecordFile(String recordFile)
    {
        this.recordFile = recordFile;
    }

    /**
     * @return the recordFolder
     */
    public String getRecordFolder()
    {
        return recordFolder;
    }

    /**
     * @param recordFolder the recordFolder to set
     */
    public void setRecordFolder(String recordFolder)
    {
        this.recordFolder = recordFolder;
    }

    /**
     * @return the callerRecordFile
     */
    public String getCallerRecordFile()
    {
        return callerRecordFile;
    }

    /**
     * @param callerRecordFile the callerRecordFile to set
     */
    public void setCallerRecordFile(String callerRecordFile)
    {
        this.callerRecordFile = callerRecordFile;
    }

    /**
     * @return the calleeRecordFile
     */
    public String getCalleeRecordFile()
    {
        return calleeRecordFile;
    }

    /**
     * @param calleeRecordFile the calleeRecordFile to set
     */
    public void setCalleeRecordFile(String calleeRecordFile)
    {
        this.calleeRecordFile = calleeRecordFile;
    }

    /**
     * @return the userAccount
     */
    public String getUserAccount()
    {
        return userAccount;
    }

    /**
     * @param userAccount the userAccount to set
     */
    public void setUserAccount(String userAccount)
    {
        this.userAccount = userAccount;
    }

    /**
     * @return the groupName
     */
    public String getGroupName()
    {
        return groupName;
    }

    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    /**
     * @return the confSubject
     */
    public String getConfSubject()
    {
        return confSubject;
    }

    /**
     * @param confSubject the confSubject to set
     */
    public void setConfSubject(String confSubject)
    {
        this.confSubject = confSubject;
    }

    /**
     * @return the caller
     */
    public String getCaller()
    {
        return caller;
    }

    /**
     * @param caller the caller to set
     */
    public void setCaller(String caller)
    {
        this.caller = caller;
    }

    /**
     * @return the callee
     */
    public String getCallee()
    {
        return callee;
    }

    /**
     * @param callee the callee to set
     */
    public void setCallee(String callee)
    {
        this.callee = callee;
    }

    /**
     * @return the startTime
     */
    public String getStartTime()
    {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    /**
     * @return the duration
     */
    public int getDuration()
    {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    /**
     * @return the localRecord
     */
    public boolean isLocalRecord()
    {
        return localRecord;
    }

    /**
     * @param localRecord the localRecord to set
     */
    public void setLocalRecord(boolean localRecord)
    {
        this.localRecord = localRecord;
    }
    
}
