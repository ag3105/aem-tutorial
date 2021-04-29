package com.adobe.aem.tutorial.core.models.coronatracker;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.aem.tutorial.core.services.CoronaTrackerService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import javax.annotation.Nonnull;

@Model(adaptables = SlingHttpServletRequest.class,
        resourceType = CoronaTrackerModel.RESOURCE_TYPE,
        adapters = {CoronaTrackerModel.class, ComponentExporter.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class CoronaTrackerModel implements ComponentExporter {

    protected static final String RESOURCE_TYPE = "mysite/components/coronatracker";
    public String dataJson;

    @OSGiService
    CoronaTrackerService tracker;

    @ValueMapValue
    @Default(values="No country provided")
    String country;

    public String getCountry() {
        return country;
    }

    public String getDataJson() {
        if (country.isEmpty()) {
            dataJson = tracker.makeHttpCall();
        } else {
            dataJson = tracker.makeHttpCall(country);
        }
        return dataJson;
    }

    @Nonnull
    @Override
    public String getExportedType() {
        return RESOURCE_TYPE;
    }

}