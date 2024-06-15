package com.example.DiamondShopSystem.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Gia")
public class Gia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long giaId;
    private Date issueDate;

    @OneToOne(mappedBy = "gia")
    private Diamond diamond;

    public Long getGiaId() {
        return giaId;
    }

    public void setGiaId(Long GiaId) {
        this.giaId = giaId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

}
