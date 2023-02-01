import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Students {
    private int id;
    private String fullName;
    private String institute;
    private String group;
    private int course;
    private double averageScore;
}
