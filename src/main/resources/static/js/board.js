// 게시글 클릭 시 조회수 증가 함수
async function increaseViewCount(boardId) {
    try {
        await fetch(`/api/board/${boardId}/increaseView`, {
            method: 'POST'
        });
    } catch (error) {
        console.error(`게시글 ${boardId} 조회수 증가 실패`, error);
    }
}

// 서버에서 게시글 목록과 작성자, 작성일, 조회수를 받아와서 테이블에 추가
async function loadBoardList() {
    try {
        const response = await fetch('/api/board'); // 게시글 목록 API 호출
        const boardData = await response.json();
		console.log("게시글 데이터:", boardData); // 콘솔에서 확인
		
        const boardList = document.getElementById('boardList');
        boardList.innerHTML = ''; // 기존 목록 비우기

		boardData.forEach((board, index) => {
		            const tr = document.createElement('tr');

		            const tdIndex = document.createElement('td');
		            tdIndex.textContent = board.boardId;

		            const tdTitle = document.createElement('td');
		            const a = document.createElement('a');
		            a.href = `/board/${board.boardId}`;
		            a.textContent = board.title;
		            a.addEventListener('click', async (event) => {
		                event.preventDefault(); // 기본 링크 이동 방지
		                await increaseViewCount(board.boardId); // 조회수 증가 API 호출
		                window.location.href = `/board/${board.boardId}`; // 페이지 이동
		            });

		            const tdAuthor = document.createElement('td');
		            tdAuthor.textContent = board.username;

					const tdDate = document.createElement('td');
					tdDate.textContent = board.createdDate.replace("T", " "); // 여기서 날짜 포맷을 변경합니다.
					  
		            const tdViews = document.createElement('td');
		            tdViews.textContent = board.viewCount;

		            tdTitle.appendChild(a);
		            tr.appendChild(tdIndex);
		            tr.appendChild(tdTitle);
		            tr.appendChild(tdAuthor);
		            tr.appendChild(tdDate);
		            tr.appendChild(tdViews);

		            boardList.appendChild(tr);
		        });
		    } catch (error) {
		        console.error('게시글 목록을 불러오는 데 실패했습니다.', error);
		    }
		}

		// 페이지 로드 시 로그인 상태 확인 및 게시글 목록 불러오기
		document.addEventListener('DOMContentLoaded', async () => {
		    const sessionId = getCookie('JSESSIONID');
		    const userLoggedIn = sessionId ? true : false;
		    updateBoardPage(userLoggedIn);
		    await loadBoardList();
	});
	
