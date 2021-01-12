package utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ResultEntity
 * @Description TODO
 * @Author 1
 * @Date 2020/12/30
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultEntity {
    private int code;
    private String message;
    private Object data;
}
