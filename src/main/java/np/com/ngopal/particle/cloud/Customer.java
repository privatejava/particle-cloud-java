/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.ngopal.particle.cloud;

import java.util.List;
import lombok.*;

/**
 *
 * @author NGM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {

    private String email;

    private String password;

    private String accessToken;

    private String refreshToken;

    private Long expiresIn;

    private String tokenType;

    private List<Device> devices;

}
