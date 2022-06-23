package com.delkabo.tests.models.lombok;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GenTokRespLambMain {

    @JsonProperty("GenerateToken")
    private GenerateTokenResponseLombok generatedToken;
}
