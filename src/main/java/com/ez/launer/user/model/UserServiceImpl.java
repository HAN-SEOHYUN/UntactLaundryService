package com.ez.launer.user.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ez.launer.common.UserSearchVO;
import com.ez.launer.office.model.OfficeDAO;
import com.ez.launer.office.model.OfficeVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private final UserDAO userDao;
	private final OfficeDAO officeDao;
	
	// 일반회원가입
	@Override
	public int insertUser(UserAllVO vo) {
		return userDao.insertUser(vo);
	}
	@Override
	public int insertAddress(UserAllVO vo) {
		return userDao.insertAddress(vo);
	}
	// 일반회원 이메일 중복확인, 비밀번호 찾기시 소셜계정여부 확인, hp 중복확인
	@Override
	public int chkEmail(String email) {
		int count=userDao.chkEmail(email);
		
		int result=0;
		if(count>0) {
			result=UserService.UNUSABLE_EMAIL;
		}else {
			result=UserService.USABLE_EMAIL;			
		}	
		return result;
	}
	@Override
	public int chkSocial(String email) {
		int count=userDao.chkSocial(email);
		
		int result=0;
		if(count>0) {
			result=UserService.SOCIAL_EMAIL;
		}	
		
		return result;
	}
	@Override
	public int chkHp(String hp) {
		int count=userDao.chkHp(hp);
		
		int result=0;
		if(count>0) {
			result=UserService.UNUSABLE_HP;
		}else {
			result=UserService.USABLE_HP;			
		}	
		return result;
	}
	
	//배송기사 회원가입
	@Override
	public int insertDriver(DriverAllVO vo) {
		return userDao.insertDriver(vo);
	}
	
	@Override
	public int insertAccount(DriverAllVO vo) {
		return userDao.insertAccount(vo);
	}
	//배송기사 이메일, hp 중복확인
	@Override
	public int chkDmail(String email) {
		int count=userDao.chkDmail(email);
		
		int result=0;
		if(count>0) {
			result=UserService.UNUSABLE_EMAIL;
		}else {
			result=UserService.USABLE_EMAIL;			
		}	
		return result;
	}
	@Override
	public int chkDhp(String hp) {
		int count=userDao.chkDhp(hp);
		
		int result=0;
		if(count>0) {
			result=UserService.UNUSABLE_HP;
		}else {
			result=UserService.USABLE_HP;			
		}	
		return result;
	}
	
	//일반회원 로그인
	@Override
	public UserVO selectByEmail(String email) {
		return userDao.selectByEmail(email);
	}
	
	@Override
	public int loginChk(String email, String pwd) {
		String dbPwd = userDao.selectPwd2(email);
		
		int result=0;
		if(dbPwd !=null && !dbPwd.isEmpty()) {
			if(dbPwd.equals(pwd)) {
				result=UserService.LOGIN_OK;
			}else {
				result=UserService.DISAGREE_PWD;				
			}
		}else {
			result=UserService.NONE_USEREMAIL;
		}
		
		return result;
	}
	//배송기사 로그인
	@Override
	public DriverVO selectByDmail(String dmail) {
		return userDao.selectByDmail(dmail);
	}
	
	@Override
	public int dloginChk(String dmail, String dPwd) {
		String dbPwd = userDao.selectPwd3(dmail);
		
		int result=0;
		if(dbPwd !=null && !dbPwd.isEmpty()) {
			if(dbPwd.equals(dPwd)) {
				result=UserService.LOGIN_OK;
			}else {
				result=UserService.DISAGREE_PWD;				
			}
		}else {
			result=UserService.NONE_USEREMAIL;
		}
		
		return result;
	}
	
	//일반회원 이메일 찾기
	@Override
	public String findEmail(UserVO vo) {
		return userDao.findEmail(vo);
	}
	//배송기사 이메일 찾기
	@Override
	public String findDmail(DriverAllVO vo) {
		return userDao.findDmail(vo);
	}
	
	//일반회원 비밀번호 찾기 난수 업데이트
	@Override
	public int randomPwd(UserVO vo) {
		return userDao.randomPwd(vo);
	}
	//배송기사 비밀번호 찾기 난수 업데이트
	@Override
	public int randomDpwd(DriverAllVO vo) {
		return userDao.randomDpwd(vo);
	}
	
	@Override
	public UserVO selectById(int no) {
		return userDao.selectById(no);
	}

	@Override
	public HashMap<String, Object> selectByIdAddress(int no) {
		return userDao.selectByIdAddress(no);
	}

	@Override
	public int checkLogin(int no, String pwd) {
		String dbPwd = userDao.selectPwd(no);
		int result=0;
		logger.info("결과 리턴 dbPwd={}", dbPwd);
		if(dbPwd != null && !dbPwd.isEmpty()) {
			if(dbPwd.equals(pwd)) {
				result=UserService.LOGIN_OK;
			}else {
				result=UserService.DISAGREE_PWD;
			}
		}else {
			result=UserService.NONE_USEREMAIL;
		}
		

		logger.info("결과 리턴 result={}", result);
		return result;
	}

	
	@Override
	public int deleteUser(int no) {
		return userDao.deleteUser(no);
	}

	@Override
	public int updateUserHp(UserAllVO vo) {
		return userDao.updateUserHp(vo);
	}

	@Override
	public int updateUserAddress(UserAllVO vo) {
		return userDao.updateUserAddress(vo);
	}

	@Override
	public int editPwd(UserVO vo) {
		return userDao.editPwd(vo);
	}
	
	@Override
	public List<OfficeVO> selectOffice() {
		return userDao.selectOffice();
	}

	
	
	
	
	
	
	
	
	
	
	
	/* 한서현 */
	@Override
	public int insertKakaoUser(UserVO userVo) {
		return userDao.insertKakaoUser(userVo);
	}

	@Override
	public int accIsExist(String email) {
		return userDao.accIsExist(email);
	}

	@Override
	public int isAddressExist(int no) {
		return userDao.isAddressExist(no);
	}
	@Override
	public int insertAddressOnlyPart(UserAddressVO addressVo) {
		return userDao.insertAddressOnlyPart(addressVo);
	}

	@Override
	public int insertSnsUser(UserVO userVO) {
		return userDao.insertSnsUser(userVO);
	}

	@Override
	public int socialReJoin(int no) {
		return userDao.socialReJoin(no);
	}

	@Override
	public List<UserVO> selectUser(UserSearchVO userSearchVo) {
		return userDao.selectUser(userSearchVo);
	}
	@Override
	public int getUserTotalRecord(UserSearchVO userSearchVo) {
		return userDao.getUserTotalRecord(userSearchVo);
	}
	@Override
	public int insertBranchManager(UserVO userVo) {
		return userDao.insertBranchManager(userVo);
	}

	@Override
	public List<UserVO> withdrawUsers() {
		return userDao.withdrawUsers();
	}
	@Override
	public int insertToday(String userNo, String date) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int insertToday(Map<String, Object> map) {
		return userDao.insertToday(map);
	}
	@Override
	public int kakaoRejoin(UserVO userVo) {
		return userDao.kakaoRejoin(userVo);
	}






}

