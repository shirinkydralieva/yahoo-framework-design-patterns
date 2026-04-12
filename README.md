# yahoo-framework-design-patterns

## Running Tests

Run tests via command line:

```bash
mvn clean test -Dbrowser=chrome -Denv=PROD -DsuiteXmlFile=ui_tests.xml
```

## Fixes

| Class | Problem                                                                                                                                                          | Solution                                                                                                                                                                                             |
|---|------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `WebDriverSingleton` (old: `driver.WebDriverSingleton`) | Mixed responsibilities: manages singleton and contains browser-specific driver creation details (options, driver instantiation, browser switch). (SRP violation). | Extract driver creation into `WebDriverFactory` (new: `framework.ui.driver.WebDriverFactory`). Keep the singleton focused on providing an instance / access point.                                   |
| `AbstractPage` (old: `pages.AbstractPage`) | Base page mixes page-level responsibilities (navigation/window logic) with low-level element actions (click/type/sendKeys). (SRP violation).                     | Move element-level actions into `WebElementDecorator` / `LogWebElementDecorator` (new: `framework.ui.element.*`). Keep `AbstractPage` for page-oriented behavior (open page, window switching, etc.). |
| `YahooMailTest` (old test class) | Test class mixes scenario logic with infrastructure responsibilities (driver teardown in AfterClass). (SRP violation).                 | Move browser lifecycle into `OpenCloseBrowserEachClassListener`. Register it globally in `ui_tests.xml` so tests remain focused only on scenario logic.                           |