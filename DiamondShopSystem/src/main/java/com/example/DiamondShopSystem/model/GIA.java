package com.example.DiamondShopSystem.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "GIA")
public class GIA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long GIAId;
    private Date issueDate;
    private Long diamondId;

    public Long getGIAId() {
        return GIAId;
    }

    public void setGIAId(Long GIAId) {
        this.GIAId = GIAId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Long getDiamondId() {
        return diamondId;
    }

    public void setDiamondId(Long diamondId) {
        this.diamondId = diamondId;
    }
}
