//modifyform 찾은 후 action -  /movie/remove 이동
const modifyForm = document.querySelector("#modifyForm");
if (modifyForm) {
  modifyForm.addEventListener("submit", (e) => {
    e.preventDefault();
    if (!confirm("삭제 하시겠습니까?")) {
      return;
    }
    modifyForm.action = "/movie/remove";
    modifyForm.submit();
  });
}
