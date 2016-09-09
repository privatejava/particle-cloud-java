/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
