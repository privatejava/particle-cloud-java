/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.ngopal.particle.cloud;

import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author NGM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    private String id;

    private String name;

    private String lastApp;

    private String lastIpAddress;

    private String productId;

    private boolean connected;

    private List<Map<String, String>> variables;

    private List<String> functions;

    private Date lastHeard;

}
