package com.boco.desc.liunx;

public class LinuxCommand2 {
	//资产名称
	public final static String ASSET_NAME="uname -n";
	//厂商
	public final static String MANU_FACTURER="uname";
	//资产型号
	public final static String ASSET_TYPE="cat /etc/redhat-release|awk '{print $1}'";
	//版本
	public final static String VERSION="cat /etc/redhat-release";
	
	//默认网关
	public final static String MASK="ifconfig|grep mask |awk '{print $4}'|head -1";
	
	//物理地址
	public final static String MAC_ADDRESS="ifconfig -a";
	
	//查看应用的安装路径
	public  static String COMPONENT_PATH="whereis";
	
	//查看使用的应用的账号
	public  static String COMPONENT_USER="ps -ef | grep ";
	//网卡地址
	public final static String NET_CARD_ADDRESS="ifconfig";

	//获得默认网关
	public final static String GATE_WAY="cat /etc/resolv.conf |grep nameserver|awk '{print $2}'|head -1";
	//获得广播地址
	public final static String BROADCAST="ifconfig|grep broadcast|awk '{print $6}'";
}
