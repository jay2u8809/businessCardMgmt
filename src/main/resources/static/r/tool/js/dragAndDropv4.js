 // [start] 맵을 쓰기 위한 코드
Map = function(){
    this.map = new Object();
};   
Map.prototype = {   
    put : function(key, value){   
        this.map[key] = value;
    },   
    get : function(key){   
        return this.map[key];
    },
    containsKey : function(key){    
        return key in this.map;
    },
    containsValue : function(value){    
        for(var prop in this.map){
            if(this.map[prop] == value) return true;
        }
        return false;
    },
    isEmpty : function(key){    
        return (this.size() == 0);
    },
    clear : function(){   
        for(var prop in this.map){
            delete this.map[prop];
        }
    },
    remove : function(key){    
        delete this.map[key];
    },
    keys : function(){   
        var keys = new Array();   
        for(var prop in this.map){   
            keys.push(prop);
        }   
        return keys;
    },
    values : function(){   
        var values = new Array();   
        for(var prop in this.map){   
            values.push(this.map[prop]);
        }   
        return values;
    },
    size : function(){
        var count = 0;
        for (var prop in this.map) {
            count++;
        }
        return count;
    }
};
// [end] 맵을 쓰기 위한 코드


// 전역 변수 선언
var map_box = new Map();    //box를 담을 map
var count = 0;                  //각 텍스트박스에 id를 주기 위해 증가시킬 변수
var src = "";               //업로드 파일 경로 저장시킬 변수

// [start] 페이지 로딩 후 처리
$(document).ready(function(){

    //캔버스 변수 선언, 엘리멘트 연결
    var canvas = $("#canvas");

    // .tool을 드래그할 경우 드래그한 element를 복제한 helper 생성
    // (캔버스 위에 생성되는 textbox는 helper 속성을 물려받는다.)
    // $(".tool").draggable({
    //     helper: "clone"
    // });

    //파일 업로드 부분이 INNER HTML로 동적 생성되므로 레디함수$(DOCUMENT)로 초기화 해줘야 이벤트가 적용이 된다.
     $(document).on("mouseenter",".tool",function(){

        $(this).draggable({
           helper: "clone"
           //드래그를 시작할 때 동작할 내용
           ,start :function (event, ui) { 
              src = $(this).attr("src"); //경로를 가져오고 전역변수에 값을 넣는다.
              
           }
         
        });
    });  

    // 캔버스에 아이템 드랍 시 이벤트 처리
    canvas.droppable({
        drop: function(event, ui) {

            // node는 각 텍스트 상자, node를 상기 기본값으로 초기화한다.
            var id = 'box_' + count;

            // node 객체 생성
            var node = {
                id: id,
                position: ui.helper.position(),
                width: ui.helper.width,
                height: ui.helper.height
            };

            // node의 최초 위치 조정..?
            node.position.left -= canvas.position().right;
            node.position.top -= canvas.position().bottom;
           
            // 드랍한 아이템이 3가지 텍스트 메뉴 중 어느 것인지 판별해서 type에 저장
            if(ui.helper.hasClass("text")){
                node.type = "text";
            } else if(ui.helper.hasClass("icon")){
                node.type = "icon";
                $('#title').text(ui.helper.html());
                node.svgsrc =  ui.helper.children().children('img').attr('src');             
            } else if(ui.helper.hasClass("Template")){
               // node.type = "Template";
               // node.imgsrc = ui.helper.children().children('img').attr('src');
                var str = '<img class="background" src=' + src + '>';  // 템플릿 이미지 가져오기
                var output = "<img class='delete' src='resources/img/delBTN.png' onclick='delAction()'>"; //삭제 이미지 누를시
                $('#canvas').prepend(str).trigger("create"); //캔버스에 가장 뒤쪽에 위치시킨다.(background처럼 보이기 위해서)
                $('#output').append(output).trigger("create"); //캔버스에 가장 앞쪽에 위치시킨다.(background 위에 와야되기 때문에)
            } else if(ui.helper.hasClass("file")){ //file이란 클래스를 가지고 있을 경우 
                node.type = "file";
                node.filesrc = src; //src 전역변수 경로를 참조한다.
           //     var str = "<img class='background' src=\"" +  src + "\">"; 
           //    $('#canvas').prepend(str).trigger("create");
           //     $('#canvas').html(str).css("background-image","url("+src+")").css("width","610px").css("height","430px").trigger("create");
            } else {
                return;
            }

            // node를 map에 저장
            map_box.put(id, node);

            // 드랍으로 만든 node를 canvas 위에 그림
            renderbox(node);

            // node의 아이디를 구별하기 위해 1 증가
            count++;
        }
    });



    // 텍스트 상자나 옵션창이 아닌 곳 클릭하면.... 아무튼 효과 해제해버림...
    $('body').mousedown(function(e) {
        e.stopPropagation();

        if(!$(e.target).hasClass('textOption') && !$(e.target.parentNode.parentNode).hasClass('textOption')) {
            removeEdit();
            $('.onEdit').draggable('enable').resizable('disable')
                .removeClass('onEdit').prop("contenteditable", false).find(".ui-resizable-handle").hide();
            $('.onSelect').draggable('enable').resizable('disable').removeClass('onSelect')
                .find(".ui-resizable-handle").hide();
        }
    })

    // 백스페이스 누를 시 onSelect 박스 삭제!!!!
    $('body').keydown(function(e){
        if(e.keyCode == '8') {
            $('.onSelect').remove();
        }
    })

});
// [end] 페이지 로딩 후 처리

