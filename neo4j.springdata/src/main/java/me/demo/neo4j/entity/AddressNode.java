package me.demo.neo4j.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressNode implements Cloneable {
  private String GUID;
  @JsonProperty("code")
  private String 地址编码;
  @JsonProperty("adtext")
  private String[] 地址实全称;
  @JsonProperty("assiText")
  private String[] 地址辅助全称;

  private String 地址节全称;
  @JsonProperty("abbr")
  private String 地址节简称;
  @JsonProperty("alias")
  private String 地址节别称;
  @JsonProperty("fuzzy")
  private String[] 地址节模糊名;
  @JsonProperty("fuzzypy2")
  private String[] 模糊名模糊音;
  @JsonProperty("pinyin")
  private String 拼音全称;
  @JsonProperty("py")
  private String 拼音首字母;
  @JsonProperty("fuzzypy")
  private String[] 模糊拼音;
  @JsonProperty("en")
  private String 英文全称;
  @JsonProperty("enabbr")
  private String 英文简称;

  @JsonProperty("long")
  private double 地理经度;
  @JsonProperty("lati")
  private double 地理纬度;
  @JsonProperty("accu")
  private double 地理精度;

  @JsonProperty("spapriority")
  private int 空间优先级;
  @JsonProperty("aggnum")
  private String 聚合编号;
  @JsonProperty("catcode")
  private String 分类代码;
  @JsonProperty("catname")
  private String 分类名称;
  @JsonProperty("anno")
  private String 标注样式;
  @JsonProperty("statcomm")
  private String 状态备注;
  @JsonProperty("maincomm")
  private String 维护备注;

  @JsonProperty("reg")
  private String 所属行政区划代码;

  private int 序号; // Address中标识AddressPath顺序
  @JsonProperty("ruleabbr")
  private String 规范地址节简称;

  @JsonIgnore
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
  @JsonIgnore
  private Set<AddressNode> preAddressNodes;

  private Set<String> preAddressNodeGUIDs;

  /**
   * 除父地址节以外的其他前序地址节集合，冗余面向AddressTree
   * 
   * @Embedded 暂不采用MongoDB的多层嵌套存储
   */
  @JsonIgnore
  private Set<AddressNode> ancAddressNodes;

  private Set<String> ancAddressNodeGUIDs;

  /**
   * 兄弟地址节（具体相同或类同父地址节点的地址节）集合，冗余面向AddressTree
   * 
   * @Embedded 暂不采用MongoDB的多层嵌套存储
   */
  @JsonIgnore
  private Set<AddressNode> siblingAddressNodes;

  private Set<String> siblingAddressNodeGUIDs;


  /**
   * 后续地址节
   */
  @JsonIgnore
  private AddressNode sucAddressNode;

  /**
   * 前序地址节
   */
  @JsonIgnore
  private AddressNode preAddressNode;


  public AddressNode() {
	super();
	this.sucAddressNodes = new HashSet<AddressNode>();
	this.sucAddressNodeGUIDs = new HashSet<String>();
	this.preAddressNodes = new HashSet<AddressNode>();
	this.preAddressNodeGUIDs = new HashSet<String>();
	this.ancAddressNodes = new HashSet<AddressNode>();
	this.ancAddressNodeGUIDs = new HashSet<String>();

	this.GUID = UUID.randomUUID().toString();
  }

  public AddressNode(String guid, String 地址编码, String 地址节全称) {
	super();
	this.sucAddressNodes = new HashSet<AddressNode>();
	this.sucAddressNodeGUIDs = new HashSet<String>();
	this.preAddressNodes = new HashSet<AddressNode>();
	this.preAddressNodeGUIDs = new HashSet<String>();
	this.ancAddressNodes = new HashSet<AddressNode>();
	this.ancAddressNodeGUIDs = new HashSet<String>();

	this.GUID = guid;
	this.地址编码 = 地址编码;
	this.地址节全称 = 地址节全称;
  }

  public AddressNode(String guid, String 地址编码, String 地址节全称, String 规范地址节简称) {
	super();
	this.sucAddressNodes = new HashSet<AddressNode>();
	this.sucAddressNodeGUIDs = new HashSet<String>();
	this.preAddressNodes = new HashSet<AddressNode>();
	this.preAddressNodeGUIDs = new HashSet<String>();
	this.ancAddressNodes = new HashSet<AddressNode>();
	this.ancAddressNodeGUIDs = new HashSet<String>();

	this.GUID = guid;
	this.地址编码 = 地址编码;
	this.地址节全称 = 地址节全称;
	this.规范地址节简称 = 规范地址节简称;
  }

  public AddressNode(String 地址编码, String 地址节全称, String 规范地址节简称, double 地理经度, double 地理纬度, int 空间优先级, String 聚合编号) {
	super();
	this.sucAddressNodes = new HashSet<AddressNode>();
	this.sucAddressNodeGUIDs = new HashSet<String>();
	this.preAddressNodes = new HashSet<AddressNode>();
	this.preAddressNodeGUIDs = new HashSet<String>();
	this.ancAddressNodes = new HashSet<AddressNode>();
	this.ancAddressNodeGUIDs = new HashSet<String>();

	this.GUID = UUID.randomUUID().toString();
	this.地址编码 = 地址编码;
	this.地址节全称 = 地址节全称;
	this.规范地址节简称 = 规范地址节简称;

	this.地理经度 = 地理经度;
	this.地理纬度 = 地理纬度;
	this.空间优先级 = 空间优先级;
	this.聚合编号 = 聚合编号;

	double[][] coordinates = new double[1][2];
	coordinates[0][0] = 地理经度;
	coordinates[0][1] = 地理纬度;
  }

  public AddressNode(String guid, String 地址编码, String 地址节全称, String 规范地址节简称, double 地理经度, double 地理纬度, int 空间优先级, String 聚合编号) {
	super();
	this.sucAddressNodes = new HashSet<AddressNode>();
	this.sucAddressNodeGUIDs = new HashSet<String>();
	this.preAddressNodes = new HashSet<AddressNode>();
	this.preAddressNodeGUIDs = new HashSet<String>();
	this.ancAddressNodes = new HashSet<AddressNode>();
	this.ancAddressNodeGUIDs = new HashSet<String>();

	this.GUID = guid;
	this.地址编码 = 地址编码;
	this.地址节全称 = 地址节全称;
	this.规范地址节简称 = 规范地址节简称;
	this.地理经度 = 地理经度;
	this.地理纬度 = 地理纬度;
	this.空间优先级 = 空间优先级;
	this.聚合编号 = 聚合编号;

	double[][] coordinates = new double[1][2];
	coordinates[0][0] = 地理经度;
	coordinates[0][1] = 地理纬度;

  }

  public AddressNode(String guid, String 地址编码, String 地址节全称, String 规范地址节简称, double 地理经度, double 地理纬度, int 空间优先级, String 聚合编号, String 分类代码,
	  String 分类名称) {
	super();
	this.sucAddressNodes = new HashSet<AddressNode>();
	this.sucAddressNodeGUIDs = new HashSet<String>();
	this.preAddressNodes = new HashSet<AddressNode>();
	this.preAddressNodeGUIDs = new HashSet<String>();
	this.ancAddressNodes = new HashSet<AddressNode>();
	this.ancAddressNodeGUIDs = new HashSet<String>();

	this.GUID = guid;
	this.地址编码 = 地址编码;
	this.地址节全称 = 地址节全称;
	this.规范地址节简称 = 规范地址节简称;
	this.地理经度 = 地理经度;
	this.地理纬度 = 地理纬度;
	this.空间优先级 = 空间优先级;
	this.聚合编号 = 聚合编号;
	this.分类代码 = 分类代码;
	this.分类名称 = 分类名称;


  }

  @JsonIgnore
  public AddressNode copy() {
	AddressNode result = new AddressNode(GUID, 地址编码, 地址节全称, 规范地址节简称, 地理经度, 地理纬度, 空间优先级, 聚合编号, 分类代码, 分类名称);
	result.set地址实全称(地址实全称);
	result.set地址节简称(地址节简称);
	result.set地址节别称(地址节别称);
	result.set地址节模糊名(地址节模糊名);
	result.set模糊名模糊音(模糊名模糊音);
	result.set拼音全称(拼音全称);
	result.set拼音首字母(拼音首字母);
	result.set模糊拼音(模糊拼音);
	result.set英文全称(英文全称);
	result.set英文简称(英文简称);
	result.set序号(序号);
	return result;
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
	this.sucAddressNodeGUIDs.add(addressNode.getGUID());
  }

  /**
   * 采用该方法添加前序地址节（父地址节），而非getPreAddressNode().add
   * 
   * @param addressNode
   */
  @JsonIgnore
  public void addPreAddressNode(AddressNode addressNode) {
	this.preAddressNodes.add(addressNode);
	this.preAddressNodeGUIDs.add(addressNode.getGUID());
  }

  /**
   * 采用该方法添加前序地址节（除父地址节），而非getAncAddressNode().add
   * 
   * @param addressNode
   */
  @JsonIgnore
  public void addAncAddressNode(AddressNode addressNode) {
	this.ancAddressNodes.add(addressNode);
	this.ancAddressNodeGUIDs.add(addressNode.getGUID());
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

  public String getGUID() {
	return GUID;
  }

  public void setGUID(String gUID) {
	GUID = gUID;
  }

  public String get地址编码() {
	return 地址编码;
  }

  public void set地址编码(String 地址编码) {
	this.地址编码 = 地址编码;
  }


  public String[] get地址实全称() {
	return 地址实全称;
  }

  public void set地址实全称(String[] 地址实全称) {
	this.地址实全称 = 地址实全称;
  }

  public String[] get地址辅助全称() {
	if (地址辅助全称 == null) {
	  return new String[] {};
	} else {
	  return 地址辅助全称;
	}
  }

  public void set地址辅助全称(String[] 地址辅助全称) {
	this.地址辅助全称 = 地址辅助全称;
  }

  public String get地址节全称() {
	return 地址节全称;
  }

  public void set地址节全称(String 地址节全称) {
	this.地址节全称 = 地址节全称;
  }

  public String get地址节简称() {
	return 地址节简称;
  }

  public void set地址节简称(String 地址节简称) {
	this.地址节简称 = 地址节简称;
  }

  public String get地址节别称() {
	return 地址节别称;
  }

  public void set地址节别称(String 地址节别称) {
	this.地址节别称 = 地址节别称;
  }

  public String get拼音全称() {
	return 拼音全称;
  }

  public void set拼音全称(String 拼音全称) {
	this.拼音全称 = 拼音全称;
  }

  public String get拼音首字母() {
	return 拼音首字母;
  }

  public void set拼音首字母(String 拼音首字母) {
	this.拼音首字母 = 拼音首字母;
  }

  public String get英文全称() {
	return 英文全称;
  }

  public void set英文全称(String 英文全称) {
	this.英文全称 = 英文全称;
  }

  public String get英文简称() {
	return 英文简称;
  }

  public void set英文简称(String 英文简称) {
	this.英文简称 = 英文简称;
  }

  public double get地理经度() {
	return 地理经度;
  }

  public void set地理经度(double 地理经度) {
	this.地理经度 = 地理经度;
  }

  public double get地理纬度() {
	return 地理纬度;
  }

  public void set地理纬度(double 地理纬度) {
	this.地理纬度 = 地理纬度;
  }

  public double get地理精度() {
	return 地理精度;
  }

  public void set地理精度(double 地理精度) {
	this.地理精度 = 地理精度;
  }

  public int get空间优先级() {
	return 空间优先级;
  }

  public void set空间优先级(int 空间优先级) {
	this.空间优先级 = 空间优先级;
  }

  public String get聚合编号() {
	return 聚合编号;
  }

  public void set聚合编号(String 聚合编号) {
	this.聚合编号 = 聚合编号;
  }

  public String get标注样式() {
	return 标注样式;
  }

  public void set标注样式(String 标注样式) {
	this.标注样式 = 标注样式;
  }

  public int get序号() {
	return 序号;
  }

  public void set序号(int 序号) {
	this.序号 = 序号;
  }

  public String get规范地址节简称() {
	return 规范地址节简称;
  }

  public String get分类代码() {
	return 分类代码;
  }

  public void set分类代码(String 分类代码) {
	this.分类代码 = 分类代码;
  }

  public String get分类名称() {
	return 分类名称;
  }

  public void set分类名称(String 分类名称) {
	this.分类名称 = 分类名称;
  }

  public void set规范地址节简称(String 规范地址节简称) {
	this.规范地址节简称 = 规范地址节简称;
  }

  public String get状态备注() {
	return 状态备注;
  }

  public void set状态备注(String 状态备注) {
	this.状态备注 = 状态备注;
  }

  public String get维护备注() {
	return 维护备注;
  }

  public void set维护备注(String 维护备注) {
	this.维护备注 = 维护备注;
  }


  public String get所属行政区划代码() {
	return 所属行政区划代码;
  }

  public void set所属行政区划代码(String 所属行政区划代码) {
	this.所属行政区划代码 = 所属行政区划代码;
  }

  public String[] get地址节模糊名() {
	return 地址节模糊名;
  }

  public void set地址节模糊名(String[] 地址节模糊名) {
	this.地址节模糊名 = 地址节模糊名;
  }

  public String[] get模糊名模糊音() {
	return 模糊名模糊音;
  }

  public void set模糊名模糊音(String[] 模糊名模糊音) {
	this.模糊名模糊音 = 模糊名模糊音;
  }

  public String[] get模糊拼音() {
	return 模糊拼音;
  }

  public void set模糊拼音(String[] 模糊拼音) {
	this.模糊拼音 = 模糊拼音;
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

	if (!this.getGUID().equals(addressNode.getGUID()))
	  return false;
	return true;
  }

  @Override
  public int hashCode() {
	int result = this.getGUID().hashCode();
	return result;
  }
}
