function openPopup() {
    var selectBox = document.getElementById("category");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    var path = ""
    console.log("선택한 요소: ", selectedValue);

      if (selectedValue === "BOOK") {
        path = "/board/book-search";
      } else if (selectedValue === "MOVIE") {
        path = "/board/movie-search";
      }
    var popup = window.open(path, "도서/영화 검색", "width=500, height=500");
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
    document.getElementById("add_form_creator").value = data.creator;
}

//카테고리 버튼에 따라 도서 검색 or 영화 검색 선택
function getPath(path) {
  var selectBox = document.getElementById("category");
  var selectedValue = selectBox.options[selectBox.selectedIndex].value;
  console.log("선택한 요소: ", selectedValue);

  if (selectedValue === "BOOK") {
    path = "/board/book-search";
  } else if (selectedValue === "MOVIE") {
    path = "/board/movie-search";
  }
}

