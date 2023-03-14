package TelegramBot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RootManga {
    public int id;
    public String name;
    public String russian;
    public Image image;
    public String url;
    public String kind;
    public String score;
    public String status;
    public int volumes;
    public int chapters;
    public String aired_on;
    public Object released_on;
}
