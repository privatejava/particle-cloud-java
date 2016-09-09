/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
