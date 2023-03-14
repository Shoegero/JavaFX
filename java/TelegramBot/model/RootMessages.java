package TelegramBot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RootMessages {
    public int id;
    public String kind;
    public boolean read;
    public String body;
    public String html_body;
    public Date created_at;
    public int linked_id;
    public Object linked_type;
    public Object linked;
    public MessageFrom from;
    @JsonProperty("to")
    public MessageTo myto;
}
