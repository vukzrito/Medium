package za.co.entuit.medium.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by RVukela on 2017/04/18.
 */
public class ApiClientTest {
    @Test
    public void getClient() throws Exception {
        Assert.assertNotNull(ApiClient.getClient());
    }

}