/*
 * Copyright (C) 2016 Narayan G. Maharjan <me@ngopal.com.np>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package np.com.ngopal.particle.cloud.api;

import static java.lang.Math.log;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import np.com.ngopal.particle.cloud.AuthUser;
import np.com.ngopal.particle.cloud.api.exception.APIException;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

/**
 *
 * @author NGM
 */
@Getter
@Slf4j
public abstract class AbstractAPI implements API {

    private String host = "api.particle.io";

    private String schema = "https";

    protected AuthUser authUser;

    @Override
    public Map<String, String> getAuthHeaders() throws APIException {
        return getAuthHeaders(false);
    }

    @Override
    public Map<String, String> getAuthHeaders(boolean clientDetails) throws APIException {
        Map<String, String> headers = new HashMap<>();
        if (getAuthUser().getAccessToken() == null || getAuthUser().getAccessToken().isEmpty()) {

            if (hasBasicCredential() && !clientDetails) {
                headers.put("Authorization", "Basic "
                        + Base64.encodeBase64String("particle:particle".getBytes()));
            } else {
                headers.put("Authorization", "Basic "
                        + Base64.encodeBase64String(new StringBuilder().append(getAuthUser().getId()).append(":").append(getAuthUser().getSecret()).toString().getBytes()));
            }

        }
        log.debug("HEADERS: {}", headers);
        //headers.put("Authorization", "Bearer " + getAuthUser().getAccessToken());

        return headers;
    }

    @Override
    public Map<String, String> getAccessTokenAuthHeaders() throws APIException {
        Map<String, String> headers = new HashMap<>();
        if (getAuthUser().getAccessToken() == null || getAuthUser().getAccessToken().isEmpty()) {
            auth().generateAccessToken();
        }
        headers.put("Authorization", "Bearer " + getAuthUser().getAccessToken());

        return headers;
    }

    @Override
    public Map<String, String> getAccessTokenAuthHeaders(String customerEmail,Long time)
            throws APIException {
        Map<String, String> headers = new HashMap<>();
        if (getAuthUser().getAccessToken() == null || getAuthUser().getAccessToken().isEmpty()) {
            auth().generateCustomerAccessToken(customerEmail,time);
        }
        headers.put("Authorization", "Bearer " + getAuthUser().getAccessToken());

        return headers;
    }
    
    @Override
    public Map<String, String> getAccessTokenAuthHeaders(String customerEmail)
            throws APIException {
        return getAccessTokenAuthHeaders(customerEmail,600L);
    }

    @Override
    public void handleException(JSONObject object) throws APIException {
        int code = object.has("code") ? object.getInt("code") : 0;
        boolean ok = object.has("ok") ? object.getBoolean("ok") : false;
        String error = object.has("error") ? object.getString("error") : "";
        String errorDescription = object.has("error_description") ? object.getString("error_description") : "";
        log.info("Object: {}", object);
        APIException ex = null;
        if (code > 1) {
            ex = new APIException(error, code, ok);
        } else {
            ex = new APIException(error, errorDescription);
        }

        throw ex;
    }

}
