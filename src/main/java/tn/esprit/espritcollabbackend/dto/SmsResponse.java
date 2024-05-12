package tn.esprit.espritcollabbackend.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsResponse {
    private SmsStatus status;
    private String message;
}
