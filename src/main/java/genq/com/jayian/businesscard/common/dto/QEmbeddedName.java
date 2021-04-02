package com.jayian.businesscard.common.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmbeddedName is a Querydsl query type for EmbeddedName
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QEmbeddedName extends BeanPath<EmbeddedName> {

    private static final long serialVersionUID = 547389526L;

    public static final QEmbeddedName embeddedName = new QEmbeddedName("embeddedName");

    public final StringPath givenName = createString("givenName");

    public final StringPath givenNameEn = createString("givenNameEn");

    public final StringPath surName = createString("surName");

    public final StringPath surNameEn = createString("surNameEn");

    public QEmbeddedName(String variable) {
        super(EmbeddedName.class, forVariable(variable));
    }

    public QEmbeddedName(Path<? extends EmbeddedName> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmbeddedName(PathMetadata metadata) {
        super(EmbeddedName.class, metadata);
    }

}

