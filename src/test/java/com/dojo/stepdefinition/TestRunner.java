package com.dojo.stepdefinition;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/java/com/dojo/feature" }, glue = {
		"com.dojo.stepDefination" }, stepNotifications = true)

class TestRunner {

}
