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
package np.com.ngopal.particle.cloud.api.resources;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import np.com.ngopal.particle.cloud.Device;
import np.com.ngopal.particle.cloud.DeviceClaim;
import np.com.ngopal.particle.cloud.api.API;
import np.com.ngopal.particle.cloud.api.exception.APIException;

/**
 *
 * @author NGM
 */
@Getter
public abstract class DeviceResource extends APIResource {

    public DeviceResource(API api) {
        super(api);
    }

    public abstract List<Device> listDevices() throws APIException;

    public abstract Device getDeviceInformation(String deviceId);

    public abstract DeviceClaim createClaim(String imei, String customerEmail, String iccid)
            throws APIException;

    public abstract DeviceClaim createClaim(String iccid, String customerEmail)
            throws APIException;

    public abstract Map<String, String> claim(String deviceId, String customerEmail)
            throws APIException;

    public abstract Map<String, String> claim(String deviceId)
            throws APIException;

}