//TODO canvas 초기화
function init_canvas(diagram) {
    canvas.empty();
}

// [start] 텍스트 박스를 캔버스에 추가
function renderbox(node) {

    //alert(node.id == null);

    /*var $div_box;

    if(node.id == null) {
        // db에서 가져온 박스
        
    } else {
        // 새로 추가한 박스

    }*/

    // 위치, 크기 조절 이벤트 적용할 textbox
    var div_box = document.createElement('div');
    var img_box = document.createElement('img');

    // textfield에 기본 텍스트 삽입, 기본 스타일 적용
    // 변수명 그대로 textbox 클래스 부여, id는 파라미터 node의 id 적용
    $(div_box).addClass('div_box').attr('id', node.id);

    // node의 타입별 textfield 기본 폰트 크기 지정
    switch(node.type) {
        case "text": 
            $(div_box).addClass('textbox').text("Insert Your Text");
            break;
        case "icon": 
            $(div_box).text('').css({
                'width':'250px',
                'height':'150px;'
            });
            $(img_box).css({
                'width':'100%',
                'height':'100%'
            }).attr('src',node.svgsrc);
            break;
        //case "template": $(div_box).addClass('templatebox'); break;
        case "Template": 
            $(div_box).text('').css({
                'width':'550px',
                'height':'450px;'
            }).addClass("template", "template").trigger("create");
            $(img_box).css({
                'width':'100%',
                'height':'100%'
            }).attr('src',node.imgsrc);
            break;
        case "file": // node.type이 file을 가지고 있을 경우 
            $(div_box).text('').css({
                'width':'40px',
                'height':'20px;'
            });
            $(img_box).css({
                'width':'100%',
                'height':'100%'
            }).attr('src',node.filesrc).trigger("create");
            break;
    }

    // box 드래그, 리사이즈 초기화
    $(div_box).css({
        "position": "absolute",
        "top": node.position.top,
        "left": node.position.left,
        "width": node.width,
        "height": node.height
    }).draggable({
        // textbox 드래그 시 위치 이동 처리 (ui.helper는 이벤트의 대상)
        stop: function(event, ui) {
            var id = ui.helper.attr("id");
            var box = map_box.get(id);
            box.position.top = ui.position.top;
            box.position.left = ui.position.left;
        },
        drag: function(event, ui) {
            $('.div_whole_editor').css({
                "top": ui.position.top + $('#canvas').position().top + 30,
                "left": ui.position.left + $('#canvas').position().left
            });
        },
        containment: '#canvas'  // 캔버스 영역 밖으로 나가지 못하게 제한
    }).resizable({
        // textbox 크기 조절 처리
        stop: function(event, ui) {
            var id = ui.helper.attr("id");
            var box = map_box.get(id);
            box.width = $(this).width();
            box.height = $(this).height();
        },
        containment: "#canvas", // 캔버스 영역을 넘지 못하도록 제한
        disabled: true          // 리사이즈는 onSelect 상태인 박스만 가능하므로.. 초기 설정에는 disable
    });

    // canvas에 textbox 출력
    $(div_box).prepend(img_box).find(".ui-resizable-handle").hide();
    $(div_box).find(".ui-resizable-handle").hide();
    canvas.prepend(div_box);


    // textbox 마우스 다운 시 크기 조절 모드 + 전역 편집 모드
    $(div_box).mousedown(function(e) {
        //이벤트 bubble 제거
        e.stopPropagation();

        // 클릭한 대상이...
        if($(this).hasClass('onEdit')) {
            // 수정 중인 박스였다면 부분 옵션창을 끈다.
            //removeTextEdit();
        } else if ($(this).hasClass('onSelect')) {
            //removeTextToolbar();
        } else {
            // 아무 것도 아닌 박스였다면... 수정 중/선택 중인 다른 박스 모두 해제하고... 클릭한 대상에게 선택 중 효과 적용..
            $('.onEdit').draggable('enable').resizable('disable')
                .removeClass('onEdit').prop("contenteditable", false).find(".ui-resizable-handle").hide();
            $('.onSelect').draggable('enable').resizable('disable')
                .removeClass('onSelect').find(".ui-resizable-handle").hide();
            $(this).draggable('enable').resizable('enable')
                .addClass('onSelect').find(".ui-resizable-handle").show();
            
            removeEdit();
            createWholeEditor($(div_box));
            $('#selection').text($(this).position().top + ' / ' + $(this).position().left);
            insertTextToolbar();
        }

        //클릭시 ㅁ메뉴바 나오기
        /*$('#bold').click(function(){
            console.log($('.onSelect').css('font-weight'));
            if ($('.onSelect').css('font-weight') == 700) {
                    $('.onSelect').css('font-weight','normal');
            }else{
                $('.onSelect').css('font-weight','bold');
            }
        });*/
    });
    
    // textbox 더블클릭 시 텍스트 입력 + 선택 편집 모드로 전환(TODO 텍스트 입력에 따라 높이 자동 조절되도록, 너비는 직접 수정)
    $(div_box).dblclick(function(e){

        e.stopPropagation();

        // 텍스트박스가 아닌 경우 더블클릭은 미적용
        if (!$(this).hasClass('textbox')) return;

        // 텍스트박스가 아닌 경우 더블클릭은 미적용
        if (!$(this).hasClass('textbox')) return;

        // textbox 드래그 끔, 리사이즈 켬, 수정 중 css 적용
        $(div_box).draggable('disable').resizable('disable').removeClass('onSelect').addClass('onEdit')
            .find(".ui-resizable-handle").hide();

        // textfiled를 수정 가능하게 변경
        $(div_box).prop("contenteditable", true);

        // 마우스 업 이벤트 시 텍스트편집창 띄울 것인지 판단
        $(div_box).mouseup(function() {
            isTextboxHighlighted();
        });
    });

}
// [end] 텍스트 박스를 캔버스에 추가

