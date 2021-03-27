package com.jayian.businesscard.service.businesscard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Transactional
@Service
public class BusinessCardServiceImpl implements BusinessCardService {

    @Inject
    private SqlSession	sqlSession;

    private static final Logger logger = LoggerFactory.getLogger(MyCardInfoDAO.class);


    /*
     * @comment	:	전체 내 명함 가져오기
     * @param	:	회원 ID
     * @return	:	회원이 보유하고 있는 자신의 명함 전체 리스트
     * @author	:	정보승
     */
    public ArrayList<MyCardInfoVO> selectAllMyCard(String userid) {

        logger.info("Select All My Card");

        ArrayList<MyCardInfoVO> allMyCard = null;
        MyCardInfoMapper mapper = sqlSession.getMapper(MyCardInfoMapper.class);

        try {

            allMyCard = mapper.selectAllMyCard(userid);
            logger.info("Select All My Card Result");
        }
        catch (Exception e) {

            logger.info(e.toString());
        }


        if(allMyCard != null) {

            logger.info("Select All My Card Success");
        }
        else {

            logger.info("Select All My Card Fail");
        }

        return allMyCard;

    }



    /*
     * @comment	:	내 명함 1개 가져오기
     * @param	:	회원 ID와 원하는 카드 번호
     * @author	:	정보승
     */
    public MyCardInfoVO selectOneMyCard(HashMap<String, Object> myCard) {

        logger.info("Select One My Card");

        MyCardInfoVO oneMyCard = null;
        MyCardInfoMapper mapper = sqlSession.getMapper(MyCardInfoMapper.class);

        try {

            oneMyCard = mapper.selectOneMyCard(myCard);
            logger.info("Select One My Card Result : ");
        }
        catch (Exception e) {

            logger.info(e.toString());
        }


        if(oneMyCard != null) {

            logger.info("Select One My Card Success");
        }
        else {

            logger.info("Select One My Card Fail");
        }

        return oneMyCard;

    }



    /*
     * @comment	:	명함 등록하기
     * @param	:	MyCardInfoVO
     * @author	:	정현수
     */

    public void insertMyCard (MyCardInfoVO mycard) {

        logger.info("Insert My Card Start");

        MyCardInfoMapper mapper = sqlSession.getMapper(MyCardInfoMapper.class);

        try {

            mapper.insertMyCard(mycard);
        } catch (Exception e) {

            e.printStackTrace();
        }

        logger.info("Insert My Card End");
    }


    /*
     * @comment	:	내 명함 추가 정보 등록
     * @author	:	정보승
     */
    public boolean updateAddMyCard(MyCardInfoVO mycard) {

        logger.info("Update My Card Start");

        MyCardInfoMapper mapper = sqlSession.getMapper(MyCardInfoMapper.class);
        int updateCnt = 0;

        try {

            updateCnt = mapper.updateAddMyCard(mycard);
        } catch (Exception e) {

            e.printStackTrace();
        }

        if(updateCnt == 1) {

            logger.info("Update My Card Success");
            return true;
        }
        else {

            logger.info("Update My Card Fail");
            return false;
        }

    }




    /*
     * @comment	:	회원 명함 1개 삭제
     * @author	:	정보승
     */
    public boolean deleteMyCard (HashMap<String, Object> deleteMyCard) {

        logger.info("Delete My Card Start");

        MyCardInfoMapper mapper = sqlSession.getMapper(MyCardInfoMapper.class);
        int deleteCnt = 0;

        try {

            deleteCnt = mapper.deleteMyCard(deleteMyCard);
        } catch (Exception e) {

            e.printStackTrace();
        }

        if(deleteCnt == 1) {

            logger.info("Delete My Card Success");
            return true;
        }
        else {

            logger.info("Delete My Card Fail");
            return false;
        }
    }

    @Inject
    private SqlSession sqlSession;

    private static final Logger logger = LoggerFactory.getLogger(YourCardInfoDAO.class);


    /*
     * @comment		:	회원이 받은 모든 명함을 가져오는 메소드.
     * @param		:	회원의 ID
     * @return		:	회원이 받아서 등록, 관리하는 모든 명함 	정보.
     * @author		:	정보승
     */
    public ArrayList<YourCardInfoVO> selectAllCard(String userid) {

        logger.info("YourCardInfo List");

        YourCardInfoMapper mapper = sqlSession.getMapper(YourCardInfoMapper.class);
        ArrayList<YourCardInfoVO> resultList = null;

        try {

            resultList = mapper.selectAllCard(userid);
        }
        catch(Exception e) {

            e.printStackTrace();
        }
        int resultSize = resultList.size();
        if(resultList != null && resultSize != 0) {

            logger.info("YourCardInfo List Success");
        }
        else {

            logger.info("YourCardInfo List Fail");
        }

        return resultList;
    }


    /*
     * @comment	:	(모바일용) 받은 명함 전체 조회
     * @author	:	정보승
     */
    public ArrayList<YourCardInfoVO> mSelectAllCard (String userid) {

        logger.info("Mobile YourCardInfo List");

        YourCardInfoMapper mapper = sqlSession.getMapper(YourCardInfoMapper.class);

        ArrayList<YourCardInfoVO> resultList = null;

        try {

            resultList = mapper.mSelectAllCard(userid);
        }
        catch(Exception e) {

            e.printStackTrace();
        }
        int resultSize = resultList.size();
        if(resultList != null && resultSize != 0) {

            logger.info("Mobile YourCardInfo List Success");
        }
        else {

            logger.info("Mobile YourCardInfo List Fail");
        }

        return resultList;
    }


