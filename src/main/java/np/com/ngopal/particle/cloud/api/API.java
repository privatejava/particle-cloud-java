/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.ngopal.particle.cloud.api;

import java.util.Map;
import np.com.ngopal.particle.cloud.api.exception.APIException;
import np.com.ngopal.particle.cloud.api.resources.CustomerResource;
import org.json.JSONObject;

/**
 *
 * @author NGM
 */
public interface API {

    public String getRestUrl();

    public String getSchema();

    public String getHost();

    public String getVersion();

    public CustomerResource customers();

    public Map<String, String> getAuthHeaders();

    public void handleException(JSONObject object) throws APIException;

}
