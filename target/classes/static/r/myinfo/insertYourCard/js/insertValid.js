/**
 * 카드 등록시 유효성 검사
 */

function insertValid() {
		
	var name1 = $("#name1").val();
	var company = $("#company").val();
	var phone = $("#phone").val();
	var pattern = /[%^&*()+|<>?:{}[]\"\']/;	// 특수문자

	if(name1 == "") {
		
		alert("첫 번째 이름은 반드시 입력해야 합니다.");
		return false;
	}
	else if(company == "") {
		
		alert("회사명은 반드시 입력해야 합니다.");
		return false;
	}
	else if(phone == "") {
		
		alert("휴대전화 번호는 반드시 입력해야 합니다.");
		return false;
	}
	else {
		
		return true;
	}
}



/*
 * @comment	:	받은 명함 정보 수정하기 메소드
 * @author	:	정보승
 */
function updateValid() {
	
	var name1 = $("#name1").val();
	var company = $("#company").val();
	var phone = $("#phone").val();
	var pattern = /[%^&*()+|<>?:{}[]\"\']/;	// 특수문자

	if(name1 == "") {
		
		alert("첫 번째 이름은 반드시 입력해야 합니다.");
		return false;
	}
	else if(company == "") {
		
		alert("회사명은 반드시 입력해야 합니다.");
		return false;
	}
	else if(phone == "") {
		
		alert("휴대전화 번호는 반드시 입력해야 합니다.");
		return false;
	}
	else {
		
		return true;
	}
}
