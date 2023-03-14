package TelegramBot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Anime {
    public int id;
    public String russian;
    public Image image;
    public String url;
    public String kind;
    public String score;
    public String status;
    public int episodes;
    public int episodes_aired;
    public String aired_on;
    public String released_on;
}
