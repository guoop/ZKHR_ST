package com.ruoyi.project.duties.tasksCars.domain;

import com.ruoyi.common.utils.LocationUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 任务车辆表 tb_tasks_cars
 * 
 * @author admin
 * @date 2019-04-25
 */
@Configuration
public class TasksCars extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	public static final int TASK_INIT_0 = 0;
	public static final int TASK_RUNING_1 = 1;
	public static final int TASK_SUCCESS_2 = 2;
	public static final int TASK_DELETE = -1;

	
	/** 序号 */
	private Long id;
	/** 任务id */
	private Long taskId;
	@Excel(name="序号")
	private String sno;
	@Excel(name ="流水号")
	private String taskNumber;
	/**任务名称*/
	@Excel(name = "工程名称")
	private String taskName;
	@Excel(name = "工程部位")
	private String waterPart;
	/**  */
	@Excel(name = "车号")
	private Integer carNo;
	//总重量
	@Excel(name = "毛重")
	private BigDecimal grossWeight;
	//皮重量
	@Excel(name = "皮重")
	private BigDecimal carWeight;
	//净重量
	@Excel(name = "净重")
	private BigDecimal netWeight;
	@Excel(name="折方系数")
	@Value("${project.equalRate}")
	private String equalRate;
	//折合方量
	@Excel(name = "折方方量")
	private BigDecimal equalFangliang;
	@Excel(name="累计车次")
	private Integer carCnt;
	@Excel(name ="累计方量")
	private BigDecimal ljfangliang;
	@Excel(name = "强度等级")
	private String productKind;
	@Excel(name="浇筑方式")
	private String waterMethod;
	@Excel(name = "收货人")
	private String receiver;
	@Excel(name = "收货人电话")
	private String receivePhone;
	private String tanluodu;
	//计划方量
	private BigDecimal planFangliang;
	/** 车辆ID */
	private Long carId;
	/** 车牌 */
	private String carBrand;
	/** 是否到达终点 */
	private String isEnd;
	/** 是否出发 */
	private String isStart;
	/** 状态:0.任务分配完成.1.任务完成 */
	private Integer status;
	private boolean isInEnd = false;
	/**接料门号*/
	private String doorNo;
	/**打料主机号*/
	private Integer productLine;

	public String getEqualRate() {
		return equalRate;
	}

	public void setEqualRate(String equalRate) {
		this.equalRate = equalRate;
	}

	//任务开始时间
	private Date startTime;

	//任务结束时间
	private Date endTime;
	/** 运用砂浆方量 */
	private Double shajiangfl;

	private String sqlWhere;

	private String driverMobile;

	private String comment;
	private String urls;
	//任务重点维度
	private String lat;
	//任务终点经度
	private String lng;
	//是否最后一单
	private boolean isLast;
	private String stationStatus;
	/**排队情况*/
	private Integer queueStatus;
	private Date taskTime;
	private BigDecimal price;
	private String officerMobile;
	private String officer;
	private String mixNumber;
	private Boolean isPrivilegeTask;
	private Integer syncStatus;
	private Date signTime;
	private boolean sendTask;
	//司机是否确认订单
	private boolean isSure;
	private Long dispatchTime;
	/**起始经度*/
	private String startLng;
	/**起始维度*/
	private String startLat;


	//水泥标号
	private String cementlevel;
	//外加剂
	private String mixture;
	//沙子等级
	private String sandLevel;
	//石子等级
	private String stoneLevel;
	//发货人
	private String sendor;
	//操作员
	private String operator;
	//发货日期
	private String sendDate;
	//发货时间
	private String sendTime;
	private String kslevel;

	public String getKslevel() {
		return kslevel;
	}

	public void setKslevel(String kslevel) {
		this.kslevel = kslevel;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getCementlevel() {
		return cementlevel;
	}

	public void setCementlevel(String cementlevel) {
		this.cementlevel = cementlevel;
	}

	public String getMixture() {
		return mixture;
	}

	public void setMixture(String mixture) {
		this.mixture = mixture;
	}

	public String getSandLevel() {
		return sandLevel;
	}

	public void setSandLevel(String sandLevel) {
		this.sandLevel = sandLevel;
	}

	public String getStoneLevel() {
		return stoneLevel;
	}

	public void setStoneLevel(String stoneLevel) {
		this.stoneLevel = stoneLevel;
	}

	public String getSendor() {
		return sendor;
	}

	public void setSendor(String sendor) {
		this.sendor = sendor;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getTanluodu() {
		return tanluodu;
	}

	public void setTanluodu(String tanluodu) {
		this.tanluodu = tanluodu;
	}

	public Long getDispatchTime() {
		return dispatchTime;
	}

	public void setDispatchTime(Long dispatchTime) {
		this.dispatchTime = dispatchTime;
	}

	public boolean isSure() {
		return isSure;
	}

	public void setSure(boolean sure) {
		isSure = sure;
	}

	public boolean isSendTask() {
		return sendTask;
	}

	public void setSendTask(boolean sendTask) {
		this.sendTask = sendTask;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public Integer getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(Integer syncStatus) {
		this.syncStatus = syncStatus;
	}

	public BigDecimal getLjfangliang() {
		return ljfangliang;
	}

	public void setLjfangliang(BigDecimal ljfangliang) {
		this.ljfangliang = ljfangliang;
	}

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	public Boolean isPrivilegeTask() {
		return isPrivilegeTask;
	}

	public void setPrivilegeTask(Boolean privilegeTask) {
		isPrivilegeTask = privilegeTask;
	}

	public String getMixNumber() {
		return mixNumber;
	}

	public void setMixNumber(String mixNumber) {
		this.mixNumber = mixNumber;
	}

	public String getProductKind() {
		return productKind;
	}

	public void setProductKind(String productKind) {
		this.productKind = productKind;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceivePhone() {
		return receivePhone;
	}

	public void setReceivePhone(String receivePhone) {
		this.receivePhone = receivePhone;
	}

	public Date getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(Date taskTime) {
		this.taskTime = taskTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getWaterPart() {
		return waterPart;
	}

	public void setWaterPart(String waterPart) {
		this.waterPart = waterPart;
	}

	public String getWaterMethod() {
		return waterMethod;
	}

	public void setWaterMethod(String waterMethod) {
		this.waterMethod = waterMethod;
	}

	public String getOfficerMobile() {
		return officerMobile;
	}

	public void setOfficerMobile(String officerMobile) {
		this.officerMobile = officerMobile;
	}

	public String getOfficer() {
		return officer;
	}

	public void setOfficer(String officer) {
		this.officer = officer;
	}

	public Integer getQueueStatus() {
		return queueStatus;
	}

	public void setQueueStatus(Integer queueStatus) {
		this.queueStatus = queueStatus;
	}

	public Integer getProductLine() {
		return productLine;
	}

	public void setProductLine(Integer productLine) {
		this.productLine = productLine;
	}

	public BigDecimal getPlanFangliang() {
		return planFangliang;
	}

	public void setPlanFangliang(BigDecimal planFangliang) {
		this.planFangliang = planFangliang;
	}

	public Integer getCarCnt() {
		return carCnt;
	}

	public void setCarCnt(Integer carCnt) {
		this.carCnt = carCnt;
	}

	public boolean isInEnd() {
		return isInEnd;
	}

	public void setInEnd(boolean inEnd) {
		isInEnd = inEnd;
	}

	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}


	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getStationStatus() {
		return stationStatus;
	}

	public void setStationStatus(String stationStatus) {
		this.stationStatus = stationStatus;
	}

	public boolean isLast() {
		return isLast;
	}

	public void setLast(boolean last) {
		isLast = last;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	//中间变量
	private Long cnt;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Long getCnt() {
		return cnt;
	}

	public void setCnt(Long cnt) {
		this.cnt = cnt;
	}

	public BigDecimal getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
	}

	public BigDecimal getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(BigDecimal netWeight) {
		this.netWeight = netWeight;
	}

	public BigDecimal getCarWeight() {
		return carWeight;
	}

	public void setCarWeight(BigDecimal carWeight) {
		this.carWeight = carWeight;
	}

	public String getDriverMobile() {
		return driverMobile;
	}

	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}
	private String notifyId;

	public String getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Double getShajiangfl() {
		return shajiangfl;
	}

	public void setShajiangfl(Double shajiangfl) {
		this.shajiangfl = shajiangfl;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setTaskId(Long taskId) 
	{
		this.taskId = taskId;
	}

	public Long getTaskId() 
	{
		return taskId;
	}
	public void setCarId(Long carId) 
	{
		this.carId = carId;
	}

	public Long getCarId() 
	{
		return carId;
	}
	public void setCarNo(Integer carNo) 
	{
		this.carNo = carNo;
	}

	public Integer getCarNo() 
	{
		return carNo;
	}
	public void setCarBrand(String carBrand) 
	{
		this.carBrand = carBrand;
	}

	public String getCarBrand() 
	{
		return carBrand;
	}

	public String isEnd() {
		return isEnd;
	}

	public void setEnd(String end) {
		isEnd = end;
	}

	public String getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(String isEnd) {
		this.isEnd = isEnd;
	}

	public String getIsStart() {
		return isStart;
	}

	public void setIsStart(String isStart) {
		this.isStart = isStart;
	}

	public BigDecimal getEqualFangliang() {
		return equalFangliang;
	}

	public void setEqualFangliang(BigDecimal equalFangliang) {
		this.equalFangliang = equalFangliang;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}

	public String getStartLng() {
		return startLng;
	}

	public TasksCars setStartLng(String startLng) {
		this.startLng = startLng;
		return this;
	}

	public String getStartLat() {
		return startLat;
	}

	public TasksCars setStartLat(String startLat) {
		this.startLat = startLat;
		return this;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("carId", getCarId())
            .append("carNo", getCarNo())
            .append("carBrand", getCarBrand())
            .append("isEnd", isEnd())
            .append("isStart", getIsStart())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
