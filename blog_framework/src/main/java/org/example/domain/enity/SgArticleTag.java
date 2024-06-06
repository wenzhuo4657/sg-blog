package org.example.domain.enity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 文章标签关联表(SgArticleTag)表实体类
 *
 * @author makejava
 * @since 2024-02-26 09:40:24
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sg_article_tag")
public class SgArticleTag  {
    //文章id@TableId
    private Long articleId;
    //标签id@TableId
    private Long tagId;

    
}
