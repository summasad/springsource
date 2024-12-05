//삭제 클릭시 actionForm 전송
// 정말로 삭제하시겠습니까?
const actionForm = document.querySelector("#actionForm");
document.querySelector(".btn-danger").addEventListener("click", (e) => {
  if (confirm("정말로 삭제하시겠습니까")) {
    actionForm.action = "/book/remove";
    actionForm.submit();
  }
});

//목록 클릭시 a 태그 기능 중지
//actionForm 에서 아이디 요소 제거하기
//actrionForm method는 get으로 변경
//actionForm action은 list 변경
//actionForm submit
document.querySelector(".btn-secondary").addEventListener("click", (e) => {
  e.preventDefault;
  actionForm.querySelector("[name='id]").remove();
  actionForm.method = "get";
  actionForm.action = "/book/list";
  actionForm.submit();
});
