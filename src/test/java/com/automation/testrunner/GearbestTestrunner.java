package com.automation.testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
        features="classpath:Feature",//to tell cucumber where is ur feature file
        glue="com.automation.stepdefs", // to tell cucumber where is ur step def code
        tags="@Datatable", // to tell which tagged feature file to execute
        plugin = {"pretty", // to generate reports
            "html:target/html/htmlreport.html",
            "json:target/json/file.json",
            },
        publish=true,
        dryRun=false // to tell whether to test run(true) or actual run(false)
        )


public class GearbestTestrunner {

}
