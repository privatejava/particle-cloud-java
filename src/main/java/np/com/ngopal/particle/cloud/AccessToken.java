/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.ngopal.particle.cloud;

/**
 *
 * @author NGM
 */
public interface AccessToken {

    String getAccessToken();

    String getRefreshToken();

    Long getExpiresIn();

}
