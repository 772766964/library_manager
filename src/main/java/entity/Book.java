package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Book
 * @Description TODO
 * @Author 1
 * @Date 2021/1/6
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    String ISBN;
    String bookName;
    String author;
    String press;
    String pressDate;
    String synopsis;
    String url_img;
    String count;
}
