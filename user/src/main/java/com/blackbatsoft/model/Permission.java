package com.blackbatsoft.model;


import javax.persistence.*;

/**
 * Created by sunb on 2017/7/13.
 */
@Entity
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String permissonName;

    @Column
    private String permissonSign;

    @Column
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissonName() {
        return permissonName;
    }

    public void setPermissonName(String permissonName) {
        this.permissonName = permissonName;
    }

    public String getPermissonSign() {
        return permissonSign;
    }

    public void setPermissonSign(String permissonSign) {
        this.permissonSign = permissonSign;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
