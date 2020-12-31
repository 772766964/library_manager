package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 1
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    String readerId;
    String readerName;
    String readerSex;
    String phone;
    String dept;
    String situation;
    String borrowAdd;
}
