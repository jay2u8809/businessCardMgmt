/**
 * 
 */

// 이미지 정보들을 담을 배열
var sel_files = [];
var firstImg = true;
var index = 0;
var num = 0;			// <input ~~> 들의 id 구분 변수
var totalLen = 0;		// 전체 이미지들의 갯수
var inputTypeNum = 0;	// inputType의 갯수
var uploadFile;			// 서버로 이미지를 보내기 위한 변수.


/*
 *	@comment	:	 
 */
$(document).ready(function() {
	$("#input_imgs").on("change", handleImgFileSelect)
	//getMyCardsImg();
});


/*
 *	@comment	:	 파일 업로드 버튼과 복수의 <input type="file" /> 태그의 연결 메소드
 */
function fileUploadAction() {
	
	var	inputTag = "<input type='file' class='fileUploadBtn' id='input_imgs" + num + "' multiple />";

	$("#input_imgs").append(inputTag);		// input Tag 생성
	$("#input_imgs" + num).trigger("click");	// input Tag 실행
	
	num++;
}


/*
 *	@comment	:	업로드한 이미지들의 미리보기 화면 생성 메소드
 */ 
function handleImgFileSelect(e) {
	// 이미지 정보들을 초기화
	sel_files = [];
	
	// <div class="imgs_wrap"> 의 영역을 비워준다.
	if(firstImg == true){
		 
		$(".imgs_wrap").empty();
		firstImg = false;
	};
	
	var files = e.target.files; 						// 업로드한 이미지 파일을 담을 변수
	var filesArr = Array.prototype.slice.call(files); 	// 업로드한 이미지 파일들을 담을 리스트
	
	filesArr[index];
	
	var inputLen = 0;		// input으로 업로드한 이미지의 갯수.
	
	filesArr.forEach(function(f) {
		if(!f.type.match("image.*")) {
			alert("이미지 파일만 가능합니다.");
			return;
		};
		
		$("#comment").hide();		// <P>NO IMAGES , PLEASE CLICK YOUR IMAGES</P> 감추기
		
		sel_files.push(f);
		var reader = new FileReader();
		
		reader.onload = function(e) {
			
			var src = e.target.result;
			// 업로드한 이미지의 미리보기 영역 생성
			var makeImgPreview = "<div class='imgPreview_"+index+"' style='margin-left : 20px; margin-top : 10px; float : left;'>";
				makeImgPreview += "<a href='javascript:void(0)' onclick='moveToDiv(\"" + src + "\"," + (num-1) + "," + inputLen + "," + index + ")'>";
				makeImgPreview += "<img style='width : 200px;' src='" + e.target.result + "' data-file='" + f.name + "' class='selProductFile' id='ocrImg_" + index + "'>";
				makeImgPreview += "</a>";
				makeImgPreview += "<div class='imgPreview_Del' style='float : right ' id='delBTN"+index+"'>";
				makeImgPreview += " <img class='delBtnClass'  src='../resources/img/delBTN.png' onclick='delOne("+index+")'>";
				makeImgPreview += "</div>";
				makeImgPreview += "</div>";
				
			    
			$(".imgs_wrap").append(makeImgPreview);
			
			index++;
			inputLen++;
		};
		
		//이미지가 가운데로 옮겨질 때, getMyCardsImg함수 발동
		getMyCardsImg();
	
		// 이미지 파일을 보여주기
		reader.readAsDataURL(f);
		
		// 
		/*if(inputLen <= $("#input_imgs"+(num-1))[0].files.length) {
			
			inputLen = 0;
		}*/
	});
	
}



/*
 * @comment		:	삭제버튼 누르고 미리보기 이미지 삭제
 * @param		:	미리보기 이미지의 번호
 * @author		:	여지원	
 */
function delOne(index){
	
	var ocrImg = "ocrImg_"+index;
	var bool = confirm("이미지를 목록에서 지울까요?");
	
	if(!bool){
		
		return false;
	}
	//class='ocrImg_" + index + "' 
	//$("."+ocrImg+"").remove();
	$(".imgPreview_"+index+"").remove();
	alert(".imgPreview_"+index+"");
	$("#delBTN"+index+"").remove();
	
	
}



/*
 *	@comment			:	업로드한 이미지들의 미리보기 화면 생성 메소드
 *	@param	src			:	업로드한 이미지의 바이너리 경로(경로자체가 이미지 파일)
 *			inputTagNum	:	동적 생성된 <input type="file" />의 수
 *			inputNum	:	동적 생성된 <input type="file" />에서 업로드한 이미지 파일의 수
 *			index		:	사용자가 업로드한 전체 이미지의 수
 */ 
