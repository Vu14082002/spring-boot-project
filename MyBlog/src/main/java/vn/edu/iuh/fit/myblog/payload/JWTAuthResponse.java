package vn.edu.iuh.fit.myblog.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWTAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
}
