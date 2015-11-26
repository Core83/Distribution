package com.core.WeChat;

public class Config {
    public static final String SESSION_FRAME = "SYS_USER";
	//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。 
	public static final String SIGNATURE = "signature";
	//时间戳 
	public static final String TIME_STAMP = "timestamp";
	//随机数 
	public static final String NONCE = "nonce";
	//随机字符串
	public static final String ECHO_STR = "echostr";
	// 赋权类型
	public static final String grant_type = "client_credential";
	//网页授权
	public static final String GRANT_TYPE_CODE="authorization_code";	
	//消息体签名
	public static final String MSG_SIGNATURE="msg_signature";

    //
    public static final String TOKEN = "5fsgSxen8fsdp5uNEMm3";
    public static final String APPID = "wxaec7de22b7b9dd81";
    public static final String AppSecret="68167a66af466886202560942677ab7d";
    public static final String AESKey = "moRVtoOLenldTkDkKfYxBbfejDrwSqAeEsaYHOJLbVO";
    public static final String MCHID="1285414101";
    public static final String APPNAME="品尚会";
    public static final String SER_URL="http://095398.ichengyun.net";
    public static final String SER_URLS="http://69a441a0.ngrok.io";
    public static final String keyStoreFilePath="D:\\apache-tomcat-7.0.55-windows-x64\\apache-tomcat-7.0.55\\webapps\\distribution\\statics\\cert\\apiclient_cert.p12";
	public static final String singKey="128541410112854141011285414101sf";
	
	// 获取access token URL
	public static final String URL_ACCESSTOKEN = "https://api.weixin.qq.com/cgi-bin/token";
	// 创建菜单URL
	public static final String URL_MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create";
	// 获取菜单URL
	public static final String URL_MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/get";
	// 删除菜单URL
	public static final String URL_MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete";
	//获取用户信息URL
	public static final String URL_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info";
	//获取openId的信息
	public static final String URL_OPENID = "https://api.weixin.qq.com/sns/oauth2/access_token";
	//图片获取接口
	public static final String URL_IMAGEGET = "https://api.weixin.qq.com/cgi-bin/media/get";
	
	//通用公共节点
	public static final String ROOT = "xml";
	public static final String TO_USER_NAME = "ToUserName";
	public static final String FROM_USER_NAME = "FromUserName";
	public static final String CREATE_TIME = "CreateTime";
	public static final String MSG_TYPE = "MsgType";
	//文本消息内容 
	public static final String CONTENT = "Content";
	
	//消息ID
	public static final String MSG_ID = "MsgId";
	// 	图片链接 
	public static final String PIC_URL = "PicUrl";
	//通过上传多媒体文件，得到的id
	public static final String MEDIAID = "MediaId";
	public static final String IMAGE = "Image";
	public static final String VOICE = "Voice";
	public static final String VIDEO = "Video";
	public static final String FORMAT = "Format";
	public static final String THUMB_MEDIAID = "thumbMediaId";
	public static final String TITLE = "Title";
	public static final String DESCRIPTION = "Description";
	public static final String URL = "Url";
	//
	public static final String LOCATION_X = "Location_X";
	public static final String LOCATION_Y = "Location_Y";
	public static final String SCALE = "Scale";
	public static final String LABEL = "Label";
	
	//接收消息
	public static final String ACCEPT_MSG = "0";
	//发送消息
	public static final String SEND_MSG="1";
	//语言
	public static final String LANG_EN = "zh_CN";
	
	//关注提示信息
	public static final String PROM_CONTENT = "感谢关注";
	
	//图片上传成功提示
	public static final String PROM_UPLOAD_SUCCESS = "您上传的图片已收到";
	
	//图片上传失败提示
	public static final String PROM_UPLOAD_FAIL = "图片上传失败请重新上传";
	
	//没处理订单的提示
	public static final String PROM_UPLOAD_ORDER_FAIL = "您没有正在处理的订单,请稍后再上传";
	
	//未有回应消息的提示
	public static final String PROM_NO_MSG = "请点击下面菜单查看相应内容";
}
