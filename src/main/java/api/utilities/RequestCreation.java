package api.utilities;

import io.restassured.authentication.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RequestCreation {

    private final RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
    private final BasicAuthScheme basicAuth = new BasicAuthScheme();
    private final PreemptiveBasicAuthScheme premptAuth = new PreemptiveBasicAuthScheme();
    private final FormAuthScheme formAuth = new FormAuthScheme();
    private final OAuth2Scheme oAuth2 = new OAuth2Scheme();
    private final OAuthScheme oAuth = new OAuthScheme();
    private RequestSpecification requestSpec;

    {
        requestBuilder.log(LogDetail.ALL);
    }

    /***
     * This method is used to set the Base URI
     * @param baseURI - Set base URI to the Request Builder.
     */
    public void setBaseURI(String baseURI) {
        requestBuilder.log(LogDetail.ALL);
        requestBuilder.setBaseUri(baseURI);
    }

    /***
     * This method is used to add Basic Authentication to Request builder.
     * @param userName - Username for Authentication
     * @param password - Password for Authentication
     */
    public void setBasicAuth(String userName, String password) {
        basicAuth.setUserName(userName);
        basicAuth.setPassword(password);
        requestBuilder.setAuth(basicAuth);
    }

    /***
     * This method is used to add Preempt Authentication to the Request Builder.
     * @param userName - Username for Authentication
     * @param password - Password for Authentication
     */
    public void setPremptAuth(String userName, String password) {
        premptAuth.setUserName(userName);
        premptAuth.setPassword(password);
        requestBuilder.setAuth(premptAuth);
    }

    /***
     * This method is used to add Form Authentication to the Request Builder.
     * @param userName - Username for Authentication
     * @param password - Password for Authentication
     */
    public void setFormAuth(String userName, String password) {
        formAuth.setUserName(userName);
        formAuth.setPassword(password);
        requestBuilder.setAuth(formAuth);
    }

    /***
     * This method is used to Set oAUth2 authorization to the Request Builder.
     * @param accesstoken - Access Token for Authorization
     */
    public void setoAuth2(String accesstoken) {
        oAuth2.setAccessToken(accesstoken);
        requestBuilder.setAuth(oAuth2);
    }

    /***
     * This method is used to set oAuth Authorization to the Request Builder.
     * @param cosumerKey - Consumer Key
     * @param consumerSecret - Consumer Secret
     * @param accessToken - Access Token
     * @param tokenSecret - Secret Token
     */
    public void setoAuth(String cosumerKey, String consumerSecret, String accessToken, String tokenSecret) {
        oAuth.setConsumerKey(cosumerKey);
        oAuth.setConsumerSecret(consumerSecret);
        oAuth.setAccessToken(accessToken);
        oAuth.setSecretToken(tokenSecret);
        requestBuilder.setAuth(oAuth);
    }

    /***
     * This method is use to add cookies to the Request Builder.
     * @param cookies - Map of Cookies
     */
    public void addCookies(Map<String, ?> cookies) {
        requestBuilder.addCookies(cookies);
    }

    /***
     * This method is used to add Headers to the Request Builder.
     * @param headers - Map of Headers
     */
    public void addHeaders(Map<String, String> headers) {
        requestBuilder.addHeaders(headers);
    }

    /***
     * This method is used to add Query parameters to the Request Builder.
     * @param parameters - Map of Query Parameters
     */
    public void addQueryParameters(Map<String, String> parameters) {
        requestBuilder.addQueryParams(parameters);
    }

    /**
     * This method is used to add Form parameters to the Request Builder.
     *
     * @param parameters - Map of form Parameters
     */
    public void addFormParameters(Map<String, String> parameters) {
        requestBuilder.addFormParams(parameters);
    }

    /***
     * This method is used to add Path parameters to the Request Builder.
     * @param parameters - Map of Path Parameters
     */
    public void addPathParameters(Map<String, String> parameters) {
        requestBuilder.addPathParams(parameters);
    }

    /***
     * This method is used to set string body to the Request Builder.
     * @param body - Body
     */
    public void addBody(String body) {
        requestBuilder.setBody(body);
    }

    /***
     * This method is used to add multipart parameter to the Request Builder.
     * @param filePath - Path of the file to be passed as parameter
     */
    public void addMultiLine(String filePath) {
        requestBuilder.addMultiPart(new File("/path/to/file"));
    }

    /***
     * This method generates request specification from Request Builder.
     */
    private void specBuilder() {
        requestSpec = requestBuilder.build();
    }

    /**
     * This method is used to fetch the response using Get Request
     *
     * @param path - Request Path
     * @return - Returns response
     */

    public Response getRequest(String path) {
        specBuilder();
        return given().spec(requestSpec).when().get(path).prettyPeek();
    }

    /**
     * This method is used to fetch the response using Post Request
     *
     * @param path - Request Path
     * @return - Returns response
     */
    public Response postRequest(String path) {
        specBuilder();
        return given().spec(requestSpec).when().post(path).prettyPeek();
    }

    /**
     * This method is used to fetch the response using Put Request
     *
     * @param path - Request Path
     * @return - Returns response
     */
    public Response putRequest(String path) {
        specBuilder();
        return given().spec(requestSpec).when().put(path).prettyPeek();
    }

    /**
     * This method is used to fetch the response using Patch Request
     *
     * @param path - Request Path
     * @return - Returns response
     */
    public Response patchRequest(String path) {
        specBuilder();
        return given().spec(requestSpec).when().patch(path).prettyPeek();
    }

    /**
     * This method is used to fetch the response using Delete Request
     *
     * @param path - Request Path
     * @return - Returns response
     */
    public Response deleteRequest(String path) {
        specBuilder();
        return given().spec(requestSpec).when().delete(path).prettyPeek();
    }
}
