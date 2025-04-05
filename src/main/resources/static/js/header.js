
	// 쿠키에서 특정 값을 찾는 함수
	function getCookie(name) {
	    const value = `; ${document.cookie}`;
	    const parts = value.split(`; ${name}=`);
	    if (parts.length === 2) return parts.pop().split(';').shift();
	    return null;
	}
	
	// 쿠키에서 특정 값을 지우는 함수
	function deleteCookie(name) {
	    document.cookie = `${name}=; Max-Age=-99999999; path=/`;
	}
	
	// 헤더의 상태를 변경하는 함수
	function updateHeaderBasedOnCookie() {
	    const loginButton = document.querySelector('.login-button');
	    const logoutButton = document.querySelector('.logout-button');
	    
	    // JSESSIONID나 사용자 정의 쿠키 확인
	    const sessionId = getCookie('JSESSIONID'); // 세션 쿠키 이름
	    if (sessionId) {
	        // 로그인 상태
	        loginButton.style.display = 'none';
	        logoutButton.style.display = 'flex';
	    } else {
	        // 비로그인 상태
	        loginButton.style.display = 'flex';
	        logoutButton.style.display = 'none';
	    }
	}
	
	// 로그아웃 함수
	function logout() {
	    // 쿠키 삭제
	    deleteCookie('JSESSIONID');
	    
	    // 상태 업데이트
	    updateHeaderBasedOnCookie();
	}
	
	// 페이지 로드 시 실행
	document.addEventListener('DOMContentLoaded', updateHeaderBasedOnCookie);
	
	// 로그아웃 버튼에 이벤트 리스너 추가
	const logoutButton = document.querySelector('.logout-button');
	if (logoutButton) {
	    logoutButton.addEventListener('click', logout);
	}
	
