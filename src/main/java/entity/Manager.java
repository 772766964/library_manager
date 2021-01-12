package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Manager
 * @Description TODO
 * @Author 1
 * @Date 2021/1/6
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Manager {
    String workerId;
    String name;
    String sex;
    String phones;
    String wages;
}
