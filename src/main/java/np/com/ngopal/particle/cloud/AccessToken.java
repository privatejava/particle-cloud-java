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
