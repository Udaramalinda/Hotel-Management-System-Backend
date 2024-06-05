package com.suntravels.backend.SunTravelsBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "markup")
public class Markup
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer markupID;
    private float markupPercentage;

    public Markup()
    {
    }

    public Markup(Integer markupPercentage)
    {
        super();
        this.markupPercentage = markupPercentage;
    }

    public Integer getMarkupID()
    {
        return markupID;
    }

    public void setMarkupID( Integer markupID )
    {
        this.markupID = markupID;
    }

    public float getMarkupPercentage()
    {
        return markupPercentage;
    }

    public void setMarkupPercentage( Integer markupPercentage )
    {
        this.markupPercentage = markupPercentage;
    }
}
