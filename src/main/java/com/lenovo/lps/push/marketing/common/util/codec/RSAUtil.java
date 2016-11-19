package com.lenovo.lps.push.marketing.common.util.codec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

import org.apache.log4j.Logger;


/**
 * RSAUtil工具类
 * com.lenovo.lps.psb.pt.engine.RSAUtil
 * @author yinzj
 * @version 2.0
 * @deprecated
 */
public class RSAUtil {
	 private static final Logger logger = Logger.getLogger(RSAUtil.class);
	
	 //RSA key位数
	 private static final int KEY_SIZE = 512;
	 
	 //单次RSA加密操作所允许的最大块长度，该值与 KEY_SIZE、padding方法有关。 
	 //1024key->128,2048key->1256,512key->53,11个字节用于保存padding信息。
	 private static final int BLOCK_SIZE = 53;
	 
	 private static final int OUTPUT_BLOCK_SIZE = 64;
	
	 //private static final int KEY_SIZE = 1024;
	 //private static final int BLOCK_SIZE = 117;
	 //private static final int OUTPUT_BLOCK_SIZE = 128;
	 
	 //private static final int KEY_SIZE = 2048; 
	 //private static final int BLOCK_SIZE = 245; 
	 //private static final int OUTPUT_BLOCK_SIZE = 256;
	
	private static SecureRandom secrand	= new SecureRandom();
	public static Cipher rsaCipher;
	
	
	public static String algorithm="RSA";//RSA、RSA/ECB/PKCS1Padding
	//public static String Algorithm="RSA/ECB/PKCS1Padding";//RSA、RSA/ECB/PKCS1Padding
	
	public RSAUtil()throws Exception{

	}
	
	/**
	 * 生成密钥对
	 * @return KeyPair
	 * @throws Exception 
	 * @throws NoSuchAlgorithmException 
	 * @throws Exception
	 */

	public static String[] generateRSAKeyPair(){
		String[] keypair=new String[2];
		try { 
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(algorithm);    
            //密钥位数    
            keyPairGen.initialize(KEY_SIZE);
            //密钥对    
            KeyPair keyPair = keyPairGen.generateKeyPair();    
     
            // 公钥    
            PublicKey publicKey = (RSAPublicKey) keyPair.getPublic();    
     
            // 私钥    
            PrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();    
     
            String publicKeyString = getKeyString(publicKey);    
            logger.info("Public KEY===>" + publicKeyString);  
            keypair[0]=publicKeyString;
     
            String privateKeyString = getKeyString(privateKey);    
            logger.info("Private KEY===>" + privateKeyString);  
            keypair[1]=privateKeyString;
             
        }catch(Exception e){
       	    // System.err.println("Exception:"+e.getMessage()); 
        }   
        return keypair;
	}
         