    /*
     * @comment	:	최근 받은 명함 5개 조회
     * @author	:	정보승
     */
    public ArrayList<YourCardInfoVO> recent5YourCard (String userid) {

        logger.info("Recent 5 YourCardInfo List");

        YourCardInfoMapper mapper = sqlSession.getMapper(YourCardInfoMapper.class);

        int startRecord = 0;
        int countPerPage = 5;
        ArrayList<YourCardInfoVO> resultList = null;
        RowBounds rb = new RowBounds(startRecord, countPerPage);

        try {

            resultList = mapper.recent5YourCard(userid, rb);
        }
        catch(Exception e) {

            e.printStackTrace();
        }
        int resultSize = resultList.size();
        if(resultList != null && resultSize != 0) {

            logger.info("Recent 5  YourCardInfo List Success");
        }
        else {

            logger.info("Recent 5  YourCardInfo List Fail");
        }

        return resultList;

    }


    /*
     * @comment	:	받은 명함 등록 갯수 조회
     * @author	:	정보승
     */
    public int cntYourCard(String userid) {

        logger.info("YourCard Count");

        YourCardInfoMapper mapper = sqlSession.getMapper(YourCardInfoMapper.class);

        int yourCardCnt = 0;

        try {

            yourCardCnt = mapper.cntYourCard(userid);
        }
        catch(Exception e) {

            e.printStackTrace();
        }

        return yourCardCnt;

    }



    /*
     * @comment		:	회원이 가진 명함 중 검색어에 해당하는 명함 호출
     * @param		:	회원의 ID
     * @return		:	검색어에 해당하는 모든 명함 정보.
     * @author		:	전병익
     */
    public ArrayList<YourCardInfoVO> selectSearchCard(HashMap<String, String> autoComplete) {

        logger.info("YourCardInfo Selected List");

        YourCardInfoMapper mapper = sqlSession.getMapper(YourCardInfoMapper.class);

        ArrayList<YourCardInfoVO> resultList = null;

        try {

            resultList = mapper.selectSearchCard(autoComplete);
        }
        catch(Exception e) {

            e.printStackTrace();
        }
        int resultSize = resultList.size();
        if(resultList != null && resultSize != 0) {

            logger.info("YourCardInfo Selected List Success");
        }
        else {

            logger.info("YourCardInfo List Fail");
        }

        return resultList;
    }


    /*
     * @comment		:	회원이 받은 명함을 등록하는 메소드.
     * @param		:	받은 명함의 정보.
     * @return		:	받은 명함의 등록 여부
     * @author		:	정보승
     */
    public boolean yourCardInsert(YourCardInfoVO insertYourCardInfo) {

        logger.info("YourCardInfo Insert");

        YourCardInfoMapper mapper = sqlSession.getMapper(YourCardInfoMapper.class);

        int insertIs = 0;

        try {

            insertIs = mapper.yourCardInsert(insertYourCardInfo);
        }
        catch(Exception e) {

            e.printStackTrace();
        }

        if(insertIs == 1) {

            logger.info("Insert Your Card Success");
            return true;
        }
        else {

            logger.info("Insert Your Card Fail");
            return false;
        }

    }


    /*
     * @comment		: 하나의 명함 정보 선택
     * @author		: 전병익
     */
    public YourCardInfoVO selectYourCardOne(HashMap<String, String> selectYourCard) {

        logger.info("Select One YourCardInfo");

        YourCardInfoMapper mapper = sqlSession.getMapper(YourCardInfoMapper.class);
        YourCardInfoVO resultYourCard = null;

        try {

            resultYourCard = mapper.selectYourCardOne(selectYourCard);
        }
        catch(Exception e) {

            e.printStackTrace();
        }

        if(resultYourCard != null) {

            logger.info("Select One YourCardInfo Success");
        }
        else {

            logger.info("Select One YourCardInfo Fail");
        }

        return resultYourCard;

    }



    /*
     * @comment		: 명함 삭제 (1개)
     * @author		: 정보승
     */
    public boolean deleteYourCardOne(HashMap<String, String> deleteYourCard) {

        logger.info("Delete One YourCardInfo");

        YourCardInfoMapper mapper = sqlSession.getMapper(YourCardInfoMapper.class);
        int deleteCnt = 0;

        try {

            deleteCnt = mapper.deleteYourCardOne(deleteYourCard);
        }
        catch (Exception e) {

            e.printStackTrace();
        }

        if(deleteCnt == 1) {

            logger.info("Delete One YourCardInfo Success");
            return true;
        }
        else {

            logger.info("Delete One YourCardInfo Fail");
            return false;
        }

    }



    /*
     * @comment	:	명함 정보 수정
     * @author	:	정보승
     */
    public boolean updaetYourCardOne(YourCardInfoVO updateYourCard) {

        logger.info("Update One YourCardInfo");

        YourCardInfoMapper mapper = sqlSession.getMapper(YourCardInfoMapper.class);
        int updateCnt = 0;

        try {

            updateCnt = mapper.updaetYourCardOne(updateYourCard);
        }
        catch (Exception e) {

            e.printStackTrace();
        }

        if(updateCnt == 1) {

            logger.info("Update One YourCardInfo Success");
            return true;
        }
        else {

            logger.info("Update One YourCardInfo Fail");
            return false;
        }

    }
}
