package com.dajungdagam.dg.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@DynamicUpdate
@DynamicInsert
@Table(name = "post")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")

    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    @Column(length = 50, name = "post_title", nullable = false)
    private String title;

    @Column(columnDefinition = "integer default 0")
    @NonNull
    private Integer price;

    @Column(columnDefinition = "integer default 0")
    @NonNull
    private Integer personCount;

    @Column(columnDefinition = "integer default 0")
    @NonNull
    private Integer personCurrCount;

    @Column
    private LocalDateTime deadline;

    @Column(name = "post_type", nullable = false)
    private int postType;

    @Column(length = 10, name = "trade_area")
    private String tradeArea;

    @Column(columnDefinition = "TEXT", name = "tp_content", nullable = false)
    private String content;

    @Column(columnDefinition = "TIMESTAMP", name = "created_time")
    private LocalDateTime createdTime;

    @Column(columnDefinition = "TIMESTAMP", name = "update_time")
    private LocalDateTime updateTime;

    @Column(columnDefinition = "integer default 0", name = "view_count")
    private int viewCount;

    @Column(columnDefinition = "integer default 0", name = "wishlist_count")
    private Long wishlistCount;

    @Column(columnDefinition = "TEXT", name = "chat_link", nullable = false)
    private String chatLink;

    @Enumerated(EnumType.STRING)
    @Column(name = "trade_status", nullable = false)
    private TradeStatus tradeStatus;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, //orphanRemoval = true,
                fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Image> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_category_id", nullable = false)
    private ItemCategory itemCategory;


    @Builder
    public Post(Long id, User user, Area area, String title, int postType,
                String tradeArea, String content, LocalDateTime createdTime,
                LocalDateTime updateTime, int viewCount, Long wishlistCount,
                String chatLink, TradeStatus tradeStatus, List<Image> images,
                ItemCategory itemCategory) {

        this.id = id;
        this.user = user;
        this.area = area;
        this.title = title;
        this.postType = postType;
        this.tradeArea = tradeArea;
        this.content = content;
        this.createdTime = createdTime;
        this.updateTime = updateTime;
        this.viewCount = viewCount;
        this.wishlistCount = wishlistCount;
        this.chatLink = chatLink;
        this.tradeStatus = tradeStatus;
        this.images = images;
        this.itemCategory = itemCategory;
    }

}