function moveToDiv(src, inputTagNum, inputNum, index){
			 
	//  업로드한 이미지 파일을 나타내는 경로를 <div class='selectedImg img' src=''> 에 추가한다.
	$(".selectedImg img").attr("src", src);
	
	// 이미지 미리보기를 선택할 경우 이미지에 테두리를 나타나게 한다.
	//$("#ocrImg_" + index).css('border', 'solid 3px red');
	$("#ocrImg_" + index).trigger("create");
	
	
	
	var form = $('#tempUpload')[0];
	var formData = new FormData(form);
	
	var filename = $("#input_imgs"+inputTagNum)[0].files[inputNum].name;  	// 실제 리스트에 올라간 name
	var fileLen = $("#input_imgs"+inputTagNum)[0].files.length;				// 실제 리스트의 파일갯수

	var dataFileName = $("#ocrImg_"+index).attr("data-file");				// 화면 이미지의 파일명.
	
	uploadFile = filename;
	// 화면과 실제 리스트의 파일명을 맞춰주기 위한 반복문
	for(var i=0; i<fileLen; i++) {
		
		if(dataFileName === $("#input_imgs"+inputTagNum)[0].files[i].name) {
			
			formData.append("fileObj", $("#input_imgs"+inputTagNum)[0].files[i]);
		}
	} 

	// 이미지 미리보기를 하는 순간 OCR 분석을 위해 이미지를 서버로 전송.
	$.ajax({
	 
		url : "tempUpload"
		, processData: false
		, contentType: false
		, type : "post"
		, data : formData
		, success : function(ocrImage) {
			 
			// 이미지 전송이 성공함과 동시에 OCR분석
			$.ajax({
			
				url : "detectTextFromImage"
				, type : "GET"
				, data : {
				
					"ocrImage" : ocrImage
				}
				, success : function (detectResult) {	// detectResult : OCR 분석 결과 String.
					
					$("#cardInfo").html('');
					console.log(detectResult);
					$("#comment").show();		// 나중에 삭제
					$("#cardBtn").show();
					
					
					var str = detectResult;
					$("#comment").html(str);	// 나중에 삭제
					
					//OCR 분석 자료
					resultInput(detectResult);
					
					// Ajax 후 CSS 적용을 위한 이벤트 발생
					$("#cardInfo").trigger("create");
					
				}
				, error : function (e) {
			
					alert("실패: "+e);
				}
			});
		}
		, error : function(e) {
			console.log(e);
		}
	});

}


/*
 *	@comment	:	OCR분석 결과를 텍스트에 집어넣기
 * @author		:	정보승
 */
//정규표현식들
//var regExp = /\s/g;					//모든 공백 체크 정규식
//var numberRegExp = /^[0-9]+$/;		//숫자만 체크 정규식
//var urlRegExp = /(((http(s?))\:\/\/)?)([0-9a-zA-Z\-]+\.)+[a-zA-Z]{2,6}(\:[0-9]+)?(\/\S*)?$/;
//var eMailRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
//var phoneRegExp = /\w\/cell\/[ ]\d{3}[- ]?\d{3,4}[- ]?\d{4}/g;
//var telRegExp = /\d[0]{2,3}?[- ]?\d{3,4}[- ]?\d{4}/g;


