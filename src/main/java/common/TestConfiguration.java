package common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestConfiguration {

    @JsonProperty(value="ui")
    private UIConfiguration uiConfiguration;

    @JsonProperty(value="api")
    private APIConfiguration apiConfiguration;

    @JsonProperty(value="variance")
    private float variance;
}
