/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.ngopal.particle.cloud.api.resources;

import java.util.List;
import lombok.Getter;
import np.com.ngopal.particle.cloud.Device;
import np.com.ngopal.particle.cloud.DeviceClaim;
import np.com.ngopal.particle.cloud.api.API;

/**
 *
 * @author NGM
 */
@Getter
public abstract class DeviceResource extends APIResource {

    public DeviceResource(API api) {
        super(api);
    }

    public abstract List<Device> listDevices();

    public abstract Device getDeviceInformation(String deviceId);

    public abstract DeviceClaim createClaim(String imei, String iccid);

}
