package cn.com.rexen.roffice.note.rest.internal;

import cn.com.rexen.core.util.SystemUtil;
import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 *
 */
public class InitActivator implements BundleActivator {

    private static final String BUNDLE_NAME = " Roffice Note Rest ";
    private static BundleContext context;
    private static Logger logger = Logger.getLogger(InitActivator.class);

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        SystemUtil.succeedPrintln(String.format("Start-up %s bundle!!", BUNDLE_NAME));
        context = bundleContext;
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        SystemUtil.succeedPrintln(String.format("Stop %s bundle!!", BUNDLE_NAME));
        context = null;
    }
}
