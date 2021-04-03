package com.jayian.businesscard.service.businesscard;

import com.jayian.businesscard.JpaTestConfiguration;
import com.jayian.businesscard.common.dto.CommonExtends;
import com.jayian.businesscard.domain.businesscard.BusinessCard;
import com.jayian.businesscard.domain.businesscard.BusinessCardRepository;
import com.jayian.businesscard.domain.businesscard.QBusinessCard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

//@ActiveProfiles("")
//@EnableJpaRepositories("a.b.c")
//@EntityScan("a.b.c")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaTestConfiguration.class)
@DataJpaTest
public class BusinessCardServiceImplTest extends CommonExtends {

    @Autowired
    private BusinessCardRepository businessCardRepository;
    @Autowired
    private JPAQueryFactory factory;

    @Autowired(required = false)
    private BusinessCardService businessCardService;

    @BeforeEach
    public void preProcess() {
        businessCardService = new BusinessCardServiceImpl(businessCardRepository, factory);
    }

    @Test
    void deleteBusinessCardsTest() {

        businessCardService.deleteBusinessCards(100L, null);
    }

}