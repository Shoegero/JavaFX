package TelegramBot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageTo {
    public int id;
    public String nickname;
    public String avatar;
    public ImageAvatar image;
    public Date last_online_at;
    public String url;
}
