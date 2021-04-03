package com.jayian.businesscard.service.businesscard;

import com.jayian.businesscard.domain.businesscard.BusinessCard;

import java.util.List;

public interface BusinessCardService {

    /**
     * Save a Business Card
     * @param businessCard
     * @return
     */
    default BusinessCard saveBusinessCard(BusinessCard businessCard) {
        return null;
    }

    /**
     * Delete a Business Card
     * @param businessCardSn
     * @return
     */
    default boolean deleteBusinessCard(Long businessCardSn) {
        return false;
    }

    /**
     * Delete Business Cards
     * @param memberSn
     * @param businessCardSnList
     * @return
     */
    default Integer deleteBusinessCards(Long memberSn, List<Long> businessCardSnList) {
        return -1;
    }

    default BusinessCard getMyRepBusinessCard(Long memberSn) {
        return null;
    }

    default List<BusinessCard> getAllMemberBusinessCards(Long memberSn) {
        return null;
    }

    default Integer getAllMemberBusinessCardsCnt(Long memberSn) {
        return -1;
    }

    default List<BusinessCard> getAllMyBusinessCards(Long memberSn) {
        return null;
    }

    default Integer getAllMyBusinessCardsCnt(Long memberSn) {
        return -1;
    }

    default List<BusinessCard> getAllReceivedAllBusinessCards(Long memberSn) {
        return null;
    }

    default Integer getAllReceivedAllBusinessCardsCnt(Long memberSn) {
        return -1;
    }

    default List<BusinessCard> searchBusinessCards(Long memberSn) {
        return null;
    }
}
