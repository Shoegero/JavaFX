package TelegramBot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    public String grant_type;
    public String client_id;
    public String client_secret;
    public String refresh_token;
}
