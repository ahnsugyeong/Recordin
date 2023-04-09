// 부모 창으로 element 전달
// Chrome의 보안 정책 때문에, 팝업 창을 닫는 용도로 생성된 js 파일

function selectElement(selectedElement) {
    window.opener.selectElementInfo(selectedElement);
    window.close();
}