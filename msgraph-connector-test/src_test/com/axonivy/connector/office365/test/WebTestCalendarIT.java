package com.axonivy.connector.office365.test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.ivy.webtest.engine.EngineUrl;
import com.axonivy.ivy.webtest.primeui.PrimeUi;
import com.axonivy.ivy.webtest.primeui.widget.Table;

@IvyWebTest
public class WebTestCalendarIT {

  @Test
  public void checkEventExists() {
    // set environment
    open(EngineUrl
            .createProcessUrl("/msgraph-connector-test/17F40684A56F5FEF/start.ivp?environment=dev-axonivy"));

    open(EngineUrl.createProcessUrl("/msgraph-calendar-demo/176D21535A8FEE20/readCalendar.ivp"));

    Table table = PrimeUi.table(By.id("form:theTable"));
    table.contains("Workflow UI");
    table.contains("Hallo ...");

    $(By.id("form:proceed")).shouldBe(visible).click();
  }
}
