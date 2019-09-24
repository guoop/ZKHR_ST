package com.ruoyi.project.heavlywork.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 三一重工实体
 * </p>
 *
 * @author paulo123
 * @since 2019-08-20
 */
public class Product  {

    private static final long serialVersionUID = 1L;

    private static final Product product = new Product();

    public static Product getInstance(){
        return product;
    }

    private Integer id;
    private Integer ErpID;
    private String Mark;
    private String Code;
    private Date DatTim;
    private String Attribute;
    private String Contract;
    private String Customer;
    private String ProjName;
    private String ProjType;
    private String ProjGrade;
    private Double ProjArea;
    private String ProjAdr;
    private Double Distance;
    private String ConsPos;
    private String Pour;
    private String Variety;
    private String BetLev;
    private String Filter;
    private String Freeze;
    private String Lands;
    private String Cement;
    private String Stone;
    private String BnSize;
    private String AddLiq;
    private String Request;
    private Double MixLast;
    private String Recipe;
    private String MorRec;
    private Double Mete;
    private Date BegTim;
    private Date EndTim;
    private String Attamper;
    private String Flag;
    private String Note;
    private String Vehicle;
    private String Driver;
    private Date ProdTimB;
    private Date ProdTimE;
    private Double ProdMete;
    private Double MorMete;
    private Integer PieCnt;
    private Integer TotVehs;
    private Double TotMete;
    private String Qualitor;
    private String Acceptor;
    private String Operator;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getErpID() {
        return ErpID;
    }

    public void setErpID(Integer ErpID) {
        this.ErpID = ErpID;
    }

    public String getMark() {
        return Mark;
    }

    public void setMark(String Mark) {
        this.Mark = Mark;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public Date getDatTim() {
        return DatTim;
    }

    public void setDatTim(Date DatTim) {
        this.DatTim = DatTim;
    }

    public String getAttribute() {
        return Attribute;
    }

    public void setAttribute(String Attribute) {
        this.Attribute = Attribute;
    }

    public String getContract() {
        return Contract;
    }

    public void setContract(String Contract) {
        this.Contract = Contract;
    }

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String Customer) {
        this.Customer = Customer;
    }

    public String getProjName() {
        return ProjName;
    }

    public void setProjName(String ProjName) {
        this.ProjName = ProjName;
    }

    public String getProjType() {
        return ProjType;
    }

    public void setProjType(String ProjType) {
        this.ProjType = ProjType;
    }

    public String getProjGrade() {
        return ProjGrade;
    }

    public void setProjGrade(String ProjGrade) {
        this.ProjGrade = ProjGrade;
    }

    public Double getProjArea() {
        return ProjArea;
    }

    public void setProjArea(Double ProjArea) {
        this.ProjArea = ProjArea;
    }

    public String getProjAdr() {
        return ProjAdr;
    }

    public void setProjAdr(String ProjAdr) {
        this.ProjAdr = ProjAdr;
    }

    public Double getDistance() {
        return Distance;
    }

    public void setDistance(Double Distance) {
        this.Distance = Distance;
    }

    public String getConsPos() {
        return ConsPos;
    }

    public void setConsPos(String ConsPos) {
        this.ConsPos = ConsPos;
    }

    public String getPour() {
        return Pour;
    }

    public void setPour(String Pour) {
        this.Pour = Pour;
    }

    public String getVariety() {
        return Variety;
    }

    public void setVariety(String Variety) {
        this.Variety = Variety;
    }

    public String getBetLev() {
        return BetLev;
    }

    public void setBetLev(String BetLev) {
        this.BetLev = BetLev;
    }

    public String getFilter() {
        return Filter;
    }

    public void setFilter(String Filter) {
        this.Filter = Filter;
    }

    public String getFreeze() {
        return Freeze;
    }

    public void setFreeze(String Freeze) {
        this.Freeze = Freeze;
    }

    public String getLands() {
        return Lands;
    }

    public void setLands(String Lands) {
        this.Lands = Lands;
    }

    public String getCement() {
        return Cement;
    }

    public void setCement(String Cement) {
        this.Cement = Cement;
    }

    public String getStone() {
        return Stone;
    }

    public void setStone(String Stone) {
        this.Stone = Stone;
    }

    public String getBnSize() {
        return BnSize;
    }

    public void setBnSize(String BnSize) {
        this.BnSize = BnSize;
    }

    public String getAddLiq() {
        return AddLiq;
    }

    public void setAddLiq(String AddLiq) {
        this.AddLiq = AddLiq;
    }

    public String getRequest() {
        return Request;
    }

    public void setRequest(String Request) {
        this.Request = Request;
    }

    public Double getMixLast() {
        return MixLast;
    }

