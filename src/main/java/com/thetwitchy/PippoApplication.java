package com.thetwitchy;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import ro.pippo.core.Application;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamResult;

/**
 * A simple Pippo application.
 *
 * @see com.thetwitchy.PippoLauncher#main(String[])
 */
public class PippoApplication extends Application {

    //private final static Logger log = LoggerFactory.getLogger(PippoApplication.class);

    @Override
    protected void onInit() {
        getRouter().ignorePaths("/favicon.ico");

        // send a template as response
        GET("/", (routeContext) -> {
            routeContext.render("index");
        });

        // send a template as response
        GET("/normal", (routeContext) -> {
            String result = "";
            try{
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                StringBuilder xmlStringBuilder = new StringBuilder();
                xmlStringBuilder.append(routeContext.getParameter("in"));
                ByteArrayInputStream input =  new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
                Document doc = builder.parse(input);

                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                StringWriter writer = new StringWriter();
                transformer.transform(new DOMSource(doc), new StreamResult(writer));
                result = writer.getBuffer().toString().replaceAll("\n|\r", "");
            }catch (Exception e){
                result = "Error occurred. " + e.getMessage();
            }

            routeContext.setLocal("payload", routeContext.getParameter("in"));
            routeContext.setLocal("result", result);
            routeContext.render("run");
        });

        GET("/blind", (routeContext) -> {
            String result = "";
            boolean success = false;
            try{
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                StringBuilder xmlStringBuilder = new StringBuilder();
                xmlStringBuilder.append(routeContext.getParameter("in"));
                ByteArrayInputStream input =  new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
                Document doc = builder.parse(input);

                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                StringWriter writer = new StringWriter();
                transformer.transform(new DOMSource(doc), new StreamResult(writer));
                result = writer.getBuffer().toString().replaceAll("\n|\r", "");
                success = true;
            }catch (Exception e){
                success = false;
            }

            routeContext.setLocal("payload", routeContext.getParameter("in"));
            routeContext.setLocal("result", success? "Success.":"Error.");
            routeContext.render("run");
        });
    }

}
