package com.adobe.aem.tutorial.core.services.impl;

import com.adobe.aem.tutorial.core.services.ReadJsonDataService;
import com.adobe.aem.tutorial.core.services.configurations.JsonConfiguration;
import com.mysite.core.utils.HttpConnection;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = ReadJsonDataService.class, immediate = true)
@Designate(ocd = JsonConfiguration.class)
public class ReadJsonDataServiceImpl implements ReadJsonDataService {

    private static final Logger log = LoggerFactory.getLogger(ReadJsonDataServiceImpl.class);

    @OSGiService
    JsonConfiguration config;

    @Activate
    public void Activate (JsonConfiguration config) {
        this.config = config;
    }

    @Override
    public String readData() {
        try {
            String URL = config.endPoint();
            if (!StringUtils.isBlank(URL)) {
                log.info("Endpoint configure :::::::::" + URL);
                String response = HttpConnection.readJson(config.endPoint());
                return response;
            } else {
                log.info(":::::: Config not found");
                return "Config not found";
            }
        }  catch (Exception e) {
            log.error(e.getMessage(), e);
            return "Error Occured" + e.getMessage();
        }
    }
}
