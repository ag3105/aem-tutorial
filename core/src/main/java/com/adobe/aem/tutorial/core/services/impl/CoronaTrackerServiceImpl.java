package com.adobe.aem.tutorial.core.services.impl;

import com.adobe.aem.tutorial.core.services.configurations.CoronaTrackerConfiguration;
import com.adobe.aem.tutorial.core.services.CoronaTrackerService;
import com.mysite.core.utils.HttpConnection;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = CoronaTrackerService.class, immediate = true)
@Designate(ocd = CoronaTrackerConfiguration.class)
public class CoronaTrackerServiceImpl implements CoronaTrackerService {

    private static final Logger log = LoggerFactory.getLogger(CoronaTrackerServiceImpl.class);

    @OSGiService
    private CoronaTrackerConfiguration configuration;

    private boolean enable;

    @Activate
    protected void activate(CoronaTrackerConfiguration configuration) {
        this.configuration = configuration;
        enable = configuration.enableConfig();
    }

    @Override
    public String makeHttpCall() {
            String endpoint = configuration.endPoint();
            return getResponse(enable, endpoint);
    }

    @Override
    public String makeHttpCall(String params) {
        String endpoint = configuration.endPoint() + "?country=" + params;
        return getResponse(enable, endpoint);
    }

    public String getResponse (boolean enable, String url) {
        try {
            if (enable) {
                log.info("URL :::::::::" + url);
                String response = HttpConnection.readJson(url);

                log.info("Endpoint response ----->" + response);

                return response;
            } else {
                log.info("Config not found ---------->");
                return "Config not found";
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "Error occured" + e.getMessage();
        }
    }

}
