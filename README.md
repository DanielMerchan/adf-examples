<?xml version="1.0" encoding="UTF-8"?>
<metadata>
  <groupId>com.oracle.commons.beanutils.commons.beanutils</groupId>
  <artifactId>commons-beanutils-1.9.3</artifactId>
  <versioning>
    <release>1.9.3-0-0</release>
    <versions>
      <version>1.9.3-0-0</version>
    </versions>
    <lastUpdated>20181003091234</lastUpdated>
  </versioning>
</metadata>



# Oracle ADF - Sample Projects
POCs, Demos and Helpful code of Oracle ADF

**Compatibility**: Oracle ADF 12.2.1.3+**
**Java JDK**: 1.8+

## WebSocketConcurrentTaskFlowEdit
This is an example on how to user Java WebSockets for notifying other users about the concurrent edit of a specific Task Flow in an ADF Web Page.
How to test the Sample:
- Execute test.jsf
- Open the application in two browsers
- Login in Browser A with user A
- Login in Browser B with user B
- A warning message will be displayed for both about concurrent editing.


## DatabaseResourceBundle

*Installation:*

    - Use the SQL Scripts for generating the Database Components.
    - Add the ADF JAR Library dependency to your ADF projects.
    - Register a JDBC Data Source called: jdbc/CUSTOMDS to point to your Database Schema where the Resource Bundle tables has been added (this can be changed).

*Usage:* Register the DatabaseResourceBundle in faces-config or use in JSF tag ```<f:loadBundle>``` or use the ADF Wrapper for Resource Bundles ```#{adfBundle}``` for loading the text from the custom resource bundle.

```html
<!-- faces-config.xml -->
<resource-bundle>
      <base-name>com.magicpigeon.i18n.resource.DatabaseResourceBundle</base-name>
      <var>dbBunde</var>
</resource-bundle>

<!-- Usage on ADF Page / Fragment -->
<af:outputText text="#{dbBundle['submit']}"/>

<!-- Using ADF Bundle Wrapper (ResourceBundleRT implementation of Oracle) -->
<c:set var="dbBundle" value="#{adfBundle['com.magicpigeon.i18n.resource.DatabaseResourceBundle']"/>

<!-- Usage on ADF Page / Fragment -->
<af:outputText text="#{dbBundle['submit']}"/>

<!-- JSF standard -->
<f:loadBundle basename="com.magicpigeon.i18n.resource.DatabaseResourceBundle" var="dbBundle"/>

<!-- Usage on ADF Page / Fragment -->
<af:outputText text="#{dbBundle['submit']}"/>
```
