package com.vtca.color.reader.consumer.domain.color.reference;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@ToString
@Table(name = "`color_reference`")
public class ColorReference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "color_id")
    private Long colorId;
    @Column(name = "group")
    private String group;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "description")
    private String description;
    @Column(name = "m_lab_channel_1")
    private double m_lab_channel_1;
    @Column(name = "m_lab_channel_2")
    private double m_lab_channel_2;
    @Column(name = "m_lab_channel_3")
    private double m_lab_channel_3;
    @Column(name = "m_lab_channel_4")
    private double m_lab_channel_4;
    @Column(name = "m_lab_channel_5")
    private double m_lab_channel_5;
    @Column(name = "m_rgb_r")
    private double m_rgb_r;
    @Column(name = "m_rgb_g")
    private double m_rgb_g;
    @Column(name = "m_rgb_b")
    private double m_rgb_b;
    @Column(name = "m_cmyk_c")
    private double m_cmyk_c;
    @Column(name = "m_cmyk_m")
    private double m_cmyk_m;
    @Column(name = "m_cmyk_y")
    private double m_cmyk_y;
    @Column(name = "m_cmyk_k")
    private double m_cmyk_k;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "last_update_by")
    private String lastUpdateBy;
    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;

}