      public static PublicKey getPublicKey(String key) throws Exception {    
            byte[] keyBytes;    
            keyBytes = Base64Encoder.decode(key);    
     
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);    
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);    
            PublicKey publicKey = keyFactory.generatePublic(keySpec);    
            return publicKey;    
      }    
         
      public static PrivateKey getPrivateKey(String key) throws Exception {    
            byte[] keyBytes;    
            keyBytes =Base64Encoder.decode(key);   
     
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);    
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);    
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);    
            return privateKey;    
      }    
     
         
      public static String getKeyString(Key key) throws Exception {    
            byte[] keyBytes = key.getEncoded();    
            String s =Base64Encoder.encode(keyBytes);   
            return s;    
      }    
     
  	/**
 	 * 对content采用RSA加密，再BASE64加密
 	 * 
 	 * @param strPubKey 公钥值
 	 * @param content 明文串
 	 * @return cardSecretPwd
 	 * @throws Exception
 	 */
 	public static String encodeSecret(String publicKeyString, String content) throws Exception {
 		Cipher en_rsaCipher = null;
		try {
			en_rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			throw e;
		}
 		
		Key publicKey = getPublicKey(publicKeyString); 
		try {
			//PublicKey pubkey = keys.getPublic();
			en_rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey,secrand);
			//System.out.println(rsaCipher.getBlockSize()); 
			//System.out.println(Message.getBytes("utf-8").length);
			//byte[] encryptedData = rsaCipher.doFinal(Message.getBytes("utf-8"));
			byte[] data = content.getBytes("utf-8");
			int blocks = data.length / BLOCK_SIZE ;
			int lastBlockSize = data.length % BLOCK_SIZE ;
			byte [] encryptedData = new byte[ (lastBlockSize == 0 ? blocks : blocks + 1)* OUTPUT_BLOCK_SIZE];
			for (int i=0; i < blocks; i++)
			{
				//int thisBlockSize = ( i + 1 ) * BLOCK_SIZE > data.length ? data.length - i * BLOCK_SIZE : BLOCK_SIZE ;
				en_rsaCipher.doFinal(data,i * BLOCK_SIZE, BLOCK_SIZE, encryptedData ,i * OUTPUT_BLOCK_SIZE);
			}
			if (lastBlockSize != 0 ){
				en_rsaCipher.doFinal(data, blocks * BLOCK_SIZE, lastBlockSize,encryptedData ,blocks * OUTPUT_BLOCK_SIZE);
			}

		    return Base64Encoder.encode(encryptedData);
			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			throw new IOException("InvalidKey") ;
		}catch (ShortBufferException e) {
			e.printStackTrace();
			throw new IOException("ShortBuffer") ;
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new IOException("UnsupportedEncoding") ;
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			throw new IOException("IllegalBlockSize") ;
		} catch (BadPaddingException e) {
			e.printStackTrace();
			throw new IOException("BadPadding") ;
		}
 	}
 	
 	/**
 	 * 对content采用RSA加密，再BASE64加密
 	 * 
 	 * @param privateKeyString 私钥值
 	 * @param content 明文串
 	 * @return cardSecretPwd
 	 * @throws Exception
 	 */
 	public static String encodeSecretByPriKey(String privateKeyString, String content) throws Exception {
 		Cipher de_rsaCipher = null;
		try {
			de_rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			throw e;
		}
 		
		Key privateKey = getPrivateKey(privateKeyString); 
		try {
			//PublicKey pubkey = keys.getPublic();
			de_rsaCipher.init(Cipher.ENCRYPT_MODE, privateKey,secrand);
			//System.out.println(rsaCipher.getBlockSize()); 
			//System.out.println(Message.getBytes("utf-8").length);
			//byte[] encryptedData = rsaCipher.doFinal(Message.getBytes("utf-8"));
			byte[] data = content.getBytes("utf-8");
			int blocks = data.length / BLOCK_SIZE ;
			int lastBlockSize = data.length % BLOCK_SIZE ;
			byte [] encryptedData = new byte[ (lastBlockSize == 0 ? blocks : blocks + 1)* OUTPUT_BLOCK_SIZE];
			for (int i=0; i < blocks; i++)
			{
				//int thisBlockSize = ( i + 1 ) * BLOCK_SIZE > data.length ? data.length - i * BLOCK_SIZE : BLOCK_SIZE ;
				de_rsaCipher.doFinal(data,i * BLOCK_SIZE, BLOCK_SIZE, encryptedData ,i * OUTPUT_BLOCK_SIZE);
			}
			if (lastBlockSize != 0 ){
				de_rsaCipher.doFinal(data, blocks * BLOCK_SIZE, lastBlockSize,encryptedData ,blocks * OUTPUT_BLOCK_SIZE);
			}

		    return Base64Encoder.encode(encryptedData);
			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			throw new IOException("InvalidKey") ;
		}catch (ShortBufferException e) {
			e.printStackTrace();
			throw new IOException("ShortBuffer") ;
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new IOException("UnsupportedEncoding") ;
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			throw new IOException("IllegalBlockSize") ;
		} catch (BadPaddingException e) {
			e.printStackTrace();
			throw new IOException("BadPadding") ;
		}
 	}
 
 	/**
 	 * BASE64解密，再RSA解密
 	 * 
 	 * @param strPriKey 私钥值
 	 * @param content 密文串
 	 * @return 用私钥解密串
 	 * @throws Exception
 	 */
 	public static String decodeSecret(String privateKeyString, String content) throws Exception {
 		
 		Cipher de_rsaCipher=null;
		try {
			de_rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			throw e;
		}
 		
 		byte[] decoded=null;
		try {
			decoded = Base64Encoder.decode(content);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        Key privateKey = getPrivateKey(privateKeyString);  
		try {
			de_rsaCipher.init(Cipher.DECRYPT_MODE, privateKey, secrand);
			int blocks = decoded.length / OUTPUT_BLOCK_SIZE;
			ByteArrayOutputStream decodedStream = new ByteArrayOutputStream(decoded.length);
			for (int i =0 ;i < blocks ; i ++ )
			{
				decodedStream.write (de_rsaCipher.doFinal(decoded,i * OUTPUT_BLOCK_SIZE, OUTPUT_BLOCK_SIZE));
			}
			return new String(decodedStream.toByteArray(), "UTF-8");
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			throw new IOException("InvalidKey");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new IOException("UnsupportedEncoding");
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			throw new IOException("IllegalBlockSize");
		} catch (BadPaddingException e) {
			e.printStackTrace();
			throw new IOException("BadPadding");
		}
 	}
     
 	/**
 	 * BASE64解密，再RSA解密
 	 * 
 	 * @param strPriKey 私钥值
 	 * @param content 密文串
 	 * @return 用私钥解密串
 	 * @throws Exception
 	 */
 	public static String decodeSecretByPubKey(String publicKeyString, String content) throws Exception {
 		Cipher de_rsaCipher = null;
		try {
			de_rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			throw e;
		}
 		
 		byte[] decoded=null;
		try {
			decoded = Base64Encoder.decode(content);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        Key publicKey = getPublicKey(publicKeyString);
		try {
			de_rsaCipher.init(Cipher.DECRYPT_MODE, publicKey, secrand);
			int blocks = decoded.length / OUTPUT_BLOCK_SIZE;
			ByteArrayOutputStream decodedStream = new ByteArrayOutputStream(decoded.length);
			for (int i =0 ;i < blocks ; i ++ )
			{
				decodedStream.write (de_rsaCipher.doFinal(decoded,i * OUTPUT_BLOCK_SIZE, OUTPUT_BLOCK_SIZE));
			}
			return new String(decodedStream.toByteArray(), "UTF-8");
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			throw new IOException("InvalidKey");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new IOException("UnsupportedEncoding");
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			throw new IOException("IllegalBlockSize");
		} catch (BadPaddingException e) {
			e.printStackTrace();
			throw new IOException("BadPadding");
		}
 	}
      
      public static void main(String[] args) throws Exception {    
     
            
            //密钥对
            //String[] keys=generateRSAKeyPair();
            
    		String priKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAkvMGkImJkXv2PfF9f1bk7BTsJFSWGz8iO8FwM8dLF4kBCiAqc8d8bg9ruI1fy2tEqK1koccPA1bRv-lbIWpK8wIDAQABAkA1Sosr6aUJLLJtXmGLx6B3eVL2DfLt6KRqlUkyjejOnKRQ7MIoWNL03yJlsK5IzfMB_D0exZQSYvDg4gtj5_VRAiEA2C-LfYSGWPry8R72_nVOl7gX_rVk22XIyABIwmtqxrkCIQCuAzWFgr7FWvuR46ZM5eqR3JFmn5aCKOFym8KLZfFJCwIga59vv_LjtxRnMWaK666Wi61YNLM1HIwVYovRrQgwxfECIDMexDltuIeX-_HW9AMBRFEHgDuqxHeGdPzLX3K-Rw0TAiAZWIcMc7RsHPrp3nCrPkzDR1ZJwYiE6jBsv737_ZDmFw";
    		String pubKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJLzBpCJiZF79j3xfX9W5OwU7CRUlhs_IjvBcDPHSxeJAQogKnPHfG4Pa7iNX8trRKitZKHHDwNW0b_pWyFqSvMCAwEAAQ";
    		String s = "ExpTime=1226577284468$Pid=100013$Sid=rlpm001";
    		
    		//公钥加密
    		String d = encodeSecret(pubKey, s);
    		logger.info("公钥加密密文：" + d);
    		
    		//私钥解密
    		String c = decodeSecret(priKey, d);
    		
    		logger.info("私钥解密明文：" + c);
    		
    		//私钥加密
    		String e = encodeSecretByPriKey(priKey, s);
    		logger.info("私钥加密密文：" + e);
    		
    		//公钥解密
    		String f = decodeSecretByPubKey(pubKey, e);
    		
    		logger.info("公钥解密明文：" + f);
      }    
     
} 