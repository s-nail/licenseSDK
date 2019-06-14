package com.hundsun.licensesdk.common.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * Created by jiayq24996 on 2019-06-04
 */

@XStreamAlias("module")
public class Module {
    /**
     *
     */
    @XStreamAlias("module_name")
    private String moduleName;
    @XStreamAlias("module_no")
    private String moduleNo;
    @XStreamAlias("begin_date")
    private String beginDate;
    @XStreamAlias("expire_date")
    private String expireDate;
    @XStreamAlias("max_connections")
    private Integer maxConnections;
    @XStreamAlias("flow_control")
    private String flowControl;
    @XStreamAlias("extend_field_set")
    private List<ExtendField> extendFieldSet;
    @XStreamAlias("api_set")
    private List<Api> apiSet;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleNo() {
        return moduleNo;
    }

    public void setModuleNo(String moduleNo) {
        this.moduleNo = moduleNo;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(Integer maxConnections) {
        this.maxConnections = maxConnections;
    }

    public String getFlowControl() {
        return flowControl;
    }

    public void setFlowControl(String flowControl) {
        this.flowControl = flowControl;
    }

    public List<ExtendField> getExtendFieldSet() {
        return extendFieldSet;
    }

    public void setExtendFieldSet(List<ExtendField> extendFieldSet) {
        this.extendFieldSet = extendFieldSet;
    }

    public List<Api> getApiSet() {
        return apiSet;
    }

    public void setApiSet(List<Api> apiSet) {
        this.apiSet = apiSet;
    }
}
