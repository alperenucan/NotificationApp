/* Get Elements */

let fileInput = document.getElementById("file-upload");
let check = document.getElementById("check");
let close = document.getElementById("close");

/* OnChange event */
fileInput.addEventListener("change", func);

/* Tick or cross notification */

function func() {

    /* If any file chosen tick will show cross won't show  */
    if(fileInput.files.length > 0) {
        check.style.display = "block";
        close.style.display = "none";
    } else {
        check.style.display = "none";
        close.style.display = "block";
    }
}