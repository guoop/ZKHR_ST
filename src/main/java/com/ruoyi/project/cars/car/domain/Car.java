package com.ruoyi.project.cars.car.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 车辆表 tb_car
 * 
 * @author admin
 * @date 2019-04-25
 */
public class Car extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Long id;
	/** 车牌号
	 * @date 2019-08-12 18:44  carNum 修改成 licenseNumber
	 * */
	private String carNum;
	/**  */
	private Integer carNo;
	/** 车辆类型: 大,中,小 */
	private String carType;
	/** 所属 */
	private String nature;
	/**  */
	private String owner;
	/** 车主电话 */
	private String ownerPhone;
	/** 方量 */
	private BigDecimal fangl;
	/** 砂浆方量 */
	private BigDecimal shajiangfl;
	/** 是否有加长抖 */
	private String jcdou;
	/**皮重*/
	private BigDecimal carWeight;
	/*湿拌砂浆*/
	private BigDecimal shibanfl;
	private String isOtherCar;
	private Integer everyPrivilege;
	private Double height;
	private Date signTime;


	private String sqlWhere;
	private Boolean flag;
	/**
	 * 定位器设备号
	 */
	private String imei;
	/**
	 * 车架号
	 */
	private String vin;
	/**
	 * 定位器设备Sim卡号
	 */
	private String mobile;
	/**
	 * 定位器设备型号
	 */
	private String type;

	/**
	 * 设备名称 批量绑定设备时
	 * @return
	 */
    private String deviceName;
	/**
	 * 设备名称  绑定单台设备时
	 */
	private String name;
	/**
	 * 车牌号  与 carNum 一样  数据库存对应的是carNum
	 */
	private String licenseNumber;



	public String getLicenseNumber() {
		return licenseNumber;
	}

	public Car setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
		this.carNum = licenseNumber;
		setCarNum(licenseNumber);
		return this;
	}

	/**
	 * 车主姓名  与 owner 字段一样   数据库对应的是owner
	 */
	private String  carOwner;

	public String getCarOwner() {
		return carOwner;
	}

	public Car setCarOwner(String carOwner) {
		this.carOwner = carOwner;
		return this;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getIsOtherCar() {
		return isOtherCar;
	}

	public void setIsOtherCar(String isOtherCar) {
		this.isOtherCar = isOtherCar;
	}

	public Integer getEveryPrivilege() {
		return everyPrivilege;
	}

	public void setEveryPrivilege(Integer everyPrivilege) {
		this.everyPrivilege = everyPrivilege;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	private boolean isTasking = false;

	public boolean isTasking() {
		return isTasking;
	}

	public void setTasking(boolean tasking) {
		isTasking = tasking;
	}

	/** 岗位组 */
	private Long[] driverIds;

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	public Long[] getDriverIds() {
		return driverIds;
	}

	public void setDriverIds(Long[] driverIds) {
		this.driverIds = driverIds;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setCarNum(String carNum) 
	{
		this.carNum = carNum;
	}

	public String getCarNum() 
	{
		return carNum;
	}
	public void setCarNo(Integer carNo) 
	{
		this.carNo = carNo;
	}

	public Integer getCarNo() 
	{
		return carNo;
	}
	public void setCarType(String carType) 
	{
		this.carType = carType;
	}

	public String getCarType() 
	{
		return carType;
	}
	public void setNature(String nature) 
	{
		this.nature = nature;
	}

	public String getNature() 
	{
		return nature;
	}
	public void setOwner(String owner) 
	{
		this.owner = owner;
	}

	public String getOwner() 
	{
		return owner;
	}
	public void setOwnerPhone(String ownerPhone) 
	{
		this.ownerPhone = ownerPhone;
	}

	public String getOwnerPhone() 
	{
		return ownerPhone;
	}
	public void setFangl(BigDecimal fangl) 
	{
		this.fangl = fangl;
	}

	public BigDecimal getFangl() 
	{
		return fangl;
	}
	public void setShajiangfl(BigDecimal shajiangfl) 
	{
		this.shajiangfl = shajiangfl;
	}

	public BigDecimal getShajiangfl() 
	{
		return shajiangfl;
	}

	public String getJcdou() {
		return jcdou;
	}

	public void setJcdou(String jcdou) {
		this.jcdou = jcdou;
	}

	public BigDecimal getCarWeight() {
		return carWeight;
	}

	public void setCarWeight(BigDecimal carWeight) {
		this.carWeight = carWeight;
	}

	public BigDecimal getShibanfl() {
		return shibanfl;
	}

	public void setShibanfl(BigDecimal shibanfl) {
		this.shibanfl = shibanfl;
	}

	public String getImei() {
		return imei;
	}

	public Car setImei(String imei) {
		this.imei = imei;
		return this;
	}

	public String getVin() {
		return vin;
	}

	public Car setVin(String vin) {
		this.vin = vin;
		return this;
	}

	public String getMobile() {
		return mobile;
	}

	public Car setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public String getType() {
	return type;
   }
	public Car setType(String type) {
		this.type = type;
		return this;
	}

	public Car setDeviceName(String deviceName) {
		this.deviceName = deviceName;
		return this;
	}
	public String getDeviceName() {
		return deviceName;
	}

	public String getName() {
		return name;
	}

	public Car setName(String name) {
		this.name = deviceName;
		return this;
	}
}
