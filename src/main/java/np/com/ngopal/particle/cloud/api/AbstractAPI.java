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
import np.com.ngopal.particle.cloud.AuthClient;
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

    protected AuthClient client;

    @Override
    public Map<String, String> getAuthHeaders() {
        Map<String, String> headers = new HashMap<>();
        if (getClient().getAccessToken() == null) {
            if (getClient().getUser() == null || getClient().getUser().isEmpty()) {
                headers.put("Authorization", "Basic " + Base64.encodeBase64String("particle:particle".getBytes()));
            } else {
                headers.put("Authorization", "Basic " + Base64.encodeBase64String(new StringBuilder().append(getClient().getUser()).append(":").append(getClient().getSecret()).toString().getBytes()));
            }
        } else {
            headers.put("Authorization", "Bearer " + getClient().getAccessToken());
        }
        return headers;
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