// 폰트 크기 적용
function execFontSize(size, unit) {
    var spanString = document.createElement('span');
    $(spanString).html(getSelectionHtml()).css("font-size", size+unit);
    document.execCommand('insertHTML', false, spanString.outerHTML);
};

// 편집 옵션 창 제거
function removeEdit() {
    // 편집 옵션 창 제거
    $('.div_whole_editor').remove();
    $('.div_selection_editor').remove();

}
//지워도 됨
/*function getSelectionHtml() {
    var html = "";
    if (typeof window.getSelection != "undefined") {
        var sel = window.getSelection();
        if (sel.rangeCount) {
            var container = document.createElement("div");
            for (var i = 0, len = sel.rangeCount; i < len; ++i) {
                container.appendChild(sel.getRangeAt(i).cloneContents());
            }
            html = container.innerHTML;
        }
    } else if (typeof document.selection != "undefined") {
        if (document.selection.type == "Text") {
            html = document.selection.createRange().htmlText;
        }
    }
    return html;
}*/


// 텍스트 선택 시 텍스트박스 상단 편집창 팝업
function isTextboxHighlighted(e) {

    removeEdit();

    var sel = window.getSelection();

    if(sel.toString().length > 0) {
        createSelectionEditor($('.onEdit').position().top, $('.onEdit')
            .position().left);
    }
}

