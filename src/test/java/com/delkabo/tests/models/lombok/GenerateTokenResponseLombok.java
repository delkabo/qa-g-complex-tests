package com.delkabo.tests.models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenerateTokenResponseLombok {

    private String token;
    private String expires;
    private String status;
    private String result;

}
