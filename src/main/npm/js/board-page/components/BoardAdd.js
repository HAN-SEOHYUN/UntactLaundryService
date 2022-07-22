import React, {useCallback, useEffect, useRef, useState} from 'react';
import EditorComponent from "./Editor/EditorComponent";
// import QuillEditor from "./Editor/QuillEditor";
// import Home from "./Editor/Home";

// 에디터 내 이미지가 들어갈 시
// base64 포맷으로 들어감
// 해당 포맷은 용량이 너무 커서 db에 저장이 불가능
// 그래서 텍스터에디터의 움직임을 감지/이미지 경로(태그)를 찾고
// ajax로 이미지 업로드와 업로드 된 이미ㄴ의 경로를 써줄 거임
// 응 실패 => QuillEditor에서 관련된 이벤트를 제공함, 그걸 활용

const BoardAdd = ({userInfo, animateClass, addBtnOnClickEvent, contentList}) => {
    const [title, setTitle] = useState('');
    const [desc, setDesc] = useState('');
    const [maxiumText, setMaxiumText] = useState("");
    const inputRef = useRef([]);
    const maxTextByte = 4000;

    const onChange = useCallback((value) => {
        console.log(value);
        setDesc(value);
    });

    const getByteLengthOfString = function (s, b, i, c) {
        for (b = i = 0; c = s.charCodeAt(i++); b += c >> 11 ? 3 : c >> 7 ? 2 : 1) ;
        return b;
    };

    //ajax로 파일 업로드 처리하기!!
    const addBoard = useCallback((e) => {
        e.preventDefault();

        //유효성 체크
        if (title === "") {
            alert("제목을 입력해주세요.");
            inputRef.current["title"].focus();
            return;
        } else if (desc === "") {
            alert("내용을 입력해주세요.");
            inputRef.current["content"].focus();
            return;
        } else if (maxiumText >= maxTextByte) {
            alert("4,000byte 이내로 내용을 작성해주세요.");
            inputRef.current["content"].focus();
            return;
        }


        const formData = new FormData();    //ajax로 파일을 보내주기 위한 객체임, append로 값을 넣어줌, append는 키와 값

        const data = {  //일반 input값은 객체로 넣어줄 거임
            "title": $("input[name=title]").val(),
            "content": desc,
            "usersNo": userInfo.no,
            "categoryNo": categoryNo,
            "name": userInfo.name
        }

        const fileInput = $('input[type=file]');    //input type=file인 요소 선택
        console.log(fileInput[0].files);    //해당 요소의 파일을 꺼내줄 거임

        for (let i = 0; i < fileInput[0].files.length; i++)  //해당 요소에 들어있는 파일 개수에 따라 반복문 처리임
            formData.append("file", fileInput[0].files[i]); //file이라는 이름으로 파일을 formdata객체에 넣어줌

        console.log(formData);

        // 'key'라는 이름으로 위에서 담은 data를 formData에 append한다. type은 json
        // 이거는 일반 input의 값을 담아 준 객체를 formData에 넣어주는 거임
        formData.append('key', new Blob([JSON.stringify(data)], {type: "application/json"}));

        $.ajax({
            url: "/launer/board/upload",
            method: "POST",
            data: formData,
            contentType: false,     //contentType : false 로 선언 시 content-type 헤더가 multipart/form-data로 전송되게 함
            processData: false,     //processData : false로 선언 시 formData를 string으로 변환하지 않음
            success: (res) => {
                alert(res.msg);

                if (res.SUCCESS) {
                    contentList();
                    addBtnOnClickEvent();
                } else {
                    window.location.reload();
                }
            },
            error: (xhr, status, error) => alert(`error : ${error}`)
        });
    });

    const maximumTextUpdate = useCallback(() => {
        setMaxiumText(getByteLengthOfString(desc));
    });

    useEffect(() => {
        maximumTextUpdate();
    }, [desc]);


    return (
        <div className={animateClass === true ? "board-add-component dropdown" : "board-add-component dropUp"}>
            <form method="get" name="board-form" encType="multipart/form-data" onSubmit={addBoard}>
                <input value={title} ref={(el) => inputRef.current["title"] = el}
                       onChange={e => setTitle(e.target.value)} name="title" placeholder="제목을 입력하세요."/>
                <input className="upload-name" type="file" placeholder="첨부파일" multiple/>
                <EditorComponent ref={(el) => inputRef.current["content"] = el} onChange={onChange}/>
                <span>{maxiumText} / {maxTextByte} Byte</span>
                <button type="submit"><i className="fa-solid fa-plus"></i></button>
            </form>
        </div>
    );
};

export default BoardAdd;