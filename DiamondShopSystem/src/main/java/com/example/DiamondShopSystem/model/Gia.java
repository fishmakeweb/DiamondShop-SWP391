package com.example.DiamondShopSystem.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "Gia")
public class Gia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long giaId;
    private Date issueDate;

    @OneToOne(mappedBy = "gia")
    private Diamond diamond;

    @Column(name = "gia_number", unique = true)
    private String giaNumber;

    @PrePersist
    protected void onCreate() {
        if (giaNumber == null) {
            giaNumber = generateUniqueGiaNumber();
        }
    }

    private String generateUniqueGiaNumber() {
        return "GIA-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public String getGiaNumber() {
        return giaNumber;
    }

    public void setGiaNumber(String giaNumber) {
        this.giaNumber = giaNumber;
    }

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