// [start] 텍스트 편집 옵션창 생성
function createWholeEditor($elem) {
    if($elem.hasClass('imagebox')) {
        
        // 사진 추가
        var $div_plus = $('<div />'); 
        var $bt_plus = $('<button />')
        var $i_plus = $('<i class="fas fa-plus"></i>');
        $bt_plus.append($i_plus);
        $bt_plus.click(function() {
            //TODO 이미지 추가, 편집
        });
        $div_plus.append($bt_plus);

        // 삭제
        var $div_delete = $('<div />'); 
        var $bt_delete = $('<button />')
        var $i_delete = $('<i class="fas fa-times"></i>');
        $bt_delete.append($i_delete);
        $bt_delete.click(function() {
            // TODO 삭제
        });
        $div_delete.append($bt_delete);

        // 필터 1
        var $div_align_right = $('<div />'); 
        var $bt_align_right = $('<button />')
        var $i_align_right = $('<i class="fas fa-align-right"></i>');
        $bt_align_right.append($i_align_right);
        $bt_align_right.click(function() {
            document.execCommand('justifyRight', false, null);
        });
        $div_align_right.append($bt_align_right);

        // 사진 편집 옵션창
        var $div_whole_editor = $('<div />');
        $div_whole_editor.addClass('imgOption').addClass('div_whole_editor').css({
            "position": "absolute",
            "top": $('.onSelect').position().top + $('#canvas').position().top + 30,
            "left": $('.onSelect').position().left + $('#canvas').position().left,
            "width": "150px",
            "height": "40px",
            "z-index": "1000"
        }).prop("contenteditable", false);

        // 효과 버튼 append
        $div_whole_editor.append($div_plus);
        $div_whole_editor.append($div_delete);
        $div_whole_editor.append($div_align_right);

        $('body').append($div_whole_editor);
        
        // 공통 클래스 적용.....
        $div_whole_editor.contents().addClass('imgOption');
        $div_whole_editor.contents().contents().addClass('imgOption');
    }
    
}
// [end] 전역 옵션창 생성
// [start] 부분 옵션창 생성
function createSelectionEditor(top, left) {
    
    // 왼쪽 정렬
    var $div_align_left = $('<div />'); 
    var $bt_align_left = $('<button />')
    var $i_align_left = $('<i class="fas fa-align-left"></i>');
    $bt_align_left.append($i_align_left);
    $bt_align_left.click(function() {
        document.execCommand('justifyLeft', false, null);
    });
    $div_align_left.append($bt_align_left);

    // 가운데 정렬
    var $div_align_center = $('<div />'); 
    var $bt_align_center = $('<button />')
    var $i_align_center = $('<i class="fas fa-align-center"></i>');
    $bt_align_center.append($i_align_center);
    $bt_align_center.click(function() {
        document.execCommand('justifyCenter', false, null);
    });
    $div_align_center.append($bt_align_center);

    // 오른쪽 정렬
    var $div_align_right = $('<div />'); 
    var $bt_align_right = $('<button />')
    var $i_align_right = $('<i class="fas fa-align-right"></i>');
    $bt_align_right.append($i_align_right);
    $bt_align_right.click(function() {
        document.execCommand('justifyRight', false, null);
    });
    $div_align_right.append($bt_align_right);

    // 볼드
    var $div_bold = $('<div />');
    var $bt_bold = $('<button />');
    var $i_bold = $('<i class="fas fa-bold"></i>');
    $bt_bold.append($i_bold);
    $bt_bold.click(function(e) {
        // TODO 매우 중요!! document.queryCommandState('bold') 
        document.execCommand('bold', false, null);
    });
    $div_bold.append($bt_bold);

    // 이탤릭
    var $div_italic = $('<div />');
    var $bt_italic = $('<button />');
    var $i_italic = $('<i class="fas fa-italic"></i>');
    $bt_italic.append($i_italic);
    $bt_italic.click(function(e) {
        // TODO 매우 중요!! document.queryCommandState('bold') 
        document.execCommand('italic', false, null);
    });
    $div_italic.append($bt_italic);

    // 밑줄
    var $div_underline = $('<div />');
    var $bt_underline = $('<button />');
    var $i_underline = $('<i class="fas fa-underline"></i>');
    $bt_underline.append($i_underline);
    $bt_underline.click(function(e) {
        // TODO 매우 중요!! document.queryCommandState('bold')
        document.execCommand('underline', false, null);
    });
    $div_underline.append($bt_underline);

    // 글자 색
    var $div_fontColor = $('<div />');
    var $bt_fontColor = $('<button />');
    var $i_font_white = $('<i class="fas fa-font"></i>');
    var $i_font_yellow = $('<i class="fas fa-font tonari"></i>');
    $i_font_yellow.css('color', 'yellow');
    $bt_fontColor.append($i_font_white).append($i_font_yellow).appendTo($div_fontColor);
    $bt_fontColor.click(function(e) {

        // TODO div 만들고 div 내에 색상 선택 옵션 보여주어야 함.

        // 글자 색 바꾸는 예제
        document.execCommand('foreColor', false, 'red');
    });


     // [start] TODO 폰트 크기
     var $div_fontsize = $('<div />');
     var bt_curr_fontsize = document.createElement('button');
     var div_select_fontsize = document.createElement('div');
     $(div_select_fontsize).css({
         "display": "none"
     });
     var arr_fontsize = ['9', '10', '11', '12', '14', '18', '24', '36', '60'];
     var arr_bt_fontsize = [];
     for (var i = 0; i < arr_fontsize.length; i++) {
         arr_bt_fontsize[i] = document.createElement('button');
         $(arr_bt_fontsize[i]).text(arr_fontsize[i]).click(function() {
             execFontSize($(this).text(), "px");
         }).appendTo(div_select_fontsize);
     }

     if(document.queryCommandState('fontSize') == false) {
         $(bt_curr_fontsize).text('16');
     } else {
         $(bt_curr_fontsize).text(document.queryCommandState('fontSize'));
     }
     $(bt_curr_fontsize).click(function(e) {
         // TODO 매우 중요!! document.queryCommandState('bold')
         if($(div_select_fontsize).css("display") == "block") {
             $(div_select_fontsize).css("display", "none");
         } else {
             $(div_select_fontsize).css("display", "block");
         }
     });

     $div_fontsize.append(bt_curr_fontsize).append(div_select_fontsize);
     // [end] TODO 폰트 크기

    // 텍스트 편집 옵션창
    var $div_selection_editor = $('<div />');
    $div_selection_editor.addClass('textOption').addClass('div_selection_editor').css({
        "position": "absolute",
        "top": top + $('#canvas').position().top + 30,
        "left": left + $('#canvas').position().left - 100,
    }).prop("contenteditable", false);

    // 효과 버튼 append
    $div_selection_editor.append($div_align_left);
    $div_selection_editor.append($div_align_center);
    $div_selection_editor.append($div_align_right);
    $div_selection_editor.append($div_bold);
    $div_selection_editor.append($div_italic);
    $div_selection_editor.append($div_underline);
    $div_selection_editor.append($div_fontColor);

    $('body').append($div_selection_editor);
    
    // 공통 클래스 적용.....
    $div_selection_editor.contents().addClass('textOption');
    $div_selection_editor.contents().contents().addClass('textOption');
    $div_selection_editor.contents().contents().contents().addClass('textOption');
}
// [end] 텍스트 부분 옵션창 생성

