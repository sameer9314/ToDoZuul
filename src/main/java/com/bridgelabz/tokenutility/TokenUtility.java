package com.bridgelabz.tokenutility;
import javax.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * Purpose : To provide methods to generate token.
 * 
 * @author   Sameer Saurabh
 * @version  1.0
 * @Since    11/07/2018
 */
@Service
public class TokenUtility {

	final static String KEY = "Sameer";

	/**
	 * Method is written to claim token and return back the user email.
	 * 
	 * @param jwt
	 * @return String
	 */
	public Claims parseJWT(String jwt) {

		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(KEY)).parseClaimsJws(jwt)
				.getBody();
		return claims;
	}
}
