package com.huawei.cdsdemo.bean;

public class Record
{
    private String corpId;
    
    private String searchKey;
    
    private String startTime;
    
    private String endTime;
    
    private boolean onlyLocalRecord;
    
    private boolean isConfRecord;
    
    private int pageIndex;
    
    private int pageSize;
    
    public Record(String corpId, String searchKey, String startTime, String endTime, boolean onlyLocalRecord,
        boolean isConfRecord, int pageIndex, int pageSize)
    {
        super();
        this.corpId = corpId;
        this.searchKey = searchKey;
        this.startTime = startTime;
        this.endTime = endTime;
        this.onlyLocalRecord = onlyLocalRecord;
        this.isConfRecord = isConfRecord;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }
    
    /**
     * @return the corpId
     */
    public String getCorpId()
    {
        return corpId;
    }
    
    /**
     * @param corpId the corpId to set
     */
    public void setCorpId(String corpId)
    {
        this.corpId = corpId;
    }
    
    /**
     * @return the searchKey
     */
    public String getSearchKey()
    {
        return searchKey;
    }
    
    /**
     * @param searchKey the searchKey to set
     */
    public void setSearchKey(String searchKey)
    {
        this.searchKey = searchKey;
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
     * @return the endTime
     */
    public String getEndTime()
    {
        return endTime;
    }
    
    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }
    
    /**
     * @return the onlyLocalRecord
     */
    public boolean isOnlyLocalRecord()
    {
        return onlyLocalRecord;
    }
    
    /**
     * @param onlyLocalRecord the onlyLocalRecord to set
     */
    public void setOnlyLocalRecord(boolean onlyLocalRecord)
    {
        this.onlyLocalRecord = onlyLocalRecord;
    }
    
    /**
     * @return the isConfRecord
     */
    public boolean isConfRecord()
    {
        return isConfRecord;
    }
    
    /**
     * @param isConfRecord the isConfRecord to set
     */
    public void setConfRecord(boolean isConfRecord)
    {
        this.isConfRecord = isConfRecord;
    }
    
    /**
     * @return the pageIndex
     */
    public int getPageIndex()
    {
        return pageIndex;
    }
    
    /**
     * @param pageIndex the pageIndex to set
     */
    public void setPageIndex(int pageIndex)
    {
        this.pageIndex = pageIndex;
    }
    
    /**
     * @return the pageSize
     */
    public int getPageSize()
    {
        return pageSize;
    }
    
    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }
    
}
