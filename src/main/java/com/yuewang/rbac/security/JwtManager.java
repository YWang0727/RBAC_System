package com.yuewang.rbac.security;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.jwt.Claims;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.yuewang.rbac.enums.ResultCode;
import com.yuewang.rbac.exception.ApiException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JwtManager
 * @Description Generation and management util for jwt token
 * @Author Yue Wang
 * @Date 2023/5/8 18:18
 **/
@Slf4j
public class JwtManager {

    /**
     * This secret key is crucial to prevent tampering of the JWT.
     */
    private final static byte[] secretKeyBytes = "my_secret_key".getBytes();

    /**
     * The expiration time is currently set to 30 minutes,
     * this configuration depends on business requirements.
     */
    private final static Integer EXPIRATION = 30;

    /**
     * Generate jwt token
     * @author imyuanxiao
     * @date 14:54 2023/5/7
     * @param userName username
     * @return jwt token
     **/
    public static String generate(String userName) {
        DateTime now = DateUtil.date();
        DateTime ddl = DateUtil.offsetMinute(now, EXPIRATION);
        Map<String, Object> map = new HashMap<String, Object>() {
            {
                put(JWTPayload.ISSUED_AT, now);  //token created time
                put(JWTPayload.EXPIRES_AT, ddl);  //token expired time
                put(JWTPayload.NOT_BEFORE, now);  //token available time
                put(JWTPayload.SUBJECT, userName); //put username in 'sub'
            }
        };
        //generate a JWT String and add 'Bearer' in front it for authentication and authorisation
        return "Bearer " + JWTUtil.createToken(map, secretKeyBytes);
    }

    /**
     * Verify token
     * @author imyuanxiao
     * @date 14:54 2023/5/7
     * @param token jwt token
     * @throws ApiException Throw an exception if verification fails.
     **/
    public static void verifyToken(String token) throws ApiException {  //verify: signature + algorithm + datetime
        // verify signature
        // JWTUtil.verify: the first param is the JWT string to be verified, and the second is the secret key used for signing the JWT
        // JWTSignerUtil.hs256(): generate the signing key for the HMAC-SHA256 algorithm, is a symmetric key algorithm to sign the JWT payload
        boolean verify = JWTUtil.verify(token, JWTSignerUtil.hs256(secretKeyBytes));
        if(!verify) {
            throw new ApiException(ResultCode.INVALID_TOKEN);
        }

        // verify algorithm and datetime
        JWTValidator validator = JWTValidator.of(token);
        // throw exception when failed(expired\invalid)
        try {
            // verify algorithm
            validator.validateAlgorithm(JWTSignerUtil.hs256(secretKeyBytes));
        } catch (ValidateException e) {
            throw new ApiException(ResultCode.INVALID_TOKEN);
        }
        try {
            // verify datetime
            JWTValidator.of(token).validateDate();
        } catch (ValidateException e) {
            throw new ApiException(ResultCode.TOKEN_EXPIRED);
        }
    }

    /**
     * Parse token
     * @author imyuanxiao
     * @date 14:56 2023/5/7
     * @param token token to parse
     * @return Parse the JWT token to a JWTPayload object if successful
     **/
    private static Claims extractAllClaims(String token) throws ApiException {  //get payload
        verifyToken(token);
        return JWTUtil.parseToken(token).getPayload();
    }

    /**
     * Extract username from token
     * @author imyuanxiao
     * @date 14:56 2023/5/7
     * @param token token to parse
     * @return Return username if successful
     **/
    public static String extractUsername(String token) throws ApiException {  //get username
        Claims claims = extractAllClaims(token);
        return String.valueOf(claims.getClaim(JWTPayload.SUBJECT));
    }
}
