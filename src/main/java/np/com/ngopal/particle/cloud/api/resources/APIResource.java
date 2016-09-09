/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.ngopal.particle.cloud.api.resources;

import lombok.Getter;
import np.com.ngopal.particle.cloud.api.API;

/**
 *
 * @author NGM
 */
@Getter
public abstract class APIResource {

    protected API api;

    public APIResource(API api) {
        this.api = api;
    }

    public abstract String getBaseURIPattern();

}
