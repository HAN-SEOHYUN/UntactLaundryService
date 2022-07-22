<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/layouts/user"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<t:wrapper>
	<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" />
	<script src="<c:url value="/js/bootstrap.min.js"/>"
		type="text/javascript"></script>
	<div id="myinfo_wrap">
		<div class="myinfo_top"></div>
		<div class="myinfo_title_wrap">
			<div class="myinfo_title">
				<p>기본 정보</p>
			</div>
			<div class="myinfo_editbtn">
				<a href="/launer/mypage/useredit">
					<button type="button" class="btn btn-dark">정보 수정</button>
				</a>
			</div>
		</div>

		<div class="myinfo_container1">
			<ul class="myinfo_table1">
				<li class="myinfo_table1-row">
					<div class="myinfo_col1-1">
						<p>
							이메일 &nbsp;&nbsp;&nbsp;&nbsp;<span>${map['EMAIL']}</span>
						</p>
						<p>
							이름 &nbsp;&nbsp;&nbsp;&nbsp;<span>${map['NAME']}</span>
						</p>
						<p>
							전화번호 &nbsp;&nbsp;&nbsp;&nbsp;<span>${map['HP']}</span>
						</p>
						<p>
							가입일 &nbsp;&nbsp;&nbsp;&nbsp; <span><fmt:formatDate
									value="${map['START_DATE']}" pattern="yyyy년 MM월 dd일" /></span>
						</p>


					</div>
				</li>
			</ul>
		</div>


		<div class="myinfo_delivery">
			<p>배송 정보</p>
		</div>
		<div class="myinfo_container1">
			<ul class="myinfo_table1">
				<li class="myinfo_table1-row">
					<div class="myinfo_col1-1">
						<p>
							배송지&nbsp;&nbsp;&nbsp;&nbsp;<span>${map['ADDRESS']}&nbsp;${map['ADDRESS_DETAIL']}</span>
						</p>
						<p>
							공동현관 출입방법&nbsp;&nbsp;&nbsp;&nbsp;<span>${map['ENTERMETHOD']}</span>
						</p>

					</div>
				</li>
			</ul>
		</div>


		<div class="myinfo_withdrawbtn">
			<c:if test="${map['SOCIAL_LOGIN_KEY']==null}">
				<button type="button" class="btn btn-dark"
					onclick="location.href='/launer/mypage/editPwd'">비밀번호 변경</button>
			</c:if>
			<button type="button" class="btn btn-dark"
				onclick="location.href='/launer/mypage/withdraw'">회원 탈퇴</button>
		</div>
	</div>
	</div>
</t:wrapper>