package com.ruoyi.pill.test;

import lombok.Data;

@Data
public class Ticket {
    private String AssignedGroup;
    private String ServiceDeskGroup;
    private String RegisteredForLocation;
    private String RegisteredForActualService;
    private String DisplayLabel;
    private String Description;
    private String Urgency;
    private String ImpactScope;
    private String Priority;
    private String Test;

    public Ticket(){
        this.AssignedGroup = "10128103";
        this.ServiceDeskGroup = "10128103";
        this.RegisteredForLocation = "13345";
        this.RegisteredForActualService = "4803068";
        this.DisplayLabel = "TEST CNST1232 FW WAN1 OFFLINE";
        this.Description = "<p><span style=\"font-size: 1em;\">Location: CNST1677</span><br></p><p>Problem device type: FW</p><p>Problem name: WAN1&WAN2 OFFLINE</p><p>Problem occurred time: Tue Sep 05 15:27:21 CST 2023</p><p>Problem description: Now status wan1:failed,wan2:failed<br></p>";
        this.Urgency = "SlightDisruption";
        this.ImpactScope = "SiteOrDepartment";
        this.Priority = "MediumPriority";
//        this.Test = "testtestset";
    }
}
