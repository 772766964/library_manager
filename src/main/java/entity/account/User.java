package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Users
 * @Description TODO
 * @Author 1
 * @Date 2020/12/30
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//用户
public class User implements AllAccount {
    String uId;
    String account;
    String password;
    String admin_name;
}
