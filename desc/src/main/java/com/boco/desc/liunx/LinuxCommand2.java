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
	public final static String DEFAULT_GATEWAY="ifconfig|grep mask |awk '{print $4}'|head -1";
	
	//物理地址
	public final static String MAC_ADDRESS="ifconfig -a";
	
	//查看应用的安装路径
	public  static String COMPONENT_PATH="whereis";
	
	//查看使用的应用的账号
	public  static String COMPONENT_USER="ps -ef | grep ";
	//网卡地址
	public final static String NET_CARD_ADDRESS="ifconfig";
	//tomcat服务器主机地址发现
	public final static String TOMCAT_PROCESS="ps -ef|grep tomcat|awk '{print $2}'";
	//tomcat服务器主机地址发现
		public final static String TOMCAT_ADDRESS="ps -ef|grep tomcat";
	//tomcat服务器主机端口发现
		public static String TOMCAT_PORT="netstat -tnulp |grep ";

}
