package com.huawei.cdsdemo.bean;

public class PubParamters
{
    
    private Integer maxGrpMonCount = 10;
    
    private Integer maxGrpSubCount = 10;
    
    private Integer maxDisMonCount = 10;
    
    private Integer maxDisSubCount = 10;
    
    private Integer maxTempGrpMemberCount = 36;
    
    private boolean isPttDependsSelect = true;
    
    private Integer maxPttTimeoutInterval = 70;
    
    private String recordPath = "opt/root/";
    
    private boolean isAutoDownCall = false;
    
    private Integer maxMonOpTimeoutInterval = 60;
    
    private Integer maxPttOpTimeoutInterval = 2000;
    
    private boolean isMixSupportGrant = true;
    
    private boolean optimizedPtt = true;
    
    public PubParamters()
    {
    
    }
    
    public PubParamters(Integer maxGrpMonCount, Integer maxGrpSubCount, Integer maxDisMonCount, Integer maxDisSubCount,
        Integer maxTempGrpMemberCount, boolean isPttDependsSelect, boolean isAutoDownCall,
        Integer maxMonOpTimeoutInterval, Integer maxPttOpTimeoutInterval, Integer maxPttTimeoutInterval,
        String recordPath, boolean isMixSupportGrant, boolean optimizedPtt)
    {
        super();
        this.maxGrpMonCount = maxGrpMonCount;
        this.maxGrpSubCount = maxGrpSubCount;
        this.maxDisMonCount = maxDisMonCount;
        this.maxDisSubCount = maxDisSubCount;
        this.maxTempGrpMemberCount = maxTempGrpMemberCount;
        this.isPttDependsSelect = isPttDependsSelect;
        this.isAutoDownCall = isAutoDownCall;
        this.maxMonOpTimeoutInterval = maxMonOpTimeoutInterval;
        this.maxPttOpTimeoutInterval = maxPttOpTimeoutInterval;
        this.maxPttTimeoutInterval = maxPttTimeoutInterval;
        this.recordPath = recordPath;
        this.isMixSupportGrant = isMixSupportGrant;
        this.setOptimizedPtt(optimizedPtt);
    }
    
  

    public PubParamters(Integer maxGrpMonCount, Integer maxGrpSubCount, Integer maxDisMonCount, Integer maxDisSubCount,
            Integer maxTempGrpMemberCount, boolean isPttDependsSelect, Integer maxPttTimeoutInterval,
            String recordPath) {
        super();
        this.maxGrpMonCount = maxGrpMonCount;
        this.maxGrpSubCount = maxGrpSubCount;
        this.maxDisMonCount = maxDisMonCount;
        this.maxDisSubCount = maxDisSubCount;
        this.maxTempGrpMemberCount = maxTempGrpMemberCount;
        this.isPttDependsSelect = isPttDependsSelect;
        this.maxPttTimeoutInterval = maxPttTimeoutInterval;
        this.recordPath = recordPath;
    }

    /**
     * @return the maxGrpMonCount
     */
    public Integer getMaxGrpMonCount()
    {
        return maxGrpMonCount;
    }
    
    /**
     * @param maxGrpMonCount the maxGrpMonCount to set
     */
    public void setMaxGrpMonCount(Integer maxGrpMonCount)
    {
        this.maxGrpMonCount = maxGrpMonCount;
    }
    
    /**
     * @return the maxGrpSubCount
     */
    public Integer getMaxGrpSubCount()
    {
        return maxGrpSubCount;
    }
    
    /**
     * @param maxGrpSubCount the maxGrpSubCount to set
     */
    public void setMaxGrpSubCount(Integer maxGrpSubCount)
    {
        this.maxGrpSubCount = maxGrpSubCount;
    }
    
    /**
     * @return the maxDisMonCount
     */
    public Integer getMaxDisMonCount()
    {
        return maxDisMonCount;
    }
    
    /**
     * @param maxDisMonCount the maxDisMonCount to set
     */
    public void setMaxDisMonCount(Integer maxDisMonCount)
    {
        this.maxDisMonCount = maxDisMonCount;
    }
    
