package com.bcc.security.auth.common.util.jwt;

import org.joda.time.DateTime;

import com.bcc.security.auth.common.constatns.CommonConstants;
import com.bcc.security.auth.common.util.StringHelper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/**
 * Created by ace on 2017/9/10.
 */
public class JWTHelper {
	private static RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();

	/**
	 * 密钥加密token
	 *
	 * @param jwtInfo
	 * @param priKeyPath
	 * @param expire
	 * @return
	 * @throws Exception
	 */
	public static String generateToken(IJWTInfo jwtInfo, String priKeyPath, int expire) throws Exception {
		String compactJws = Jwts.builder().setSubject(jwtInfo.getUniqueName())
				.claim(CommonConstants.JWT_KEY_USER_ID, jwtInfo.getId())
				.claim(CommonConstants.JWT_KEY_NAME, jwtInfo.getName())
				.setExpiration(DateTime.now().plusSeconds(expire).toDate())
				.signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKeyPath)).compact();
		return compactJws;
	}

	/**
	 * 密钥加密token
	 *
	 * @param jwtInfo
	 * @param priKey
	 * @param expire
	 * @return
	 * @throws Exception
	 */
	public static String generateToken(IJWTInfo jwtInfo, byte priKey[], int expire) throws Exception {
		String compactJws = Jwts.builder().setSubject(jwtInfo.getUniqueName())
				.claim(CommonConstants.JWT_KEY_USER_ID, jwtInfo.getId())
				.claim(CommonConstants.JWT_KEY_NAME, jwtInfo.getName())
				.setExpiration(DateTime.now().plusSeconds(expire).toDate())
				.signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKey)).compact();
		return compactJws;
	}

	/**
	 * 公钥解析token
	 *
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static Jws<Claims> parserToken(String token, String pubKeyPath) throws Exception {
		Jws<Claims> claimsJws = Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(pubKeyPath))
				.parseClaimsJws(token);
		return claimsJws;
	}

	/**
	 * 公钥解析token
	 *
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static Jws<Claims> parserToken(String token, byte[] pubKey) throws SignatureException {
		Jws<Claims> claimsJws = null;
		try {
			claimsJws = Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(pubKey)).parseClaimsJws(token);
		} catch (Exception e) {
			throw new SignatureException("signature check failed.", e);
		}

		return claimsJws;
	}

	/**
	 * 通过数字签名校验Token的有效性.
	 * 
	 * @param token
	 * @param pubKey
	 * @return
	 */
	public static boolean isTokenValid(String token, byte[] pubKey) {
		try {
			Jws<Claims> claimsJws = parserToken(token, pubKey);
			Claims body = claimsJws.getBody();
			if (System.currentTimeMillis() > body.getExpiration().getTime()) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 获取token中的用户信息
	 *
	 * @param token
	 * @param pubKeyPath
	 * @return
	 * @throws Exception
	 */
	public static IJWTInfo getInfoFromToken(String token, String pubKeyPath) throws Exception {
		Jws<Claims> claimsJws = parserToken(token, pubKeyPath);
		Claims body = claimsJws.getBody();
		return new JWTInfo(body.getSubject(), StringHelper.getObjectValue(body.get(CommonConstants.JWT_KEY_USER_ID)),
				StringHelper.getObjectValue(body.get(CommonConstants.JWT_KEY_NAME)));
	}

	/**
	 * 获取token中的用户信息
	 *
	 * @param token
	 * @param pubKey
	 * @return
	 * @throws Exception
	 */
	public static JWTInfo getInfoFromToken(String token, byte[] pubKey)
			throws ExpiredJwtException, SignatureException {
		Jws<Claims> claimsJws = parserToken(token, pubKey);
		Claims body = claimsJws.getBody();
		return new JWTInfo(body.getSubject(), StringHelper.getObjectValue(body.get(CommonConstants.JWT_KEY_USER_ID)),
				StringHelper.getObjectValue(body.get(CommonConstants.JWT_KEY_NAME)));
	}
}
