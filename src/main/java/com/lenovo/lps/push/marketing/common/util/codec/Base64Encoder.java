package com.lenovo.lps.push.marketing.common.util.codec;


/**
 * Base64编码解码
 * 可以对二进制数据生成URL安全的字符串
 * @author Administrator
 *  @deprecated
 */
public final class Base64Encoder  {
	private Base64Encoder(){
		
	}

	/**
	 * 对字节数据进行Base64编码
	 * @param src 源字节数组
	 * @return 编码后的字符串
	 */
	public static String encode(byte[] src)throws Exception {
		String result = Base64_EXT.encodeBytes(src, Base64_EXT.URL_SAFE);
		int index = result.indexOf("=");
		if(index>-1){
			result = result.substring(0, index);
		}
		return result;
	}
	
	/**
	 * 对Base64编码的字符串进行解码
	 * @param src 源字组
	 * @return 解码后的字节数组
	 */
	public static byte[] decode(String src)throws Exception {
		int i = src.length()%4;
		if(i==3){
			src = src+"=";
		}else if(i==2){
			src = src+"==";
		}
		return Base64_EXT.decode(src, Base64_EXT.URL_SAFE);
	}

}
