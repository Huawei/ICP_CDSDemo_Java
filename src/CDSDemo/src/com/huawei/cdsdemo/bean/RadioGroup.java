package com.huawei.cdsdemo.bean;

public class RadioGroup
{   
    private String radioTKGroupNumber;
    
    private Integer radioTKGroupType;
    
    private Integer radioMode = 6;
    
    private boolean emergencyCall = false;
    
    private String radioTKGroupDesc;
    
    public RadioGroup(String radioTKGroupNumber, Integer radioTKGroupType, Integer radioMode, boolean emergencyCall,
            String radioTKGroupDesc) 
    {
        super();
        this.radioTKGroupNumber = radioTKGroupNumber;
        this.radioTKGroupType = radioTKGroupType;
        this.radioMode = radioMode;
        this.emergencyCall = emergencyCall;
        this.radioTKGroupDesc = radioTKGroupDesc;
    }

    public RadioGroup(String radioTKGroupNumber, Integer radioTKGroupType,
        String radioTKGroupDesc)
    {
        super();
        this.radioTKGroupNumber = radioTKGroupNumber;
        this.radioTKGroupType = radioTKGroupType;
        this.radioTKGroupDesc = radioTKGroupDesc;
    }
    
    public String getRadioTKGroupNumber()
    {
        return radioTKGroupNumber;
    }
    
    public void setRadioTKGroupNumber(String radioTKGroupNumber)
    {
        this.radioTKGroupNumber = radioTKGroupNumber;
    }
    
    public Integer getRadioTKGroupType()
    {
        return radioTKGroupType;
    }
    
    public void setRadioTKGroupType(Integer radioTKGroupType)
    {
        this.radioTKGroupType = radioTKGroupType;
    }
    
    public void setRadioMode(Integer radioMode)
    {
        this.radioMode = radioMode;
    }
    
    public Integer getRadioMode()
    {
        return radioMode;
    }
    
    public boolean isEmergencyCall()
    {
        return emergencyCall;
    }

    public void setEmergencyCall(boolean emergencyCall)
    {
        this.emergencyCall = emergencyCall;
    }

    public String getRadioTKGroupDesc()
    {
        return radioTKGroupDesc;
    }
    
    public void setRadioTKGroupDesc(String radioTKGroupDesc)
    {
        this.radioTKGroupDesc = radioTKGroupDesc;
    }
}
