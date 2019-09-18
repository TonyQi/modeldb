package com.bcc.security.auth.common.util.jwt;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ace on 2017/9/10.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTInfo implements Serializable,IJWTInfo {

	private static final long serialVersionUID = -726837627868001746L;
	private String username;
    private String userId;
    private String name;

    @Override
    public String getUniqueName() {
        return username;
    }

    @Override
    public String getId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JWTInfo jwtInfo = (JWTInfo) o;

        if (username != null ? !username.equals(jwtInfo.username) : jwtInfo.username != null) {
            return false;
        }
        return userId != null ? userId.equals(jwtInfo.userId) : jwtInfo.userId == null;

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
