package com.star.utils;

public class Constants {
	
		public static final String URI_GETZCDAIKUAN = "http://115.28.12.59/didiao/daikuansecondhouse.do";
		public static final String URI_GETDAIKUANBYFAL = "http://115.28.12.59/didiao/daikuanunsolve.do";
		public static final String URI_GETDAIKUANBYID = "http://115.28.12.59/didiao/daikuanid.do";
		public static final String URI_APPLYINFO = "http://115.28.12.59/didiao/adddaikuanapplyinfo.do?";
		public static final String URI_CLIENTLOGIN = "http://115.28.12.59/didiao/login.do?";
		public static final String URI_CLIENTREGISTER = "http://115.28.12.59/didiao/register.do?";
		public static final String URI_APPLYUSER = "http://115.28.12.59/didiao/adduserapplyinfo.do?";
		public static final String URI_APPLYCONTACT = "http://115.28.12.59/didiao/addcontapplyinfo.do?";
		public static final String URI_APPLYBANK = "http://115.28.12.59/didiao/addbankapplyinfo.do?";
		public static final String URI_APPLYPIC = "http://115.28.12.59/didiao/addspicapplyinfo.do?";
		public static final String URI_GETAPPLYINFO = "http://115.28.12.59/didiao/daikuanapplyinfo.do?";
		public static final String URI_GETAPPLYUSER = "http://115.28.12.59/didiao/userapplyinfo.do?";
		public static final String URI_GETAPPLYCONTACT = "http://115.28.12.59/didiao/contapplyinfo.do?";
		public static final String URI_GETAPPLYBANK = "http://115.28.12.59/didiao/bankapplyinfo.do?";
		public static final String URI_GETAPPLYPIC = "http://115.28.12.59/didiao/picapplyinfo.do?";
		public static final String URI_USERCROWDFUND = "http://115.28.12.59/didiao/usercrowdfund.do?";
		public static final String URI_UPLOAD = "http://115.28.12.59/UpFile1/UploadServlet";
		public static final String URI_SENDMSG = "http://115.28.12.59/didiao/sendmessage.do";
		public static final String URI_GETAPPLYINFOSTATE = "http://115.28.12.59/didiao/daikuanfal.do?";
		public static final String URI_GETUSERMONEY = "http://115.28.12.59/didiao/usermoneyuse.do?";
		public static final String URI_CROWDFUNDRECORD = "http://115.28.12.59/didiao/crowdfundbyid.do?";
		public static final String URI_CROWDFUNDUSERRECORD = "http://115.28.12.59/didiao/crowdfundbyuser.do?";
		public static final String URI_USERCENTERINFO = "http://115.28.12.59/didiao/usermoneyuse.do?";
		public static final String URI_GETREPAYMENT = "http://115.28.12.59/didiao/repaymentList.do";
		public static final String URI_GETNEXTPAYMENT = "http://115.28.12.59/didiao/nextrepayment.do";
		public static final String URI_GETBANKCARDINFO = "http://115.28.12.59/didiao/bankcardbyu.do?";
		public static final String URI_ADDBANKCARD = "http://115.28.12.59/didiao/addbankcard.do?";
		public static final String URI_MONEYRECORD = "http://115.28.12.59/didiao/moneyrecorduse.do?";
		public static final String URI_TRADEF = "http://115.28.12.59/didiao/istradef.do?";
		public static final String URI_CHECKTRADE = "http://115.28.12.59/didiao/checktradepsw.do?";
		public static final String URI_CHANGEPWD = "http://115.28.12.59/didiao/changepsw.do?";
		public static final String URI_CHANGETRADEPWD = "http://115.28.12.59/didiao/changetradepsw.do?";
		public static final String URI_SETTRADEPWD = "http://115.28.12.59/didiao/settradepsw.do?";

