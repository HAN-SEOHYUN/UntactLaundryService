package com.ez.launer.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ez.launer.user.model.KakaoAPI;
import com.ez.launer.user.model.UserService;
import com.ez.launer.user.model.UserVO;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/user/kakaoLogin")
@RequiredArgsConstructor
public class KakaoLoginController {
	
	private static final Logger logger
	 = LoggerFactory.getLogger(KakaoLoginController.class);

    private final KakaoAPI kakao;
	private final UserService userService;
	
	
	
	
//한서현 카카오로그인
	
	@RequestMapping(value = "/requestToken")
	public String kakaoLoginRequestToken(@RequestParam ("code")String code,Model model) {
		
		logger.info("카카오로그인컨트롤러 code ={}",code );
		model.addAttribute("code",code);

		//accessToken 발급받기
		String access_Token = kakao.getAccessToken(code);
		logger.info("controller access_token ={} " , access_Token);
		
		//user email과 name, kakaoId 받아오기 
		HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
		logger.info("userInfo={}",userInfo);
		
		String name = (String)userInfo.get("name");
		String email = (String)userInfo.get("email");
		String socialLoginKey = (String)userInfo.get("socialLoginKey");
		
		//db존재여부 check
		int count = userService.accIsExist(email);
		logger.info("count(*) = {}",count);

		String socialInfo = "";
		UserVO userVo = new UserVO();
		
		String url ="/user/login", msg ="로그인처리 실패";
		
		if(count > 0) { //존재하면 social_login_host 받아서 model 저장
			socialInfo = userService.getSocialInfo(email);
			logger.info("socialInfo={}",socialInfo);
			msg =socialInfo + " 로 로그인되었습니다";
			url = "/";

			
		}else {
			// 존재 X => 회원정보 insert
			if (userInfo.get("email") != null) {
				
				userVo.setName(name);
				userVo.setEmail(email);
				userVo.setPwd(socialLoginKey); 
				userVo.setSocialLoginKey(socialLoginKey);
				logger.info("미가입회원 userVo ={}",userVo);
			}	
			int cnt = userService.insertKakaoUser(userVo);
			logger.info("카카오 회원가입결과={}",cnt);
			url ="/";
			msg =name+ "님, 회원가입되었습니다";
		} //if
		
		
		model.addAttribute("msg",msg);
		model.addAttribute("url",url);
		
		return "common/message";
	}

		


}
