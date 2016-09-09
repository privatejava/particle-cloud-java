/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.ngopal.particle.cloud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author NGM
 */
@Getter
@AllArgsConstructor
public class OAuthClient extends AuthClient {

    private final String name;

    private OAuthClientType type;

    private String id;

    private final String secret;

    public OAuthClient(String name, String secret) {
        this.name = name;
        this.secret = secret;
    }

    @Override
    public String getUser() {
        return name;
    }

}
