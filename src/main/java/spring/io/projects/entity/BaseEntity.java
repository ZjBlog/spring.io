package spring.io.projects.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 
 * @author Mr.Zhang
 * @Date 2016年11月21日
 * @Email zhangjun150429@qq.com
 * 
 */
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -2963618002145345835L;

    @Id
    @Column(length = 32)
    @GeneratedValue(generator = "sys-uuid")
    @GenericGenerator(name = "sys-uuid", strategy = "uuid")
    private String id;

    @CreatedBy
    @Column(name = "created_by", length = 32)
    private String createdBy;

    /**
     * 注意:CURRENT_TIMESTAMP 在 5.6+ 版本才能赋值DEFAULT到DATETIME 类型的数据中 所以此处使用timestamp
     * 只有在5.6+版本上,一个表中才能有多个时间字段的默认值为DEFAULT CURRENT_TIMESTAMP
     */
    @CreatedDate
    @Column(name = "created_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 32)
    private String lastModifiedBy;

    /**
     * 此处使用默认值为0 我的mysql版本5.5+ select version()查看版本
     */
    @LastModifiedDate
    @Column(name = "last_modified_date", columnDefinition = "TIMESTAMP DEFAULT 0 not null COMMENT '最后修改时间'")
    private Date lastModifiedDate;

    // getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}