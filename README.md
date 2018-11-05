# Oracle ADF - Sample Projects
POCs, Demos and Helpful code of Oracle ADF

**Compatibility**: Oracle ADF 12.2.1.3+**
**Java JDK**: 1.8+

## ImageLazyLoading

This is an example on how to use one of the multiple existing JavaScript libraries implementing Lazy-Loading for Images, Videos etc...

In Oracle WebCenter Portal and some Oracle ADF applications it is common to render multiple high-res images which penalizes the load page time / rendering.

This demo makes use of https://github.com/verlok/lazyload implementation in combination with ADF Image tag to demonstrate how to use it in Oracle ADF.

As described in the library used, we need to add the **data-src** HTML attribute to our ```<af:image>``` tag. By using ```<af:passThroughAttribute>``` in combination with any ADF tag we can generate whatever attribute named in the way we want.

lazyload.min.js requires to configure the ```styleClass``` with the value *lazyload* to allow the library to replace the image se in **data-src** to the image set in **src** automatically.

```xml
<af:image id="i1" styleClass="lazyload"
                  source="/images/giphy.gif">
 <af:passThroughAttribute name="data-src"
   value="https://images.pexels.com/photos/145939/pexels-photo-145939.jpeg?auto=compress&amp;cs=tinysrgb&amp;h=750&amp;w=1260"/>
</af:image>
<af:resource source="/js/lazysizes.min.js" type="javascript"/>
```

In **WebCenter Portal - Content Presenter** or other components can be used EL Expressions to retrieve low-resolution versions of the image and then use this technique to replace the low-res by the hi-res variant when the page has been loaded.

- Execute test.jsf and explore it to understand how it has been implemented

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
