package common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UIConfiguration {

    @JsonProperty(value="website")
    private String website;

    @JsonProperty(value="browser")
    private String browser;

    @JsonProperty(value="waitTimeout")
    private long waitTimeout;

    @JsonProperty(value="pollingTimeout")
    private long pollingTimeout;
}
