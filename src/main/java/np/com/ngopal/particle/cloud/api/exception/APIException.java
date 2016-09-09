/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package np.com.ngopal.particle.cloud.api.exception;

import lombok.Getter;

/**
 *
 * @author NGM
 */
@Getter
public class APIException extends Exception {

    private String error;

    private int code;

    private boolean ok;

    public APIException(String error, int code, boolean ok) {
        this.error = error;
        this.code = code;
        this.ok = ok;
    }

    public APIException(String message, Throwable cause) {
        super(message, cause);
    }

    public APIException(Throwable cause) {
        super(cause);
    }

    public APIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
