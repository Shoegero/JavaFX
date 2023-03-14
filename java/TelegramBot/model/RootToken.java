package TelegramBot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RootToken {
    public String access_token;
    public String token_type;
    public int expires_in;
    public String refresh_token;
    public String scope;
    public int created_at;
}
