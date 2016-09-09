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

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import np.com.ngopal.particle.cloud.AuthClient;
import np.com.ngopal.particle.cloud.api.exception.APIException;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

/**
 *
 * @author NGM
 */
@Getter
public abstract class AbstractAPI implements API {

    private String host = "api.particle.io";

    private String schema = "https";

    protected boolean isAccessToken = false;

    protected AuthClient client;

    @Override
    public Map<String, String> getAuthHeaders() {
        Map<String, String> headers = new HashMap<>();
        if (!isAccessToken) {
            headers.put("Authorization", "Basic " + Base64.encodeBase64String(new StringBuilder().append(getClient().getUser()).append(":").append(getClient().getSecret()).toString().getBytes()));
        } else {
            headers.put("Authorization", "Bearer " + getClient().getAccessToken());
        }
        return headers;
    }

    @Override
    public void handleException(JSONObject object) throws APIException {
        throw new APIException(object.getString("error"), object.getInt("code"), object.getBoolean("ok"));
    }

}