	/*// 测试地址
	public static final String URI_GETZCDAIKUAN = "http://10.1.156.230:8080/didiao/daikuansecondhouse.do";
	public static final String URI_GETDAIKUANBYFAL = "http://10.1.156.230:8080/didiao/daikuanunsolve.do";
	public static final String URI_GETDAIKUANBYID = "http://10.1.156.230:8080/didiao/daikuanid.do";
	public static final String URI_APPLYINFO = "http://10.1.156.230:8080/didiao/adddaikuanapplyinfo.do?";
	public static final String URI_CLIENTLOGIN = "http://10.1.156.230:8080/didiao/login.do?";
	public static final String URI_CLIENTREGISTER = "http://10.1.156.230:8080/didiao/register.do?";
	public static final String URI_APPLYUSER = "http://10.1.156.230:8080/didiao/adduserapplyinfo.do?";
	public static final String URI_APPLYCONTACT = "http://10.1.156.230:8080/didiao/addcontapplyinfo.do?";
	public static final String URI_APPLYBANK = "http://10.1.156.230:8080/didiao/addbankapplyinfo.do?";
	public static final String URI_APPLYPIC = "http://10.1.156.230:8080/didiao/addspicapplyinfo.do?";
	public static final String URI_GETAPPLYINFO = "http://10.1.156.230:8080/didiao/daikuanapplyinfo.do?";
	public static final String URI_GETAPPLYUSER = "http://10.1.156.230:8080/didiao/userapplyinfo.do?";
	public static final String URI_GETAPPLYCONTACT = "http://10.1.156.230:8080/didiao/contapplyinfo.do?";
	public static final String URI_GETAPPLYBANK = "http://10.1.156.230:8080/didiao/bankapplyinfo.do?";
	public static final String URI_GETAPPLYPIC = "http://10.1.156.230:8080/didiao/picapplyinfo.do?";
	public static final String URI_USERCROWDFUND = "http://10.1.156.230:8080/didiao/usercrowdfund.do?";
	public static final String URI_UPLOAD = "http://10.1.156.230:8080/UpFile1/UploadServlet";
	public static final String URI_SENDMSG = "http://10.1.156.230:8080/didiao/sendmessage.do";
	public static final String URI_GETAPPLYINFOSTATE = "http://10.1.156.230:8080/didiao/daikuanfal.do?";
	public static final String URI_GETUSERMONEY = "http://10.1.156.230:8080/didiao/usermoneyuse.do?";
	public static final String URI_CROWDFUNDRECORD = "http://10.1.156.230:8080/didiao/crowdfundbyid.do?";
	public static final String URI_CROWDFUNDUSERRECORD = "http://10.1.156.230:8080/didiao/crowdfundbyuser.do?";
	public static final String URI_USERCENTERINFO = "http://10.1.156.230:8080/didiao/usermoneyuse.do?";
	public static final String URI_GETREPAYMENT = "http://115.28.12.59/didiao/repaymentList.do";
	public static final String URI_GETNEXTPAYMENT = "http://10.1.156.230:8080/didiao/nextrepayment.do";
	public static final String URI_GETBANKCARDINFO = "http://10.1.156.230:8080/didiao/bankcardbyu.do?";
	public static final String URI_ADDBANKCARD = "http://10.1.156.230:8080/didiao/addbankcard.do?";
	public static final String URI_MONEYRECORD = "http://10.1.156.230:8080/didiao/moneyrecorduse.do?";
	public static final String URI_TRADEF = "http://10.1.156.230:8080/didiao/istradef.do?";
	public static final String URI_CHECKTRADE = "http://10.1.156.230:8080/didiao/checktradepsw.do?";
	public static final String URI_CHANGEPWD = "http://10.1.156.230:8080/didiao/changepsw.do?";
	public static final String URI_CHANGETRADEPWD = "http://10.1.156.230:8080/didiao/changetradepsw.do?";
	public static final String URI_SETTRADEPWD = "http://10.1.156.230:8080/didiao/settradepsw.do?";*/
	
	private static final String[] companyNatureItem = { "机关及事业单位", "国营", "民营",
			"三资企业", "其他" };
	private static final String[] marriedItem = { "未婚", "已婚有子", "已婚无子", "离异",
			"丧偶" };
	private static final String[] houseItem = { "有房有房贷", "有房无房贷", "无房" };

	private static final String[] educationItem = { "初中", "高中", "中专", "大专",
			"本科", "硕士", "博士", "博士后" };

	private static final String[] UsingItem = { "买房", "买车", "结婚", "兼职创业",
			"教育培训", "家居", "装修", "旅游", "医疗", "日常消费", "偿还债务", "投资", "其他" };

	public static String[] getBorrowtime() {
		return borrowtime;
	}

	public static String[] getBorrowrate() {
		return borrowrate;
	}

	public static String[] getServerate() {
		return serverate;
	}

	// private static final String[] borrowtime = { "12", "18", "24", "36" };
	// private static final String[] borrowrate = { "10", "10.5", "11", "12" };
	// private static final String[] serverate = { "0.330", "0.307", "0.284",
	// "0.235" };

	private static final String[] borrowtime = { "12", "12", "12", "12" };
	private static final String[] borrowrate = { "1.8", "1.8", "1.8", "1.8" };
	private static final String[] serverate = { "3", "3", "3", "0.235" };
	// private static final String[] simplePeriodItem = { "月利率1.8%(12个月)",
	// "年利率10.5%(18个月)", "年利率11%(24个月)", "年利率12%(36个月)" };
	private static final String[] simplePeriodItem = { "月利率1.8%(12个月)" };
	// private static final String[] periodItem = {
	// "借款期数为12个月,月利率1.8%,月平台费率3%", "借款期数为18个月,年利率10.5%,月平台费率0.307%",
	// "借款期数为24个月,年利率11%,月平台费率0.284%", "借款期数为36个月,年利率12%,月平台费率0.235%" };
	private static final String[] periodItem = { "借款期数为12个月,月利率1.8%,月平台费率3%" };

	private static final String[] homerelationItem = { "父母", "配偶", "子女" };
	private static final String[] workrelationItem = { "同事", "人事(HR)" };
	private static final String[] otherrelationItem = { "同学", "老师", "朋友", "其他" };
	private static final String[] bankItem = { "工商银行", "建设银行", "招商银行", "农业银行",
			"交通银行", "浦发银行", "中信银行", "光大银行", "民生银行", "邮政储蓄", "中国银行", "兴业银行",
			"北京银行", "华夏银行", "深发银行", "汇丰银行", "农信联社" };

	public static String[] getWorkrelationitem() {
		return workrelationItem;
	}

	public static String[] getBankitem() {
		return bankItem;
	}

	public static String[] getCompanynatureitem() {
		return companyNatureItem;
	}

	public static String[] getHomerelationitem() {
		return homerelationItem;
	}

	public static String[] getOtherrelationitem() {
		return otherrelationItem;
	}

	public static String[] getCompanyNatureItem() {
		return companyNatureItem;
	}

	public static String[] getMarrieditem() {
		return marriedItem;
	}

	public static String[] getHouseitem() {
		return houseItem;
	}

	public static String[] getEducationitem() {
		return educationItem;
	}

	public static String[] getUsingitem() {
		return UsingItem;
	}

	public static String[] getPerioditem() {
		return periodItem;
	}

	public static String[] getSimpleperioditem() {
		return simplePeriodItem;
	}

	public static int getLoanState() {

		return 0;

	}

}
