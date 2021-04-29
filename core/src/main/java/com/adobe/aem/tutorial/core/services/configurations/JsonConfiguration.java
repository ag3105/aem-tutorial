package com.adobe.aem.tutorial.core.services.configurations;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "JSON Endpoint Configuration",
        description = "Configuration to get the JSON endpoint"
)
public @interface JsonConfiguration {
    @AttributeDefinition(
            name = "URL",
            description = "Enter the endpoint returning JSON response"
    )
    public String endPoint();
}
