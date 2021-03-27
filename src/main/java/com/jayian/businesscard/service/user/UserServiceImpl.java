package com.jayian.businesscard.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Inject

    private SqlSession sqlSession;



    private static final Logger logger = LoggerFactory.getLogger(UserInfoDAO.class);



    /*

     *  @comment		: 회원 가입

     *  @param joinUser : 회원가입 정보를 가진 객체

     *  @return			: 회원 가입 성공 여부

     *  @author			: 정보승

     */

    public boolean joinUser(UserInfoVO joinUser) {



        logger.info("Join User Start");



        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);



        boolean result = false;

        int joinCnt = 0;



        try {



            joinCnt = mapper.joinUser(joinUser);

            logger.info("Join User Count");

        }

        catch(Exception e) {



            e.printStackTrace();

        }



        if(joinCnt == 1) {



            logger.info("Join User Success");



            result = true;

        }

        else {



            logger.info("Join User Fail");



            result = false;

        }



        return result;

    }





    /*

     *  @comment		: E-mail 인증을 확인

     *  @param	userid	: E-mail 인증을 받은 Mail Address

     *  @return			: E-mail 인증 여부

     *  @author			: 정보승

     */

    public boolean verifyUser(String userid) {



        logger.info("E-mail Verify Start");



        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);



        boolean result = false;

        int updateCnt = 0;



        try {



            updateCnt = mapper.verifyUser(userid);

        }

        catch(Exception e) {



            e.printStackTrace();

        }



        if(updateCnt == 1) {



            logger.info("E-mail Verify Success");



            result = true;

        }

        else {



            logger.info("E-mail Verify Fail");



            result = false;

        }



        return result;



    }



    /*

     * @comment			: 회원 로그인 + 회원 정보 조회 : 비밀번호를 제외한 모든 정보가 출력

     * @param loginUser	: 회원 ID와 회원 Pw를 저장한 HashMap

     * @return			: 회원의 정보를 저장한 객체를 반환

     * @author			: 정보승

     */

    public UserInfoVO selectUser(HashMap<String, Object> loginUser) {



        logger.info("Select User Info Start");



        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);

        UserInfoVO userInfo = null;



        try {



            userInfo = mapper.selectUser(loginUser);

        }

        catch(Exception e) {



            e.printStackTrace();

        }



        if(userInfo != null) {



            logger.info("Select User Info Success");

        }

        else {



            logger.info("Select User Info Fail");

        }



        return userInfo;

    }







    /*

     * @comment				: 회원 정보 수정

     * @param	updateUser	: 수정된 회원 정보를 저장한 객체

     * @return				: 회원 정보 수정 성공 여부

     * @author				: 정보승

     */

    public boolean updateUserInfo(UserInfoVO updateUser) {



        logger.info("Update User Info Start");



        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);

        boolean result = false;

        int updateCnt = 0;



        try {



            updateCnt = mapper.updateUserInfo(updateUser);

            logger.info("Update User Info Cnt : " + updateCnt);

        }

        catch(Exception e) {



            e.printStackTrace();

        }



        if (updateCnt == 1) {



            logger.info("Update User Info Success");



            result = true;

        }

        else {



            logger.info("Update User Info Fail");



            result = false;

        }



        return result;

    }



    //대표이미지 가져오기 여지원

    public String getProfile(String userid){

        logger.info("getProfile Start");

        String profilecardNum = null;

        try {

            UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);

            profilecardNum = mapper.getProfile(userid);

        }

        catch(Exception e) {



            e.printStackTrace();

        }



        return profilecardNum;

    }

}
