/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.ngopal.particle.cloud;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author NGM
 */
@Getter
@AllArgsConstructor
public enum OAuthClientType {
    INSTALLED("installed"), WEB("web");

    String name;

}
