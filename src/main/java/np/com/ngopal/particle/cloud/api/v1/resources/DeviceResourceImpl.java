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

import java.util.List;
import np.com.ngopal.particle.cloud.Device;
import np.com.ngopal.particle.cloud.DeviceClaim;
import np.com.ngopal.particle.cloud.api.API;
import np.com.ngopal.particle.cloud.api.AbstractAPI;
import np.com.ngopal.particle.cloud.api.resources.DeviceResource;

/**
 *
 * @author NGM
 */
public class DeviceResourceImpl extends DeviceResource {

    private String baseURIPattern = "/devices/:deviceId/:name";

    public DeviceResourceImpl(API api) {
        super(api);
    }

    @Override
    public List<Device> listDevices() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