/*function createTextEdit(top, left) {
    
    // 왼쪽 정렬
    var bt_align_left = document.createElement('button');
    $(bt_align_left).addClass('fas fa-align-left');
    $(bt_align_left).click(function() {
        document.execCommand('justifyLeft', false, null);
    });

    // 가운데 정렬
    var bt_align_center = document.createElement('button');
    $(bt_align_center).addClass('fas fa-align-center');
    $(bt_align_center).click(function() {
        document.execCommand('justifyCenter', false, null);
    });

    // 오른쪽 정렬
    var bt_align_right = document.createElement('button');
    $(bt_align_right).addClass('fas fa-align-right');
    $(bt_align_right).click(function() {
        document.execCommand('justifyRight', false, null);
    });

    // 볼드
    var bt_bold = document.createElement('button');
    $(bt_bold).addClass('fas fa-bold');
    $(bt_bold).click(function(e) {
        // TODO 매우 중요!! document.queryCommandState('bold') 
        document.execCommand('bold', false, null);
    });

    // 이탤릭
    var bt_italic = document.createElement('button');
    $(bt_italic).addClass('fas fa-italic');
    $(bt_italic).click(function(e) {
        // TODO 매우 중요!! document.queryCommandState('bold') 
        document.execCommand('italic', false, null);
    });

    // 밑줄
    var bt_underline = document.createElement('button');
    $(bt_underline).addClass('fas fa-underline');
    $(bt_underline).click(function(e) {
        // TODO 매우 중요!! document.queryCommandState('bold') 
        document.execCommand('underline', false, null);
    });

    // [start] TODO 폰트 크기
    var div_fontsize = document.createElement('div');
    var bt_curr_fontsize = document.createElement('button');
    var div_select_fontsize = document.createElement('div');
    $(div_select_fontsize).css({
        "display": "none"
    });
    var arr_fontsize = ['9', '10', '11', '12', '14', '18', '24', '36', '60'];
    var arr_bt_fontsize = [];
    for (var i = 0; i < arr_fontsize.length; i++) {
        arr_bt_fontsize[i] = document.createElement('button');
        $(arr_bt_fontsize[i]).text(arr_fontsize[i]).click(function() {
            execFontSize($(this).text(), "px");
        }).appendTo(div_select_fontsize);
    }

    if(document.queryCommandState('fontSize') == false) {
        $(bt_curr_fontsize).text('16');
    } else {
        $(bt_curr_fontsize).text(document.queryCommandState('fontSize'));
    }
    $(bt_curr_fontsize).click(function(e) {
        // TODO 매우 중요!! document.queryCommandState('bold')
        if($(div_select_fontsize).css("display") == "block") {
            $(div_select_fontsize).css("display", "none");
        } else {
            $(div_select_fontsize).css("display", "block");
        }
    });

    $(div_fontsize).append(bt_curr_fontsize).append(div_select_fontsize);
    // [end] TODO 폰트 크기

    // 텍스트 편집 옵션창
    var div_textOption = $('<div />');
    $(div_textOption).addClass('textOption').addClass('div_select_editor').css({
        "position": "absolute",
        "top": top - $("#canvas").position().top+30,
        "left": left + $("#canvas").position().left-100,
        "z-index": "1000",
        "-webkit-user-select": "none",
        "user-select": "none",
        "animation": "updown 5s infinite",
        "background-color": "#000"
    }).prop("contenteditable", false).addClass('div_textOption');


    // 효과 버튼 append
    $(div_textOption).append(bt_align_left);
    $(div_textOption).append(bt_align_center);
    $(div_textOption).append(bt_align_right);
    $(div_textOption).append(bt_bold);
    $(div_textOption).append(bt_italic);
    $(div_textOption).append(bt_underline);
    $(div_textOption).append(div_fontsize);
    $('body').append(div_textOption);
    
    // 공통 클래스 적용.....
    $(div_textOption).contents().addClass('textOption');
    $(div_textOption).contents().contents().addClass('textOption');
    $(div_textOption).contents().contents().contents().addClass('textOption');
}*/
// [end] 텍스트 편집 옵션창 생성


