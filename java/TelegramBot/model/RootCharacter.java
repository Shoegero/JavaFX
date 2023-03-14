package TelegramBot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RootCharacter {
    public int id;
    public String name;
    public String russian;
    public Image image;
    public String url;
}
