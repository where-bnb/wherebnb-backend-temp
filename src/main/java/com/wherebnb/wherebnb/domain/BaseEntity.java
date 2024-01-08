package com.wherebnb.wherebnb.domain;

import com.wherebnb.wherebnb.domain.BaseTimeEntity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@Getter
@MappedSuperclass
public abstract class BaseEntity extends BaseTimeEntity {

    @CreatedBy
    private String createBy;

    @LastModifiedBy
    private String lastModifiedBy;
}