var jobRegExp = /사장|ceo|대표|부장|소장|고문|과장|대리|계장|사원|선임|주임|manager|비서|실장|상무|위원|차장|점장|팀장/g;
var departmentRegExp = /인사|총무|회계|기획|영업|경리|경영|재경|구매|전략|기획|연구|시설|홍보|금융/g;
var cityNameRegExp = /서울|대구|부산|광주|대전|인천|울산|세종|경기도|경상북도|경북|경상남도|경남|전라북도|전북|전라남도|전남|충청북도|충북|충청남도|충남|강원도|제주도|제주/g;
var urlRegExp = /([0-9a-zA-Z\-]+\.)+[a-zA-Z]{2,6}(\:[0-9]+)?(\/\S*)?$/;	//도메인 형태, http:// https:// 포함안해도 되고 해도 되고
var eMailRegExp = /[0-9a-zA-Z]*[\-\_\.]?[0-9a-zA-Z]*\@[0-9a-zA-Z]*[\-\_\.]?[0-9a-zA-Z]*\.[a-zA-Z]{2,3}[\.]?[a-zA-Z]{2}?/g;
var phoneRegExp =  /[0][1]\d{1}[- ]?\d{3,4}[- ]?\d{4}$/;
var telRegExp = /\d{2,3}[- ]?\d{3,4}[- ]?\d{4}$/;
var delRegExp = /tel|fax|phone|cell/g;
function resultInput(detectResult) {
	 
	// 엔터키 기준으로 OCR 분석 결과 자르기
	var detectResultArr = detectResult.split('\n');
	var detectResultArrLen = detectResultArr.length;
	inputTypeNum = detectResultArrLen;
	
	// 회원ID를 hidden으로 추가
	$("#cardInfo").append("<input type='hidden' name='userid' />");
	
	// 현재 이미지의 파일명을 저장. 	
	$("#cardInfo").append("<input type='hidden' name='uploadImg' value='"+ uploadFile + "' />");
	
	var htmlCode = '';
	var keyName = '';
	
	
	//폼이 시작하는 가장 첫번째에 성별선택을 넣는다.
	htmlCode += "<div class='input-group'>";
	htmlCode += "<span class='input-group-addon' style='height: 70px;'>" + "성별" + "</span>";
	htmlCode += "<input type ='radio' name='selectGender' value='M' checked >남성   ";
	htmlCode += "<input type ='radio' name='selectGender' value='W'>여성   ";
	htmlCode += "</div>";
	
	
	for(var i=0; i<detectResultArrLen; i++) {
		var delStr = "<div class ='delMenuOne' style='float:right;' ><img class='delBtnClass' src='../resources/img/delBTN.png' onclick='delForm("+i+")'></div> "; 
		console.log(detectResultArr[i]);
		
		// OCR 분석이 된 문자열의 길이가 1인 경우나 delRegExp에 해당하는 경우 생략
		if(	detectResultArr[i].length == 1)
		{
			
			continue;
		}
		
		
		// E-Mail
		if(eMailRegExp.test(detectResultArr[i])) {
			
			htmlCode += "<div class='input-group'id='input-group"+i+"'>";
				htmlCode += "<span class='input-group-addon'>" + "E-Mail" + "</span>";
				htmlCode += "<textarea class='form-control' name='email' id='cardInfo_" + i + "' >" + detectResultArr[i] +  "</textarea>";
				htmlCode += delStr;
			htmlCode += "</div>";
		}
		// 회사 홈페이지 주소
		else if(urlRegExp.test(detectResultArr[i])) {
			
			htmlCode += "<div class='input-group'id='input-group"+i+"'>";
				htmlCode += "<span class='input-group-addon'>" + "Etc" + "</span>";
				htmlCode += "<textarea class='form-control' name='otherinfo' id='cardInfo_" + i + "' >" + detectResultArr[i] +  "</textarea>";
				htmlCode += delStr;
			htmlCode += "</div>";
		}
		// 휴대폰 번호
		else if( phoneRegExp.test(detectResultArr[i])) {
			
			htmlCode += "<div class='input-group'id='input-group"+i+"'>";
				htmlCode += "<span class='input-group-addon'>" + "PHONE" + "</span>";
				htmlCode += "<textarea class='form-control' name='phone' id='cardInfo_" + i + "' >" + detectResultArr[i] +  "</textarea>";
				//추가!!!!!!!
				htmlCode += delStr;
			htmlCode += "</div>";
				
		}
		// 전화번호
		else if(telRegExp.test(detectResultArr[i])) {

			htmlCode += "<div class='input-group'id='input-group"+i+"'>";
				htmlCode += "<span class='input-group-addon'>" + "TEL" + "</span>";
				htmlCode += "<textarea class='form-control' name='tel' id='cardInfo_" + i + "' >" + detectResultArr[i] +  "</textarea>";
				htmlCode += delStr;
			htmlCode += "</div>";
			
		}
		
		// 회사 주소
		else if(cityNameRegExp.test(detectResultArr[i].toLowerCase())) {
			
			htmlCode += "<div class='input-group'id='input-group"+i+"'>";
				htmlCode += "<span class='input-group-addon'>" + "주소" + "</span>";
				htmlCode += "<textarea class='form-control' name='address' id='cardInfo_" + i + "' >" + detectResultArr[i] +  "</textarea>";
				htmlCode += delStr;
			htmlCode += "</div>";
			console.log("주소");
		}
		// 부서
		else if(departmentRegExp.test(detectResultArr[i].toLowerCase())) {
			
			htmlCode += "<div class='input-group'id='id='input-group"+i+"'>";
				htmlCode += "<span class='input-group-addon'>" + "부서" + "</span>";
				htmlCode += "<textarea class='form-control' name='department' id='cardInfo_" + i + "' >" + detectResultArr[i] +  "</textarea>";
				htmlCode += delStr;
			htmlCode += "</div>";
			console.log("부서");
		}
		// 직책 및 직급
		else if(jobRegExp.test(detectResultArr[i].toLowerCase())) {
			
			htmlCode += "<div class='input-group'id='input-group"+i+"'>";
				htmlCode += "<span class='input-group-addon'>" + "직책" + "</span>";
				htmlCode += "<textarea class='form-control' name='job' id='cardInfo_" + i + "' >" + detectResultArr[i] +  "</textarea>";
				htmlCode += delStr;
			htmlCode += "</div>";
			console.log("직책");
		}
		// 그 외
		else {
			

			keyName += "<select class='selectMenu' id='selectMenu_" + i + "'>";
				keyName += "<option>선택</option>";
				keyName += "<option value='name1'>이름(국문)</option>";
				keyName += "<option value='name2'>이름(영문)</option>";
				keyName += "<option value='name3'>이름</option>";
				keyName += "<option value='company'>회사</option>";
				keyName += "<option value='job'>직급</option>";
				keyName += "<option value='department'>부서</option>";
				keyName += "<option value='address'>주소</option>";
				keyName += "<option value='tel'>전화번호</option>";
				keyName += "<option value='phone'>휴대폰번호</option>";
				keyName += "<option value='email'>E-Mail</option>";
				keyName += "<option value='fax'>Fax</option>";
				keyName += "<option value='memo'>Memo</option>";
				keyName += "<option value='otherinfo'>Etc</option>";
				keyName += "</select>";
			
				htmlCode += "<div class='input-group' id='input-group"+i+"'>";
				htmlCode += "<span class='input-group-addon'>" + keyName + "</span>";
				
				
				
				htmlCode += "<textarea class='form-control' name='' id='cardInfo_" + i + "' >" + detectResultArr[i] +  "</textarea>";
				//항목삭제버튼 추가하기
				htmlCode += delStr;
			htmlCode += "</div>";
		}
		
		$("#cardInfo").append(htmlCode);
		
		// htmlCode 초기화
		htmlCode='';
		keyName='';
			
	}
	
}



	 
/*
 * @comment		:	입력 항목을 추가하는 메소드
 * @author		:	정보승
 */	 