//텍스트 toolbar
function insertTextToolbar(){
    $('.tool_text_button_group').css('display','block');
}

function removeTextToolbar(){
    $('.tool_text_button_group').css('display','none');   
}

// 텍스트 편집창 제거
function removeTextEdit() {
    $('.div_selection_editor').remove();
}

function save_div() {
    var target = document.getElementById('box_0');
    $(target).draggable('destroy').resizable('destroy');
    var clone = $(target.outerHTML);
    // clone.css('background-color', 'blue').draggable({
    //     // textbox 드래그 시 위치 이동 처리 (ui.helper는 이벤트의 대상)
    //     stop: function(event, ui) {
    //         var id = ui.helper.attr("id");
    //         var box = map_box.get(id);
    //         box.position.top = ui.position.top;
    //         box.position.left = ui.position.left;
    //     },
    //     containment: '#canvas'  // 캔버스 영역 밖으로 나가지 못하게 제한
    // });
    $(target).remove();
    renderbox(clone);
    //$('#canvas').append(clone);
    //alert(target.outerHTML);
    //$('#map_box').text();
}

//파일 다중 업로드 부분 
         function readURL(input) {
           
            // var sel_files = []; // 이미지가 담길 배열
            var id = 0; // 이미지의 아이디 값 
             var $input = $("div.content.file"); //작업 영역 div
             if (input.files) {
                for(var i in input.files){
                    if(isNaN(i)) {
                    return; 
                     }

                   
                var reader = new FileReader();
                  
                reader.onload = function (e) {
                    id++;
                    var src =  e.target.result; // 파일경로 
                    var html = "<div class='tool div_file'>";
                        html += "<img class='tool file'  file-data='" + id + "'  src=\"" +  src + "\"  onclick=\"moveToImg(\'" + src + "\'" + "," +  id + ")\"  >";
                        html += "</div>";
                    // sel_files.push(html);
                    // console.log(sel_files);
                    $input.append(html).trigger("create");
                    console.log(e);
                    
                };
                console.log(i);
                
                reader.readAsDataURL(input.files[i]);
                
                }


            }

        }
         
         //템플릿 삭제 함수
        function delAction() {
        	
        	var result = confirm('템플릿을 지우시겠습니까?'); 
        	
        	if(result) { 
        		//true
        		
        		$(".background").remove();//객체를 삭제
            	$(".delete").remove();//삭제 버튼을 삭제
            	alert("삭제완료");
        	} else {
        		//false
        		
        		alert("삭제취소");
        	}

        	
        	
        	
        } 
         
