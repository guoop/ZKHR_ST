package com.ruoyi.project.duties.tasks.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.project.cars.tasksMixformula.domain.TasksMixformula;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.springframework.data.annotation.Transient;

import java.sql.Timestamp;
import java.util.Date;
import java.math.BigDecimal;
import java.util.List;

/**
 * 任务单表 tb_tasks
 * 
 * @author admin
 * @date 2019-04-25
 */
public class Tasks extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 任务标号 */
	private String id;
	/** 任务名称 */
	private String name;
	private String planOrderNo;
	/** 自动派发间隔时间(单位min) */
	private Integer subTime;
	/** 好坏活 */
	private String isGoodJob;
	/** 是否掐方 */
	private String isPinch;
	/** 是否需要加长斗 */
	private String isJcdou;
	/** 车辆类型: 0.小  1.中 2.大 */
	private Integer carType;
	/** 掐方量 */
	private BigDecimal pinchFl;
	/** 任务总方量 */
	private BigDecimal totalFl;
	private BigDecimal shajiangfl;
	/** 产品型号 */
	private String productKind;
	/** 开始时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	/** 开始时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date arriveTime;
	/** 目的地 */
	private String targetAddr;
	/** 目的地经度 */
	private String lon;
	/** 目的地纬度 */
	private String lat;
	/**起始地*/
	private String startAddr;
	/**起始地经度*/
	private String startLng;
	/**起始地维度*/
	private String startLat;
	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createtime;
	/** 任务状态 0.待分配 1.任务进行中 2.任务已完成 */
	private Integer status;
	/** 是否暂停 */
	private String pause;
	/** 是否自动 */
	private String isAuto;
	/**浇筑方式*/
	private String waterMethod;
	/**浇筑部位*/
	private String waterPart;
	/**现场坍落度*/
	private String locationTanluodu;
	/**坍落度*/
	private String tanLuodu;
	/**抗渗等级*/
	private String ksLevel;

	private BigDecimal ljfangliang;
	private String sqlWhere;
	private String productLine;//产线
	//水泥标号
	private String cementLevel;
	//外加剂
	private String wjj;
	//沙子等级
	private String sandLevel;
	//石子等级
	private String stoneLevel;
	//发货人
	private String sendor;
	//操作员
	private String operator;
	//序号
	private String sno;

	public String getCementLevel() {
		return cementLevel;
	}

	public void setCementLevel(String cementLevel) {
		this.cementLevel = cementLevel;
	}

	public String getWjj() {
		return wjj;
	}

	public void setWjj(String wjj) {
		this.wjj = wjj;
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

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	@Transient
	private List<TasksMixformula> mixList;

	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastCarTime;
	private Integer privilege;

	private BigDecimal maxcarfl;//每辆车最大方量-用于桩基、容易下陷路面
	private BigDecimal carsubfl;//每辆车需要减少的方量--用于陡坡倒车进入工地，防止洒出


	public List<TasksMixformula> getMixList() {
		return mixList;
	}

	public void setMixList(List<TasksMixformula> mixList) {
		this.mixList = mixList;
	}

	public BigDecimal getMaxcarfl() {
		return maxcarfl;
	}

	public void setMaxcarfl(BigDecimal maxcarfl) {
		this.maxcarfl = maxcarfl;
	}

	public BigDecimal getCarsubfl() {
		return carsubfl;
	}

	public void setCarsubfl(BigDecimal carsubfl) {
		this.carsubfl = carsubfl;
	}

	public Integer getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Integer privilege) {
		this.privilege = privilege;
	}

	private String isCarMoney;
	private String isCobblestone;
	private String timeoutfee;
	private String bufang;
	private BigDecimal price;
	private String officerMobile;
	private String officer;
	private String receivor;
	private String receiver;//收货单位
	private String receivorMobile;
	private String doDeptPart;//施工部位
	private String doDept;//施工单位
	private String isOtherArea;//是否跨县
	private String financePause;
	private String isMixture;//是否配比标志位
	private String isSchedule;//调度是否完善标志位
	private String mixNo;//配比单号
	private String mixor;//配比人
	private Date mixtime;//配比时间
	private int cnt=0;
	//如果该字段为null，则证明是第一车，如果有值就说明不是第一车
	private Long taskId;
	private Integer carCnt;
	private String cancelBy;//取消人
	private String contact;//施工单位联系电话
	private Integer planCarCnt;
	private Integer mixCar;//所需车辆最小承载方量
	private Integer maxCar;//所需车辆最大承载方量
	private Double height;
	private Long createId;
	private Integer SyncStatus;//同步状态
	private Integer SyncStatus2;//从机同步状态
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date planEndTime;
	private String cartypeList;//选用车辆类型列表
	private String carList;//选用车辆列表
	private Integer targetCarcnt;
	private Integer topping;//是否置顶
	private Boolean timeToTask;

	public Boolean getTimeToTask() {
		return timeToTask;
	}

	public void setTimeToTask(Boolean timeToTask) {
		this.timeToTask = timeToTask;
	}

	public Integer getTopping() {
		return topping;
	}

	public void setTopping(Integer topping) {
		this.topping = topping;
	}

	public Integer getTargetCarcnt() {
		return targetCarcnt;
	}

	public void setTargetCarcnt(Integer targetCarcnt) {
		this.targetCarcnt = targetCarcnt;
	}

	public String getCarList() {
		return carList;
	}

	public void setCarList(String carList) {
		this.carList = carList;
	}

	public Integer getSyncStatus() {
		return SyncStatus;
	}

	public void setSyncStatus(Integer syncStatus) {
		SyncStatus = syncStatus;
	}

	public Integer getSyncStatus2() {
		return SyncStatus2;
	}

	public void setSyncStatus2(Integer syncStatus2) {
		SyncStatus2 = syncStatus2;
	}

	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Integer getMixCar() {
		return mixCar;
	}

	public void setMixCar(Integer mixCar) {
		this.mixCar = mixCar;
	}

	public Integer getMaxCar() {
		return maxCar;
	}

	public void setMaxCar(Integer maxCar) {
		this.maxCar = maxCar;
	}


	public String getCartypeList() {
		return cartypeList;
	}

	public void setCartypeList(String cartypeList) {
		this.cartypeList = cartypeList;
	}

	public Date getPlanEndTime() {
		return planEndTime;
	}

	public void setPlanEndTime(Date planEndTime) {
		this.planEndTime = planEndTime;
	}

	public Integer getPlanCarCnt() {
		return planCarCnt;
	}

	public void setPlanCarCnt(Integer planCarCnt) {
		this.planCarCnt = planCarCnt;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDoDept() {
		return doDept;
	}

	public void setDoDept(String doDept) {
		this.doDept = doDept;
	}

	public Date getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}

	public String getCancelBy() {
		return cancelBy;
	}

	public void setCancelBy(String cancelBy) {
		this.cancelBy = cancelBy;
	}

	public Integer getCarCnt() {
		return carCnt;
	}

	public void setCarCnt(Integer carCnt) {
		this.carCnt = carCnt;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public BigDecimal getShajiangfl() {
		return shajiangfl;
	}

	public void setShajiangfl(BigDecimal shajiangfl) {
		this.shajiangfl = shajiangfl;
	}

	public Date getMixtime() {
		return mixtime;
	}

	public void setMixtime(Date mixtime) {
		this.mixtime = mixtime;
	}

	public String getIsMixture() {
		return isMixture;
	}

	public void setIsMixture(String isMixture) {
		this.isMixture = isMixture;
	}

	public String getIsSchedule() {
		return isSchedule;
	}

	public void setIsSchedule(String isSchedule) {
		this.isSchedule = isSchedule;
	}

	public String getMixNo() {
		return mixNo;
	}

	public void setMixNo(String mixNo) {
		this.mixNo = mixNo;
	}

	public String getMixor() {
		return mixor;
	}

	public void setMixor(String mixor) {
		this.mixor = mixor;
	}

	public Date getMixTime() {
		return mixtime;
	}

	public void setMixTime(Date mixtime) {
		this.mixtime = mixtime;
	}

	public String getIsOtherArea() {
		return isOtherArea;
	}

	public void setIsOtherArea(String isOtherArea) {
		this.isOtherArea = isOtherArea;
	}

	public String getFinancePause() {
		return financePause;
	}

	public void setFinancePause(String financePause) {
		this.financePause = financePause;
	}

	public String getOfficer() {
		return officer;
	}

	public void setOfficer(String officer) {
		this.officer = officer;
	}

	public String getOfficerMobile() {
		return officerMobile;
	}

	public void setOfficerMobile(String officerMobile) {
		this.officerMobile = officerMobile;
	}

	public String getIsCarMoney() {
		return isCarMoney;
	}

	public void setIsCarMoney(String isCarMoney) {
		this.isCarMoney = isCarMoney;
	}

	public String getIsCobblestone() {
		return isCobblestone;
	}

	public void setIsCobblestone(String isCobblestone) {
		this.isCobblestone = isCobblestone;
	}

	public String getTimeoutfee() {
		return timeoutfee;
	}

	public void setTimeoutfee(String timeoutfee) {
		this.timeoutfee = timeoutfee;
	}

	public String getBufang() {
		return bufang;
	}

	public void setBufang(String bufang) {
		this.bufang = bufang;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getLastCarTime() {
		return lastCarTime;
	}

	public void setLastCarTime(Date lastCarTime) {
		this.lastCarTime = lastCarTime;
	}

	public BigDecimal getLjfangliang() {
		return ljfangliang;
	}

	public void setLjfangliang(BigDecimal ljfangliang) {
		this.ljfangliang = ljfangliang;
	}

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	public String getWaterMethod() {
		return waterMethod;
	}

	public void setWaterMethod(String waterMethod) {
		this.waterMethod = waterMethod;
	}

	public String getWaterPart() {
		return waterPart;
	}

	public void setWaterPart(String waterPart) {
		this.waterPart = waterPart;
	}

	public String getLocationTanluodu() {
		return locationTanluodu;
	}

	public void setLocationTanluodu(String locationTanluodu) {
		this.locationTanluodu = locationTanluodu;
	}

	public String getTanLuodu() {
		return tanLuodu;
	}

	public void setTanLuodu(String tanLuodu) {
		this.tanLuodu = tanLuodu;
	}

	public String getKsLevel() {
		return ksLevel;
	}

	public void setKsLevel(String ksLevel) {
		this.ksLevel = ksLevel;
	}

	public String getPlanOrderNo() {
		return planOrderNo;
	}

	public void setPlanOrderNo(String planOrderNo) {
		this.planOrderNo = planOrderNo;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setSubTime(Integer subTime) 
	{
		this.subTime = subTime;
	}

	public Integer getSubTime() 
	{
		return subTime;
	}
	public void setIsGoodJob(String isGoodJob) 
	{
		this.isGoodJob = isGoodJob;
	}

	public String getIsGoodJob() 
	{
		return isGoodJob;
	}
	public void setIsPinch(String isPinch) 
	{
		this.isPinch = isPinch;
	}

	public String getIsPinch() 
	{
		return isPinch;
	}
	public void setIsJcdou(String isJcdou) 
	{
		this.isJcdou = isJcdou;
	}

	public String getIsJcdou() 
	{
		return isJcdou;
	}
	public void setCarType(Integer carType) 
	{
		this.carType = carType;
	}

	public Integer getCarType() 
	{
		return carType;
	}
	public void setPinchFl(BigDecimal pinchFl) 
	{
		this.pinchFl = pinchFl;
	}

	public BigDecimal getPinchFl() 
	{
		return pinchFl;
	}
	public void setTotalFl(BigDecimal totalFl) 
	{
		this.totalFl = totalFl;
	}

	public BigDecimal getTotalFl() 
	{
		return totalFl;
	}
	public void setProductKind(String productKind) 
	{
		this.productKind = productKind;
	}

	public String getProductKind() 
	{
		return productKind;
	}
	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	public Date getStartTime()
	{
		return startTime;
	}
	public void setTargetAddr(String targetAddr) 
	{
		this.targetAddr = targetAddr;
	}

	public String getTargetAddr() 
	{
		return targetAddr;
	}
	public void setLon(String lon) 
	{
		this.lon = lon;
	}

	public String getLon() 
	{
		return lon;
	}
	public void setLat(String lat) 
	{
		this.lat = lat;
	}

	public String getLat() 
	{
		return lat;
	}
	public void setCreatetime(Date createtime) 
	{
		this.createtime = createtime;
	}

	public Date getCreatetime() 
	{
		return createtime;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setPause(String pause) 
	{
		this.pause = pause;
	}

	public String getPause() 
	{
		return pause;
	}
	public void setIsAuto(String isAuto) 
	{
		this.isAuto = isAuto;
	}

	public String getIsAuto() 
	{
		return isAuto;
	}

	public String getReceivor() {
		return receivor;
	}

	public void setReceivor(String receivor) {
		this.receivor = receivor;
	}

	public String getReceivorMobile() {
		return receivorMobile;
	}

	public void setReceivorMobile(String receivorMobile) {
		this.receivorMobile = receivorMobile;
	}

	public String getDoDeptPart() {
		return doDeptPart;
	}

	public void setDoDeptPart(String doDeptPart) {
		this.doDeptPart = doDeptPart;
	}

	public String getStartAddr() {
		return startAddr;
	}

	public Tasks setStartAddr(String startAddr) {
		this.startAddr = startAddr;
		return this;
	}

	public String getStartLng() {
		return startLng;
	}

	public Tasks setStartLng(String startLng) {
		this.startLng = startLng;
		return this;
	}

	public String getStartLat() {
		return startLat;
	}

	public Tasks setStartLat(String startLat) {
		this.startLat = startLat;
		return this;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("subTime", getSubTime())
            .append("isGoodJob", getIsGoodJob())
            .append("isPinch", getIsPinch())
            .append("isJcdou", getIsJcdou())
            .append("carType", getCarType())
            .append("pinchFl", getPinchFl())
            .append("totalFl", getTotalFl())
            .append("productKind", getProductKind())
            .append("startTime", getStartTime())
            .append("targetAddr", getTargetAddr())
            .append("lon", getLon())
            .append("lat", getLat())
            .append("createtime", getCreatetime())
            .append("status", getStatus())
            .append("pause", getPause())
            .append("isAuto", getIsAuto())
			.append("startAddr",getStartAddr())
			.append("startLng",getStartLng())
			.append("startLat",getStartLat())
            .toString();
    }
}