    public void setMixLast(Double MixLast) {
        this.MixLast = MixLast;
    }

    public String getRecipe() {
        return Recipe;
    }

    public void setRecipe(String Recipe) {
        this.Recipe = Recipe;
    }

    public String getMorRec() {
        return MorRec;
    }

    public void setMorRec(String MorRec) {
        this.MorRec = MorRec;
    }

    public Double getMete() {
        return Mete;
    }

    public void setMete(Double Mete) {
        this.Mete = Mete;
    }

    public Date getBegTim() {
        return BegTim;
    }

    public void setBegTim(Date BegTim) {
        this.BegTim = BegTim;
    }

    public Date getEndTim() {
        return EndTim;
    }

    public void setEndTim(Date EndTim) {
        this.EndTim = EndTim;
    }

    public String getAttamper() {
        return Attamper;
    }

    public void setAttamper(String Attamper) {
        this.Attamper = Attamper;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String Flag) {
        this.Flag = Flag;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public String getVehicle() {
        return Vehicle;
    }

    public void setVehicle(String Vehicle) {
        this.Vehicle = Vehicle;
    }

    public String getDriver() {
        return Driver;
    }

    public void setDriver(String Driver) {
        this.Driver = Driver;
    }

    public Date getProdTimB() {
        return ProdTimB;
    }

    public void setProdTimB(Date ProdTimB) {
        this.ProdTimB = ProdTimB;
    }

    public Date getProdTimE() {
        return ProdTimE;
    }

    public void setProdTimE(Date ProdTimE) {
        this.ProdTimE = ProdTimE;
    }

    public Double getProdMete() {
        return ProdMete;
    }

    public void setProdMete(Double ProdMete) {
        this.ProdMete = ProdMete;
    }

    public Double getMorMete() {
        return MorMete;
    }

    public void setMorMete(Double MorMete) {
        this.MorMete = MorMete;
    }

    public Integer getPieCnt() {
        return PieCnt;
    }

    public void setPieCnt(Integer PieCnt) {
        this.PieCnt = PieCnt;
    }

    public Integer getTotVehs() {
        return TotVehs;
    }

    public void setTotVehs(Integer TotVehs) {
        this.TotVehs = TotVehs;
    }

    public Double getTotMete() {
        return TotMete;
    }

    public void setTotMete(Double TotMete) {
        this.TotMete = TotMete;
    }

    public String getQualitor() {
        return Qualitor;
    }

    public void setQualitor(String Qualitor) {
        this.Qualitor = Qualitor;
    }

    public String getAcceptor() {
        return Acceptor;
    }

    public void setAcceptor(String Acceptor) {
        this.Acceptor = Acceptor;
    }

    public String getOperator() {
        return Operator;
    }

    public void setOperator(String Operator) {
        this.Operator = Operator;
    }


    @Override
    public String toString() {
        return "Product{" +
        "id=" + id +
        ", ErpID=" + ErpID +
        ", Mark=" + Mark +
        ", Code=" + Code +
        ", DatTim=" + DatTim +
        ", Attribute=" + Attribute +
        ", Contract=" + Contract +
        ", Customer=" + Customer +
        ", ProjName=" + ProjName +
        ", ProjType=" + ProjType +
        ", ProjGrade=" + ProjGrade +
        ", ProjArea=" + ProjArea +
        ", ProjAdr=" + ProjAdr +
        ", Distance=" + Distance +
        ", ConsPos=" + ConsPos +
        ", Pour=" + Pour +
        ", Variety=" + Variety +
        ", BetLev=" + BetLev +
        ", Filter=" + Filter +
        ", Freeze=" + Freeze +
        ", Lands=" + Lands +
        ", Cement=" + Cement +
        ", Stone=" + Stone +
        ", BnSize=" + BnSize +
        ", AddLiq=" + AddLiq +
        ", Request=" + Request +
        ", MixLast=" + MixLast +
        ", Recipe=" + Recipe +
        ", MorRec=" + MorRec +
        ", Mete=" + Mete +
        ", BegTim=" + BegTim +
        ", EndTim=" + EndTim +
        ", Attamper=" + Attamper +
        ", Flag=" + Flag +
        ", Note=" + Note +
        ", Vehicle=" + Vehicle +
        ", Driver=" + Driver +
        ", ProdTimB=" + ProdTimB +
        ", ProdTimE=" + ProdTimE +
        ", ProdMete=" + ProdMete +
        ", MorMete=" + MorMete +
        ", PieCnt=" + PieCnt +
        ", TotVehs=" + TotVehs +
        ", TotMete=" + TotMete +
        ", Qualitor=" + Qualitor +
        ", Acceptor=" + Acceptor +
        ", Operator=" + Operator +
        "}";
    }
}
