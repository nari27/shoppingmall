document.addEventListener("DOMContentLoaded", function() {
    // 서버에서 전달된 board 객체가 존재하는지 확인
    const boardTitle = document.querySelector(".post-title");
    const boardUsername = document.querySelector(".author strong");
    const boardCreatedDate = document.querySelector(".date strong");
    const boardViewCount = document.querySelector(".views strong");
    const boardContent = document.querySelector(".post-content p");

    // 데이터가 있을 경우, 값 설정
    if (boardTitle) {
        boardTitle.textContent = document.getElementById("boardTitle").textContent;
    }
    if (boardUsername) {
        boardUsername.textContent = document.getElementById("boardUsername").textContent;
    }
    if (boardCreatedDate) {
        boardCreatedDate.textContent = document.getElementById("boardCreatedDate").textContent;
    }
    if (boardViewCount) {
        boardViewCount.textContent = document.getElementById("boardViewCount").textContent;
    }
    if (boardContent) {
        boardContent.textContent = document.getElementById("boardContent").textContent;
    }
});

// 댓글 수정 버튼 클릭 시, 수정 폼을 보여주는 함수
function editComment(commentId) {
    
    var contentElement = document.getElementById('content_' + commentId);
    var editForm = document.getElementById('editForm_' + commentId);
    var textarea = document.getElementById('editContent_' + commentId); 

    // 댓글 내용을 텍스트 영역에 넣어주고 수정 폼을 보여줍니다.
    textarea.value = contentElement.innerText.trim();
    editForm.style.display = 'block';
    contentElement.style.display = 'none';
}


function cancelEdit(commentId) {
    var contentElement = document.getElementById('content_' + commentId);
    var editForm = document.getElementById('editForm_' + commentId);

    // 수정 폼 숨기기
    editForm.style.display = 'none';

    // 원래 댓글 내용 다시 보이게 하기
    contentElement.style.display = 'block';
}