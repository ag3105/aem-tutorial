package com.adobe.aem.tutorial.core.services.configurations;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "Corona Tracker Service Configuration",
        description = "This configuration reads the Corona Tracker APi Endpoint.")
public @interface CoronaTrackerConfiguration {
    @AttributeDefinition (
            name = "Enable config",
            description = "This property will ensure the service is enabled or not",
            type = AttributeType.BOOLEAN)
    public boolean enableConfig();

    @AttributeDefinition(
            name = "Endpoint",
            description = "Enter the endpoint"
    )
    public String endPoint();
}