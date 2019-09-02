package jeeno.example.springbootdistributionredis;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * encapsulated class of return data
 * @author 杜家浩
 * @version 0.0.1
 * @date 2019/9/2 20:41
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReturnDTO<T> {
    private T data;
    private State state;
    private String msg;

    /**
     * whether succeed this request.
     */
    public enum State{
        /**
         * success
         */
        SUCCESS(Boolean.TRUE),
        /**
         * failure
         */
        FAILURE(Boolean.FALSE);

        /**
         * mark
         */
        private Boolean state;

        State(Boolean state){
            this.state = state;
        }
    }
}
