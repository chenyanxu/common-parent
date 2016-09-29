package com.kalix.common.message.extjs.internal;


import com.kalix.framework.core.api.osgi.KalixBundleActivator;
import com.kalix.framework.core.util.SystemUtil;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;

/**
 * Created by sunlf on 14-3-23.
 */
public class InitActivator extends KalixBundleActivator {

    public static final String KALIX_APP_ROFFICE_PATH = "/kalix/app/message";
    public static final String KALIX_ROFFICE_RESOURCES_IMAGES = "/kalix/message/resources/images";
    private static BundleContext context;
    private ServiceReference reference;
    private HttpService httpService;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        SystemUtil.startBundlePrintln(bundleContext);
        context = bundleContext;

        reference = bundleContext.getServiceReference(HttpService.class.getName());
        httpService = (HttpService) bundleContext.getService(reference);

        if(deploy){
            httpService.registerResources(KALIX_APP_ROFFICE_PATH, "/min/message", null);
        }
        else{
            httpService.registerResources(KALIX_APP_ROFFICE_PATH, "/message", null);
        }
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        SystemUtil.stopBundlePrintln(bundleContext);
        context = null;
        if (httpService != null) {
            httpService.unregister(KALIX_APP_ROFFICE_PATH);
            httpService.unregister(KALIX_ROFFICE_RESOURCES_IMAGES);
        }
        if (reference != null)
            bundleContext.ungetService(reference);
    }
}
