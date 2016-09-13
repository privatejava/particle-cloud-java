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

import java.util.Map;
import np.com.ngopal.particle.cloud.AuthClient;
import np.com.ngopal.particle.cloud.api.exception.APIException;
import np.com.ngopal.particle.cloud.api.resources.AuthResource;
import np.com.ngopal.particle.cloud.api.resources.CustomerResource;
import np.com.ngopal.particle.cloud.api.resources.DeviceResource;
import org.json.JSONObject;

/**
 *
 * @author NGM
 */
public interface API {

    public String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public String getRestUrl();

    public String getNonVersionedRestUrl();

    public String getSchema();

    public String getHost();

    public String getVersion();

    public AuthClient getClient();

    public CustomerResource customers();

    public DeviceResource devices();

    public AuthResource auth();

    public Map<String, String> getAuthHeaders();

    public void handleException(JSONObject object) throws APIException;

}
