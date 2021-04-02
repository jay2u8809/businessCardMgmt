package com.jayian.businesscard.common.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmbeddedTel is a Querydsl query type for EmbeddedTel
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QEmbeddedTel extends BeanPath<EmbeddedTel> {

    private static final long serialVersionUID = -397978384L;

    public static final QEmbeddedTel embeddedTel = new QEmbeddedTel("embeddedTel");

    public final StringPath phoneNo = createString("phoneNo");

    public QEmbeddedTel(String variable) {
        super(EmbeddedTel.class, forVariable(variable));
    }

    public QEmbeddedTel(Path<? extends EmbeddedTel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmbeddedTel(PathMetadata metadata) {
        super(EmbeddedTel.class, metadata);
    }

}

