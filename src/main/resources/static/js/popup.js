function openPopup() {
    var popup = window.open("/board/book-search", "도서 검색", "width=400, height=300");
    popup.focus();
}

var ele;
function selectElementInfo(selectedElement) {
    ele = selectedElement;
    console.log("선택한 요소: ", selectedElement);
    addDataToForm();
}

// 데이터를 테이블에 추가하는 함수
function addDataToForm() {

    let elements = ele.getElementsByTagName("td");
    let data = {
        imageURL: elements[0].innerText,
        title: elements[1].innerText,
        creator: elements[2].innerText
    };

    // TODO imageURL 추가
    document.getElementById("add_form_title").value = data.title;
    document.getElementById("add_form_writer").value = data.creator;
}