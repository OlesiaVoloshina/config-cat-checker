package test.configcatchecker.cat;

import com.configcat.*;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CatService {

    private static final String DEFAULT_VALUE = "__DEFAULT";

    private Logger LOG = LoggerFactory.getLogger(CatService.class);

    @Value("${configcat.client.sdk.key}")
    private String clientSdkKey;
    @Value("${configcat.base.url}")
    private String baseUrl;
    @Value("${configcat.client.autoPoll.autoPollIntervalInSeconds}")
    private int autoPollInterval;
    @Value("${configcat.client.autoPoll.maxInitWaitTimeSeconds}")
    private int maxInitWaitTime;

    private ConfigCatClient configCatClient;

    @PostConstruct
    public void init() {
        this.configCatClient = ConfigCatClient.get(clientSdkKey, this::setOnlineOptions);
    }
    private void setOnlineOptions(final ConfigCatClient.Options options) {
        options.pollingMode(PollingModes.autoPoll(autoPollInterval, maxInitWaitTime));
        options.dataGovernance(DataGovernance.EU_ONLY);
        options.baseUrl(baseUrl);
        options.logLevel(LogLevel.DEBUG);
    }


    public  <T> T getValue(final String code, final Class<T> type, final T defaultValue) {
        return code != null ? configCatClient.getValue(type, code, defaultValue) : null;
    }

    public List<String> executeTestRun() {
        List<String> values = new ArrayList<>();

        long start = System.currentTimeMillis();
        values.add("testCode: " + this.getValue("testCode", String.class, DEFAULT_VALUE));
        values.add("featureGambitSponsoredProductsProductStreamId: " +
                this.getValue("featureGambitSponsoredProductsProductStreamId", String.class, DEFAULT_VALUE));
        values.add("featureGambitSponsoredProductsCategoryStreamId: " +
                this.getValue("featureGambitSponsoredProductsCategoryStreamId", String.class, DEFAULT_VALUE));
        values.add("featureGambitSponsoredProductsAssortmentStreamId: " +
                this.getValue("featureGambitSponsoredProductsAssortmentStreamId", String.class, DEFAULT_VALUE));
        values.add("dashboardDeliveryPlusCapacityReportZipCodeGroups: " +
                this.getValue("dashboardDeliveryPlusCapacityReportZipCodeGroups", String.class, DEFAULT_VALUE));


        values.add("isDashboardDeliveryPlusCapacityReportEnabled: " +
                this.getValue("isDashboardDeliveryPlusCapacityReportEnabled", Boolean.class, false));
        values.add("isCompanySizeEnabled: " +
                this.getValue("isCompanySizeEnabled", Boolean.class, false));
        values.add("isAsmCaseNumberValidationEnabled: " +
                this.getValue("isAsmCaseNumberValidationEnabled", Boolean.class, false));
        values.add("isHqlLoyaltyCardValidateRequestEnabled: " +
                this.getValue("isHqlLoyaltyCardValidateRequestEnabled", Boolean.class, false));
        values.add("isGambitProductsFeedDeltaDetectionEnabled: " +
                this.getValue("isGambitProductsFeedDeltaDetectionEnabled", Boolean.class, false));


        values.add("notFound1: " +
                this.getValue("notFound1", Boolean.class, false));
        values.add("notFound2: " +
                this.getValue("notFound2", Boolean.class, false));
        values.add("notFound3: " +
                this.getValue("notFound3", Boolean.class, false));
        values.add("notFound4: " +
                this.getValue("notFound4", Boolean.class, false));
        values.add("notFound5: " +
                this.getValue("notFound5", Boolean.class, false));

        values.add("notFound6: " +
                this.getValue("notFound6", Boolean.class, false));
        values.add("notFound7: " +
                this.getValue("notFound7", Boolean.class, false));
        values.add("notFound8: " +
                this.getValue("notFound8", Boolean.class, false));
        values.add("notFound9: " +
                this.getValue("notFound9", Boolean.class, false));
        values.add("notFound10: " +
                this.getValue("notFound10", Boolean.class, false));

        LOG.info("Thread {}; {}", Thread.currentThread().getName(), System.currentTimeMillis() - start);
        return values;
    }
}