//         $(content).html2canvas({  
//        	    onrendered: function (canvas) {  
//        	      var img=canvas.toDataURL("image/png");
//        	      $('#img_val').val(img);  
//        	      //$("#target").html('<img src=' + img + '>');<=target 영역에 캡쳐한 이미지 표시하고 싶을때
//        	      var frm = document.captureForm;  
//        	  	    frm.action = root + "/capture.do";  
//        	  	    frm.submit();          
//        	    }  
//    	  });  
//         
         
         
//         //이미지 캡처 
//         function  html2img(){
//             var element = $(".Template");
//             html2canvas(element, {
//                   onrendered: function(canvas) {
//                
//                   getCanvas = canvas;
//                   upload();
//                   }
//            
//             });
//            
//           }
//         
//         //업로드 ajax
//         function upload(){
//        	 
//        	 var imageData = getCanvas.toDataURL("image/png");
//        	 var formData = new FormData();
//        	 formData.append('file', imageData);
//        	 
//        	 $.ajax({
//        		url : "tool/cature", 
//        		type : "POST",
//        		dataType : "json",
//        		data : formData,
//        		processData : false,
//        		contentType : false,
//        		success :  function (data) {
//        			
//        		},
//        		error : function (request, status, error) {
//        			
//        			console.log(request, status, error);
//        		}
//        		
//        		
//        	 });
//         }
//
