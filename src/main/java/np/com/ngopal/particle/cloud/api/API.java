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
import np.com.ngopal.particle.cloud.AuthUser;
import np.com.ngopal.particle.cloud.api.exception.APIException;
import np.com.ngopal.particle.cloud.api.resources.AuthResource;
import np.com.ngopal.particle.cloud.api.resources.CustomerResource;
import np.com.ngopal.particle.cloud.api.resources.DeviceResource;
import np.com.ngopal.particle.cloud.api.resources.ProductResource;
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

    public AuthUser getAuthUser();

    public boolean hasBasicCredential();

    public CustomerResource customers();

    public DeviceResource devices();

    public ProductResource products();

    public <T extends AuthResource> T auth();

    public Map<String, String> getAuthHeaders(boolean addAuthExplict) throws APIException;

    public Map<String, String> getAuthHeaders() throws APIException;

    public Map<String, String> getAccessTokenAuthHeaders() throws APIException;

    public Map<String, String> getAccessTokenAuthHeaders(String customerEmail)
            throws APIException;

    public void handleException(JSONObject object) throws APIException;

}
