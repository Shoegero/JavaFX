package TelegramBot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RootAnime {
    public int id;
    public String russian;
    public Image image;
    public String url;
    public String score;
    public String status;
    public int episodes;
    public String released_on;
    public UserRate user_rate;
    public String frontend;
    public Message message;
}
