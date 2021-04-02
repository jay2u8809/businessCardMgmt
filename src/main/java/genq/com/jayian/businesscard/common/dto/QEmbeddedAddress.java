package com.jayian.businesscard.common.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmbeddedAddress is a Querydsl query type for EmbeddedAddress
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QEmbeddedAddress extends BeanPath<EmbeddedAddress> {

    private static final long serialVersionUID = 715963977L;

    public static final QEmbeddedAddress embeddedAddress = new QEmbeddedAddress("embeddedAddress");

    public final StringPath address1 = createString("address1");

    public final StringPath address2 = createString("address2");

    public final StringPath address3 = createString("address3");

    public final StringPath address4 = createString("address4");

    public final StringPath countryCode = createString("countryCode");

    public final StringPath zipCode = createString("zipCode");

    public QEmbeddedAddress(String variable) {
        super(EmbeddedAddress.class, forVariable(variable));
    }

    public QEmbeddedAddress(Path<? extends EmbeddedAddress> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmbeddedAddress(PathMetadata metadata) {
        super(EmbeddedAddress.class, metadata);
    }

}

