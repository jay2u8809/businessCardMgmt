package com.jayian.businesscard.service.businesscard;

import com.jayian.businesscard.common.code.UseYN;
import com.jayian.businesscard.common.CommonExtends;
import com.jayian.businesscard.domain.businesscard.BusinessCard;
import com.jayian.businesscard.domain.businesscard.BusinessCardRepository;
import com.jayian.businesscard.domain.businesscard.QBusinessCard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class BusinessCardServiceImpl extends CommonExtends implements BusinessCardService {

    private final BusinessCardRepository businessCardRepository;

    private final JPAQueryFactory factory;

    @Override
    public BusinessCard saveBusinessCard(BusinessCard businessCard) {
        return businessCardRepository.save(businessCard);
    }

    @Override
    public boolean deleteBusinessCard(Long businessCardSn) {

        BusinessCard deleteBusinessCard = businessCardRepository.findById(businessCardSn)
                                                                .orElse(null);

        if (deleteBusinessCard == null) return false;

        deleteBusinessCard.setUseYn(UseYN.N);
        businessCardRepository.save(deleteBusinessCard);

        return true;
    }

    @Override
    public Integer deleteBusinessCards(Long memberSn, List<Long> businessCardSnList) {

        QBusinessCard qBusinessCard = QBusinessCard.businessCard;

        List<BusinessCard> deleteBusinessCarsList = factory.query()
                .select(qBusinessCard)
                .from(qBusinessCard)
                .where(qBusinessCard.member.memberSn.eq(memberSn)
                        .and(qBusinessCard.businessCardSn.in(businessCardSnList))
                        .and(qBusinessCard.useYn.eq(UseYN.Y)))
                .fetch();

        if (ObjectUtils.isEmpty(deleteBusinessCarsList)) return 0;

        deleteBusinessCarsList.forEach(f -> f.setUseYn(UseYN.N));

        return businessCardRepository.saveAll(deleteBusinessCarsList).size();
    }

}
