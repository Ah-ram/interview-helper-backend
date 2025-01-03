package road_to_employment.interview_helper.oauth.dto;

import lombok.Data;

@Data
public class AccessTokenDto {
    private String access_token;
    private int expires_in;
    private String scope;
    private String token_type;
    private String id_token;
}
