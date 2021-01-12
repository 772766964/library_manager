package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName RB
 * @Description TODO
 * @Author 1
 * @Date 2021/1/12
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RB {
    String workerId;
    String ISBN;
    String readerId;
    String BorrowDate;
    String inbookDate;
    String iflend;
}
