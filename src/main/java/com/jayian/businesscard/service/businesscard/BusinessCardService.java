package com.jayian.businesscard.service.businesscard;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

public interface BusinessCardService {

    // 정현수 : 내 명함 등록
    public void insertMyCard (MyCardInfoVO mycard);

    // 정보승	:	내 명함 추가 정보 등록
    public int updateAddMyCard(MyCardInfoVO mycard);

    // 정보승 : 등록한 내 명함 전체 조회
    public ArrayList<MyCardInfoVO> selectAllMyCard(String userid) throws DataAccessException;

    // 정보승 : 등록한 내 명함 하나 조회
    public MyCardInfoVO selectOneMyCard(HashMap<String, Object> myCard);

    // 정보승 : 내 명함 하나 삭제
    public int deleteMyCard (HashMap<String, Object> deleteMyCard);

    // 정보승 : 받은 명함 전체 가져오기
    public ArrayList<YourCardInfoVO> selectAllCard(String userid);

    // 정보승 : (모바일용) 받은 명함 전체 가져오기
    public ArrayList<YourCardInfoVO> mSelectAllCard (String userid);

    // 정보승 : 최근 받은 명함 5개 가져오기
    public ArrayList<YourCardInfoVO> recent5YourCard (String userid, RowBounds rb);

    // 정보승 : 받은 명함 등록 갯수 조회
    public int cntYourCard(String userid);

    // 정보승 : 받은 명함 등록
    public int yourCardInsert(YourCardInfoVO insertYourCardInfo);


    // 정보승 : 하나의 명함 정보 선택
    public YourCardInfoVO selectYourCardOne(HashMap<String, String> selectYourCard);

    // 정보승 : 명함 삭제(1개)
    public int deleteYourCardOne(HashMap<String, String> deleteYourCard);

    // 정보승 : 명함 수정(1개)
    public int updaetYourCardOne(YourCardInfoVO updateYourCard);

    // 전병익 : 검색한 명함 리스트를 가져옴
    public ArrayList<YourCardInfoVO> selectSearchCard(HashMap<String, String> autoComplete);
}
