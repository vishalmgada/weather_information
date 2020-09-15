package api.utilities;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.junit.Assert;

import java.lang.reflect.Type;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@AllArgsConstructor
public class ResponseValidation {
    private final Response response;

    /**
     * This method is used to validate status code of the Response
     *
     * @param statusCode - Expected Status Code
     */
    public void validateStatusCode(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    /**
     * This method is used to validate the schema of the response.
     *
     * @param schemaFileName - Expected Schema File Name
     */
    public void validateJSONSchema(String schemaFileName) {
        response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaFileName));
    }

    /**
     * This method is used to validate the headers of the response.
     *
     * @param headers - Expected Headers
     */
    public void validateHeaders(Map<String, String> headers) {
        for (String key : headers.keySet()) {
            Assert.assertEquals(headers.get(key), response.getHeader(key));

        }

    }

    /**
     * This method is used to validate cookies of the response
     *
     * @param cookies - Expected Cookies
     */
    public void validateCookies(Map<String, String> cookies) {

        for (String key : cookies.keySet()) {
            Assert.assertEquals(cookies.get(key), response.getHeader(key));
        }

    }

    /**
     * This method is used to Deserialize the JSON Response into Java Object
     *
     * @param Deserializer - Generic Java Object of the Response
     * @return - Return Deserialized Objects
     */
    @SneakyThrows
    public <T> T deserializeResponse(Class<T> Deserializer) {
        T deserializer = null;
        deserializer = Deserializer.getDeclaredConstructor().newInstance();

        return response.as((Type) deserializer.getClass());
    }
}
