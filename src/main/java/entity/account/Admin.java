package entity.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Admin
 * @Description TODO
 * @Author 1
 * @Date 2020/12/30
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//管理员账户
public class Admin implements AllAccount {
    String aId;
    String account;
    String password;
    String admin_name;
}
