package api.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.ConfigProvider;


public class BaseSpecs {

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigProvider.BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }

}
