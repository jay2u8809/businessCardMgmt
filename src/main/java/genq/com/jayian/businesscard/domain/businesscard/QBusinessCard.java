package com.jayian.businesscard.domain.businesscard;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBusinessCard is a Querydsl query type for BusinessCard
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBusinessCard extends EntityPathBase<BusinessCard> {

    private static final long serialVersionUID = -1736405259L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBusinessCard businessCard = new QBusinessCard("businessCard");

    public final com.jayian.businesscard.domain.QBaseEntity _super = new com.jayian.businesscard.domain.QBaseEntity(this);

    public final NumberPath<Long> businessCardSn = createNumber("businessCardSn", Long.class);

    public final StringPath companyDepartment = createString("companyDepartment");

    public final StringPath companyJob = createString("companyJob");

    public final StringPath companyName = createString("companyName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath emailAddress = createString("emailAddress");

    public final com.jayian.businesscard.common.dto.QEmbeddedTel faxNo;

    public final com.jayian.businesscard.domain.member.QMember member;

    public final com.jayian.businesscard.common.dto.QEmbeddedName memberName;

    public final StringPath memo = createString("memo");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final EnumPath<com.jayian.businesscard.common.code.YN> ownBusinessCardYn = createEnum("ownBusinessCardYn", com.jayian.businesscard.common.code.YN.class);

    public final com.jayian.businesscard.common.dto.QEmbeddedTel phoneNo;

    public final com.jayian.businesscard.common.dto.QEmbeddedAddress repAddress;

    public final EnumPath<com.jayian.businesscard.common.code.YN> repYn = createEnum("repYn", com.jayian.businesscard.common.code.YN.class);

    public final NumberPath<Integer> sortOrder = createNumber("sortOrder", Integer.class);

    public final EnumPath<com.jayian.businesscard.common.code.UseYN> useYn = createEnum("useYn", com.jayian.businesscard.common.code.UseYN.class);

    public QBusinessCard(String variable) {
        this(BusinessCard.class, forVariable(variable), INITS);
    }

    public QBusinessCard(Path<? extends BusinessCard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBusinessCard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBusinessCard(PathMetadata metadata, PathInits inits) {
        this(BusinessCard.class, metadata, inits);
    }

    public QBusinessCard(Class<? extends BusinessCard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.faxNo = inits.isInitialized("faxNo") ? new com.jayian.businesscard.common.dto.QEmbeddedTel(forProperty("faxNo")) : null;
        this.member = inits.isInitialized("member") ? new com.jayian.businesscard.domain.member.QMember(forProperty("member"), inits.get("member")) : null;
        this.memberName = inits.isInitialized("memberName") ? new com.jayian.businesscard.common.dto.QEmbeddedName(forProperty("memberName")) : null;
        this.phoneNo = inits.isInitialized("phoneNo") ? new com.jayian.businesscard.common.dto.QEmbeddedTel(forProperty("phoneNo")) : null;
        this.repAddress = inits.isInitialized("repAddress") ? new com.jayian.businesscard.common.dto.QEmbeddedAddress(forProperty("repAddress")) : null;
    }

}