function selectMenuAdd1() {
	
	
	var keyName_ = "";
	var htmlCode_ = "";
	
		keyName_ += "<select class='selectMenu' id='selectMenu_" + inputTypeNum + "'>";
		keyName_ += "<option>선택</option>";
		keyName_ += "<option value='name1'>이름(국문)</option>";
		keyName_ += "<option value='name2'>이름(영문)</option>";
		keyName_ += "<option value='name3'>이름</option>";
		keyName_ += "<option value='company'>회사</option>";
		keyName_ += "<option value='job'>직급</option>";
		keyName_ += "<option value='department'>부서</option>";
		keyName_ += "<option value='address'>주소</option>";
		keyName_ += "<option value='tel'>전화번호</option>";
		keyName_ += "<option value='phone'>휴대폰번호</option>";
		keyName_ += "<option value='email'>E-Mail</option>";
		keyName_ += "<option value='fax'>Fax</option>";
		keyName_ += "<option value='memo'>Memo</option>";
		keyName_ += "<option value='otherinfo'>Etc</option>";
		keyName_ += "</select>";
	
		htmlCode_ += "<div class='input-group' id='input-group"+inputTypeNum+"'>";
		htmlCode_ += "<span class='input-group-addon'>" + keyName_ + "</span>";
		htmlCode_ += "<textarea class='form-control' name='' id='cardInfo_" + inputTypeNum + "' >" +  "</textarea>";
		
	//삭제 버튼 추가 (여지원)
	htmlCode_ += "<div class ='delMenuOne' style='float:right;' > ";
	htmlCode_ += "<img class='delBtnClass' src='../resources/img/delBTN.png' onclick='delForm("+inputTypeNum+")'>" ;
	htmlCode_ +="</div>"
	htmlCode_ += "</div>";
	
	
	$("#cardInfo").append(htmlCode_);
	
	// htmlCode 초기화
	htmlCode_='';
	keyName_='';
	inputTypeNum++;
	
	$("#cardInfo").trigger("create");

}


