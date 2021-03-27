package com.jayian.businesscard.service.businesscard;

import com.jayian.businesscard.common.code.UseYN;
import com.jayian.businesscard.common.dto.CommonExtends;
import com.jayian.businesscard.domain.businesscard.BusinessCard;
import com.jayian.businesscard.domain.businesscard.BusinessCardRepository;
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

    @Override
    public BusinessCard saveBusinessCard(BusinessCard businessCard) {
        return businessCardRepository.save(businessCard);
    }

    @Override
    public boolean deleteBusinessCard(Long businessCardSn) {

        BusinessCard deleteBusinessCard = businessCardRepository.findById(businessCardSn)
                                                                .orElse(null);

        if (deleteBusinessCard == null) return false;

        deleteBusinessCard.setUseYn(UseYN.Y);
        businessCardRepository.save(deleteBusinessCard);

        return true;
    }

    @Override
    public Integer deleteBusinessCards(Long memberSn, List<Long> businessCardSnList) {

        List<BusinessCard> deleteBusinessCarsList = businessCardRepository.findAllById(businessCardSnList);

        if (ObjectUtils.isEmpty(deleteBusinessCarsList)) return 0;

        deleteBusinessCarsList.forEach(f -> f.setUseYn(UseYN.N));

        return businessCardRepository.saveAll(deleteBusinessCarsList).size();
    }

}
