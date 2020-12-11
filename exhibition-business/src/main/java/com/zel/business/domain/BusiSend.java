package com.zel.business.domain;

import com.zel.system.domain.SysDept;
import com.zel.system.domain.SysUser;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 发货信息 busi_send
 * @author andy
 */
public class BusiSend {

    /**
     * 发货ID
     */
    private Long sendId;

    /**
     *发货单号
     */
    private String sendNumber;

    /**
     * 展会id
     */
    private Long exhibitionId;

    /**
     * 物流名称
     */
    private String logisticsName;

    /**
     * 物流单号
     */
    private String logisticsNumber;

    /**
     * 状态
     * （1：保存  2：已发货 3：已收货）
     */
    private Integer status;

    /**
     * 发货人
     */
    private String sendBy;

    /**
     * 发货时间
     */
    private Date sendTime;

    /**
     * 收货人
     */
    private Long receiveBy;

    /**
     * 收货地址
     */
    private String receiveAddress;

    /**
     * 收货部门ID
     */
    private Long  deptId;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标识
     * 1：正常  2：删除
     */
    private Integer del;

    /**
     * 备注
     */
    private String remark;

    /**
     * 展会信息
     */
    private BusiExhibition busiExhibitions;

    /*收货部门*/
    private String deptName;

    /*收货人*/
    private String receiveName;

    /*发货人*/
    private String sendName;

    /*创建人*/
    private String createName;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    private List<Map> listMap;

    private Integer sendQuantity;

    public Integer getSendQuantity() {
        return sendQuantity;
    }

    public void setSendQuantity(Integer sendQuantity) {
        this.sendQuantity = sendQuantity;
    }

    public List<Map> getListMap() {
        return listMap;
    }

    public void setListMap(List<Map> listMap) {
        this.listMap = listMap;
    }

    public Long getSendId() {
        return sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

    public String getSendNumber() {
        return sendNumber;
    }

    public Long getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(Long exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    public void setSendNumber(String sendNumber) {
        this.sendNumber = sendNumber;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public String getLogisticsNumber() {
        return logisticsNumber;
    }

    public void setLogisticsNumber(String logisticsNumber) {
        this.logisticsNumber = logisticsNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSendBy() {
        return sendBy;
    }

    public void setSendBy(String sendBy) {
        this.sendBy = sendBy;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Long getReceiveBy() {
        return receiveBy;
    }

    public void setReceiveBy(Long receiveBy) {
        this.receiveBy = receiveBy;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BusiExhibition getBusiExhibitions() {
        return busiExhibitions;
    }

    public void setBusiExhibitions(BusiExhibition busiExhibitions) {
        this.busiExhibitions = busiExhibitions;
    }
}
