package io.github.pleuvoir.kit;

import java.io.IOException;
import java.util.Hashtable;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import io.github.pleuvoir.exception.AuthentException;

/**
 * 二维码工具类
 * @author pleuvoir
 *
 */
public class QRBarcodeUtil {

	/**
	 * 返回供google身份验证器扫描的地址 <br>
	 * 格式如：otpauth://totp/kisexu@gmail.com?secret=DPI45HCEBCJK6HG7
	 * @param user    账号
	 * @param host
	 * @param secret  密钥
	 */
	public static String getQRBarcodeURL(String user, String host, String secret) {
		if (StringUtils.isBlank(user)) {
			throw new AuthentException("加密帐号不能为空");
		}
		if (StringUtils.isBlank(host)) {
			host = "easy-authent";
		}
		if (StringUtils.isBlank(secret)) {
			throw new AuthentException("密钥不能为空");
		}
		String format = "otpauth://totp/%s@%s?secret=%s";
		return String.format(format, user, host, secret);
	}
	
	/**
	 * 返回供google身份验证器扫描的地址 ，host使用默认值easy-authentication<br>
	 * 格式如：otpauth://totp/kisexu@gmail.com?secret=DPI45HCEBCJK6HG7
	 * @param user    账号
	 * @param secret  密钥
	 */
	public static String getQRBarcodeURL(String user, String secret) {
		return getQRBarcodeURL(user, null, secret);
	}
	
	/**
	 * 将地址写入流中，即向浏览器返回PNG格式的二维码图片
	 * @param user			用户名
	 * @param secret		密钥
	 * @param response
	 * @throws IOException
	 * @throws WriterException
	 */
	public static void writeToStream(String user, String secret, HttpServletResponse response) throws IOException, WriterException {
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/png");
		BitMatrix matrix = null;
		Writer writer = new MultiFormatWriter();
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>(2);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		matrix = writer.encode(getQRBarcodeURL(user, secret), com.google.zxing.BarcodeFormat.QR_CODE, 400, 400, hints);
		MatrixToImageWriter.writeToStream(matrix, "PNG", response.getOutputStream());
	}
	
	/**
	 * 将地址写入流中，即向浏览器返回PNG格式的二维码图片
	 * @throws IOException 
	 * @throws WriterException  
	 */
	public static void writeToStream(String url, HttpServletResponse response) throws IOException, WriterException {
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/png");
		BitMatrix matrix = null;
		Writer writer = new MultiFormatWriter();
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>(2);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		matrix = writer.encode(url, com.google.zxing.BarcodeFormat.QR_CODE, 400, 400, hints);
		MatrixToImageWriter.writeToStream(matrix, "PNG", response.getOutputStream());
	}
}
