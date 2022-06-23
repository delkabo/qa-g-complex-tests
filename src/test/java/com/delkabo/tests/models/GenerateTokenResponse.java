package com.delkabo.tests.models;

public class GenerateTokenResponse {
    /*
    {
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImFsZXgiLCJwYXNzd29yZCI6ImFzZHNhZCNmcmV3X0RGUzIiLCJpYXQiOjE2NTAwMzIwMTB9.PVab6IMv16Ztk31k01bMGBjsP8LD19Yg_wT-BDQX-58",
        "expires": "2022-04-22T14:13:30.835Z",
        "status": "Success",
        "result": "User authorized successfully."
    }
    */
    private String token;
    private String expires;
    private String status;
    private String result;

    public String getToken() {
        return token;
    }

    public String getExpires() {
        return expires;
    }

    public String getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }
}
