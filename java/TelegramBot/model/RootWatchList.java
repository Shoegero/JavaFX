package TelegramBot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RootWatchList {
    public int id;
    public int score;
    public String status;
    public Object text;
    public int episodes;
    public int chapters;
    public int volumes;
    public String text_html;
    public int rewatches;
    public Anime anime;
    public Object manga;
}
