package com.huawei.cdsdemo.bean;

import java.util.List;

public class RecordList
{

    private int totalCount;

    private int pageIndex;

    private int pageSize;
    
    private int returnCode;

    private String returnDesc;

    private List<RecordData> recordlist;
        
    public RecordList()
    {
    
    }


    public RecordList(int totalCount, int pageIndex, int pageSize, int returnCode, String returnDesc,
        List<RecordData> recordlist)
    {
        super();
        this.totalCount = totalCount;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.returnCode = returnCode;
        this.returnDesc = returnDesc;
        this.recordlist = recordlist;
    }

    /**
     * @return the totalCount
     */
    public int getTotalCount()
    {
        return totalCount;
    }

    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
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

    /**
     * @return the recordlist
     */
    public List<RecordData> getRecordlist()
    {
        return recordlist;
    }

    /**
     * @param recordlist the recordlist to set
     */
    public void setRecordlist(List<RecordData> recordlist)
    {
        this.recordlist = recordlist;
    }

    /**
     * @return the returnCode
     */
    public int getReturnCode()
    {
        return returnCode;
    }

    /**
     * @param returnCode the returnCode to set
     */
    public void setReturnCode(int returnCode)
    {
        this.returnCode = returnCode;
    }

    /**
     * @return the returnDesc
     */
    public String getReturnDesc()
    {
        return returnDesc;
    }

    /**
     * @param returnDesc the returnDesc to set
     */
    public void setReturnDesc(String returnDesc)
    {
        this.returnDesc = returnDesc;
    }
  
}