//여지원: 내 명함들의 이미지및 정보들을 가지고와서 보여준뒤 선택하기 (서로주고받은 명함 표시를 위해)
function getMyCardsImg(){
	
	
	$.ajax({
		url: '../mycard/getMyCards'
		,type : 'get'
		,datatype :'json'
		,success: function(json){
			
			//나의 명함이 리스트에 없을 때
			if(json=='[]'){
				str ='<br><현재 등록된 나의 명함이 없습니다>';
				$(".selectMyCard").html(str);
				return ;
			}
			
			var str ='<br><상대방과의 명함 교환시 건낸 나의 명함은 무엇입니까?>';
			str += '<table>';
			str += '<tr>';
			$.each($.parseJSON(json), function(idx, item) {
				str += '<td><input type="radio" name="gaveMyCardRadio" value="' + item.mycardnum + '" checked /> </td>' ;	
			});	
			str += '</tr><tr>';
			$.each($.parseJSON(json), function(idx, item) {
				str+= '<td>'+ item.company +'</td>';	
			});	
			str += '</tr><tr>';
			$.each($.parseJSON(json), function(idx, item) {
				str+= '<td>'+ item.job +'</td>';
			});	
			str += '</tr><tr>';
			
			//이미지
			$.each($.parseJSON(json), function(idx, item) {
				var frontimage = item.frontimgoriginal
				str += '<td><img src="../mycard/download?mycardnum='+item.mycardnum+'" width="90px"></td>';
				
			});	
			str += '</tr>';
			str+= '</table>';
			console.log(str);
			$(".selectMyCard").html(str);
			
			
		}
		
		,error :function(e){
			alert(e);
		}
	})
}
	 
/*
 * @comment		:	동적으로 생성된 input 태그들의 name속성을 설정하고 Submit
 * @author		:	정보승
 */ 
function cardInfoSubmit() {
	
	
	//여지원-라디오박스로 선택된 mycardnum 과 성별
	var getmycardnum = $(".selectMyCard").find("input[type=radio]:checked").val();
	var getGender = $(".cardInfoMenu").find("input[type=radio]:checked").val();
	
	$(".selectMyCard").trigger("create");
	console.log("카드 번호 : " + getmycardnum);
	
	for(var i=0; i<inputTypeNum; i++) {
		
		// 동적으로 생성된 input 태그들의 name 속성에 selectBox의 value를 대입
		$("#cardInfo_"+i).attr("name", $("#selectMenu_"+i).val());
	}
	
	if(insertValid() === true) {
		return false;
	}
	else {
		
		$.ajax({
			 
			url : "insertYourCard"
			, type : "post"
			, data : {
				
				name1 : $("textarea[name*=name1]").val()
				, name2 : $("textarea[name*=name2]").val()
				, name3 : $("textarea[name*=name3]").val()
				, company : $("textarea[name*=company]").val()
				, job : $("textarea[name*=job]").val()
				, department : $("textarea[name*=department]").val()
				, address : $("textarea[name*=address]").val()
				, tel : $("textarea[name*=tel]").val()
				, phone : $("textarea[name*=phone]").val()
				, email : $("textarea[name*=email]").val()
				, fax : $("textarea[name*=fax]").val()
				, memo : $("textarea[name*=memo]").val()
				, otherinfo : $("textarea[name*=otherinfo]").val()
				, mycardnum : getmycardnum 				// 선택한 내 명함 번호
				, uploadImg :uploadFile					// original file name
				, sex : getGender
			}
			, success : function(resultMsg) {
		
				if(resultMsg == "true") {
					
					alert("성공적으로 등록되었습니다.");
					$("#cardInfo").html("");				// input tag 부분 날리기.
					$("#cardBtn").hide();					// submit 버튼 숨기기
				}
				else {
					
					alert("등록에 실패하였습니다.");
				}
			}
		});
	}
	
}

//폼에서 항목을 지우는 함수 -여지원
function delForm(i){
	
	var bool = confirm("이 항목을 지울까요?");
	if(!bool){
		return false;
	}
	$("#input-group"+i).remove();
	alert("#input-group"+i);
}


		