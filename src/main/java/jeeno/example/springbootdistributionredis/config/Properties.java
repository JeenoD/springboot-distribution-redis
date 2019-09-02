package jeeno.example.springbootdistributionredis.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 杜家浩
 * @version 0.0.1
 * @date 2019/9/2 20:37
 */
@Component
@Data
public class Properties {

    /**
     * port of application
     */
    @Value("${server.port}")
    private String port;

}
