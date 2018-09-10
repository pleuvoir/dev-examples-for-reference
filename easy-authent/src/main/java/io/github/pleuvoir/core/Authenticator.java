package io.github.pleuvoir.core;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import io.github.pleuvoir.exception.AuthentException;
import io.github.pleuvoir.kit.QRBarcodeUtil;
import io.github.pleuvoir.kit.TOTPUtils;


/**
 * 验证器
 * @author pleuvoir
 *
 */
public class Authenticator {
	
	
	/**
	 * 根据用户名生成二维码链接，使用默认的TOTP密钥格式如：DPI45HKISEXU6HG7
	 * @param user  用户名
	 * @return		返回的链接地址，可供google身份验证器扫描
	 */
	public static String generateQRcode(String user) {
		return QRBarcodeUtil.getQRBarcodeURL(user, TOTPUtils.generateSecret());
	}
	
	/**
	 * 根据用户名和密钥生成二维码链接
	 * @param user		用户名
	 * @param secret	密钥
	 * @return			返回的链接地址，可供google身份验证器扫描
	 */
	public static String generateQRcode(String user, String secret) {
		return QRBarcodeUtil.getQRBarcodeURL(user, secret);
	}
	
	/**
	 * 根据用户名，主机标识，和密钥生成二维码链接
	 * @param user		用户名
	 * @param host		主机标识
	 * @param secret	密钥
	 * @return			返回的链接地址，可供google身份验证器扫描
	 */
	public static String generateQRcode(String user, String host, String secret) {
		return QRBarcodeUtil.getQRBarcodeURL(user, host, secret);
	}

	/**
	 * 验证一次性密码正确性
	 * @param secrect	密钥
	 * @param code		一次性密码
	 * @return			正确返回true
	 */
	public static boolean authorize(String secrect, String code) {
		try {
			return TOTPUtils.checkCode(secrect, Long.parseLong(code));
		} catch (InvalidKeyException | NumberFormatException | NoSuchAlgorithmException e) {
			throw new AuthentException(e);
		}
	}
	
}
