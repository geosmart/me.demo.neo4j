package demo.neo4j.jcypher.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AddressNode implements Cloneable {
  private String _id;
   
  private String code;

  private String[] adtext; 
  private String[] assiText;
 
  private String text; 
  private String abbr; 
  private String alias; 
  private String[] fuzzy; 
  private String[] fuzzypy2; 
  private String pinyin; 
  private String py; 
  private String[] fuzzypy; 
  private String en; 
  private String enabbr;
 
  private double longi;
  private double lati; 
  private double accu;
 
  private int spapriority; 
  private String aggnum; 
  private String catcode; 
  private String catname; 
  private String anno; 
  private String statcomm; 
  private String maincomm;

  private String reg;
  // Address中标识AddressPath顺序
  private int 序号;
  private String ruleabbr;


  private AddressLocation location;
  /**
   * 后继地址节集合，面向构建AddressTree
   * 
   * @Embedded 暂不采用MongoDB的多层嵌套存储
   */
  private Set<AddressNode> sucAddressNodes;

  private Set<String> sucAddressNodeGUIDs;

  /**
   * 前序地址节（父地址节）集合，面向构建AddressTree
   * 
   * @Embedded 暂不采用MongoDB的多层嵌套存储
   */
  private Set<AddressNode> preAddressNodes;

  private Set<String> preAddressNodeGUIDs;

  /**
   * 除父地址节以外的其他前序地址节集合，冗余面向AddressTree
   * 
   * @Embedded 暂不采用MongoDB的多层嵌套存储
   */
  private Set<AddressNode> ancAddressNodes;

  private Set<String> ancAddressNodeGUIDs;

  /**
   * 兄弟地址节（具体相同或类同父地址节点的地址节）集合，冗余面向AddressTree
   * 
   * @Embedded 暂不采用MongoDB的多层嵌套存储
   */
  private Set<AddressNode> siblingAddressNodes;

  private Set<String> siblingAddressNodeGUIDs;


  /**
   * 后续地址节
   */
  private AddressNode sucAddressNode;

  /**
   * 前序地址节
   */
  private AddressNode preAddressNode;


  public AddressNode() {
	super();
	this.sucAddressNodes = new HashSet<AddressNode>();
	this.sucAddressNodeGUIDs = new HashSet<String>();
	this.preAddressNodes = new HashSet<AddressNode>();
	this.preAddressNodeGUIDs = new HashSet<String>();
	this.ancAddressNodes = new HashSet<AddressNode>();
	this.ancAddressNodeGUIDs = new HashSet<String>();

	this._id = UUID.randomUUID().toString();
  }

  public AddressNode(String _id, String 地址编码, String 地址节全称) {
	super();
	this.sucAddressNodes = new HashSet<AddressNode>();
	this.sucAddressNodeGUIDs = new HashSet<String>();
	this.preAddressNodes = new HashSet<AddressNode>();
	this.preAddressNodeGUIDs = new HashSet<String>();
	this.ancAddressNodes = new HashSet<AddressNode>();
	this.ancAddressNodeGUIDs = new HashSet<String>();

	this._id = _id;
	this.code = 地址编码;
	this.text = 地址节全称;
  }

  public AddressNode(String _id, String 地址编码, String 地址节全称, String 规范地址节简称) {
	super();
	this.sucAddressNodes = new HashSet<AddressNode>();
	this.sucAddressNodeGUIDs = new HashSet<String>();
	this.preAddressNodes = new HashSet<AddressNode>();
	this.preAddressNodeGUIDs = new HashSet<String>();
	this.ancAddressNodes = new HashSet<AddressNode>();
	this.ancAddressNodeGUIDs = new HashSet<String>();

	this._id = _id;
	this.code = 地址编码;
	this.text = 地址节全称;
	this.ruleabbr = 规范地址节简称;
  }

  public AddressNode(String 地址编码, String 地址节全称, String 规范地址节简称, double 地理经度, double 地理纬度, int 空间优先级, String 聚合编号) {
	super();
	this.sucAddressNodes = new HashSet<AddressNode>();
	this.sucAddressNodeGUIDs = new HashSet<String>();
	this.preAddressNodes = new HashSet<AddressNode>();
	this.preAddressNodeGUIDs = new HashSet<String>();
	this.ancAddressNodes = new HashSet<AddressNode>();
	this.ancAddressNodeGUIDs = new HashSet<String>();

	this._id = UUID.randomUUID().toString();
	this.code = 地址编码;
	this.text = 地址节全称;
	this.ruleabbr = 规范地址节简称;

	this.longi = 地理经度;
	this.lati = 地理纬度;
	this.spapriority = 空间优先级;
	this.aggnum = 聚合编号;

	double[][] coordinates = new double[1][2];
	coordinates[0][0] = 地理经度;
	coordinates[0][1] = 地理纬度;
  }

  public AddressNode(String _id, String 地址编码, String 地址节全称, String 规范地址节简称, double 地理经度, double 地理纬度, int 空间优先级, String 聚合编号) {
	super();
	this.sucAddressNodes = new HashSet<AddressNode>();
	this.sucAddressNodeGUIDs = new HashSet<String>();
	this.preAddressNodes = new HashSet<AddressNode>();
	this.preAddressNodeGUIDs = new HashSet<String>();
	this.ancAddressNodes = new HashSet<AddressNode>();
	this.ancAddressNodeGUIDs = new HashSet<String>();

	this._id = _id;
	this.code = 地址编码;
	this.text = 地址节全称;
	this.ruleabbr = 规范地址节简称;
	this.longi = 地理经度;
	this.lati = 地理纬度;
	this.spapriority = 空间优先级;
	this.aggnum = 聚合编号;

	double[][] coordinates = new double[1][2];
	coordinates[0][0] = 地理经度;
	coordinates[0][1] = 地理纬度;

  }

  public AddressNode(String _id, String 地址编码, String 地址节全称, String 规范地址节简称, double 地理经度, double 地理纬度, int 空间优先级, String 聚合编号, String 分类代码,
	  String 分类名称) {
	super();
	this.sucAddressNodes = new HashSet<AddressNode>();
	this.sucAddressNodeGUIDs = new HashSet<String>();
	this.preAddressNodes = new HashSet<AddressNode>();
	this.preAddressNodeGUIDs = new HashSet<String>();
	this.ancAddressNodes = new HashSet<AddressNode>();
	this.ancAddressNodeGUIDs = new HashSet<String>();

	this._id = _id;
	this.code = 地址编码;
	this.text = 地址节全称;
	this.ruleabbr = 规范地址节简称;
	this.longi = 地理经度;
	this.lati = 地理纬度;
	this.spapriority = 空间优先级;
	this.aggnum = 聚合编号;
	this.catcode = 分类代码;
	this.catname = 分类名称;
  }


  @Override
  public AddressNode clone() {
	AddressNode result = null;
	try {
	  result = (AddressNode) super.clone();
	} catch (CloneNotSupportedException exp) {
	  exp.printStackTrace();
	}
	return result;
  }

  /**
   * 获取包括本地址节在内的所有后续地址节集合
   * 
   * @return
   */
  @JsonIgnore
  public Set<AddressNode> getAllSucAddressNodes() {
	Set<AddressNode> result = new HashSet<AddressNode>();
	result.add(this);
	for (AddressNode sucAddressNode : this.getSucAddressNodes()) {
	  result.addAll(sucAddressNode.getAllSucAddressNodes());
	}
	return result;
  }

  /**
   * 获取包括本地址节在内的所有前序地址节集合
   * 
   * @return
   */
  @JsonIgnore
  public Set<AddressNode> getAllPreAddressNodes() {
	Set<AddressNode> result = new HashSet<AddressNode>();
	result.add(this);
	for (AddressNode preAddressNode : this.getPreAddressNodes()) {
	  result.addAll(preAddressNode.getAllPreAddressNodes());
	}
	return result;
  }

  /**
   * 采用该方法添加后继地址节，而非getSucAddressNode().add
   * 
   * @param addressNode
   */
  @JsonIgnore
  public void addSucAddressNode(AddressNode addressNode) {
	this.sucAddressNodes.add(addressNode);
	this.sucAddressNodeGUIDs.add(addressNode.get_id());
  }

  /**
   * 采用该方法添加前序地址节（父地址节），而非getPreAddressNode().add
   * 
   * @param addressNode
   */
  @JsonIgnore
  public void addPreAddressNode(AddressNode addressNode) {
	this.preAddressNodes.add(addressNode);
	this.preAddressNodeGUIDs.add(addressNode.get_id());
  }

  /**
   * 采用该方法添加前序地址节（除父地址节），而非getAncAddressNode().add
   * 
   * @param addressNode
   */
  @JsonIgnore
  public void addAncAddressNode(AddressNode addressNode) {
	this.ancAddressNodes.add(addressNode);
	this.ancAddressNodeGUIDs.add(addressNode.get_id());
  }

  public Set<AddressNode> getSucAddressNodes() {
	return sucAddressNodes;
  }

  public void setSucAddressNodes(Set<AddressNode> sucAddressNodes) {
	this.sucAddressNodes = sucAddressNodes;
  }

  public Set<AddressNode> getPreAddressNodes() {
	return preAddressNodes;
  }

  public void setPreAddressNodes(Set<AddressNode> preAddressNodes) {
	this.preAddressNodes = preAddressNodes;
  }

  public Set<String> getSucAddressNodeGUIDs() {
	return sucAddressNodeGUIDs;
  }

  public void setSucAddressNodeGUIDs(Set<String> sucAddressNodeGUIDs) {
	this.sucAddressNodeGUIDs = sucAddressNodeGUIDs;
  }

  public Set<String> getPreAddressNodeGUIDs() {
	return preAddressNodeGUIDs;
  }

  public void setPreAddressNodeGUIDs(Set<String> preAddressNodeGUIDs) {
	this.preAddressNodeGUIDs = preAddressNodeGUIDs;
  }


  public Set<AddressNode> getAncAddressNodes() {
	return ancAddressNodes;
  }

  public void setAncAddressNodes(Set<AddressNode> ancAddressNodes) {
	this.ancAddressNodes = ancAddressNodes;
  }

  public Set<String> getAncAddressNodeGUIDs() {
	return ancAddressNodeGUIDs;
  }

  public void setAncAddressNodeGUIDs(Set<String> ancAddressNodeGUIDs) {
	this.ancAddressNodeGUIDs = ancAddressNodeGUIDs;
  }

  public Set<AddressNode> getSiblingAddressNodes() {
	return siblingAddressNodes;
  }

  public void setSiblingAddressNodes(Set<AddressNode> siblingAddressNodes) {
	this.siblingAddressNodes = siblingAddressNodes;
  }

  public Set<String> getSiblingAddressNodeGUIDs() {
	return siblingAddressNodeGUIDs;
  }

  public void setSiblingAddressNodeGUIDs(Set<String> siblingAddressNodeGUIDs) {
	this.siblingAddressNodeGUIDs = siblingAddressNodeGUIDs;
  }

  public AddressNode getSucAddressNode() {
	return sucAddressNode;
  }

  public void setSucAddressNode(AddressNode sucAddressNode) {
	this.sucAddressNode = sucAddressNode;
  }

  public AddressNode getPreAddressNode() {
	return preAddressNode;
  }

  public void setPreAddressNode(AddressNode preAddressNode) {
	this.preAddressNode = preAddressNode;
  }


  public String get地址编码() {
	return code;
  }

  public void set地址编码(String 地址编码) {
	this.code = 地址编码;
  }



  @Override
  public boolean equals(Object other) {
	if (this == other)
	  return true;
	if (other == null)
	  return false;
	if (!(other instanceof AddressNode))
	  return false;

	final AddressNode addressNode = (AddressNode) other;

	if (!this.get_id().equals(addressNode.get_id()))
	  return false;
	return true;
  }

  @Override
  public int hashCode() {
	int result = this.get_id().hashCode();
	return result;
  }

  public String getCode() {
	return code;
  }

  public void setCode(String code) {
	this.code = code;
  }

  public String[] getAdtext() {
	return adtext;
  }

  public void setAdtext(String[] adtext) {
	this.adtext = adtext;
  }

  public String[] getAssiText() {
	return assiText;
  }

  public void setAssiText(String[] assiText) {
	this.assiText = assiText;
  }

  public String getText() {
	return text;
  }

  public void setText(String text) {
	this.text = text;
  }

  public String getAbbr() {
	return abbr;
  }

  public void setAbbr(String abbr) {
	this.abbr = abbr;
  }

  public String getAlias() {
	return alias;
  }

  public void setAlias(String alias) {
	this.alias = alias;
  }

  public String[] getFuzzy() {
	return fuzzy;
  }

  public void setFuzzy(String[] fuzzy) {
	this.fuzzy = fuzzy;
  }

  public String[] getFuzzypy2() {
	return fuzzypy2;
  }

  public void setFuzzypy2(String[] fuzzypy2) {
	this.fuzzypy2 = fuzzypy2;
  }

  public String getPinyin() {
	return pinyin;
  }

  public void setPinyin(String pinyin) {
	this.pinyin = pinyin;
  }

  public String getPy() {
	return py;
  }

  public void setPy(String py) {
	this.py = py;
  }

  public String[] getFuzzypy() {
	return fuzzypy;
  }

  public void setFuzzypy(String[] fuzzypy) {
	this.fuzzypy = fuzzypy;
  }

  public String getEn() {
	return en;
  }

  public void setEn(String en) {
	this.en = en;
  }

  public String getEnabbr() {
	return enabbr;
  }

  public void setEnabbr(String enabbr) {
	this.enabbr = enabbr;
  }

  public double getLongi() {
	return longi;
  }

  public void setLongi(double longi) {
	this.longi = longi;
  }

  public double getLati() {
	return lati;
  }

  public void setLati(double lati) {
	this.lati = lati;
  }

  public double getAccu() {
	return accu;
  }

  public void setAccu(double accu) {
	this.accu = accu;
  }

  public int getSpapriority() {
	return spapriority;
  }

  public void setSpapriority(int spapriority) {
	this.spapriority = spapriority;
  }

  public String getAggnum() {
	return aggnum;
  }

  public void setAggnum(String aggnum) {
	this.aggnum = aggnum;
  }

  public String getCatcode() {
	return catcode;
  }

  public void setCatcode(String catcode) {
	this.catcode = catcode;
  }

  public String getCatname() {
	return catname;
  }

  public void setCatname(String catname) {
	this.catname = catname;
  }

  public String getAnno() {
	return anno;
  }

  public void setAnno(String anno) {
	this.anno = anno;
  }

  public String getStatcomm() {
	return statcomm;
  }

  public void setStatcomm(String statcomm) {
	this.statcomm = statcomm;
  }

  public String getMaincomm() {
	return maincomm;
  }

  public void setMaincomm(String maincomm) {
	this.maincomm = maincomm;
  }

  public String getReg() {
	return reg;
  }

  public void setReg(String reg) {
	this.reg = reg;
  }

  public int get序号() {
	return 序号;
  }

  public void set序号(int 序号) {
	this.序号 = 序号;
  }

  public String getRuleabbr() {
	return ruleabbr;
  }

  public void setRuleabbr(String ruleabbr) {
	this.ruleabbr = ruleabbr;
  }

  public AddressLocation getLocation() {
	return location;
  }

  public void setLocation(AddressLocation location) {
	this.location = location;
  }

  public String get_id() {
	return _id;
  }

  public void set_id(String _id) {
	this._id = _id;
  }
}
