package spring.io.projects.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * 
 * @author Mr.Zhang
 * @Date 2016年11月21日
 * @Email zhangjun150429@qq.com
 */

@Entity
@Table(name = "user", indexes = { @Index(columnList = "name") })
public class User extends BaseEntity {
    private static final long serialVersionUID = 8039463663838848178L;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 11, nullable = false, unique = true)
    private String mobile;

    @Column(length = 32, unique = true)
    private String email;

    @Column
    private String passWord;

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
