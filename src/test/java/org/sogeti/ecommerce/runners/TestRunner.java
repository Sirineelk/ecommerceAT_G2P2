package org.sogeti.ecommerce.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.sogeti.ecommerce.configuration.Hooks;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "org.sogeti.ecommerce.steps",
                "org.sogeti.ecommerce.utils",
                "org.sogeti.ecommerce.configuration"
        },
        plugin = { "pretty", "html:target/cucumber-reports.html","json:target/cucumber.json" },
        monochrome = true,
        tags = "@R6" //pour rajouter plusieurs tags il faut mettre un or entre eux

)
public class TestRunner extends Hooks {
}