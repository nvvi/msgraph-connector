package com.axonivy.connector.office365.test.webtest;

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
public class WebTestToDoIT {

  @Test
  public void checkToDoTaskExists() {
    // set environment
    open(EngineUrl
            .createProcessUrl("/msgraph-connector-test/17F40684A56F5FEF/start.ivp?environment=dev-axonivy"));

    open(EngineUrl.createProcessUrl("/msgraph-todo-demo/176F208BF8721ECC/myToDo.ivp"));

    Table table = PrimeUi.table(By.id("form:tasksTable"));
    table.contains("Digitalize your business");
    table.contains("digitalization journey");

    $(By.id("form:proceed")).shouldBe(visible).click();
  }
}
