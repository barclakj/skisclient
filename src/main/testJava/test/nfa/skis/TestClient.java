package test.nfa.skis;

import com.nfa.skisclient.SkiClient;
import com.nfa.skisclient.SkiClientException;
import junit.framework.TestCase;

/**
 * Created by barclakj on 02/01/2017.
 */
public class TestClient extends TestCase {

    static {
        SkiClient.ROOT_URL = "http://localhost:9080";
    }

    public void testCreateToken() {
        String identity = "dhajklasdnlk";

        SkiClient client = new SkiClient();
        try {
            String tkn = client.createToken(identity);
            assertNotNull(tkn);
        } catch (SkiClientException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    public void testCreateKey() {
        int k = (int)(Math.random()*Integer.MAX_VALUE);
        String keyName = "SENSITIVE_DATA_KEY_" + k;

        String identity = "dhajklasdnlk";

        SkiClient client = new SkiClient();

        try {
            String tkn = client.createToken(identity);
            assertNotNull(tkn);
            byte[] key = client.createKey(keyName, tkn);
            assertNotNull(key);
            System.out.println("Successfully created key: " + key);

        } catch (SkiClientException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    public void testRetrieveKey() {
        int k = (int)(Math.random()*Integer.MAX_VALUE);
        String keyName = "SENSITIVE_DATA_KEY_" + k;

        String identity = "dhajklasdnlk";

        SkiClient client = new SkiClient();

        try {
            String tkn = client.createToken(identity);
            assertNotNull(tkn);
            byte[] key = client.createKey(keyName, "rudolph".getBytes(), tkn);
            assertNotNull(key);
            String ks = new String(key);
            assertNotNull(ks);
            System.out.println("Key val: " + ks);
            assertEquals("rudolph", ks);
            System.out.println("Successfully created key");

            key = client.getKey(keyName, tkn);
            ks = new String(key);
            assertNotNull(ks);
            System.out.println("Key val: " + ks);
            assertEquals("rudolph", ks);
            System.out.println("Successfully retrieved key");

        } catch (SkiClientException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    public void testRevokeIdentity() {
        String identity = "revokedidentity";
        String rootToken = ""; // use system key from init..
        SkiClient client = new SkiClient();

        try {
            client.revokeIdentity(identity, rootToken);
            System.out.println("Successfully revoked identity");
        } catch (SkiClientException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    public void testRetrieveKeyNewToken() {
        int k = (int)(Math.random()*Integer.MAX_VALUE);
        String keyName = "SENSITIVE_DATA_KEY_" + k;

        String identity = "dhajklasdnlk";

        SkiClient client = new SkiClient();

        try {
            String tkn = client.createToken(identity);
            assertNotNull(tkn);
            byte[] key = client.createKey(keyName, "santa".getBytes(), tkn);
            assertNotNull(key);
            assertEquals("santa", new String(key));
            System.out.println("Successfully created key");

            String newtkn = client.grantToken("vampire", tkn);
            assertTrue(!tkn.equals(newtkn));

            key = client.getKey(keyName, newtkn);
            assertNotNull(key);
            assertEquals("santa", new String(key));
            System.out.println("Successfully retrieved key using new token");

        } catch (SkiClientException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }
}
