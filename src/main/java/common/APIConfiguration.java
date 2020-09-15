package common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIConfiguration {

    @JsonProperty(value="baseuri")
    private String baseuri;

    @JsonProperty(value="resource")
    private String resource;

    @JsonProperty(value="key")
    private String key =System.getProperty("APIKey");
}
