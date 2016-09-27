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
package np.com.ngopal.particle.cloud.api.v1.resources;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import np.com.ngopal.particle.cloud.Device;
import np.com.ngopal.particle.cloud.DeviceClaim;
import np.com.ngopal.particle.cloud.api.API;
import np.com.ngopal.particle.cloud.api.APIMethodType;
import np.com.ngopal.particle.cloud.api.exception.APIException;
import np.com.ngopal.particle.cloud.api.resources.DeviceResource;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author NGM
 */
@Slf4j
public class DeviceResourceImpl extends DeviceResource {

    private String baseURIPattern = "/devices/:deviceId/:name";

    public DeviceResourceImpl(API api) {
        super(api);
    }

    @Override
    public List<Device> listDevices() throws APIException {
        List<Device> devices = new ArrayList<>();
        try {

            HttpResponse<JsonNode> response = getDeviceCreateRestClient(APIMethodType.GET, null, null).asJson();
            log.debug("Response : {}", response.getBody().toString());
            SimpleDateFormat format = new SimpleDateFormat(API.DATE_FORMAT);
            if (response.getStatus() == 200) {
                JSONArray array = response.getBody().getArray();
                array.forEach(d -> {
                    JSONObject o = (JSONObject) d;
                    Device device = new Device();
                    device.setId(o.isNull("id") ? null : o.getString("id"));
                    try {
                        device.setLastHeard(format.parse(o.isNull("last_heard") ? "" : o.getString("last_heard")));
                    } catch (ParseException ex) {
                        Logger.getLogger(DeviceResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    device.setConnected(o.isNull("connected") ? null : o.getBoolean("connected"));
                    device.setConnected(o.isNull("connected") ? null : o.getBoolean("connected"));
                    device.setLastApp(o.isNull("last_app") ? null : o.getString("last_app"));
                    device.setProductId(o.isNull("product_id") ? null : o.getLong("product_id"));
                    device.setName(o.isNull("name") ? null : o.getString("name"));
                    device.setLastIpAddress(o.isNull("last_ip_address") ? null : o.getString("last_ip_address"));
                    device.setStatus(o.isNull("status") ? null : o.getString("status"));
                    device.setImei(o.isNull("imei") ? null : o.getString("imei"));
                    device.setPlatformId(o.isNull("platform_id") ? null : o.getLong("platform_id"));
                    device.setCellular(o.isNull("cellular") ? null : o.getBoolean("cellular"));
                    device.setCurrentBuildTarget(o.isNull("current_build_target") ? null : o.getString("current_build_target"));
                    device.setLastIccid(o.isNull("last_iccid") ? null : o.getString("last_iccid"));
                    device.setPinnedBuildTarget(o.isNull("pinned_build_target") ? null : o.getString("pinned_build_target"));
                    devices.add(device);
                });

            } else {
                api.handleException(response.getBody().getObject());
            }
        } catch (UnirestException ex) {
            log.debug("{}", ex);
            throw new APIException(ex);
        }
        log.debug("{}", devices);
        return devices;
    }

    @Override
    public Device getDeviceInformation(String deviceId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DeviceClaim createClaim(String imei, String iccid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getBaseURIPattern() {
        return baseURIPattern;
    }

    private HttpRequest getDeviceCreateRestClient(APIMethodType type, String deviceId, String name)
            throws APIException {

        Map<String, String> headers = getApi().getAccessTokenAuthHeaders();
        log.debug("Headers: {}", headers);

        String url = api.getRestUrl() + getBaseURIPattern().replace("/:deviceId", deviceId == null ? "" : "/" + deviceId).replace(":name", name == null ? "" : "/" + name);
        log.debug("URL : {}", url);
        switch (type) {
            case GET:
                return Unirest.get(url).headers(headers);
            case POST:
                return Unirest.post(url).headers(headers);
            case DELETE:
                return Unirest.delete(url).headers(headers);
        }

        return null;
    }

}
