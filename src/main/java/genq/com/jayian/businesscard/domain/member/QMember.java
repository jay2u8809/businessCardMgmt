package com.jayian.businesscard.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -2017416043L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final com.jayian.businesscard.domain.QBaseEntity _super = new com.jayian.businesscard.domain.QBaseEntity(this);

    public final ListPath<com.jayian.businesscard.domain.businesscard.BusinessCard, com.jayian.businesscard.domain.businesscard.QBusinessCard> businessCards = this.<com.jayian.businesscard.domain.businesscard.BusinessCard, com.jayian.businesscard.domain.businesscard.QBusinessCard>createList("businessCards", com.jayian.businesscard.domain.businesscard.BusinessCard.class, com.jayian.businesscard.domain.businesscard.QBusinessCard.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final EnumPath<com.jayian.businesscard.common.code.YN> emailVerifyYn = createEnum("emailVerifyYn", com.jayian.businesscard.common.code.YN.class);

    public final StringPath memberId = createString("memberId");

    public final com.jayian.businesscard.common.dto.QEmbeddedName memberName;

    public final StringPath memberPassword = createString("memberPassword");

    public final NumberPath<Long> memberSn = createNumber("memberSn", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final EnumPath<com.jayian.businesscard.common.code.UseYN> withdrawalYn = createEnum("withdrawalYn", com.jayian.businesscard.common.code.UseYN.class);

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberName = inits.isInitialized("memberName") ? new com.jayian.businesscard.common.dto.QEmbeddedName(forProperty("memberName")) : null;
    }

}

