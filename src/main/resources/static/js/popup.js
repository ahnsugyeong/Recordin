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

// 선택된 데이터를 add form 에 추가하는 함수
function addDataToForm() {

    let elements = ele.getElementsByTagName("td");
    console.log("선택한 이미지 태그: ", elements[0].querySelector('img').getAttribute('src'));
    console.log("선택한 td 태그: ", elements[1].innerText);
    console.log("선택한 td 태그: ", elements[2].innerText);

    let data = {
        imageURL: elements[0].querySelector('img').getAttribute('src'),
        title: elements[1].innerText,
        creator: elements[2].innerText
    };

    document.getElementById("add_form_imageURL").value = data.imageURL;
    document.getElementById("add_form_title").value = data.title;
    document.getElementById("add_form_writer").value = data.creator;
}