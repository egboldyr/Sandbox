package web.servlet.util;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * Created by EGBoldyr on 16.03.18.
 */

public class VelocityEngineUtil {

    private static final String TEMPLATES_PATH = "/templates/vm/";

    private static VelocityEngine engine;

    public static VelocityEngine getEngine() {
        if (engine == null) {
            engine = new VelocityEngine();
            engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        }
        return engine;
    }

    public static Template startTemplate(String template) {
        return getEngine().getTemplate(TEMPLATES_PATH + template);
    }
}
