package TelegramBot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RootRate {
    public int id;
    public int user_id;
    public int target_id;
    public String target_type;
    public int score;
    public String status;
    public int rewatches;
    public int episodes;
    public int volumes;
    public int chapters;
    public String text;
    public String text_html;
    public Date created_at;
    public Date updated_at;
}
