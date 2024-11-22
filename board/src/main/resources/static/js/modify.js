//remove 클릭시
//actionForm action 수정
const actionForm = document.querySelector("#actionForm");
document.querySelector(".btn-danger").addEventListener("click", () => {
  if (!confirm("정말 삭제하시겠습니까")) {
    return;
  }
  actionForm.action = "/board/remove";
  actionForm.submit();
});

//list 클릭시
//actionForm method 수정(get)
//gno 삭제
document.querySelector(".btn-info").addEventListener("click", () => {
  actionForm.method = "get";
  actionForm.querySelector("[name='bno']").remove();
  actionForm.querySelector("[name='writerEmail']").remove();
  actionForm.submit();
});
