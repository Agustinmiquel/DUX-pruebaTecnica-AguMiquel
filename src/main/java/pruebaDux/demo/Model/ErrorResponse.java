package pruebaDux.demo.Model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private int status;

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
