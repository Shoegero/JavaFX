package TelegramBot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRate {
    public String score;
    public String target_id;
    public String target_type;
    public String user_id;
}