    /**
     * @return the maxDisSubCount
     */
    public Integer getMaxDisSubCount()
    {
        return maxDisSubCount;
    }
    
    /**
     * @param maxDisSubCount the maxDisSubCount to set
     */
    public void setMaxDisSubCount(Integer maxDisSubCount)
    {
        this.maxDisSubCount = maxDisSubCount;
    }
    
    /**
     * @return the maxTempGrpMemberCount
     */
    public Integer getMaxTempGrpMemberCount()
    {
        return maxTempGrpMemberCount;
    }
    
    /**
     * @param maxTempGrpMemberCount the maxTempGrpMemberCount to set
     */
    public void setMaxTempGrpMemberCount(Integer maxTempGrpMemberCount)
    {
        this.maxTempGrpMemberCount = maxTempGrpMemberCount;
    }
    
    /**
     * @return the isPttDependsSelect
     */
    public boolean isPttDependsSelect()
    {
        return isPttDependsSelect;
    }
    
    /**
     * @param isPttDependsSelect the isPttDependsSelect to set
     */
    public void setPttDependsSelect(boolean isPttDependsSelect)
    {
        this.isPttDependsSelect = isPttDependsSelect;
    }
    
    /**
     * @return the isAutoDownCall
     */
    public boolean isAutoDownCall()
    {
        return isAutoDownCall;
    }
    
    /**
     * @param isAutoDownCall the isAutoDownCall to set
     */
    public void setAutoDownCall(boolean isAutoDownCall)
    {
        this.isAutoDownCall = isAutoDownCall;
    }
    
    /**
     * @return the maxMonOpTimeoutInterval
     */
    public Integer getMaxMonOpTimeoutInterval()
    {
        return maxMonOpTimeoutInterval;
    }
    
    /**
     * @param maxMonOpTimeoutInterval the maxMonOpTimeoutInterval to set
     */
    public void setMaxMonOpTimeoutInterval(Integer maxMonOpTimeoutInterval)
    {
        this.maxMonOpTimeoutInterval = maxMonOpTimeoutInterval;
    }
    
    /**
     * @return the maxPttOpTimeoutInterval
     */
    public Integer getMaxPttOpTimeoutInterval()
    {
        return maxPttOpTimeoutInterval;
    }
    
    /**
     * @param maxPttOpTimeoutInterval the maxPttOpTimeoutInterval to set
     */
    public void setMaxPttOpTimeoutInterval(Integer maxPttOpTimeoutInterval)
    {
        this.maxPttOpTimeoutInterval = maxPttOpTimeoutInterval;
    }
    
    /**
     * @return the maxPttTimeoutInterval
     */
    public Integer getMaxPttTimeoutInterval()
    {
        return maxPttTimeoutInterval;
    }
    
    /**
     * @param maxPttTimeoutInterval the maxPttTimeoutInterval to set
     */
    public void setMaxPttTimeoutInterval(Integer maxPttTimeoutInterval)
    {
        this.maxPttTimeoutInterval = maxPttTimeoutInterval;
    }
    
    /**
     * @return the recordPath
     */
    public String getRecordPath()
    {
        return recordPath;
    }
    
    /**
     * @param recordPath the recordPath to set
     */
    public void setRecordPath(String recordPath)
    {
        this.recordPath = recordPath;
    }
    
    /**
     * @return the isMixSupportGrant
     */
    public boolean isMixSupportGrant()
    {
        return isMixSupportGrant;
    }
    
    /**
     * @param isMixSupportGrant the isMixSupportGrant to set
     */
    public void setMixSupportGrant(boolean isMixSupportGrant)
    {
        this.isMixSupportGrant = isMixSupportGrant;
    }

    /**
     * @return the optimizedPtt
     */
    public boolean isOptimizedPtt()
    {
        return optimizedPtt;
    }

    /**
     * @param optimizedPtt the optimizedPtt to set
     */
    public void setOptimizedPtt(boolean optimizedPtt)
    {
        this.optimizedPtt = optimizedPtt;
    }
    
}
