package com.boco.desc.liunx;

public class LinuxCommand {
	//资产名称
	public final static String ASSET_NAME="uname -n";
	//厂商
	public final static String MANU_FACTURER="uname";
	//资产型号
	public final static String ASSET_TYPE="cat /etc/issue |awk '{print $1}'|head -1'";
	//版本
	public final static String VERSION="cat /etc/redhat-release";
	
	//获得子网掩码
	public final static String MASK="ifconfig|grep Mask |awk '{print $3}'|head -1";
	
	//物理地址
	public final static String MAC_ADDRESS="ifconfig|grep HWaddr|awk '{print $5}'|head -1";
	
	//查看应用的安装路径
	public  static String COMPONENT_PATH="whereis";
	
	//查看使用的应用的账号
	public  static String COMPONENT_USER="ps -ef | grep ";
	//网卡地址
	public final static String NET_CARD_ADDRESS="ifconfig";
		//获得默认网关
	public final static String GATE_WAY="cat /etc/resolv.conf |grep nameserver|awk '{print $2}'|head -1";
}
