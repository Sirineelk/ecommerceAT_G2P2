package org.sogeti.ecommerce.configuration;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setUp()  {
        DriverFactory.getDriver();
    }

    @After
    public void tearDown()  {
        DriverFactory.quitDriver();
    }
/*
    @AfterClass
    public static void afterAllTests() throws IOException, NoSuchAlgorithmException, KeyStoreException, InterruptedException, KeyManagementException {
            ImportResultsToXray api = new ImportResultsToXray();
            //api.token = api.getToken();
            api.RemonteeXRAY();
    }
*/
   /* @BeforeAll
    public static void downloadFeatures() {
        // télécharge et dézip les fichiers avant que Cucumber ne commence
        ExportFeaturesFromXRAY.downloadFeatureFiles("POEI2-643");
    }*/

}
